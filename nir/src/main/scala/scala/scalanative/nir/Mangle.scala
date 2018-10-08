package scala.scalanative
package nir

object Mangle {
  def apply(ty: Type): String = {
    val impl = new Impl
    impl.mangleType(ty)
    impl.toString
  }

  def apply(name: Global): String = {
    val impl = new Impl
    impl.mangleGlobal(name)
    impl.toString
  }

  def apply(sig: Sig): String = {
    val impl = new Impl
    impl.mangleSig(sig)
    impl.toString
  }

  private class Impl {
    val sb = new scalanative.util.ShowBuilder
    import sb._

    def mangleGlobal(name: Global): Unit = name match {
      case name: Global.Top =>
        sb.str("C")
        mangleIdent(name.id)
      case name: Global.Member =>
        val ownerId = name.owner match {
          case owner: Global.Top => owner.id
          case _                 => util.unreachable
        }
        sb.str("M")
        mangleIdent(ownerId)
        mangleSig(name.sig)
      case _ =>
        ()
    }

    def mangleSig(sig: Sig): Unit = sig match {
      case Sig.Field(id) =>
        str("F")
        mangleIdent(id)
      case Sig.Ctor(types) =>
        str("R")
        types.foreach(mangleType)
        str("E")
      case Sig.Method(id, types) =>
        str("D")
        mangleIdent(id)
        types.foreach(mangleType)
        str("E")
      case Sig.Proxy(id, types) =>
        str("P")
        mangleIdent(id)
        types.foreach(mangleType)
        str("E")
      case Sig.Extern(id) =>
        str("U")
        mangleIdent(id)
      case Sig.Generated(id) =>
        str("G")
        mangleIdent(id)
    }

    def mangleType(ty: Type): Unit = ty match {
      case Type.Void         => mangleIdent("scala.scalanative.native.Void")
      case Type.Vararg       => mangleIdent("scala.scalanative.native.CVararg")
      case Type.Ptr          => mangleIdent("scala.scalanative.native.Ptr")
      case Type.I(8, false)  => mangleIdent("scala.scalanative.native.UByte")
      case Type.I(16, false) => mangleIdent("scala.scalanative.native.UShort")
      case Type.I(32, false) => mangleIdent("scala.scalanative.native.UInt")
      case Type.I(64, false) => mangleIdent("scala.scalanative.native.ULong")
      case Type.Bool         => mangleIdent("scala.Boolean")
      case Type.Char         => mangleIdent("scala.Char")
      case Type.I(8, true)   => mangleIdent("scala.Byte")
      case Type.I(16, true)  => mangleIdent("scala.Short")
      case Type.I(32, true)  => mangleIdent("scala.Int")
      case Type.I(64, true)  => mangleIdent("scala.Long")
      case Type.Float        => mangleIdent("scala.Float")
      case Type.Double       => mangleIdent("scala.Double")
      case Type.Nothing      => mangleIdent("scala.Nothing")
      case Type.Unit         => mangleIdent("scala.Unit")
      case Type.ArrayValue(ty, n) =>
        str("A")
        mangleType(ty)
        str(n)
        str("E")
      case Type.StructValue(Global.Top(id), _) =>
        mangleIdent(id)
      case Type.StructValue(Global.None, tys) =>
        str("S")
        tys.foreach(mangleType)
        str("E")
      case Type.Function(args, ret) =>
        str("R")
        args.foreach(mangleType)
        mangleType(ret)
        str("E")

      case Type.Class(Global.Top(id))  => mangleIdent(id)
      case Type.Trait(Global.Top(id))  => mangleIdent(id)
      case Type.Module(Global.Top(id)) => mangleIdent(id)
      case Type.Array(ty) =>
        str("A")
        mangleType(ty)
        str("_")
      case _ =>
        util.unreachable
    }

    def mangleIdent(id: String): Unit = {
      str(id.length)
      str(id)
    }

    override def toString = sb.toString
  }
}
