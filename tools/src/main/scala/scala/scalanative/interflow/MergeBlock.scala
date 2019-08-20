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

  def toInsts(): Seq[Inst] = {
    val block  = this
    val result = new nir.Buffer()(Fresh(0))
    val trailing = new nir.Buffer()(Fresh(0))
    def mergeNext(next: Next.Label): Next.Label = {
      if (next.weight == 0) {
        val fallback = end.fresh()
        trailing.label(fallback, Seq())
        trailing.unreachable(Next.None)
        Next(fallback)
      } else {
        val nextBlock = outgoing(next.name)
        val mergeValues = nextBlock.phis.flatMap {
          case MergePhi(_, incoming) =>
            incoming.collect {
              case (name, value) if name == block.label.name =>
                value
            }
        }
        Next(nextBlock.name, mergeValues, next.weight)
      }
    }
    def mergeUnwind(next: Next): Next = next match {
      case Next.None =>
        next
      case Next.Unwind(exc, next: Next.Label) =>
        Next.Unwind(exc, mergeNext(next))
      case _ =>
        util.unreachable
    }
    val params = block.phis.map(_.param)
    result.label(block.name, params)
    result ++= block.end.emit
    block.cf match {
      case ret: Inst.Ret =>
        result += ret
      case Inst.Jump(next: Next.Label) =>
        result.jump(mergeNext(next))
      case Inst.If(cond, thenNext: Next.Label, elseNext: Next.Label) =>
        result.branch(cond, mergeNext(thenNext), mergeNext(elseNext))
      case Inst.Switch(scrut, defaultNext: Next.Label, cases) =>
        val mergeCases = cases.map {
          case Next.Case(v, next: Next.Label) =>
            Next.Case(v, mergeNext(next))
          case _ =>
            util.unreachable
        }
        result.switch(scrut, mergeNext(defaultNext), mergeCases)
      case Inst.Throw(v, unwind) =>
        result.raise(v, mergeUnwind(unwind))
      case Inst.Unreachable(unwind) =>
        result.unreachable(mergeUnwind(unwind))
    }
    result ++= trailing
    result.toSeq
  }
}
