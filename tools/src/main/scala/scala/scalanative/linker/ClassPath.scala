package scala.scalanative
package linker

import nir.{Global, Dep, Attr, Defn}
import nir.serialization.BinaryReader
import java.nio.file.FileSystems
import scalanative.nir.serialization.Stats
import scalanative.io.VirtualDirectory
import scalanative.util.Scope

sealed trait ClassPath {

  /** Check if given global is present in this classpath. */
  def contains(name: Global): Boolean

  /** Load dependencies for given global. */
  def deps(name: Global): Option[Seq[Dep]]

  /** Binary reader for given global. */
  def reader(name: Global): Option[BinaryReader]

  /** Load all globals */
  def globals: Set[Global]
}

object ClassPath {

  /** Create classpath based on the virtual directory. */
  def apply(directory: VirtualDirectory): ClassPath =
    new Impl(directory)

  private final class Impl(directory: VirtualDirectory) extends ClassPath {
    private val entries: Map[Global, BinaryReader] = {
      directory.files
        .filter(_.toString.endsWith(".nir"))
        .map { file =>
          val name = Global.Top(io.packageNameFromPath(file))

          (name -> new BinaryReader(
            Stats.time("read i/o")(directory.read(file))
          ))
        }
        .toMap
    }

    def contains(name: Global) =
      entries.contains(name.top)

    def deps(name: Global): Option[Seq[Dep]] =
      entries.get(name.top).flatMap { deserializer =>
        deserializer.deps(name)
      }

    def reader(name: Global): Option[BinaryReader] =
      entries.get(name.top)

    def globals: Set[Global] =
      entries.values.flatMap(_.globals).toSet
  }
}
