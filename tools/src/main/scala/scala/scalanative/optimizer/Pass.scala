package scala.scalanative
package optimizer

import analysis.ClassHierarchy.Top
import tools.Config
import scala.collection.mutable
import nir._

sealed trait AnyPass

object NoPass extends AnyPass

trait Inject extends AnyPass {
  def apply(buffer: mutable.Buffer[Defn]): Unit
}

trait Pass extends AnyPass {
  def onInsts(insts: Seq[Inst]): Seq[Inst] =
    insts.map(onInst)

  def onInst(inst: Inst): Inst = inst match {
    case Inst.None =>
      inst
    case _: Inst.Label =>
      inst
    case Inst.Let(n, op) =>
      Inst.Let(n, onOp(op))

    case Inst.Unreachable =>
      Inst.Unreachable
    case Inst.Ret(v) =>
      Inst.Ret(onVal(v))
    case Inst.Jump(next) =>
      Inst.Jump(onNext(next))
    case Inst.If(v, thenp, elsep) =>
      Inst.If(onVal(v), onNext(thenp), onNext(elsep))
    case Inst.Switch(v, default, cases) =>
      Inst.Switch(onVal(v), onNext(default), cases.map(onNext))
    case Inst.Throw(v, unwind) =>
      Inst.Throw(onVal(v), onNext(unwind))
  }

  def onOp(op: Op): Op = op match {
    case Op.Call(ty, ptrv, argvs, unwind) =>
      Op.Call(ty, onVal(ptrv), argvs.map(onVal), onNext(unwind))
    case Op.Load(ty, ptrv, isVolatile) =>
      Op.Load(ty, onVal(ptrv), isVolatile)
    case Op.Store(ty, ptrv, v, isVolatile) =>
      Op.Store(ty, onVal(ptrv), onVal(v), isVolatile)
    case Op.Elem(ty, ptrv, indexvs) =>
      Op.Elem(ty, onVal(ptrv), indexvs.map(onVal))
    case Op.Extract(aggrv, indexvs) =>
      Op.Extract(onVal(aggrv), indexvs)
    case Op.Insert(aggrv, v, indexvs) =>
      Op.Insert(onVal(aggrv), onVal(v), indexvs)
    case Op.Stackalloc(ty, v) =>
      Op.Stackalloc(ty, onVal(v))
    case Op.Bin(bin, ty, lv, rv) =>
      Op.Bin(bin, ty, onVal(lv), onVal(rv))
    case Op.Comp(comp, ty, lv, rv) =>
      Op.Comp(comp, ty, onVal(lv), onVal(rv))
    case Op.Conv(conv, ty, v) =>
      Op.Conv(conv, ty, onVal(v))
    case Op.Select(v1, v2, v3) =>
      Op.Select(onVal(v1), onVal(v2), onVal(v3))

    case Op.Classalloc(n) =>
      Op.Classalloc(n)
    case Op.Field(v, n) =>
      Op.Field(onVal(v), n)
    case Op.Method(v, n) =>
      Op.Method(onVal(v), n)
    case Op.Dynmethod(obj, signature) =>
      Op.Dynmethod(onVal(obj), signature)
    case Op.Module(n, unwind) =>
      Op.Module(n, onNext(unwind))
    case Op.As(ty, v) =>
      Op.As(ty, onVal(v))
    case Op.Is(ty, v) =>
      Op.Is(ty, onVal(v))
    case Op.Copy(v) =>
      Op.Copy(onVal(v))
    case Op.Sizeof(ty) =>
      Op.Sizeof(ty)
    case Op.Closure(ty, fun, captures) =>
      Op.Closure(ty, onVal(fun), captures.map(onVal))
    case Op.Box(ty, obj) =>
      Op.Box(ty, onVal(obj))
    case Op.Unbox(ty, obj) =>
      Op.Unbox(ty, onVal(obj))
  }

  def onVal(value: Val): Val = value match {
    case Val.Zero(ty)          => Val.Zero(ty)
    case Val.Undef(ty)         => Val.Undef(ty)
    case Val.Struct(n, values) => Val.Struct(n, values.map(onVal))
    case Val.Array(ty, values) => Val.Array(ty, values.map(onVal))
    case Val.Local(n, ty)      => Val.Local(n, ty)
    case Val.Global(n, ty)     => Val.Global(n, ty)
    case Val.Const(v)          => Val.Const(onVal(v))
    case _                     => value
  }

  def onNext(next: Next): Next = next match {
    case Next.None           => Next.None
    case unwind: Next.Unwind => unwind
    case Next.Label(n, args) => Next.Label(n, args.map(onVal))
    case Next.Case(v, n)     => Next.Case(onVal(v), n)
  }
}
