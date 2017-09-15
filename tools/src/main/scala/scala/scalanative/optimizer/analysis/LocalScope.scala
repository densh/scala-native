package scala.scalanative
package optimizer
package analysis

import scala.collection.mutable
import scalanative.nir.{Val, Local, Op, Inst}

final class LocalScope(val lookup: Local => Option[Op])

object LocalScope {
  def apply(insts: Seq[Inst]): LocalScope = {
    val lookup = mutable.Map.empty[Local, Op]
    insts.foreach {
      case Inst.Let(n, op) => lookup(n) = op
      case _               => ()
    }
    new LocalScope(lookup.get(_))
  }
}

object LocalRef {
  def unapply(value: Val)(implicit scope: LocalScope): Option[Op] =
    value match {
      case Val.Local(local, _) => unapply(local)
      case _                   => None
    }

  def unapply(local: Local)(implicit scope: LocalScope): Option[Op] =
    scope.lookup(local)
}
