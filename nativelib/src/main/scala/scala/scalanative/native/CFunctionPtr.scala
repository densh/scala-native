// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 1)
package scala.scalanative
package native

import scala.reflect.ClassTag
import scalanative.runtime.intrinsic

/** C-style function pointer. */
sealed abstract class CFunctionPtr

object CFunctionPtr {

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 14)

  implicit def fromFunction0[R](f: Function0[R]): CFunctionPtr0[R] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 14)

  implicit def fromFunction1[T1, R](f: Function1[T1, R]): CFunctionPtr1[T1, R] =
    intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 14)

  implicit def fromFunction2[T1, T2, R](
      f: Function2[T1, T2, R]): CFunctionPtr2[T1, T2, R] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 14)

  implicit def fromFunction3[T1, T2, T3, R](
      f: Function3[T1, T2, T3, R]): CFunctionPtr3[T1, T2, T3, R] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 14)

  implicit def fromFunction4[T1, T2, T3, T4, R](
      f: Function4[T1, T2, T3, T4, R]): CFunctionPtr4[T1, T2, T3, T4, R] =
    intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 14)

  implicit def fromFunction5[T1, T2, T3, T4, T5, R](
      f: Function5[T1, T2, T3, T4, T5, R])
    : CFunctionPtr5[T1, T2, T3, T4, T5, R] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 14)

  implicit def fromFunction6[T1, T2, T3, T4, T5, T6, R](
      f: Function6[T1, T2, T3, T4, T5, T6, R])
    : CFunctionPtr6[T1, T2, T3, T4, T5, T6, R] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 14)

  implicit def fromFunction7[T1, T2, T3, T4, T5, T6, T7, R](
      f: Function7[T1, T2, T3, T4, T5, T6, T7, R])
    : CFunctionPtr7[T1, T2, T3, T4, T5, T6, T7, R] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 14)

  implicit def fromFunction8[T1, T2, T3, T4, T5, T6, T7, T8, R](
      f: Function8[T1, T2, T3, T4, T5, T6, T7, T8, R])
    : CFunctionPtr8[T1, T2, T3, T4, T5, T6, T7, T8, R] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 14)

  implicit def fromFunction9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R](
      f: Function9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R])
    : CFunctionPtr9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 14)

  implicit def fromFunction10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R](
      f: Function10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R])
    : CFunctionPtr10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 14)

  implicit def fromFunction11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R](
      f: Function11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R])
    : CFunctionPtr11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R] =
    intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 14)

  implicit def fromFunction12[T1,
                              T2,
                              T3,
                              T4,
                              T5,
                              T6,
                              T7,
                              T8,
                              T9,
                              T10,
                              T11,
                              T12,
                              R](
      f: Function12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R])
    : CFunctionPtr12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R] =
    intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 14)

  implicit def fromFunction13[T1,
                              T2,
                              T3,
                              T4,
                              T5,
                              T6,
                              T7,
                              T8,
                              T9,
                              T10,
                              T11,
                              T12,
                              T13,
                              R](
      f: Function13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R])
    : CFunctionPtr13[T1,
                     T2,
                     T3,
                     T4,
                     T5,
                     T6,
                     T7,
                     T8,
                     T9,
                     T10,
                     T11,
                     T12,
                     T13,
                     R] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 14)

  implicit def fromFunction14[T1,
                              T2,
                              T3,
                              T4,
                              T5,
                              T6,
                              T7,
                              T8,
                              T9,
                              T10,
                              T11,
                              T12,
                              T13,
                              T14,
                              R](
      f: Function14[T1,
                    T2,
                    T3,
                    T4,
                    T5,
                    T6,
                    T7,
                    T8,
                    T9,
                    T10,
                    T11,
                    T12,
                    T13,
                    T14,
                    R]): CFunctionPtr14[T1,
                                        T2,
                                        T3,
                                        T4,
                                        T5,
                                        T6,
                                        T7,
                                        T8,
                                        T9,
                                        T10,
                                        T11,
                                        T12,
                                        T13,
                                        T14,
                                        R] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 14)

  implicit def fromFunction15[T1,
                              T2,
                              T3,
                              T4,
                              T5,
                              T6,
                              T7,
                              T8,
                              T9,
                              T10,
                              T11,
                              T12,
                              T13,
                              T14,
                              T15,
                              R](
      f: Function15[T1,
                    T2,
                    T3,
                    T4,
                    T5,
                    T6,
                    T7,
                    T8,
                    T9,
                    T10,
                    T11,
                    T12,
                    T13,
                    T14,
                    T15,
                    R]): CFunctionPtr15[T1,
                                        T2,
                                        T3,
                                        T4,
                                        T5,
                                        T6,
                                        T7,
                                        T8,
                                        T9,
                                        T10,
                                        T11,
                                        T12,
                                        T13,
                                        T14,
                                        T15,
                                        R] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 14)

  implicit def fromFunction16[T1,
                              T2,
                              T3,
                              T4,
                              T5,
                              T6,
                              T7,
                              T8,
                              T9,
                              T10,
                              T11,
                              T12,
                              T13,
                              T14,
                              T15,
                              T16,
                              R](
      f: Function16[T1,
                    T2,
                    T3,
                    T4,
                    T5,
                    T6,
                    T7,
                    T8,
                    T9,
                    T10,
                    T11,
                    T12,
                    T13,
                    T14,
                    T15,
                    T16,
                    R]): CFunctionPtr16[T1,
                                        T2,
                                        T3,
                                        T4,
                                        T5,
                                        T6,
                                        T7,
                                        T8,
                                        T9,
                                        T10,
                                        T11,
                                        T12,
                                        T13,
                                        T14,
                                        T15,
                                        T16,
                                        R] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 14)

  implicit def fromFunction17[T1,
                              T2,
                              T3,
                              T4,
                              T5,
                              T6,
                              T7,
                              T8,
                              T9,
                              T10,
                              T11,
                              T12,
                              T13,
                              T14,
                              T15,
                              T16,
                              T17,
                              R](
      f: Function17[T1,
                    T2,
                    T3,
                    T4,
                    T5,
                    T6,
                    T7,
                    T8,
                    T9,
                    T10,
                    T11,
                    T12,
                    T13,
                    T14,
                    T15,
                    T16,
                    T17,
                    R]): CFunctionPtr17[T1,
                                        T2,
                                        T3,
                                        T4,
                                        T5,
                                        T6,
                                        T7,
                                        T8,
                                        T9,
                                        T10,
                                        T11,
                                        T12,
                                        T13,
                                        T14,
                                        T15,
                                        T16,
                                        T17,
                                        R] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 14)

  implicit def fromFunction18[T1,
                              T2,
                              T3,
                              T4,
                              T5,
                              T6,
                              T7,
                              T8,
                              T9,
                              T10,
                              T11,
                              T12,
                              T13,
                              T14,
                              T15,
                              T16,
                              T17,
                              T18,
                              R](
      f: Function18[T1,
                    T2,
                    T3,
                    T4,
                    T5,
                    T6,
                    T7,
                    T8,
                    T9,
                    T10,
                    T11,
                    T12,
                    T13,
                    T14,
                    T15,
                    T16,
                    T17,
                    T18,
                    R]): CFunctionPtr18[T1,
                                        T2,
                                        T3,
                                        T4,
                                        T5,
                                        T6,
                                        T7,
                                        T8,
                                        T9,
                                        T10,
                                        T11,
                                        T12,
                                        T13,
                                        T14,
                                        T15,
                                        T16,
                                        T17,
                                        T18,
                                        R] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 14)

  implicit def fromFunction19[T1,
                              T2,
                              T3,
                              T4,
                              T5,
                              T6,
                              T7,
                              T8,
                              T9,
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
                              R](
      f: Function19[T1,
                    T2,
                    T3,
                    T4,
                    T5,
                    T6,
                    T7,
                    T8,
                    T9,
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
                    R]): CFunctionPtr19[T1,
                                        T2,
                                        T3,
                                        T4,
                                        T5,
                                        T6,
                                        T7,
                                        T8,
                                        T9,
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
                                        R] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 14)

  implicit def fromFunction20[T1,
                              T2,
                              T3,
                              T4,
                              T5,
                              T6,
                              T7,
                              T8,
                              T9,
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
                              R](
      f: Function20[T1,
                    T2,
                    T3,
                    T4,
                    T5,
                    T6,
                    T7,
                    T8,
                    T9,
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
                    R]): CFunctionPtr20[T1,
                                        T2,
                                        T3,
                                        T4,
                                        T5,
                                        T6,
                                        T7,
                                        T8,
                                        T9,
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
                                        R] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 14)

  implicit def fromFunction21[T1,
                              T2,
                              T3,
                              T4,
                              T5,
                              T6,
                              T7,
                              T8,
                              T9,
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
                              R](
      f: Function21[T1,
                    T2,
                    T3,
                    T4,
                    T5,
                    T6,
                    T7,
                    T8,
                    T9,
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
                    R]): CFunctionPtr21[T1,
                                        T2,
                                        T3,
                                        T4,
                                        T5,
                                        T6,
                                        T7,
                                        T8,
                                        T9,
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
                                        R] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 14)

  implicit def fromFunction22[T1,
                              T2,
                              T3,
                              T4,
                              T5,
                              T6,
                              T7,
                              T8,
                              T9,
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
                              T22,
                              R](
      f: Function22[T1,
                    T2,
                    T3,
                    T4,
                    T5,
                    T6,
                    T7,
                    T8,
                    T9,
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
                    T22,
                    R]): CFunctionPtr22[T1,
                                        T2,
                                        T3,
                                        T4,
                                        T5,
                                        T6,
                                        T7,
                                        T8,
                                        T9,
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
                                        T22,
                                        R] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 18)

}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 25)

final abstract class CFunctionPtr0[R] extends CFunctionPtr {
  def apply()(implicit tag1: ClassTag[R]): R = intrinsic
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 25)

final abstract class CFunctionPtr1[T1, R] extends CFunctionPtr {
  def apply(arg1: T1)(implicit tag1: ClassTag[T1], tag2: ClassTag[R]): R =
    intrinsic
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 25)

final abstract class CFunctionPtr2[T1, T2, R] extends CFunctionPtr {
  def apply(arg1: T1, arg2: T2)(implicit tag1: ClassTag[T1],
                                tag2: ClassTag[T2],
                                tag3: ClassTag[R]): R = intrinsic
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 25)

final abstract class CFunctionPtr3[T1, T2, T3, R] extends CFunctionPtr {
  def apply(arg1: T1, arg2: T2, arg3: T3)(implicit tag1: ClassTag[T1],
                                          tag2: ClassTag[T2],
                                          tag3: ClassTag[T3],
                                          tag4: ClassTag[R]): R = intrinsic
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 25)

final abstract class CFunctionPtr4[T1, T2, T3, T4, R] extends CFunctionPtr {
  def apply(arg1: T1, arg2: T2, arg3: T3, arg4: T4)(implicit tag1: ClassTag[T1],
                                                    tag2: ClassTag[T2],
                                                    tag3: ClassTag[T3],
                                                    tag4: ClassTag[T4],
                                                    tag5: ClassTag[R]): R =
    intrinsic
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 25)

final abstract class CFunctionPtr5[T1, T2, T3, T4, T5, R] extends CFunctionPtr {
  def apply(arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5)(
      implicit tag1: ClassTag[T1],
      tag2: ClassTag[T2],
      tag3: ClassTag[T3],
      tag4: ClassTag[T4],
      tag5: ClassTag[T5],
      tag6: ClassTag[R]): R = intrinsic
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 25)

final abstract class CFunctionPtr6[T1, T2, T3, T4, T5, T6, R]
    extends CFunctionPtr {
  def apply(arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6)(
      implicit tag1: ClassTag[T1],
      tag2: ClassTag[T2],
      tag3: ClassTag[T3],
      tag4: ClassTag[T4],
      tag5: ClassTag[T5],
      tag6: ClassTag[T6],
      tag7: ClassTag[R]): R = intrinsic
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 25)

final abstract class CFunctionPtr7[T1, T2, T3, T4, T5, T6, T7, R]
    extends CFunctionPtr {
  def apply(arg1: T1,
            arg2: T2,
            arg3: T3,
            arg4: T4,
            arg5: T5,
            arg6: T6,
            arg7: T7)(implicit tag1: ClassTag[T1],
                      tag2: ClassTag[T2],
                      tag3: ClassTag[T3],
                      tag4: ClassTag[T4],
                      tag5: ClassTag[T5],
                      tag6: ClassTag[T6],
                      tag7: ClassTag[T7],
                      tag8: ClassTag[R]): R = intrinsic
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 25)

final abstract class CFunctionPtr8[T1, T2, T3, T4, T5, T6, T7, T8, R]
    extends CFunctionPtr {
  def apply(arg1: T1,
            arg2: T2,
            arg3: T3,
            arg4: T4,
            arg5: T5,
            arg6: T6,
            arg7: T7,
            arg8: T8)(implicit tag1: ClassTag[T1],
                      tag2: ClassTag[T2],
                      tag3: ClassTag[T3],
                      tag4: ClassTag[T4],
                      tag5: ClassTag[T5],
                      tag6: ClassTag[T6],
                      tag7: ClassTag[T7],
                      tag8: ClassTag[T8],
                      tag9: ClassTag[R]): R = intrinsic
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 25)

final abstract class CFunctionPtr9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R]
    extends CFunctionPtr {
  def apply(arg1: T1,
            arg2: T2,
            arg3: T3,
            arg4: T4,
            arg5: T5,
            arg6: T6,
            arg7: T7,
            arg8: T8,
            arg9: T9)(implicit tag1: ClassTag[T1],
                      tag2: ClassTag[T2],
                      tag3: ClassTag[T3],
                      tag4: ClassTag[T4],
                      tag5: ClassTag[T5],
                      tag6: ClassTag[T6],
                      tag7: ClassTag[T7],
                      tag8: ClassTag[T8],
                      tag9: ClassTag[T9],
                      tag10: ClassTag[R]): R = intrinsic
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 25)

final abstract class CFunctionPtr10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R]
    extends CFunctionPtr {
  def apply(arg1: T1,
            arg2: T2,
            arg3: T3,
            arg4: T4,
            arg5: T5,
            arg6: T6,
            arg7: T7,
            arg8: T8,
            arg9: T9,
            arg10: T10)(implicit tag1: ClassTag[T1],
                        tag2: ClassTag[T2],
                        tag3: ClassTag[T3],
                        tag4: ClassTag[T4],
                        tag5: ClassTag[T5],
                        tag6: ClassTag[T6],
                        tag7: ClassTag[T7],
                        tag8: ClassTag[T8],
                        tag9: ClassTag[T9],
                        tag10: ClassTag[T10],
                        tag11: ClassTag[R]): R = intrinsic
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 25)

final abstract class CFunctionPtr11[
    T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R]
    extends CFunctionPtr {
  def apply(arg1: T1,
            arg2: T2,
            arg3: T3,
            arg4: T4,
            arg5: T5,
            arg6: T6,
            arg7: T7,
            arg8: T8,
            arg9: T9,
            arg10: T10,
            arg11: T11)(implicit tag1: ClassTag[T1],
                        tag2: ClassTag[T2],
                        tag3: ClassTag[T3],
                        tag4: ClassTag[T4],
                        tag5: ClassTag[T5],
                        tag6: ClassTag[T6],
                        tag7: ClassTag[T7],
                        tag8: ClassTag[T8],
                        tag9: ClassTag[T9],
                        tag10: ClassTag[T10],
                        tag11: ClassTag[T11],
                        tag12: ClassTag[R]): R = intrinsic
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 25)

final abstract class CFunctionPtr12[
    T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R]
    extends CFunctionPtr {
  def apply(arg1: T1,
            arg2: T2,
            arg3: T3,
            arg4: T4,
            arg5: T5,
            arg6: T6,
            arg7: T7,
            arg8: T8,
            arg9: T9,
            arg10: T10,
            arg11: T11,
            arg12: T12)(implicit tag1: ClassTag[T1],
                        tag2: ClassTag[T2],
                        tag3: ClassTag[T3],
                        tag4: ClassTag[T4],
                        tag5: ClassTag[T5],
                        tag6: ClassTag[T6],
                        tag7: ClassTag[T7],
                        tag8: ClassTag[T8],
                        tag9: ClassTag[T9],
                        tag10: ClassTag[T10],
                        tag11: ClassTag[T11],
                        tag12: ClassTag[T12],
                        tag13: ClassTag[R]): R = intrinsic
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 25)

final abstract class CFunctionPtr13[
    T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R]
    extends CFunctionPtr {
  def apply(arg1: T1,
            arg2: T2,
            arg3: T3,
            arg4: T4,
            arg5: T5,
            arg6: T6,
            arg7: T7,
            arg8: T8,
            arg9: T9,
            arg10: T10,
            arg11: T11,
            arg12: T12,
            arg13: T13)(implicit tag1: ClassTag[T1],
                        tag2: ClassTag[T2],
                        tag3: ClassTag[T3],
                        tag4: ClassTag[T4],
                        tag5: ClassTag[T5],
                        tag6: ClassTag[T6],
                        tag7: ClassTag[T7],
                        tag8: ClassTag[T8],
                        tag9: ClassTag[T9],
                        tag10: ClassTag[T10],
                        tag11: ClassTag[T11],
                        tag12: ClassTag[T12],
                        tag13: ClassTag[T13],
                        tag14: ClassTag[R]): R = intrinsic
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 25)

final abstract class CFunctionPtr14[
    T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R]
    extends CFunctionPtr {
  def apply(arg1: T1,
            arg2: T2,
            arg3: T3,
            arg4: T4,
            arg5: T5,
            arg6: T6,
            arg7: T7,
            arg8: T8,
            arg9: T9,
            arg10: T10,
            arg11: T11,
            arg12: T12,
            arg13: T13,
            arg14: T14)(implicit tag1: ClassTag[T1],
                        tag2: ClassTag[T2],
                        tag3: ClassTag[T3],
                        tag4: ClassTag[T4],
                        tag5: ClassTag[T5],
                        tag6: ClassTag[T6],
                        tag7: ClassTag[T7],
                        tag8: ClassTag[T8],
                        tag9: ClassTag[T9],
                        tag10: ClassTag[T10],
                        tag11: ClassTag[T11],
                        tag12: ClassTag[T12],
                        tag13: ClassTag[T13],
                        tag14: ClassTag[T14],
                        tag15: ClassTag[R]): R = intrinsic
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 25)

final abstract class CFunctionPtr15[
    T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R]
    extends CFunctionPtr {
  def apply(arg1: T1,
            arg2: T2,
            arg3: T3,
            arg4: T4,
            arg5: T5,
            arg6: T6,
            arg7: T7,
            arg8: T8,
            arg9: T9,
            arg10: T10,
            arg11: T11,
            arg12: T12,
            arg13: T13,
            arg14: T14,
            arg15: T15)(implicit tag1: ClassTag[T1],
                        tag2: ClassTag[T2],
                        tag3: ClassTag[T3],
                        tag4: ClassTag[T4],
                        tag5: ClassTag[T5],
                        tag6: ClassTag[T6],
                        tag7: ClassTag[T7],
                        tag8: ClassTag[T8],
                        tag9: ClassTag[T9],
                        tag10: ClassTag[T10],
                        tag11: ClassTag[T11],
                        tag12: ClassTag[T12],
                        tag13: ClassTag[T13],
                        tag14: ClassTag[T14],
                        tag15: ClassTag[T15],
                        tag16: ClassTag[R]): R = intrinsic
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 25)

final abstract class CFunctionPtr16[
    T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R]
    extends CFunctionPtr {
  def apply(arg1: T1,
            arg2: T2,
            arg3: T3,
            arg4: T4,
            arg5: T5,
            arg6: T6,
            arg7: T7,
            arg8: T8,
            arg9: T9,
            arg10: T10,
            arg11: T11,
            arg12: T12,
            arg13: T13,
            arg14: T14,
            arg15: T15,
            arg16: T16)(implicit tag1: ClassTag[T1],
                        tag2: ClassTag[T2],
                        tag3: ClassTag[T3],
                        tag4: ClassTag[T4],
                        tag5: ClassTag[T5],
                        tag6: ClassTag[T6],
                        tag7: ClassTag[T7],
                        tag8: ClassTag[T8],
                        tag9: ClassTag[T9],
                        tag10: ClassTag[T10],
                        tag11: ClassTag[T11],
                        tag12: ClassTag[T12],
                        tag13: ClassTag[T13],
                        tag14: ClassTag[T14],
                        tag15: ClassTag[T15],
                        tag16: ClassTag[T16],
                        tag17: ClassTag[R]): R = intrinsic
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 25)

final abstract class CFunctionPtr17[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    R]
    extends CFunctionPtr {
  def apply(arg1: T1,
            arg2: T2,
            arg3: T3,
            arg4: T4,
            arg5: T5,
            arg6: T6,
            arg7: T7,
            arg8: T8,
            arg9: T9,
            arg10: T10,
            arg11: T11,
            arg12: T12,
            arg13: T13,
            arg14: T14,
            arg15: T15,
            arg16: T16,
            arg17: T17)(implicit tag1: ClassTag[T1],
                        tag2: ClassTag[T2],
                        tag3: ClassTag[T3],
                        tag4: ClassTag[T4],
                        tag5: ClassTag[T5],
                        tag6: ClassTag[T6],
                        tag7: ClassTag[T7],
                        tag8: ClassTag[T8],
                        tag9: ClassTag[T9],
                        tag10: ClassTag[T10],
                        tag11: ClassTag[T11],
                        tag12: ClassTag[T12],
                        tag13: ClassTag[T13],
                        tag14: ClassTag[T14],
                        tag15: ClassTag[T15],
                        tag16: ClassTag[T16],
                        tag17: ClassTag[T17],
                        tag18: ClassTag[R]): R = intrinsic
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 25)

final abstract class CFunctionPtr18[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
                                    T10,
                                    T11,
                                    T12,
                                    T13,
                                    T14,
                                    T15,
                                    T16,
                                    T17,
                                    T18,
                                    R]
    extends CFunctionPtr {
  def apply(arg1: T1,
            arg2: T2,
            arg3: T3,
            arg4: T4,
            arg5: T5,
            arg6: T6,
            arg7: T7,
            arg8: T8,
            arg9: T9,
            arg10: T10,
            arg11: T11,
            arg12: T12,
            arg13: T13,
            arg14: T14,
            arg15: T15,
            arg16: T16,
            arg17: T17,
            arg18: T18)(implicit tag1: ClassTag[T1],
                        tag2: ClassTag[T2],
                        tag3: ClassTag[T3],
                        tag4: ClassTag[T4],
                        tag5: ClassTag[T5],
                        tag6: ClassTag[T6],
                        tag7: ClassTag[T7],
                        tag8: ClassTag[T8],
                        tag9: ClassTag[T9],
                        tag10: ClassTag[T10],
                        tag11: ClassTag[T11],
                        tag12: ClassTag[T12],
                        tag13: ClassTag[T13],
                        tag14: ClassTag[T14],
                        tag15: ClassTag[T15],
                        tag16: ClassTag[T16],
                        tag17: ClassTag[T17],
                        tag18: ClassTag[T18],
                        tag19: ClassTag[R]): R = intrinsic
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 25)

final abstract class CFunctionPtr19[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
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
                                    R]
    extends CFunctionPtr {
  def apply(arg1: T1,
            arg2: T2,
            arg3: T3,
            arg4: T4,
            arg5: T5,
            arg6: T6,
            arg7: T7,
            arg8: T8,
            arg9: T9,
            arg10: T10,
            arg11: T11,
            arg12: T12,
            arg13: T13,
            arg14: T14,
            arg15: T15,
            arg16: T16,
            arg17: T17,
            arg18: T18,
            arg19: T19)(implicit tag1: ClassTag[T1],
                        tag2: ClassTag[T2],
                        tag3: ClassTag[T3],
                        tag4: ClassTag[T4],
                        tag5: ClassTag[T5],
                        tag6: ClassTag[T6],
                        tag7: ClassTag[T7],
                        tag8: ClassTag[T8],
                        tag9: ClassTag[T9],
                        tag10: ClassTag[T10],
                        tag11: ClassTag[T11],
                        tag12: ClassTag[T12],
                        tag13: ClassTag[T13],
                        tag14: ClassTag[T14],
                        tag15: ClassTag[T15],
                        tag16: ClassTag[T16],
                        tag17: ClassTag[T17],
                        tag18: ClassTag[T18],
                        tag19: ClassTag[T19],
                        tag20: ClassTag[R]): R = intrinsic
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 25)

final abstract class CFunctionPtr20[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
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
                                    R]
    extends CFunctionPtr {
  def apply(arg1: T1,
            arg2: T2,
            arg3: T3,
            arg4: T4,
            arg5: T5,
            arg6: T6,
            arg7: T7,
            arg8: T8,
            arg9: T9,
            arg10: T10,
            arg11: T11,
            arg12: T12,
            arg13: T13,
            arg14: T14,
            arg15: T15,
            arg16: T16,
            arg17: T17,
            arg18: T18,
            arg19: T19,
            arg20: T20)(implicit tag1: ClassTag[T1],
                        tag2: ClassTag[T2],
                        tag3: ClassTag[T3],
                        tag4: ClassTag[T4],
                        tag5: ClassTag[T5],
                        tag6: ClassTag[T6],
                        tag7: ClassTag[T7],
                        tag8: ClassTag[T8],
                        tag9: ClassTag[T9],
                        tag10: ClassTag[T10],
                        tag11: ClassTag[T11],
                        tag12: ClassTag[T12],
                        tag13: ClassTag[T13],
                        tag14: ClassTag[T14],
                        tag15: ClassTag[T15],
                        tag16: ClassTag[T16],
                        tag17: ClassTag[T17],
                        tag18: ClassTag[T18],
                        tag19: ClassTag[T19],
                        tag20: ClassTag[T20],
                        tag21: ClassTag[R]): R = intrinsic
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 25)

final abstract class CFunctionPtr21[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
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
                                    R]
    extends CFunctionPtr {
  def apply(arg1: T1,
            arg2: T2,
            arg3: T3,
            arg4: T4,
            arg5: T5,
            arg6: T6,
            arg7: T7,
            arg8: T8,
            arg9: T9,
            arg10: T10,
            arg11: T11,
            arg12: T12,
            arg13: T13,
            arg14: T14,
            arg15: T15,
            arg16: T16,
            arg17: T17,
            arg18: T18,
            arg19: T19,
            arg20: T20,
            arg21: T21)(implicit tag1: ClassTag[T1],
                        tag2: ClassTag[T2],
                        tag3: ClassTag[T3],
                        tag4: ClassTag[T4],
                        tag5: ClassTag[T5],
                        tag6: ClassTag[T6],
                        tag7: ClassTag[T7],
                        tag8: ClassTag[T8],
                        tag9: ClassTag[T9],
                        tag10: ClassTag[T10],
                        tag11: ClassTag[T11],
                        tag12: ClassTag[T12],
                        tag13: ClassTag[T13],
                        tag14: ClassTag[T14],
                        tag15: ClassTag[T15],
                        tag16: ClassTag[T16],
                        tag17: ClassTag[T17],
                        tag18: ClassTag[T18],
                        tag19: ClassTag[T19],
                        tag20: ClassTag[T20],
                        tag21: ClassTag[T21],
                        tag22: ClassTag[R]): R = intrinsic
}

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/CFunctionPtr.scala.gyb", line: 25)

final abstract class CFunctionPtr22[T1,
                                    T2,
                                    T3,
                                    T4,
                                    T5,
                                    T6,
                                    T7,
                                    T8,
                                    T9,
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
                                    T22,
                                    R]
    extends CFunctionPtr {
  def apply(arg1: T1,
            arg2: T2,
            arg3: T3,
            arg4: T4,
            arg5: T5,
            arg6: T6,
            arg7: T7,
            arg8: T8,
            arg9: T9,
            arg10: T10,
            arg11: T11,
            arg12: T12,
            arg13: T13,
            arg14: T14,
            arg15: T15,
            arg16: T16,
            arg17: T17,
            arg18: T18,
            arg19: T19,
            arg20: T20,
            arg21: T21,
            arg22: T22)(implicit tag1: ClassTag[T1],
                        tag2: ClassTag[T2],
                        tag3: ClassTag[T3],
                        tag4: ClassTag[T4],
                        tag5: ClassTag[T5],
                        tag6: ClassTag[T6],
                        tag7: ClassTag[T7],
                        tag8: ClassTag[T8],
                        tag9: ClassTag[T9],
                        tag10: ClassTag[T10],
                        tag11: ClassTag[T11],
                        tag12: ClassTag[T12],
                        tag13: ClassTag[T13],
                        tag14: ClassTag[T14],
                        tag15: ClassTag[T15],
                        tag16: ClassTag[T16],
                        tag17: ClassTag[T17],
                        tag18: ClassTag[T18],
                        tag19: ClassTag[T19],
                        tag20: ClassTag[T20],
                        tag21: ClassTag[T21],
                        tag22: ClassTag[T22],
                        tag23: ClassTag[R]): R = intrinsic
}
