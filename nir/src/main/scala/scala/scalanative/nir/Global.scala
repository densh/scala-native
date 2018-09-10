package scala.scalanative
package nir

sealed abstract class Name {
  final def mangle: String = ???
}
object Name {

  // Definition names
  final case class Method(owner: Name, types: Seq[Name])      extends Name
  final case class Field(owner: Name, name: Name)             extends Name
  final case class Constructor(owner: Name, types: Seq[Name]) extends Name
  final case class Duplicate(of: Name, types: Seq[Name])      extends Name
  final case class Rtti(owner: Name)                          extends Name
  final case class Layout(owner: Name)                        extends Name
  final case class Accessor(owner: Name)                      extends Name
  final case class Dispatch(name: Name)                       extends Name
  final case class Proxy(name: Name)                          extends Name

  // Type names
  final case class FuncPtr(types: Seq[Name])         extends Name
  final case class Ptr(of: Name)                     extends Name
  final case class Unsigned(of: Name)                extends Name
  final case class Array(of: Name)                   extends Name
  final case class FixedArray(of: Name, length: Int) extends Name
  final case class Exact(of: Name)                   extends Name
  final case class NonNull(of: Name)                 extends Name
  final case class Tuple(arity: Int)                 extends Name
  final case class Function(arity: Int)              extends Name

  // Source names
  final case class Source(value: String) extends Name
}

sealed abstract class Global {
  def id: String
  def top: Global.Top

  def member(id: String): Global.Member =
    Global.Member(this, id)

  def tag(tag: String): Global = this match {
    case Global.Top(id)       => Global.Top(s"$tag.$id")
    case Global.Member(n, id) => Global.Member(n, s"$tag.$id")
    case _                    => util.unreachable
  }

  final def isTop: Boolean = this.isInstanceOf[Global.Top]

  final def show: String = nir.Show(this)

  final def normalize: Global = this match {
    case Global.Member(Global.Top("__extern"), id) =>
      Global.Top(id)
    case _ =>
      this
  }
}
object Global {
  final case object None extends Global {
    override def id  = throw new Exception("None doesn't have an id.")
    override def top = throw new Exception("None doesn't have a top.")
    override def member(id: String) =
      throw new Exception("None can't have any members.")
    override def tag(id: String) = throw new Exception("None is not taggable.")
  }

  final case class Top(override val id: String) extends Global {
    override def top = this
  }

  final case class Member(val owner: Global, override val id: String)
      extends Global {
    override def top: Global.Top = owner.top
  }

  def genSignature(methodName: nir.Global): String =
    genSignature(methodName, proxy = false)

  def genSignature(methodName: nir.Global, proxy: Boolean): String =
    genSignature(methodName.id, proxy)

  def genSignature(fullSignature: String): String =
    genSignature(fullSignature, proxy = false)

  def genSignature(fullSignature: String, proxy: Boolean): String = {
    val index = fullSignature.lastIndexOf("_")
    val signature =
      if (index != -1) {
        fullSignature.substring(0, index)
      } else {
        fullSignature
      }
    if (proxy) {
      toProxySignature(signature)
    } else {
      signature
    }
  }

  def toProxySignature(signature: String) = signature + "_proxy"

  def stripImplClassTrailingDollar(name: Global): Global = name match {
    case Global.None =>
      name
    case Global.Top(id) =>
      if (id.endsWith("$class$")) {
        Global.Top(id.substring(0, id.length - 1))
      } else {
        name
      }
    case Global.Member(subname, id) =>
      Global.Member(stripImplClassTrailingDollar(subname), id)
  }
}
