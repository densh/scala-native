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

  override def onInst(inst: Inst) = super.onInst {
    inst match {
      case Inst.Let(n, Op.Field(obj, FieldRef(cls: Class, fld))) =>
        val layout = cls.layout
        val ty     = layout.struct
        val index  = layout.index(fld)

        Inst.Let(n, Op.Elem(ty, obj, Seq(Val.Int(0), Val.Int(index))))

      case _ =>
        inst
    }
  }
}

object FieldLowering extends PassCompanion {
  override def apply(config: tools.Config, top: Top) =
    new FieldLowering()(top, top.fresh)
}
