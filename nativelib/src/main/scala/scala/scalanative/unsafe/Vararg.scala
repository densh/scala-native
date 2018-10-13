package scala.scalanative
package unsafe

import scala.language.implicitConversions
import scala.reflect.ClassTag
import scalanative.runtime.intrinsic

/** Type of a C-style vararg in an extern method. */
final abstract class Vararg

object Vararg {

  /** Automatically wrap a value into vararg. */
  implicit def apply[T](value: T)(implicit ct: ClassTag[T]): Vararg =
    runtime.intrinsic
}
