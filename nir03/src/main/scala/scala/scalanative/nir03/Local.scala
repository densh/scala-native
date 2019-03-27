package scala.scalanative
package nir03

final case class Local(id: Int) extends AnyVal {
  final def show: String = nir03.Show(this)
}
