package scala.scalanative
package optimizer
package analysis

import scala.collection.mutable
import scalanative.nir._

final class LocalScope(val lookup: Local => Option[Op])

object LocalScope {
  def apply(insts: Seq[Inst]): LocalScope = {
    val lets = mutable.Map.empty[Local, Op]
    insts.foreach {
      case Inst.Let(n, op) =>
        lets(n) = op
      case _ =>
        ()
    }
    new LocalScope(lets.get(_))
  }
}

object LocalRef {
  def unapply(local: Local)(implicit scope: LocalScope): Option[Op] =
    scope.lookup(local)

  def unapply(value: Val)(implicit scope: LocalScope): Option[Op] =
    value match {
      case Val.Local(local, _) => unapply(local)
      case _                   => None
    }
}
