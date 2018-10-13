// ###sourceLocation(file: "/Users/Denys/.src/native/nativelib/src/main/scala/scala/scalanative/native/Ptr.scala.gyb", line: 1)
package scala.scalanative
package unsafe

import scala.reflect.ClassTag
import scalanative.runtime.intrinsic

/** The C `const T *` pointer. */
final abstract class Ptr[T] {

  /** Dereference a pointer. */
  @unsafe def unary_!(implicit ct: ClassTag[T]): T = intrinsic

  /** Store a value to the address pointed at by a pointer. */
  @unsafe def `unary_!_=`(value: T)(implicit ct: ClassTag[T]): Unit = intrinsic

  /** Compute a derived pointer by adding given offset. */
  def +(offset: Word)(implicit ct: ClassTag[T]): Ptr[T] = intrinsic

  /** Compute a derived pointer by subtracting given offset. */
  def -(offset: Word)(implicit ct: ClassTag[T]): Ptr[T] = intrinsic

  /** Compute the offset between two pointers. */
  def -(ptr: Ptr[T])(implicit ct: ClassTag[T]): CPtrDiff = intrinsic

  /** Read a value at given offset. Equivalent to !(offset + word). */
  @unsafe def apply(offset: Word)(implicit ct: ClassTag[T]): T = intrinsic

  /** Store a value to given offset. Equivalent to !(offset + word) = value. */
  @unsafe def update(offset: Word, value: T)(implicit ct: ClassTag[T]): T =
    intrinsic
}

object Ptr {

  /** Implicitly dereference values. */
  @unsafe implicit def unwrap[T](ptr: Ptr[T])(implicit ct: ClassTag[T]): T =
    intrinsic
}
