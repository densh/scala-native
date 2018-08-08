package scala.scalanative
package build

sealed abstract class Cache {
  private[scalanative] def link: linker.Cache

  def clear(): Unit
}

object Cache {
  def empty: Cache =
    new Impl(link = linker.Cache.empty)

  private final class Impl(val link: linker.Cache) extends Cache {
    def clear(): Unit = {
      link.clear()
    }
  }
}
