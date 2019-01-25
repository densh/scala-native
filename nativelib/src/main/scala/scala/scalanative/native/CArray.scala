// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 1)
package scala.scalanative
package native

import scalanative.runtime._

final class CArray[T, N <: Nat](private[scalanative] val rawptr: RawPtr) {

  @inline def at(idx: Int)(implicit tag: Tag.CArray[T, N]): Ptr[T] =
    fromRawPtr[T](rawptr).+(idx)(tag.of)

  @inline def apply(idx: Int)(implicit tag: Tag.CArray[T, N]): T =
    fromRawPtr[T](rawptr).apply(idx)(tag.of)

  @inline def update(idx: Int, value: T)(implicit tag: Tag.CArray[T, N]): Unit =
    fromRawPtr[T](rawptr).update(idx, value)(tag.of)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 18)

  def _1(implicit tag: Tag.CArray[T, N]): T =
    fromRawPtr[T](rawptr).apply(0)(tag.of)

  def _1_=(value: T)(implicit tag: Tag.CArray[T, N]): Unit =
    fromRawPtr[T](rawptr).apply(0)(tag.of)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 18)

  def _2(implicit tag: Tag.CArray[T, N]): T =
    fromRawPtr[T](rawptr).apply(1)(tag.of)

  def _2_=(value: T)(implicit tag: Tag.CArray[T, N]): Unit =
    fromRawPtr[T](rawptr).apply(1)(tag.of)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 18)

  def _3(implicit tag: Tag.CArray[T, N]): T =
    fromRawPtr[T](rawptr).apply(2)(tag.of)

  def _3_=(value: T)(implicit tag: Tag.CArray[T, N]): Unit =
    fromRawPtr[T](rawptr).apply(2)(tag.of)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 18)

  def _4(implicit tag: Tag.CArray[T, N]): T =
    fromRawPtr[T](rawptr).apply(3)(tag.of)

  def _4_=(value: T)(implicit tag: Tag.CArray[T, N]): Unit =
    fromRawPtr[T](rawptr).apply(3)(tag.of)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 18)

  def _5(implicit tag: Tag.CArray[T, N]): T =
    fromRawPtr[T](rawptr).apply(4)(tag.of)

  def _5_=(value: T)(implicit tag: Tag.CArray[T, N]): Unit =
    fromRawPtr[T](rawptr).apply(4)(tag.of)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 18)

  def _6(implicit tag: Tag.CArray[T, N]): T =
    fromRawPtr[T](rawptr).apply(5)(tag.of)

  def _6_=(value: T)(implicit tag: Tag.CArray[T, N]): Unit =
    fromRawPtr[T](rawptr).apply(5)(tag.of)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 18)

  def _7(implicit tag: Tag.CArray[T, N]): T =
    fromRawPtr[T](rawptr).apply(6)(tag.of)

  def _7_=(value: T)(implicit tag: Tag.CArray[T, N]): Unit =
    fromRawPtr[T](rawptr).apply(6)(tag.of)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 18)

  def _8(implicit tag: Tag.CArray[T, N]): T =
    fromRawPtr[T](rawptr).apply(7)(tag.of)

  def _8_=(value: T)(implicit tag: Tag.CArray[T, N]): Unit =
    fromRawPtr[T](rawptr).apply(7)(tag.of)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 18)

  def _9(implicit tag: Tag.CArray[T, N]): T =
    fromRawPtr[T](rawptr).apply(8)(tag.of)

  def _9_=(value: T)(implicit tag: Tag.CArray[T, N]): Unit =
    fromRawPtr[T](rawptr).apply(8)(tag.of)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 18)

  def _10(implicit tag: Tag.CArray[T, N]): T =
    fromRawPtr[T](rawptr).apply(9)(tag.of)

  def _10_=(value: T)(implicit tag: Tag.CArray[T, N]): Unit =
    fromRawPtr[T](rawptr).apply(9)(tag.of)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 18)

  def _11(implicit tag: Tag.CArray[T, N]): T =
    fromRawPtr[T](rawptr).apply(10)(tag.of)

  def _11_=(value: T)(implicit tag: Tag.CArray[T, N]): Unit =
    fromRawPtr[T](rawptr).apply(10)(tag.of)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 18)

  def _12(implicit tag: Tag.CArray[T, N]): T =
    fromRawPtr[T](rawptr).apply(11)(tag.of)

  def _12_=(value: T)(implicit tag: Tag.CArray[T, N]): Unit =
    fromRawPtr[T](rawptr).apply(11)(tag.of)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 18)

  def _13(implicit tag: Tag.CArray[T, N]): T =
    fromRawPtr[T](rawptr).apply(12)(tag.of)

  def _13_=(value: T)(implicit tag: Tag.CArray[T, N]): Unit =
    fromRawPtr[T](rawptr).apply(12)(tag.of)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 18)

  def _14(implicit tag: Tag.CArray[T, N]): T =
    fromRawPtr[T](rawptr).apply(13)(tag.of)

  def _14_=(value: T)(implicit tag: Tag.CArray[T, N]): Unit =
    fromRawPtr[T](rawptr).apply(13)(tag.of)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 18)

  def _15(implicit tag: Tag.CArray[T, N]): T =
    fromRawPtr[T](rawptr).apply(14)(tag.of)

  def _15_=(value: T)(implicit tag: Tag.CArray[T, N]): Unit =
    fromRawPtr[T](rawptr).apply(14)(tag.of)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 18)

  def _16(implicit tag: Tag.CArray[T, N]): T =
    fromRawPtr[T](rawptr).apply(15)(tag.of)

  def _16_=(value: T)(implicit tag: Tag.CArray[T, N]): Unit =
    fromRawPtr[T](rawptr).apply(15)(tag.of)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 18)

  def _17(implicit tag: Tag.CArray[T, N]): T =
    fromRawPtr[T](rawptr).apply(16)(tag.of)

  def _17_=(value: T)(implicit tag: Tag.CArray[T, N]): Unit =
    fromRawPtr[T](rawptr).apply(16)(tag.of)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 18)

  def _18(implicit tag: Tag.CArray[T, N]): T =
    fromRawPtr[T](rawptr).apply(17)(tag.of)

  def _18_=(value: T)(implicit tag: Tag.CArray[T, N]): Unit =
    fromRawPtr[T](rawptr).apply(17)(tag.of)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 18)

  def _19(implicit tag: Tag.CArray[T, N]): T =
    fromRawPtr[T](rawptr).apply(18)(tag.of)

  def _19_=(value: T)(implicit tag: Tag.CArray[T, N]): Unit =
    fromRawPtr[T](rawptr).apply(18)(tag.of)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 18)

  def _20(implicit tag: Tag.CArray[T, N]): T =
    fromRawPtr[T](rawptr).apply(19)(tag.of)

  def _20_=(value: T)(implicit tag: Tag.CArray[T, N]): Unit =
    fromRawPtr[T](rawptr).apply(19)(tag.of)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 18)

  def _21(implicit tag: Tag.CArray[T, N]): T =
    fromRawPtr[T](rawptr).apply(20)(tag.of)

  def _21_=(value: T)(implicit tag: Tag.CArray[T, N]): Unit =
    fromRawPtr[T](rawptr).apply(20)(tag.of)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 18)

  def _22(implicit tag: Tag.CArray[T, N]): T =
    fromRawPtr[T](rawptr).apply(21)(tag.of)

  def _22_=(value: T)(implicit tag: Tag.CArray[T, N]): Unit =
    fromRawPtr[T](rawptr).apply(21)(tag.of)

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CArray.scala.gyb", line: 26)
}
