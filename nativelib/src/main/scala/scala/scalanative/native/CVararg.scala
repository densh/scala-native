package scala.scalanative
package native

import scala.reflect.ClassTag
import scala.language.implicitConversions
import runtime.intrinsic

/** Type of a C-style vararg in an extern method. */
final class CVararg(val value: Any)

object CVararg {
  implicit def apply[T](value: T): CVararg = CVararg(value)
}
