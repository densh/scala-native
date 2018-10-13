// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/unsafe/Arr.scala.gyb", line: 1)
package scala.scalanative
package unsafe

import scala.reflect.ClassTag
import scalanative.runtime.intrinsic
import scalanative.unsafe.Nat._

/** Fixed-size array value. */
final abstract class Arr[T, N <: Nat] {

  /** Read an element from the array. */
  @unsafe def apply(i: Int)(implicit ct: ClassTag[T]): T = intrinsic

  /** Store an element to the array. */
  @unsafe def update(i: Int, value: T)(implicit ct: ClassTag[T]): T = intrinsic
}

object Arr {

  /** Empty array literal. */
  def empty[T, N <: Nat]: Arr[T, N] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/unsafe/Arr.scala.gyb", line: 25)

  /** Array literal of length 0. */
  def apply[T]()(implicit ct: ClassTag[T]): Arr[T, _0] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/unsafe/Arr.scala.gyb", line: 25)

  /** Array literal of length 1. */
  def apply[T](arg1: T)(implicit ct: ClassTag[T]): Arr[T, _1] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/unsafe/Arr.scala.gyb", line: 25)

  /** Array literal of length 2. */
  def apply[T](arg1: T, arg2: T)(implicit ct: ClassTag[T]): Arr[T, _2] =
    intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/unsafe/Arr.scala.gyb", line: 25)

  /** Array literal of length 3. */
  def apply[T](arg1: T, arg2: T, arg3: T)(
      implicit ct: ClassTag[T]): Arr[T, _3] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/unsafe/Arr.scala.gyb", line: 25)

  /** Array literal of length 4. */
  def apply[T](arg1: T, arg2: T, arg3: T, arg4: T)(
      implicit ct: ClassTag[T]): Arr[T, _4] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/unsafe/Arr.scala.gyb", line: 25)

  /** Array literal of length 5. */
  def apply[T](arg1: T, arg2: T, arg3: T, arg4: T, arg5: T)(
      implicit ct: ClassTag[T]): Arr[T, _5] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/unsafe/Arr.scala.gyb", line: 25)

  /** Array literal of length 6. */
  def apply[T](arg1: T, arg2: T, arg3: T, arg4: T, arg5: T, arg6: T)(
      implicit ct: ClassTag[T]): Arr[T, _6] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/unsafe/Arr.scala.gyb", line: 25)

  /** Array literal of length 7. */
  def apply[T](arg1: T, arg2: T, arg3: T, arg4: T, arg5: T, arg6: T, arg7: T)(
      implicit ct: ClassTag[T]): Arr[T, _7] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/unsafe/Arr.scala.gyb", line: 25)

  /** Array literal of length 8. */
  def apply[T](arg1: T,
               arg2: T,
               arg3: T,
               arg4: T,
               arg5: T,
               arg6: T,
               arg7: T,
               arg8: T)(implicit ct: ClassTag[T]): Arr[T, _8] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/unsafe/Arr.scala.gyb", line: 25)

  /** Array literal of length 9. */
  def apply[T](arg1: T,
               arg2: T,
               arg3: T,
               arg4: T,
               arg5: T,
               arg6: T,
               arg7: T,
               arg8: T,
               arg9: T)(implicit ct: ClassTag[T]): Arr[T, _9] = intrinsic

// ###sourceLocation(file: "/home/denys/.src/native/nativelib/src/main/scala/scala/scalanative/unsafe/Arr.scala.gyb", line: 30)

}
