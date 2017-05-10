package java.lang

import scalanative.native._
import scalanative.runtime._

// These two methods are generated at link-time by the toolchain
// using current closed-world knowledge of classes and traits in
// the current application.
@extern
object rtti {
  def __check_class_has_trait(classId: Int, traitId: Int): scala.Boolean =
    extern
  def __check_trait_has_trait(leftId: Int, rightId: Int): scala.Boolean =
    extern
}
import rtti._

final class _Class[A](val ty: Ptr[Type]) {
  def cast(obj: Object): A =
    obj.asInstanceOf[A]

  def getComponentType(): _Class[_] = {
    if (ty == typeof[Array[scala.Boolean]]) classOf[scala.Boolean]
    else if (ty == typeof[Array[scala.Char]]) classOf[scala.Char]
    else if (ty == typeof[Array[scala.Byte]]) classOf[scala.Byte]
    else if (ty == typeof[Array[scala.Short]]) classOf[scala.Short]
    else if (ty == typeof[Array[scala.Int]]) classOf[scala.Int]
    else if (ty == typeof[Array[scala.Long]]) classOf[scala.Long]
    else if (ty == typeof[Array[scala.Float]]) classOf[scala.Float]
    else if (ty == typeof[Array[scala.Double]]) classOf[scala.Double]
    else classOf[java.lang.Object]
  }

  def getInterfaces(): Array[_Class[_]] =
    ???

  def getName(): String =
    ty.name

  def getSimpleName(): String =
    getName.split('.').last.split('$').last

  def getSuperclass(): Class[_ >: A] =
    ???

  def isArray(): scala.Boolean =
    (ty == typeof[Array[scala.Boolean]] ||
      ty == typeof[Array[scala.Char]] ||
      ty == typeof[Array[scala.Byte]] ||
      ty == typeof[Array[scala.Short]] ||
      ty == typeof[Array[scala.Int]] ||
      ty == typeof[Array[scala.Long]] ||
      ty == typeof[Array[scala.Float]] ||
      ty == typeof[Array[scala.Double]] ||
      ty == typeof[Array[Object]])

  def isAssignableFrom(that: Class[_]): scala.Boolean =
    is(that.asInstanceOf[_Class[_]].ty, ty)

  def isInstance(obj: Object): scala.Boolean =
    is(obj.getClass.asInstanceOf[_Class[_]].ty, ty)

  private def is(left: Ptr[Type], right: Ptr[Type]): Boolean =
    // This replicates the logic of the compiler-generated instance check
    // that you would normally get if you do (obj: L).isInstanceOf[R],
    // where rtti for L and R are `left` and `right`.
    left.kind match {
      case CLASS_KIND =>
        right.kind match {
          case CLASS_KIND =>
            val rightCls  = right.cast[Ptr[ClassType]]
            val rightFrom = rightCls.idRangeFrom
            val rightTo   = rightCls.idRangeTo
            val leftId    = left.id
            leftId >= rightFrom && leftId <= rightTo
          case TRAIT_KIND =>
            __check_class_has_trait(left.id, right.id)
          case STRUCT_KIND =>
            false
        }
      case TRAIT_KIND =>
        right.kind match {
          case CLASS_KIND =>
            false
          case TRAIT_KIND =>
            __check_trait_has_trait(left.id, right.id)
          case STRUCT_KIND =>
            false
        }
      case STRUCT_KIND =>
        right.kind match {
          case CLASS_KIND =>
            false
          case TRAIT_KIND =>
            false
          case STRUCT_KIND =>
            left.id == right.id
        }
    }

  def isInterface(): scala.Boolean =
    ty.kind == TRAIT_KIND

  def isPrimitive(): scala.Boolean =
    (ty == typeof[PrimitiveBoolean] ||
      ty == typeof[PrimitiveChar] ||
      ty == typeof[PrimitiveByte] ||
      ty == typeof[PrimitiveShort] ||
      ty == typeof[PrimitiveInt] ||
      ty == typeof[PrimitiveLong] ||
      ty == typeof[PrimitiveFloat] ||
      ty == typeof[PrimitiveDouble] ||
      ty == typeof[PrimitiveUnit])

  override def equals(other: Any): scala.Boolean =
    other match {
      case other: _Class[_] =>
        ty == other.ty
      case _ =>
        false
    }

  override def hashCode: Int =
    ty.cast[scala.Long].##

  override def toString = {
    val name = getName
    val prefix = ty.kind match {
      case CLASS_KIND  => "class "
      case TRAIT_KIND  => "interface "
      case STRUCT_KIND => "struct "
    }
    prefix + name
  }
}

object _Class {
  private[java] implicit def _class2class[A](cls: _Class[A]): Class[A] =
    cls.asInstanceOf[Class[A]]
  private[java] implicit def class2_class[A](cls: Class[A]): _Class[A] =
    cls.asInstanceOf[_Class[A]]
}
