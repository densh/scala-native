package scala.scalanative
package pgo

import nir._

class DeadBlockElimination extends Pass {
  def onDefns(defns: Seq[Defn]): Seq[Defn] = defns.map {
    case defn: Defn.Define =>
      defn.copy(insts = onInsts(defn.insts))
    case defn =>
      defn
  }

  def onInsts(insts: Seq[Inst]) = {
    val cfg = ControlFlow.Graph(insts)
    val buf = new nir.Buffer()(Fresh(insts))

    cfg.all.foreach { b =>
      buf += b.label
      buf ++= b.insts
    }

    buf.toSeq
  }
}

object DeadBlockElimination extends PassCompanion {
  override def apply(config: build.Config, linked: linker.Result) =
    new DeadBlockElimination()
}
