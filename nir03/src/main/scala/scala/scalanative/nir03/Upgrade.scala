package scala.scalanative
package nir03

import scala.collection.mutable
import scalanative.{nir03 => n3, nir => n4}

object Upgrade {
  def apply(input: Seq[n3.Defn]): Seq[n4.Defn] =
    input.map(upDefn)

  def upDefn(defn: n3.Defn): n4.Defn = defn match {
    case n3.Defn.Var(attrs, name, ty, rhs) =>
      n4.Defn.Var(upAttrs(attrs), upName(name), upType(ty), upVal(rhs))
    case n3.Defn.Const(attrs, name, ty, rhs) =>
      n4.Defn.Const(upAttrs(attrs), upName(name), upType(ty), upVal(rhs))
    case n3.Defn.Declare(attrs, name, ty) =>
      n4.Defn.Declare(upAttrs(attrs), upName(name), upType(ty))
    case n3.Defn.Define(attrs, name, ty, insts) =>
      n4.Defn
        .Define(upAttrs(attrs), upName(name), upType(ty), upInsts(insts))
    case n3.Defn.Trait(attrs, name, traits) =>
      n4.Defn.Trait(upAttrs(attrs), upName(name), traits.map(upName))
    case n3.Defn.Struct(attrs, name, _) =>
      n4.Defn.Class(upAttrs(attrs), upName(name), None, Seq.empty)
    case n3.Defn.Class(attrs, name, parent, traits) =>
      n4.Defn.Class(upAttrs(attrs),
                    upName(name),
                    parent.map(upName),
                    traits.map(upName))
    case n3.Defn.Module(attrs, name, parent, traits) =>
      n4.Defn.Class(upAttrs(attrs),
                    upName(name),
                    parent.map(upName),
                    traits.map(upName))
  }

  def upAttrs(attrs: n3.Attrs): n4.Attrs = {
    val inline = attrs.inline match {
      case n3.Attr.MayInline    => n4.Attr.MayInline
      case n3.Attr.InlineHint   => n4.Attr.InlineHint
      case n3.Attr.NoInline     => n4.Attr.NoInline
      case n3.Attr.AlwaysInline => n4.Attr.AlwaysInline
    }
    val isExtern   = attrs.isExtern
    val isDyn      = attrs.isDyn
    val isStub     = attrs.isStub
    val isAbstract = false
    val links      = attrs.links.map { case n3.Attr.Link(v) => n4.Attr.Link(v) }
    n4.Attrs(inline, isExtern, isDyn, isStub, isAbstract, links)
  }

  def upName(n: n3.Global): n4.Global = n match {
    case n3.Global.None    => n4.Global.None
    case n3.Global.Top(id) => n4.Global.Top(id)
    case n3.Global.Member(n, sig) =>
      n4.Global.Member(upName(n), upMemberSig(sig))
  }

  def upType(ty: n3.Type): n4.Type = ty match {
    case n3.Type.None           => n4.Type.Unit
    case n3.Type.Void           => n4.Type.Unit
    case n3.Type.Vararg         => n4.Type.Vararg
    case n3.Type.Bool           => n4.Type.Bool
    case n3.Type.Char           => n4.Type.Char
    case n3.Type.Byte           => n4.Type.Byte
    case n3.Type.UByte          => n4.Type.Byte
    case n3.Type.Short          => n4.Type.Short
    case n3.Type.UShort         => n4.Type.Short
    case n3.Type.Int            => n4.Type.Int
    case n3.Type.UInt           => n4.Type.Int
    case n3.Type.Long           => n4.Type.Long
    case n3.Type.ULong          => n4.Type.Long
    case n3.Type.Float          => n4.Type.Float
    case n3.Type.Double         => n4.Type.Double
    case n3.Type.Array(ty, n)   => n4.Type.ArrayValue(upType(ty), n)
    case n3.Type.Struct(_, tys) => n4.Type.StructValue(tys.map(upType))
    case n3.Type.Function(tys, ty) =>
      n4.Type.Function(tys.map(upType), upType(ty))
    case n3.Type.Class(n)  => n4.Type.Ref(upName(n))
    case n3.Type.Trait(n)  => n4.Type.Ref(upName(n))
    case n3.Type.Module(n) => n4.Type.Ref(upName(n))
    case n3.Type.Nothing   => n4.Type.Nothing
    case n3.Type.Ptr       => n4.Type.Ptr
    case n3.Type.Unit      => n4.Type.Unit
  }

  def upVal(value: n3.Val): n4.Val = value match {
    case n3.Val.None            => n4.Val.Unit
    case n3.Val.Null            => n4.Val.Null
    case n3.Val.Zero(ty)        => n4.Val.Zero(upType(ty))
    case n3.Val.Undef(ty)       => n4.Val.Zero(upType(ty))
    case n3.Val.True            => n4.Val.True
    case n3.Val.False           => n4.Val.False
    case n3.Val.Byte(v)         => n4.Val.Byte(v)
    case n3.Val.Short(v)        => n4.Val.Short(v)
    case n3.Val.Int(v)          => n4.Val.Int(v)
    case n3.Val.Long(v)         => n4.Val.Long(v)
    case n3.Val.Float(v)        => n4.Val.Float(v)
    case n3.Val.Double(v)       => n4.Val.Double(v)
    case n3.Val.Struct(_, vals) => n4.Val.StructValue(vals.map(upVal))
    case n3.Val.Array(ty, vals) =>
      n4.Val.ArrayValue(upType(ty), vals.map(upVal))
    case n3.Val.Chars(s)      => n4.Val.Chars(s)
    case n3.Val.Local(l, ty)  => n4.Val.Local(upLocal(l), upType(ty))
    case n3.Val.Global(n, ty) => n4.Val.Global(upName(n), upType(ty))
    case n3.Val.Unit          => n4.Val.Unit
    case n3.Val.Const(v)      => n4.Val.Const(upVal(v))
    case n3.Val.String(v)     => n4.Val.String(v)
  }

  def upLocal(local: n3.Local): n4.Local = n4.Local(local.id)

  def upInsts(insts: Seq[n3.Inst]): Seq[n4.Inst] = {
    val out = mutable.UnrolledBuffer.empty[n4.Inst]

    insts.foreach {
      case n3.Inst.None =>
        ()
      case n3.Inst.Label(l, params) =>
        out += n4.Inst.Label(upLocal(l), params.map {
          case n3.Val.Local(l, ty) => n4.Val.Local(upLocal(l), upType(ty))
        })
      case n3.Inst.Let(name, op) =>
        val (n4op, n4unwind) = upOp(op)
        out += n4.Inst.Let(upLocal(name), n4op, n4unwind)
      case n3.Inst.Unreachable =>
        out += n4.Inst.Unreachable(n4.Next.None)
      case n3.Inst.Ret(v) =>
        out += n4.Inst.Ret(upVal(v))
      case n3.Inst.Jump(next) =>
        out += n4.Inst.Jump(upNext(next))
      case n3.Inst.If(v, n1, n2) =>
        out += n4.Inst.If(upVal(v), upNext(n1), upNext(n2))
      case n3.Inst.Switch(v, default, cases) =>
        out += n4.Inst.Switch(upVal(v), upNext(default), cases.map(upNext))
      case n3.Inst.Throw(v, unwind) =>
        out += n4.Inst.Throw(upVal(v), upNext(unwind))
    }

    out.toSeq
  }

  def upOp(op: n3.Op): (n4.Op, n4.Next) = {
    def o(op: n4.Op)             = (op, n4.Next.None)
    def u(op: n4.Op, n: n4.Next) = (op, n)

    op match {
      case n3.Op.Call(ty, v, vs, unwind) =>
        u(n4.Op.Call(upType(ty), upVal(v), vs.map(upVal)), upNext(unwind))
      case n3.Op.Load(ty, v1, _) =>
        o(n4.Op.Load(upType(ty), upVal(v1)))
      case n3.Op.Store(ty, v1, v2, _) =>
        o(n4.Op.Store(upType(ty), upVal(v1), upVal(v2)))
      case n3.Op.Elem(ty, v, vs) =>
        o(n4.Op.Elem(upType(ty), upVal(v), vs.map(upVal)))
      case n3.Op.Extract(aggr, idxs) =>
        o(n4.Op.Extract(upVal(aggr), idxs))
      case n3.Op.Insert(aggr, v, idxs) =>
        o(n4.Op.Insert(upVal(aggr), upVal(v), idxs))
      case n3.Op.Stackalloc(ty, v) =>
        o(n4.Op.Stackalloc(upType(ty), upVal(v)))
      case n3.Op.Bin(bin, ty, v1, v2) =>
        o(n4.Op.Bin(upBin(bin), upType(ty), upVal(v1), upVal(v2)))
      case n3.Op.Comp(comp, ty, v1, v2) =>
        o(n4.Op.Comp(upComp(comp), upType(ty), upVal(v1), upVal(v2)))
      case n3.Op.Conv(conv, ty, v) =>
        o(n4.Op.Conv(upConv(conv), upType(ty), upVal(v)))
      case n3.Op.Select(v1, v2, v3) =>
        ??? // hopefully not used
      case n3.Op.Classalloc(n) =>
        o(n4.Op.Classalloc(upName(n)))
      case n3.Op.Field(obj, n) =>
        ??? // needs context information to turn field+load/store into fieldload/fieldstore
      case n3.Op.Method(v, n) =>
        o(n4.Op.Method(upVal(v), upMethodSig(n)))
      case n3.Op.Dynmethod(v, sig) =>
        o(n4.Op.Dynmethod(upVal(v), upDynMethodSig(sig)))
      case n3.Op.Module(n, unwind) =>
        u(n4.Op.Module(upName(n)), upNext(unwind))
      case n3.Op.As(ty, v) =>
        o(n4.Op.As(upType(ty), upVal(v)))
      case n3.Op.Is(ty, v) =>
        o(n4.Op.Is(upType(ty), upVal(v)))
      case n3.Op.Copy(v) =>
        o(n4.Op.Copy(upVal(v)))
      case n3.Op.Sizeof(ty) =>
        o(n4.Op.Sizeof(upType(ty)))
      case _: n3.Op.Closure =>
        ??? // was never used
      case n3.Op.Box(ty, v) =>
        o(n4.Op.Box(upType(ty), upVal(v)))
      case n3.Op.Unbox(ty, v) =>
        o(n4.Op.Unbox(upType(ty), upVal(v)))
    }
  }

  def upBin(bin: n3.Bin): n4.Bin = bin match {
    case n3.Bin.Iadd => n4.Bin.Iadd
    case n3.Bin.Fadd => n4.Bin.Fadd
    case n3.Bin.Isub => n4.Bin.Isub
    case n3.Bin.Fsub => n4.Bin.Fsub
    case n3.Bin.Imul => n4.Bin.Imul
    case n3.Bin.Fmul => n4.Bin.Fmul
    case n3.Bin.Sdiv => n4.Bin.Sdiv
    case n3.Bin.Udiv => n4.Bin.Udiv
    case n3.Bin.Fdiv => n4.Bin.Fdiv
    case n3.Bin.Srem => n4.Bin.Srem
    case n3.Bin.Urem => n4.Bin.Urem
    case n3.Bin.Frem => n4.Bin.Frem
    case n3.Bin.Shl  => n4.Bin.Shl
    case n3.Bin.Lshr => n4.Bin.Lshr
    case n3.Bin.Ashr => n4.Bin.Ashr
    case n3.Bin.And  => n4.Bin.And
    case n3.Bin.Or   => n4.Bin.Or
    case n3.Bin.Xor  => n4.Bin.Xor
  }

  def upComp(comp: n3.Comp): n4.Comp = comp match {
    case n3.Comp.Ieq => n4.Comp.Ieq
    case n3.Comp.Ine => n4.Comp.Ine
    case n3.Comp.Ugt => n4.Comp.Ugt
    case n3.Comp.Uge => n4.Comp.Uge
    case n3.Comp.Ult => n4.Comp.Ult
    case n3.Comp.Ule => n4.Comp.Ule
    case n3.Comp.Sgt => n4.Comp.Sgt
    case n3.Comp.Sge => n4.Comp.Sge
    case n3.Comp.Slt => n4.Comp.Slt
    case n3.Comp.Sle => n4.Comp.Sle
    case n3.Comp.Feq => n4.Comp.Feq
    case n3.Comp.Fne => n4.Comp.Fne
    case n3.Comp.Fgt => n4.Comp.Fgt
    case n3.Comp.Fge => n4.Comp.Fge
    case n3.Comp.Flt => n4.Comp.Flt
    case n3.Comp.Fle => n4.Comp.Fle
  }

  def upConv(conv: n3.Conv): n4.Conv = conv match {
    case n3.Conv.Trunc    => n4.Conv.Trunc
    case n3.Conv.Zext     => n4.Conv.Zext
    case n3.Conv.Sext     => n4.Conv.Sext
    case n3.Conv.Fptrunc  => n4.Conv.Fptrunc
    case n3.Conv.Fpext    => n4.Conv.Fpext
    case n3.Conv.Fptoui   => n4.Conv.Fptoui
    case n3.Conv.Fptosi   => n4.Conv.Fptosi
    case n3.Conv.Uitofp   => n4.Conv.Uitofp
    case n3.Conv.Sitofp   => n4.Conv.Sitofp
    case n3.Conv.Ptrtoint => n4.Conv.Ptrtoint
    case n3.Conv.Inttoptr => n4.Conv.Inttoptr
    case n3.Conv.Bitcast  => n4.Conv.Bitcast
  }

  def upNext(op: n3.Next): n4.Next = op match {
    case n3.Next.None      => n4.Next.None
    case n3.Next.Unwind(l) => ??? // needs fresh
    case n3.Next.Case(v, l) =>
      n4.Next.Case(upVal(v), n4.Next.Label(upLocal(l), Seq.empty))
    case n3.Next.Label(l, vs) => n4.Next.Label(upLocal(l), vs.map(upVal))
  }

  // needs parser for old name mangling
  def upMemberSig(sig: String): n4.Sig    = ???
  def upMethodSig(sig: n3.Global): n4.Sig = ???
  def upDynMethodSig(sig: String): n4.Sig = ???
}
