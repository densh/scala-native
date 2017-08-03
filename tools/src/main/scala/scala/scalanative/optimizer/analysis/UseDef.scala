package scala.scalanative
package optimizer
package analysis

import scala.collection.mutable
import ClassHierarchy.Top
import ClassHierarchyExtractors._
import nir._
import util.unreachable

object UseDef {
  sealed abstract class Def {
    def name: Local
    val deps: mutable.Set[Def] = mutable.Set.empty[Def]
    val uses: mutable.Set[Def] = mutable.Set.empty[Def]
    var alive: Boolean         = false
  }
  final case class InstDef(name: Local)                    extends Def
  final case class BlockDef(name: Local, params: Seq[Def]) extends Def

  private class VisitLocalValDeps(f: Local => Unit) {
    def onInst(inst: Inst): Unit = inst match {
      case Inst.Let(n, op) =>
        onOp(op)
      case Inst.Ret(v) =>
        onVal(v)
      case Inst.Jump(next) =>
        onNext(next)
      case Inst.If(v, thenp, elsep) =>
        onVal(v); onNext(thenp); onNext(elsep)
      case Inst.Switch(v, default, cases) =>
        onVal(v); onNext(default); cases.foreach(onNext)
      case Inst.Throw(v, unwind) =>
        onVal(v); onNext(unwind)
      case _ =>
        ()
    }

    def onOp(op: Op): Unit = op match {
      case Op.Call(ty, ptrv, argvs, unwind) =>
        onVal(ptrv); argvs.foreach(onVal); onNext(unwind)
      case Op.Load(ty, ptrv, isVolatile) =>
        onVal(ptrv)
      case Op.Store(ty, ptrv, v, isVolatile) =>
        onVal(ptrv); onVal(v)
      case Op.Elem(ty, ptrv, indexvs) =>
        onVal(ptrv); indexvs.foreach(onVal)
      case Op.Extract(aggrv, indexvs) =>
        onVal(aggrv)
      case Op.Insert(aggrv, v, indexvs) =>
        onVal(aggrv); onVal(v)
      case Op.Stackalloc(ty, v) =>
        onVal(v)
      case Op.Bin(bin, ty, lv, rv) =>
        onVal(lv); onVal(rv)
      case Op.Comp(comp, ty, lv, rv) =>
        onVal(lv); onVal(rv)
      case Op.Conv(conv, ty, v) =>
        onVal(v)
      case Op.Select(v1, v2, v3) =>
        onVal(v1); onVal(v2); onVal(v3)

      case Op.Classalloc(n) =>
        ()
      case Op.Field(v, n) =>
        onVal(v)
      case Op.Method(v, n) =>
        onVal(v)
      case Op.Dynmethod(obj, signature) =>
        onVal(obj)
      case Op.Module(n, unwind) =>
        onNext(unwind)
      case Op.As(ty, v) =>
        onVal(v)
      case Op.Is(ty, v) =>
        onVal(v)
      case Op.Copy(v) =>
        onVal(v)
      case Op.Sizeof(ty) =>
        ()
      case Op.Closure(ty, fun, captures) =>
        onVal(fun); captures.foreach(onVal)
      case Op.Box(ty, obj) =>
        onVal(obj)
      case Op.Unbox(ty, obj) =>
        onVal(obj)
    }

    def onVal(value: Val): Unit = value match {
      case Val.Local(n, _) =>
        f(n)
      case Val.Struct(_, vals) =>
        vals.foreach(onVal)
      case Val.Array(_, vals) =>
        vals.foreach(onVal)
      case _ =>
        ()
    }

    def onNext(next: Next): Unit = {
      next match {
        case Next.Label(n, vals) => vals.foreach(onVal)
        case Next.Case(v, _)     => onVal(v)
        case _                   => ()
      }
      if (next ne Next.None) {
        f(next.name)
      }
    }
  }

  private def foreachLocalDep(inst: Inst)(f: Local => Unit): Unit = {
    val collector = new VisitLocalValDeps(f)
    collector.onInst(inst)
  }

  private def isPure(inst: Inst)(implicit top: Top) = inst match {
    case Inst.Let(_, Op.Call(_, Val.Global(Ref(node), _), _, _)) =>
      node.attrs.isPure
    case Inst.Let(_, Op.Module(Ref(node), _)) =>
      node.attrs.isPure
    case Inst.Let(_, _: Op.Pure) =>
      true
    case _ =>
      false
  }

  def apply(cfg: ControlFlow.Graph)(implicit top: Top): Map[Local, Def] = {
    val defs   = mutable.Map.empty[Local, Def]
    val blocks = cfg.all

    def enterBlock(n: Local, params: Seq[Local]): Unit = {
      params.foreach(enterInst)
      val paramDefs = params.map(defs)
      defs += ((n, BlockDef(n, paramDefs)))
    }
    def enterInst(n: Local): Unit = {
      defs += ((n, InstDef(n)))
    }
    def dep(from: Local, to: Local): Unit = {
      val fromdef = defs(from)
      val todef   = defs(to)
      todef.uses += fromdef
      fromdef.deps += todef
    }
    def localDeps(from: Local, inst: Inst): Unit = {
      val fromdef = defs(from)
      foreachLocalDep(inst) { to =>
        val todef = defs(to)
        todef.uses += fromdef
        fromdef.deps += todef
      }
    }
    def alive(ndef: Def): Unit =
      if (!ndef.alive) {
        ndef.alive = true
        ndef.deps.foreach(alive)
      }

    // enter definitions
    blocks.foreach { block =>
      enterBlock(block.name, block.params.map(_.name))
      block.insts.foreach {
        case Inst.Let(n, _) => enterInst(n)
        case _              => ()
      }
    }

    // enter deps & uses
    blocks.foreach { block =>
      block.insts.foreach {
        case inst: Inst.Let =>
          localDeps(inst.name, inst)
          if (!isPure(inst)) dep(block.name, inst.name)
        case inst: Inst.Cf =>
          localDeps(block.name, inst)
        case Inst.None =>
          ()
        case inst =>
          unreachable
      }
    }

    // trace live code
    alive(defs(cfg.entry.name))

    defs.toMap
  }
}
