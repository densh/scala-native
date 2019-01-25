// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 1)
package scala.scalanative
package native

import scalanative.runtime._
import scalanative.runtime.Intrinsics._

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 10)

final class CStruct0(private[scalanative] val rawptr: RawPtr) {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 29)
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 10)

final class CStruct1[T1](private[scalanative] val rawptr: RawPtr) {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _1(implicit tag: Tag.CStruct1[T1]): T1 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.unary_!(tag._1)
  }

  def _1_=(value: T1)(implicit tag: Tag.CStruct1[T1]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._1)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 29)
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 10)

final class CStruct2[T1, T2](private[scalanative] val rawptr: RawPtr) {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _1(implicit tag: Tag.CStruct2[T1, T2]): T1 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.unary_!(tag._1)
  }

  def _1_=(value: T1)(implicit tag: Tag.CStruct2[T1, T2]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._1)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _2(implicit tag: Tag.CStruct2[T1, T2]): T2 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.unary_!(tag._2)
  }

  def _2_=(value: T2)(implicit tag: Tag.CStruct2[T1, T2]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._2)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 29)
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 10)

final class CStruct3[T1, T2, T3](private[scalanative] val rawptr: RawPtr) {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _1(implicit tag: Tag.CStruct3[T1, T2, T3]): T1 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.unary_!(tag._1)
  }

  def _1_=(value: T1)(implicit tag: Tag.CStruct3[T1, T2, T3]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._1)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _2(implicit tag: Tag.CStruct3[T1, T2, T3]): T2 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.unary_!(tag._2)
  }

  def _2_=(value: T2)(implicit tag: Tag.CStruct3[T1, T2, T3]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._2)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _3(implicit tag: Tag.CStruct3[T1, T2, T3]): T3 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.unary_!(tag._3)
  }

  def _3_=(value: T3)(implicit tag: Tag.CStruct3[T1, T2, T3]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._3)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 29)
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 10)

final class CStruct4[T1, T2, T3, T4](private[scalanative] val rawptr: RawPtr) {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _1(implicit tag: Tag.CStruct4[T1, T2, T3, T4]): T1 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.unary_!(tag._1)
  }

  def _1_=(value: T1)(implicit tag: Tag.CStruct4[T1, T2, T3, T4]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._1)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _2(implicit tag: Tag.CStruct4[T1, T2, T3, T4]): T2 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.unary_!(tag._2)
  }

  def _2_=(value: T2)(implicit tag: Tag.CStruct4[T1, T2, T3, T4]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._2)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _3(implicit tag: Tag.CStruct4[T1, T2, T3, T4]): T3 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.unary_!(tag._3)
  }

  def _3_=(value: T3)(implicit tag: Tag.CStruct4[T1, T2, T3, T4]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._3)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _4(implicit tag: Tag.CStruct4[T1, T2, T3, T4]): T4 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.unary_!(tag._4)
  }

  def _4_=(value: T4)(implicit tag: Tag.CStruct4[T1, T2, T3, T4]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._4)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 29)
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 10)

final class CStruct5[T1, T2, T3, T4, T5](
    private[scalanative] val rawptr: RawPtr) {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _1(implicit tag: Tag.CStruct5[T1, T2, T3, T4, T5]): T1 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.unary_!(tag._1)
  }

  def _1_=(value: T1)(implicit tag: Tag.CStruct5[T1, T2, T3, T4, T5]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._1)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _2(implicit tag: Tag.CStruct5[T1, T2, T3, T4, T5]): T2 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.unary_!(tag._2)
  }

  def _2_=(value: T2)(implicit tag: Tag.CStruct5[T1, T2, T3, T4, T5]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._2)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _3(implicit tag: Tag.CStruct5[T1, T2, T3, T4, T5]): T3 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.unary_!(tag._3)
  }

  def _3_=(value: T3)(implicit tag: Tag.CStruct5[T1, T2, T3, T4, T5]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._3)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _4(implicit tag: Tag.CStruct5[T1, T2, T3, T4, T5]): T4 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.unary_!(tag._4)
  }

  def _4_=(value: T4)(implicit tag: Tag.CStruct5[T1, T2, T3, T4, T5]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._4)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _5(implicit tag: Tag.CStruct5[T1, T2, T3, T4, T5]): T5 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.unary_!(tag._5)
  }

  def _5_=(value: T5)(implicit tag: Tag.CStruct5[T1, T2, T3, T4, T5]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._5)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 29)
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 10)

final class CStruct6[T1, T2, T3, T4, T5, T6](
    private[scalanative] val rawptr: RawPtr) {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _1(implicit tag: Tag.CStruct6[T1, T2, T3, T4, T5, T6]): T1 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.unary_!(tag._1)
  }

  def _1_=(value: T1)(
      implicit tag: Tag.CStruct6[T1, T2, T3, T4, T5, T6]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._1)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _2(implicit tag: Tag.CStruct6[T1, T2, T3, T4, T5, T6]): T2 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.unary_!(tag._2)
  }

  def _2_=(value: T2)(
      implicit tag: Tag.CStruct6[T1, T2, T3, T4, T5, T6]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._2)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _3(implicit tag: Tag.CStruct6[T1, T2, T3, T4, T5, T6]): T3 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.unary_!(tag._3)
  }

  def _3_=(value: T3)(
      implicit tag: Tag.CStruct6[T1, T2, T3, T4, T5, T6]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._3)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _4(implicit tag: Tag.CStruct6[T1, T2, T3, T4, T5, T6]): T4 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.unary_!(tag._4)
  }

  def _4_=(value: T4)(
      implicit tag: Tag.CStruct6[T1, T2, T3, T4, T5, T6]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._4)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _5(implicit tag: Tag.CStruct6[T1, T2, T3, T4, T5, T6]): T5 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.unary_!(tag._5)
  }

  def _5_=(value: T5)(
      implicit tag: Tag.CStruct6[T1, T2, T3, T4, T5, T6]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._5)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _6(implicit tag: Tag.CStruct6[T1, T2, T3, T4, T5, T6]): T6 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.unary_!(tag._6)
  }

  def _6_=(value: T6)(
      implicit tag: Tag.CStruct6[T1, T2, T3, T4, T5, T6]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._6)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 29)
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 10)

final class CStruct7[T1, T2, T3, T4, T5, T6, T7](
    private[scalanative] val rawptr: RawPtr) {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _1(implicit tag: Tag.CStruct7[T1, T2, T3, T4, T5, T6, T7]): T1 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.unary_!(tag._1)
  }

  def _1_=(value: T1)(
      implicit tag: Tag.CStruct7[T1, T2, T3, T4, T5, T6, T7]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._1)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _2(implicit tag: Tag.CStruct7[T1, T2, T3, T4, T5, T6, T7]): T2 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.unary_!(tag._2)
  }

  def _2_=(value: T2)(
      implicit tag: Tag.CStruct7[T1, T2, T3, T4, T5, T6, T7]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._2)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _3(implicit tag: Tag.CStruct7[T1, T2, T3, T4, T5, T6, T7]): T3 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.unary_!(tag._3)
  }

  def _3_=(value: T3)(
      implicit tag: Tag.CStruct7[T1, T2, T3, T4, T5, T6, T7]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._3)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _4(implicit tag: Tag.CStruct7[T1, T2, T3, T4, T5, T6, T7]): T4 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.unary_!(tag._4)
  }

  def _4_=(value: T4)(
      implicit tag: Tag.CStruct7[T1, T2, T3, T4, T5, T6, T7]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._4)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _5(implicit tag: Tag.CStruct7[T1, T2, T3, T4, T5, T6, T7]): T5 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.unary_!(tag._5)
  }

  def _5_=(value: T5)(
      implicit tag: Tag.CStruct7[T1, T2, T3, T4, T5, T6, T7]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._5)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _6(implicit tag: Tag.CStruct7[T1, T2, T3, T4, T5, T6, T7]): T6 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.unary_!(tag._6)
  }

  def _6_=(value: T6)(
      implicit tag: Tag.CStruct7[T1, T2, T3, T4, T5, T6, T7]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._6)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _7(implicit tag: Tag.CStruct7[T1, T2, T3, T4, T5, T6, T7]): T7 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.unary_!(tag._7)
  }

  def _7_=(value: T7)(
      implicit tag: Tag.CStruct7[T1, T2, T3, T4, T5, T6, T7]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._7)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 29)
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 10)

final class CStruct8[T1, T2, T3, T4, T5, T6, T7, T8](
    private[scalanative] val rawptr: RawPtr) {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _1(implicit tag: Tag.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8]): T1 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.unary_!(tag._1)
  }

  def _1_=(value: T1)(
      implicit tag: Tag.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._1)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _2(implicit tag: Tag.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8]): T2 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.unary_!(tag._2)
  }

  def _2_=(value: T2)(
      implicit tag: Tag.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._2)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _3(implicit tag: Tag.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8]): T3 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.unary_!(tag._3)
  }

  def _3_=(value: T3)(
      implicit tag: Tag.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._3)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _4(implicit tag: Tag.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8]): T4 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.unary_!(tag._4)
  }

  def _4_=(value: T4)(
      implicit tag: Tag.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._4)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _5(implicit tag: Tag.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8]): T5 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.unary_!(tag._5)
  }

  def _5_=(value: T5)(
      implicit tag: Tag.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._5)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _6(implicit tag: Tag.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8]): T6 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.unary_!(tag._6)
  }

  def _6_=(value: T6)(
      implicit tag: Tag.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._6)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _7(implicit tag: Tag.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8]): T7 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.unary_!(tag._7)
  }

  def _7_=(value: T7)(
      implicit tag: Tag.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._7)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _8(implicit tag: Tag.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8]): T8 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.unary_!(tag._8)
  }

  def _8_=(value: T8)(
      implicit tag: Tag.CStruct8[T1, T2, T3, T4, T5, T6, T7, T8]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._8)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 29)
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 10)

final class CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9](
    private[scalanative] val rawptr: RawPtr) {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _1(implicit tag: Tag.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9]): T1 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.unary_!(tag._1)
  }

  def _1_=(value: T1)(
      implicit tag: Tag.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._1)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _2(implicit tag: Tag.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9]): T2 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.unary_!(tag._2)
  }

  def _2_=(value: T2)(
      implicit tag: Tag.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._2)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _3(implicit tag: Tag.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9]): T3 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.unary_!(tag._3)
  }

  def _3_=(value: T3)(
      implicit tag: Tag.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._3)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _4(implicit tag: Tag.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9]): T4 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.unary_!(tag._4)
  }

  def _4_=(value: T4)(
      implicit tag: Tag.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._4)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _5(implicit tag: Tag.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9]): T5 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.unary_!(tag._5)
  }

  def _5_=(value: T5)(
      implicit tag: Tag.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._5)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _6(implicit tag: Tag.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9]): T6 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.unary_!(tag._6)
  }

  def _6_=(value: T6)(
      implicit tag: Tag.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._6)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _7(implicit tag: Tag.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9]): T7 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.unary_!(tag._7)
  }

  def _7_=(value: T7)(
      implicit tag: Tag.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._7)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _8(implicit tag: Tag.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9]): T8 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.unary_!(tag._8)
  }

  def _8_=(value: T8)(
      implicit tag: Tag.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._8)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _9(implicit tag: Tag.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9]): T9 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.unary_!(tag._9)
  }

  def _9_=(value: T9)(
      implicit tag: Tag.CStruct9[T1, T2, T3, T4, T5, T6, T7, T8, T9]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._9)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 29)
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 10)

final class CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](
    private[scalanative] val rawptr: RawPtr) {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _1(implicit tag: Tag.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10])
    : T1 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.unary_!(tag._1)
  }

  def _1_=(value: T1)(
      implicit tag: Tag.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10])
    : Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._1)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _2(implicit tag: Tag.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10])
    : T2 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.unary_!(tag._2)
  }

  def _2_=(value: T2)(
      implicit tag: Tag.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10])
    : Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._2)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _3(implicit tag: Tag.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10])
    : T3 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.unary_!(tag._3)
  }

  def _3_=(value: T3)(
      implicit tag: Tag.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10])
    : Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._3)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _4(implicit tag: Tag.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10])
    : T4 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.unary_!(tag._4)
  }

  def _4_=(value: T4)(
      implicit tag: Tag.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10])
    : Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._4)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _5(implicit tag: Tag.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10])
    : T5 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.unary_!(tag._5)
  }

  def _5_=(value: T5)(
      implicit tag: Tag.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10])
    : Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._5)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _6(implicit tag: Tag.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10])
    : T6 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.unary_!(tag._6)
  }

  def _6_=(value: T6)(
      implicit tag: Tag.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10])
    : Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._6)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _7(implicit tag: Tag.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10])
    : T7 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.unary_!(tag._7)
  }

  def _7_=(value: T7)(
      implicit tag: Tag.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10])
    : Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._7)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _8(implicit tag: Tag.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10])
    : T8 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.unary_!(tag._8)
  }

  def _8_=(value: T8)(
      implicit tag: Tag.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10])
    : Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._8)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _9(implicit tag: Tag.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10])
    : T9 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.unary_!(tag._9)
  }

  def _9_=(value: T9)(
      implicit tag: Tag.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10])
    : Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._9)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _10(implicit tag: Tag.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10])
    : T10 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.unary_!(tag._10)
  }

  def _10_=(value: T10)(
      implicit tag: Tag.CStruct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10])
    : Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._10)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 29)
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 10)

final class CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](
    private[scalanative] val rawptr: RawPtr) {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _1(
      implicit tag: Tag.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11])
    : T1 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.unary_!(tag._1)
  }

  def _1_=(value: T1)(
      implicit tag: Tag.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11])
    : Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._1)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _2(
      implicit tag: Tag.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11])
    : T2 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.unary_!(tag._2)
  }

  def _2_=(value: T2)(
      implicit tag: Tag.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11])
    : Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._2)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _3(
      implicit tag: Tag.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11])
    : T3 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.unary_!(tag._3)
  }

  def _3_=(value: T3)(
      implicit tag: Tag.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11])
    : Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._3)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _4(
      implicit tag: Tag.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11])
    : T4 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.unary_!(tag._4)
  }

  def _4_=(value: T4)(
      implicit tag: Tag.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11])
    : Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._4)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _5(
      implicit tag: Tag.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11])
    : T5 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.unary_!(tag._5)
  }

  def _5_=(value: T5)(
      implicit tag: Tag.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11])
    : Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._5)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _6(
      implicit tag: Tag.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11])
    : T6 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.unary_!(tag._6)
  }

  def _6_=(value: T6)(
      implicit tag: Tag.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11])
    : Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._6)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _7(
      implicit tag: Tag.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11])
    : T7 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.unary_!(tag._7)
  }

  def _7_=(value: T7)(
      implicit tag: Tag.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11])
    : Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._7)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _8(
      implicit tag: Tag.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11])
    : T8 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.unary_!(tag._8)
  }

  def _8_=(value: T8)(
      implicit tag: Tag.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11])
    : Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._8)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _9(
      implicit tag: Tag.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11])
    : T9 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.unary_!(tag._9)
  }

  def _9_=(value: T9)(
      implicit tag: Tag.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11])
    : Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._9)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _10(
      implicit tag: Tag.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11])
    : T10 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.unary_!(tag._10)
  }

  def _10_=(value: T10)(
      implicit tag: Tag.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11])
    : Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._10)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _11(
      implicit tag: Tag.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11])
    : T11 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.unary_!(tag._11)
  }

  def _11_=(value: T11)(
      implicit tag: Tag.CStruct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11])
    : Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._11)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 29)
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 10)

final class CStruct12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](
    private[scalanative] val rawptr: RawPtr) {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _1(
      implicit tag: Tag.CStruct12[T1,
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
                                  T12]): T1 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.unary_!(tag._1)
  }

  def _1_=(value: T1)(implicit tag: Tag.CStruct12[T1,
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
                                                  T12]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._1)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _2(
      implicit tag: Tag.CStruct12[T1,
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
                                  T12]): T2 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.unary_!(tag._2)
  }

  def _2_=(value: T2)(implicit tag: Tag.CStruct12[T1,
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
                                                  T12]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._2)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _3(
      implicit tag: Tag.CStruct12[T1,
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
                                  T12]): T3 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.unary_!(tag._3)
  }

  def _3_=(value: T3)(implicit tag: Tag.CStruct12[T1,
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
                                                  T12]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._3)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _4(
      implicit tag: Tag.CStruct12[T1,
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
                                  T12]): T4 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.unary_!(tag._4)
  }

  def _4_=(value: T4)(implicit tag: Tag.CStruct12[T1,
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
                                                  T12]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._4)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _5(
      implicit tag: Tag.CStruct12[T1,
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
                                  T12]): T5 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.unary_!(tag._5)
  }

  def _5_=(value: T5)(implicit tag: Tag.CStruct12[T1,
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
                                                  T12]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._5)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _6(
      implicit tag: Tag.CStruct12[T1,
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
                                  T12]): T6 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.unary_!(tag._6)
  }

  def _6_=(value: T6)(implicit tag: Tag.CStruct12[T1,
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
                                                  T12]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._6)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _7(
      implicit tag: Tag.CStruct12[T1,
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
                                  T12]): T7 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.unary_!(tag._7)
  }

  def _7_=(value: T7)(implicit tag: Tag.CStruct12[T1,
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
                                                  T12]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._7)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _8(
      implicit tag: Tag.CStruct12[T1,
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
                                  T12]): T8 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.unary_!(tag._8)
  }

  def _8_=(value: T8)(implicit tag: Tag.CStruct12[T1,
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
                                                  T12]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._8)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _9(
      implicit tag: Tag.CStruct12[T1,
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
                                  T12]): T9 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.unary_!(tag._9)
  }

  def _9_=(value: T9)(implicit tag: Tag.CStruct12[T1,
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
                                                  T12]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._9)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _10(
      implicit tag: Tag.CStruct12[T1,
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
                                  T12]): T10 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.unary_!(tag._10)
  }

  def _10_=(value: T10)(implicit tag: Tag.CStruct12[T1,
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
                                                    T12]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._10)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _11(
      implicit tag: Tag.CStruct12[T1,
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
                                  T12]): T11 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.unary_!(tag._11)
  }

  def _11_=(value: T11)(implicit tag: Tag.CStruct12[T1,
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
                                                    T12]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._11)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _12(
      implicit tag: Tag.CStruct12[T1,
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
                                  T12]): T12 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset12)
    val fldptr    = fromRawPtr[T12](fldrawptr)
    fldptr.unary_!(tag._12)
  }

  def _12_=(value: T12)(implicit tag: Tag.CStruct12[T1,
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
                                                    T12]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset12)
    val fldptr    = fromRawPtr[T12](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._12)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 29)
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 10)

final class CStruct13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](
    private[scalanative] val rawptr: RawPtr) {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _1(
      implicit tag: Tag.CStruct13[T1,
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
                                  T13]): T1 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.unary_!(tag._1)
  }

  def _1_=(value: T1)(implicit tag: Tag.CStruct13[T1,
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
                                                  T13]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._1)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _2(
      implicit tag: Tag.CStruct13[T1,
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
                                  T13]): T2 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.unary_!(tag._2)
  }

  def _2_=(value: T2)(implicit tag: Tag.CStruct13[T1,
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
                                                  T13]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._2)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _3(
      implicit tag: Tag.CStruct13[T1,
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
                                  T13]): T3 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.unary_!(tag._3)
  }

  def _3_=(value: T3)(implicit tag: Tag.CStruct13[T1,
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
                                                  T13]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._3)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _4(
      implicit tag: Tag.CStruct13[T1,
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
                                  T13]): T4 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.unary_!(tag._4)
  }

  def _4_=(value: T4)(implicit tag: Tag.CStruct13[T1,
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
                                                  T13]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._4)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _5(
      implicit tag: Tag.CStruct13[T1,
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
                                  T13]): T5 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.unary_!(tag._5)
  }

  def _5_=(value: T5)(implicit tag: Tag.CStruct13[T1,
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
                                                  T13]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._5)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _6(
      implicit tag: Tag.CStruct13[T1,
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
                                  T13]): T6 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.unary_!(tag._6)
  }

  def _6_=(value: T6)(implicit tag: Tag.CStruct13[T1,
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
                                                  T13]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._6)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _7(
      implicit tag: Tag.CStruct13[T1,
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
                                  T13]): T7 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.unary_!(tag._7)
  }

  def _7_=(value: T7)(implicit tag: Tag.CStruct13[T1,
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
                                                  T13]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._7)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _8(
      implicit tag: Tag.CStruct13[T1,
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
                                  T13]): T8 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.unary_!(tag._8)
  }

  def _8_=(value: T8)(implicit tag: Tag.CStruct13[T1,
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
                                                  T13]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._8)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _9(
      implicit tag: Tag.CStruct13[T1,
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
                                  T13]): T9 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.unary_!(tag._9)
  }

  def _9_=(value: T9)(implicit tag: Tag.CStruct13[T1,
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
                                                  T13]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._9)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _10(
      implicit tag: Tag.CStruct13[T1,
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
                                  T13]): T10 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.unary_!(tag._10)
  }

  def _10_=(value: T10)(implicit tag: Tag.CStruct13[T1,
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
                                                    T13]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._10)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _11(
      implicit tag: Tag.CStruct13[T1,
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
                                  T13]): T11 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.unary_!(tag._11)
  }

  def _11_=(value: T11)(implicit tag: Tag.CStruct13[T1,
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
                                                    T13]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._11)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _12(
      implicit tag: Tag.CStruct13[T1,
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
                                  T13]): T12 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset12)
    val fldptr    = fromRawPtr[T12](fldrawptr)
    fldptr.unary_!(tag._12)
  }

  def _12_=(value: T12)(implicit tag: Tag.CStruct13[T1,
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
                                                    T13]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset12)
    val fldptr    = fromRawPtr[T12](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._12)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _13(
      implicit tag: Tag.CStruct13[T1,
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
                                  T13]): T13 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset13)
    val fldptr    = fromRawPtr[T13](fldrawptr)
    fldptr.unary_!(tag._13)
  }

  def _13_=(value: T13)(implicit tag: Tag.CStruct13[T1,
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
                                                    T13]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset13)
    val fldptr    = fromRawPtr[T13](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._13)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 29)
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 10)

final class CStruct14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13,
T14](private[scalanative] val rawptr: RawPtr) {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _1(
      implicit tag: Tag.CStruct14[T1,
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
                                  T14]): T1 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.unary_!(tag._1)
  }

  def _1_=(value: T1)(implicit tag: Tag.CStruct14[T1,
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
                                                  T14]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._1)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _2(
      implicit tag: Tag.CStruct14[T1,
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
                                  T14]): T2 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.unary_!(tag._2)
  }

  def _2_=(value: T2)(implicit tag: Tag.CStruct14[T1,
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
                                                  T14]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._2)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _3(
      implicit tag: Tag.CStruct14[T1,
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
                                  T14]): T3 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.unary_!(tag._3)
  }

  def _3_=(value: T3)(implicit tag: Tag.CStruct14[T1,
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
                                                  T14]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._3)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _4(
      implicit tag: Tag.CStruct14[T1,
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
                                  T14]): T4 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.unary_!(tag._4)
  }

  def _4_=(value: T4)(implicit tag: Tag.CStruct14[T1,
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
                                                  T14]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._4)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _5(
      implicit tag: Tag.CStruct14[T1,
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
                                  T14]): T5 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.unary_!(tag._5)
  }

  def _5_=(value: T5)(implicit tag: Tag.CStruct14[T1,
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
                                                  T14]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._5)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _6(
      implicit tag: Tag.CStruct14[T1,
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
                                  T14]): T6 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.unary_!(tag._6)
  }

  def _6_=(value: T6)(implicit tag: Tag.CStruct14[T1,
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
                                                  T14]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._6)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _7(
      implicit tag: Tag.CStruct14[T1,
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
                                  T14]): T7 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.unary_!(tag._7)
  }

  def _7_=(value: T7)(implicit tag: Tag.CStruct14[T1,
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
                                                  T14]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._7)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _8(
      implicit tag: Tag.CStruct14[T1,
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
                                  T14]): T8 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.unary_!(tag._8)
  }

  def _8_=(value: T8)(implicit tag: Tag.CStruct14[T1,
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
                                                  T14]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._8)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _9(
      implicit tag: Tag.CStruct14[T1,
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
                                  T14]): T9 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.unary_!(tag._9)
  }

  def _9_=(value: T9)(implicit tag: Tag.CStruct14[T1,
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
                                                  T14]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._9)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _10(
      implicit tag: Tag.CStruct14[T1,
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
                                  T14]): T10 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.unary_!(tag._10)
  }

  def _10_=(value: T10)(implicit tag: Tag.CStruct14[T1,
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
                                                    T14]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._10)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _11(
      implicit tag: Tag.CStruct14[T1,
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
                                  T14]): T11 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.unary_!(tag._11)
  }

  def _11_=(value: T11)(implicit tag: Tag.CStruct14[T1,
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
                                                    T14]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._11)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _12(
      implicit tag: Tag.CStruct14[T1,
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
                                  T14]): T12 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset12)
    val fldptr    = fromRawPtr[T12](fldrawptr)
    fldptr.unary_!(tag._12)
  }

  def _12_=(value: T12)(implicit tag: Tag.CStruct14[T1,
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
                                                    T14]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset12)
    val fldptr    = fromRawPtr[T12](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._12)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _13(
      implicit tag: Tag.CStruct14[T1,
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
                                  T14]): T13 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset13)
    val fldptr    = fromRawPtr[T13](fldrawptr)
    fldptr.unary_!(tag._13)
  }

  def _13_=(value: T13)(implicit tag: Tag.CStruct14[T1,
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
                                                    T14]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset13)
    val fldptr    = fromRawPtr[T13](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._13)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _14(
      implicit tag: Tag.CStruct14[T1,
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
                                  T14]): T14 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset14)
    val fldptr    = fromRawPtr[T14](fldrawptr)
    fldptr.unary_!(tag._14)
  }

  def _14_=(value: T14)(implicit tag: Tag.CStruct14[T1,
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
                                                    T14]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset14)
    val fldptr    = fromRawPtr[T14](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._14)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 29)
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 10)

final class CStruct15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13,
T14, T15](private[scalanative] val rawptr: RawPtr) {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _1(
      implicit tag: Tag.CStruct15[T1,
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
                                  T15]): T1 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.unary_!(tag._1)
  }

  def _1_=(value: T1)(implicit tag: Tag.CStruct15[T1,
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
                                                  T15]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._1)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _2(
      implicit tag: Tag.CStruct15[T1,
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
                                  T15]): T2 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.unary_!(tag._2)
  }

  def _2_=(value: T2)(implicit tag: Tag.CStruct15[T1,
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
                                                  T15]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._2)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _3(
      implicit tag: Tag.CStruct15[T1,
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
                                  T15]): T3 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.unary_!(tag._3)
  }

  def _3_=(value: T3)(implicit tag: Tag.CStruct15[T1,
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
                                                  T15]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._3)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _4(
      implicit tag: Tag.CStruct15[T1,
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
                                  T15]): T4 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.unary_!(tag._4)
  }

  def _4_=(value: T4)(implicit tag: Tag.CStruct15[T1,
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
                                                  T15]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._4)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _5(
      implicit tag: Tag.CStruct15[T1,
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
                                  T15]): T5 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.unary_!(tag._5)
  }

  def _5_=(value: T5)(implicit tag: Tag.CStruct15[T1,
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
                                                  T15]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._5)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _6(
      implicit tag: Tag.CStruct15[T1,
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
                                  T15]): T6 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.unary_!(tag._6)
  }

  def _6_=(value: T6)(implicit tag: Tag.CStruct15[T1,
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
                                                  T15]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._6)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _7(
      implicit tag: Tag.CStruct15[T1,
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
                                  T15]): T7 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.unary_!(tag._7)
  }

  def _7_=(value: T7)(implicit tag: Tag.CStruct15[T1,
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
                                                  T15]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._7)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _8(
      implicit tag: Tag.CStruct15[T1,
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
                                  T15]): T8 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.unary_!(tag._8)
  }

  def _8_=(value: T8)(implicit tag: Tag.CStruct15[T1,
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
                                                  T15]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._8)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _9(
      implicit tag: Tag.CStruct15[T1,
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
                                  T15]): T9 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.unary_!(tag._9)
  }

  def _9_=(value: T9)(implicit tag: Tag.CStruct15[T1,
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
                                                  T15]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._9)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _10(
      implicit tag: Tag.CStruct15[T1,
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
                                  T15]): T10 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.unary_!(tag._10)
  }

  def _10_=(value: T10)(implicit tag: Tag.CStruct15[T1,
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
                                                    T15]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._10)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _11(
      implicit tag: Tag.CStruct15[T1,
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
                                  T15]): T11 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.unary_!(tag._11)
  }

  def _11_=(value: T11)(implicit tag: Tag.CStruct15[T1,
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
                                                    T15]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._11)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _12(
      implicit tag: Tag.CStruct15[T1,
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
                                  T15]): T12 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset12)
    val fldptr    = fromRawPtr[T12](fldrawptr)
    fldptr.unary_!(tag._12)
  }

  def _12_=(value: T12)(implicit tag: Tag.CStruct15[T1,
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
                                                    T15]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset12)
    val fldptr    = fromRawPtr[T12](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._12)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _13(
      implicit tag: Tag.CStruct15[T1,
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
                                  T15]): T13 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset13)
    val fldptr    = fromRawPtr[T13](fldrawptr)
    fldptr.unary_!(tag._13)
  }

  def _13_=(value: T13)(implicit tag: Tag.CStruct15[T1,
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
                                                    T15]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset13)
    val fldptr    = fromRawPtr[T13](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._13)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _14(
      implicit tag: Tag.CStruct15[T1,
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
                                  T15]): T14 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset14)
    val fldptr    = fromRawPtr[T14](fldrawptr)
    fldptr.unary_!(tag._14)
  }

  def _14_=(value: T14)(implicit tag: Tag.CStruct15[T1,
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
                                                    T15]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset14)
    val fldptr    = fromRawPtr[T14](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._14)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _15(
      implicit tag: Tag.CStruct15[T1,
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
                                  T15]): T15 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset15)
    val fldptr    = fromRawPtr[T15](fldrawptr)
    fldptr.unary_!(tag._15)
  }

  def _15_=(value: T15)(implicit tag: Tag.CStruct15[T1,
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
                                                    T15]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset15)
    val fldptr    = fromRawPtr[T15](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._15)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 29)
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 10)

final class CStruct16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13,
T14, T15, T16](private[scalanative] val rawptr: RawPtr) {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _1(
      implicit tag: Tag.CStruct16[T1,
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
                                  T16]): T1 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.unary_!(tag._1)
  }

  def _1_=(value: T1)(implicit tag: Tag.CStruct16[T1,
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
                                                  T16]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._1)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _2(
      implicit tag: Tag.CStruct16[T1,
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
                                  T16]): T2 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.unary_!(tag._2)
  }

  def _2_=(value: T2)(implicit tag: Tag.CStruct16[T1,
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
                                                  T16]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._2)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _3(
      implicit tag: Tag.CStruct16[T1,
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
                                  T16]): T3 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.unary_!(tag._3)
  }

  def _3_=(value: T3)(implicit tag: Tag.CStruct16[T1,
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
                                                  T16]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._3)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _4(
      implicit tag: Tag.CStruct16[T1,
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
                                  T16]): T4 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.unary_!(tag._4)
  }

  def _4_=(value: T4)(implicit tag: Tag.CStruct16[T1,
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
                                                  T16]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._4)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _5(
      implicit tag: Tag.CStruct16[T1,
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
                                  T16]): T5 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.unary_!(tag._5)
  }

  def _5_=(value: T5)(implicit tag: Tag.CStruct16[T1,
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
                                                  T16]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._5)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _6(
      implicit tag: Tag.CStruct16[T1,
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
                                  T16]): T6 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.unary_!(tag._6)
  }

  def _6_=(value: T6)(implicit tag: Tag.CStruct16[T1,
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
                                                  T16]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._6)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _7(
      implicit tag: Tag.CStruct16[T1,
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
                                  T16]): T7 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.unary_!(tag._7)
  }

  def _7_=(value: T7)(implicit tag: Tag.CStruct16[T1,
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
                                                  T16]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._7)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _8(
      implicit tag: Tag.CStruct16[T1,
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
                                  T16]): T8 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.unary_!(tag._8)
  }

  def _8_=(value: T8)(implicit tag: Tag.CStruct16[T1,
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
                                                  T16]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._8)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _9(
      implicit tag: Tag.CStruct16[T1,
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
                                  T16]): T9 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.unary_!(tag._9)
  }

  def _9_=(value: T9)(implicit tag: Tag.CStruct16[T1,
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
                                                  T16]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._9)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _10(
      implicit tag: Tag.CStruct16[T1,
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
                                  T16]): T10 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.unary_!(tag._10)
  }

  def _10_=(value: T10)(implicit tag: Tag.CStruct16[T1,
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
                                                    T16]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._10)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _11(
      implicit tag: Tag.CStruct16[T1,
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
                                  T16]): T11 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.unary_!(tag._11)
  }

  def _11_=(value: T11)(implicit tag: Tag.CStruct16[T1,
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
                                                    T16]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._11)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _12(
      implicit tag: Tag.CStruct16[T1,
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
                                  T16]): T12 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset12)
    val fldptr    = fromRawPtr[T12](fldrawptr)
    fldptr.unary_!(tag._12)
  }

  def _12_=(value: T12)(implicit tag: Tag.CStruct16[T1,
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
                                                    T16]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset12)
    val fldptr    = fromRawPtr[T12](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._12)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _13(
      implicit tag: Tag.CStruct16[T1,
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
                                  T16]): T13 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset13)
    val fldptr    = fromRawPtr[T13](fldrawptr)
    fldptr.unary_!(tag._13)
  }

  def _13_=(value: T13)(implicit tag: Tag.CStruct16[T1,
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
                                                    T16]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset13)
    val fldptr    = fromRawPtr[T13](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._13)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _14(
      implicit tag: Tag.CStruct16[T1,
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
                                  T16]): T14 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset14)
    val fldptr    = fromRawPtr[T14](fldrawptr)
    fldptr.unary_!(tag._14)
  }

  def _14_=(value: T14)(implicit tag: Tag.CStruct16[T1,
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
                                                    T16]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset14)
    val fldptr    = fromRawPtr[T14](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._14)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _15(
      implicit tag: Tag.CStruct16[T1,
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
                                  T16]): T15 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset15)
    val fldptr    = fromRawPtr[T15](fldrawptr)
    fldptr.unary_!(tag._15)
  }

  def _15_=(value: T15)(implicit tag: Tag.CStruct16[T1,
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
                                                    T16]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset15)
    val fldptr    = fromRawPtr[T15](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._15)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _16(
      implicit tag: Tag.CStruct16[T1,
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
                                  T16]): T16 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset16)
    val fldptr    = fromRawPtr[T16](fldrawptr)
    fldptr.unary_!(tag._16)
  }

  def _16_=(value: T16)(implicit tag: Tag.CStruct16[T1,
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
                                                    T16]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset16)
    val fldptr    = fromRawPtr[T16](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._16)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 29)
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 10)

final class CStruct17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13,
T14, T15, T16, T17](private[scalanative] val rawptr: RawPtr) {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _1(
      implicit tag: Tag.CStruct17[T1,
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
                                  T17]): T1 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.unary_!(tag._1)
  }

  def _1_=(value: T1)(implicit tag: Tag.CStruct17[T1,
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
                                                  T17]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._1)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _2(
      implicit tag: Tag.CStruct17[T1,
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
                                  T17]): T2 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.unary_!(tag._2)
  }

  def _2_=(value: T2)(implicit tag: Tag.CStruct17[T1,
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
                                                  T17]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._2)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _3(
      implicit tag: Tag.CStruct17[T1,
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
                                  T17]): T3 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.unary_!(tag._3)
  }

  def _3_=(value: T3)(implicit tag: Tag.CStruct17[T1,
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
                                                  T17]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._3)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _4(
      implicit tag: Tag.CStruct17[T1,
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
                                  T17]): T4 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.unary_!(tag._4)
  }

  def _4_=(value: T4)(implicit tag: Tag.CStruct17[T1,
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
                                                  T17]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._4)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _5(
      implicit tag: Tag.CStruct17[T1,
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
                                  T17]): T5 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.unary_!(tag._5)
  }

  def _5_=(value: T5)(implicit tag: Tag.CStruct17[T1,
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
                                                  T17]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._5)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _6(
      implicit tag: Tag.CStruct17[T1,
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
                                  T17]): T6 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.unary_!(tag._6)
  }

  def _6_=(value: T6)(implicit tag: Tag.CStruct17[T1,
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
                                                  T17]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._6)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _7(
      implicit tag: Tag.CStruct17[T1,
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
                                  T17]): T7 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.unary_!(tag._7)
  }

  def _7_=(value: T7)(implicit tag: Tag.CStruct17[T1,
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
                                                  T17]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._7)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _8(
      implicit tag: Tag.CStruct17[T1,
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
                                  T17]): T8 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.unary_!(tag._8)
  }

  def _8_=(value: T8)(implicit tag: Tag.CStruct17[T1,
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
                                                  T17]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._8)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _9(
      implicit tag: Tag.CStruct17[T1,
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
                                  T17]): T9 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.unary_!(tag._9)
  }

  def _9_=(value: T9)(implicit tag: Tag.CStruct17[T1,
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
                                                  T17]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._9)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _10(
      implicit tag: Tag.CStruct17[T1,
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
                                  T17]): T10 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.unary_!(tag._10)
  }

  def _10_=(value: T10)(implicit tag: Tag.CStruct17[T1,
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
                                                    T17]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._10)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _11(
      implicit tag: Tag.CStruct17[T1,
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
                                  T17]): T11 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.unary_!(tag._11)
  }

  def _11_=(value: T11)(implicit tag: Tag.CStruct17[T1,
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
                                                    T17]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._11)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _12(
      implicit tag: Tag.CStruct17[T1,
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
                                  T17]): T12 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset12)
    val fldptr    = fromRawPtr[T12](fldrawptr)
    fldptr.unary_!(tag._12)
  }

  def _12_=(value: T12)(implicit tag: Tag.CStruct17[T1,
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
                                                    T17]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset12)
    val fldptr    = fromRawPtr[T12](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._12)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _13(
      implicit tag: Tag.CStruct17[T1,
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
                                  T17]): T13 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset13)
    val fldptr    = fromRawPtr[T13](fldrawptr)
    fldptr.unary_!(tag._13)
  }

  def _13_=(value: T13)(implicit tag: Tag.CStruct17[T1,
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
                                                    T17]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset13)
    val fldptr    = fromRawPtr[T13](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._13)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _14(
      implicit tag: Tag.CStruct17[T1,
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
                                  T17]): T14 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset14)
    val fldptr    = fromRawPtr[T14](fldrawptr)
    fldptr.unary_!(tag._14)
  }

  def _14_=(value: T14)(implicit tag: Tag.CStruct17[T1,
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
                                                    T17]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset14)
    val fldptr    = fromRawPtr[T14](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._14)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _15(
      implicit tag: Tag.CStruct17[T1,
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
                                  T17]): T15 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset15)
    val fldptr    = fromRawPtr[T15](fldrawptr)
    fldptr.unary_!(tag._15)
  }

  def _15_=(value: T15)(implicit tag: Tag.CStruct17[T1,
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
                                                    T17]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset15)
    val fldptr    = fromRawPtr[T15](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._15)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _16(
      implicit tag: Tag.CStruct17[T1,
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
                                  T17]): T16 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset16)
    val fldptr    = fromRawPtr[T16](fldrawptr)
    fldptr.unary_!(tag._16)
  }

  def _16_=(value: T16)(implicit tag: Tag.CStruct17[T1,
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
                                                    T17]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset16)
    val fldptr    = fromRawPtr[T16](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._16)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _17(
      implicit tag: Tag.CStruct17[T1,
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
                                  T17]): T17 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset17)
    val fldptr    = fromRawPtr[T17](fldrawptr)
    fldptr.unary_!(tag._17)
  }

  def _17_=(value: T17)(implicit tag: Tag.CStruct17[T1,
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
                                                    T17]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset17)
    val fldptr    = fromRawPtr[T17](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._17)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 29)
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 10)

final class CStruct18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13,
T14, T15, T16, T17, T18](private[scalanative] val rawptr: RawPtr) {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _1(
      implicit tag: Tag.CStruct18[T1,
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
                                  T18]): T1 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.unary_!(tag._1)
  }

  def _1_=(value: T1)(implicit tag: Tag.CStruct18[T1,
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
                                                  T18]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._1)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _2(
      implicit tag: Tag.CStruct18[T1,
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
                                  T18]): T2 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.unary_!(tag._2)
  }

  def _2_=(value: T2)(implicit tag: Tag.CStruct18[T1,
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
                                                  T18]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._2)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _3(
      implicit tag: Tag.CStruct18[T1,
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
                                  T18]): T3 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.unary_!(tag._3)
  }

  def _3_=(value: T3)(implicit tag: Tag.CStruct18[T1,
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
                                                  T18]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._3)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _4(
      implicit tag: Tag.CStruct18[T1,
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
                                  T18]): T4 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.unary_!(tag._4)
  }

  def _4_=(value: T4)(implicit tag: Tag.CStruct18[T1,
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
                                                  T18]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._4)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _5(
      implicit tag: Tag.CStruct18[T1,
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
                                  T18]): T5 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.unary_!(tag._5)
  }

  def _5_=(value: T5)(implicit tag: Tag.CStruct18[T1,
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
                                                  T18]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._5)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _6(
      implicit tag: Tag.CStruct18[T1,
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
                                  T18]): T6 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.unary_!(tag._6)
  }

  def _6_=(value: T6)(implicit tag: Tag.CStruct18[T1,
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
                                                  T18]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._6)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _7(
      implicit tag: Tag.CStruct18[T1,
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
                                  T18]): T7 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.unary_!(tag._7)
  }

  def _7_=(value: T7)(implicit tag: Tag.CStruct18[T1,
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
                                                  T18]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._7)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _8(
      implicit tag: Tag.CStruct18[T1,
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
                                  T18]): T8 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.unary_!(tag._8)
  }

  def _8_=(value: T8)(implicit tag: Tag.CStruct18[T1,
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
                                                  T18]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._8)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _9(
      implicit tag: Tag.CStruct18[T1,
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
                                  T18]): T9 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.unary_!(tag._9)
  }

  def _9_=(value: T9)(implicit tag: Tag.CStruct18[T1,
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
                                                  T18]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._9)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _10(
      implicit tag: Tag.CStruct18[T1,
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
                                  T18]): T10 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.unary_!(tag._10)
  }

  def _10_=(value: T10)(implicit tag: Tag.CStruct18[T1,
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
                                                    T18]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._10)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _11(
      implicit tag: Tag.CStruct18[T1,
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
                                  T18]): T11 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.unary_!(tag._11)
  }

  def _11_=(value: T11)(implicit tag: Tag.CStruct18[T1,
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
                                                    T18]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._11)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _12(
      implicit tag: Tag.CStruct18[T1,
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
                                  T18]): T12 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset12)
    val fldptr    = fromRawPtr[T12](fldrawptr)
    fldptr.unary_!(tag._12)
  }

  def _12_=(value: T12)(implicit tag: Tag.CStruct18[T1,
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
                                                    T18]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset12)
    val fldptr    = fromRawPtr[T12](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._12)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _13(
      implicit tag: Tag.CStruct18[T1,
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
                                  T18]): T13 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset13)
    val fldptr    = fromRawPtr[T13](fldrawptr)
    fldptr.unary_!(tag._13)
  }

  def _13_=(value: T13)(implicit tag: Tag.CStruct18[T1,
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
                                                    T18]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset13)
    val fldptr    = fromRawPtr[T13](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._13)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _14(
      implicit tag: Tag.CStruct18[T1,
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
                                  T18]): T14 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset14)
    val fldptr    = fromRawPtr[T14](fldrawptr)
    fldptr.unary_!(tag._14)
  }

  def _14_=(value: T14)(implicit tag: Tag.CStruct18[T1,
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
                                                    T18]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset14)
    val fldptr    = fromRawPtr[T14](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._14)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _15(
      implicit tag: Tag.CStruct18[T1,
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
                                  T18]): T15 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset15)
    val fldptr    = fromRawPtr[T15](fldrawptr)
    fldptr.unary_!(tag._15)
  }

  def _15_=(value: T15)(implicit tag: Tag.CStruct18[T1,
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
                                                    T18]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset15)
    val fldptr    = fromRawPtr[T15](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._15)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _16(
      implicit tag: Tag.CStruct18[T1,
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
                                  T18]): T16 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset16)
    val fldptr    = fromRawPtr[T16](fldrawptr)
    fldptr.unary_!(tag._16)
  }

  def _16_=(value: T16)(implicit tag: Tag.CStruct18[T1,
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
                                                    T18]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset16)
    val fldptr    = fromRawPtr[T16](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._16)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _17(
      implicit tag: Tag.CStruct18[T1,
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
                                  T18]): T17 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset17)
    val fldptr    = fromRawPtr[T17](fldrawptr)
    fldptr.unary_!(tag._17)
  }

  def _17_=(value: T17)(implicit tag: Tag.CStruct18[T1,
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
                                                    T18]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset17)
    val fldptr    = fromRawPtr[T17](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._17)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _18(
      implicit tag: Tag.CStruct18[T1,
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
                                  T18]): T18 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset18)
    val fldptr    = fromRawPtr[T18](fldrawptr)
    fldptr.unary_!(tag._18)
  }

  def _18_=(value: T18)(implicit tag: Tag.CStruct18[T1,
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
                                                    T18]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset18)
    val fldptr    = fromRawPtr[T18](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._18)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 29)
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 10)

final class CStruct19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13,
T14, T15, T16, T17, T18, T19](private[scalanative] val rawptr: RawPtr) {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _1(
      implicit tag: Tag.CStruct19[T1,
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
                                  T19]): T1 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.unary_!(tag._1)
  }

  def _1_=(value: T1)(implicit tag: Tag.CStruct19[T1,
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
                                                  T19]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._1)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _2(
      implicit tag: Tag.CStruct19[T1,
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
                                  T19]): T2 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.unary_!(tag._2)
  }

  def _2_=(value: T2)(implicit tag: Tag.CStruct19[T1,
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
                                                  T19]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._2)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _3(
      implicit tag: Tag.CStruct19[T1,
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
                                  T19]): T3 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.unary_!(tag._3)
  }

  def _3_=(value: T3)(implicit tag: Tag.CStruct19[T1,
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
                                                  T19]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._3)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _4(
      implicit tag: Tag.CStruct19[T1,
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
                                  T19]): T4 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.unary_!(tag._4)
  }

  def _4_=(value: T4)(implicit tag: Tag.CStruct19[T1,
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
                                                  T19]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._4)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _5(
      implicit tag: Tag.CStruct19[T1,
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
                                  T19]): T5 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.unary_!(tag._5)
  }

  def _5_=(value: T5)(implicit tag: Tag.CStruct19[T1,
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
                                                  T19]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._5)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _6(
      implicit tag: Tag.CStruct19[T1,
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
                                  T19]): T6 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.unary_!(tag._6)
  }

  def _6_=(value: T6)(implicit tag: Tag.CStruct19[T1,
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
                                                  T19]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._6)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _7(
      implicit tag: Tag.CStruct19[T1,
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
                                  T19]): T7 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.unary_!(tag._7)
  }

  def _7_=(value: T7)(implicit tag: Tag.CStruct19[T1,
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
                                                  T19]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._7)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _8(
      implicit tag: Tag.CStruct19[T1,
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
                                  T19]): T8 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.unary_!(tag._8)
  }

  def _8_=(value: T8)(implicit tag: Tag.CStruct19[T1,
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
                                                  T19]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._8)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _9(
      implicit tag: Tag.CStruct19[T1,
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
                                  T19]): T9 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.unary_!(tag._9)
  }

  def _9_=(value: T9)(implicit tag: Tag.CStruct19[T1,
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
                                                  T19]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._9)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _10(
      implicit tag: Tag.CStruct19[T1,
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
                                  T19]): T10 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.unary_!(tag._10)
  }

  def _10_=(value: T10)(implicit tag: Tag.CStruct19[T1,
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
                                                    T19]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._10)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _11(
      implicit tag: Tag.CStruct19[T1,
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
                                  T19]): T11 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.unary_!(tag._11)
  }

  def _11_=(value: T11)(implicit tag: Tag.CStruct19[T1,
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
                                                    T19]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._11)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _12(
      implicit tag: Tag.CStruct19[T1,
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
                                  T19]): T12 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset12)
    val fldptr    = fromRawPtr[T12](fldrawptr)
    fldptr.unary_!(tag._12)
  }

  def _12_=(value: T12)(implicit tag: Tag.CStruct19[T1,
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
                                                    T19]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset12)
    val fldptr    = fromRawPtr[T12](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._12)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _13(
      implicit tag: Tag.CStruct19[T1,
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
                                  T19]): T13 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset13)
    val fldptr    = fromRawPtr[T13](fldrawptr)
    fldptr.unary_!(tag._13)
  }

  def _13_=(value: T13)(implicit tag: Tag.CStruct19[T1,
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
                                                    T19]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset13)
    val fldptr    = fromRawPtr[T13](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._13)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _14(
      implicit tag: Tag.CStruct19[T1,
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
                                  T19]): T14 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset14)
    val fldptr    = fromRawPtr[T14](fldrawptr)
    fldptr.unary_!(tag._14)
  }

  def _14_=(value: T14)(implicit tag: Tag.CStruct19[T1,
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
                                                    T19]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset14)
    val fldptr    = fromRawPtr[T14](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._14)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _15(
      implicit tag: Tag.CStruct19[T1,
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
                                  T19]): T15 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset15)
    val fldptr    = fromRawPtr[T15](fldrawptr)
    fldptr.unary_!(tag._15)
  }

  def _15_=(value: T15)(implicit tag: Tag.CStruct19[T1,
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
                                                    T19]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset15)
    val fldptr    = fromRawPtr[T15](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._15)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _16(
      implicit tag: Tag.CStruct19[T1,
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
                                  T19]): T16 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset16)
    val fldptr    = fromRawPtr[T16](fldrawptr)
    fldptr.unary_!(tag._16)
  }

  def _16_=(value: T16)(implicit tag: Tag.CStruct19[T1,
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
                                                    T19]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset16)
    val fldptr    = fromRawPtr[T16](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._16)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _17(
      implicit tag: Tag.CStruct19[T1,
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
                                  T19]): T17 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset17)
    val fldptr    = fromRawPtr[T17](fldrawptr)
    fldptr.unary_!(tag._17)
  }

  def _17_=(value: T17)(implicit tag: Tag.CStruct19[T1,
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
                                                    T19]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset17)
    val fldptr    = fromRawPtr[T17](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._17)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _18(
      implicit tag: Tag.CStruct19[T1,
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
                                  T19]): T18 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset18)
    val fldptr    = fromRawPtr[T18](fldrawptr)
    fldptr.unary_!(tag._18)
  }

  def _18_=(value: T18)(implicit tag: Tag.CStruct19[T1,
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
                                                    T19]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset18)
    val fldptr    = fromRawPtr[T18](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._18)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _19(
      implicit tag: Tag.CStruct19[T1,
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
                                  T19]): T19 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset19)
    val fldptr    = fromRawPtr[T19](fldrawptr)
    fldptr.unary_!(tag._19)
  }

  def _19_=(value: T19)(implicit tag: Tag.CStruct19[T1,
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
                                                    T19]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset19)
    val fldptr    = fromRawPtr[T19](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._19)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 29)
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 10)

final class CStruct20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13,
T14, T15, T16, T17, T18, T19, T20](private[scalanative] val rawptr: RawPtr) {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _1(
      implicit tag: Tag.CStruct20[T1,
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
                                  T20]): T1 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.unary_!(tag._1)
  }

  def _1_=(value: T1)(implicit tag: Tag.CStruct20[T1,
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
                                                  T20]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._1)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _2(
      implicit tag: Tag.CStruct20[T1,
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
                                  T20]): T2 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.unary_!(tag._2)
  }

  def _2_=(value: T2)(implicit tag: Tag.CStruct20[T1,
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
                                                  T20]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._2)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _3(
      implicit tag: Tag.CStruct20[T1,
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
                                  T20]): T3 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.unary_!(tag._3)
  }

  def _3_=(value: T3)(implicit tag: Tag.CStruct20[T1,
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
                                                  T20]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._3)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _4(
      implicit tag: Tag.CStruct20[T1,
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
                                  T20]): T4 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.unary_!(tag._4)
  }

  def _4_=(value: T4)(implicit tag: Tag.CStruct20[T1,
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
                                                  T20]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._4)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _5(
      implicit tag: Tag.CStruct20[T1,
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
                                  T20]): T5 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.unary_!(tag._5)
  }

  def _5_=(value: T5)(implicit tag: Tag.CStruct20[T1,
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
                                                  T20]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._5)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _6(
      implicit tag: Tag.CStruct20[T1,
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
                                  T20]): T6 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.unary_!(tag._6)
  }

  def _6_=(value: T6)(implicit tag: Tag.CStruct20[T1,
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
                                                  T20]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._6)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _7(
      implicit tag: Tag.CStruct20[T1,
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
                                  T20]): T7 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.unary_!(tag._7)
  }

  def _7_=(value: T7)(implicit tag: Tag.CStruct20[T1,
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
                                                  T20]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._7)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _8(
      implicit tag: Tag.CStruct20[T1,
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
                                  T20]): T8 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.unary_!(tag._8)
  }

  def _8_=(value: T8)(implicit tag: Tag.CStruct20[T1,
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
                                                  T20]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._8)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _9(
      implicit tag: Tag.CStruct20[T1,
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
                                  T20]): T9 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.unary_!(tag._9)
  }

  def _9_=(value: T9)(implicit tag: Tag.CStruct20[T1,
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
                                                  T20]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._9)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _10(
      implicit tag: Tag.CStruct20[T1,
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
                                  T20]): T10 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.unary_!(tag._10)
  }

  def _10_=(value: T10)(implicit tag: Tag.CStruct20[T1,
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
                                                    T20]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._10)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _11(
      implicit tag: Tag.CStruct20[T1,
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
                                  T20]): T11 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.unary_!(tag._11)
  }

  def _11_=(value: T11)(implicit tag: Tag.CStruct20[T1,
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
                                                    T20]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._11)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _12(
      implicit tag: Tag.CStruct20[T1,
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
                                  T20]): T12 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset12)
    val fldptr    = fromRawPtr[T12](fldrawptr)
    fldptr.unary_!(tag._12)
  }

  def _12_=(value: T12)(implicit tag: Tag.CStruct20[T1,
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
                                                    T20]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset12)
    val fldptr    = fromRawPtr[T12](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._12)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _13(
      implicit tag: Tag.CStruct20[T1,
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
                                  T20]): T13 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset13)
    val fldptr    = fromRawPtr[T13](fldrawptr)
    fldptr.unary_!(tag._13)
  }

  def _13_=(value: T13)(implicit tag: Tag.CStruct20[T1,
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
                                                    T20]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset13)
    val fldptr    = fromRawPtr[T13](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._13)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _14(
      implicit tag: Tag.CStruct20[T1,
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
                                  T20]): T14 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset14)
    val fldptr    = fromRawPtr[T14](fldrawptr)
    fldptr.unary_!(tag._14)
  }

  def _14_=(value: T14)(implicit tag: Tag.CStruct20[T1,
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
                                                    T20]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset14)
    val fldptr    = fromRawPtr[T14](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._14)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _15(
      implicit tag: Tag.CStruct20[T1,
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
                                  T20]): T15 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset15)
    val fldptr    = fromRawPtr[T15](fldrawptr)
    fldptr.unary_!(tag._15)
  }

  def _15_=(value: T15)(implicit tag: Tag.CStruct20[T1,
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
                                                    T20]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset15)
    val fldptr    = fromRawPtr[T15](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._15)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _16(
      implicit tag: Tag.CStruct20[T1,
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
                                  T20]): T16 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset16)
    val fldptr    = fromRawPtr[T16](fldrawptr)
    fldptr.unary_!(tag._16)
  }

  def _16_=(value: T16)(implicit tag: Tag.CStruct20[T1,
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
                                                    T20]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset16)
    val fldptr    = fromRawPtr[T16](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._16)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _17(
      implicit tag: Tag.CStruct20[T1,
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
                                  T20]): T17 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset17)
    val fldptr    = fromRawPtr[T17](fldrawptr)
    fldptr.unary_!(tag._17)
  }

  def _17_=(value: T17)(implicit tag: Tag.CStruct20[T1,
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
                                                    T20]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset17)
    val fldptr    = fromRawPtr[T17](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._17)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _18(
      implicit tag: Tag.CStruct20[T1,
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
                                  T20]): T18 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset18)
    val fldptr    = fromRawPtr[T18](fldrawptr)
    fldptr.unary_!(tag._18)
  }

  def _18_=(value: T18)(implicit tag: Tag.CStruct20[T1,
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
                                                    T20]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset18)
    val fldptr    = fromRawPtr[T18](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._18)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _19(
      implicit tag: Tag.CStruct20[T1,
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
                                  T20]): T19 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset19)
    val fldptr    = fromRawPtr[T19](fldrawptr)
    fldptr.unary_!(tag._19)
  }

  def _19_=(value: T19)(implicit tag: Tag.CStruct20[T1,
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
                                                    T20]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset19)
    val fldptr    = fromRawPtr[T19](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._19)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _20(
      implicit tag: Tag.CStruct20[T1,
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
                                  T20]): T20 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset20)
    val fldptr    = fromRawPtr[T20](fldrawptr)
    fldptr.unary_!(tag._20)
  }

  def _20_=(value: T20)(implicit tag: Tag.CStruct20[T1,
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
                                                    T20]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset20)
    val fldptr    = fromRawPtr[T20](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._20)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 29)
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 10)

final class CStruct21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13,
T14, T15, T16, T17, T18, T19, T20, T21](
    private[scalanative] val rawptr: RawPtr) {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _1(
      implicit tag: Tag.CStruct21[T1,
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
                                  T21]): T1 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.unary_!(tag._1)
  }

  def _1_=(value: T1)(implicit tag: Tag.CStruct21[T1,
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
                                                  T21]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._1)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _2(
      implicit tag: Tag.CStruct21[T1,
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
                                  T21]): T2 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.unary_!(tag._2)
  }

  def _2_=(value: T2)(implicit tag: Tag.CStruct21[T1,
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
                                                  T21]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._2)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _3(
      implicit tag: Tag.CStruct21[T1,
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
                                  T21]): T3 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.unary_!(tag._3)
  }

  def _3_=(value: T3)(implicit tag: Tag.CStruct21[T1,
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
                                                  T21]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._3)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _4(
      implicit tag: Tag.CStruct21[T1,
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
                                  T21]): T4 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.unary_!(tag._4)
  }

  def _4_=(value: T4)(implicit tag: Tag.CStruct21[T1,
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
                                                  T21]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._4)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _5(
      implicit tag: Tag.CStruct21[T1,
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
                                  T21]): T5 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.unary_!(tag._5)
  }

  def _5_=(value: T5)(implicit tag: Tag.CStruct21[T1,
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
                                                  T21]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._5)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _6(
      implicit tag: Tag.CStruct21[T1,
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
                                  T21]): T6 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.unary_!(tag._6)
  }

  def _6_=(value: T6)(implicit tag: Tag.CStruct21[T1,
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
                                                  T21]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._6)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _7(
      implicit tag: Tag.CStruct21[T1,
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
                                  T21]): T7 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.unary_!(tag._7)
  }

  def _7_=(value: T7)(implicit tag: Tag.CStruct21[T1,
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
                                                  T21]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._7)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _8(
      implicit tag: Tag.CStruct21[T1,
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
                                  T21]): T8 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.unary_!(tag._8)
  }

  def _8_=(value: T8)(implicit tag: Tag.CStruct21[T1,
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
                                                  T21]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._8)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _9(
      implicit tag: Tag.CStruct21[T1,
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
                                  T21]): T9 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.unary_!(tag._9)
  }

  def _9_=(value: T9)(implicit tag: Tag.CStruct21[T1,
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
                                                  T21]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._9)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _10(
      implicit tag: Tag.CStruct21[T1,
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
                                  T21]): T10 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.unary_!(tag._10)
  }

  def _10_=(value: T10)(implicit tag: Tag.CStruct21[T1,
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
                                                    T21]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._10)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _11(
      implicit tag: Tag.CStruct21[T1,
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
                                  T21]): T11 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.unary_!(tag._11)
  }

  def _11_=(value: T11)(implicit tag: Tag.CStruct21[T1,
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
                                                    T21]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._11)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _12(
      implicit tag: Tag.CStruct21[T1,
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
                                  T21]): T12 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset12)
    val fldptr    = fromRawPtr[T12](fldrawptr)
    fldptr.unary_!(tag._12)
  }

  def _12_=(value: T12)(implicit tag: Tag.CStruct21[T1,
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
                                                    T21]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset12)
    val fldptr    = fromRawPtr[T12](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._12)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _13(
      implicit tag: Tag.CStruct21[T1,
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
                                  T21]): T13 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset13)
    val fldptr    = fromRawPtr[T13](fldrawptr)
    fldptr.unary_!(tag._13)
  }

  def _13_=(value: T13)(implicit tag: Tag.CStruct21[T1,
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
                                                    T21]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset13)
    val fldptr    = fromRawPtr[T13](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._13)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _14(
      implicit tag: Tag.CStruct21[T1,
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
                                  T21]): T14 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset14)
    val fldptr    = fromRawPtr[T14](fldrawptr)
    fldptr.unary_!(tag._14)
  }

  def _14_=(value: T14)(implicit tag: Tag.CStruct21[T1,
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
                                                    T21]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset14)
    val fldptr    = fromRawPtr[T14](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._14)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _15(
      implicit tag: Tag.CStruct21[T1,
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
                                  T21]): T15 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset15)
    val fldptr    = fromRawPtr[T15](fldrawptr)
    fldptr.unary_!(tag._15)
  }

  def _15_=(value: T15)(implicit tag: Tag.CStruct21[T1,
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
                                                    T21]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset15)
    val fldptr    = fromRawPtr[T15](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._15)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _16(
      implicit tag: Tag.CStruct21[T1,
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
                                  T21]): T16 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset16)
    val fldptr    = fromRawPtr[T16](fldrawptr)
    fldptr.unary_!(tag._16)
  }

  def _16_=(value: T16)(implicit tag: Tag.CStruct21[T1,
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
                                                    T21]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset16)
    val fldptr    = fromRawPtr[T16](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._16)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _17(
      implicit tag: Tag.CStruct21[T1,
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
                                  T21]): T17 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset17)
    val fldptr    = fromRawPtr[T17](fldrawptr)
    fldptr.unary_!(tag._17)
  }

  def _17_=(value: T17)(implicit tag: Tag.CStruct21[T1,
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
                                                    T21]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset17)
    val fldptr    = fromRawPtr[T17](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._17)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _18(
      implicit tag: Tag.CStruct21[T1,
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
                                  T21]): T18 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset18)
    val fldptr    = fromRawPtr[T18](fldrawptr)
    fldptr.unary_!(tag._18)
  }

  def _18_=(value: T18)(implicit tag: Tag.CStruct21[T1,
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
                                                    T21]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset18)
    val fldptr    = fromRawPtr[T18](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._18)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _19(
      implicit tag: Tag.CStruct21[T1,
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
                                  T21]): T19 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset19)
    val fldptr    = fromRawPtr[T19](fldrawptr)
    fldptr.unary_!(tag._19)
  }

  def _19_=(value: T19)(implicit tag: Tag.CStruct21[T1,
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
                                                    T21]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset19)
    val fldptr    = fromRawPtr[T19](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._19)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _20(
      implicit tag: Tag.CStruct21[T1,
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
                                  T21]): T20 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset20)
    val fldptr    = fromRawPtr[T20](fldrawptr)
    fldptr.unary_!(tag._20)
  }

  def _20_=(value: T20)(implicit tag: Tag.CStruct21[T1,
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
                                                    T21]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset20)
    val fldptr    = fromRawPtr[T20](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._20)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _21(
      implicit tag: Tag.CStruct21[T1,
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
                                  T21]): T21 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset21)
    val fldptr    = fromRawPtr[T21](fldrawptr)
    fldptr.unary_!(tag._21)
  }

  def _21_=(value: T21)(implicit tag: Tag.CStruct21[T1,
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
                                                    T21]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset21)
    val fldptr    = fromRawPtr[T21](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._21)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 29)
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 10)

final class CStruct22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13,
T14, T15, T16, T17, T18, T19, T20, T21, T22](
    private[scalanative] val rawptr: RawPtr) {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _1(
      implicit tag: Tag.CStruct22[T1,
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
                                  T22]): T1 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.unary_!(tag._1)
  }

  def _1_=(value: T1)(implicit tag: Tag.CStruct22[T1,
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
                                                  T22]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset1)
    val fldptr    = fromRawPtr[T1](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._1)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _2(
      implicit tag: Tag.CStruct22[T1,
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
                                  T22]): T2 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.unary_!(tag._2)
  }

  def _2_=(value: T2)(implicit tag: Tag.CStruct22[T1,
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
                                                  T22]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset2)
    val fldptr    = fromRawPtr[T2](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._2)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _3(
      implicit tag: Tag.CStruct22[T1,
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
                                  T22]): T3 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.unary_!(tag._3)
  }

  def _3_=(value: T3)(implicit tag: Tag.CStruct22[T1,
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
                                                  T22]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset3)
    val fldptr    = fromRawPtr[T3](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._3)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _4(
      implicit tag: Tag.CStruct22[T1,
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
                                  T22]): T4 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.unary_!(tag._4)
  }

  def _4_=(value: T4)(implicit tag: Tag.CStruct22[T1,
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
                                                  T22]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset4)
    val fldptr    = fromRawPtr[T4](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._4)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _5(
      implicit tag: Tag.CStruct22[T1,
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
                                  T22]): T5 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.unary_!(tag._5)
  }

  def _5_=(value: T5)(implicit tag: Tag.CStruct22[T1,
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
                                                  T22]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset5)
    val fldptr    = fromRawPtr[T5](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._5)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _6(
      implicit tag: Tag.CStruct22[T1,
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
                                  T22]): T6 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.unary_!(tag._6)
  }

  def _6_=(value: T6)(implicit tag: Tag.CStruct22[T1,
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
                                                  T22]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset6)
    val fldptr    = fromRawPtr[T6](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._6)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _7(
      implicit tag: Tag.CStruct22[T1,
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
                                  T22]): T7 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.unary_!(tag._7)
  }

  def _7_=(value: T7)(implicit tag: Tag.CStruct22[T1,
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
                                                  T22]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset7)
    val fldptr    = fromRawPtr[T7](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._7)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _8(
      implicit tag: Tag.CStruct22[T1,
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
                                  T22]): T8 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.unary_!(tag._8)
  }

  def _8_=(value: T8)(implicit tag: Tag.CStruct22[T1,
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
                                                  T22]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset8)
    val fldptr    = fromRawPtr[T8](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._8)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _9(
      implicit tag: Tag.CStruct22[T1,
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
                                  T22]): T9 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.unary_!(tag._9)
  }

  def _9_=(value: T9)(implicit tag: Tag.CStruct22[T1,
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
                                                  T22]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset9)
    val fldptr    = fromRawPtr[T9](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._9)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _10(
      implicit tag: Tag.CStruct22[T1,
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
                                  T22]): T10 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.unary_!(tag._10)
  }

  def _10_=(value: T10)(implicit tag: Tag.CStruct22[T1,
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
                                                    T22]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset10)
    val fldptr    = fromRawPtr[T10](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._10)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _11(
      implicit tag: Tag.CStruct22[T1,
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
                                  T22]): T11 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.unary_!(tag._11)
  }

  def _11_=(value: T11)(implicit tag: Tag.CStruct22[T1,
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
                                                    T22]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset11)
    val fldptr    = fromRawPtr[T11](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._11)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _12(
      implicit tag: Tag.CStruct22[T1,
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
                                  T22]): T12 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset12)
    val fldptr    = fromRawPtr[T12](fldrawptr)
    fldptr.unary_!(tag._12)
  }

  def _12_=(value: T12)(implicit tag: Tag.CStruct22[T1,
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
                                                    T22]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset12)
    val fldptr    = fromRawPtr[T12](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._12)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _13(
      implicit tag: Tag.CStruct22[T1,
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
                                  T22]): T13 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset13)
    val fldptr    = fromRawPtr[T13](fldrawptr)
    fldptr.unary_!(tag._13)
  }

  def _13_=(value: T13)(implicit tag: Tag.CStruct22[T1,
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
                                                    T22]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset13)
    val fldptr    = fromRawPtr[T13](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._13)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _14(
      implicit tag: Tag.CStruct22[T1,
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
                                  T22]): T14 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset14)
    val fldptr    = fromRawPtr[T14](fldrawptr)
    fldptr.unary_!(tag._14)
  }

  def _14_=(value: T14)(implicit tag: Tag.CStruct22[T1,
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
                                                    T22]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset14)
    val fldptr    = fromRawPtr[T14](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._14)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _15(
      implicit tag: Tag.CStruct22[T1,
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
                                  T22]): T15 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset15)
    val fldptr    = fromRawPtr[T15](fldrawptr)
    fldptr.unary_!(tag._15)
  }

  def _15_=(value: T15)(implicit tag: Tag.CStruct22[T1,
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
                                                    T22]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset15)
    val fldptr    = fromRawPtr[T15](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._15)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _16(
      implicit tag: Tag.CStruct22[T1,
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
                                  T22]): T16 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset16)
    val fldptr    = fromRawPtr[T16](fldrawptr)
    fldptr.unary_!(tag._16)
  }

  def _16_=(value: T16)(implicit tag: Tag.CStruct22[T1,
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
                                                    T22]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset16)
    val fldptr    = fromRawPtr[T16](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._16)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _17(
      implicit tag: Tag.CStruct22[T1,
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
                                  T22]): T17 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset17)
    val fldptr    = fromRawPtr[T17](fldrawptr)
    fldptr.unary_!(tag._17)
  }

  def _17_=(value: T17)(implicit tag: Tag.CStruct22[T1,
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
                                                    T22]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset17)
    val fldptr    = fromRawPtr[T17](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._17)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _18(
      implicit tag: Tag.CStruct22[T1,
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
                                  T22]): T18 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset18)
    val fldptr    = fromRawPtr[T18](fldrawptr)
    fldptr.unary_!(tag._18)
  }

  def _18_=(value: T18)(implicit tag: Tag.CStruct22[T1,
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
                                                    T22]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset18)
    val fldptr    = fromRawPtr[T18](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._18)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _19(
      implicit tag: Tag.CStruct22[T1,
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
                                  T22]): T19 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset19)
    val fldptr    = fromRawPtr[T19](fldrawptr)
    fldptr.unary_!(tag._19)
  }

  def _19_=(value: T19)(implicit tag: Tag.CStruct22[T1,
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
                                                    T22]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset19)
    val fldptr    = fromRawPtr[T19](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._19)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _20(
      implicit tag: Tag.CStruct22[T1,
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
                                  T22]): T20 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset20)
    val fldptr    = fromRawPtr[T20](fldrawptr)
    fldptr.unary_!(tag._20)
  }

  def _20_=(value: T20)(implicit tag: Tag.CStruct22[T1,
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
                                                    T22]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset20)
    val fldptr    = fromRawPtr[T20](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._20)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _21(
      implicit tag: Tag.CStruct22[T1,
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
                                  T22]): T21 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset21)
    val fldptr    = fromRawPtr[T21](fldrawptr)
    fldptr.unary_!(tag._21)
  }

  def _21_=(value: T21)(implicit tag: Tag.CStruct22[T1,
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
                                                    T22]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset21)
    val fldptr    = fromRawPtr[T21](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._21)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 15)

  def _22(
      implicit tag: Tag.CStruct22[T1,
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
                                  T22]): T22 = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset22)
    val fldptr    = fromRawPtr[T22](fldrawptr)
    fldptr.unary_!(tag._22)
  }

  def _22_=(value: T22)(implicit tag: Tag.CStruct22[T1,
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
                                                    T22]): Unit = {
    val fldrawptr = elemRawPtr(rawptr, tag.offset22)
    val fldptr    = fromRawPtr[T22](fldrawptr)
    fldptr.`unary_!_=`(value)(tag._22)
  }

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CStruct.scala.gyb", line: 29)
}
