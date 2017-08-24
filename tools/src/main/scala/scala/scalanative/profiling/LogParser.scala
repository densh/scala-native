package scala.scalanative
package profiling

import java.io.{
  DataInputStream,
  ByteArrayInputStream,
  ByteArrayOutputStream,
  InputStream,
  File,
  EOFException
}
import java.nio.file.Files
import java.nio.{ByteOrder, ByteBuffer}
import java.util.zip.Inflater

final case class Event(id: Int, time: Int)

object LogParser {
  private def profileFiles(base: File): Seq[File] = {
    import scala.util.matching.Regex
    val profile = """profile\.(\d+)""".r
    base.listFiles
      .map(f => (f, f.getName))
      .collect {
        case (f, profile(n)) => (f, n.toInt)
      }
      .sortBy(_._2)
      .map(_._1)
      .toSeq
  }

  private def decompress(f: File): ByteBuffer = {
    val compressedBytes = Files.readAllBytes(f.toPath)
    val decompresser    = new Inflater()
    val decompressedOut = new ByteArrayOutputStream()
    val buffer          = new Array[Byte](1024 * 1024)

    decompresser.setInput(compressedBytes)
    while (!decompresser.finished()) {
      val count = decompresser.inflate(buffer)
      decompressedOut.write(buffer, 0, count)
    }

    val buf = ByteBuffer.wrap(decompressedOut.toByteArray)
    buf.order(ByteOrder.LITTLE_ENDIAN)
    buf
  }

  def apply(baseDirectory: File): Seq[() => ByteBuffer] = {
    assert(baseDirectory.isDirectory)

    profileFiles(baseDirectory).map { file =>
      () => decompress(file)
    }
  }
}
