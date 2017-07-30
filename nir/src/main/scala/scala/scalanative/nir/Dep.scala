package scala.scalanative
package nir

import scala.collection.mutable

sealed abstract class Dep
object Dep {
  final case class Direct(dep: Global)                    extends Dep
  final case class Conditional(dep: Global, cond: Global) extends Dep
  final case class Weak(dep: Global)                      extends Dep
  final case class Wildcard(sig: String)                  extends Dep
  final case class Link(name: String)                     extends Dep

  def of(defn: Defn): Seq[Dep] = {
    val deps = mutable.UnrolledBuffer.empty[Dep]

    def onDefn(defn: Defn): Unit = {
      onAttrs(defn.attrs)
      defn match {
        case defn: Defn.Var =>
          onType(defn.ty); onVal(defn.rhs)
        case defn: Defn.Const =>
          onType(defn.ty); onVal(defn.rhs)
        case defn: Defn.Declare =>
          onType(defn.ty)
        case defn: Defn.Define =>
          onType(defn.ty); defn.insts.foreach(onInst)
        case defn: Defn.Struct =>
          defn.tys.foreach(onType)
        case defn: Defn.Trait =>
          defn.traits.foreach(onGlobal)
        case defn: Defn.Class =>
          defn.parent.foreach(onGlobal)
          defn.traits.foreach(onGlobal)
        case defn: Defn.Module =>
          defn.parent.foreach(onGlobal)
          defn.traits.foreach(onGlobal)
      }
    }

    def onAttrs(attrs: Attrs): Unit = {
      attrs.overrides.foreach(onGlobal)
      attrs.pins.foreach {
        case Attr.PinAlways(dep)   => deps += Dep.Direct(dep)
        case Attr.PinIf(dep, cond) => deps += Dep.Conditional(dep, cond)
        case Attr.PinWeak(dep)     => deps += Dep.Weak(dep)
      }
      attrs.links.foreach {
        case Attr.Link(name) => deps += Dep.Link(name)
      }
    }

    def onInst(inst: Inst): Unit = inst match {
      case Inst.None             => ()
      case Inst.Label(n, params) => params.foreach(onVal)
      case Inst.Let(n, op)       => onOp(op)
      case Inst.Unreachable      => ()
      case Inst.Ret(v)           => onVal(v)
      case Inst.Jump(next)       => onNext(next)
      case Inst.If(v, thenp, elsep) =>
        onVal(v); onNext(thenp); onNext(elsep)
      case Inst.Switch(v, default, cases) =>
        onVal(v); onNext(default); cases.foreach(onNext)
      case Inst.Throw(v, unwind) =>
        onVal(v); onNext(unwind)
    }

    def onOp(op: Op): Unit = op match {
      case Op.Call(ty, ptrv, argvs, unwind) =>
        onType(ty); onVal(ptrv); argvs.foreach(onVal); onNext(unwind)
      case Op.Load(ty, ptrv, isVolatile) =>
        onType(ty); onVal(ptrv)
      case Op.Store(ty, ptrv, v, isVolatile) =>
        onType(ty); onVal(ptrv); onVal(v)
      case Op.Elem(ty, ptrv, indexvs) =>
        onType(ty); onVal(ptrv); indexvs.foreach(onVal)
      case Op.Extract(aggrv, indexvs) =>
        onVal(aggrv)
      case Op.Insert(aggrv, v, indexvs) =>
        onVal(aggrv); onVal(v)
      case Op.Stackalloc(ty, v) =>
        onType(ty); onVal(v)
      case Op.Bin(bin, ty, lv, rv) =>
        onType(ty); onVal(lv); onVal(rv)
      case Op.Comp(comp, ty, lv, rv) =>
        onType(ty); onVal(lv); onVal(rv)
      case Op.Conv(conv, ty, v) =>
        onType(ty); onVal(v)
      case Op.Select(v1, v2, v3) =>
        onVal(v1); onVal(v2); onVal(v3)

      case Op.Classalloc(n) =>
        onGlobal(n)
      case Op.Field(v, n) =>
        onVal(v); onGlobal(n)
      case Op.Method(v, n) =>
        onVal(v); onGlobal(n)
      case Op.Dynmethod(obj, sig) =>
        onVal(obj); deps += Dep.Wildcard(sig)
      case Op.Module(n, unwind) =>
        onGlobal(n); onNext(unwind)
      case Op.As(ty, v) =>
        onType(ty); onVal(v)
      case Op.Is(ty, v) =>
        onType(ty); onVal(v)
      case Op.Copy(v) =>
        onVal(v)
      case Op.Sizeof(ty) =>
        onType(ty)
      case Op.Closure(ty, fun, captures) =>
        onType(ty); onVal(fun); captures.foreach(onVal)
      case Op.Box(ty, obj) =>
        onType(ty); onVal(obj)
      case Op.Unbox(ty, obj) =>
        onType(ty); onVal(obj)
    }

    def onVal(value: Val): Unit = value match {
      case Val.Zero(ty)          => onType(ty)
      case Val.Undef(ty)         => onType(ty)
      case Val.Struct(n, values) => onGlobal(n); values.foreach(onVal)
      case Val.Array(ty, values) => onType(ty); values.foreach(onVal)
      case Val.Local(n, ty)      => onType(ty)
      case Val.Global(n, ty)     => onGlobal(n); onType(ty)
      case Val.Const(v)          => onVal(v)
      case _                     => ()
    }

    def onType(ty: Type): Unit = ty match {
      case Type.Array(ty, n) =>
        onType(ty)
      case Type.Function(args, ty) =>
        args.foreach(onType); onType(ty)
      case Type.Struct(n, tys) =>
        onGlobal(n); tys.foreach(onType)
      case Type.Class(n) =>
        onGlobal(n)
      case Type.Trait(n) =>
        onGlobal(n)
      case Type.Module(n) =>
        onGlobal(n)
      case _ =>
        ()
    }

    def onNext(next: Next): Unit = next match {
      case Next.Label(n, args) => args.foreach(onVal)
      case Next.Case(v, n)     => onVal(v)
      case _                   => ()
    }

    def onGlobal(g: Global): Unit =
      if ((g ne Global.None) && g != defn.name) {
        deps += Dep.Direct(g)
      }

    onDefn(defn)
    deps.distinct
  }

  def deep(assembly: Seq[Defn]): Map[Defn, (Seq[Global], Seq[Dep])] = {
    val defns =
      assembly.map { defn =>
        defn.name -> defn
      }
    def isInner(dep: Dep) =
      dep match {
        case Dep.Direct(n) if defns.contains(n) => true
        case _                                  => false
      }
    val rawDeps =
      defns.map { case (name, defn) => name -> Dep.of(defn) }
    val outerDeps =
      rawDeps.map { case (name, deps) => (name, deps.filter(!isInner(_))) }.toMap
    val innerDeps =
      rawDeps.map {
        case (name, deps) =>
          (name, deps.filter(isInner).asInstanceOf[Seq[Dep.Direct]])
      }.toMap
    def transitiveInnerDeps(name: Global): Seq[Global] = {
      val visited  = mutable.Set.empty[Global]
      val worklist = mutable.Stack.empty[Global]
      worklist.push(name)
      while (worklist.nonEmpty) {
        val name = worklist.pop()
        if (!visited(name)) {
          visited += name
          innerDeps(name).foreach { inner =>
            worklist.push(inner.dep)
          }
        }
      }
      visited -= name
      visited.toSeq
    }

    defns.map {
      case (name, defn) =>
        val inner = transitiveInnerDeps(name)
        val outer = (name +: inner).flatMap(outerDeps).distinct
        (defn, (inner, outer))
    }.toMap
  }

}
