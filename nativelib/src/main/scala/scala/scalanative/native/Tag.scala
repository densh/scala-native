// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 1)
package scala.scalanative
package native

import scala.reflect.ClassTag
import scalanative.runtime._
import scalanative.runtime.Intrinsics._

sealed abstract class Tag[T] {
  def size: Int
  def alignment: Int
  @noinline def offset(idx: Int): Int                     = throwUndefined()
  @noinline def load(ptr: native.Ptr[T]): T               = throwUndefined()
  @noinline def store(ptr: native.Ptr[T], value: T): Unit = throwUndefined()
}

object Tag {
  case class Ptr[T](of: Tag[T]) extends Tag[native.Ptr[T]] {
    @alwaysinline def size: Int      = 8
    @alwaysinline def alignment: Int = 8
    @alwaysinline override def load(
        ptr: native.Ptr[native.Ptr[T]]): native.Ptr[T] =
      fromRawPtr[T](loadRawPtr(toRawPtr(ptr)))
    @alwaysinline override def store(ptr: native.Ptr[native.Ptr[T]],
                                     value: native.Ptr[T]): Unit =
      storeRawPtr(toRawPtr(ptr), toRawPtr(value))
  }

  case class Class[T <: AnyRef](of: java.lang.Class[T]) extends Tag[T] {
    @alwaysinline def size: Int      = 8
    @alwaysinline def alignment: Int = 8
    @alwaysinline override def load(ptr: native.Ptr[T]): T =
      loadObject(toRawPtr(ptr)).asInstanceOf[T]
    @alwaysinline override def store(ptr: native.Ptr[T], value: T): Unit =
      storeObject(toRawPtr(ptr), value.asInstanceOf[Object])
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 51)

  object Unit extends Tag[scala.Unit] {
    @alwaysinline def size: Int                                              = 8
    @alwaysinline def alignment: Int                                         = 8
    @alwaysinline override def load(ptr: native.Ptr[scala.Unit]): scala.Unit =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 57)
      loadObject(toRawPtr(ptr)).asInstanceOf[Unit]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 64)
    @alwaysinline override def store(ptr: native.Ptr[scala.Unit],
                                     value: scala.Unit): Unit =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 66)
      storeObject(toRawPtr(ptr), value.asInstanceOf[Object])
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 73)
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 51)

  object Boolean extends Tag[scala.Boolean] {
    @alwaysinline def size: Int      = 1
    @alwaysinline def alignment: Int = 1
    @alwaysinline override def load(
        ptr: native.Ptr[scala.Boolean]): scala.Boolean =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 62)
      loadBoolean(toRawPtr(ptr))
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 64)
    @alwaysinline override def store(ptr: native.Ptr[scala.Boolean],
                                     value: scala.Boolean): Unit =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 71)
      storeBoolean(toRawPtr(ptr), value)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 73)
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 51)

  object Char extends Tag[scala.Char] {
    @alwaysinline def size: Int                                              = 2
    @alwaysinline def alignment: Int                                         = 2
    @alwaysinline override def load(ptr: native.Ptr[scala.Char]): scala.Char =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 62)
      loadChar(toRawPtr(ptr))
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 64)
    @alwaysinline override def store(ptr: native.Ptr[scala.Char],
                                     value: scala.Char): Unit =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 71)
      storeChar(toRawPtr(ptr), value)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 73)
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 51)

  object Byte extends Tag[scala.Byte] {
    @alwaysinline def size: Int                                              = 1
    @alwaysinline def alignment: Int                                         = 1
    @alwaysinline override def load(ptr: native.Ptr[scala.Byte]): scala.Byte =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 62)
      loadByte(toRawPtr(ptr))
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 64)
    @alwaysinline override def store(ptr: native.Ptr[scala.Byte],
                                     value: scala.Byte): Unit =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 71)
      storeByte(toRawPtr(ptr), value)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 73)
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 51)

  object UByte extends Tag[native.UByte] {
    @alwaysinline def size: Int      = 1
    @alwaysinline def alignment: Int = 1
    @alwaysinline override def load(
        ptr: native.Ptr[native.UByte]): native.UByte =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 60)
      loadByte(toRawPtr(ptr)).toUByte
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 64)
    @alwaysinline override def store(ptr: native.Ptr[native.UByte],
                                     value: native.UByte): Unit =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 69)
      storeByte(toRawPtr(ptr), value.toByte)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 73)
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 51)

  object Short extends Tag[scala.Short] {
    @alwaysinline def size: Int                                                = 2
    @alwaysinline def alignment: Int                                           = 2
    @alwaysinline override def load(ptr: native.Ptr[scala.Short]): scala.Short =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 62)
      loadShort(toRawPtr(ptr))
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 64)
    @alwaysinline override def store(ptr: native.Ptr[scala.Short],
                                     value: scala.Short): Unit =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 71)
      storeShort(toRawPtr(ptr), value)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 73)
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 51)

  object UShort extends Tag[native.UShort] {
    @alwaysinline def size: Int      = 2
    @alwaysinline def alignment: Int = 2
    @alwaysinline override def load(
        ptr: native.Ptr[native.UShort]): native.UShort =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 60)
      loadShort(toRawPtr(ptr)).toUShort
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 64)
    @alwaysinline override def store(ptr: native.Ptr[native.UShort],
                                     value: native.UShort): Unit =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 69)
      storeShort(toRawPtr(ptr), value.toShort)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 73)
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 51)

  object Int extends Tag[scala.Int] {
    @alwaysinline def size: Int                                            = 4
    @alwaysinline def alignment: Int                                       = 4
    @alwaysinline override def load(ptr: native.Ptr[scala.Int]): scala.Int =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 62)
      loadInt(toRawPtr(ptr))
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 64)
    @alwaysinline override def store(ptr: native.Ptr[scala.Int],
                                     value: scala.Int): Unit =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 71)
      storeInt(toRawPtr(ptr), value)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 73)
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 51)

  object UInt extends Tag[native.UInt] {
    @alwaysinline def size: Int                                                = 4
    @alwaysinline def alignment: Int                                           = 4
    @alwaysinline override def load(ptr: native.Ptr[native.UInt]): native.UInt =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 60)
      loadInt(toRawPtr(ptr)).toUInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 64)
    @alwaysinline override def store(ptr: native.Ptr[native.UInt],
                                     value: native.UInt): Unit =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 69)
      storeInt(toRawPtr(ptr), value.toInt)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 73)
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 51)

  object Long extends Tag[scala.Long] {
    @alwaysinline def size: Int                                              = 8
    @alwaysinline def alignment: Int                                         = 8
    @alwaysinline override def load(ptr: native.Ptr[scala.Long]): scala.Long =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 62)
      loadLong(toRawPtr(ptr))
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 64)
    @alwaysinline override def store(ptr: native.Ptr[scala.Long],
                                     value: scala.Long): Unit =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 71)
      storeLong(toRawPtr(ptr), value)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 73)
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 51)

  object ULong extends Tag[native.ULong] {
    @alwaysinline def size: Int      = 8
    @alwaysinline def alignment: Int = 8
    @alwaysinline override def load(
        ptr: native.Ptr[native.ULong]): native.ULong =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 60)
      loadLong(toRawPtr(ptr)).toULong
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 64)
    @alwaysinline override def store(ptr: native.Ptr[native.ULong],
                                     value: native.ULong): Unit =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 69)
      storeLong(toRawPtr(ptr), value.toLong)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 73)
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 51)

  object Float extends Tag[scala.Float] {
    @alwaysinline def size: Int                                                = 4
    @alwaysinline def alignment: Int                                           = 4
    @alwaysinline override def load(ptr: native.Ptr[scala.Float]): scala.Float =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 62)
      loadFloat(toRawPtr(ptr))
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 64)
    @alwaysinline override def store(ptr: native.Ptr[scala.Float],
                                     value: scala.Float): Unit =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 71)
      storeFloat(toRawPtr(ptr), value)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 73)
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 51)

  object Double extends Tag[scala.Double] {
    @alwaysinline def size: Int      = 8
    @alwaysinline def alignment: Int = 8
    @alwaysinline override def load(
        ptr: native.Ptr[scala.Double]): scala.Double =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 62)
      loadDouble(toRawPtr(ptr))
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 64)
    @alwaysinline override def store(ptr: native.Ptr[scala.Double],
                                     value: scala.Double): Unit =
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 71)
      storeDouble(toRawPtr(ptr), value)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 73)
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 76)

  private[scalanative] sealed trait NatTag {
    def toInt: Int
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 82)

  object Nat0 extends Tag[native.Nat._0] with NatTag {
    @noinline def size: Int      = throwUndefined()
    @noinline def alignment: Int = throwUndefined()
    @alwaysinline def toInt: Int = 0
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 82)

  object Nat1 extends Tag[native.Nat._1] with NatTag {
    @noinline def size: Int      = throwUndefined()
    @noinline def alignment: Int = throwUndefined()
    @alwaysinline def toInt: Int = 1
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 82)

  object Nat2 extends Tag[native.Nat._2] with NatTag {
    @noinline def size: Int      = throwUndefined()
    @noinline def alignment: Int = throwUndefined()
    @alwaysinline def toInt: Int = 2
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 82)

  object Nat3 extends Tag[native.Nat._3] with NatTag {
    @noinline def size: Int      = throwUndefined()
    @noinline def alignment: Int = throwUndefined()
    @alwaysinline def toInt: Int = 3
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 82)

  object Nat4 extends Tag[native.Nat._4] with NatTag {
    @noinline def size: Int      = throwUndefined()
    @noinline def alignment: Int = throwUndefined()
    @alwaysinline def toInt: Int = 4
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 82)

  object Nat5 extends Tag[native.Nat._5] with NatTag {
    @noinline def size: Int      = throwUndefined()
    @noinline def alignment: Int = throwUndefined()
    @alwaysinline def toInt: Int = 5
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 82)

  object Nat6 extends Tag[native.Nat._6] with NatTag {
    @noinline def size: Int      = throwUndefined()
    @noinline def alignment: Int = throwUndefined()
    @alwaysinline def toInt: Int = 6
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 82)

  object Nat7 extends Tag[native.Nat._7] with NatTag {
    @noinline def size: Int      = throwUndefined()
    @noinline def alignment: Int = throwUndefined()
    @alwaysinline def toInt: Int = 7
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 82)

  object Nat8 extends Tag[native.Nat._8] with NatTag {
    @noinline def size: Int      = throwUndefined()
    @noinline def alignment: Int = throwUndefined()
    @alwaysinline def toInt: Int = 8
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 82)

  object Nat9 extends Tag[native.Nat._9] with NatTag {
    @noinline def size: Int      = throwUndefined()
    @noinline def alignment: Int = throwUndefined()
    @alwaysinline def toInt: Int = 9
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 90)

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 97)

  case class Digit2[N1 <: Nat.Base, N2 <: Nat.Base](_1: Tag[N1], _2: Tag[N2])
      extends Tag[native.Nat.Digit2[N1, N2]]
      with NatTag {
    @alwaysinline def size: Int      = throwUndefined()
    @alwaysinline def alignment: Int = throwUndefined()
    @alwaysinline def toInt: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _1.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _2.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 109)
      res
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 97)

  case class Digit3[N1 <: Nat.Base, N2 <: Nat.Base, N3 <: Nat.Base](_1: Tag[N1],
                                                                    _2: Tag[N2],
                                                                    _3: Tag[N3])
      extends Tag[native.Nat.Digit3[N1, N2, N3]]
      with NatTag {
    @alwaysinline def size: Int      = throwUndefined()
    @alwaysinline def alignment: Int = throwUndefined()
    @alwaysinline def toInt: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _1.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _2.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _3.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 109)
      res
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 97)

  case class Digit4[N1 <: Nat.Base,
                    N2 <: Nat.Base,
                    N3 <: Nat.Base,
                    N4 <: Nat.Base](_1: Tag[N1],
                                    _2: Tag[N2],
                                    _3: Tag[N3],
                                    _4: Tag[N4])
      extends Tag[native.Nat.Digit4[N1, N2, N3, N4]]
      with NatTag {
    @alwaysinline def size: Int      = throwUndefined()
    @alwaysinline def alignment: Int = throwUndefined()
    @alwaysinline def toInt: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _1.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _2.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _3.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _4.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 109)
      res
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 97)

  case class Digit5[N1 <: Nat.Base,
                    N2 <: Nat.Base,
                    N3 <: Nat.Base,
                    N4 <: Nat.Base,
                    N5 <: Nat.Base](_1: Tag[N1],
                                    _2: Tag[N2],
                                    _3: Tag[N3],
                                    _4: Tag[N4],
                                    _5: Tag[N5])
      extends Tag[native.Nat.Digit5[N1, N2, N3, N4, N5]]
      with NatTag {
    @alwaysinline def size: Int      = throwUndefined()
    @alwaysinline def alignment: Int = throwUndefined()
    @alwaysinline def toInt: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _1.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _2.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _3.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _4.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _5.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 109)
      res
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 97)

  case class Digit6[N1 <: Nat.Base,
                    N2 <: Nat.Base,
                    N3 <: Nat.Base,
                    N4 <: Nat.Base,
                    N5 <: Nat.Base,
                    N6 <: Nat.Base](_1: Tag[N1],
                                    _2: Tag[N2],
                                    _3: Tag[N3],
                                    _4: Tag[N4],
                                    _5: Tag[N5],
                                    _6: Tag[N6])
      extends Tag[native.Nat.Digit6[N1, N2, N3, N4, N5, N6]]
      with NatTag {
    @alwaysinline def size: Int      = throwUndefined()
    @alwaysinline def alignment: Int = throwUndefined()
    @alwaysinline def toInt: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _1.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _2.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _3.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _4.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _5.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _6.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 109)
      res
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 97)

  case class Digit7[N1 <: Nat.Base,
                    N2 <: Nat.Base,
                    N3 <: Nat.Base,
                    N4 <: Nat.Base,
                    N5 <: Nat.Base,
                    N6 <: Nat.Base,
                    N7 <: Nat.Base](_1: Tag[N1],
                                    _2: Tag[N2],
                                    _3: Tag[N3],
                                    _4: Tag[N4],
                                    _5: Tag[N5],
                                    _6: Tag[N6],
                                    _7: Tag[N7])
      extends Tag[native.Nat.Digit7[N1, N2, N3, N4, N5, N6, N7]]
      with NatTag {
    @alwaysinline def size: Int      = throwUndefined()
    @alwaysinline def alignment: Int = throwUndefined()
    @alwaysinline def toInt: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _1.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _2.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _3.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _4.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _5.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _6.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _7.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 109)
      res
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 97)

  case class Digit8[N1 <: Nat.Base,
                    N2 <: Nat.Base,
                    N3 <: Nat.Base,
                    N4 <: Nat.Base,
                    N5 <: Nat.Base,
                    N6 <: Nat.Base,
                    N7 <: Nat.Base,
                    N8 <: Nat.Base](_1: Tag[N1],
                                    _2: Tag[N2],
                                    _3: Tag[N3],
                                    _4: Tag[N4],
                                    _5: Tag[N5],
                                    _6: Tag[N6],
                                    _7: Tag[N7],
                                    _8: Tag[N8])
      extends Tag[native.Nat.Digit8[N1, N2, N3, N4, N5, N6, N7, N8]]
      with NatTag {
    @alwaysinline def size: Int      = throwUndefined()
    @alwaysinline def alignment: Int = throwUndefined()
    @alwaysinline def toInt: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _1.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _2.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _3.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _4.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _5.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _6.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _7.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _8.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 109)
      res
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 97)

  case class Digit9[N1 <: Nat.Base,
                    N2 <: Nat.Base,
                    N3 <: Nat.Base,
                    N4 <: Nat.Base,
                    N5 <: Nat.Base,
                    N6 <: Nat.Base,
                    N7 <: Nat.Base,
                    N8 <: Nat.Base,
                    N9 <: Nat.Base](_1: Tag[N1],
                                    _2: Tag[N2],
                                    _3: Tag[N3],
                                    _4: Tag[N4],
                                    _5: Tag[N5],
                                    _6: Tag[N6],
                                    _7: Tag[N7],
                                    _8: Tag[N8],
                                    _9: Tag[N9])
      extends Tag[native.Nat.Digit9[N1, N2, N3, N4, N5, N6, N7, N8, N9]]
      with NatTag {
    @alwaysinline def size: Int      = throwUndefined()
    @alwaysinline def alignment: Int = throwUndefined()
    @alwaysinline def toInt: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _1.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _2.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _3.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _4.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _5.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _6.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _7.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _8.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 107)
      res = res * 10 + _9.asInstanceOf[NatTag].toInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 109)
      res
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 114)

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 116)

  sealed trait Field1[T, F] extends Tag[T]

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 116)

  sealed trait Field2[T, F] extends Tag[T]

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 116)

  sealed trait Field3[T, F] extends Tag[T]

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 116)

  sealed trait Field4[T, F] extends Tag[T]

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 116)

  sealed trait Field5[T, F] extends Tag[T]

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 116)

  sealed trait Field6[T, F] extends Tag[T]

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 116)

  sealed trait Field7[T, F] extends Tag[T]

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 116)

  sealed trait Field8[T, F] extends Tag[T]

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 116)

  sealed trait Field9[T, F] extends Tag[T]

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 116)

  sealed trait Field10[T, F] extends Tag[T]

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 116)

  sealed trait Field11[T, F] extends Tag[T]

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 116)

  sealed trait Field12[T, F] extends Tag[T]

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 116)

  sealed trait Field13[T, F] extends Tag[T]

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 116)

  sealed trait Field14[T, F] extends Tag[T]

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 116)

  sealed trait Field15[T, F] extends Tag[T]

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 116)

  sealed trait Field16[T, F] extends Tag[T]

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 116)

  sealed trait Field17[T, F] extends Tag[T]

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 116)

  sealed trait Field18[T, F] extends Tag[T]

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 116)

  sealed trait Field19[T, F] extends Tag[T]

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 116)

  sealed trait Field20[T, F] extends Tag[T]

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 116)

  sealed trait Field21[T, F] extends Tag[T]

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 116)

  sealed trait Field22[T, F] extends Tag[T]

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 120)

  case class CArray[T, N <: native.Nat](of: Tag[T], n: Tag[N])
      extends Tag[native.CArray[T, N]]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 124)
      with Field1[native.CArray[T, N], T]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 124)
      with Field2[native.CArray[T, N], T]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 124)
      with Field3[native.CArray[T, N], T]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 124)
      with Field4[native.CArray[T, N], T]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 124)
      with Field5[native.CArray[T, N], T]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 124)
      with Field6[native.CArray[T, N], T]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 124)
      with Field7[native.CArray[T, N], T]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 124)
      with Field8[native.CArray[T, N], T]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 124)
      with Field9[native.CArray[T, N], T]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 124)
      with Field10[native.CArray[T, N], T]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 124)
      with Field11[native.CArray[T, N], T]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 124)
      with Field12[native.CArray[T, N], T]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 124)
      with Field13[native.CArray[T, N], T]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 124)
      with Field14[native.CArray[T, N], T]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 124)
      with Field15[native.CArray[T, N], T]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 124)
      with Field16[native.CArray[T, N], T]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 124)
      with Field17[native.CArray[T, N], T]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 124)
      with Field18[native.CArray[T, N], T]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 124)
      with Field19[native.CArray[T, N], T]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 124)
      with Field20[native.CArray[T, N], T]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 124)
      with Field21[native.CArray[T, N], T]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 124)
      with Field22[native.CArray[T, N], T]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 126)
      {
    @alwaysinline def size: Int                      = of.size * n.asInstanceOf[NatTag].toInt
    @alwaysinline def alignment: Int                 = of.alignment
    @alwaysinline override def offset(idx: Int): Int = of.size * idx
  }

  private[scalanative] sealed trait StructTag

  @alwaysinline private[scalanative] def align(offset: Int, alignment: Int) = {
    val alignmentMask = alignment - 1
    val padding =
      if ((offset & alignmentMask) == 0) 0
      else alignment - (offset & alignmentMask)
    offset + padding
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 147)

  case class CStruct0()
      extends Tag[native.CStruct0]
      with StructTag
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 154)
      {
    @alwaysinline def size: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 160)
      align(res, alignment)
    }
    @alwaysinline def alignment: Int = {
      var res = 1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 167)
      res
    }
    @alwaysinline override def offset(idx: Int): Int = idx match {
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 178)
      case _ =>
        throwUndefined()
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 147)

  case class CStruct1[T1](_1: Tag[T1])
      extends Tag[native.CStruct1[T1]]
      with StructTag
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field1[native.CStruct1[T1], T1]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 154)
      {
    @alwaysinline def size: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 160)
      align(res, alignment)
    }
    @alwaysinline def alignment: Int = {
      var res = 1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 167)
      res
    }
    @alwaysinline override def offset(idx: Int): Int = idx match {
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 0 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 178)
      case _ =>
        throwUndefined()
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 147)

  case class CStruct2[T1, T2](_1: Tag[T1], _2: Tag[T2])
      extends Tag[native.CStruct2[T1, T2]]
      with StructTag
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field1[native.CStruct2[T1, T2], T1]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field2[native.CStruct2[T1, T2], T2]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 154)
      {
    @alwaysinline def size: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 160)
      align(res, alignment)
    }
    @alwaysinline def alignment: Int = {
      var res = 1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 167)
      res
    }
    @alwaysinline override def offset(idx: Int): Int = idx match {
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 0 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 1 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 178)
      case _ =>
        throwUndefined()
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 147)

  case class CStruct3[T1, T2, T3](_1: Tag[T1], _2: Tag[T2], _3: Tag[T3])
      extends Tag[native.CStruct3[T1, T2, T3]]
      with StructTag
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field1[native.CStruct3[T1, T2, T3], T1]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field2[native.CStruct3[T1, T2, T3], T2]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field3[native.CStruct3[T1, T2, T3], T3]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 154)
      {
    @alwaysinline def size: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 160)
      align(res, alignment)
    }
    @alwaysinline def alignment: Int = {
      var res = 1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 167)
      res
    }
    @alwaysinline override def offset(idx: Int): Int = idx match {
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 0 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 1 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 2 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 178)
      case _ =>
        throwUndefined()
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 147)

  case class CStruct4[T1, T2, T3, T4](_1: Tag[T1],
                                      _2: Tag[T2],
                                      _3: Tag[T3],
                                      _4: Tag[T4])
      extends Tag[native.CStruct4[T1, T2, T3, T4]]
      with StructTag
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field1[native.CStruct4[T1, T2, T3, T4], T1]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field2[native.CStruct4[T1, T2, T3, T4], T2]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field3[native.CStruct4[T1, T2, T3, T4], T3]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field4[native.CStruct4[T1, T2, T3, T4], T4]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 154)
      {
    @alwaysinline def size: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 160)
      align(res, alignment)
    }
    @alwaysinline def alignment: Int = {
      var res = 1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 167)
      res
    }
    @alwaysinline override def offset(idx: Int): Int = idx match {
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 0 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 1 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 2 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 3 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 178)
      case _ =>
        throwUndefined()
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 147)

  case class CStruct5[T1, T2, T3, T4, T5](_1: Tag[T1],
                                          _2: Tag[T2],
                                          _3: Tag[T3],
                                          _4: Tag[T4],
                                          _5: Tag[T5])
      extends Tag[native.CStruct5[T1, T2, T3, T4, T5]]
      with StructTag
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field1[native.CStruct5[T1, T2, T3, T4, T5], T1]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field2[native.CStruct5[T1, T2, T3, T4, T5], T2]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field3[native.CStruct5[T1, T2, T3, T4, T5], T3]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field4[native.CStruct5[T1, T2, T3, T4, T5], T4]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field5[native.CStruct5[T1, T2, T3, T4, T5], T5]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 154)
      {
    @alwaysinline def size: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 160)
      align(res, alignment)
    }
    @alwaysinline def alignment: Int = {
      var res = 1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 167)
      res
    }
    @alwaysinline override def offset(idx: Int): Int = idx match {
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 0 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 1 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 2 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 3 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 4 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 178)
      case _ =>
        throwUndefined()
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 147)

  case class CStruct6[T1, T2, T3, T4, T5, T6](_1: Tag[T1],
                                              _2: Tag[T2],
                                              _3: Tag[T3],
                                              _4: Tag[T4],
                                              _5: Tag[T5],
                                              _6: Tag[T6])
      extends Tag[native.CStruct6[T1, T2, T3, T4, T5, T6]]
      with StructTag
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field1[native.CStruct6[T1, T2, T3, T4, T5, T6], T1]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field2[native.CStruct6[T1, T2, T3, T4, T5, T6], T2]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field3[native.CStruct6[T1, T2, T3, T4, T5, T6], T3]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field4[native.CStruct6[T1, T2, T3, T4, T5, T6], T4]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field5[native.CStruct6[T1, T2, T3, T4, T5, T6], T5]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field6[native.CStruct6[T1, T2, T3, T4, T5, T6], T6]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 154)
      {
    @alwaysinline def size: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 160)
      align(res, alignment)
    }
    @alwaysinline def alignment: Int = {
      var res = 1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 167)
      res
    }
    @alwaysinline override def offset(idx: Int): Int = idx match {
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 0 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 1 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 2 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 3 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 4 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 5 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 178)
      case _ =>
        throwUndefined()
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 147)

  case class CStruct7[T1, T2, T3, T4, T5, T6, T7](_1: Tag[T1],
                                                  _2: Tag[T2],
                                                  _3: Tag[T3],
                                                  _4: Tag[T4],
                                                  _5: Tag[T5],
                                                  _6: Tag[T6],
                                                  _7: Tag[T7])
      extends Tag[native.CStruct7[T1, T2, T3, T4, T5, T6, T7]]
      with StructTag
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field1[native.CStruct7[T1, T2, T3, T4, T5, T6, T7], T1]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field2[native.CStruct7[T1, T2, T3, T4, T5, T6, T7], T2]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field3[native.CStruct7[T1, T2, T3, T4, T5, T6, T7], T3]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field4[native.CStruct7[T1, T2, T3, T4, T5, T6, T7], T4]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field5[native.CStruct7[T1, T2, T3, T4, T5, T6, T7], T5]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field6[native.CStruct7[T1, T2, T3, T4, T5, T6, T7], T6]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field7[native.CStruct7[T1, T2, T3, T4, T5, T6, T7], T7]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 154)
      {
    @alwaysinline def size: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 160)
      align(res, alignment)
    }
    @alwaysinline def alignment: Int = {
      var res = 1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 167)
      res
    }
    @alwaysinline override def offset(idx: Int): Int = idx match {
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 0 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 1 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 2 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 3 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 4 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 5 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 6 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 178)
      case _ =>
        throwUndefined()
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 147)

  case class CStruct8[T1, T2, T3, T4, T5, T6, T7, T8](_1: Tag[T1],
                                                      _2: Tag[T2],
                                                      _3: Tag[T3],
                                                      _4: Tag[T4],
                                                      _5: Tag[T5],
                                                      _6: Tag[T6],
                                                      _7: Tag[T7],
                                                      _8: Tag[T8])
      extends Tag[native.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8]]
      with StructTag
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field1[native.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8], T1]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field2[native.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8], T2]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field3[native.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8], T3]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field4[native.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8], T4]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field5[native.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8], T5]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field6[native.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8], T6]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field7[native.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8], T7]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field8[native.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8], T8]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 154)
      {
    @alwaysinline def size: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 160)
      align(res, alignment)
    }
    @alwaysinline def alignment: Int = {
      var res = 1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 167)
      res
    }
    @alwaysinline override def offset(idx: Int): Int = idx match {
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 0 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 1 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 2 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 3 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 4 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 5 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 6 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 7 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 178)
      case _ =>
        throwUndefined()
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 147)

  case class CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9](_1: Tag[T1],
                                                          _2: Tag[T2],
                                                          _3: Tag[T3],
                                                          _4: Tag[T4],
                                                          _5: Tag[T5],
                                                          _6: Tag[T6],
                                                          _7: Tag[T7],
                                                          _8: Tag[T8],
                                                          _9: Tag[T9])
      extends Tag[native.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9]]
      with StructTag
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field1[native.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9], T1]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field2[native.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9], T2]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field3[native.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9], T3]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field4[native.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9], T4]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field5[native.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9], T5]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field6[native.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9], T6]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field7[native.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9], T7]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field8[native.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9], T8]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field9[native.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9], T9]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 154)
      {
    @alwaysinline def size: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 160)
      align(res, alignment)
    }
    @alwaysinline def alignment: Int = {
      var res = 1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 167)
      res
    }
    @alwaysinline override def offset(idx: Int): Int = idx match {
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 0 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 1 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 2 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 3 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 4 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 5 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 6 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 7 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 8 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 178)
      case _ =>
        throwUndefined()
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 147)

  case class CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](_1: Tag[T1],
                                                                _2: Tag[T2],
                                                                _3: Tag[T3],
                                                                _4: Tag[T4],
                                                                _5: Tag[T5],
                                                                _6: Tag[T6],
                                                                _7: Tag[T7],
                                                                _8: Tag[T8],
                                                                _9: Tag[T9],
                                                                _10: Tag[T10])
      extends Tag[native.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10]]
      with StructTag
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field1[native.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10], T1]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field2[native.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10], T2]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field3[native.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10], T3]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field4[native.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10], T4]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field5[native.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10], T5]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field6[native.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10], T6]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field7[native.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10], T7]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field8[native.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10], T8]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field9[native.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10], T9]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field10[native.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10],
                   T10]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 154)
      {
    @alwaysinline def size: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 160)
      align(res, alignment)
    }
    @alwaysinline def alignment: Int = {
      var res = 1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 167)
      res
    }
    @alwaysinline override def offset(idx: Int): Int = idx match {
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 0 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 1 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 2 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 3 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 4 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 5 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 6 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 7 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 8 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 9 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 178)
      case _ =>
        throwUndefined()
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 147)

  case class CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](
      _1: Tag[T1],
      _2: Tag[T2],
      _3: Tag[T3],
      _4: Tag[T4],
      _5: Tag[T5],
      _6: Tag[T6],
      _7: Tag[T7],
      _8: Tag[T8],
      _9: Tag[T9],
      _10: Tag[T10],
      _11: Tag[T11])
      extends Tag[
        native.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11]]
      with StructTag
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field1[
        native.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11],
        T1]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field2[
        native.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11],
        T2]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field3[
        native.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11],
        T3]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field4[
        native.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11],
        T4]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field5[
        native.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11],
        T5]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field6[
        native.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11],
        T6]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field7[
        native.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11],
        T7]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field8[
        native.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11],
        T8]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field9[
        native.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11],
        T9]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field10[
        native.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11],
        T10]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field11[
        native.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11],
        T11]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 154)
      {
    @alwaysinline def size: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 160)
      align(res, alignment)
    }
    @alwaysinline def alignment: Int = {
      var res = 1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 167)
      res
    }
    @alwaysinline override def offset(idx: Int): Int = idx match {
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 0 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 1 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 2 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 3 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 4 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 5 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 6 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 7 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 8 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 9 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 10 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 178)
      case _ =>
        throwUndefined()
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 147)

  case class CStruct12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](
      _1: Tag[T1],
      _2: Tag[T2],
      _3: Tag[T3],
      _4: Tag[T4],
      _5: Tag[T5],
      _6: Tag[T6],
      _7: Tag[T7],
      _8: Tag[T8],
      _9: Tag[T9],
      _10: Tag[T10],
      _11: Tag[T11],
      _12: Tag[T12])
      extends Tag[
        native.CStruct12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12]]
      with StructTag
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field1[
        native.CStruct12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12],
        T1]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field2[
        native.CStruct12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12],
        T2]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field3[
        native.CStruct12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12],
        T3]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field4[
        native.CStruct12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12],
        T4]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field5[
        native.CStruct12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12],
        T5]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field6[
        native.CStruct12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12],
        T6]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field7[
        native.CStruct12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12],
        T7]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field8[
        native.CStruct12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12],
        T8]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field9[
        native.CStruct12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12],
        T9]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field10[
        native.CStruct12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12],
        T10]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field11[
        native.CStruct12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12],
        T11]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field12[
        native.CStruct12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12],
        T12]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 154)
      {
    @alwaysinline def size: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 160)
      align(res, alignment)
    }
    @alwaysinline def alignment: Int = {
      var res = 1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _12.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 167)
      res
    }
    @alwaysinline override def offset(idx: Int): Int = idx match {
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 0 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 1 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 2 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 3 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 4 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 5 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 6 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 7 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 8 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 9 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 10 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 11 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _12.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 178)
      case _ =>
        throwUndefined()
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 147)

  case class CStruct13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](
      _1: Tag[T1],
      _2: Tag[T2],
      _3: Tag[T3],
      _4: Tag[T4],
      _5: Tag[T5],
      _6: Tag[T6],
      _7: Tag[T7],
      _8: Tag[T8],
      _9: Tag[T9],
      _10: Tag[T10],
      _11: Tag[T11],
      _12: Tag[T12],
      _13: Tag[T13])
      extends Tag[
        native.CStruct13[T1,
                         T2,
                         T3,
                         T4,
                         T5,
                         T6,
                         T7,
                         T8,
                         T9,
                         T10,
                         T11,
                         T12,
                         T13]]
      with StructTag
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field1[native.CStruct13[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13],
                  T1]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field2[native.CStruct13[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13],
                  T2]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field3[native.CStruct13[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13],
                  T3]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field4[native.CStruct13[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13],
                  T4]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field5[native.CStruct13[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13],
                  T5]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field6[native.CStruct13[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13],
                  T6]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field7[native.CStruct13[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13],
                  T7]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field8[native.CStruct13[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13],
                  T8]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field9[native.CStruct13[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13],
                  T9]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field10[native.CStruct13[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13],
                   T10]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field11[native.CStruct13[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13],
                   T11]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field12[native.CStruct13[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13],
                   T12]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field13[native.CStruct13[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13],
                   T13]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 154)
      {
    @alwaysinline def size: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 160)
      align(res, alignment)
    }
    @alwaysinline def alignment: Int = {
      var res = 1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _12.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _13.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 167)
      res
    }
    @alwaysinline override def offset(idx: Int): Int = idx match {
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 0 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 1 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 2 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 3 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 4 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 5 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 6 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 7 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 8 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 9 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 10 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 11 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _12.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 12 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _13.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 178)
      case _ =>
        throwUndefined()
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 147)

  case class CStruct14[T1,
                       T2,
                       T3,
                       T4,
                       T5,
                       T6,
                       T7,
                       T8,
                       T9,
                       T10,
                       T11,
                       T12,
                       T13,
                       T14](_1: Tag[T1],
                            _2: Tag[T2],
                            _3: Tag[T3],
                            _4: Tag[T4],
                            _5: Tag[T5],
                            _6: Tag[T6],
                            _7: Tag[T7],
                            _8: Tag[T8],
                            _9: Tag[T9],
                            _10: Tag[T10],
                            _11: Tag[T11],
                            _12: Tag[T12],
                            _13: Tag[T13],
                            _14: Tag[T14])
      extends Tag[
        native.CStruct14[T1,
                         T2,
                         T3,
                         T4,
                         T5,
                         T6,
                         T7,
                         T8,
                         T9,
                         T10,
                         T11,
                         T12,
                         T13,
                         T14]]
      with StructTag
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field1[native.CStruct14[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14],
                  T1]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field2[native.CStruct14[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14],
                  T2]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field3[native.CStruct14[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14],
                  T3]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field4[native.CStruct14[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14],
                  T4]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field5[native.CStruct14[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14],
                  T5]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field6[native.CStruct14[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14],
                  T6]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field7[native.CStruct14[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14],
                  T7]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field8[native.CStruct14[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14],
                  T8]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field9[native.CStruct14[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14],
                  T9]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field10[native.CStruct14[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14],
                   T10]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field11[native.CStruct14[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14],
                   T11]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field12[native.CStruct14[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14],
                   T12]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field13[native.CStruct14[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14],
                   T13]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field14[native.CStruct14[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14],
                   T14]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 154)
      {
    @alwaysinline def size: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 160)
      align(res, alignment)
    }
    @alwaysinline def alignment: Int = {
      var res = 1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _12.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _13.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _14.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 167)
      res
    }
    @alwaysinline override def offset(idx: Int): Int = idx match {
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 0 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 1 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 2 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 3 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 4 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 5 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 6 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 7 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 8 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 9 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 10 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 11 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _12.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 12 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _13.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 13 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _14.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 178)
      case _ =>
        throwUndefined()
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 147)

  case class CStruct15[T1,
                       T2,
                       T3,
                       T4,
                       T5,
                       T6,
                       T7,
                       T8,
                       T9,
                       T10,
                       T11,
                       T12,
                       T13,
                       T14,
                       T15](_1: Tag[T1],
                            _2: Tag[T2],
                            _3: Tag[T3],
                            _4: Tag[T4],
                            _5: Tag[T5],
                            _6: Tag[T6],
                            _7: Tag[T7],
                            _8: Tag[T8],
                            _9: Tag[T9],
                            _10: Tag[T10],
                            _11: Tag[T11],
                            _12: Tag[T12],
                            _13: Tag[T13],
                            _14: Tag[T14],
                            _15: Tag[T15])
      extends Tag[
        native.CStruct15[T1,
                         T2,
                         T3,
                         T4,
                         T5,
                         T6,
                         T7,
                         T8,
                         T9,
                         T10,
                         T11,
                         T12,
                         T13,
                         T14,
                         T15]]
      with StructTag
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field1[native.CStruct15[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15],
                  T1]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field2[native.CStruct15[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15],
                  T2]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field3[native.CStruct15[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15],
                  T3]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field4[native.CStruct15[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15],
                  T4]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field5[native.CStruct15[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15],
                  T5]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field6[native.CStruct15[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15],
                  T6]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field7[native.CStruct15[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15],
                  T7]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field8[native.CStruct15[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15],
                  T8]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field9[native.CStruct15[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15],
                  T9]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field10[native.CStruct15[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15],
                   T10]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field11[native.CStruct15[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15],
                   T11]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field12[native.CStruct15[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15],
                   T12]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field13[native.CStruct15[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15],
                   T13]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field14[native.CStruct15[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15],
                   T14]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field15[native.CStruct15[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15],
                   T15]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 154)
      {
    @alwaysinline def size: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 160)
      align(res, alignment)
    }
    @alwaysinline def alignment: Int = {
      var res = 1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _12.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _13.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _14.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _15.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 167)
      res
    }
    @alwaysinline override def offset(idx: Int): Int = idx match {
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 0 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 1 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 2 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 3 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 4 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 5 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 6 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 7 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 8 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 9 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 10 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 11 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _12.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 12 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _13.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 13 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _14.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 14 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _15.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 178)
      case _ =>
        throwUndefined()
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 147)

  case class CStruct16[T1,
                       T2,
                       T3,
                       T4,
                       T5,
                       T6,
                       T7,
                       T8,
                       T9,
                       T10,
                       T11,
                       T12,
                       T13,
                       T14,
                       T15,
                       T16](_1: Tag[T1],
                            _2: Tag[T2],
                            _3: Tag[T3],
                            _4: Tag[T4],
                            _5: Tag[T5],
                            _6: Tag[T6],
                            _7: Tag[T7],
                            _8: Tag[T8],
                            _9: Tag[T9],
                            _10: Tag[T10],
                            _11: Tag[T11],
                            _12: Tag[T12],
                            _13: Tag[T13],
                            _14: Tag[T14],
                            _15: Tag[T15],
                            _16: Tag[T16])
      extends Tag[
        native.CStruct16[T1,
                         T2,
                         T3,
                         T4,
                         T5,
                         T6,
                         T7,
                         T8,
                         T9,
                         T10,
                         T11,
                         T12,
                         T13,
                         T14,
                         T15,
                         T16]]
      with StructTag
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field1[native.CStruct16[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16],
                  T1]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field2[native.CStruct16[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16],
                  T2]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field3[native.CStruct16[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16],
                  T3]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field4[native.CStruct16[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16],
                  T4]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field5[native.CStruct16[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16],
                  T5]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field6[native.CStruct16[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16],
                  T6]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field7[native.CStruct16[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16],
                  T7]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field8[native.CStruct16[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16],
                  T8]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field9[native.CStruct16[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16],
                  T9]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field10[native.CStruct16[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16],
                   T10]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field11[native.CStruct16[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16],
                   T11]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field12[native.CStruct16[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16],
                   T12]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field13[native.CStruct16[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16],
                   T13]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field14[native.CStruct16[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16],
                   T14]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field15[native.CStruct16[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16],
                   T15]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field16[native.CStruct16[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16],
                   T16]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 154)
      {
    @alwaysinline def size: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 160)
      align(res, alignment)
    }
    @alwaysinline def alignment: Int = {
      var res = 1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _12.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _13.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _14.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _15.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _16.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 167)
      res
    }
    @alwaysinline override def offset(idx: Int): Int = idx match {
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 0 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 1 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 2 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 3 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 4 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 5 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 6 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 7 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 8 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 9 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 10 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 11 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _12.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 12 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _13.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 13 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _14.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 14 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _15.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 15 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _16.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 178)
      case _ =>
        throwUndefined()
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 147)

  case class CStruct17[T1,
                       T2,
                       T3,
                       T4,
                       T5,
                       T6,
                       T7,
                       T8,
                       T9,
                       T10,
                       T11,
                       T12,
                       T13,
                       T14,
                       T15,
                       T16,
                       T17](_1: Tag[T1],
                            _2: Tag[T2],
                            _3: Tag[T3],
                            _4: Tag[T4],
                            _5: Tag[T5],
                            _6: Tag[T6],
                            _7: Tag[T7],
                            _8: Tag[T8],
                            _9: Tag[T9],
                            _10: Tag[T10],
                            _11: Tag[T11],
                            _12: Tag[T12],
                            _13: Tag[T13],
                            _14: Tag[T14],
                            _15: Tag[T15],
                            _16: Tag[T16],
                            _17: Tag[T17])
      extends Tag[
        native.CStruct17[T1,
                         T2,
                         T3,
                         T4,
                         T5,
                         T6,
                         T7,
                         T8,
                         T9,
                         T10,
                         T11,
                         T12,
                         T13,
                         T14,
                         T15,
                         T16,
                         T17]]
      with StructTag
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field1[native.CStruct17[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17],
                  T1]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field2[native.CStruct17[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17],
                  T2]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field3[native.CStruct17[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17],
                  T3]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field4[native.CStruct17[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17],
                  T4]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field5[native.CStruct17[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17],
                  T5]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field6[native.CStruct17[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17],
                  T6]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field7[native.CStruct17[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17],
                  T7]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field8[native.CStruct17[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17],
                  T8]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field9[native.CStruct17[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17],
                  T9]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field10[native.CStruct17[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17],
                   T10]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field11[native.CStruct17[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17],
                   T11]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field12[native.CStruct17[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17],
                   T12]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field13[native.CStruct17[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17],
                   T13]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field14[native.CStruct17[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17],
                   T14]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field15[native.CStruct17[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17],
                   T15]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field16[native.CStruct17[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17],
                   T16]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field17[native.CStruct17[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17],
                   T17]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 154)
      {
    @alwaysinline def size: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _17.alignment) + _17.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 160)
      align(res, alignment)
    }
    @alwaysinline def alignment: Int = {
      var res = 1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _12.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _13.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _14.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _15.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _16.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _17.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 167)
      res
    }
    @alwaysinline override def offset(idx: Int): Int = idx match {
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 0 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 1 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 2 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 3 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 4 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 5 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 6 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 7 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 8 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 9 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 10 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 11 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _12.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 12 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _13.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 13 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _14.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 14 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _15.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 15 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _16.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 16 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _17.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 178)
      case _ =>
        throwUndefined()
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 147)

  case class CStruct18[T1,
                       T2,
                       T3,
                       T4,
                       T5,
                       T6,
                       T7,
                       T8,
                       T9,
                       T10,
                       T11,
                       T12,
                       T13,
                       T14,
                       T15,
                       T16,
                       T17,
                       T18](_1: Tag[T1],
                            _2: Tag[T2],
                            _3: Tag[T3],
                            _4: Tag[T4],
                            _5: Tag[T5],
                            _6: Tag[T6],
                            _7: Tag[T7],
                            _8: Tag[T8],
                            _9: Tag[T9],
                            _10: Tag[T10],
                            _11: Tag[T11],
                            _12: Tag[T12],
                            _13: Tag[T13],
                            _14: Tag[T14],
                            _15: Tag[T15],
                            _16: Tag[T16],
                            _17: Tag[T17],
                            _18: Tag[T18])
      extends Tag[
        native.CStruct18[T1,
                         T2,
                         T3,
                         T4,
                         T5,
                         T6,
                         T7,
                         T8,
                         T9,
                         T10,
                         T11,
                         T12,
                         T13,
                         T14,
                         T15,
                         T16,
                         T17,
                         T18]]
      with StructTag
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field1[native.CStruct18[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18],
                  T1]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field2[native.CStruct18[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18],
                  T2]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field3[native.CStruct18[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18],
                  T3]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field4[native.CStruct18[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18],
                  T4]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field5[native.CStruct18[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18],
                  T5]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field6[native.CStruct18[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18],
                  T6]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field7[native.CStruct18[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18],
                  T7]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field8[native.CStruct18[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18],
                  T8]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field9[native.CStruct18[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18],
                  T9]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field10[native.CStruct18[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18],
                   T10]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field11[native.CStruct18[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18],
                   T11]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field12[native.CStruct18[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18],
                   T12]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field13[native.CStruct18[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18],
                   T13]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field14[native.CStruct18[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18],
                   T14]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field15[native.CStruct18[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18],
                   T15]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field16[native.CStruct18[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18],
                   T16]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field17[native.CStruct18[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18],
                   T17]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field18[native.CStruct18[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18],
                   T18]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 154)
      {
    @alwaysinline def size: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _17.alignment) + _17.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _18.alignment) + _18.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 160)
      align(res, alignment)
    }
    @alwaysinline def alignment: Int = {
      var res = 1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _12.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _13.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _14.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _15.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _16.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _17.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _18.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 167)
      res
    }
    @alwaysinline override def offset(idx: Int): Int = idx match {
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 0 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 1 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 2 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 3 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 4 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 5 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 6 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 7 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 8 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 9 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 10 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 11 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _12.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 12 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _13.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 13 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _14.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 14 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _15.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 15 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _16.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 16 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _17.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 17 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _17.alignment) + _17.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _18.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 178)
      case _ =>
        throwUndefined()
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 147)

  case class CStruct19[T1,
                       T2,
                       T3,
                       T4,
                       T5,
                       T6,
                       T7,
                       T8,
                       T9,
                       T10,
                       T11,
                       T12,
                       T13,
                       T14,
                       T15,
                       T16,
                       T17,
                       T18,
                       T19](_1: Tag[T1],
                            _2: Tag[T2],
                            _3: Tag[T3],
                            _4: Tag[T4],
                            _5: Tag[T5],
                            _6: Tag[T6],
                            _7: Tag[T7],
                            _8: Tag[T8],
                            _9: Tag[T9],
                            _10: Tag[T10],
                            _11: Tag[T11],
                            _12: Tag[T12],
                            _13: Tag[T13],
                            _14: Tag[T14],
                            _15: Tag[T15],
                            _16: Tag[T16],
                            _17: Tag[T17],
                            _18: Tag[T18],
                            _19: Tag[T19])
      extends Tag[
        native.CStruct19[T1,
                         T2,
                         T3,
                         T4,
                         T5,
                         T6,
                         T7,
                         T8,
                         T9,
                         T10,
                         T11,
                         T12,
                         T13,
                         T14,
                         T15,
                         T16,
                         T17,
                         T18,
                         T19]]
      with StructTag
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field1[native.CStruct19[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19],
                  T1]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field2[native.CStruct19[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19],
                  T2]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field3[native.CStruct19[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19],
                  T3]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field4[native.CStruct19[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19],
                  T4]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field5[native.CStruct19[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19],
                  T5]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field6[native.CStruct19[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19],
                  T6]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field7[native.CStruct19[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19],
                  T7]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field8[native.CStruct19[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19],
                  T8]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field9[native.CStruct19[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19],
                  T9]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field10[native.CStruct19[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19],
                   T10]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field11[native.CStruct19[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19],
                   T11]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field12[native.CStruct19[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19],
                   T12]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field13[native.CStruct19[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19],
                   T13]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field14[native.CStruct19[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19],
                   T14]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field15[native.CStruct19[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19],
                   T15]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field16[native.CStruct19[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19],
                   T16]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field17[native.CStruct19[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19],
                   T17]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field18[native.CStruct19[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19],
                   T18]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field19[native.CStruct19[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19],
                   T19]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 154)
      {
    @alwaysinline def size: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _17.alignment) + _17.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _18.alignment) + _18.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _19.alignment) + _19.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 160)
      align(res, alignment)
    }
    @alwaysinline def alignment: Int = {
      var res = 1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _12.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _13.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _14.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _15.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _16.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _17.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _18.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _19.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 167)
      res
    }
    @alwaysinline override def offset(idx: Int): Int = idx match {
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 0 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 1 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 2 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 3 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 4 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 5 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 6 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 7 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 8 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 9 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 10 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 11 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _12.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 12 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _13.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 13 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _14.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 14 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _15.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 15 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _16.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 16 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _17.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 17 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _17.alignment) + _17.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _18.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 18 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _17.alignment) + _17.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _18.alignment) + _18.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _19.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 178)
      case _ =>
        throwUndefined()
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 147)

  case class CStruct20[T1,
                       T2,
                       T3,
                       T4,
                       T5,
                       T6,
                       T7,
                       T8,
                       T9,
                       T10,
                       T11,
                       T12,
                       T13,
                       T14,
                       T15,
                       T16,
                       T17,
                       T18,
                       T19,
                       T20](_1: Tag[T1],
                            _2: Tag[T2],
                            _3: Tag[T3],
                            _4: Tag[T4],
                            _5: Tag[T5],
                            _6: Tag[T6],
                            _7: Tag[T7],
                            _8: Tag[T8],
                            _9: Tag[T9],
                            _10: Tag[T10],
                            _11: Tag[T11],
                            _12: Tag[T12],
                            _13: Tag[T13],
                            _14: Tag[T14],
                            _15: Tag[T15],
                            _16: Tag[T16],
                            _17: Tag[T17],
                            _18: Tag[T18],
                            _19: Tag[T19],
                            _20: Tag[T20])
      extends Tag[
        native.CStruct20[T1,
                         T2,
                         T3,
                         T4,
                         T5,
                         T6,
                         T7,
                         T8,
                         T9,
                         T10,
                         T11,
                         T12,
                         T13,
                         T14,
                         T15,
                         T16,
                         T17,
                         T18,
                         T19,
                         T20]]
      with StructTag
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field1[native.CStruct20[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20],
                  T1]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field2[native.CStruct20[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20],
                  T2]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field3[native.CStruct20[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20],
                  T3]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field4[native.CStruct20[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20],
                  T4]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field5[native.CStruct20[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20],
                  T5]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field6[native.CStruct20[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20],
                  T6]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field7[native.CStruct20[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20],
                  T7]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field8[native.CStruct20[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20],
                  T8]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field9[native.CStruct20[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20],
                  T9]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field10[native.CStruct20[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20],
                   T10]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field11[native.CStruct20[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20],
                   T11]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field12[native.CStruct20[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20],
                   T12]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field13[native.CStruct20[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20],
                   T13]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field14[native.CStruct20[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20],
                   T14]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field15[native.CStruct20[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20],
                   T15]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field16[native.CStruct20[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20],
                   T16]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field17[native.CStruct20[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20],
                   T17]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field18[native.CStruct20[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20],
                   T18]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field19[native.CStruct20[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20],
                   T19]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field20[native.CStruct20[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20],
                   T20]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 154)
      {
    @alwaysinline def size: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _17.alignment) + _17.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _18.alignment) + _18.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _19.alignment) + _19.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _20.alignment) + _20.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 160)
      align(res, alignment)
    }
    @alwaysinline def alignment: Int = {
      var res = 1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _12.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _13.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _14.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _15.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _16.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _17.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _18.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _19.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _20.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 167)
      res
    }
    @alwaysinline override def offset(idx: Int): Int = idx match {
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 0 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 1 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 2 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 3 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 4 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 5 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 6 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 7 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 8 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 9 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 10 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 11 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _12.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 12 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _13.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 13 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _14.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 14 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _15.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 15 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _16.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 16 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _17.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 17 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _17.alignment) + _17.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _18.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 18 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _17.alignment) + _17.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _18.alignment) + _18.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _19.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 19 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _17.alignment) + _17.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _18.alignment) + _18.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _19.alignment) + _19.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _20.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 178)
      case _ =>
        throwUndefined()
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 147)

  case class CStruct21[T1,
                       T2,
                       T3,
                       T4,
                       T5,
                       T6,
                       T7,
                       T8,
                       T9,
                       T10,
                       T11,
                       T12,
                       T13,
                       T14,
                       T15,
                       T16,
                       T17,
                       T18,
                       T19,
                       T20,
                       T21](_1: Tag[T1],
                            _2: Tag[T2],
                            _3: Tag[T3],
                            _4: Tag[T4],
                            _5: Tag[T5],
                            _6: Tag[T6],
                            _7: Tag[T7],
                            _8: Tag[T8],
                            _9: Tag[T9],
                            _10: Tag[T10],
                            _11: Tag[T11],
                            _12: Tag[T12],
                            _13: Tag[T13],
                            _14: Tag[T14],
                            _15: Tag[T15],
                            _16: Tag[T16],
                            _17: Tag[T17],
                            _18: Tag[T18],
                            _19: Tag[T19],
                            _20: Tag[T20],
                            _21: Tag[T21])
      extends Tag[
        native.CStruct21[T1,
                         T2,
                         T3,
                         T4,
                         T5,
                         T6,
                         T7,
                         T8,
                         T9,
                         T10,
                         T11,
                         T12,
                         T13,
                         T14,
                         T15,
                         T16,
                         T17,
                         T18,
                         T19,
                         T20,
                         T21]]
      with StructTag
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field1[native.CStruct21[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20,
                                   T21],
                  T1]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field2[native.CStruct21[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20,
                                   T21],
                  T2]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field3[native.CStruct21[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20,
                                   T21],
                  T3]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field4[native.CStruct21[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20,
                                   T21],
                  T4]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field5[native.CStruct21[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20,
                                   T21],
                  T5]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field6[native.CStruct21[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20,
                                   T21],
                  T6]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field7[native.CStruct21[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20,
                                   T21],
                  T7]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field8[native.CStruct21[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20,
                                   T21],
                  T8]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field9[native.CStruct21[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20,
                                   T21],
                  T9]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field10[native.CStruct21[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21],
                   T10]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field11[native.CStruct21[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21],
                   T11]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field12[native.CStruct21[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21],
                   T12]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field13[native.CStruct21[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21],
                   T13]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field14[native.CStruct21[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21],
                   T14]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field15[native.CStruct21[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21],
                   T15]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field16[native.CStruct21[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21],
                   T16]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field17[native.CStruct21[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21],
                   T17]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field18[native.CStruct21[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21],
                   T18]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field19[native.CStruct21[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21],
                   T19]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field20[native.CStruct21[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21],
                   T20]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field21[native.CStruct21[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21],
                   T21]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 154)
      {
    @alwaysinline def size: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _17.alignment) + _17.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _18.alignment) + _18.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _19.alignment) + _19.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _20.alignment) + _20.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _21.alignment) + _21.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 160)
      align(res, alignment)
    }
    @alwaysinline def alignment: Int = {
      var res = 1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _12.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _13.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _14.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _15.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _16.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _17.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _18.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _19.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _20.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _21.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 167)
      res
    }
    @alwaysinline override def offset(idx: Int): Int = idx match {
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 0 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 1 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 2 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 3 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 4 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 5 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 6 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 7 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 8 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 9 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 10 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 11 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _12.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 12 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _13.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 13 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _14.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 14 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _15.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 15 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _16.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 16 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _17.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 17 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _17.alignment) + _17.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _18.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 18 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _17.alignment) + _17.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _18.alignment) + _18.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _19.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 19 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _17.alignment) + _17.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _18.alignment) + _18.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _19.alignment) + _19.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _20.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 20 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _17.alignment) + _17.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _18.alignment) + _18.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _19.alignment) + _19.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _20.alignment) + _20.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _21.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 178)
      case _ =>
        throwUndefined()
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 147)

  case class CStruct22[T1,
                       T2,
                       T3,
                       T4,
                       T5,
                       T6,
                       T7,
                       T8,
                       T9,
                       T10,
                       T11,
                       T12,
                       T13,
                       T14,
                       T15,
                       T16,
                       T17,
                       T18,
                       T19,
                       T20,
                       T21,
                       T22](_1: Tag[T1],
                            _2: Tag[T2],
                            _3: Tag[T3],
                            _4: Tag[T4],
                            _5: Tag[T5],
                            _6: Tag[T6],
                            _7: Tag[T7],
                            _8: Tag[T8],
                            _9: Tag[T9],
                            _10: Tag[T10],
                            _11: Tag[T11],
                            _12: Tag[T12],
                            _13: Tag[T13],
                            _14: Tag[T14],
                            _15: Tag[T15],
                            _16: Tag[T16],
                            _17: Tag[T17],
                            _18: Tag[T18],
                            _19: Tag[T19],
                            _20: Tag[T20],
                            _21: Tag[T21],
                            _22: Tag[T22])
      extends Tag[
        native.CStruct22[T1,
                         T2,
                         T3,
                         T4,
                         T5,
                         T6,
                         T7,
                         T8,
                         T9,
                         T10,
                         T11,
                         T12,
                         T13,
                         T14,
                         T15,
                         T16,
                         T17,
                         T18,
                         T19,
                         T20,
                         T21,
                         T22]]
      with StructTag
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field1[native.CStruct22[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20,
                                   T21,
                                   T22],
                  T1]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field2[native.CStruct22[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20,
                                   T21,
                                   T22],
                  T2]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field3[native.CStruct22[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20,
                                   T21,
                                   T22],
                  T3]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field4[native.CStruct22[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20,
                                   T21,
                                   T22],
                  T4]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field5[native.CStruct22[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20,
                                   T21,
                                   T22],
                  T5]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field6[native.CStruct22[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20,
                                   T21,
                                   T22],
                  T6]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field7[native.CStruct22[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20,
                                   T21,
                                   T22],
                  T7]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field8[native.CStruct22[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20,
                                   T21,
                                   T22],
                  T8]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field9[native.CStruct22[T1,
                                   T2,
                                   T3,
                                   T4,
                                   T5,
                                   T6,
                                   T7,
                                   T8,
                                   T9,
                                   T10,
                                   T11,
                                   T12,
                                   T13,
                                   T14,
                                   T15,
                                   T16,
                                   T17,
                                   T18,
                                   T19,
                                   T20,
                                   T21,
                                   T22],
                  T9]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field10[native.CStruct22[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21,
                                    T22],
                   T10]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field11[native.CStruct22[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21,
                                    T22],
                   T11]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field12[native.CStruct22[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21,
                                    T22],
                   T12]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field13[native.CStruct22[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21,
                                    T22],
                   T13]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field14[native.CStruct22[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21,
                                    T22],
                   T14]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field15[native.CStruct22[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21,
                                    T22],
                   T15]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field16[native.CStruct22[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21,
                                    T22],
                   T16]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field17[native.CStruct22[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21,
                                    T22],
                   T17]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field18[native.CStruct22[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21,
                                    T22],
                   T18]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field19[native.CStruct22[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21,
                                    T22],
                   T19]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field20[native.CStruct22[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21,
                                    T22],
                   T20]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field21[native.CStruct22[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21,
                                    T22],
                   T21]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 152)
      with Field22[native.CStruct22[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    T19,
                                    T20,
                                    T21,
                                    T22],
                   T22]
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 154)
      {
    @alwaysinline def size: Int = {
      var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _17.alignment) + _17.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _18.alignment) + _18.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _19.alignment) + _19.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _20.alignment) + _20.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _21.alignment) + _21.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 158)
      res = align(res, _22.alignment) + _22.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 160)
      align(res, alignment)
    }
    @alwaysinline def alignment: Int = {
      var res = 1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _12.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _13.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _14.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _15.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _16.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _17.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _18.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _19.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _20.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _21.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 165)
      res = Math.max(res, _22.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 167)
      res
    }
    @alwaysinline override def offset(idx: Int): Int = idx match {
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 0 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _1.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 1 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _2.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 2 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _3.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 3 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _4.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 4 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _5.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 5 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _6.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 6 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _7.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 7 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _8.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 8 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _9.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 9 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _10.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 10 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _11.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 11 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _12.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 12 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _13.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 13 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _14.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 14 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _15.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 15 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _16.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 16 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _17.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 17 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _17.alignment) + _17.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _18.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 18 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _17.alignment) + _17.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _18.alignment) + _18.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _19.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 19 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _17.alignment) + _17.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _18.alignment) + _18.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _19.alignment) + _19.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _20.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 20 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _17.alignment) + _17.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _18.alignment) + _18.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _19.alignment) + _19.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _20.alignment) + _20.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _21.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 171)
      case 21 =>
        var res = 0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _1.alignment) + _1.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _2.alignment) + _2.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _3.alignment) + _3.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _4.alignment) + _4.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _5.alignment) + _5.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _6.alignment) + _6.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _7.alignment) + _7.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _8.alignment) + _8.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _9.alignment) + _9.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _10.alignment) + _10.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _11.alignment) + _11.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _12.alignment) + _12.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _13.alignment) + _13.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _14.alignment) + _14.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _15.alignment) + _15.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _16.alignment) + _16.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _17.alignment) + _17.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _18.alignment) + _18.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _19.alignment) + _19.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _20.alignment) + _20.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 174)
        res = align(res, _21.alignment) + _21.size
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 176)
        align(res, _22.alignment)
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 178)
      case _ =>
        throwUndefined()
    }
  }

// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 184)

  @alwaysinline implicit def materializePtrTag[T](
      implicit tag: Tag[T]): Tag[native.Ptr[T]] =
    Tag.Ptr(tag)
  @alwaysinline implicit def materializeClassTag[T <: AnyRef: ClassTag]
    : Tag[T] =
    Tag.Class(
      implicitly[ClassTag[T]].runtimeClass.asInstanceOf[java.lang.Class[T]])
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 190)
  @alwaysinline implicit def materializeUnitTag: Tag[scala.Unit] =
    Unit
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 190)
  @alwaysinline implicit def materializeBooleanTag: Tag[scala.Boolean] =
    Boolean
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 190)
  @alwaysinline implicit def materializeCharTag: Tag[scala.Char] =
    Char
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 190)
  @alwaysinline implicit def materializeByteTag: Tag[scala.Byte] =
    Byte
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 190)
  @alwaysinline implicit def materializeUByteTag: Tag[native.UByte] =
    UByte
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 190)
  @alwaysinline implicit def materializeShortTag: Tag[scala.Short] =
    Short
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 190)
  @alwaysinline implicit def materializeUShortTag: Tag[native.UShort] =
    UShort
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 190)
  @alwaysinline implicit def materializeIntTag: Tag[scala.Int] =
    Int
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 190)
  @alwaysinline implicit def materializeUIntTag: Tag[native.UInt] =
    UInt
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 190)
  @alwaysinline implicit def materializeLongTag: Tag[scala.Long] =
    Long
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 190)
  @alwaysinline implicit def materializeULongTag: Tag[native.ULong] =
    ULong
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 190)
  @alwaysinline implicit def materializeFloatTag: Tag[scala.Float] =
    Float
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 190)
  @alwaysinline implicit def materializeDoubleTag: Tag[scala.Double] =
    Double
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 194)
  @alwaysinline implicit def materializeNat0Tag: Tag[native.Nat._0] =
    Nat0
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 194)
  @alwaysinline implicit def materializeNat1Tag: Tag[native.Nat._1] =
    Nat1
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 194)
  @alwaysinline implicit def materializeNat2Tag: Tag[native.Nat._2] =
    Nat2
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 194)
  @alwaysinline implicit def materializeNat3Tag: Tag[native.Nat._3] =
    Nat3
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 194)
  @alwaysinline implicit def materializeNat4Tag: Tag[native.Nat._4] =
    Nat4
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 194)
  @alwaysinline implicit def materializeNat5Tag: Tag[native.Nat._5] =
    Nat5
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 194)
  @alwaysinline implicit def materializeNat6Tag: Tag[native.Nat._6] =
    Nat6
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 194)
  @alwaysinline implicit def materializeNat7Tag: Tag[native.Nat._7] =
    Nat7
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 194)
  @alwaysinline implicit def materializeNat8Tag: Tag[native.Nat._8] =
    Nat8
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 194)
  @alwaysinline implicit def materializeNat9Tag: Tag[native.Nat._9] =
    Nat9
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 202)
  @alwaysinline implicit def materializeNatDigit2Tag[N1 <: Nat.Base: Tag,
                                                     N2 <: Nat.Base: Tag]
    : Tag.Digit2[N1, N2] =
    Tag.Digit2(implicitly[Tag[N1]], implicitly[Tag[N2]])
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 202)
  @alwaysinline implicit def materializeNatDigit3Tag[N1 <: Nat.Base: Tag,
                                                     N2 <: Nat.Base: Tag,
                                                     N3 <: Nat.Base: Tag]
    : Tag.Digit3[N1, N2, N3] =
    Tag.Digit3(implicitly[Tag[N1]], implicitly[Tag[N2]], implicitly[Tag[N3]])
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 202)
  @alwaysinline implicit def materializeNatDigit4Tag[N1 <: Nat.Base: Tag,
                                                     N2 <: Nat.Base: Tag,
                                                     N3 <: Nat.Base: Tag,
                                                     N4 <: Nat.Base: Tag]
    : Tag.Digit4[N1, N2, N3, N4] =
    Tag.Digit4(implicitly[Tag[N1]],
               implicitly[Tag[N2]],
               implicitly[Tag[N3]],
               implicitly[Tag[N4]])
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 202)
  @alwaysinline implicit def materializeNatDigit5Tag[N1 <: Nat.Base: Tag,
                                                     N2 <: Nat.Base: Tag,
                                                     N3 <: Nat.Base: Tag,
                                                     N4 <: Nat.Base: Tag,
                                                     N5 <: Nat.Base: Tag]
    : Tag.Digit5[N1, N2, N3, N4, N5] =
    Tag.Digit5(implicitly[Tag[N1]],
               implicitly[Tag[N2]],
               implicitly[Tag[N3]],
               implicitly[Tag[N4]],
               implicitly[Tag[N5]])
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 202)
  @alwaysinline implicit def materializeNatDigit6Tag[N1 <: Nat.Base: Tag,
                                                     N2 <: Nat.Base: Tag,
                                                     N3 <: Nat.Base: Tag,
                                                     N4 <: Nat.Base: Tag,
                                                     N5 <: Nat.Base: Tag,
                                                     N6 <: Nat.Base: Tag]
    : Tag.Digit6[N1, N2, N3, N4, N5, N6] =
    Tag.Digit6(implicitly[Tag[N1]],
               implicitly[Tag[N2]],
               implicitly[Tag[N3]],
               implicitly[Tag[N4]],
               implicitly[Tag[N5]],
               implicitly[Tag[N6]])
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 202)
  @alwaysinline implicit def materializeNatDigit7Tag[N1 <: Nat.Base: Tag,
                                                     N2 <: Nat.Base: Tag,
                                                     N3 <: Nat.Base: Tag,
                                                     N4 <: Nat.Base: Tag,
                                                     N5 <: Nat.Base: Tag,
                                                     N6 <: Nat.Base: Tag,
                                                     N7 <: Nat.Base: Tag]
    : Tag.Digit7[N1, N2, N3, N4, N5, N6, N7] =
    Tag.Digit7(implicitly[Tag[N1]],
               implicitly[Tag[N2]],
               implicitly[Tag[N3]],
               implicitly[Tag[N4]],
               implicitly[Tag[N5]],
               implicitly[Tag[N6]],
               implicitly[Tag[N7]])
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 202)
  @alwaysinline implicit def materializeNatDigit8Tag[N1 <: Nat.Base: Tag,
                                                     N2 <: Nat.Base: Tag,
                                                     N3 <: Nat.Base: Tag,
                                                     N4 <: Nat.Base: Tag,
                                                     N5 <: Nat.Base: Tag,
                                                     N6 <: Nat.Base: Tag,
                                                     N7 <: Nat.Base: Tag,
                                                     N8 <: Nat.Base: Tag]
    : Tag.Digit8[N1, N2, N3, N4, N5, N6, N7, N8] =
    Tag.Digit8(
      implicitly[Tag[N1]],
      implicitly[Tag[N2]],
      implicitly[Tag[N3]],
      implicitly[Tag[N4]],
      implicitly[Tag[N5]],
      implicitly[Tag[N6]],
      implicitly[Tag[N7]],
      implicitly[Tag[N8]]
    )
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 202)
  @alwaysinline implicit def materializeNatDigit9Tag[N1 <: Nat.Base: Tag,
                                                     N2 <: Nat.Base: Tag,
                                                     N3 <: Nat.Base: Tag,
                                                     N4 <: Nat.Base: Tag,
                                                     N5 <: Nat.Base: Tag,
                                                     N6 <: Nat.Base: Tag,
                                                     N7 <: Nat.Base: Tag,
                                                     N8 <: Nat.Base: Tag,
                                                     N9 <: Nat.Base: Tag]
    : Tag.Digit9[N1, N2, N3, N4, N5, N6, N7, N8, N9] =
    Tag.Digit9(
      implicitly[Tag[N1]],
      implicitly[Tag[N2]],
      implicitly[Tag[N3]],
      implicitly[Tag[N4]],
      implicitly[Tag[N5]],
      implicitly[Tag[N6]],
      implicitly[Tag[N7]],
      implicitly[Tag[N8]],
      implicitly[Tag[N9]]
    )
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 210)
  @alwaysinline implicit def materializeCStruct0Tag: Tag.CStruct0 =
    Tag.CStruct0()
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 210)
  @alwaysinline implicit def materializeCStruct1Tag[T1: Tag]: Tag.CStruct1[T1] =
    Tag.CStruct1(implicitly[Tag[T1]])
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 210)
  @alwaysinline implicit def materializeCStruct2Tag[T1: Tag, T2: Tag]
    : Tag.CStruct2[T1, T2] =
    Tag.CStruct2(implicitly[Tag[T1]], implicitly[Tag[T2]])
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 210)
  @alwaysinline implicit def materializeCStruct3Tag[T1: Tag, T2: Tag, T3: Tag]
    : Tag.CStruct3[T1, T2, T3] =
    Tag.CStruct3(implicitly[Tag[T1]], implicitly[Tag[T2]], implicitly[Tag[T3]])
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 210)
  @alwaysinline implicit def materializeCStruct4Tag[T1: Tag,
                                                    T2: Tag,
                                                    T3: Tag,
                                                    T4: Tag]
    : Tag.CStruct4[T1, T2, T3, T4] =
    Tag.CStruct4(implicitly[Tag[T1]],
                 implicitly[Tag[T2]],
                 implicitly[Tag[T3]],
                 implicitly[Tag[T4]])
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 210)
  @alwaysinline implicit def materializeCStruct5Tag[T1: Tag,
                                                    T2: Tag,
                                                    T3: Tag,
                                                    T4: Tag,
                                                    T5: Tag]
    : Tag.CStruct5[T1, T2, T3, T4, T5] =
    Tag.CStruct5(implicitly[Tag[T1]],
                 implicitly[Tag[T2]],
                 implicitly[Tag[T3]],
                 implicitly[Tag[T4]],
                 implicitly[Tag[T5]])
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 210)
  @alwaysinline implicit def materializeCStruct6Tag[T1: Tag,
                                                    T2: Tag,
                                                    T3: Tag,
                                                    T4: Tag,
                                                    T5: Tag,
                                                    T6: Tag]
    : Tag.CStruct6[T1, T2, T3, T4, T5, T6] =
    Tag.CStruct6(implicitly[Tag[T1]],
                 implicitly[Tag[T2]],
                 implicitly[Tag[T3]],
                 implicitly[Tag[T4]],
                 implicitly[Tag[T5]],
                 implicitly[Tag[T6]])
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 210)
  @alwaysinline implicit def materializeCStruct7Tag[T1: Tag,
                                                    T2: Tag,
                                                    T3: Tag,
                                                    T4: Tag,
                                                    T5: Tag,
                                                    T6: Tag,
                                                    T7: Tag]
    : Tag.CStruct7[T1, T2, T3, T4, T5, T6, T7] =
    Tag.CStruct7(implicitly[Tag[T1]],
                 implicitly[Tag[T2]],
                 implicitly[Tag[T3]],
                 implicitly[Tag[T4]],
                 implicitly[Tag[T5]],
                 implicitly[Tag[T6]],
                 implicitly[Tag[T7]])
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 210)
  @alwaysinline implicit def materializeCStruct8Tag[T1: Tag,
                                                    T2: Tag,
                                                    T3: Tag,
                                                    T4: Tag,
                                                    T5: Tag,
                                                    T6: Tag,
                                                    T7: Tag,
                                                    T8: Tag]
    : Tag.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8] =
    Tag.CStruct8(
      implicitly[Tag[T1]],
      implicitly[Tag[T2]],
      implicitly[Tag[T3]],
      implicitly[Tag[T4]],
      implicitly[Tag[T5]],
      implicitly[Tag[T6]],
      implicitly[Tag[T7]],
      implicitly[Tag[T8]]
    )
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 210)
  @alwaysinline implicit def materializeCStruct9Tag[T1: Tag,
                                                    T2: Tag,
                                                    T3: Tag,
                                                    T4: Tag,
                                                    T5: Tag,
                                                    T6: Tag,
                                                    T7: Tag,
                                                    T8: Tag,
                                                    T9: Tag]
    : Tag.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9] =
    Tag.CStruct9(
      implicitly[Tag[T1]],
      implicitly[Tag[T2]],
      implicitly[Tag[T3]],
      implicitly[Tag[T4]],
      implicitly[Tag[T5]],
      implicitly[Tag[T6]],
      implicitly[Tag[T7]],
      implicitly[Tag[T8]],
      implicitly[Tag[T9]]
    )
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 210)
  @alwaysinline implicit def materializeCStruct10Tag[T1: Tag,
                                                     T2: Tag,
                                                     T3: Tag,
                                                     T4: Tag,
                                                     T5: Tag,
                                                     T6: Tag,
                                                     T7: Tag,
                                                     T8: Tag,
                                                     T9: Tag,
                                                     T10: Tag]
    : Tag.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10] =
    Tag.CStruct10(
      implicitly[Tag[T1]],
      implicitly[Tag[T2]],
      implicitly[Tag[T3]],
      implicitly[Tag[T4]],
      implicitly[Tag[T5]],
      implicitly[Tag[T6]],
      implicitly[Tag[T7]],
      implicitly[Tag[T8]],
      implicitly[Tag[T9]],
      implicitly[Tag[T10]]
    )
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 210)
  @alwaysinline implicit def materializeCStruct11Tag[T1: Tag,
                                                     T2: Tag,
                                                     T3: Tag,
                                                     T4: Tag,
                                                     T5: Tag,
                                                     T6: Tag,
                                                     T7: Tag,
                                                     T8: Tag,
                                                     T9: Tag,
                                                     T10: Tag,
                                                     T11: Tag]
    : Tag.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11] =
    Tag.CStruct11(
      implicitly[Tag[T1]],
      implicitly[Tag[T2]],
      implicitly[Tag[T3]],
      implicitly[Tag[T4]],
      implicitly[Tag[T5]],
      implicitly[Tag[T6]],
      implicitly[Tag[T7]],
      implicitly[Tag[T8]],
      implicitly[Tag[T9]],
      implicitly[Tag[T10]],
      implicitly[Tag[T11]]
    )
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 210)
  @alwaysinline implicit def materializeCStruct12Tag[T1: Tag,
                                                     T2: Tag,
                                                     T3: Tag,
                                                     T4: Tag,
                                                     T5: Tag,
                                                     T6: Tag,
                                                     T7: Tag,
                                                     T8: Tag,
                                                     T9: Tag,
                                                     T10: Tag,
                                                     T11: Tag,
                                                     T12: Tag]
    : Tag.CStruct12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12] =
    Tag.CStruct12(
      implicitly[Tag[T1]],
      implicitly[Tag[T2]],
      implicitly[Tag[T3]],
      implicitly[Tag[T4]],
      implicitly[Tag[T5]],
      implicitly[Tag[T6]],
      implicitly[Tag[T7]],
      implicitly[Tag[T8]],
      implicitly[Tag[T9]],
      implicitly[Tag[T10]],
      implicitly[Tag[T11]],
      implicitly[Tag[T12]]
    )
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 210)
  @alwaysinline implicit def materializeCStruct13Tag[T1: Tag,
                                                     T2: Tag,
                                                     T3: Tag,
                                                     T4: Tag,
                                                     T5: Tag,
                                                     T6: Tag,
                                                     T7: Tag,
                                                     T8: Tag,
                                                     T9: Tag,
                                                     T10: Tag,
                                                     T11: Tag,
                                                     T12: Tag,
                                                     T13: Tag]
    : Tag.CStruct13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13] =
    Tag.CStruct13(
      implicitly[Tag[T1]],
      implicitly[Tag[T2]],
      implicitly[Tag[T3]],
      implicitly[Tag[T4]],
      implicitly[Tag[T5]],
      implicitly[Tag[T6]],
      implicitly[Tag[T7]],
      implicitly[Tag[T8]],
      implicitly[Tag[T9]],
      implicitly[Tag[T10]],
      implicitly[Tag[T11]],
      implicitly[Tag[T12]],
      implicitly[Tag[T13]]
    )
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 210)
  @alwaysinline implicit def materializeCStruct14Tag[T1: Tag,
                                                     T2: Tag,
                                                     T3: Tag,
                                                     T4: Tag,
                                                     T5: Tag,
                                                     T6: Tag,
                                                     T7: Tag,
                                                     T8: Tag,
                                                     T9: Tag,
                                                     T10: Tag,
                                                     T11: Tag,
                                                     T12: Tag,
                                                     T13: Tag,
                                                     T14: Tag]
    : Tag.CStruct14[T1,
                    T2,
                    T3,
                    T4,
                    T5,
                    T6,
                    T7,
                    T8,
                    T9,
                    T10,
                    T11,
                    T12,
                    T13,
                    T14] =
    Tag.CStruct14(
      implicitly[Tag[T1]],
      implicitly[Tag[T2]],
      implicitly[Tag[T3]],
      implicitly[Tag[T4]],
      implicitly[Tag[T5]],
      implicitly[Tag[T6]],
      implicitly[Tag[T7]],
      implicitly[Tag[T8]],
      implicitly[Tag[T9]],
      implicitly[Tag[T10]],
      implicitly[Tag[T11]],
      implicitly[Tag[T12]],
      implicitly[Tag[T13]],
      implicitly[Tag[T14]]
    )
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 210)
  @alwaysinline implicit def materializeCStruct15Tag[T1: Tag,
                                                     T2: Tag,
                                                     T3: Tag,
                                                     T4: Tag,
                                                     T5: Tag,
                                                     T6: Tag,
                                                     T7: Tag,
                                                     T8: Tag,
                                                     T9: Tag,
                                                     T10: Tag,
                                                     T11: Tag,
                                                     T12: Tag,
                                                     T13: Tag,
                                                     T14: Tag,
                                                     T15: Tag]
    : Tag.CStruct15[T1,
                    T2,
                    T3,
                    T4,
                    T5,
                    T6,
                    T7,
                    T8,
                    T9,
                    T10,
                    T11,
                    T12,
                    T13,
                    T14,
                    T15] =
    Tag.CStruct15(
      implicitly[Tag[T1]],
      implicitly[Tag[T2]],
      implicitly[Tag[T3]],
      implicitly[Tag[T4]],
      implicitly[Tag[T5]],
      implicitly[Tag[T6]],
      implicitly[Tag[T7]],
      implicitly[Tag[T8]],
      implicitly[Tag[T9]],
      implicitly[Tag[T10]],
      implicitly[Tag[T11]],
      implicitly[Tag[T12]],
      implicitly[Tag[T13]],
      implicitly[Tag[T14]],
      implicitly[Tag[T15]]
    )
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 210)
  @alwaysinline implicit def materializeCStruct16Tag[T1: Tag,
                                                     T2: Tag,
                                                     T3: Tag,
                                                     T4: Tag,
                                                     T5: Tag,
                                                     T6: Tag,
                                                     T7: Tag,
                                                     T8: Tag,
                                                     T9: Tag,
                                                     T10: Tag,
                                                     T11: Tag,
                                                     T12: Tag,
                                                     T13: Tag,
                                                     T14: Tag,
                                                     T15: Tag,
                                                     T16: Tag]
    : Tag.CStruct16[T1,
                    T2,
                    T3,
                    T4,
                    T5,
                    T6,
                    T7,
                    T8,
                    T9,
                    T10,
                    T11,
                    T12,
                    T13,
                    T14,
                    T15,
                    T16] =
    Tag.CStruct16(
      implicitly[Tag[T1]],
      implicitly[Tag[T2]],
      implicitly[Tag[T3]],
      implicitly[Tag[T4]],
      implicitly[Tag[T5]],
      implicitly[Tag[T6]],
      implicitly[Tag[T7]],
      implicitly[Tag[T8]],
      implicitly[Tag[T9]],
      implicitly[Tag[T10]],
      implicitly[Tag[T11]],
      implicitly[Tag[T12]],
      implicitly[Tag[T13]],
      implicitly[Tag[T14]],
      implicitly[Tag[T15]],
      implicitly[Tag[T16]]
    )
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 210)
  @alwaysinline implicit def materializeCStruct17Tag[T1: Tag,
                                                     T2: Tag,
                                                     T3: Tag,
                                                     T4: Tag,
                                                     T5: Tag,
                                                     T6: Tag,
                                                     T7: Tag,
                                                     T8: Tag,
                                                     T9: Tag,
                                                     T10: Tag,
                                                     T11: Tag,
                                                     T12: Tag,
                                                     T13: Tag,
                                                     T14: Tag,
                                                     T15: Tag,
                                                     T16: Tag,
                                                     T17: Tag]
    : Tag.CStruct17[T1,
                    T2,
                    T3,
                    T4,
                    T5,
                    T6,
                    T7,
                    T8,
                    T9,
                    T10,
                    T11,
                    T12,
                    T13,
                    T14,
                    T15,
                    T16,
                    T17] =
    Tag.CStruct17(
      implicitly[Tag[T1]],
      implicitly[Tag[T2]],
      implicitly[Tag[T3]],
      implicitly[Tag[T4]],
      implicitly[Tag[T5]],
      implicitly[Tag[T6]],
      implicitly[Tag[T7]],
      implicitly[Tag[T8]],
      implicitly[Tag[T9]],
      implicitly[Tag[T10]],
      implicitly[Tag[T11]],
      implicitly[Tag[T12]],
      implicitly[Tag[T13]],
      implicitly[Tag[T14]],
      implicitly[Tag[T15]],
      implicitly[Tag[T16]],
      implicitly[Tag[T17]]
    )
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 210)
  @alwaysinline implicit def materializeCStruct18Tag[T1: Tag,
                                                     T2: Tag,
                                                     T3: Tag,
                                                     T4: Tag,
                                                     T5: Tag,
                                                     T6: Tag,
                                                     T7: Tag,
                                                     T8: Tag,
                                                     T9: Tag,
                                                     T10: Tag,
                                                     T11: Tag,
                                                     T12: Tag,
                                                     T13: Tag,
                                                     T14: Tag,
                                                     T15: Tag,
                                                     T16: Tag,
                                                     T17: Tag,
                                                     T18: Tag]
    : Tag.CStruct18[T1,
                    T2,
                    T3,
                    T4,
                    T5,
                    T6,
                    T7,
                    T8,
                    T9,
                    T10,
                    T11,
                    T12,
                    T13,
                    T14,
                    T15,
                    T16,
                    T17,
                    T18] =
    Tag.CStruct18(
      implicitly[Tag[T1]],
      implicitly[Tag[T2]],
      implicitly[Tag[T3]],
      implicitly[Tag[T4]],
      implicitly[Tag[T5]],
      implicitly[Tag[T6]],
      implicitly[Tag[T7]],
      implicitly[Tag[T8]],
      implicitly[Tag[T9]],
      implicitly[Tag[T10]],
      implicitly[Tag[T11]],
      implicitly[Tag[T12]],
      implicitly[Tag[T13]],
      implicitly[Tag[T14]],
      implicitly[Tag[T15]],
      implicitly[Tag[T16]],
      implicitly[Tag[T17]],
      implicitly[Tag[T18]]
    )
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 210)
  @alwaysinline implicit def materializeCStruct19Tag[T1: Tag,
                                                     T2: Tag,
                                                     T3: Tag,
                                                     T4: Tag,
                                                     T5: Tag,
                                                     T6: Tag,
                                                     T7: Tag,
                                                     T8: Tag,
                                                     T9: Tag,
                                                     T10: Tag,
                                                     T11: Tag,
                                                     T12: Tag,
                                                     T13: Tag,
                                                     T14: Tag,
                                                     T15: Tag,
                                                     T16: Tag,
                                                     T17: Tag,
                                                     T18: Tag,
                                                     T19: Tag]
    : Tag.CStruct19[T1,
                    T2,
                    T3,
                    T4,
                    T5,
                    T6,
                    T7,
                    T8,
                    T9,
                    T10,
                    T11,
                    T12,
                    T13,
                    T14,
                    T15,
                    T16,
                    T17,
                    T18,
                    T19] =
    Tag.CStruct19(
      implicitly[Tag[T1]],
      implicitly[Tag[T2]],
      implicitly[Tag[T3]],
      implicitly[Tag[T4]],
      implicitly[Tag[T5]],
      implicitly[Tag[T6]],
      implicitly[Tag[T7]],
      implicitly[Tag[T8]],
      implicitly[Tag[T9]],
      implicitly[Tag[T10]],
      implicitly[Tag[T11]],
      implicitly[Tag[T12]],
      implicitly[Tag[T13]],
      implicitly[Tag[T14]],
      implicitly[Tag[T15]],
      implicitly[Tag[T16]],
      implicitly[Tag[T17]],
      implicitly[Tag[T18]],
      implicitly[Tag[T19]]
    )
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 210)
  @alwaysinline implicit def materializeCStruct20Tag[T1: Tag,
                                                     T2: Tag,
                                                     T3: Tag,
                                                     T4: Tag,
                                                     T5: Tag,
                                                     T6: Tag,
                                                     T7: Tag,
                                                     T8: Tag,
                                                     T9: Tag,
                                                     T10: Tag,
                                                     T11: Tag,
                                                     T12: Tag,
                                                     T13: Tag,
                                                     T14: Tag,
                                                     T15: Tag,
                                                     T16: Tag,
                                                     T17: Tag,
                                                     T18: Tag,
                                                     T19: Tag,
                                                     T20: Tag]
    : Tag.CStruct20[T1,
                    T2,
                    T3,
                    T4,
                    T5,
                    T6,
                    T7,
                    T8,
                    T9,
                    T10,
                    T11,
                    T12,
                    T13,
                    T14,
                    T15,
                    T16,
                    T17,
                    T18,
                    T19,
                    T20] =
    Tag.CStruct20(
      implicitly[Tag[T1]],
      implicitly[Tag[T2]],
      implicitly[Tag[T3]],
      implicitly[Tag[T4]],
      implicitly[Tag[T5]],
      implicitly[Tag[T6]],
      implicitly[Tag[T7]],
      implicitly[Tag[T8]],
      implicitly[Tag[T9]],
      implicitly[Tag[T10]],
      implicitly[Tag[T11]],
      implicitly[Tag[T12]],
      implicitly[Tag[T13]],
      implicitly[Tag[T14]],
      implicitly[Tag[T15]],
      implicitly[Tag[T16]],
      implicitly[Tag[T17]],
      implicitly[Tag[T18]],
      implicitly[Tag[T19]],
      implicitly[Tag[T20]]
    )
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 210)
  @alwaysinline implicit def materializeCStruct21Tag[T1: Tag,
                                                     T2: Tag,
                                                     T3: Tag,
                                                     T4: Tag,
                                                     T5: Tag,
                                                     T6: Tag,
                                                     T7: Tag,
                                                     T8: Tag,
                                                     T9: Tag,
                                                     T10: Tag,
                                                     T11: Tag,
                                                     T12: Tag,
                                                     T13: Tag,
                                                     T14: Tag,
                                                     T15: Tag,
                                                     T16: Tag,
                                                     T17: Tag,
                                                     T18: Tag,
                                                     T19: Tag,
                                                     T20: Tag,
                                                     T21: Tag]
    : Tag.CStruct21[T1,
                    T2,
                    T3,
                    T4,
                    T5,
                    T6,
                    T7,
                    T8,
                    T9,
                    T10,
                    T11,
                    T12,
                    T13,
                    T14,
                    T15,
                    T16,
                    T17,
                    T18,
                    T19,
                    T20,
                    T21] =
    Tag.CStruct21(
      implicitly[Tag[T1]],
      implicitly[Tag[T2]],
      implicitly[Tag[T3]],
      implicitly[Tag[T4]],
      implicitly[Tag[T5]],
      implicitly[Tag[T6]],
      implicitly[Tag[T7]],
      implicitly[Tag[T8]],
      implicitly[Tag[T9]],
      implicitly[Tag[T10]],
      implicitly[Tag[T11]],
      implicitly[Tag[T12]],
      implicitly[Tag[T13]],
      implicitly[Tag[T14]],
      implicitly[Tag[T15]],
      implicitly[Tag[T16]],
      implicitly[Tag[T17]],
      implicitly[Tag[T18]],
      implicitly[Tag[T19]],
      implicitly[Tag[T20]],
      implicitly[Tag[T21]]
    )
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 210)
  @alwaysinline implicit def materializeCStruct22Tag[T1: Tag,
                                                     T2: Tag,
                                                     T3: Tag,
                                                     T4: Tag,
                                                     T5: Tag,
                                                     T6: Tag,
                                                     T7: Tag,
                                                     T8: Tag,
                                                     T9: Tag,
                                                     T10: Tag,
                                                     T11: Tag,
                                                     T12: Tag,
                                                     T13: Tag,
                                                     T14: Tag,
                                                     T15: Tag,
                                                     T16: Tag,
                                                     T17: Tag,
                                                     T18: Tag,
                                                     T19: Tag,
                                                     T20: Tag,
                                                     T21: Tag,
                                                     T22: Tag]
    : Tag.CStruct22[T1,
                    T2,
                    T3,
                    T4,
                    T5,
                    T6,
                    T7,
                    T8,
                    T9,
                    T10,
                    T11,
                    T12,
                    T13,
                    T14,
                    T15,
                    T16,
                    T17,
                    T18,
                    T19,
                    T20,
                    T21,
                    T22] =
    Tag.CStruct22(
      implicitly[Tag[T1]],
      implicitly[Tag[T2]],
      implicitly[Tag[T3]],
      implicitly[Tag[T4]],
      implicitly[Tag[T5]],
      implicitly[Tag[T6]],
      implicitly[Tag[T7]],
      implicitly[Tag[T8]],
      implicitly[Tag[T9]],
      implicitly[Tag[T10]],
      implicitly[Tag[T11]],
      implicitly[Tag[T12]],
      implicitly[Tag[T13]],
      implicitly[Tag[T14]],
      implicitly[Tag[T15]],
      implicitly[Tag[T16]],
      implicitly[Tag[T17]],
      implicitly[Tag[T18]],
      implicitly[Tag[T19]],
      implicitly[Tag[T20]],
      implicitly[Tag[T21]],
      implicitly[Tag[T22]]
    )
// ###sourceLocation(file: "/Users/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Tag.scala.gyb", line: 213)
  @alwaysinline implicit def materializeCArrayTag[T: Tag, N <: native.Nat: Tag]
    : Tag.CArray[T, N] =
    Tag.CArray(implicitly[Tag[T]], implicitly[Tag[N]])
}
