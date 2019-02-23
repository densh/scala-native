package scala.scalanative
package interflow

import scalanative.nir.{Type, Val}
import scalanative.linker.Class

object InstanceRef {
  def unapply(addr: Addr)(implicit state: State): Option[Type] =
    unapply(Val.Virtual(addr))
  def unapply(value: Val)(implicit state: State): Option[Type] = value match {
    case Val.Virtual(addr) =>
      val cls = state.deref(addr).cls
      Some(Type.Ref(cls.name, exact = true, nullable = false))
    case _ =>
      None
  }
}

object VirtualRef {
  def unapply(addr: Addr)(
      implicit state: State): Option[(Kind, Class, Array[Val])] =
    unapply(Val.Virtual(addr))
  def unapply(value: Val)(
      implicit state: State): Option[(Kind, Class, Array[Val])] = value match {
    case Val.Virtual(addr) =>
      state.deref(addr) match {
        case VirtualInstance(kind, cls, values) =>
          Some((kind, cls, values))
        case _ =>
          None
      }
    case _ =>
      None
  }
}

object EscapedRef {
  def unapply(addr: Addr)(implicit state: State): Option[Val] =
    unapply(Val.Virtual(addr))
  def unapply(value: Val)(implicit state: State): Option[Val] = value match {
    case Val.Virtual(addr) =>
      state.deref(addr) match {
        case EscapedInstance(_, value) =>
          Some(value)
        case _ =>
          None
      }
    case _ =>
      None
  }
}