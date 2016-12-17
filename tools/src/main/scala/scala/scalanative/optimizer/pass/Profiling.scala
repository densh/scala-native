package scala.scalanative
package optimizer
package pass

import analysis.ClassHierarchy
import analysis.ClassHierarchyExtractors.{Ref, MethodRef, ClassRef, ScopeRef}
import nir._
import Inst.Let
import tools.Config

/** Inject profiling instructions */
class Profiling(implicit top: ClassHierarchy.Top) extends Pass {
  import Profiling._

  override def onDefn(defn: Defn): Defn = defn match {
    case defn @ Defn.Define(_, MethodRef(scope, meth), _, _) =>
      defn.copy(insts = instrument(scope.id, meth.id, defn.insts))
    case defn =>
      defn
  }

  private def instrument(scopeId: Int,
                         methId: Int,
                         insts: Seq[Inst]): Seq[Inst] = {
    implicit val fresh = Fresh(insts)

    val buf = new nir.Buffer
    import buf._

    val label +: rest = insts

    buf ++= Seq(
      label,
      Let(
        Op.Call(log_callSig,
                log_call,
                Seq(Val.Int(scopeId), Val.Int(methId)),
                Next.None))
    )
    rest.foreach {
      case l @ Let(_, Op.Load(ty, ptr, _)) =>
        call(log_loadSig, log_load, Seq(), Next.None)
        buf += l

      case l @ Let(_, Op.Store(ty, ptr, value, _)) =>
        call(log_storeSig, log_store, Seq(), Next.None)
        buf += l

      case l @ Let(_, Op.Classalloc(ClassRef(clss))) =>
        call(log_classallocSig,
             log_classalloc,
             Seq(Val.Int(clss.id)),
             Next.None)
        buf += l

      case l @ Let(_, Op.Method(obj, MethodRef(scope, meth))) =>
        buf ++= withTypeId(scope, obj) { typeid =>
          Seq(Let(
                Op.Call(log_methodSig,
                        log_method,
                        Seq(typeid, Val.Int(scope.id), Val.Int(meth.id)),
                        Next.None)),
              l)
        }

      case l @ Let(_, Op.As(ScopeRef(scope), obj)) =>
        val ifNullLog =
          Seq(
            Let(
              Op.Call(log_asSig,
                      log_as,
                      Seq(Val.Int(0), Val.Int(scope.id)),
                      Next.None)))
        val ifNonNullLog =
          (objTypeId: Val) =>
            Seq(
              Let(
                Op.Call(log_asSig,
                        log_as,
                        Seq(objTypeId, Val.Int(scope.id)),
                        Next.None)))

        buf ++= guardedForNull(scope, obj)(ifNullLog)(ifNonNullLog)
        buf += l

      case l @ Let(_, Op.Is(ScopeRef(scope), obj)) =>
        val ifNullLog =
          Seq(
            Let(
              Op.Call(log_isSig,
                      log_is,
                      Seq(Val.Int(0), Val.Int(scope.id)),
                      Next.None)))
        val ifNonNullLog =
          (objTypeId: Val) =>
            Seq(
              Let(
                Op.Call(log_isSig,
                        log_is,
                        Seq(objTypeId, Val.Int(scope.id)),
                        Next.None)))

        buf ++= guardedForNull(scope, obj)(ifNullLog)(ifNonNullLog)
        buf += l

      case l @ Let(_, Op.Box(Ref(node), obj)) =>
        call(log_boxSig, log_box, Seq(Val.Int(node.id)), Next.None)
        buf += l

      case l @ Let(_, Op.Unbox(ScopeRef(scope), obj)) =>
        buf ++= withTypeId(scope, obj) { objTypeId =>
          Seq(
            Let(Op.Call(log_unboxSig, log_unbox, Seq(objTypeId), Next.None)),
            l
          )
        }
      case inst =>
        buf += inst
    }

    buf.toSeq
  }

  /** Is `v` a reference to an intrinsic LLVM definition?
   *
   *  We exclude them from logging because these definitions are replaced by LLVM and don't really
   *  exist. Having reference to these definitions would yield linking errors.
   */
  private def isLLVMIntrinsic(v: Val): Boolean =
    v match {
      case Val.Global(name, _) =>
        name.id.startsWith("extern.llvm")
      case _ =>
        false
    }

  /** Extract the type id of `obj`, executes `ifNull` is `obj` is null or `ifNonNull` otherwise.
   *
   *  @param scope The scope that contains `obj`
   *  @param obj   The object whose type we want to inspect.
   *  @param ifNull The instructions to execute if `obj` is null.
   *  @param ifNonNull A function that given a value holding the runtime type ID of `obj`, returns a
   *                   sequence of instruction to execute. These instructions will be executed if
   *                   `obj` is non null.
   *  @return A sequence of instruction that forms the new blocks.
   */
  private def guardedForNull(scope: ClassHierarchy.Scope, obj: Val)(
      ifNull: Seq[Inst])(ifNonNull: Val => Seq[Inst])(
      implicit fresh: Fresh): Seq[Inst] = {
    val intPtr      = Val.Local(fresh(), Type.Long)
    val isNull      = Val.Local(fresh(), Type.Bool)
    val nullCase    = Inst.Label(fresh(), Seq())
    val nonNullCase = Inst.Label(fresh(), Seq())
    val merge       = Inst.Label(fresh(), Seq())

    val preamble =
      Seq(
        Let(intPtr.name, Op.Conv(Conv.Ptrtoint, Type.Long, obj)),
        Let(isNull.name, Op.Comp(Comp.Ieq, Type.Long, intPtr, Val.Long(0L))),
        Inst.If(isNull, Next(nullCase.name), Next(nonNullCase.name))
      )

    val ifNullInsts =
      Seq(nullCase) ++
        ifNull ++
        Seq(Inst.Jump(Next(merge.name)))

    val ifNonNullInsts =
      Seq(nonNullCase) ++
        withTypeId(scope, obj) { objTypeId =>
          ifNonNull(objTypeId) ++
            Seq(Inst.Jump(Next(merge.name)))
        }

    val end =
      Seq(merge)

    preamble ++ ifNonNullInsts ++ ifNullInsts ++ end
  }

  /** Execute the given instructions with the type ID of `v`.
   *
   *  @param scope The scope that contains `v`.
   *  @param v     The value whose type we want to inspect.
   *  @param fn    A function that given a value holding the runtime type ID of `v`, returns a
   *               sequence of instructions to execute.
   *  @return A sequence of instruction that load the type ID and execute `fn`.
   */
  private def withTypeId(scope: ClassHierarchy.Scope, v: Val)(
      fn: Val => Seq[Inst])(implicit fresh: Fresh): Seq[Inst] = {
    val typeptr   = Val.Local(fresh(), Type.Ptr)
    val typeidptr = Val.Local(fresh(), Type.Ptr)
    val typeid    = Val.Local(fresh(), Type.Int)

    val typeStruct = scope match {
      case trt: ClassHierarchy.Trait => Rt.Type
      case cls: ClassHierarchy.Class => cls.layout.struct
      case _                         => throw new IllegalArgumentException(scope.getClass.getName)
    }

    val preamble =
      Seq(
        Let(typeptr.name, Op.Load(Type.Ptr, v)),
        Let(typeidptr.name,
            Op.Elem(typeStruct, typeptr, Seq(Val.Int(0), Val.Int(0)))),
        Let(typeid.name, Op.Load(Type.Int, typeidptr))
      )

    preamble ++ fn(typeid)
  }
}

object Profiling extends PassCompanion {

  val log_call     = Val.Global(Global.Top("log_call"), Type.Ptr)
  val log_callSig  = Type.Function(Seq(Type.Int, Type.Int), Type.Void)
  val log_callDecl = Defn.Declare(Attrs.None, log_call.name, log_callSig)

  val log_load     = Val.Global(Global.Top("log_load"), Type.Ptr)
  val log_loadSig  = Type.Function(Seq(), Type.Void)
  val log_loadDecl = Defn.Declare(Attrs.None, log_load.name, log_loadSig)

  val log_store     = Val.Global(Global.Top("log_store"), Type.Ptr)
  val log_storeSig  = Type.Function(Seq(), Type.Void)
  val log_storeDecl = Defn.Declare(Attrs.None, log_store.name, log_storeSig)

  val log_classalloc = Val.Global(Global.Top("log_classalloc"), Type.Ptr)
  val log_classallocSig =
    Type.Function(Seq(Type.Int), Type.Void)
  val log_classallocDecl =
    Defn.Declare(Attrs.None, log_classalloc.name, log_classallocSig)

  val log_method = Val.Global(Global.Top("log_method"), Type.Ptr)
  val log_methodSig =
    Type.Function(Seq(Type.Int, Type.Int, Type.Int), Type.Void)
  val log_methodDecl = Defn.Declare(Attrs.None, log_method.name, log_methodSig)

  val log_as = Val.Global(Global.Top("log_as"), Type.Ptr)
  val log_asSig =
    Type.Function(Seq(Type.Int, Type.Int), Type.Void)
  val log_asDecl = Defn.Declare(Attrs.None, log_as.name, log_asSig)

  val log_is = Val.Global(Global.Top("log_is"), Type.Ptr)
  val log_isSig =
    Type.Function(Seq(Type.Int, Type.Int), Type.Void)
  val log_isDecl = Defn.Declare(Attrs.None, log_is.name, log_isSig)

  val log_box     = Val.Global(Global.Top("log_box"), Type.Ptr)
  val log_boxSig  = Type.Function(Seq(Type.Int), Type.Void)
  val log_boxDecl = Defn.Declare(Attrs.None, log_box.name, log_boxSig)

  val log_unbox = Val.Global(Global.Top("log_unbox"), Type.Ptr)
  val log_unboxSig =
    Type.Function(Seq(Type.Int), Type.Void)
  val log_unboxDecl = Defn.Declare(Attrs.None, log_unbox.name, log_unboxSig)

  override val injects =
    Seq(log_callDecl,
        log_loadDecl,
        log_storeDecl,
        log_classallocDecl,
        log_methodDecl,
        log_asDecl,
        log_isDecl,
        log_boxDecl,
        log_unboxDecl)

  override def apply(config: Config, top: ClassHierarchy.Top): Pass =
    new Profiling()(top)
}
