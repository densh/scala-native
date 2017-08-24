package scala.scalanative
package profiling

import java.io.{
  ByteArrayInputStream,
  ByteArrayOutputStream,
  InputStream,
  File,
  EOFException
}
import java.nio.file.Files
import java.util.zip.Inflater

final case class Event(id: Int, time: Int)

/** A class that can parse the logs produced by profiling. */
class LogParser(stream: InputStream) {
  import LogParser._

  private val buffer    = new Array[Byte](1024)
  private var pos       = 0
  private var available = 0

  /** Read the next byte from the stream. */
  private def next(): Byte = {
    if (available == 0) {
      available = stream.read(buffer, 0, buffer.length)
      pos = 0
    }

    if (available <= 0)
      throw new EOFException()

    val result = buffer(pos)
    pos = pos + 1
    available = available - 1
    result
  }

  private def nextLong(): Long = {
    var value = 0L
    var i     = 0
    while (i < 8) {
      value += (next().toLong & 0xffL) << (8 * i)
      i += 1
    }
    value
  }

  /** Reads an LEB128 encoded unsigned int from the stream. */
  private def nextLEB128(): Int = {
    var result: Int = 0
    var byte: Byte  = 0
    var shift: Int  = 0
    do {
      byte = next()
      result |= (byte & 0x7F) << shift
      shift += 7
    } while ((byte & 0x80) != 0)

    result
  }

  /** Reads the next event from the stream, if any. Throws an exception at EOF. */
  private def nextEventUnsafe(): Event =
    Event(nextLEB128(), nextLEB128())

  /** Optionally reads an event from the stream, if any. */
  def nextEvent(): Option[Event] =
    try Some(nextEventUnsafe())
    catch { case _: EOFException => None }
}

object LogParser {

  /** Collect and sort all the parts of the profiling info from `base`. */
  private def profileParts(base: File): Seq[File] = {
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

  /** Returns an iterator containing all the events in `file`. */
  def apply(baseDirectory: File): Iterator[Event] = {
    if (!baseDirectory.isDirectory) Iterator.empty
    else {
      val stream = new ZippedPartsInputStream(profileParts(baseDirectory))
      val parser = new LogParser(stream)

      new Iterator[Event] {
        private var lookahead: Option[Event] = parser.nextEvent()

        override def hasNext(): Boolean = lookahead.isDefined
        override def next(): Event = {
          val result = lookahead.get
          lookahead = parser.nextEvent()
          result
        }
      }
    }
  }
}

/** An input stream composed of multiple files whose contents are zipped. */
private class ZippedPartsInputStream(private var remainingFiles: Seq[File])
    extends InputStream {
  private var currentStream: InputStream = {
    new ByteArrayInputStream(Array.empty)
  }
  private def decompress(f: File): InputStream = {
    val compressedBytes = Files.readAllBytes(f.toPath)
    val decompresser    = new Inflater()
    val decompressedOut = new ByteArrayOutputStream()
    val buffer          = new Array[Byte](1024)

    decompresser.setInput(compressedBytes)
    while (!decompresser.finished()) {
      val count = decompresser.inflate(buffer)
      decompressedOut.write(buffer, 0, count)
    }

    new ByteArrayInputStream(decompressedOut.toByteArray)
  }
  override def read(): Int =
    currentStream.read() match {
      case -1 if remainingFiles.nonEmpty =>
        currentStream = decompress(remainingFiles.head)
        remainingFiles = remainingFiles.tail
        read()
      case value =>
        value
    }
}
