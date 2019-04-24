package scala.scalanative
package pgo

import scala.collection.mutable
import scalanative.nir._

class PruneUntakenPaths()(implicit linked: linker.Result)
    extends Pass
    with Transform {
  import PruneUntakenPaths._

  type Liveness = mutable.Map[Local, mutable.Set[Val.Local]]

  var cond = false

  private def computeLiveness(insts: Seq[Inst]): Liveness = {
    val cfg  = ControlFlow.Graph(insts)
    val defs = mutable.Map.empty[Local, mutable.Set[Local]]
    val tys  = mutable.Map.empty[Local, Type]
    val uses = mutable.Map.empty[Local, mutable.Set[Local]]

    cfg.all.foreach { b =>
      val blockUses = mutable.Set.empty[Local]
      val blockDefs = mutable.Set.empty[Local]
      b.params.foreach { p =>
        blockDefs += p.name
        tys(p.name) = p.ty
      }
      b.insts.foreach {
        case Inst.Let(name, op, _) =>
          blockDefs += name
          tys(name) = op.resty
        case _ =>
          ()
      }
      val collectUses = new Transform {
        override def onVal(value: Val) = {
          value match {
            case Val.Local(name, _) =>
              blockUses += name
            case _ =>
              ()
          }
          super.onVal(value)
        }
      }
      collectUses.onInsts(b.insts)
      defs(b.name) = blockDefs
      uses(b.name) = blockUses
    }

    val live = uses.map {
      case (name, _) =>
        (name, mutable.Set.empty[Local])
    }
    def mark(block: ControlFlow.Block, value: Local): Unit = {
      if (!live(block.name).contains(value)
          && !defs(block.name).contains(value)) {
        live(block.name) += value
        block.inEdges.foreach { edge =>
          mark(edge.from, value)
        }
      }
    }
    uses.foreach {
      case (name, locals) =>
        locals.foreach { local =>
          mark(cfg.find(name), local)
        }
    }

    live.map {
      case (key, locals) =>
        key -> locals.map(local => Val.Local(local, tys(local)))
    }
  }

  private def deoptInsts(insts: Seq[Inst],
                         entry: Local,
                         live: Seq[Val.Local]): Seq[Inst] = {
    implicit val fresh = Fresh(insts)

    val dirty = new Buffer
    dirty.label(fresh(), live)
    dirty.jump(Next(entry))
    dirty ++= insts

    if (live.isEmpty) {
      dirty.toSeq
    } else {
      val defs = mutable.Set.empty[Local]
      val cfg  = ControlFlow.Graph(dirty.toSeq)
      cfg.all.foreach { b =>
        b.label.params.foreach { p =>
          defs += p.name
        }
        b.insts.foreach {
          case inst: Inst.Let =>
            defs += inst.name
          case _ =>
            ()
        }
      }
      val loopVars = live.filter(v => defs.contains(v.name))

      val entryLoopVars = loopVars.map { v =>
        v -> v.copy(name = fresh())
      }.toMap
      val entryParams = live.map { v =>
        entryLoopVars.get(v).getOrElse(v)
      }
      val entryNext = live.flatMap(entryLoopVars.get)

      val clean = new Buffer
      clean.label(fresh(), entryParams)
      clean.jump(entry, entryNext)
      cfg.all.foreach { b =>
        if (b ne cfg.entry) {
          var blockDefs = mutable.Set.empty[Local]
          b.params.foreach { p =>
            blockDefs += p.name
          }
          b.insts.foreach {
            case let: Inst.Let => blockDefs += let.name
            case _             => ()
          }

          val blockLoopVars = loopVars.map(v => v.copy(name = fresh()))
          val subsVars = loopVars
            .zip(blockLoopVars)
            .collect {
              case (lv, blv) if !blockDefs.contains(lv.name) =>
                lv.name -> blv
            }
            .toMap
          val blockArgs = loopVars.zip(blockLoopVars).map {
            case (lv, blv) =>
              if (blockDefs.contains(lv.name)) {
                lv
              } else {
                blv
              }
          }

          val subs = new Transform {
            override def onVal(value: Val): Val = value match {
              case value: Val.Local if subsVars.contains(value.name) =>
                subsVars(value.name)
              case _ =>
                super.onVal(value)
            }
          }
          val appendParams = new Transform {
            override def onNext(next: Next): Next = next match {
              case Next.None =>
                Next.None
              case Next.Label(n, args) =>
                Next.Label(n, args ++ blockArgs)
              case _: Next.Case =>
                ???
              case _: Next.Unwind =>
                ???
            }
          }
          clean.label(b.label.name, b.label.params ++ blockLoopVars)
          b.insts.foreach {
            case inst: Inst.Let =>
              clean += subs.onInst(inst)
            case inst: Inst.Cf =>
              clean += appendParams.onInst(subs.onInst(inst))
            case inst =>
              clean += inst
          }
        }
      }

      clean.toSeq
    }
  }

  private def deoptHandler(defn: Defn.Define,
                           deopt: Inst.Deopt): Defn.Define = {
    val Type.Function(_, retty) = defn.ty
    val Inst.Deopt(entry, live) = deopt

    val Global.Member(owner, sig) = defn.name

    val handlerAttrs = Attrs(inline = Attr.NoInline)
    val handlerName  = Global.Member(owner, Sig.Deopt(sig, entry.id))
    val handlerTy    = Type.Function(live.map(_.ty), retty)
    val handlerInsts = deoptInsts(defn.insts, entry, live)

    Defn.Define(handlerAttrs, handlerName, handlerTy, handlerInsts)
  }

  private def prune(defnBuf: mutable.UnrolledBuffer[Defn],
                    defn: Defn.Define): Seq[Inst] = {
    implicit val fresh = Fresh(defn.insts)
    val buf            = new Buffer
    val defnId         = linked.ids(defn.name)

    val deopts   = mutable.Map.empty[Local, Defn]
    val liveness = computeLiveness(defn.insts)

    defn.insts.zipWithIndex.foreach {
      case (label @ Inst.Label(name, params, warmth), idx) if warmth == 0 =>
        buf += label
        buf += Inst.Deopt(name, (liveness(name) ++ params).toSeq)
        buf += Inst.Label(fresh(), Seq())
      case (inst, _) =>
        buf += inst
    }

    val initialCfg = ControlFlow.Graph(buf.toSeq)
    val cleanBuf   = new Buffer
    initialCfg.all.foreach { b =>
      cleanBuf += b.label
      b.insts.foreach {
        case deopt @ Inst.Deopt(_, live) =>
          val deoptDefn  = deoptHandler(defn, deopt)
          val deoptValue = Val.Global(deoptDefn.name, Type.Ptr)
          val res        = cleanBuf.call(deoptDefn.ty, deoptValue, live, Next.None)
          cleanBuf.ret(res)
          defnBuf += deoptDefn
        case inst =>
          cleanBuf += inst
      }
    }

    cleanBuf.toSeq
  }

  private def onMethod(buf: mutable.UnrolledBuffer[Defn],
                       defn: Defn.Define): Unit = {
    val defnId     = linked.ids(defn.name)
    val entryInst  = defn.insts.head.asInstanceOf[Inst.Label]
    val entryScore = entryInst.warmth

    val newAttrs =
      if (PROFILE_DIRECTED_INLINING) {
        if (entryScore == 0L) {
          defn.attrs.copy(inline = Attr.NoInline)
        } else if (entryScore < 100L) {
          defn.attrs.copy(inline = Attr.MayInline)
        } else if (entryScore < 1000L) {
          defn.attrs.copy(inline = Attr.InlineHint)
        } else {
          defn.attrs.copy(inline = Attr.AlwaysInline)
        }
      } else {
        defn.attrs
      }
    val newInsts =
      if (PRUNE_UNTAKEN_BRANCHES) {
        if (entryScore == 0L) {
          defn.insts
        } else {
          def ignore(defn: Defn): Boolean = {
            var res = false
            (new Transform {
              override def onNext(next: Next): Next = {
                res = res || next.isInstanceOf[Next.Unwind] ||
                  next.isInstanceOf[Next.Case]
                next
              }
            }).onDefn(defn)
            res
          }
          if (ignore(defn)) {
            defn.insts
          } else {
            prune(buf, defn)
          }
        }
      } else {
        defn.insts
      }

    buf += defn.copy(attrs = newAttrs, insts = newInsts)
  }

  override def onDefns(defns: Seq[Defn]): Seq[Defn] = {
    val buf = mutable.UnrolledBuffer.empty[Defn]
    defns.foreach {
      case defn: Defn.Define if linked.infos.contains(defn.name) =>
        onMethod(buf, defn)
      case defn =>
        buf += defn
    }
    buf
  }
}

object PruneUntakenPaths extends PassCompanion {
  val PRUNE_UNTAKEN_BRANCHES    = true
  val PROFILE_DIRECTED_INLINING = false

  override def apply(config: build.Config, linked: linker.Result) = {
    new PruneUntakenPaths()(linked)
  }
}
