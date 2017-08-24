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

/** A class that can parse the logs produced by profiling. */
class LogParser(buf: ByteBuffer) {
  import LogParser._

  /** Reads the next event from the stream, if any. Throws an exception at EOF. */
  private def nextEventUnsafe(): Event =
    Event(buf.getInt(), buf.getInt())

  /** Optionally reads an event from the stream, if any. */
  def nextEvent(): Option[Event] =
    try Some(nextEventUnsafe())
    catch { case _: Exception => None }
}

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
    val buffer          = new Array[Byte](1024)

    decompresser.setInput(compressedBytes)
    while (!decompresser.finished()) {
      val count = decompresser.inflate(buffer)
      decompressedOut.write(buffer, 0, count)
    }

    val buf = ByteBuffer.wrap(decompressedOut.toByteArray)
    buf.order(ByteOrder.LITTLE_ENDIAN)
    buf
  }

  def apply(baseDirectory: File): collection.parallel.ParSeq[Iterator[Event]] = {
    assert(baseDirectory.isDirectory)

    profileFiles(baseDirectory).par.map { file =>
      new Iterator[Event] {
        private val parser: LogParser = new LogParser(decompress(file))
        private var lookahead: Option[Event] = parser.nextEvent()

        override def hasNext(): Boolean = lookahead.nonEmpty
        override def next(): Event = {
          val result = lookahead.get
          lookahead = parser.nextEvent()
          result
        }
      }
    }
  }
}
