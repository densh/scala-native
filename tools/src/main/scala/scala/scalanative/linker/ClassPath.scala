package scala.scalanative
package linker

import scala.collection.mutable
import nir.{Global, Dep, Attr, Defn}
import nir.serialization.deserializeBinary
import java.nio.file.{FileSystems, Path}
import scalanative.io.VirtualDirectory
import scalanative.util.Scope
import scalanative.nir03

sealed trait ClassPath {

  /** Check if given global is present in this classpath. */
  private[scalanative] def contains(name: Global): Boolean

  /** Load given global and info about its dependencies. */
  private[scalanative] def load(name: Global): Option[Seq[Defn]]
}

object ClassPath {

  /** Create classpath based on the directory. */
  def apply(directory: Path): ClassPath =
    new Impl(VirtualDirectory.local(directory))

  /** Create classpath based on the virtual directory. */
  private[scalanative] def apply(directory: VirtualDirectory): ClassPath =
    new Impl(directory)

  private final class Impl(directory: VirtualDirectory) extends ClassPath {
    private val files =
      directory.files
        .filter(_.toString.endsWith(".nir"))
        .map { file =>
          val name = Global.Top(io.packageNameFromPath(file))

          name -> file
        }
        .toMap

    private val cache =
      mutable.Map.empty[Global, Option[Seq[Defn]]]

    def contains(name: Global) =
      files.contains(name.top)

    def load(name: Global): Option[Seq[Defn]] =
      cache.getOrElseUpdate(
        name, {
          files.get(name.top).map {
            file =>
              val data = directory.read(file)
              try {
                deserializeBinary(data)
              } catch {
                case _: AssertionError =>
                  val deserializer = nir03.serialization.deserializeBinary(data)
                  val defns = deserializer.globals.toSeq.map { g =>
                    val (_, _, _, defn) = deserializer.deserialize(g).get
                    defn
                  }
                  nir03.Upgrade(defns)
              }
          }
        }
      )
  }
}
