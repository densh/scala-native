package scala.scalanative
package interflow

import scala.collection.mutable
import scalanative.nir._

object Prune {
  def hasUnwind(defn: Defn.Define): Boolean = {
    defn.insts.exists {
      case Inst.Let(_, _, unwind) if unwind ne Next.None =>
        true
      case Inst.Throw(_, unwind) if unwind ne Next.None =>
        true
      case Inst.Unreachable(unwind) if unwind ne Next.None =>
        true
      case _ =>
        false
    }
  }

  def liveAt(entry: Local, cfg: ControlFlow.Graph): Seq[Val.Local] = {
    val visited = mutable.Set.empty[Local]
    val used    = mutable.Map.empty[Local, Val.Local]
    val defined = mutable.Set.empty[Local]

    val collectUses = new Transform {
      override def onVal(value: Val) = {
        value match {
          case value @ Val.Local(name, _) =>
            if (!used.contains(name)) {
              used(name) = value
            }
          case _ =>
            ()
        }
        super.onVal(value)
      }
    }

    def visit(block: ControlFlow.Block): Unit = {
      if (!visited.contains(block.name)) {
        visited += block.name
        block.params.foreach {
          case Val.Local(n, _) =>
            defined += n
        }
        block.insts.foreach {
          case Inst.Let(n, _, _) =>
            defined += n
          case _ =>
            ()
        }
        collectUses.onInsts(block.insts)
        block.outEdges.foreach { e =>
          visit(e.to)
        }
      }
    }

    visit(cfg.find(entry))

    used.collect {
      case (name, value) if !defined.contains(name) =>
        value
    }.toSeq
  }

  def deoptInsts(cfg: ControlFlow.Graph, entry: Local, live: Seq[Val.Local]): Seq[Inst] = {
    val visited = mutable.Set.empty[Local]
    val buf     = new nir.Buffer()(Fresh())

    def visit(block: ControlFlow.Block): Unit = {
      if (!visited.contains(block.name)) {
        visited += block.name
        if (block.name == entry) {
          val Inst.Label(name, params) = block.label
          buf += Inst.Label(name, params ++ live)
        } else {
          buf += block.label
        }
        buf ++= block.insts
        block.outEdges.foreach(e => visit(e.to))
      }
    }

    visit(cfg.find(entry))

    buf.toSeq
  }

  def deoptMethod(defn: Defn.Define, cfg: ControlFlow.Graph, entry: Local, live: Seq[Val.Local]): Defn.Define = {
    val Type.Function(_, retty)   = defn.ty
    val Global.Member(owner, sig) = defn.name

    val params = cfg.find(entry).params
    val attrs  = Attrs(inline = Attr.NoInline)
    val name   = Global.Member(owner, Sig.Deopt(sig, entry.id))
    val ty     = Type.Function(params.map(_.ty) ++ live.map(_.ty), retty)
    val insts  = deoptInsts(cfg, entry, live)

    Defn.Define(attrs, name, ty, insts)
  }

  def prune(defn: Defn.Define)(implicit linked: linker.Result): Seq[Defn.Define] = {
    val cfg      = nir.ControlFlow.Graph(defn.insts)
    val fresh    = Fresh(defn.insts)
    val buf      = new nir.Buffer()(fresh)
    val tailbuf  = new nir.Buffer()(fresh)
    val handlers = mutable.Map.empty[Local, Local]
    val defns    = mutable.UnrolledBuffer.empty[Defn.Define]

    def newHandler(entry: Local): Local = {
      val hname   = fresh()
      val hparams = cfg.find(entry).params.map(p => Val.Local(fresh(), p.ty))
      tailbuf.label(hname, hparams)
      val live      = liveAt(entry, cfg)
      val deoptDefn = deoptMethod(defn, cfg, entry, live)
      val methPtr   = Val.Global(deoptDefn.name, Type.Ptr)
      val args      = hparams ++ live
      val result    = tailbuf.call(deoptDefn.ty, methPtr, args, Next.None)
      tailbuf.ret(result)
      defns += deoptDefn
      hname
    }

    def handleNext(next: Next): Next = next match {
      case next: Next.Label if next.weight == 0 && !cfg.find(next.name).inLoop =>
        if (!handlers.contains(next.name)) {
          handlers(next.name) = newHandler(next.name)
        }
        Next.Label(handlers(next.name), next.args, next.weight)
      case Next.Case(v, next) =>
        Next.Case(v, handleNext(next))
      case _ =>
        next
    }

    defn.insts.foreach {
      case Inst.If(v, next1: Next.Label, next2: Next.Label) =>
        buf += Inst.If(v, handleNext(next1), handleNext(next2))
      case Inst.Switch(v, next: Next.Label, nexts) =>
        buf += Inst.Switch(v, handleNext(next), nexts.map(handleNext))
      case inst  =>
        buf += inst
    }

    buf ++= tailbuf

    if (!handlers.isEmpty) {
      val newDefn = defn.copy(insts = buf.toSeq)
      newDefn +: defns
    } else {
      Seq(defn)
    }
  }

  def apply(defns: Seq[Defn])(implicit linked: linker.Result): Seq[Defn] = {
    defns.flatMap {
      case defn: Defn.Define if defn.attrs.weight > 0 && !hasUnwind(defn) =>
        prune(defn)
      case defn =>
        Seq(defn)
    }
  }
}
