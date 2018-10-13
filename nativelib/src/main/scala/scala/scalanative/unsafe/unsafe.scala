package scala.scalanative
package unsafe

/** Annotation used to mark unsafe methods. */
final class unsafe extends scala.annotation.StaticAnnotation

object unsafe {

  /** Unsafe block literal. */
  @inline def apply(f: () => Unit): Unit = f()
}
