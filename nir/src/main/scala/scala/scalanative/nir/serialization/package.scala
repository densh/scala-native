package scala.scalanative
package nir

import java.nio._
import java.nio.file.{StandardOpenOption => OpenOpt, _}
import java.nio.channels._

package object serialization {
  def serializeText(defns: Seq[Defn], buffer: ByteBuffer): Unit = {
    val builder = Show.newBuilder
    builder.defns_(defns)
    buffer.put(builder.toString.getBytes)
  }

  def serializeBinary(defns: Seq[Defn], channel: ByteChannel): Unit = {
    val writer = new BinaryWriter
    writer.put(defns)
    writer.write(channel)
  }

  def deserializeBinary(buffer: ByteBuffer): BinaryReader =
    new BinaryReader(buffer)
}
