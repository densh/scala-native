package scala.scalanative
package optimizer
package pass

import scala.collection.mutable
import analysis.ClassHierarchy.Top
import analysis.UseDef
import analysis.ControlFlow
import nir._

/** Eliminates pure computations that are not being used, as well as unused block parameters. */
class SimpleDeadCodeElimination(implicit top: Top) extends Pass {
  import SimpleDeadCodeElimination._

  override def onInsts(insts: Seq[Inst]): Seq[Inst] = {
    val cfg = ControlFlow.Graph(insts)
    val buf = new nir.Buffer

    cfg.all.foreach { block =>
      buf += block.label
      block.insts.foreach { inst =>
        buf += inst
      }
    }

    buf.toSeq
  }
}

object SimpleDeadCodeElimination extends PassCompanion {
  override def apply(config: tools.Config, top: Top) =
    new SimpleDeadCodeElimination()(top)
}
