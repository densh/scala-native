package scala.scalanative
package optimizer
package pass

import analysis.ClassHierarchy._
import analysis.ClassHierarchyExtractors._
import util.unsupported
import nir._, Inst.{Let, Label}

class TypeProfiling(implicit top: Top) extends Pass {
  import TypeProfiling._

  override def onDefn(defn: Defn): Defn = defn match {
    case defn: Defn.Define if top.nodes.contains(defn.name) =>
      defn.copy(insts = onMethod(defn))
    case _ =>
      defn
  }

  def onMethod(defn: Defn.Define): Seq[Inst] = {
    implicit val fresh = Fresh(defn.insts)
    val buf            = new Buffer
    val defnId         = top.nodes(defn.name).id
    import buf._

    def logProfile(local: Local, obj: Val): Unit =
      let(
        Op.Call(profileMethodSig,
                profileMethod,
                Seq(Val.Long((defnId << 32) | local.id), obj),
                Next.None))

    val profileLocals =
      defn.insts.collect {
        case inst @ Let(_, Op.Is(_, Val.Local(local, _))) =>
          local
        case inst @ Let(
              _,
              Op.Method(Val.Local(local, _), MethodRef(_: Class, meth)))
            if meth.isVirtual =>
          local
        case inst @ Let(
              n,
              Op.Method(Val.Local(local, _), MethodRef(_: Trait, _))) =>
          local
      }.toSet

    defn.insts.foreach {
      case inst @ Label(n, params) =>
        buf += inst
        params.foreach {
          case Val.Local(local, ty) =>
            if (profileLocals.contains(local)) {
              logProfile(local, Val.Local(local, ty))
            }
        }

      case inst @ Let(local, op) if profileLocals.contains(local) =>
        buf += inst
        logProfile(local, Val.Local(local, op.resty))

      case inst =>
        buf += inst
    }

    buf.toSeq
  }
}

object TypeProfiling extends PassCompanion {
  override def apply(config: tools.Config, top: Top) =
    new TypeProfiling()(top)

  val profileMethodName = Global.Top("method_call_log")
  val profileMethodSig =
    Type.Function(Seq(Type.Long, Type.Ptr), Type.Void)
  val profileMethod = Val.Global(profileMethodName, Type.Ptr)

  override val injects = Seq(
    Defn.Declare(Attrs.None, profileMethodName, profileMethodSig))
}
