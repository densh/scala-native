package scala.scalanative
package nir

sealed abstract class Next {
  def name: Local

  final def show: String = nir.Show(this)
}
object Next {
  final case object None extends Next {
    def name: Local =
      throw new UnsupportedOperationException
  }
  final case class Unwind(exc: Val.Local, next: Next) extends Next {
    def name: Local = next.name
  }
  final case class Case(value: Val, next: Next) extends Next {
    def name: Local = next.name
  }
  final case class Label(name: Local, args: Seq[Val], weight: Long) extends Next

  def apply(name: Local): Label =
    Label(name, Seq.empty, -1)
  def apply(name: Local, args: Seq[Val]): Label =
    Label(name, args, -1)
  def apply(name: Local, weight: Long): Label =
    Label(name, Seq.empty, weight)
  def apply(name: Local, args: Seq[Val], weight: Long): Label =
    Label(name, args, weight)
  def Case(value: Val, name: Local): Case =
    Case(value, Next(name))
}
