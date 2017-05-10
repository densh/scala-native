package scala.scalanative
package optimizer
package pass

import scalanative.optimizer.analysis.ClassHierarchy.Top
import scalanative.nir._

/** Lowers array classes and operations on them. */
class ArrayLowering extends Pass {}

object ArrayLowering extends PassCompanion {
  override def apply(config: tools.Config, top: Top) =
    new ArrayLowering()
}
