package scala.scalanative
package optimizer
package pass

import scala.collection.mutable
import scalanative.optimizer.analysis.{LocalScope, LocalRef}
import scalanative.optimizer.analysis.ClassHierarchy.{Top, Class}
import scalanative.optimizer.analysis.ClassHierarchyExtractors.FieldRef
import scalanative.nir._, Inst._, Op._

/** Inserts safepoint probes before every return. */
class CMSWriteBarrier(implicit top: Top, fresh: Fresh) extends Pass {
  import CMSWriteBarrier._

  override def onDefn(defn: Defn): Defn =
    if (defn.name == ObjectArrayUpdateName) {
      defn
    } else {
      super.onDefn(defn)
    }

  override def onInsts(insts: Seq[Inst]): Seq[Inst] = {
    implicit val scope = LocalScope(insts)
    val buf            = new nir.Buffer
    import buf._

    insts.foreach {
      case store @ Let(n,
                       Store(ty: Type.RefKind,
                             LocalRef(Field(obj, FieldRef(cls: Class, fld))),
                             value,
                             _)) =>
        val storeLabel, logReplica, logSnoop, continue = Next(fresh())

        // if CMS_traceOn { CMS_logReplica(obj) }
        val isTraceOn = let(Op.Load(Type.Bool, TraceOnVal, isVolatile = true))
        branch(isTraceOn, logReplica, storeLabel)
        label(logReplica.name)
        let(Op.Call(LogReplicaSig, LogReplicaVal, Seq(obj), Next.None))
        jump(storeLabel)

        // obj.fld = value
        label(storeLabel.name)
        buf += store

        // if CMS_snoopOn { CMS_logSnoop(value) }
        val isSnoopOn = let(Op.Load(Type.Bool, SnoopOnVal, isVolatile = true))
        branch(isSnoopOn, logSnoop, continue)
        label(logSnoop.name)
        let(Op.Call(LogSnoopSig, LogSnoopVal, Seq(obj), Next.None))
        jump(continue)

        // continue with normal execution
        label(continue.name)

      case inst =>
        buf += inst
    }

    buf.toSeq
  }
}

object CMSWriteBarrier extends PassCompanion {
  val ObjectArrayName = Global.Top("scala.scalanative.runtime.ObjectArray")
  val ObjectArrayUpdateName =
    Global.Member(ObjectArrayName, "update_i32_java.lang.Object_unit")

  val SnoopOnName = Global.Top("CMS_snoopOn")
  val SnoopOnVal  = Val.Global(SnoopOnName, Type.Ptr)
  val SnoopOnDefn =
    Defn.Var(Attrs(isExtern = true), SnoopOnName, Type.Bool, Val.None)

  val TraceOnName = Global.Top("CMS_traceOn")
  val TraceOnVal  = Val.Global(TraceOnName, Type.Ptr)
  val TraceOnDefn =
    Defn.Var(Attrs(isExtern = true), TraceOnName, Type.Bool, Val.None)

  val LogReplicaName = Global.Top("CMS_logReplica")
  val LogReplicaSig  = Type.Function(Seq(Type.Ptr), Type.Unit)
  val LogReplicaVal  = Val.Global(LogReplicaName, Type.Ptr)
  val LogReplicaDefn = Defn.Declare(Attrs.None, LogReplicaName, LogReplicaSig)

  val LogSnoopName = Global.Top("CMS_logSnoop")
  val LogSnoopSig  = Type.Function(Seq(Type.Ptr), Type.Unit)
  val LogSnoopVal  = Val.Global(LogSnoopName, Type.Ptr)
  val LogSnoopDefn = Defn.Declare(Attrs.None, LogSnoopName, LogSnoopSig)

  override def injects =
    Seq(TraceOnDefn, SnoopOnDefn, LogReplicaDefn, LogSnoopDefn)

  override def apply(config: tools.Config, top: Top) =
    new CMSWriteBarrier()(top, top.fresh)
}
