package scala.scalanative
package tools

sealed abstract class InlineCachingMode
final case object NoInlineCaching                 extends InlineCachingMode
final case object InlineCacheAll                  extends InlineCachingMode
final case class InlineCacheN(n: Int)             extends InlineCachingMode
final case class InlineCacheP(p: Double)          extends InlineCachingMode
final case class InlineCacheNP(n: Int, p: Double) extends InlineCachingMode
