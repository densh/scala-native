package scala.scalanative.runtime

import scalanative.native._, stdlib.free, string.memcpy
import java.nio.charset.Charset

object RE2Utils {
  def fromRE2String(restr: Ptr[RE2.string_t]): String = {
    val bytes = ByteArray.alloc(restr.length)

    memcpy(bytes.at(0), restr.data, restr.length)

    new String(bytes.asInstanceOf[scala.Array[Byte]], Charset.forName("UTF-8"))
  }

  def toRE2String(str: String, restr: Ptr[RE2.string_t]): Unit = {
    restr.data = toCString(str, Charset.forName("UTF-8"))
    // TODO: this isn't correct, str.length != restr.length
    restr.length = str.length
  }

  def freeRE2String(ptr: Ptr[RE2.string_t]): Unit =
    free(ptr.data.cast[Ptr[Byte]])

  implicit class RE2StringOps(val ptr: Ptr[RE2.string_t]) extends AnyVal {
    def data: CString            = !ptr._1
    def length: CInt             = !ptr._2
    def data_=(v: CString): Unit = !ptr._1 = v
    def length_=(v: CInt): Unit  = !ptr._2 = v
  }

  final val ENCODING_UNKNOWN = 0
  final val ENCODING_UTF8    = 1
  final val ENCODING_LATIN1  = 2

  final val UNANCHORED   = 1
  final val ANCHOR_START = 2
  final val ANCHOR_BOTH  = 3

  final val ERROR_NO_ERROR           = 0
  final val ERROR_INTERNAL           = 1
  final val ERROR_BAD_ESCAPE         = 2
  final val ERROR_BAD_CHAR_CLASS     = 3
  final val ERROR_BAD_CHAR_RANGE     = 4
  final val ERROR_MISSING_BRACKET    = 5
  final val ERROR_MISSING_PAREN      = 6
  final val ERROR_TRAILING_BACKSLASH = 7
  final val ERROR_REPEAT_ARGUMENT    = 8
  final val ERROR_REPEAT_SIZE        = 9
  final val ERROR_REPEAT_OP          = 10
  final val ERROR_BAD_PERL_OP        = 11
  final val ERROR_BAD_UTF8           = 12
  final val ERROR_BAD_NAMED_CAPTURE  = 13
  final val ERROR_PATTERN_TOO_LARGE  = 14
}
