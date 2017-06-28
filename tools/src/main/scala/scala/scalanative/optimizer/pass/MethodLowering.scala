package scala.scalanative
package optimizer
package pass

import scala.collection.mutable
import scalanative.optimizer.analysis.ClassHierarchy._
import scalanative.optimizer.analysis.ClassHierarchyExtractors._
import scalanative.nir._, Inst.Let
import scalanative.util.unsupported

/** Translates high-level object-oriented method calls into
 *  low-level dispatch based on vtables for classes
 *  and dispatch tables for interfaces.
 */
class MethodLowering(implicit fresh: Fresh, top: Top) extends Pass {
  import top.tables

  final class LocalScope(val lookup: Local => Option[Op])

  object LocalScope {
    def apply(insts: Seq[Inst]): LocalScope = {
      val lookup = mutable.Map.empty[Local, Op]
      insts.foreach {
        case Inst.Let(n, op) => lookup(n) = op
        case _               => ()
      }
      new LocalScope(lookup.get(_))
    }
  }

  object LocalRef {
    def unapply(value: Val)(implicit scope: LocalScope): Option[Op] =
      value match {
        case Val.Local(local, _) => unapply(local)
        case _                   => None
      }

    def unapply(local: Local)(implicit scope: LocalScope): Option[Op] =
      scope.lookup(local)
  }

  override def onInsts(insts: Seq[Inst]) = {
    implicit val scope = LocalScope(insts)
    val buf            = new nir.Buffer
    import buf._

    insts.foreach {
      case Let(n,
               call @ Op.Call(ty,
                              LocalRef(
                                Op.Method(obj, MethodRef(cls: Class, meth))),
                              args,
                              unwind)) if meth.isVirtual =>
        val impls = cls.vtable.impls(meth)

        if (impls.size == 1) {
          val (_, impl) = impls.head
          let(n, Op.Call(ty, Val.Global(impl, Type.Ptr), args, unwind))
        } else if (impls.size > 1 && impls.size <= 4) {
          val default = Next(fresh())
          val merge   = fresh()
          val conds   = impls.map(_ => fresh())
          val succs   = impls.map(_ => fresh())

          val typeptr = let(Op.Load(Type.Ptr, obj))
          jump(Next(conds.head))
          impls
            .zip(conds.zip(succs))
            .zip(conds.tail.map(Some(_)) :+ None)
            .foreach {
              case (((cls, impl), (cond, succ)), Some(next)) =>
                label(cond)
                val cmp =
                  let(Op.Comp(Comp.Ieq, Type.Ptr, typeptr, cls.rtti.const))
                branch(cmp, Next(succ), Next(next))
                label(succ)
                val res =
                  let(Op.Call(ty, Val.Global(impl, Type.Ptr), args, unwind))
                jump(Next.Label(merge, Seq(res)))
              case (((cls, impl), (cond, _)), None) =>
                label(cond)
                val res =
                  let(Op.Call(ty, Val.Global(impl, Type.Ptr), args, unwind))
                jump(Next.Label(merge, Seq(res)))
            }
          label(merge, Seq(Val.Local(n, call.resty)))
        } else {
          val vindex  = cls.vtable.index(meth)
          val typeptr = let(Op.Load(Type.Ptr, obj))
          val methptrptr = let(
            Op.Elem(cls.rtti.struct,
                    typeptr,
                    Seq(Val.Int(0),
                        Val.Int(5), // index of vtable in type struct
                        Val.Int(vindex))))
          val resolved = let(Op.Load(Type.Ptr, methptrptr))
          let(n, Op.Call(ty, resolved, args, unwind))
        }

      case Let(n,
               Op.Call(ty,
                       LocalRef(Op.Method(obj, MethodRef(_: Class, meth))),
                       args,
                       unwind)) if meth.isStatic =>
        let(n, Op.Call(ty, meth.value, args, unwind))

      case Let(n,
               Op.Call(ty,
                       LocalRef(Op.Method(obj, MethodRef(trt: Trait, meth))),
                       args,
                       unwind)) if tables.tableSigs.contains(meth.name.id) =>
        val sigid   = tables.tableSigs(meth.name.id)
        val typeptr = let(Op.Load(Type.Ptr, obj))
        val idptr   = let(Op.Elem(Rt.Type, typeptr, Seq(Val.Int(0), Val.Int(0))))
        val id      = let(Op.Load(Type.Int, idptr))
        val rowptr = let(
          Op.Elem(Type.Ptr,
                  tables.dispatchVal,
                  Seq(Val.Int(tables.dispatchOffset(sigid)))))
        val methptrptr =
          let(Op.Elem(Type.Ptr, rowptr, Seq(id)))
        val resolved = let(Op.Load(Type.Ptr, methptrptr))
        let(n, Op.Call(ty, resolved, args, unwind))

      case Let(n,
               call @ Op.Call(
                 ty,
                 LocalRef(Op.Method(obj, MethodRef(trt: Trait, meth))),
                 args,
                 unwind)) if tables.switchSigs.contains(meth.name.id) =>
        val impls = tables.traitSigImpls(meth.name.id).filter {
          case (name, impl) =>
            top.nodes(impl).asInstanceOf[Method].isConcrete
        }
        val default = Next(fresh())
        val merge   = fresh()
        val conds   = impls.map(_ => fresh())
        val succs   = impls.map(_ => fresh())

        val typeptr = let(Op.Load(Type.Ptr, obj))
        jump(Next(conds.head))
        impls
          .zip(conds.zip(succs))
          .zip(conds.tail.map(Some(_)) :+ None)
          .foreach {
            case (((cls, impl), (cond, succ)), Some(next)) =>
              label(cond)
              val cmp =
                let(Op.Comp(Comp.Ieq, Type.Ptr, typeptr, cls.rtti.const))
              branch(cmp, Next(succ), Next(next))
              label(succ)
              val res =
                let(Op.Call(ty, Val.Global(impl, Type.Ptr), args, unwind))
              jump(Next.Label(merge, Seq(res)))
            case (((cls, impl), (cond, _)), None) =>
              label(cond)
              val res =
                let(Op.Call(ty, Val.Global(impl, Type.Ptr), args, unwind))
              jump(Next.Label(merge, Seq(res)))
          }
        label(merge, Seq(Val.Local(n, call.resty)))

      case let @ Let(n, call @ Op.Call(_, LocalRef(meth: Op.Method), _, _)) =>
        unsupported(let)

      case inst =>
        buf += inst
    }

    buf.toSeq
  }
}

object MethodLowering extends PassCompanion {
  override def apply(config: tools.Config, top: Top) =
    new MethodLowering()(top.fresh, top)
}
