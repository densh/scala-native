package java.security

class MessageDigest extends java.security.MessageDigestSpi {
  def digest(): Array[Byte]                                               = ???
  def update(value: scala.Byte): Unit                                     = ???
  def update(bytes: Array[Byte], offset: scala.Int, len: scala.Int): Unit = ???
  def update(bytes: Array[Byte]): Unit                                    = ???
}

object MessageDigest {
  def getInstance(name: String): java.security.MessageDigest           = ???
  def isEqual(bytes1: Array[Byte], bytes2: Array[Byte]): scala.Boolean = ???
}
