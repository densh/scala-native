package scala.scalanative
package nir

import scala.util.matching.Regex

import util.{unreachable, ShowBuilder}

object Show {
  def withBuilder(f: NirShowBuilder => Unit): String = {
    val builder = new NirShowBuilder(new ShowBuilder)
    f(builder)
    builder.toString
  }
  def apply(v: Attr): String      = withBuilder(_.showAttr(v))
  def apply(v: Attrs): String     = withBuilder(_.showAttrs(v))
  def apply(v: Bin): String       = withBuilder(_.showBin(v))
  def apply(v: Comp): String      = withBuilder(_.showComp(v))
  def apply(v: Conv): String      = withBuilder(_.showConv(v))
  def apply(v: Defn): String      = withBuilder(_.showDefn(v))
  def apply(v: Seq[Defn]): String = withBuilder(_.showDefns(v))
  def apply(v: Global): String    = withBuilder(_.showGlobal(v))
  def apply(v: Inst): String      = withBuilder(_.showInst(v))
  def apply(v: Local): String     = withBuilder(_.showLocal(v))
  def apply(v: Next): String      = withBuilder(_.showNext(v))
  def apply(v: Op): String        = withBuilder(_.showOp(v))
  def apply(v: Type): String      = withBuilder(_.showType(v))
  def apply(v: Val): String       = withBuilder(_.showVal(v))

  final class NirShowBuilder(val builder: ShowBuilder) extends AnyVal {
    import builder._

    def showAttrs(attrs: Attrs): Unit =
      if (attrs == Attrs.None) {
        ()
      } else {
        showAttrs(attrs.toSeq)
      }

    def showAttrs(attrs: Seq[Attr]): Unit = {
      rep(attrs, sep = " ")(showAttr)
      str(" ")
    }

    def showAttr(attr: Attr): Unit = attr match {
      case Attr.MayInline =>
        str("mayinline")
      case Attr.InlineHint =>
        str("inlinehint")
      case Attr.NoInline =>
        str("noinline")
      case Attr.AlwaysInline =>
        str("alwaysinline")
      case Attr.Dyn =>
        str("dyn")
      case Attr.Align(value) =>
        str("align(")
        str(value)
        str(")")
      case Attr.Pure =>
        str("pure")
      case Attr.Extern =>
        str("extern")
      case Attr.Override(name) =>
        str("override(")
        showGlobal(name)
        str(")")
      case Attr.Link(name) =>
        str("link(")
        str(name)
        str(")")
      case Attr.PinAlways(name) =>
        str("pin(")
        showGlobal(name)
        str(")")
      case Attr.PinIf(name, cond) =>
        str("pin-if(")
        showGlobal(name)
        str(", ")
        showGlobal(cond)
        str(")")
      case Attr.PinWeak(name) =>
        str("pin-weak(")
        showGlobal(name)
        str(")")
    }

    def showNext(next: Next): Unit = next match {
      case Next.Label(name, Seq()) =>
        showLocal(name)
      case Next.Label(name, args) =>
        showLocal(name)
        str("(")
        rep(args, sep = ", ")(showVal)
        str(")")
      case Next.Unwind(name) =>
        str("unwind ")
        showLocal(name)
      case Next.Case(v, name) =>
        str("case ")
        showVal(v)
        str(" => ")
        showLocal(name)
    }

    def showInst(inst: Inst): Unit = inst match {
      case Inst.None =>
        str("none")
      case Inst.Label(name, params) =>
        showLocal(name)
        if (params.isEmpty) {
          ()
        } else {
          str("(")
          rep(params, sep = ", ") {
            case Val.Local(n, ty) =>
              showLocal(n)
              str(" : ")
              showType(ty)
          }
          str(")")
        }
        str(":")
      case Inst.Let(name, op) =>
        showLocal(name)
        str(" = ")
        showOp(op)
      case Inst.Unreachable =>
        str("unreachable")
      case Inst.Ret(Val.None) =>
        str("ret")
      case Inst.Ret(value) =>
        str("ret ")
        showVal(value)
      case Inst.Jump(next) =>
        str("jump ")
        showNext(next)
      case Inst.If(cond, thenp, elsep) =>
        str("if ")
        showVal(cond)
        str(" then ")
        showNext(thenp)
        str(" else ")
        showNext(elsep)
      case Inst.Switch(scrut, default, cases) =>
        str("switch ")
        showVal(scrut)
        str(" {")
        indent()
        rep(cases) { next =>
          newline()
          showNext(next)
        }
        newline()
        str("default => ")
        showNext(default)
        unindent()
        newline()
        str("}")
      case Inst.Throw(v, unwind) =>
        str("throw ")
        showVal(v)
        if (unwind ne Next.None) {
          str(" ")
          showNext(unwind)
        }
    }

    def showOp(op: Op): Unit = op match {
      case Op.Call(ty, f, args, unwind) =>
        str("call[")
        showType(ty)
        str("] ")
        showVal(f)
        str("(")
        rep(args, sep = ", ")(showVal)
        str(")")
        if (unwind ne Next.None) {
          str(" ")
          showNext(unwind)
        }
      case Op.Load(ty, ptr, isVolatile) =>
        str(if (isVolatile) "volatile load[" else "load[")
        showType(ty)
        str("] ")
        showVal(ptr)
      case Op.Store(ty, ptr, value, isVolatile) =>
        str(if (isVolatile) "volatile store[" else "store[")
        showType(ty)
        str("] ")
        showVal(ptr)
        str(", ")
        showVal(value)
      case Op.Elem(ty, ptr, indexes) =>
        str("elem[")
        showType(ty)
        str("] ")
        showVal(ptr)
        str(", ")
        rep(indexes, sep = ", ")(showVal)
      case Op.Extract(aggr, indexes) =>
        str("extract ")
        showVal(aggr)
        str(", ")
        rep(indexes, sep = ", ")(str)
      case Op.Insert(aggr, value, indexes) =>
        str("insert ")
        showVal(aggr)
        str(", ")
        showVal(value)
        str(", ")
        rep(indexes, sep = ", ")(str)
      case Op.Stackalloc(ty, n) =>
        str("stackalloc[")
        showType(ty)
        str("]")
        if (n ne Val.None) {
          str(" ")
          showVal(n)
        }
      case Op.Bin(bin, ty, l, r) =>
        showBin(bin)
        str("[")
        showType(ty)
        str("] ")
        showVal(l)
        str(", ")
        showVal(r)
      case Op.Comp(comp, ty, l, r) =>
        showComp(comp)
        str("[")
        showType(ty)
        str("] ")
        showVal(l)
        str(", ")
        showVal(r)
      case Op.Conv(conv, ty, v) =>
        showConv(conv)
        str("[")
        showType(ty)
        str("] ")
        showVal(v)
      case Op.Select(cond, thenv, elsev) =>
        str("select ")
        showVal(cond)
        str(", ")
        showVal(thenv)
        str(", ")
        showVal(elsev)

      case Op.Classalloc(name) =>
        str("classalloc ")
        showGlobal(name)
      case Op.Fieldload(ty, obj, name) =>
        str("fieldload[")
        showType(ty)
        str("]")
        showVal(obj)
        str(", ")
        showGlobal(name)
      case Op.Fieldstore(ty, obj, name, value) =>
        str("fieldstore[")
        showType(ty)
        str("]")
        showVal(obj)
        str(", ")
        showGlobal(name)
        str(", ")
        showVal(value)
      case Op.Method(value, name) =>
        str("method ")
        showVal(value)
        str(", ")
        showGlobal(name)
      case Op.Dynmethod(value, signature) =>
        str("dynmethod ")
        showVal(value)
        str(", \"")
        str(escapeQuotes(signature))
        str("\"")
      case Op.Module(name, unwind) =>
        str("module ")
        showGlobal(name)
        if (unwind ne Next.None) {
          str(" ")
          showNext(unwind)
        }
      case Op.As(ty, v) =>
        str("as[")
        showType(ty)
        str("] ")
        showVal(v)
      case Op.Is(ty, v) =>
        str("is[")
        showType(ty)
        str("] ")
        showVal(v)
      case Op.Copy(value) =>
        str("copy ")
        showVal(value)
      case Op.Sizeof(ty) =>
        str("sizeof[")
        showType(ty)
        str("] ")
      case Op.Closure(ty, fun, captures) =>
        str("closure[")
        showType(ty)
        str("] ")
        rep(fun +: captures, sep = ", ")(showVal)
      case Op.Box(ty, v) =>
        str("box[")
        showType(ty)
        str("] ")
        showVal(v)
      case Op.Unbox(ty, v) =>
        str("unbox[")
        showType(ty)
        str("] ")
        showVal(v)
      case Op.Arrayalloc(ty, n) =>
        str("arrayalloc[")
        showType(ty)
        str("] ")
        showVal(n)
      case Op.Arraylength(obj) =>
        str("arraylength ")
        showVal(obj)
      case Op.Arrayat(ty, obj, index) =>
        str("arrayat[")
        showType(ty)
        str("] ")
        showVal(obj)
        str(", ")
        showVal(index)
      case Op.Arrayload(ty, obj, index) =>
        str("arrayload[")
        showType(ty)
        str("] ")
        showVal(obj)
        str(", ")
        showVal(index)
      case Op.Arraystore(ty, obj, index, value) =>
        str("arraystore[")
        showType(ty)
        str("] ")
        showVal(obj)
        str(", ")
        showVal(index)
        str(", ")
        showVal(value)
    }

    def showBin(bin: Bin): Unit = bin match {
      case Bin.Iadd => str("iadd")
      case Bin.Fadd => str("fadd")
      case Bin.Isub => str("isub")
      case Bin.Fsub => str("fsub")
      case Bin.Imul => str("imul")
      case Bin.Fmul => str("fmul")
      case Bin.Sdiv => str("sdiv")
      case Bin.Udiv => str("udiv")
      case Bin.Fdiv => str("fdiv")
      case Bin.Srem => str("srem")
      case Bin.Urem => str("urem")
      case Bin.Frem => str("frem")
      case Bin.Shl  => str("shl")
      case Bin.Lshr => str("lshr")
      case Bin.Ashr => str("ashr")
      case Bin.And  => str("and")
      case Bin.Or   => str("or")
      case Bin.Xor  => str("xor")
    }

    def showComp(comp: Comp): Unit = comp match {
      case Comp.Ieq => str("ieq")
      case Comp.Ine => str("ine")
      case Comp.Ugt => str("ugt")
      case Comp.Uge => str("uge")
      case Comp.Ult => str("ult")
      case Comp.Ule => str("ule")
      case Comp.Sgt => str("sgt")
      case Comp.Sge => str("sge")
      case Comp.Slt => str("slt")
      case Comp.Sle => str("sle")
      case Comp.Feq => str("feq")
      case Comp.Fne => str("fne")
      case Comp.Fgt => str("fgt")
      case Comp.Fge => str("fge")
      case Comp.Flt => str("flt")
      case Comp.Fle => str("fle")
    }

    def showConv(conv: Conv): Unit = conv match {
      case Conv.Trunc    => str("trunc")
      case Conv.Zext     => str("zext")
      case Conv.Sext     => str("sext")
      case Conv.Fptrunc  => str("fptrunc")
      case Conv.Fpext    => str("fpext")
      case Conv.Fptoui   => str("fptoui")
      case Conv.Fptosi   => str("fptosi")
      case Conv.Uitofp   => str("uitofp")
      case Conv.Sitofp   => str("sitofp")
      case Conv.Ptrtoint => str("ptrtoint")
      case Conv.Inttoptr => str("inttoptr")
      case Conv.Bitcast  => str("bitcast")
    }

    def showVal(value: Val): Unit = value match {
      case Val.None =>
        str("none")
      case Val.True =>
        str("true")
      case Val.False =>
        str("false")
      case Val.Null =>
        str("null")
      case Val.Zero(ty) =>
        str("zero[")
        showType(ty)
        str("]")
      case Val.Undef(ty) =>
        str("undef[")
        showType(ty)
        str("]")
      case Val.Byte(value) =>
        str("byte ")
        str(value)
      case Val.Short(value) =>
        str("short ")
        str(value)
      case Val.Int(value) =>
        str("int ")
        str(value)
      case Val.Long(value) =>
        str("long ")
        str(value)
      case Val.Float(value) =>
        str("float ")
        str(value)
      case Val.Double(value) =>
        str("double ")
        str(value)
      case Val.Struct(n, values) =>
        str("struct ")
        if (n ne Global.None) {
          showGlobal(n)
          str(" ")
        }
        str("{")
        rep(values, sep = ", ")(showVal)
        str("}")
      case Val.Array(ty, values) =>
        str("array ")
        showType(ty)
        str("{")
        rep(values, sep = ", ")(showVal)
        str("}")
      case Val.Chars(v) =>
        str("c\"")
        str(escapeNewLine(escapeQuotes(v)))
        str("\"")
      case Val.Local(name, ty) =>
        showLocal(name)
        str(" : ")
        showType(ty)
      case Val.Global(name, ty) =>
        showGlobal(name)
        str(" : ")
        showType(ty)
      case Val.Unit =>
        str("unit")
      case Val.Const(v) =>
        str("const ")
        showVal(v)
      case Val.String(v) =>
        str("\"")
        str(escapeNewLine(escapeQuotes(v)))
        str("\"")
    }

    def showDefns(defns: Seq[Defn]): Unit =
      rep(defns) { defn =>
        newline()
        showDefn(defn)
      }

    def showDefn(defn: Defn): Unit = defn match {
      case Defn.Var(attrs, name, ty, v) =>
        showAttrs(attrs)
        str("var ")
        showGlobal(name)
        str(" : ")
        showType(ty)
        if (v ne Val.None) {
          str(" = ")
          showVal(v)
        }
      case Defn.Const(attrs, name, ty, v) =>
        showAttrs(attrs)
        str("const ")
        showGlobal(name)
        str(" : ")
        showType(ty)
        if (v ne Val.None) {
          str(" = ")
          showVal(v)
        }
      case Defn.Declare(attrs, name, ty) =>
        showAttrs(attrs)
        str("def ")
        showGlobal(name)
        str(" : ")
        showType(ty)
      case Defn.Define(attrs, name, ty, insts) =>
        showAttrs(attrs)
        str("def ")
        showGlobal(name)
        str(" : ")
        showType(ty)
        str(" {")
        rep(insts) {
          case inst: Inst.Label =>
            newline()
            showInst(inst)
          case inst =>
            indent()
            newline()
            showInst(inst)
            unindent()
        }
        newline()
        str("}")
      case Defn.Struct(attrs, name, tys) =>
        showAttrs(attrs)
        str("struct ")
        showGlobal(name)
        str(" {")
        rep(tys, sep = ", ")(showType)
        str("}")
      case Defn.Trait(attrs, name, ifaces) =>
        showAttrs(attrs)
        str("trait ")
        showGlobal(name)
        if (ifaces.nonEmpty) {
          str(" : ")
          rep(ifaces, sep = ", ")(showGlobal)
        }
      case Defn.Class(attrs, name, parent, ifaces) =>
        val parents = parent ++: ifaces
        showAttrs(attrs)
        str("class ")
        showGlobal(name)
        if (parents.nonEmpty) {
          str(" : ")
          rep(parents, sep = ", ")(showGlobal)
        }
      case Defn.Module(attrs, name, parent, ifaces) =>
        val parents = parent ++: ifaces
        showAttrs(attrs)
        str("module ")
        showGlobal(name)
        if (parents.nonEmpty) {
          str(" : ")
          rep(parents, sep = ", ")(showGlobal)
        }
    }

    def showType(ty: Type): Unit = ty match {
      case Type.None   => str("none")
      case Type.Void   => str("void")
      case Type.Vararg => str("...")
      case Type.Bool   => str("bool")
      case Type.Ptr    => str("ptr")
      case Type.Char   => str("char")
      case Type.Byte   => str("byte")
      case Type.UByte  => str("ubyte")
      case Type.Short  => str("short")
      case Type.UShort => str("ushort")
      case Type.Int    => str("int")
      case Type.UInt   => str("uint")
      case Type.Long   => str("long")
      case Type.ULong  => str("ulong")
      case Type.Float  => str("float")
      case Type.Double => str("double")

      case Type.Array(ty, n) =>
        str("[")
        showType(ty)
        str(" x ")
        str(n)
        str("]")
      case Type.Function(args, ret) =>
        str("(")
        rep(args, sep = ", ")(showType)
        str(") => ")
        showType(ret)
      case Type.Struct(Global.None, tys) =>
        str("{")
        rep(tys, sep = ", ")(showType)
        str("}")
      case Type.Struct(name, _) =>
        str("struct ")
        showGlobal(name)

      case Type.Unit    => str("unit")
      case Type.Nothing => str("nothing")
      case Type.Class(name) =>
        str("class ")
        showGlobal(name)
      case Type.Trait(name) =>
        str("trait ")
        showGlobal(name)
      case Type.Module(name) =>
        str("module ")
        showGlobal(name)
      case Type.ArrayClass(ty) =>
        str("array-class ")
        str(ty)
    }

    def showGlobal(global: Global): Unit = global match {
      case Global.None =>
        unreachable
      case Global.Top(id) =>
        str("@")
        str(id)
      case Global.Member(n, id) =>
        showGlobal(n)
        str("::")
        str(id)
    }

    def showLocal(local: Local): Unit = local match {
      case Local(scope, id) =>
        str("%")
        str(scope)
        str(".")
        str(id)
    }

    private def escapeNewLine(s: String): String =
      """([^\\]|^)\n""".r.replaceAllIn(s, _.matched.toSeq match {
        case Seq(sngl)     => s"""\\\\n"""
        case Seq(fst, snd) => s"""${fst}\\\\n"""
      })

    private def escapeQuotes(s: String): String =
      """([^\\]|^)"""".r.replaceAllIn(s, _.matched.toSeq match {
        case Seq(sngl)     => s"\\\\$sngl"
        case Seq(fst, snd) => s"$fst\\\\$snd"
      })

    override def toString: String = builder.toString
  }
}
