package scala.scalanative
package pgo

import scala.collection.mutable
import scalanative.nir._, Inst.{Let, Label}
import scalanative.util.unsupported

class Profiling(implicit linked: linker.Result) extends Pass {
  import Profiling._

  def onDefns(defns: Seq[Defn]): Seq[Defn] = {
    val out = mutable.UnrolledBuffer.empty[Defn]

    defns.foreach {
      case defn: Defn.Define if linked.infos.contains(defn.name) =>
        out += defn.copy(insts = onMethod(defn))
      case defn =>
        out += defn
    }

    out += Defn.Module(ExternAttrs,
                       profilingName,
                       Some(Rt.Object.name),
                       Seq.empty)
    out += Defn.Declare(ExternAttrs,
                        typeProfileMethodName,
                        typeProfileMethodSig)
    out += Defn.Declare(ExternAttrs,
                        freqProfileMethodName,
                        freqProfileMethodSig)
    out
  }

  def onMethod(defn: Defn.Define): Seq[Inst] = {
    implicit val fresh = Fresh(defn.insts)
    val buf            = new Buffer
    val defnId         = linked.ids(defn.name)
    import buf._

    def typeLog(local: Local, obj: Val): Unit =
      let(Op.Call(typeProfileMethodSig,
                  typeProfileMethod,
                  Seq(Val.Long((defnId.toLong << 32) | local.id), obj)),
          Next.None)

    def freqLog(local: Local): Unit =
      let(Op.Call(freqProfileMethodSig,
                  freqProfileMethod,
                  Seq(Val.Long((defnId.toLong << 32) | local.id))),
          Next.None)

    val profileLocals =
      defn.insts.collect {
        case inst @ Let(_, Op.Method(Val.Local(local, _), sig), _) =>
          local
      }.toSet

    defn.insts.foreach {
      case inst @ Label(n, params, _) =>
        buf += inst
        freqLog(n)
        params.foreach {
          case Val.Local(local, ty) =>
            if (profileLocals.contains(local)) {
              typeLog(local, Val.Local(local, ty))
            }
        }

      case inst @ Let(local, op, _) if profileLocals.contains(local) =>
        buf += inst
        typeLog(local, Val.Local(local, op.resty))

      case inst =>
        buf += inst
    }

    buf.toSeq
  }
}

object Profiling extends PassCompanion {
  override def apply(config: build.Config, linked: linker.Result) =
    new Profiling()(linked)

  val ExternAttrs = Attrs(isExtern = true)

  val profilingName = Global.Top("__profiling")

  val typeProfileMethodName =
    Global.Member(profilingName, Sig.Extern("typeprofile_log"))
  val typeProfileMethodSig =
    Type.Function(Seq(Type.Long, Type.Ptr), Type.Unit)
  val typeProfileMethod =
    Val.Global(typeProfileMethodName, Type.Ptr)

  val freqProfileMethodName =
    Global.Member(profilingName, Sig.Extern("freqprofile_log"))
  val freqProfileMethodSig =
    Type.Function(Seq(Type.Long), Type.Unit)
  val freqProfileMethod =
    Val.Global(freqProfileMethodName, Type.Ptr)
}
