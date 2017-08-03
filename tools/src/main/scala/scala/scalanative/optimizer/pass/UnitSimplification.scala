package scala.scalanative
package optimizer
package pass

import nir.{Val, Type, Global, Defn, Attrs}
import analysis.ClassHierarchy.Top

class UnitSimplification extends Pass {
  import UnitSimplification._

  override def onVal(value: Val): Val = value match {
    case v if (v.ty == Type.Unit) =>
      unit

    case _ =>
      super.onVal(value)
  }
}

object UnitSimplification extends PassCompanion {
  val unitName  = Global.Top("scala.scalanative.runtime.BoxedUnit$")
  val unit      = Val.Global(unitName, Type.Unit)
  val unitTy    = Type.Struct(unitName member "layout", Seq(Type.Ptr))
  val unitConst = Val.Global(unitName member "type", Type.Ptr)
  val unitValue = Val.Struct(unitTy.name, Seq(unitConst))
  val unitDefn  = Defn.Const(Attrs.None, unitName, unitTy, unitValue)

  override val depends =
    Seq(unitName)

  override val injects =
    Seq(unitDefn)

  override def apply(config: tools.Config, top: Top) =
    new UnitSimplification
}
