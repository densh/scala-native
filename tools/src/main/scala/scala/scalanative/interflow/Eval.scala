package scala.scalanative
package interflow

import scala.collection.mutable
import scalanative.nir._
import scalanative.linker._
import scalanative.codegen.MemoryLayout
import scalanative.util.{unreachable, And}

trait Eval { self: Interflow =>
  def run(insts: Array[Inst], offsets: Map[Local, Int], from: Local)(
      implicit state: State): Inst.Cf = {
    import state.{materialize, delay}

    var pc = offsets(from) + 1

    while (true) {
      val inst = insts(pc)
      def bailOut =
        throw BailOut("can't eval inst: " + inst.show)
      inst match {
        case _: Inst.Label =>
          unreachable
        case Inst.Let(local, op, unwind) =>
          if (unwind ne Next.None) {
            throw BailOut("try-catch")
          }
          val value = eval(local, op)
          if (value.ty == Type.Nothing) {
            return Inst.Unreachable(unwind)
          } else {
            val shortUnitValue =
              if (value.ty == Type.Unit) Val.Unit else value
            state.storeLocal(local, shortUnitValue)
            pc += 1
          }
        case Inst.Ret(v) =>
          return Inst.Ret(eval(v))
        case Inst.Jump(Next.Label(target, args)) =>
          val evalArgs = args.map(eval)
          val next     = Next.Label(target, evalArgs)
          return Inst.Jump(next)
        case Inst.If(cond,
                     Next.Label(thenTarget, thenArgs),
                     Next.Label(elseTarget, elseArgs)) =>
          def thenNext =
            Next.Label(thenTarget, thenArgs.map(eval))
          def elseNext =
            Next.Label(elseTarget, elseArgs.map(eval))
          val next = eval(cond) match {
            case Val.True =>
              return Inst.Jump(thenNext)
            case Val.False =>
              return Inst.Jump(elseNext)
            case cond =>
              return Inst.If(materialize(cond), thenNext, elseNext)
          }
        case Inst.Switch(scrut,
                         Next.Label(defaultTarget, defaultArgs),
                         cases) =>
          def defaultNext =
            Next.Label(defaultTarget, defaultArgs.map(eval))
          eval(scrut) match {
            case value if value.isCanonical =>
              cases
                .collectFirst {
                  case Next.Case(caseValue, Next.Label(caseTarget, caseArgs))
                      if caseValue == value =>
                    val evalArgs = caseArgs.map(eval)
                    val next     = Next.Label(caseTarget, evalArgs)
                    return Inst.Jump(next)
                }
                .getOrElse {
                  return Inst.Jump(defaultNext)
                }
            case scrut =>
              return Inst.Switch(materialize(scrut), defaultNext, cases)
          }
        case Inst.Throw(v, unwind) =>
          val excv = eval(v)
          unwind match {
            case Next.None =>
              return Inst.Throw(excv, Next.None)
            case Next.Unwind(Val.Local(exc, _), Next.Label(name, args)) =>
              state.storeLocal(exc, excv)
              val eargs = args.map(eval)
              val next  = Next.Label(name, eargs)
              return Inst.Jump(next)
            case _ =>
              unreachable
          }
        case Inst.Unreachable(unwind) =>
          unwind match {
            case Next.None =>
              return Inst.Unreachable(unwind)
            case Next.Unwind(Val.Local(exc, _), Next.Label(name, args)) =>
              val eexc = Val.Local(state.fresh(), Rt.Object)
              state.storeLocal(exc, eexc)
              val eargs = args.map(eval)
              return Inst.Unreachable(
                Next.Unwind(eexc, Next.Label(name, eargs)))
            case _ =>
              unreachable
          }
      }
    }

    unreachable
  }

  def eval(local: Local, op: Op)(implicit state: State,
                                 linked: linker.Result): Val = {
    import state.{emit, materialize, delay}
    def bailOut =
      throw BailOut("can't eval op: " + op.show)
    op match {
      case Op.Call(sig, meth, args) =>
        eval(meth) match {
          case Val.Global(name, _) if intrinsics.contains(name) =>
            intrinsic(local, sig, name, args)
          case emeth =>
            val eargs = args.map(eval)
            val argtys = eargs.map {
              case VirtualRef(_, cls, _) =>
                cls.ty
              case DelayedRef(op) =>
                op.resty
              case value =>
                value.ty
            }

            val (dsig, dtarget) = emeth match {
              case Val.Global(name, _) =>
                visitDuplicate(name, argtys)
                  .map { defn =>
                    (defn.ty, Val.Global(defn.name, Type.Ptr))
                  }
                  .getOrElse {
                    (sig, emeth)
                  }
              case _ =>
                (sig, emeth)
            }

            def fallback = {
              val mtarget = materialize(dtarget)
              val margs   = eargs.map(materialize)

              val isDuplicate =
                mtarget match {
                  case Val.Global(Global.Member(_, _: Sig.Duplicate), _) =>
                    true
                  case _ =>
                    false
                }

              val cargs =
                if (!isDuplicate) {
                  margs
                } else {
                  val Type.Function(sigtys, _) = dsig

                  // Method target might have a more precise signature
                  // than what's known currently available at the call site.
                  // This is a side effect of a method target selection taking
                  // into account which classes are allocated across whole program.
                  margs.zip(sigtys).map {
                    case (marg, ty) =>
                      if (!Sub.is(marg.ty, ty)) {
                        delay(Op.Conv(Conv.Bitcast, ty, marg))
                      } else {
                        marg
                      }
                  }
                }

              emit(Op.Call(dsig, mtarget, cargs))
            }

            dtarget match {
              case Val.Global(name, _) if shallInline(name, eargs) =>
                inline(name, eargs)
              case _ =>
                fallback
            }
        }
      case Op.Load(ty, ptr) =>
        emit(Op.Load(ty, materialize(eval(ptr))))
      case Op.Store(ty, ptr, value) =>
        emit(Op.Store(ty, materialize(eval(ptr)), materialize(eval(value))))
      case Op.Elem(ty, ptr, indexes) =>
        delay(Op.Elem(ty, eval(ptr), indexes.map(eval)))
      case Op.Extract(aggr, indexes) =>
        delay(Op.Extract(eval(aggr), indexes))
      case Op.Insert(aggr, value, indexes) =>
        delay(Op.Insert(eval(aggr), eval(value), indexes))
      case Op.Stackalloc(ty, n) =>
        emit(Op.Stackalloc(ty, materialize(eval(n))))
      case op @ Op.Bin(bin, ty, l, r) =>
        (eval(l), eval(r)) match {
          case (l, r) if l.isCanonical && r.isCanonical =>
            eval(bin, ty, l, r)
          case (l, r) if Op.Bin(bin, ty, l, r).isPure =>
            delay(Op.Bin(bin, ty, l, r))
          case (l, r) =>
            emit(Op.Bin(bin, ty, materialize(l), materialize(r)))
        }
      case Op.Comp(comp, ty, l, r) =>
        (comp, eval(l), eval(r)) match {
          case (_, l, r) if l.isCanonical && r.isCanonical =>
            eval(comp, ty, l, r)

          // Two virtual allocations will compare equal if
          // and only if they have the same virtual address.
          case (Comp.Ieq, Val.Virtual(l), Val.Virtual(r))
              if state.isVirtual(l) && state.isVirtual(r) =>
            Val.Bool(l == r)
          case (Comp.Ine, Val.Virtual(l), Val.Virtual(r))
              if state.isVirtual(l) && state.isVirtual(r) =>
            Val.Bool(l != r)

          // Not-yet-materialized virtual allocation will never be
          // the same as already existing allocation (be it null
          // or any other value).
          case (Comp.Ieq, Val.Virtual(addr), r) if state.isVirtual(addr) =>
            Val.False
          case (Comp.Ieq, l, Val.Virtual(addr)) if state.isVirtual(addr) =>
            Val.False
          case (Comp.Ine, Val.Virtual(addr), r) if state.isVirtual(addr) =>
            Val.True
          case (Comp.Ine, l, Val.Virtual(addr)) if state.isVirtual(addr) =>
            Val.True

          // Comparing non-nullable value with null will always
          // yield the same result.
          case (Comp.Ieq, v @ Of(ty: Type.RefKind), Val.Null)
              if !ty.isNullable =>
            Val.False
          case (Comp.Ieq, Val.Null, v @ Of(ty: Type.RefKind))
              if !ty.isNullable =>
            Val.False
          case (Comp.Ine, v @ Of(ty: Type.RefKind), Val.Null)
              if !ty.isNullable =>
            Val.True
          case (Comp.Ine, Val.Null, v @ Of(ty: Type.RefKind))
              if !ty.isNullable =>
            Val.True

          // Comparing two non-null module references will
          // yield true only if it's the same module.
          case (Comp.Ieq,
                l @ Of(And(lty: Type.RefKind, ClassRef(lcls))),
                r @ Of(And(rty: Type.RefKind, ClassRef(rcls))))
              if !lty.isNullable && lty.isExact && lcls.isModule
                && !rty.isNullable && rty.isExact && rcls.isModule =>
            Val.Bool(lcls.name == rcls.name)
          case (Comp.Ine,
                l @ Of(And(lty: Type.RefKind, ClassRef(lcls))),
                r @ Of(And(rty: Type.RefKind, ClassRef(rcls))))
              if !lty.isNullable && lty.isExact && lcls.isModule
                && !rty.isNullable && rty.isExact && rcls.isModule =>
            Val.Bool(lcls.name != rcls.name)

          case (_, l, r) =>
            delay(Op.Comp(comp, ty, l, r))
        }
      case Op.Conv(conv, ty, value) =>
        eval(value) match {
          case value if value.isCanonical =>
            eval(conv, ty, value)
          case value =>
            delay(Op.Conv(conv, ty, value))
        }
      case Op.Classalloc(ClassRef(cls)) =>
        Val.Virtual(state.allocClass(cls))
      case Op.Fieldload(ty, obj, name @ FieldRef(cls, fld)) =>
        eval(obj) match {
          case VirtualRef(_, _, values) =>
            values(fld.index)
          case obj =>
            emit(Op.Fieldload(ty, materialize(obj), name))
        }
      case Op.Fieldstore(ty, obj, name @ FieldRef(cls, fld), value) =>
        eval(obj) match {
          case VirtualRef(_, _, values) =>
            values(fld.index) = eval(value)
            Val.Unit
          case obj =>
            emit(Op
              .Fieldstore(ty, materialize(obj), name, materialize(eval(value))))
        }
      case Op.Method(rawObj, sig) =>
        val obj = eval(rawObj)
        def fallback(targets: Iterable[Global]) = {
          targets.foreach(visitRoot)
          emit(Op.Method(materialize(obj), sig))
        }
        val objty = obj match {
          case InstanceRef(ty) =>
            ty
          case _ =>
            obj.ty
        }
        val targets = objty match {
          case Type.Null =>
            Seq.empty
          case ExactClassRef(cls, _) =>
            cls.resolve(sig).toSeq
          case ScopeRef(scope) =>
            scope.targets(sig)
          case _ =>
            bailOut
        }
        targets match {
          case Seq(meth) =>
            Val.Global(meth, Type.Ptr)
          case _ =>
            val res = fallback(targets)
            if (targets.isEmpty) Val.Zero(Type.Nothing) else res
        }
      case Op.Dynmethod(obj, dynsig) =>
        linked.dynimpls.foreach {
          case impl @ Global.Member(_, sig) if sig.toProxy == dynsig =>
            visitRoot(impl)
          case _ =>
            ()
        }
        emit(Op.Dynmethod(materialize(eval(obj)), dynsig))
      case Op.Module(clsName) =>
        val init = clsName member Sig.Ctor(Seq.empty)
        if (originals.contains(init)) {
          visitRoot(init)
        }
        val emptyCtor =
          if (!shallVisit(init)) {
            true
          } else {
            visitDuplicate(init, argumentTypes(init)).fold {
              false
            } { defn =>
              defn.insts match {
                case Seq(_: Inst.Label, _: Inst.Ret) =>
                  true
                case _ =>
                  false
              }
            }
          }
        val isWhitelisted =
          UseDef.pureWhitelist.contains(clsName)
        val canDelay =
          emptyCtor || isWhitelisted

        if (canDelay) {
          delay(Op.Module(clsName))
        } else {
          emit(Op.Module(clsName))
        }
      case Op.As(ty, rawObj) =>
        val refty = ty match {
          case ty: Type.RefKind => ty
          case _                => bailOut
        }
        val obj = eval(rawObj)
        def fallback =
          emit(Op.As(ty, materialize(obj)))
        val objty = obj match {
          case InstanceRef(ty) =>
            ty
          case _ =>
            obj.ty
        }
        objty match {
          case Type.Null =>
            Val.Null
          case ScopeRef(scope) if Sub.is(scope, refty) =>
            obj
          case _ =>
            fallback
        }
      case Op.Is(ty, rawObj) =>
        val refty = ty match {
          case ty: Type.RefKind => ty
          case _                => bailOut
        }
        val obj = eval(rawObj)
        def fallback =
          delay(Op.Is(refty, obj))
        def objNotNull =
          delay(Op.Comp(Comp.Ine, Rt.Object, obj, Val.Null))
        val objty = obj match {
          case InstanceRef(ty) =>
            ty
          case _ =>
            obj.ty
        }
        objty match {
          case Type.Null =>
            Val.False
          case And(scoperef: Type.RefKind, ScopeRef(scope)) =>
            if (Sub.is(scope, refty)) {
              if (!scoperef.isNullable) {
                Val.True
              } else {
                objNotNull
              }
            } else if (scoperef.isExact) {
              Val.False
            } else {
              fallback
            }
          case _ =>
            fallback
        }
      case Op.Copy(v) =>
        eval(v)
      case Op.Sizeof(ty) =>
        Val.Long(MemoryLayout.sizeOf(ty))
      case Op.Box(Type.Ref(boxname, _, _), value) =>
        Val.Virtual(state.allocBox(boxname, eval(value)))
      case Op.Unbox(boxty @ Type.Ref(boxname, _, _), value) =>
        eval(value) match {
          case VirtualRef(_, cls, Array(value)) if boxname == cls.name =>
            value
          case value =>
            emit(Op.Unbox(boxty, materialize(value)))
        }
      case Op.Arrayalloc(ty, init) =>
        eval(init) match {
          case Val.Int(count) if count <= 128 =>
            Val.Virtual(state.allocArray(ty, count))
          case Val.ArrayValue(_, values) if values.size <= 128 =>
            val addr     = state.allocArray(ty, values.size)
            val instance = state.derefVirtual(addr)
            values.zipWithIndex.foreach {
              case (v, idx) =>
                instance.values(idx) = v
            }
            Val.Virtual(addr)
          case init =>
            emit(Op.Arrayalloc(ty, materialize(init)))
        }
      case Op.Arrayload(ty, arr, idx) =>
        (eval(arr), eval(idx)) match {
          case (VirtualRef(_, _, values), Val.Int(offset))
              if inBounds(values, offset) =>
            values(offset)
          case (arr, idx) =>
            emit(Op.Arrayload(ty, materialize(arr), materialize(idx)))
        }
      case Op.Arraystore(ty, arr, idx, value) =>
        (eval(arr), eval(idx)) match {
          case (VirtualRef(_, _, values), Val.Int(offset))
              if inBounds(values, offset) =>
            values(offset) = eval(value)
            Val.Unit
          case (arr, idx) =>
            emit(
              Op.Arraystore(ty,
                            materialize(arr),
                            materialize(idx),
                            materialize(eval(value))))
        }
      case Op.Arraylength(arr) =>
        eval(arr) match {
          case VirtualRef(_, _, values) =>
            Val.Int(values.length)
          case arr =>
            emit(Op.Arraylength(materialize(arr)))
        }
      case Op.Var(ty) =>
        Val.Local(state.newVar(ty), Type.Var(ty))
      case Op.Varload(slot) =>
        val Val.Local(local, _) = eval(slot)
        state.loadVar(local)
      case Op.Varstore(slot, value) =>
        val Val.Local(local, _) = eval(slot)
        state.storeVar(local, eval(value))
        Val.Unit
    }
  }

  def eval(bin: Bin, ty: Type, l: Val, r: Val)(implicit state: State): Val = {
    import state.{emit, materialize}
    def fallback =
      emit(Op.Bin(bin, ty, materialize(l), materialize(r)))
    def bailOut =
      throw BailOut(s"can't eval bin op: $bin[${ty.show}] ${l.show}, ${r.show}")
    bin match {
      case Bin.Iadd =>
        (l, r) match {
          case (Val.Int(l), Val.Int(r))   => Val.Int(l + r)
          case (Val.Long(l), Val.Long(r)) => Val.Long(l + r)
          case _                          => bailOut
        }
      case Bin.Fadd =>
        (l, r) match {
          case (Val.Float(l), Val.Float(r))   => Val.Float(l + r)
          case (Val.Double(l), Val.Double(r)) => Val.Double(l + r)
          case _                              => bailOut
        }
      case Bin.Isub =>
        (l, r) match {
          case (Val.Int(l), Val.Int(r))   => Val.Int(l - r)
          case (Val.Long(l), Val.Long(r)) => Val.Long(l - r)
          case _                          => bailOut
        }
      case Bin.Fsub =>
        (l, r) match {
          case (Val.Float(l), Val.Float(r))   => Val.Float(l - r)
          case (Val.Double(l), Val.Double(r)) => Val.Double(l - r)
          case _                              => bailOut
        }
      case Bin.Imul =>
        (l, r) match {
          case (Val.Int(l), Val.Int(r))   => Val.Int(l * r)
          case (Val.Long(l), Val.Long(r)) => Val.Long(l * r)
          case _                          => bailOut
        }
      case Bin.Fmul =>
        (l, r) match {
          case (Val.Float(l), Val.Float(r))   => Val.Float(l * r)
          case (Val.Double(l), Val.Double(r)) => Val.Double(l * r)
          case _                              => bailOut
        }
      case Bin.Sdiv =>
        (l, r) match {
          case (Val.Int(l), Val.Int(r)) =>
            if (r != 0) {
              Val.Int(l / r)
            } else {
              fallback
            }
          case (Val.Long(l), Val.Long(r)) =>
            if (r != 0L) {
              Val.Long(l / r)
            } else {
              fallback
            }
          case _ =>
            bailOut
        }
      case Bin.Udiv =>
        (l, r) match {
          case (Val.Int(l), Val.Int(r)) =>
            if (r != 0) {
              Val.Int(java.lang.Integer.divideUnsigned(l, r))
            } else {
              fallback
            }
          case (Val.Long(l), Val.Long(r)) =>
            if (r != 0) {
              Val.Long(java.lang.Long.divideUnsigned(l, r))
            } else {
              fallback
            }
          case _ =>
            bailOut
        }
      case Bin.Fdiv =>
        (l, r) match {
          case (Val.Float(l), Val.Float(r))   => Val.Float(l / r)
          case (Val.Double(l), Val.Double(r)) => Val.Double(l / r)
          case _                              => bailOut
        }
      case Bin.Srem =>
        (l, r) match {
          case (Val.Int(l), Val.Int(r)) =>
            if (r != 0) {
              Val.Int(l % r)
            } else {
              fallback
            }
          case (Val.Long(l), Val.Long(r)) =>
            if (r != 0L) {
              Val.Long(l % r)
            } else {
              fallback
            }
          case _ =>
            bailOut
        }
      case Bin.Urem =>
        (l, r) match {
          case (Val.Int(l), Val.Int(r)) =>
            if (r != 0) {
              Val.Int(java.lang.Integer.remainderUnsigned(l, r))
            } else {
              fallback
            }
          case (Val.Long(l), Val.Long(r)) =>
            if (r != 0L) {
              Val.Long(java.lang.Long.remainderUnsigned(l, r))
            } else {
              fallback
            }
          case _ =>
            bailOut
        }
      case Bin.Frem =>
        (l, r) match {
          case (Val.Float(l), Val.Float(r))   => Val.Float(l % r)
          case (Val.Double(l), Val.Double(r)) => Val.Double(l % r)
          case _                              => bailOut
        }
      case Bin.Shl =>
        (l, r) match {
          case (Val.Int(l), Val.Int(r))   => Val.Int(l << r)
          case (Val.Long(l), Val.Long(r)) => Val.Long(l << r)
          case _                          => bailOut
        }
      case Bin.Lshr =>
        (l, r) match {
          case (Val.Int(l), Val.Int(r))   => Val.Int(l >>> r)
          case (Val.Long(l), Val.Long(r)) => Val.Long(l >>> r)
          case _                          => bailOut
        }
      case Bin.Ashr =>
        (l, r) match {
          case (Val.Int(l), Val.Int(r))   => Val.Int(l >> r)
          case (Val.Long(l), Val.Long(r)) => Val.Long(l >> r)
          case _                          => bailOut
        }
      case Bin.And =>
        (l, r) match {
          case (Val.Bool(l), Val.Bool(r)) => Val.Bool(l & r)
          case (Val.Int(l), Val.Int(r))   => Val.Int(l & r)
          case (Val.Long(l), Val.Long(r)) => Val.Long(l & r)
          case _                          => bailOut
        }
      case Bin.Or =>
        (l, r) match {
          case (Val.Bool(l), Val.Bool(r)) => Val.Bool(l | r)
          case (Val.Int(l), Val.Int(r))   => Val.Int(l | r)
          case (Val.Long(l), Val.Long(r)) => Val.Long(l | r)
          case _                          => bailOut
        }
      case Bin.Xor =>
        (l, r) match {
          case (Val.Bool(l), Val.Bool(r)) => Val.Bool(l ^ r)
          case (Val.Int(l), Val.Int(r))   => Val.Int(l ^ r)
          case (Val.Long(l), Val.Long(r)) => Val.Long(l ^ r)
          case _                          => bailOut
        }
    }
  }

  def eval(comp: Comp, ty: Type, l: Val, r: Val)(implicit state: State): Val = {
    def bailOut =
      throw BailOut(
        s"can't eval comp op: $comp[${ty.show}] ${l.show}, ${r.show}")
    comp match {
      case Comp.Ieq =>
        (l, r) match {
          case (Val.Bool(l), Val.Bool(r))                           => Val.Bool(l == r)
          case (Val.Int(l), Val.Int(r))                             => Val.Bool(l == r)
          case (Val.Long(l), Val.Long(r))                           => Val.Bool(l == r)
          case (Val.Null, Val.Null)                                 => Val.True
          case (Val.Global(l, _), Val.Global(r, _))                 => Val.Bool(l == r)
          case (Val.Null | _: Val.Global, Val.Null | _: Val.Global) => Val.False
          case _                                                    => bailOut
        }
      case Comp.Ine =>
        (l, r) match {
          case (Val.Bool(l), Val.Bool(r))                           => Val.Bool(l != r)
          case (Val.Int(l), Val.Int(r))                             => Val.Bool(l != r)
          case (Val.Long(l), Val.Long(r))                           => Val.Bool(l != r)
          case (Val.Null, Val.Null)                                 => Val.False
          case (Val.Global(l, _), Val.Global(r, _))                 => Val.Bool(l != r)
          case (Val.Null | _: Val.Global, Val.Null | _: Val.Global) => Val.True
          case _                                                    => bailOut
        }
      case Comp.Ugt =>
        (l, r) match {
          case (Val.Int(l), Val.Int(r)) =>
            Val.Bool(java.lang.Integer.compareUnsigned(l, r) > 0)
          case (Val.Long(l), Val.Long(r)) =>
            Val.Bool(java.lang.Long.compareUnsigned(l, r) > 0)
          case _ =>
            bailOut
        }
      case Comp.Uge =>
        (l, r) match {
          case (Val.Int(l), Val.Int(r)) =>
            Val.Bool(java.lang.Integer.compareUnsigned(l, r) >= 0)
          case (Val.Long(l), Val.Long(r)) =>
            Val.Bool(java.lang.Long.compareUnsigned(l, r) >= 0)
          case _ =>
            bailOut
        }
      case Comp.Ult =>
        (l, r) match {
          case (Val.Int(l), Val.Int(r)) =>
            Val.Bool(java.lang.Integer.compareUnsigned(l, r) < 0)
          case (Val.Long(l), Val.Long(r)) =>
            Val.Bool(java.lang.Long.compareUnsigned(l, r) < 0)
          case _ =>
            bailOut
        }
      case Comp.Ule =>
        (l, r) match {
          case (Val.Int(l), Val.Int(r)) =>
            Val.Bool(java.lang.Integer.compareUnsigned(l, r) <= 0)
          case (Val.Long(l), Val.Long(r)) =>
            Val.Bool(java.lang.Long.compareUnsigned(l, r) <= 0)
          case _ =>
            bailOut
        }
      case Comp.Sgt =>
        (l, r) match {
          case (Val.Int(l), Val.Int(r))   => Val.Bool(l > r)
          case (Val.Long(l), Val.Long(r)) => Val.Bool(l > r)
          case _                          => bailOut
        }
      case Comp.Sge =>
        (l, r) match {
          case (Val.Int(l), Val.Int(r))   => Val.Bool(l >= r)
          case (Val.Long(l), Val.Long(r)) => Val.Bool(l >= r)
          case _                          => bailOut
        }
      case Comp.Slt =>
        (l, r) match {
          case (Val.Int(l), Val.Int(r))   => Val.Bool(l < r)
          case (Val.Long(l), Val.Long(r)) => Val.Bool(l < r)
          case _                          => bailOut
        }
      case Comp.Sle =>
        (l, r) match {
          case (Val.Int(l), Val.Int(r))   => Val.Bool(l <= r)
          case (Val.Long(l), Val.Long(r)) => Val.Bool(l <= r)
          case _                          => bailOut
        }
      case Comp.Feq =>
        (l, r) match {
          case (Val.Float(l), Val.Float(r))   => Val.Bool(l == r)
          case (Val.Double(l), Val.Double(r)) => Val.Bool(l == r)
          case _                              => bailOut
        }
      case Comp.Fne =>
        (l, r) match {
          case (Val.Float(l), Val.Float(r))   => Val.Bool(l != r)
          case (Val.Double(l), Val.Double(r)) => Val.Bool(l != r)
          case _                              => bailOut
        }
      case Comp.Fgt =>
        (l, r) match {
          case (Val.Float(l), Val.Float(r))   => Val.Bool(l > r)
          case (Val.Double(l), Val.Double(r)) => Val.Bool(l > r)
          case _                              => bailOut
        }
      case Comp.Fge =>
        (l, r) match {
          case (Val.Float(l), Val.Float(r))   => Val.Bool(l >= r)
          case (Val.Double(l), Val.Double(r)) => Val.Bool(l >= r)
          case _                              => bailOut
        }
      case Comp.Flt =>
        (l, r) match {
          case (Val.Float(l), Val.Float(r))   => Val.Bool(l < r)
          case (Val.Double(l), Val.Double(r)) => Val.Bool(l < r)
          case _                              => bailOut
        }
      case Comp.Fle =>
        (l, r) match {
          case (Val.Float(l), Val.Float(r))   => Val.Bool(l <= r)
          case (Val.Double(l), Val.Double(r)) => Val.Bool(l <= r)
          case _                              => bailOut
        }
    }
  }

  def eval(conv: Conv, ty: Type, value: Val)(implicit state: State): Val = {
    def bailOut =
      throw BailOut(s"can't eval conv op: $conv[${ty.show}] ${value.show}")
    conv match {
      case _ if ty == value.ty =>
        value
      case Conv.Trunc =>
        (value, ty) match {
          case (Val.Char(v), Type.Byte)  => Val.Byte(v.toByte)
          case (Val.Short(v), Type.Byte) => Val.Byte(v.toByte)
          case (Val.Int(v), Type.Byte)   => Val.Byte(v.toByte)
          case (Val.Int(v), Type.Short)  => Val.Short(v.toShort)
          case (Val.Int(v), Type.Char)   => Val.Char(v.toChar)
          case (Val.Long(v), Type.Byte)  => Val.Int(v.toByte)
          case (Val.Long(v), Type.Short) => Val.Int(v.toShort)
          case (Val.Long(v), Type.Int)   => Val.Int(v.toInt)
          case (Val.Long(v), Type.Char)  => Val.Char(v.toChar)
          case _                         => bailOut
        }
      case Conv.Zext =>
        (value, ty) match {
          case (Val.Char(v), Type.Int) =>
            Val.Int(v.toInt)
          case (Val.Char(v), Type.Long) =>
            Val.Long(v.toLong)
          case (Val.Short(v), Type.Int) =>
            Val.Int(v.toChar.toInt)
          case (Val.Short(v), Type.Long) =>
            Val.Long(v.toChar.toLong)
          case (Val.Int(v), Type.Long) =>
            Val.Long(java.lang.Integer.toUnsignedLong(v))
          case _ =>
            bailOut
        }
      case Conv.Sext =>
        (value, ty) match {
          case (Val.Byte(v), Type.Short) => Val.Short(v.toShort)
          case (Val.Byte(v), Type.Char)  => Val.Char(v.toChar)
          case (Val.Byte(v), Type.Int)   => Val.Int(v.toInt)
          case (Val.Byte(v), Type.Long)  => Val.Long(v.toLong)
          case (Val.Short(v), Type.Int)  => Val.Int(v.toInt)
          case (Val.Short(v), Type.Long) => Val.Long(v.toLong)
          case (Val.Int(v), Type.Long)   => Val.Long(v.toLong)
          case _                         => bailOut
        }
      case Conv.Fptrunc =>
        (value, ty) match {
          case (Val.Double(v), Type.Float) => Val.Float(v.toFloat)
          case _                           => bailOut
        }
      case Conv.Fpext =>
        (value, ty) match {
          case (Val.Float(v), Type.Double) => Val.Double(v.toDouble)
          case _                           => bailOut
        }
      case Conv.Fptoui =>
        (value, ty) match {
          case (Val.Float(v), Type.Char)  => Val.Char(v.toChar)
          case (Val.Double(v), Type.Char) => Val.Char(v.toChar)
          case _                          => bailOut
        }
      case Conv.Fptosi =>
        (value, ty) match {
          case (Val.Float(v), Type.Int)   => Val.Int(v.toInt)
          case (Val.Double(v), Type.Int)  => Val.Int(v.toInt)
          case (Val.Float(v), Type.Long)  => Val.Long(v.toLong)
          case (Val.Double(v), Type.Long) => Val.Long(v.toLong)
          case _                          => bailOut
        }
      case Conv.Uitofp =>
        (value, ty) match {
          case (Val.Char(v), Type.Float)  => Val.Float(v.toInt.toFloat)
          case (Val.Char(v), Type.Double) => Val.Double(v.toInt.toFloat)
          case _                          => bailOut
        }
      case Conv.Sitofp =>
        (value, ty) match {
          case (Val.Byte(v), Type.Float)   => Val.Float(v.toFloat)
          case (Val.Byte(v), Type.Double)  => Val.Double(v.toDouble)
          case (Val.Short(v), Type.Float)  => Val.Float(v.toFloat)
          case (Val.Short(v), Type.Double) => Val.Double(v.toDouble)
          case (Val.Int(v), Type.Float)    => Val.Float(v.toFloat)
          case (Val.Int(v), Type.Double)   => Val.Double(v.toDouble)
          case (Val.Long(v), Type.Float)   => Val.Float(v.toFloat)
          case (Val.Long(v), Type.Double)  => Val.Double(v.toDouble)
          case _                           => bailOut
        }
      case Conv.Ptrtoint =>
        (value, ty) match {
          case (Val.Null, Type.Long) => Val.Long(0L)
          case _                     => bailOut
        }
      case Conv.Inttoptr =>
        (value, ty) match {
          case (Val.Long(0L), Type.Ptr) => Val.Null
          case _                        => bailOut
        }
      case Conv.Bitcast =>
        (value, ty) match {
          case (value, ty) if value.ty == ty =>
            value
          case (Val.Char(value), Type.Short) =>
            Val.Short(value.toShort)
          case (Val.Short(value), Type.Char) =>
            Val.Char(value.toChar)
          case (Val.Int(value), Type.Float) =>
            Val.Float(java.lang.Float.intBitsToFloat(value))
          case (Val.Long(value), Type.Double) =>
            Val.Double(java.lang.Double.longBitsToDouble(value))
          case (Val.Float(value), Type.Int) =>
            Val.Int(java.lang.Float.floatToRawIntBits(value))
          case (Val.Double(value), Type.Long) =>
            Val.Long(java.lang.Double.doubleToRawLongBits(value))
          case (Val.Null, Type.Ptr) =>
            Val.Null
          case _ =>
            bailOut
        }
    }
  }

  def eval(value: Val)(implicit state: State): Val = {
    value match {
      case Val.Local(local, _) if local.id >= 0 =>
        state.loadLocal(local) match {
          case value: Val.Virtual =>
            eval(value)
          case value =>
            value
        }
      case Val.Virtual(addr) if state.hasEscaped(addr) =>
        state.derefEscaped(addr).escapedValue
      case Val.String(value) =>
        Val.Virtual(state.allocString(value))
      case _ =>
        value.canonicalize
    }
  }

  private def inBounds(values: Array[Val], offset: Int): Boolean = {
    offset >= 0 && offset < values.length
  }
}
