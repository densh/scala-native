package scala.scalanative
package optimizer

import scala.collection.mutable
import scalanative.nir._
import scalanative.optimizer.analysis.ControlFlow
import scalanative.optimizer.analysis.ClassHierarchy._
import scalanative.optimizer.analysis.ClassHierarchyExtractors._

// TODO: expand basic blocks
// TODO: infer retty as lub of all returns

class Expand(implicit top: Top) {
  sealed abstract class Task {
    def expand: Expand =
      this match {
        case expand: Expand => expand
        case resume: Resume => resume.state.expand
      }
  }

  /** Start a new expansion of a given method with specific argument types. */
  final case class Expand(val name: Global, val argtys: Seq[Type])
      extends Task {

    /** Returns new signature with given argtys. */
    def signature: Type = {
      val Type.Function(_, retty) = top.nodes(name).asInstanceOf[Method].ty
      Type.Function(argtys, retty)
    }

    /** Returns a new name for the expandsion of a method with given types. */
    def expandedName: Global = {
      val suffix = argtys.map(_.mangled).mkString("<", ";", ">")
      name match {
        case Global.Top(id) => Global.Top(id + suffix)
        case Global.Member(in, id) =>
          Global.Member(in, id + suffix)
        case _ => util.unreachable
      }
    }

    /** Original method. */
    def meth: Method = top.nodes(name).asInstanceOf[Method]
  }

  /** Resume previously suspended expansion. */
  final class Resume(val state: State, val deps: Seq[Expand]) extends Task

  /** Suspendable state of a given method expansion. */
  final class State(val expand: Expand,
                    val env: mutable.Map[Local, Known],
                    val fresh: nir.Fresh,
                    val buf: nir.Buffer,
                    val cfg: ControlFlow.Graph,
                    val blocks: Array[Array[Inst]],
                    var blockIdx: Int,
                    var instIdx: Int)

  /** Result of a method expansion. */
  final class Result(val name: Global, val sig: Type.Function)

  /** Facts known about a local variable. */
  sealed abstract class Known {
    def ty: Type
  }
  final case class HasType(override val ty: Type) extends Known
  final case class MethodOf(obj: Val, meth: Global) extends Known {
    def ty: Type = Type.Ptr
  }
  final case class GlobalOf(name: Global) extends Known {
    def ty: Type = Type.Ptr
  }

  private val results  = mutable.Map.empty[Expand, Result]
  private val enqueued = mutable.Set.empty[Expand]
  private val started  = mutable.Set.empty[Expand]
  private val done     = mutable.Map.empty[Expand, Defn]
  private val active   = mutable.Queue.empty[Task]
  private var waiting  = mutable.Queue.empty[Resume]

  /** Entry point of expander work-scheduling loop. */
  def loop(entries: Seq[Global]): Seq[Defn] = {
    entries.foreach {
      case MethodRef(_, meth) =>
        val sig    = meth.ty.asInstanceOf[Type.Function]
        val expand = new Expand(meth.name, sig.argtys)
        active.enqueue(expand)
        enqueued += expand
      case _ =>
        ()
    }

    while (!active.isEmpty) {
      //println("done: " + done.size + ", active: " + active.size + ", waiting: " + waiting.size)

      // Execute all tasks which are not blocked by anything.
      while (!active.isEmpty) {
        val task = active.dequeue()
        task match {
          case task: Expand =>
            assert(!done.contains(task))
            assert(!started.contains(task))
            start(task)
          case task: Resume =>
            assert(!done.contains(task.state.expand))
            assert(task.deps.isEmpty)
            resume(task.state)
          case _ =>
            ()
        }
      }

      while (active.isEmpty && !waiting.isEmpty) {

        // Find tasks that have been unblocked.
        val newWaiting = mutable.Queue.empty[Resume]
        val allDeps    = mutable.Map.empty[Expand, Seq[Expand]]
        while (!waiting.isEmpty) {
          val task    = waiting.dequeue()
          val deps    = task.deps.filterNot(results.contains(_))
          val updated = new Resume(task.state, deps)

          if (deps.isEmpty) {
            active.enqueue(updated)
          } else {
            allDeps(task.state.expand) = deps
            newWaiting.enqueue(updated)
          }
        }
        waiting = newWaiting

        // If not tasks are unblocked, find a first cycle and short-curcuit it.
        if (active.isEmpty && !waiting.isEmpty) {
          val task = waiting.dequeue()
          val head = task.state.expand
          waiting.enqueue(task)

          val visited = mutable.Set.empty[Expand]
          def visit(node: Expand): Option[Expand] = {
            if (!visited.contains(node)) {
              visited += node
              allDeps(node).flatMap(visit).headOption
            } else {
              Some(node)
            }
          }
          val cycle = visit(head).get
          shortCircuit(cycle)
        }
      }
    }

    if (waiting.nonEmpty) {
      //println("Got stuck while expanding!")
      val sorted = waiting.toSeq.sortBy(_.expand.expandedName.show)
      val txt    = new java.io.PrintWriter("out.txt")
      val dot    = new java.io.PrintWriter("out.dot")
      dot.write("digraph G {\n")
      sorted.foreach {
        case task: Resume =>
          val outer = task.expand.expandedName.show
          txt.write("* " + outer + "\n")
          task.deps.foreach { subtask =>
            val inner = subtask.expand.expandedName.show
            txt.write("  - " + inner + "\n")
            dot.write(s"""  "$outer\" -> "$inner";""")
            dot.write("\n")
          }
      }
      txt.close
      dot.write("}\n")
      dot.close
      Seq.empty
    } else {
      done.values.toSeq
    }
  }

  def canExpand(name: Global, argtys: Seq[Type]): Option[Expand] = {
    val Type.Function(origintys, _) = top.nodes(name).asInstanceOf[Method].ty

    if (name.isTop) {
      Some(new Expand(name, origintys))

    } else {
      val exptys = origintys.zip(argtys).flatMap {
        case (origty, argty) =>
          top.glb(origty, argty)
      }

      if (exptys.size != origintys.size) {
        None
      } else {
        Some(new Expand(name, argtys))
      }
    }
  }

  def shortCircuit(expand: Expand): Result =
    if (!results.contains(expand)) {
      //println(s"short circuiting ${expand.expandedName}")
      val Type.Function(_, retty) = expand.meth.ty
      val sig                     = Type.Function(expand.argtys, retty)
      val res                     = new Result(expand.expandedName, sig)
      results(expand) = res
      res
    } else {
      results(expand)
    }

  def request(expand: Expand): Option[Result] =
    if (results.contains(expand)) {
      results.get(expand)
    } else {
      if (!enqueued.contains(expand)) {
        //println(s"requesting new ${expand.expandedName.show}")
        active.enqueue(expand)
        enqueued += expand
      }

      val Type.Function(_, retty) = expand.meth.ty
      val canShortCircuit = retty match {
        case retty: Type.RefKind =>
          retty match {
            case _: Type.Exact =>
              true
            case ClassRef(cls) if cls.range == 1 =>
              true
            case Type.Unit =>
              true
            case _ =>
              false
          }
        case _ =>
          true
      }
      if (canShortCircuit) {
        Some(shortCircuit(expand))
      } else {
        None
      }
    }

  def start(expand: Expand): Unit = {
    //println(s"starting ${expand.expandedName.show}")
    started += expand

    val insts                   = expand.meth.insts
    val Type.Function(_, retty) = expand.meth.ty

    if (insts.isEmpty) {
      complete(expand, Seq.empty)

    } else {
      val env   = mutable.Map.empty[Local, Known]
      val fresh = nir.Fresh(insts)
      val buf   = new nir.Buffer()(fresh)
      val cfg   = ControlFlow.Graph(insts)
      val blocks =
        cfg.map(block => (block.label +: block.insts).toArray).toArray

      resume(
        new State(expand,
                  env,
                  fresh,
                  buf,
                  cfg,
                  blocks,
                  blockIdx = 0,
                  instIdx = 0))
    }
  }

  def suspend(state: State, deps: Expand*): Unit = {
    //println(s"suspending ${state.expand.expandedName.show}")

    waiting.enqueue(new Resume(state, deps))
  }

  def resume(state: State): Unit = {
    import state._

    // println(s"resuming ${state.expand.expandedName.show}")
    // println("<<<")
    // var i = 0
    // while (i < blocks.size) {
    //   val block = blocks(i)
    //   var j = 0
    //   while (j < block.size) {
    //     val suffix  = if (blockIdx == i && instIdx == j) "~~~"  else "   "
    //     val suffix2 = if (j == 0) "" else "   "
    //     println(suffix + suffix2 + block(j).show)
    //     j += 1
    //   }
    //   i += 1
    // }
    // println("---")
    // env.toSeq.sortBy(_._1.id).foreach(println)
    // println(">>>")

    def known(v: Val): Known = v match {
      case Val.Local(name, _)  => env(name)
      case Val.Global(name, _) => GlobalOf(name)
      case _                   => HasType(exactify(v.ty))
    }

    def exactify(ty: Type): Type = ty match {
      case ClassRef(cls) if cls.range.size == 1 =>
        Type.Exact(cls.name)
      case _ =>
        ty
    }

    def exactifyValue(v: Val): Val = v match {
      case Val.Local(name, ty) =>
        Val.Local(name, known(v).ty)
      case _ =>
        v
    }

    while (blockIdx < blocks.size) {
      val block = blocks(blockIdx)

      while (instIdx < block.size) {
        val inst = block(instIdx)

        inst match {
          case label @ Inst.Label(name, params) =>
            if (blockIdx == 0) {
              val adaptedParams = mutable.UnrolledBuffer.empty[Val.Local]
              params.zip(expand.argtys).foreach {
                case (param, ty) =>
                  env(param.name) = HasType(exactify(top.glb(param.ty, ty).get))
                  adaptedParams += Val.Local(param.name, ty)
              }
              buf.label(name, adaptedParams)
            } else {
              params.foreach { param =>
                env(param.name) = HasType(exactify(param.ty))
              }
              buf += label
            }

          case inst @ Inst.Let(
                name,
                op @ Op.Call(origSig: Type.Function, func, args, unwind)) =>
            val argtys = args.map(arg => exactify(known(arg).ty))
            val sig    = Type.Function(argtys, exactify(origSig.retty))

            known(func) match {
              case GlobalOf(methName) =>
                val subexpand = canExpand(methName, argtys).get
                val results   = request(subexpand)
                if (results.isEmpty) return suspend(state, subexpand)
                val res  = results.head
                val func = Val.Global(res.name, Type.Ptr)
                buf.let(name, Op.Call(res.sig, func, args, unwind))
                env(name) = HasType(exactify(res.sig.retty))

              case MethodOf(obj, methName) =>
                val impls = resolveMethod(known(obj).ty, methName)
                val subexpands =
                  impls.flatMap(impl => canExpand(impl.name, argtys))
                val results = subexpands.flatMap(request)
                if (results.size != subexpands.size)
                  return suspend(state, subexpands: _*)

                results match {
                  case Seq() =>
                    buf.unreachable
                    buf.label(fresh())
                    env(name) = HasType(exactify(sig.retty))
                  case Seq(res) =>
                    val func = Val.Global(res.name, Type.Ptr)
                    buf.let(name, Op.Call(res.sig, func, args, unwind))
                    env(name) = HasType(exactify(res.sig.retty))
                  case _ =>
                    // println(
                    //   s"generic case of ${methName.show} with ${argtys.map(_.show)}:")
                    // subexpands.foreach(s =>
                    //   println("  - " + s.expandedName.show))
                    val generic = canExpand(methName, argtys).get
                    request(generic)
                    val func =
                      buf.method(exactifyValue(obj), generic.expandedName)
                    buf.let(name, Op.Call(sig, func, args, unwind))
                    env(name) = HasType(exactify(sig.retty))
                }

              case _ =>
                buf += inst
                env(name) = HasType(exactify(sig.retty))
            }

          case inst @ Inst.Let(name, op) =>
            op match {
              case Op.Method(obj, meth) =>
                env(name) = MethodOf(obj, meth)
              case _ =>
                env(name) = HasType(exactify(op.resty))
                buf += inst
            }

          case inst @ Inst.Ret(v) =>
            buf += Inst.Ret(exactifyValue(v))

          case inst =>
            buf += inst
        }

        instIdx += 1
      }

      instIdx = 0

      blockIdx += 1
    }

    complete(expand, buf.toSeq)
  }

  def complete(expand: Expand, insts: Seq[Inst]): Unit = {
    if (!done.contains(expand)) {
      // println(s"completing ${expand.expandedName.show}")

      val meth  = expand.meth
      val name  = expand.expandedName
      val attrs = meth.attrs.copy(overrides = Seq.empty)
      val retty =
        if (insts.isEmpty) {
          val Type.Function(_, origRetty) = meth.ty
          origRetty
        } else {
          val retvals = insts.collect {
            case Inst.Ret(v) => v.ty
          }
          retvals match {
            case Seq() =>
              Type.Nothing
            case Seq(ty) =>
              ty
            case head +: tail =>
              tail.foldLeft[Type](head)(top.lub)
          }
        }
      val sig = Type.Function(expand.argtys, retty)

      if (!results.contains(expand)) {
        results(expand) = new Result(name, sig)
      }

      done(expand) = if (insts.isEmpty) {
        Defn.Declare(attrs, name, sig)
      } else {
        Defn.Define(attrs, name, sig, insts)
      }

      //println(s"done ${expand.expandedName.show}")
    }
  }

  def classWithName(clsName: Global): Class =
    top.nodes(clsName).asInstanceOf[Class]

  /** Returns all possible resolutions of a given method on given type. */
  def resolveMethod(ty: Type, methName: Global): Seq[Val.Global] = {
    //println(s"resolving method ${methName.show} on ${ty.show}")

    val meth  = top.nodes(methName).asInstanceOf[Method]
    val impls = mutable.UnrolledBuffer.empty[Val.Global]
    val srcs  = mutable.UnrolledBuffer.empty[String]

    def add(ty: Type, v: Val): Unit = v match {
      case v: Val.Global => impls += v; srcs += s"  - ${ty.show} impl ${v.show}"
      case _             => ()
    }

    if (meth.inClass) {
      if (meth.isStatic) {
        add(ty, Val.Global(methName, Type.Ptr))
      } else {
        def addExactClass(cls: Class): Unit =
          if (cls.allocated) {
            add(cls.ty, cls.vtable.at(cls.vtable.index(meth)))
          }
        def addWithSubclasses(cls: Class): Unit = {
          addExactClass(cls)
          cls.subclasses.foreach(addWithSubclasses)
        }

        ty match {
          case Type.Exact(ClassRef(cls)) => addExactClass(cls)
          case Type.Class(ClassRef(cls)) => addWithSubclasses(cls)
          case Type.Trait(_)             => addWithSubclasses(classWithName(Rt.Object.name))
          case Type.Unit =>
            addExactClass(
              top
                .nodes(Global.Top("scala.scalanative.runtime.BoxedUnit$"))
                .asInstanceOf[Class])
          case ty => util.unsupported(ty)
        }
      }
    } else if (meth.inTrait) {
      val sig = meth.name.id

      if (top.tables.traitInlineSigs.contains(sig)) {
        add(ty, top.tables.traitInlineSigs(sig))
      } else {
        def addExactClass(cls: Class): Unit = {
          if (cls.allocated) {
            val sigid  = top.tables.traitDispatchSigs(sig)
            val offset = top.tables.dispatchOffset(sigid)
            add(cls.ty, top.tables.dispatchArray(offset + cls.range.start))
          }
        }
        def addWithSubclasses(cls: Class): Unit = {
          addExactClass(cls)
          cls.subclasses.foreach(addWithSubclasses)
        }
        def addAllTraitImpls(): Unit = {
          val trt = meth.in.asInstanceOf[Trait]
          top.tables.traitDispatchImpls(sig).foreach {
            case impl @ Val.Global(name, _) =>
              val implMeth = top.nodes(name).asInstanceOf[Method]
              val implCls  = implMeth.in.asInstanceOf[Class]
              if (implCls.is(trt)) {
                add(ty, impl)
              }
            case _ =>
              ()
          }
        }

        ty match {
          case Type.Exact(ClassRef(cls)) => addExactClass(cls)
          case Type.Class(ClassRef(cls)) => addWithSubclasses(cls)
          case Type.Trait(_)             => addAllTraitImpls()
          case Type.Unit =>
            addExactClass(
              top
                .nodes(Global.Top("scala.scalanative.runtime.BoxedUnit$"))
                .asInstanceOf[Class])
          case ty => util.unsupported(ty)
        }
      }
    } else {
      util.unreachable
    }

    val res = impls.distinct
    // if (res.size > 1) {
    //   println(s"dynamic method call ${ty.show} on ${methName.show}")
    //   srcs.foreach(println)
    // }
    res
  }
}

object Expand {
  def apply(defns: Seq[Defn],
            dyns: Seq[String],
            entries: Seq[Global]): Seq[Defn] = {
    val hoisted  = (new pass.ExternHoisting).onDefns(defns)
    val top      = analysis.ClassHierarchy(hoisted, dyns)
    val expander = new Expand()(top)
    val methods  = expander.loop(entries)

    methods ++ (defns.filter {
      case _: Defn.Declare | _: Defn.Define => false
      case _                                => true
    })
  }
}
