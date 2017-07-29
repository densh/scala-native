package scala.scalanative
package nscplugin

import java.nio.file._
import java.nio.channels._
import scala.tools.nsc._
import scala.tools.nsc.io.AbstractFile
import scalanative.nir.serialization.{serializeText, serializeBinary, Stats}
import scalanative.io.withScratchBuffer
import scalanative.io.VirtualDirectory

trait NirFiles { self: NirCodeGen =>
  import global._

  def getPathFor(cunit: CompilationUnit, sym: Symbol): Path = {
    val baseDir: AbstractFile =
      settings.outputDirs.outputDirFor(cunit.source.file)

    val id        = genTypeName(sym).id
    val pathParts = id.split("[./]")
    val dir       = (baseDir /: pathParts.init)(_.subdirectoryNamed(_))

    var filename = pathParts.last
    val file     = dir fileNamed (filename + ".nir")

    Paths.get(file.file.getAbsolutePath)
  }

  def genIRFiles(files: Seq[(Path, Seq[nir.Defn])]): Unit =
    Stats.time("gen files") {
      files.foreach {
        case (path, defns) =>
          val channel = FileChannel.open(path,
                                         StandardOpenOption.CREATE,
                                         StandardOpenOption.WRITE,
                                         StandardOpenOption.TRUNCATE_EXISTING)
          try serializeBinary(defns, channel)
          finally channel.close()
      }
    }
}
