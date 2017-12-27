package scala.scalanative
package optimizer
package analysis

import scala.collection.mutable
import ClassHierarchy._
import nir._

final class VirtualTable(cls: Class) {
  // TODO: short-circuit == to equals and ## to hashCode
  // javaEquals: Option[Method],
  // javaHashCode: Option[Method],
  // scalaEquals: Option[Method],
  // scalaHashCode: Option[Method]
  val index: mutable.Map[String, Int] =
    cls.parent.fold(mutable.Map.empty[String, Int])(_.vtable.index.clone())
  val at: mutable.UnrolledBuffer[Val] =
    cls.parent.fold(mutable.UnrolledBuffer.empty[Val])(_.vtable.at.clone())
  cls.methods.foreach { meth =>
    val sig  = meth.name.id
    val impl = meth.value

    if (index.contains(sig)) {
      at(index(sig)) = impl
    } else if (cls.isVirtual(sig)) {
      index(sig) = index.size
      at += impl
    }
  }
  def ty: Type                 = Type.Array(Type.Ptr, at.length)
  def value: Val               = Val.Array(Type.Ptr, at)
  def index(meth: Method): Int = index(meth.name.id)
}
