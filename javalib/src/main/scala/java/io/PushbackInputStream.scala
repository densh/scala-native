package java.io

class PushbackInputStream(in: InputStream, size: scala.Int)
    extends FilterInputStream(in) {
  def this(in: InputStream) = this(in, 4096)
  def unread(bytes: Array[Byte], off: scala.Int, len: scala.Int): Unit = ???
}
