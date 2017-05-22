package java.util
package regex

// Inspired by: https://github.com/google/re2j/blob/master/java/com/google/re2j/Pattern.java

import scalanative.native._
import scalanative.runtime.RE2
import scalanative.runtime.RE2Utils._

object Pattern {
  def CANON_EQ: Int                = 128
  def CASE_INSENSITIVE: Int        = 2
  def COMMENTS: Int                = 4
  def DOTALL: Int                  = 32
  def LITERAL: Int                 = 16
  def MULTILINE: Int               = 8
  def UNICODE_CASE: Int            = 64
  def UNICODE_CHARACTER_CLASS: Int = 256
  def UNIX_LINES: Int              = 1

  def compile(regex: String): Pattern = compile(regex, 0)

  def compile(regex: String, flags: Int): Pattern =
    compile(regex, 0, adapt = true)

  def compile(regex: String, flags: Int, adapt: Boolean): Pattern = {

    def notSupported(flag: Int, flagName: String): Unit = {
      if ((flags & flag) == flag) {
        assert(false, s"regex flag $flagName is not supported")
      }
    }

    notSupported(CANON_EQ, "CANON_EQ(canonical equivalences)")
    notSupported(COMMENTS, "COMMENTS")
    notSupported(UNICODE_CASE, "UNICODE_CASE")
    notSupported(UNICODE_CHARACTER_CLASS, "UNICODE_CHARACTER_CLASS")
    notSupported(UNIX_LINES, "UNIX_LINES")

    val options = RE2.optNew()
    RE2.setCaseSensitive(options, flags & CASE_INSENSITIVE)
    RE2.setDotNl(options, flags & DOTALL)
    RE2.setLiteral(options, flags & LITERAL)
    RE2.setLogErrors(options, 0)

    // setOneLine(false) is only available when limiting ourself to posix_syntax
    // https://github.com/google/re2/blob/2017-03-01/re2/re2.h#L548
    // regex flag MULTILINE cannot be disabled

    val re2 = RE2.compile(toCString(regex), regex.size, options)

    val code = RE2.errorCode(re2)

    if (code != ERROR_NO_ERROR) {
      val errorPattern = {
        val arg = stackalloc[RE2.string_t]
        RE2.errorArg(re2, arg)
        fromRE2String(arg)
      }

      // we try to find the index of the parsing error
      // this could return the wrong index it only finds the first match
      // see https://groups.google.com/forum/#!topic/re2-dev/rnvFZ9Ki8nk
      val index =
        if (code == ERROR_TRAILING_BACKSLASH) regex.size - 1
        else regex.indexOfSlice(errorPattern)

      val reText = fromCString(RE2.errorString(re2))

      val description =
        code match {
          case ERROR_BAD_ESCAPE         => "Illegal/unsupported escape sequence"
          case ERROR_MISSING_PAREN      => "Missing parenthesis"
          case ERROR_TRAILING_BACKSLASH => "Trailing Backslash"
          case ERROR_MISSING_BRACKET    => "Unclosed character class"
          case ERROR_BAD_CHAR_RANGE     => "Illegal character range"
          case ERROR_BAD_CHAR_CLASS     => "Illegal/unsupported character class"
          case ERROR_REPEAT_SIZE        => "Bad repetition argument"
          case ERROR_REPEAT_ARGUMENT    => "Dangling meta character '*'"
          case ERROR_REPEAT_OP          => "Bad repetition operator"
          case ERROR_BAD_PERL_OP        => "Bad perl operator"
          case ERROR_BAD_UTF8           => "Invalid UTF-8 in regexp"
          case ERROR_BAD_NAMED_CAPTURE  => "Bad named capture group"
          case ERROR_PATTERN_TOO_LARGE =>
            "Pattern too large (compilation failed)"
          case ERROR_INTERNAL => "Internal Error"
          case _              => reText
        }

      throw new PatternSyntaxException(
        description,
        regex,
        index
      )
    }

    RE2.optDelete(options)

    new Pattern(
      _pattern = regex,
      _flags = flags,
      _regex = re2
    )
  }

  def matches(regex: String, input: CharSequence): Boolean =
    compile(regex).matcher(input).matches

  def quote(s: String): String = {
    val original, quoted = stackalloc[RE2.string_t]
    toRE2String(s, original)
    RE2.quoteMeta(quoted, original)
    val res = fromRE2String(quoted)
    freeRE2String(original)
    res
  }

  def adaptPatternToRe2(regex: String): String = {
    regex
  }
}

final class Pattern private[regex] (
    _pattern: String,
    _flags: Int,
    _regex: Ptr[RE2.regexp_t]
) {

  private[regex] def regex: Ptr[RE2.regexp_t] = _regex

  def split(input: CharSequence): Array[String] =
    split(input, 0)

  def split(input: CharSequence, limit: Int): Array[String] =
    split(new Matcher(this, input), limit)

  private def split(m: Matcher, limit: Int): Array[String] = {
    var matchCount = 0
    var arraySize  = 0
    var last       = 0
    while (m.find()) {
      matchCount += 1
      if (limit != 0 || last < m.start()) {
        arraySize = matchCount
      }
      last = m.end()
    }
    if (last < m.inputLength || limit != 0) {
      matchCount += 1
      arraySize = matchCount
    }
    var trunc = 0
    if (limit > 0 && arraySize > limit) {
      arraySize = limit
      trunc = 1
    }

    val array = Array.ofDim[String](arraySize)
    var i     = 0
    last = 0
    m.reset()
    while (m.find() && i < arraySize - trunc) {
      val t = i
      i += 1
      array(t) = m.substring(last, m.start())
      last = m.end()
    }
    if (i < arraySize) {
      array(i) = m.substring(last, m.inputLength)
    }
    array
  }

  def matcher(input: CharSequence): Matcher = new Matcher(this, input)

  def flags: Int                = _flags
  def pattern: String           = _pattern
  override def toString: String = _pattern

  override protected def finalize(): Unit = {
    super.finalize()
    RE2.delete(_regex)
  }
}
