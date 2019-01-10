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
    castRawPtrToLong(rawptr).##

  override def equals(other: Any): Boolean =
    (this eq other.asInstanceOf[AnyRef]) || (other match {
      case other: Ptr[_] =>
        other.rawptr == rawptr
      case _ =>
        false
    })

  override def toString: String =
    "Ptr@" + java.lang.Long.toHexString(castRawPtrToLong(rawptr))

  def isNull: Boolean =
    rawptr == null

  def unary_!(implicit tag: Tag[T]): T = {
    val t: Tag[_] = tag

    t match {
      case _: Tag.Ptr[_]   => (new Ptr[Any](loadRawPtr(rawptr))).asInstanceOf[T]
      case _: Tag.Class[_] => loadObject(rawptr).asInstanceOf[T]
      case Tag.Unit        => ().asInstanceOf[T]
      case Tag.Boolean     => loadBoolean(rawptr).asInstanceOf[T]
      case Tag.Char        => loadChar(rawptr).asInstanceOf[T]
      case Tag.Byte        => loadByte(rawptr).asInstanceOf[T]
      case Tag.UByte       => loadByte(rawptr).toUByte.asInstanceOf[T]
      case Tag.Short       => loadShort(rawptr).asInstanceOf[T]
      case Tag.UShort      => loadShort(rawptr).toUShort.asInstanceOf[T]
      case Tag.Int         => loadInt(rawptr).asInstanceOf[T]
      case Tag.UInt        => loadInt(rawptr).toUInt.asInstanceOf[T]
      case Tag.Long        => loadLong(rawptr).asInstanceOf[T]
      case Tag.ULong       => loadLong(rawptr).toULong.asInstanceOf[T]
      case Tag.Float       => loadFloat(rawptr).asInstanceOf[T]
      case Tag.Double      => loadDouble(rawptr).asInstanceOf[T]
      case _               => throwUndefined()
    }
  }

  def `unary_!_=`(value: T)(implicit tag: Tag[T]): Unit = {
    val obj       = value.asInstanceOf[Object]
    val t: Tag[_] = tag

    t match {
      case _: Tag.Ptr[_]   => storeRawPtr(rawptr, obj.asInstanceOf[Ptr[_]].rawptr)
      case _: Tag.Class[_] => storeObject(rawptr, obj)
      case Tag.Unit        => ()
      case Tag.Boolean     => storeBoolean(rawptr, unboxToBoolean(obj))
      case Tag.Char        => storeChar(rawptr, unboxToChar(obj))
      case Tag.Byte        => storeByte(rawptr, unboxToByte(obj))
      case Tag.UByte       => storeByte(rawptr, unboxToUByte(obj).toByte)
      case Tag.Short       => storeShort(rawptr, unboxToShort(obj))
      case Tag.UShort      => storeShort(rawptr, unboxToUShort(obj).toShort)
      case Tag.Int         => storeInt(rawptr, unboxToInt(obj))
      case Tag.UInt        => storeInt(rawptr, unboxToUInt(obj).toInt)
      case Tag.Long        => storeLong(rawptr, unboxToLong(obj))
      case Tag.ULong       => storeLong(rawptr, unboxToULong(obj).toLong)
      case Tag.Float       => storeFloat(rawptr, unboxToFloat(obj))
      case Tag.Double      => storeDouble(rawptr, unboxToDouble(obj))
      case _               => throwUndefined()
    }
  }

  @inline def +(offset: Word)(implicit tag: Tag[T]): Ptr[T] =
    new Ptr(elemRawPtr(rawptr, offset * sizeof[T]))

  @inline def -(offset: Word)(implicit tag: Tag[T]): Ptr[T] =
    new Ptr(elemRawPtr(rawptr, -offset * sizeof[T]))

  @inline def -(other: Ptr[T])(implicit tag: Tag[T]): CPtrDiff = {
    val left  = castRawPtrToLong(rawptr)
    val right = castRawPtrToLong(rawptr)
    (left - right) / sizeof[T]
  }

  @inline def apply(offset: Word)(implicit tag: Tag[T]): T =
    (this + offset).unary_!

  @inline def update(offset: Word, value: T)(implicit tag: Tag[T]): Unit =
    (this + offset).`unary_!_=`(value)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 94)

  /** Get a derived pointer to the 1-th field of the struct. */
  def _1[F](implicit tag: Tag[T], fld: CField1[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, fld.offset))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 94)

  /** Get a derived pointer to the 2-th field of the struct. */
  def _2[F](implicit tag: Tag[T], fld: CField2[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, fld.offset))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 94)

  /** Get a derived pointer to the 3-th field of the struct. */
  def _3[F](implicit tag: Tag[T], fld: CField3[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, fld.offset))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 94)

  /** Get a derived pointer to the 4-th field of the struct. */
  def _4[F](implicit tag: Tag[T], fld: CField4[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, fld.offset))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 94)

  /** Get a derived pointer to the 5-th field of the struct. */
  def _5[F](implicit tag: Tag[T], fld: CField5[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, fld.offset))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 94)

  /** Get a derived pointer to the 6-th field of the struct. */
  def _6[F](implicit tag: Tag[T], fld: CField6[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, fld.offset))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 94)

  /** Get a derived pointer to the 7-th field of the struct. */
  def _7[F](implicit tag: Tag[T], fld: CField7[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, fld.offset))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 94)

  /** Get a derived pointer to the 8-th field of the struct. */
  def _8[F](implicit tag: Tag[T], fld: CField8[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, fld.offset))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 94)

  /** Get a derived pointer to the 9-th field of the struct. */
  def _9[F](implicit tag: Tag[T], fld: CField9[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, fld.offset))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 94)

  /** Get a derived pointer to the 10-th field of the struct. */
  def _10[F](implicit tag: Tag[T], fld: CField10[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, fld.offset))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 94)

  /** Get a derived pointer to the 11-th field of the struct. */
  def _11[F](implicit tag: Tag[T], fld: CField11[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, fld.offset))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 94)

  /** Get a derived pointer to the 12-th field of the struct. */
  def _12[F](implicit tag: Tag[T], fld: CField12[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, fld.offset))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 94)

  /** Get a derived pointer to the 13-th field of the struct. */
  def _13[F](implicit tag: Tag[T], fld: CField13[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, fld.offset))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 94)

  /** Get a derived pointer to the 14-th field of the struct. */
  def _14[F](implicit tag: Tag[T], fld: CField14[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, fld.offset))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 94)

  /** Get a derived pointer to the 15-th field of the struct. */
  def _15[F](implicit tag: Tag[T], fld: CField15[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, fld.offset))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 94)

  /** Get a derived pointer to the 16-th field of the struct. */
  def _16[F](implicit tag: Tag[T], fld: CField16[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, fld.offset))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 94)

  /** Get a derived pointer to the 17-th field of the struct. */
  def _17[F](implicit tag: Tag[T], fld: CField17[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, fld.offset))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 94)

  /** Get a derived pointer to the 18-th field of the struct. */
  def _18[F](implicit tag: Tag[T], fld: CField18[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, fld.offset))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 94)

  /** Get a derived pointer to the 19-th field of the struct. */
  def _19[F](implicit tag: Tag[T], fld: CField19[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, fld.offset))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 94)

  /** Get a derived pointer to the 20-th field of the struct. */
  def _20[F](implicit tag: Tag[T], fld: CField20[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, fld.offset))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 94)

  /** Get a derived pointer to the 21-th field of the struct. */
  def _21[F](implicit tag: Tag[T], fld: CField21[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, fld.offset))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 94)

  /** Get a derived pointer to the 22-th field of the struct. */
  def _22[F](implicit tag: Tag[T], fld: CField22[T, F]): Ptr[F] =
    new Ptr[F](elemRawPtr(rawptr, fld.offset))

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 100)

}

object Ptr {
  @inline def fromRaw[T](rawptr: RawPtr): Ptr[T] =
    new Ptr[T](rawptr)

  @inline def fromObject[T](obj: Any): Ptr[T] =
    new Ptr[T](castObjectToRawPtr(obj.asInstanceOf[Object]))

  @inline def fromArray[T](array: scala.Array[T], idx: Int): Ptr[T] =
    fromRaw[T](array.asInstanceOf[scalanative.runtime.Array[_]].at(idx))

  @inline def toRaw(ptr: Ptr[_]): RawPtr =
    ptr.rawptr

  @inline def toObject(ptr: Ptr[_]): Any =
    castRawPtrToObject(ptr.rawptr)
}
