package scala.scalanative
package native

import scala.annotation.implicitNotFound
import scalanative.runtime.{RawPtr, libc}

/** Zone allocator that automatically frees allocations whenever
 *  syntactic boundary of the zone is over.
 */
@implicitNotFound("Given method requires an implicit zone.")
trait Zone {

  /** Allocates memory of given size. */
  def alloc(size: CSize): Ptr[Byte]
}

object Zone {

  /** Run given function with a fresh zone and destroy it afterwards. */
  final def apply[T](f: Zone => T): T = {
    val zone = new ZoneImpl
    try f(zone)
    finally zone.close()
  }

  /** Minimalistic zone allocator that uses underlying
   *  system allocator for allocations, and frees all of
   *  the allocations once the zone is closed.
   */
  private class ZoneImpl extends Zone {
    final class Node(val head: RawPtr, val tail: Node)

    private var node: Node = null

    final def alloc(size: CSize): Ptr[Byte] = {
      val rawptr = libc.malloc(size)
      node = new Node(rawptr, node)
      Ptr.fromRaw[Byte](rawptr)
    }

    final def close(): Unit = {
      while (node != null) {
        libc.free(node.head)
        node = node.tail
      }
    }
  }
}
