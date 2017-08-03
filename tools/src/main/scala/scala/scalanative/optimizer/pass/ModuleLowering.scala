package scala.scalanative
package optimizer
package pass

import scala.collection.mutable
import analysis.ClassHierarchy._
import analysis.ClassHierarchyExtractors._
import nir._

class ModuleLowering(implicit top: Top, fresh: Fresh) extends Pass {
  override def onInst(inst: Inst): Inst = inst match {
    case Inst.Let(n, Op.Module(name, unwind)) =>
      val loadSig = Type.Function(Seq(), Type.Class(name))
      val load    = Val.Global(name member "load", Type.Ptr)

      Inst.Let(n, Op.Call(loadSig, load, Seq(), unwind))

    case _ =>
      super.onInst(inst)
  }
}

object ModuleLowering extends PassCompanion {
  override def apply(config: tools.Config, top: Top) =
    new ModuleLowering()(top, top.fresh)
}
