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
class ClassLowering(implicit top: Top, fresh: Fresh) extends Pass {
  import ClassLowering._

  override def onDefns(defns: Seq[Defn]): Seq[Defn] =
    super.onDefns(defns.filter {
      case _: Defn.Class =>
        false
      case Defn.Declare(_, MethodRef(_: Class, _), _) =>
        false
      case Defn.Var(_, FieldRef(_: Class, _), _, _) =>
        false
      case defn =>
        true
    })

  override def onType(ty: Type): Type = ty match {
    case ty: Type.RefKind if ty != Type.Unit =>
      Type.Ptr
    case _ =>
      super.onType(ty)
  }
}

object ClassLowering extends PassCompanion {
  override def apply(config: tools.Config, top: Top) =
    new ClassLowering()(top, top.fresh)
}
