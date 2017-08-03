package scala.scalanative
package nir
package serialization

import java.nio.ByteBuffer
import java.nio.channels.ByteChannel
import scala.collection.mutable
import scalanative.nir.serialization.{Tags => T}
import scalanative.util.Stats

/*

header: { magic, compat_version, revision_version, offsets_offset,
          deps_offset,
          strings_offset, globals_offset, types_offset, defns_offset,
          vals_offset, insts_offset }
offsets: [{global_offset, defn_offset}]
deps: [dep]
strings: [string]
globals: [global]
types: [type]
vals: [val]
defns: [defn]
insts: [ [inst] ]

 */

sealed abstract class BinarySectionWriter(init: Int = 1024 * 1024) {
  private val buffer = ByteBuffer.allocate(init)

  def position: Int =
    buffer.position()
  def put(values: Array[Byte]): Unit =
    buffer.put(values)
  def put(value: Byte): Unit =
    buffer.put(value)
  def putShort(value: Short): Unit =
    buffer.putShort(value)
  def putInt(value: Int): Unit =
    buffer.putInt(value)
  def putLong(value: Long): Unit =
    buffer.putLong(value)
  def putFloat(value: Float): Unit =
    buffer.putFloat(value)
  def putDouble(value: Double): Unit =
    buffer.putDouble(value)
  def putBool(v: Boolean) =
    put((if (v) 1 else 0).toByte)
  def putLeb(v: Int): Unit = {
    var value     = v
    var remaining = value >> 7
    var hasMore   = true
    var end       = if ((value & java.lang.Integer.MIN_VALUE) == 0) 0 else -1
    while (hasMore) {
      hasMore = (remaining != end) || ((remaining & 1) != ((value >> 6) & 1))
      buffer.put(((value & 0x7f) | (if (hasMore) 0x80 else 0)).toByte)
      value = remaining
      remaining >>= 7
    }
  }
  def putLebs(ints: Seq[Int]): Unit =
    putSeq[Int](ints)(putLeb(_))
  def putSeq[T](seq: Seq[T])(putT: T => Unit): Unit = {
    putLeb(seq.length)
    seq.foreach(putT)
  }
  def putOpt[T](opt: Option[T])(putT: T => Unit): Unit = opt match {
    case None    => put(0.toByte)
    case Some(t) => put(1.toByte); putT(t)
  }
  def putTag(value: Int): Unit =
    put(value.toByte)
  def write(channel: ByteChannel): Unit = {
    buffer.flip
    channel.write(buffer)
  }
}

sealed abstract class InternedBinarySectionWriter[T]
    extends BinarySectionWriter {
  private val entries = mutable.Map.empty[T, Int]
  def put(value: T): Unit
  def internDeps(value: T): Unit
  def intern(value: T): Int = {
    entries.get(value).getOrElse {
      internDeps(value)
      val offset = position
      entries(value) = offset
      put(value)
      offset
    }
  }
}

final class BinaryWriter {
  private object Header extends BinarySectionWriter(10 * 4) {
    def put: Unit = {
      val offsetsOffset = 10 * 4 // size of the header
      val stringsOffset = offsetsOffset + Offsets.position
      val globalsOffset = stringsOffset + Strings.position
      val typesOffset   = globalsOffset + Globals.position
      val defnsOffset   = typesOffset + Types.position
      val valsOffset    = defnsOffset + Defns.position
      val instsOffset   = valsOffset + Vals.position

      putInt(Versions.magic)
      putInt(Versions.compat)
      putInt(Versions.revision)
      putInt(offsetsOffset)
      putInt(stringsOffset)
      putInt(globalsOffset)
      putInt(typesOffset)
      putInt(defnsOffset)
      putInt(valsOffset)
      putInt(instsOffset)
    }
  }

  private trait Common { self: BinarySectionWriter =>
    def putVal(value: Val): Unit =
      putLeb(Vals.intern(value))
    def putVals(values: Seq[Val]): Unit =
      putSeq(values)(putVal)
    def putGlobal(g: Global): Unit =
      putLeb(Globals.intern(g))
    def putGlobals(gs: Seq[Global]): Unit =
      putSeq(gs)(putGlobal)
    def putGlobalOpt(gopt: Option[Global]): Unit =
      putOpt(gopt)(putGlobal)
    def putType(ty: Type): Unit =
      putLeb(Types.intern(ty))
    def putTypes(tys: Seq[Type]): Unit =
      putSeq(tys)(putType)
    def putString(s: String): Unit =
      putLeb(Strings.intern(s))
  }

  private object Offsets extends BinarySectionWriter with Common {
    private def putDeps(deps: Seq[Dep]): Unit = putSeq(deps)(putDep)
    private def putDep(dep: Dep): Unit = dep match {
      case Dep.Direct(dep) =>
        putTag(T.DirectDep)
        putGlobal(dep)
      case Dep.Conditional(dep, cond) =>
        putTag(T.ConditionalDep)
        putGlobal(dep)
        putGlobal(cond)
      case Dep.Weak(dep) =>
        putTag(T.WeakDep)
        putGlobal(dep)
      case Dep.Wildcard(sig) =>
        putTag(T.WildcardDep)
        putString(sig)
      case Dep.Link(name) =>
        putTag(T.LinkDep)
        putString(name)
    }

    def put(global: Global,
            defnOffset: Int,
            inner: Seq[Global],
            deps: Seq[Dep]): Unit = {
      putGlobal(global)
      putLeb(defnOffset)
      putGlobals(inner)
      putDeps(deps)
    }
  }

  private object Strings
      extends InternedBinarySectionWriter[String]
      with Common {

    def internDeps(v: String): Unit = ()

    def put(v: String) = {
      val bytes = v.getBytes("UTF-8")
      putLeb(bytes.length)
      put(bytes)
    }
  }

  private object Globals
      extends InternedBinarySectionWriter[Global]
      with Common {

    def internDeps(value: Global): Unit = value match {
      case Global.Member(n, _) => intern(n)
      case _                   => ()
    }

    def put(value: Global): Unit = value match {
      case Global.None =>
        putTag(T.NoneGlobal)
      case Global.Top(id) =>
        putTag(T.TopGlobal)
        putString(id)
      case Global.Member(n, id) =>
        putTag(T.MemberGlobal)
        putGlobal(n)
        putString(id)
    }
  }

  private object Types extends InternedBinarySectionWriter[Type] with Common {

    def internDeps(ty: Type): Unit = ty match {
      case Type.Array(ty, _)      => intern(ty)
      case Type.Function(tys, ty) => tys.foreach(intern); intern(ty)
      case Type.Struct(_, tys)    => tys.foreach(intern)
      case _                      => ()
    }

    def put(ty: Type): Unit = ty match {
      case Type.None   => putTag(T.NoneType)
      case Type.Void   => putTag(T.VoidType)
      case Type.Vararg => putTag(T.VarargType)
      case Type.Ptr    => putTag(T.PtrType)
      case Type.Bool   => putTag(T.BoolType)
      case Type.Char   => putTag(T.CharType)
      case Type.Byte   => putTag(T.ByteType)
      case Type.UByte  => putTag(T.UByteType)
      case Type.Short  => putTag(T.ShortType)
      case Type.UShort => putTag(T.UShortType)
      case Type.Int    => putTag(T.IntType)
      case Type.UInt   => putTag(T.UIntType)
      case Type.Long   => putTag(T.LongType)
      case Type.ULong  => putTag(T.ULongType)
      case Type.Float  => putTag(T.FloatType)
      case Type.Double => putTag(T.DoubleType)
      case Type.Array(ty, n) =>
        putTag(T.ArrayType)
        putType(ty)
        putLeb(n)
      case Type.Function(args, ret) =>
        putTag(T.FunctionType)
        putTypes(args)
        putType(ret)
      case Type.Struct(n, tys) =>
        putTag(T.StructType)
        putGlobal(n)
        putTypes(tys)

      case Type.Unit      => putTag(T.UnitType)
      case Type.Nothing   => putTag(T.NothingType)
      case Type.Class(n)  => putTag(T.ClassType); putGlobal(n)
      case Type.Trait(n)  => putTag(T.TraitType); putGlobal(n)
      case Type.Module(n) => putTag(T.ModuleType); putGlobal(n)
    }
  }

  private object Vals extends InternedBinarySectionWriter[Val] with Common {

    def internDeps(value: Val): Unit = value match {
      case Val.Struct(_, vs) => vs.foreach(intern)
      case Val.Array(_, vs)  => vs.foreach(intern)
      case Val.Const(v)      => intern(v)
      case _                 => ()
    }

    def put(value: Val): Unit = value match {
      case Val.None          => putTag(T.NoneVal)
      case Val.True          => putTag(T.TrueVal)
      case Val.False         => putTag(T.FalseVal)
      case Val.Null          => putTag(T.ZeroVal); putType(Type.Ptr)
      case Val.Zero(ty)      => putTag(T.ZeroVal); putType(ty)
      case Val.Undef(ty)     => putTag(T.UndefVal); putType(ty)
      case Val.Byte(v)       => putTag(T.ByteVal); put(v)
      case Val.Short(v)      => putTag(T.ShortVal); putShort(v)
      case Val.Int(v)        => putTag(T.IntVal); putLeb(v)
      case Val.Long(v)       => putTag(T.LongVal); putLong(v)
      case Val.Float(v)      => putTag(T.FloatVal); putFloat(v)
      case Val.Double(v)     => putTag(T.DoubleVal); putDouble(v)
      case Val.Struct(n, vs) => putTag(T.StructVal); putGlobal(n); putVals(vs)
      case Val.Array(ty, vs) => putTag(T.ArrayVal); putType(ty); putVals(vs)
      case Val.Chars(s)      => putTag(T.CharsVal); putString(s)
      case Val.Local(n, ty)  => putTag(T.LocalVal); putLeb(n.id); putType(ty)
      case Val.Global(n, ty) => putTag(T.GlobalVal); putGlobal(n); putType(ty)

      case Val.Unit      => putTag(T.UnitVal)
      case Val.Const(v)  => putTag(T.ConstVal); putVal(v)
      case Val.String(v) => putTag(T.StringVal); putString(v)
    }
  }

  private object Defns extends BinarySectionWriter with Common {

    private def putAttrs(attrs: Attrs) =
      putSeq(attrs.toSeq)(putAttr)

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

    def put(defn: Defn): Unit = defn match {
      case defn: Defn.Var =>
        putTag(T.VarDefn)
        putAttrs(defn.attrs)
        putGlobal(defn.name)
        putType(defn.ty)
        putVal(defn.rhs)

      case defn: Defn.Const =>
        putTag(T.ConstDefn)
        putAttrs(defn.attrs)
        putGlobal(defn.name)
        putType(defn.ty)
        putVal(defn.rhs)

      case defn: Defn.Declare =>
        putTag(T.DeclareDefn)
        putAttrs(defn.attrs)
        putGlobal(defn.name)
        putType(defn.ty)

      case defn: Defn.Define =>
        putTag(T.DefineDefn)
        putAttrs(defn.attrs)
        putGlobal(defn.name)
        putType(defn.ty)
        putLeb(Insts.position)
        Insts.put(defn.insts)

      case defn: Defn.Struct =>
        putTag(T.StructDefn)
        putAttrs(defn.attrs)
        putGlobal(defn.name)
        putTypes(defn.tys)

      case defn: Defn.Trait =>
        putTag(T.TraitDefn)
        putAttrs(defn.attrs)
        putGlobal(defn.name)
        putGlobals(defn.traits)

      case defn: Defn.Class =>
        putTag(T.ClassDefn)
        putAttrs(defn.attrs)
        putGlobal(defn.name)
        putGlobalOpt(defn.parent)
        putGlobals(defn.traits)

      case defn: Defn.Module =>
        putTag(T.ModuleDefn)
        putAttrs(defn.attrs)
        putGlobal(defn.name)
        putGlobalOpt(defn.parent)
        putGlobals(defn.traits)
    }
  }

  private object Insts extends BinarySectionWriter with Common {

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

    private def putNexts(nexts: Seq[Next]) =
      putSeq(nexts)(putNext)

    private def putNext(next: Next) = next match {
      case Next.None         => putTag(T.NoneNext)
      case Next.Unwind(n)    => putTag(T.UnwindNext); putLeb(n.id)
      case Next.Label(n, vs) => putTag(T.LabelNext); putLeb(n.id); putVals(vs)
      case Next.Case(v, n)   => putTag(T.CaseNext); putVal(v); putLeb(n.id)
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
        putLebs(indexes)

      case Op.Insert(v, value, indexes) =>
        putTag(T.InsertOp)
        putVal(v)
        putVal(value)
        putLebs(indexes)

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

    private def putParams(params: Seq[Val.Local]) =
      putSeq(params)(putParam)

    private def putParam(param: Val.Local) = {
      putLeb(param.name.id)
      putType(param.ty)
    }

    private def putInst(cf: Inst) = cf match {
      case Inst.None =>
        putTag(T.NoneInst)

      case Inst.Label(name, params) =>
        putTag(T.LabelInst)
        putLeb(name.id)
        putParams(params)

      case Inst.Let(name, op) =>
        putTag(T.LetInst)
        putLeb(name.id)
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

    def put(insts: Seq[Inst]) = putSeq(insts)(putInst)
  }

  /** Serialize defns to in-memory byte buffers. */
  def put(assembly: Seq[Defn]): Unit =
    Dep.deep(assembly).foreach {
      case (defn, (inner, deps)) =>
        val defnOffset = Defns.position
        Defns.put(defn)
        Offsets.put(defn.name, defnOffset, inner, deps)
    }

  /** Commit byte-buffer contents to the file. */
  def write(channel: ByteChannel): Unit = Stats.time("write i/o") {
    Offsets.put(Global.None, -1, Seq.empty, Seq.empty)
    Header.put
    Header.write(channel)
    Offsets.write(channel)
    Strings.write(channel)
    Globals.write(channel)
    Types.write(channel)
    Defns.write(channel)
    Vals.write(channel)
    Insts.write(channel)
  }
}
