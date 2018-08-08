package scala.scalanative
package linker

import java.nio.file.Path
import scala.collection.mutable
import scalanative.io.VirtualDirectory
import scalanative.util.{Scope, Resource}

sealed abstract class Cache {
  def classpath(path: Path): ClassPath

  def clear(): Unit
}

object Cache {
  def empty: Cache = new Impl

  private final class Impl extends Cache with Scope.Stack {
    private val cached = mutable.Map.empty[Path, ClassPath]

    def classpath(path: Path): ClassPath =
      cached.get(path).getOrElse {
        val cp = ClassPath(VirtualDirectory.real(path)(this))
        cached(path) = cp
        cp
      }

    def clear(): Unit = {
      super.close()
      cached.clear()
    }
  }
}
