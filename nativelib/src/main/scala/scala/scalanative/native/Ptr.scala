// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 1)
package scala.scalanative
package native

import scala.runtime.BoxesRunTime._
import scala.reflect.ClassTag
import scalanative.runtime._
import scalanative.runtime.Intrinsics._
import scalanative.runtime.Boxes._

final class Ptr[T](private[scalanative] val rawptr: RawPtr) {
  override def hashCode: Int =
    java.lang.Long.hashCode(castRawPtrToLong(rawptr))

  override def equals(other: Any): Boolean =
    (this eq other.asInstanceOf[AnyRef]) || (other match {
      case other: Ptr[_] =>
        other.rawptr == rawptr
      case _ =>
        false
    })

  @inline override def toString: String =
    "Ptr@" + java.lang.Long.toHexString(castRawPtrToLong(rawptr))

  @inline def toInt: scala.Int =
    Intrinsics.castRawPtrToInt(rawptr)

  @inline def toLong: scala.Int =
    Intrinsics.castRawPtrToInt(rawptr)

  @inline def unary_!(implicit tag: Tag[T]): T =
    tag.load(this)

  @inline def `unary_!_=`(value: T)(implicit tag: Tag[T]): Unit =
    tag.store(this, value)

  @inline def +(offset: Word)(implicit tag: Tag[T]): Ptr[T] =
    new Ptr(elemRawPtr(rawptr, offset * sizeof[T]))

  @inline def -(offset: Word)(implicit tag: Tag[T]): Ptr[T] =
    new Ptr(elemRawPtr(rawptr, -offset * sizeof[T]))

  @inline def -(other: Ptr[T])(implicit tag: Tag[T]): CPtrDiff = {
    val left  = castRawPtrToLong(rawptr)
    val right = castRawPtrToLong(other.rawptr)
    (left - right) / sizeof[T]
  }

  @inline def apply(offset: Word)(implicit tag: Tag[T]): T =
    (this + offset).unary_!

  @inline def update(offset: Word, value: T)(implicit tag: Tag[T]): Unit =
    (this + offset).`unary_!_=`(value)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 56)

  /** Get a derived pointer to the 1-th field of the struct. */
  def _1[F](implicit tag: Tag.Field1[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, tag.offset(0)))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 56)

  /** Get a derived pointer to the 2-th field of the struct. */
  def _2[F](implicit tag: Tag.Field2[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, tag.offset(1)))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 56)

  /** Get a derived pointer to the 3-th field of the struct. */
  def _3[F](implicit tag: Tag.Field3[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, tag.offset(2)))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 56)

  /** Get a derived pointer to the 4-th field of the struct. */
  def _4[F](implicit tag: Tag.Field4[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, tag.offset(3)))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 56)

  /** Get a derived pointer to the 5-th field of the struct. */
  def _5[F](implicit tag: Tag.Field5[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, tag.offset(4)))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 56)

  /** Get a derived pointer to the 6-th field of the struct. */
  def _6[F](implicit tag: Tag.Field6[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, tag.offset(5)))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 56)

  /** Get a derived pointer to the 7-th field of the struct. */
  def _7[F](implicit tag: Tag.Field7[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, tag.offset(6)))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 56)

  /** Get a derived pointer to the 8-th field of the struct. */
  def _8[F](implicit tag: Tag.Field8[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, tag.offset(7)))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 56)

  /** Get a derived pointer to the 9-th field of the struct. */
  def _9[F](implicit tag: Tag.Field9[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, tag.offset(8)))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 56)

  /** Get a derived pointer to the 10-th field of the struct. */
  def _10[F](implicit tag: Tag.Field10[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, tag.offset(9)))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 56)

  /** Get a derived pointer to the 11-th field of the struct. */
  def _11[F](implicit tag: Tag.Field11[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, tag.offset(10)))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 56)

  /** Get a derived pointer to the 12-th field of the struct. */
  def _12[F](implicit tag: Tag.Field12[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, tag.offset(11)))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 56)

  /** Get a derived pointer to the 13-th field of the struct. */
  def _13[F](implicit tag: Tag.Field13[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, tag.offset(12)))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 56)

  /** Get a derived pointer to the 14-th field of the struct. */
  def _14[F](implicit tag: Tag.Field14[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, tag.offset(13)))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 56)

  /** Get a derived pointer to the 15-th field of the struct. */
  def _15[F](implicit tag: Tag.Field15[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, tag.offset(14)))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 56)

  /** Get a derived pointer to the 16-th field of the struct. */
  def _16[F](implicit tag: Tag.Field16[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, tag.offset(15)))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 56)

  /** Get a derived pointer to the 17-th field of the struct. */
  def _17[F](implicit tag: Tag.Field17[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, tag.offset(16)))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 56)

  /** Get a derived pointer to the 18-th field of the struct. */
  def _18[F](implicit tag: Tag.Field18[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, tag.offset(17)))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 56)

  /** Get a derived pointer to the 19-th field of the struct. */
  def _19[F](implicit tag: Tag.Field19[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, tag.offset(18)))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 56)

  /** Get a derived pointer to the 20-th field of the struct. */
  def _20[F](implicit tag: Tag.Field20[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, tag.offset(19)))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 56)

  /** Get a derived pointer to the 21-th field of the struct. */
  def _21[F](implicit tag: Tag.Field21[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, tag.offset(20)))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 56)

  /** Get a derived pointer to the 22-th field of the struct. */
  def _22[F](implicit tag: Tag.Field22[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, tag.offset(21)))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 62)
}
