package scala.scalanative
package linker

import scala.collection.mutable
import java.nio.file.{FileSystems, Path}
import scalanative.nir.{Global, Dep, Attr, Defn}
import scalanative.nir.serialization.BinaryDeserializer
import scalanative.io.VirtualDirectory
import scalanative.util.Scope

sealed trait ClassPath {

  /** Check if given global is present in this classpath. */
  private[scalanative] def contains(name: Global): Boolean

  /** Load given global and info about its dependencies. */
  private[scalanative] def load(
      name: Global): Option[(Seq[Dep], Seq[Attr.Link], Seq[String], Defn)]

  /** Load all globals */
  private[scalanative] def globals: Set[Global]
}

object ClassPath {

  /** Create classpath based on the directory. */
  def apply(directory: Path): ClassPath =
    new Impl(VirtualDirectory.local(directory))

  /** Create classpath based on the virtual directory. */
  private[scalanative] def apply(directory: VirtualDirectory): ClassPath =
    new Impl(directory)

  private sealed class Impl(directory: VirtualDirectory) extends ClassPath {
    private val entries: Map[Global, BinaryDeserializer] = {
      directory.files
        .filter(_.toString.endsWith(".nir"))
        .map { file =>
          val name = Global.stripImplClassTrailingDollar(
            Global.Top(io.packageNameFromPath(file)))

          (name -> new BinaryDeserializer(directory.read(file)))
        }
        .toMap
    }

    type Load = Option[(Seq[Dep], Seq[Attr.Link], Seq[String], Defn)]

    private val cache = mutable.Map.empty[Global, Load]

    def contains(name: Global) =
      entries.contains(name.top)

    def load(name: Global): Load =
      cache.get(name).getOrElse {
        val res = entries.get(name.top).flatMap { deserializer =>
          deserializer.deserialize(name)
        }
        cache(name) = res
        res
      }

    def globals: Set[Global] = entries.values.flatMap(_.globals).toSet
  }
}
