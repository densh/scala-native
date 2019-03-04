package scala.scalanative
package interflow

import scala.collection.mutable
import scalanative.nir._

final class MergeBlock(val label: Inst.Label, val name: Local) {
  var incoming            = mutable.Map.empty[Local, (Seq[Val], State)]
  var outgoing            = mutable.Map.empty[Local, MergeBlock]
  var phis: Seq[MergePhi] = _
  var start: State        = _
  var end: State          = _
  var cf: Inst.Cf         = _
  var invalidations: Int  = 0
}
