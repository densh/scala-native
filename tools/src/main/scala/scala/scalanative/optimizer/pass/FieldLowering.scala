package scala.scalanative
package optimizer
package pass

import scala.collection.mutable
import analysis.ClassHierarchy._
import analysis.ClassHierarchyExtractors._
import util.unsupported
import nir._

/** Lowers class definitions, and field accesses to structs
 *  and corresponding derived pointer computation.
 */
class FieldLowering(implicit top: Top, fresh: Fresh) extends Pass {
  import FieldLowering._

  private def fieldElem(obj: Val, cls: Class, fld: Field): Op.Elem = {
    val layout = cls.layout
    val ty     = layout.struct
    val index  = layout.index(fld)

    Op.Elem(ty, obj, Seq(Val.Int(0), Val.Int(index)))
  }

  override def onInsts(insts: Seq[Inst]) = {
    val buf = new nir.Buffer
    import buf._

    insts.foreach {
      case Inst.Let(n, Op.Fieldload(ty, obj, FieldRef(cls, fld))) =>
        val elem = let(fieldElem(obj, cls, fld))
        let(n, Op.Load(onType(ty), elem))

      case Inst.Let(n, Op.Fieldstore(ty, obj, FieldRef(cls, fld), value)) =>
        val elem = let(fieldElem(obj, cls, fld))
        let(n, Op.Store(onType(ty), elem, value))

      case inst =>
        buf += inst
    }

    buf.toSeq
  }
}

object FieldLowering extends PassCompanion {
  override def apply(config: tools.Config, top: Top) =
    new FieldLowering()(top, top.fresh)
}
