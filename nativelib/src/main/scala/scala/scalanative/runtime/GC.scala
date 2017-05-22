package scala.scalanative
package runtime

import native._

/**
 * The Boehm GC conservative garbage collector
 *
 * @see [[http://hboehm.info/gc/gcinterface.html C Interface]]
 */
@extern
object GC {
  @name("scalanative_alloc")
  def alloc(ty: Ptr[Type], size: CSize): Ptr[Byte] = extern
  @name("scalanative_collect")
  def collect(): Unit = extern
  // TODO: remove me
  @name("GC_malloc")
  def malloc(size: CSize): Ptr[Byte] = extern
  // TODO: remove me
  @name("GC_malloc_atomic")
  def malloc_atomic(size: CSize): Ptr[Byte] = extern
}
