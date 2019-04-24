package scala.scalanative
package pgo

import fastparse.WhitespaceApi
import fastparse.all._

object DispatchInfo {

  private val IgnoreWhitespace = WhitespaceApi.Wrapper {
    import fastparse.all._
    NoTrace(CharIn(Seq(' ', '\t', '\n')).rep)
  }
  import IgnoreWhitespace._

  val number: P[Int] = P(CharIn('0' to '9').rep(1).!.map(_.toInt))

  val dispatchHeader: P[Long] =
    P("=" ~ "`" ~ CharsWhile(_ != '`').! ~ "`" ~ ":") map {
      case name => name.toLong
    }

  val dispatchMethod: P[(Long, Seq[(Int, Int)])] =
    dispatchHeader ~ (number ~ "(" ~ number ~ ")").rep(1) map {
      case (header, entries) =>
        (header, entries)
    }

  val dispatchInfo: P[Map[Long, Seq[(Int, Int)]]] =
    dispatchMethod.rep ~ End map (_.toMap)

  def apply(in: String): Map[Long, Seq[(Int, Int)]] =
    dispatchInfo.parse(in) match {
      case Parsed.Success(info, _) => info
      case Parsed.Failure(_, _, _) => Map.empty
    }
}
