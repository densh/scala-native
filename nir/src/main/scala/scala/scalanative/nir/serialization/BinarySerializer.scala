package scala.scalanative
package nir
package serialization

import java.nio.ByteBuffer
import scala.collection.mutable
import nir.serialization.{Tags => T}

final class BinarySerializer(buffer: ByteBuffer) {
  import buffer._

  // Things to change in next binary-breaking release:
  // 1. Val.Null should have its own tag, not encoded via Val.Zero(Type.Ptr).
  // 2. Volatile Op.{Load, Store} should become serializable.
  // 3. Attr.Align should become serializable;

  final def serialize(defns: Seq[Defn]): Unit = {
    val names     = defns.map(_.name)
    val positions = mutable.UnrolledBuffer.empty[Int]

    putInt(Versions.magic)
    putInt(Versions.compat)
    putInt(Versions.revision)

    putSeq(names) { n =>
      putGlobal(n)
      positions += buffer.position
      putInt(0)
    }

    val offsets = defns.map { defn =>
      val pos: Int = buffer.position
      putDefn(defn)
      pos
    }
    val end = buffer.position

    positions.zip(offsets).map {
      case (pos, offset) =>
        buffer.position(pos)
        putInt(offset)
    }
    buffer.position(end)
  }

  private def putTag(value: Int): Unit = put(value.toByte)

  private def putSeq[T](seq: Seq[T])(putT: T => Unit) = {
    putInt(seq.length)
    seq.foreach(putT)
  }

  private def putOpt[T](opt: Option[T])(putT: T => Unit) = opt match {
    case None    => put(0.toByte)
    case Some(t) => put(1.toByte); putT(t)
  }

  private def putInts(ints: Seq[Int]) = putSeq[Int](ints)(putInt(_))

  private def putStrings(vs: Seq[String]) = putSeq(vs)(putString)
  private def putString(v: String) = {
    val bytes = v.getBytes("UTF-8")
    putInt(bytes.length); put(bytes)
  }

  private def putBool(v: Boolean) = put((if (v) 1 else 0).toByte)

  private def putAttrs(attrs: Attrs) = putSeq(attrs.toSeq)(putAttr)
  private def putAttr(attr: Attr) = attr match {
    case Attr.MayInline    => putTag(T.MayInlineAttr)
    case Attr.InlineHint   => putTag(T.InlineHintAttr)
    case Attr.NoInline     => putTag(T.NoInlineAttr)
    case Attr.AlwaysInline => putTag(T.AlwaysInlineAttr)

    case Attr.Dyn => putTag(T.DynAttr)

    case Attr.Align(_) =>
      assert(false, "alignment attribute is not serializable")

    case Attr.Pure        => putTag(T.PureAttr)
    case Attr.Extern      => putTag(T.ExternAttr)
    case Attr.Override(n) => putTag(T.OverrideAttr); putGlobal(n)

    case Attr.Link(s)      => putTag(T.LinkAttr); putString(s)
    case Attr.PinAlways(n) => putTag(T.PinAlwaysAttr); putGlobal(n)
    case Attr.PinIf(n, cond) =>
      putTag(T.PinIfAttr); putGlobal(n); putGlobal(cond)
    case Attr.PinWeak(n) => putTag(T.PinWeakAttr); putGlobal(n)
  }

  private def putBin(bin: Bin) = bin match {
    case Bin.Iadd => putTag(T.IaddBin)
    case Bin.Fadd => putTag(T.FaddBin)
    case Bin.Isub => putTag(T.IsubBin)
    case Bin.Fsub => putTag(T.FsubBin)
    case Bin.Imul => putTag(T.ImulBin)
    case Bin.Fmul => putTag(T.FmulBin)
    case Bin.Sdiv => putTag(T.SdivBin)
    case Bin.Udiv => putTag(T.UdivBin)
    case Bin.Fdiv => putTag(T.FdivBin)
    case Bin.Srem => putTag(T.SremBin)
    case Bin.Urem => putTag(T.UremBin)
    case Bin.Frem => putTag(T.FremBin)
    case Bin.Shl  => putTag(T.ShlBin)
    case Bin.Lshr => putTag(T.LshrBin)
    case Bin.Ashr => putTag(T.AshrBin)
    case Bin.And  => putTag(T.AndBin)
    case Bin.Or   => putTag(T.OrBin)
    case Bin.Xor  => putTag(T.XorBin)
  }

  private def putInsts(insts: Seq[Inst]) = putSeq(insts)(putInst)
  private def putInst(cf: Inst) = cf match {
    case Inst.None =>
      putTag(T.NoneInst)

    case Inst.Label(name, params) =>
      putTag(T.LabelInst)
      putLocal(name)
      putParams(params)

    case Inst.Let(name, op) =>
      putTag(T.LetInst)
      putLocal(name)
      putOp(op)

    case Inst.Unreachable =>
      putTag(T.UnreachableInst)

    case Inst.Ret(v) =>
      putTag(T.RetInst)
      putVal(v)

    case Inst.Jump(next) =>
      putTag(T.JumpInst)
      putNext(next)

    case Inst.If(v, thenp, elsep) =>
      putTag(T.IfInst)
      putVal(v)
      putNext(thenp)
      putNext(elsep)

    case Inst.Switch(v, default, cases) =>
      putTag(T.SwitchInst)
      putVal(v)
      putNext(default)
      putNexts(cases)

    case Inst.Throw(v, unwind) =>
      putTag(T.ThrowInst)
      putVal(v)
      putNext(unwind)
  }

  private def putComp(comp: Comp) = comp match {
    case Comp.Ieq => putTag(T.IeqComp)
    case Comp.Ine => putTag(T.IneComp)
    case Comp.Ugt => putTag(T.UgtComp)
    case Comp.Uge => putTag(T.UgeComp)
    case Comp.Ult => putTag(T.UltComp)
    case Comp.Ule => putTag(T.UleComp)
    case Comp.Sgt => putTag(T.SgtComp)
    case Comp.Sge => putTag(T.SgeComp)
    case Comp.Slt => putTag(T.SltComp)
    case Comp.Sle => putTag(T.SleComp)

    case Comp.Feq => putTag(T.FeqComp)
    case Comp.Fne => putTag(T.FneComp)
    case Comp.Fgt => putTag(T.FgtComp)
    case Comp.Fge => putTag(T.FgeComp)
    case Comp.Flt => putTag(T.FltComp)
    case Comp.Fle => putTag(T.FleComp)
  }

  private def putConv(conv: Conv) = conv match {
    case Conv.Trunc    => putTag(T.TruncConv)
    case Conv.Zext     => putTag(T.ZextConv)
    case Conv.Sext     => putTag(T.SextConv)
    case Conv.Fptrunc  => putTag(T.FptruncConv)
    case Conv.Fpext    => putTag(T.FpextConv)
    case Conv.Fptoui   => putTag(T.FptouiConv)
    case Conv.Fptosi   => putTag(T.FptosiConv)
    case Conv.Uitofp   => putTag(T.UitofpConv)
    case Conv.Sitofp   => putTag(T.SitofpConv)
    case Conv.Ptrtoint => putTag(T.PtrtointConv)
    case Conv.Inttoptr => putTag(T.InttoptrConv)
    case Conv.Bitcast  => putTag(T.BitcastConv)
  }

  private def putDefn(value: Defn): Unit = value match {
    case Defn.Var(attrs, name, ty, value) =>
      putTag(T.VarDefn)
      putAttrs(attrs)
      putGlobal(name)
      putType(ty)
      putVal(value)

    case Defn.Const(attrs, name, ty, value) =>
      putTag(T.ConstDefn)
      putAttrs(attrs)
      putGlobal(name)
      putType(ty)
      putVal(value)

    case Defn.Declare(attrs, name, ty) =>
      putTag(T.DeclareDefn)
      putAttrs(attrs)
      putGlobal(name)
      putType(ty)

    case Defn.Define(attrs, name, ty, insts) =>
      putTag(T.DefineDefn)
      putAttrs(attrs)
      putGlobal(name)
      putType(ty)
      putInsts(insts)

    case Defn.Struct(attrs, name, members) =>
      putTag(T.StructDefn)
      putAttrs(attrs)
      putGlobal(name)
      putTypes(members)

    case Defn.Trait(attrs, name, ifaces) =>
      putTag(T.TraitDefn)
      putAttrs(attrs)
      putGlobal(name)
      putGlobals(ifaces)

    case Defn.Class(attrs, name, parent, ifaces) =>
      putTag(T.ClassDefn)
      putAttrs(attrs)
      putGlobal(name)
      putGlobalOpt(parent)
      putGlobals(ifaces)

    case Defn.Module(attrs, name, parent, ifaces) =>
      putTag(T.ModuleDefn)
      putAttrs(attrs)
      putGlobal(name)
      putGlobalOpt(parent)
      putGlobals(ifaces)
  }

  private def putGlobals(globals: Seq[Global]): Unit =
    putSeq(globals)(putGlobal)
  private def putGlobalOpt(globalopt: Option[Global]): Unit =
    putOpt(globalopt)(putGlobal)
  private def putGlobal(global: Global): Unit = global match {
    case Global.None    => putTag(T.NoneGlobal)
    case Global.Top(id) => putTag(T.TopGlobal); putString(id)
    case Global.Member(n, id) =>
      putTag(T.MemberGlobal); putGlobal(n); putString(id)
  }

  private def putLocal(local: Local): Unit = {
    putString(local.scope); putInt(local.id)
  }

  private def putNexts(nexts: Seq[Next]) = putSeq(nexts)(putNext)
  private def putNext(next: Next) = next match {
    case Next.None         => putTag(T.NoneNext)
    case Next.Unwind(n)    => putTag(T.UnwindNext); putLocal(n)
    case Next.Label(n, vs) => putTag(T.LabelNext); putLocal(n); putVals(vs)
    case Next.Case(v, n)   => putTag(T.CaseNext); putVal(v); putLocal(n)
  }

  private def putOp(op: Op) = op match {
    case Op.Call(ty, v, args, unwind) =>
      putTag(T.CallOp)
      putType(ty)
      putVal(v)
      putVals(args)
      putNext(unwind)

    case Op.Load(ty, ptr, isVolatile) =>
      assert(!isVolatile, "volatile loads are not serializable")
      putTag(T.LoadOp)
      putType(ty)
      putVal(ptr)

    case Op.Store(ty, value, ptr, isVolatile) =>
      assert(!isVolatile, "volatile stores are not serializable")
      putTag(T.StoreOp)
      putType(ty)
      putVal(value)
      putVal(ptr)

    case Op.Elem(ty, v, indexes) =>
      putTag(T.ElemOp)
      putType(ty)
      putVal(v)
      putVals(indexes)

    case Op.Extract(v, indexes) =>
      putTag(T.ExtractOp)
      putVal(v)
      putInts(indexes)

    case Op.Insert(v, value, indexes) =>
      putTag(T.InsertOp)
      putVal(v)
      putVal(value)
      putInts(indexes)

    case Op.Stackalloc(ty, n) =>
      putTag(T.StackallocOp)
      putType(ty)
      putVal(n)

    case Op.Bin(bin, ty, l, r) =>
      putTag(T.BinOp)
      putBin(bin)
      putType(ty)
      putVal(l)
      putVal(r)

    case Op.Comp(comp, ty, l, r) =>
      putTag(T.CompOp)
      putComp(comp)
      putType(ty)
      putVal(l)
      putVal(r)

    case Op.Conv(conv, ty, v) =>
      putTag(T.ConvOp)
      putConv(conv)
      putType(ty)
      putVal(v)

    case Op.Select(cond, thenv, elsev) =>
      putTag(T.SelectOp)
      putVal(cond)
      putVal(thenv)
      putVal(elsev)

    case Op.Classalloc(n) =>
      putTag(T.ClassallocOp)
      putGlobal(n)

    case Op.Field(v, name) =>
      putTag(T.FieldOp)
      putVal(v)
      putGlobal(name)

    case Op.Method(v, name) =>
      putTag(T.MethodOp)
      putVal(v)
      putGlobal(name)

    case Op.Dynmethod(obj, sign) =>
      putTag(T.DynmethodOp)
      putVal(obj)
      putString(sign)

    case Op.Module(name, unwind) =>
      putTag(T.ModuleOp)
      putGlobal(name)
      putNext(unwind)

    case Op.As(ty, v) =>
      putTag(T.AsOp)
      putType(ty)
      putVal(v)

    case Op.Is(ty, v) =>
      putTag(T.IsOp)
      putType(ty)
      putVal(v)

    case Op.Copy(v) =>
      putTag(T.CopyOp)
      putVal(v)

    case Op.Sizeof(ty) =>
      putTag(T.SizeofOp)
      putType(ty)

    case Op.Closure(ty, fun, captures) =>
      putTag(T.ClosureOp)
      putType(ty)
      putVal(fun)
      putVals(captures)

    case Op.Box(ty, obj) =>
      putTag(T.BoxOp)
      putType(ty)
      putVal(obj)

    case Op.Unbox(ty, obj) =>
      putTag(T.UnboxOp)
      putType(ty)
      putVal(obj)
  }

  private def putParams(params: Seq[Val.Local]) = putSeq(params)(putParam)
  private def putParam(param: Val.Local) = {
    putLocal(param.name)
    putType(param.ty)
  }

  private def putTypes(tys: Seq[Type]): Unit = putSeq(tys)(putType)
  private def putType(ty: Type): Unit = ty match {
    case Type.None         => putTag(T.NoneType)
    case Type.Void         => putTag(T.VoidType)
    case Type.Vararg       => putTag(T.VarargType)
    case Type.Ptr          => putTag(T.PtrType)
    case Type.Bool         => putTag(T.BoolType)
    case Type.Char         => putTag(T.CharType)
    case Type.Byte         => putTag(T.ByteType)
    case Type.UByte        => putTag(T.UByteType)
    case Type.Short        => putTag(T.ShortType)
    case Type.UShort       => putTag(T.UShortType)
    case Type.Int          => putTag(T.IntType)
    case Type.UInt         => putTag(T.UIntType)
    case Type.Long         => putTag(T.LongType)
    case Type.ULong        => putTag(T.ULongType)
    case Type.Float        => putTag(T.FloatType)
    case Type.Double       => putTag(T.DoubleType)
    case Type.Array(ty, n) => putTag(T.ArrayType); putType(ty); putInt(n)
    case Type.Function(args, ret) =>
      putTag(T.FunctionType); putTypes(args); putType(ret)
    case Type.Struct(n, tys) =>
      putTag(T.StructType); putGlobal(n); putTypes(tys)

    case Type.Unit      => putTag(T.UnitType)
    case Type.Nothing   => putTag(T.NothingType)
    case Type.Class(n)  => putTag(T.ClassType); putGlobal(n)
    case Type.Trait(n)  => putTag(T.TraitType); putGlobal(n)
    case Type.Module(n) => putTag(T.ModuleType); putGlobal(n)
  }

  private def putVals(values: Seq[Val]): Unit = putSeq(values)(putVal)
  private def putVal(value: Val): Unit = value match {
    case Val.None          => putTag(T.NoneVal)
    case Val.True          => putTag(T.TrueVal)
    case Val.False         => putTag(T.FalseVal)
    case Val.Null          => putTag(T.ZeroVal); putType(Type.Ptr)
    case Val.Zero(ty)      => putTag(T.ZeroVal); putType(ty)
    case Val.Undef(ty)     => putTag(T.UndefVal); putType(ty)
    case Val.Byte(v)       => putTag(T.ByteVal); put(v)
    case Val.Short(v)      => putTag(T.ShortVal); putShort(v)
    case Val.Int(v)        => putTag(T.IntVal); putInt(v)
    case Val.Long(v)       => putTag(T.LongVal); putLong(v)
    case Val.Float(v)      => putTag(T.FloatVal); putFloat(v)
    case Val.Double(v)     => putTag(T.DoubleVal); putDouble(v)
    case Val.Struct(n, vs) => putTag(T.StructVal); putGlobal(n); putVals(vs)
    case Val.Array(ty, vs) => putTag(T.ArrayVal); putType(ty); putVals(vs)
    case Val.Chars(s)      => putTag(T.CharsVal); putString(s)
    case Val.Local(n, ty)  => putTag(T.LocalVal); putLocal(n); putType(ty)
    case Val.Global(n, ty) => putTag(T.GlobalVal); putGlobal(n); putType(ty)

    case Val.Unit      => putTag(T.UnitVal)
    case Val.Const(v)  => putTag(T.ConstVal); putVal(v)
    case Val.String(v) => putTag(T.StringVal); putString(v)
  }
}
