package scala.scalanative
package optimizer
package pass

import scala.collection.mutable
import analysis.ClassHierarchy.Top
import nir._

/** Inserts safepoint probes before every return. */
class SafepointInsertion(implicit fresh: Fresh) extends Pass {
  import SafepointInsertion._

  override def onInsts(insts: Seq[Inst]): Seq[Inst] = {
    val buf = new nir.Buffer
    import buf._

    insts.foreach {
      case inst: Inst.Label =>
        buf += inst
        let(Op.Load(Type.Byte, safepointTriggerVal, isVolatile = true))

      case inst =>
        buf += inst
    }

    buf.toSeq
  }
}

object SafepointInsertion extends PassCompanion {
  val safepointTriggerName = Global.Top("scalanative_safepoint_trigger")
  val safepointTriggerVal  = Val.Global(safepointTriggerName, Type.Ptr)

  override def apply(config: tools.Config, top: Top) =
    new SafepointInsertion()(top.fresh)
}
