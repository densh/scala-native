package scala.scalanative
package interflow

import scalanative.nir._
import scalanative.linker._
import scalanative.optimizer.pass.DeadCodeElimination
import scalanative.interflow.Sema._
import scalanative.interflow.MergeProcessor.process

trait Expand { self: Interflow =>
  def shallExpand(exp: Expanded): Boolean =
    orig
      .get(exp.orig)
      .fold {
        false
      } { defn =>
        val notExtern = !defn.attrs.isExtern
        val hasInsts  = defn.insts.size > 0
        val hasSema   = linked.infos.contains(defn.name)

        notExtern && hasInsts && hasSema
      }

  def expandLoop(): Unit =
    while (todo.nonEmpty) {
      val exp = todo.dequeue()
      if (!done.contains(exp.name)) {
        start(exp)
      }
    }

  def start(exp: Expanded): Unit =
    if (!started.contains(exp.name)) {
      started += exp.name
      val defn = orig(exp.orig)
      try {
        val (insts, retty) = entry(exp)
        val sig            = Type.Function(exp.argtys, retty)
        assert(!done.contains(exp.name))
        val name = exp match {
          case Expanded(orig, argtys) if origArgTys(orig) == argtys =>
            orig
          case _ =>
            exp
        }
        done(exp.name) = defn.copy(name = exp.name, ty = sig, insts = insts)
      } catch {
        case exc: BailOut =>
          blacklist += exp.name
          log(s"failed to expand ${exp}: ${exc.toString}")
          done(exp.name) = defn
      }
    }

  def expandRoot(name: Global): Unit = {
    val exp = Expanded(name, origArgTys(name))
    assert(shallExpand(exp))
    todo.enqueue(exp)
  }

  def expandNow(exp: Expanded): Option[Defn.Define] =
    if (shallExpand(exp)) {
      if (!done.contains(exp.name)) {
        start(exp)
      }
      done.get(exp.name)
    } else {
      None
    }

  def entry(expand: Expanded): (Seq[Inst], Type) =
    in(s"expand ${expand.name.show}", show = true) {
      val Expanded(methName, argtys) = expand
      val meth                       = linked.infos(methName).asInstanceOf[Method]
      assert(meth.insts.nonEmpty)
      try {
        val fresh   = Fresh(0)
        val state   = new State(Local(0))
        val origtys = origArgTys(meth.name)
        val args = argtys.zip(origtys).map {
          case (argty, origty) =>
            val paramty = glb(argty, origty).getOrElse(origty)
            log(s"param ${paramty.show} = glb(${argty}, ${origty})")
            Val.Local(fresh(), paramty)
        }
        if (args.exists(_.ty == Type.Nothing)) {
          val insts = Seq(Inst.Label(Local(0), args), Inst.Unreachable)
          return (insts, Type.Nothing)
        }
        val blocks =
          process(meth.insts, args, state, Fresh(0), inline = false, this)
        val insts = blocks.flatMap { block =>
          block.cf = block.cf match {
            case Inst.Ret(retv) =>
              Inst.Ret(block.end.materialize(retv))
            case Inst.Throw(excv, unwind) =>
              Inst.Throw(block.end.materialize(excv), unwind)
            case cf =>
              cf
          }
          block.toInsts()
        }
        val rets = insts.collect {
          case Inst.Ret(v) => v.ty
        }
        val retty = rets match {
          case Seq()   => Type.Nothing
          case Seq(ty) => ty
          case tys     => lub(tys)
        }
        (insts, retty)
      } catch {
        case exc: BailOut =>
          log(s"bailed out in ${expand.name.show}: ${exc.msg}")
          (meth.insts, meth.ty.asInstanceOf[Type.Function].ret)
      }
    }

  def origArgTys(name: Global) = {
    val Type.Function(argtys, _) = linked.infos(name).asInstanceOf[Method].ty
    argtys
  }

  final case class Expanded(orig: Global, argtys: Seq[Type]) {
    val name: Global = {
      if (argtys == origArgTys(orig)) {
        orig
      } else {
        val Global.Member(top, sig) = orig
        Global.Member(top, Sig.Duplicate(sig, argtys))
      }
    }
  }
}
