package scala.scalanative
package linker

import nir.{Global, Dep, Attr, Defn}
import nir.serialization.BinaryReader
import java.nio.file.FileSystems
import scalanative.io.VirtualDirectory
import scalanative.util.{Stats, Scope}

sealed trait ClassPath {

  /** Check if given global is present in this classpath. */
  def contains(name: Global): Boolean

  /** Touch given global and all of its inner dependencies. */
  def touch(name: Global): Option[(Seq[Global], Seq[Dep])]

  /** Binary reader for given global. */
  def reader(name: Global): Option[BinaryReader]

  /** All globals defined in this classpath.*/
  def globals: Set[Global]
}

object ClassPath {

  /** Create classpath based on the virtual directory. */
  def apply(directory: VirtualDirectory): ClassPath =
    new Impl(directory)

  private final class Impl(directory: VirtualDirectory) extends ClassPath {
    private val entries: Map[Global, BinaryReader] = {
      directory.files.par
        .filter(_.toString.endsWith(".nir"))
        .map { file =>
          val name = Global.Top(io.packageNameFromPath(file))

          (name -> new BinaryReader(
            Stats.time("linker.io")(directory.read(file))
          ))
        }
        .seq
        .toMap
    }

    def contains(name: Global) =
      entries.contains(name.top)

    def touch(name: Global): Option[(Seq[Global], Seq[Dep])] =
      entries.get(name.top).flatMap { deserializer =>
        deserializer.touch(name)
      }

    def reader(name: Global): Option[BinaryReader] =
      entries.get(name.top)

    def globals: Set[Global] =
      entries.values.flatMap(_.globals).toSet
  }
}
