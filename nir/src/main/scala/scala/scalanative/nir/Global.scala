package scala.scalanative
package nir

sealed abstract class Global {
  def id: String
  def top: Global.Top

  final def isTop: Boolean =
    this.isInstanceOf[Global.Top]
  final def show: String =
    nir.Show(this)
}

object Global {
  def top(id: String): Global =
    Global.Top(id)
  def field(owner: Global, name: String): Global =
    Global.Member(owner, Mangle.fieldSig(name))
  def method(owner: Global, name: String, types: Seq[Type]): Global =
    Global.Member(owner, Mangle.methodSig(name, types))
  def method(owner: Global, sig: String): Global =
    Global.Member(owner, sig)
  def main(owner: Global): Global =
    method(owner, "main", Seq(Type.Array(Rt.String), Type.Unit))
  def init(owner: Global, types: Seq[Type] = Seq.empty): Global =
    Global.Member(owner, Mangle.initSig(types))
  def duplicate(of: Global, types: Seq[Type]): Global = {
    val Global.Member(owner, sig) = of
    Global.Member(owner, Mangle.duplicateSig(sig, types))
  }
  def proxy(of: Global): Global = {
    val Global.Member(owner, sig) = of
    Global.Member(owner, Mangle.proxySig(sig))
  }
  def accessor(owner: Global): Global =
    Global.Member(owner, "I4load")
  def layout(owner: Global): Global =
    Global.Member(owner, "I6layout")
  def rtti(owner: Global): Global =
    Global.Member(owner, "I4type")

  final case object None extends Global {
    override def id =
      throw new Exception("None doesn't have an id.")
    override def top =
      throw new Exception("None doesn't have a top.")
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
