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
  sealed abstract class Task

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
          Global.Member(in, id.split("_")(0) + suffix)
        case _ => util.unreachable
      }
    }

    /** Original method. */
    def meth: Method = top.nodes(name).asInstanceOf[Method]
  }

  /** Resume previously suspended expansion. */
  final class Resume(val state: State) extends Task

  /** Suspendable state of a given method expansion. */
  final class State(val expand: Expand,
                    val env: mutable.Map[Local, Known],
                    val fresh: nir.Fresh,
                    val buf: nir.Buffer,
                    val cfg: ControlFlow.Graph,
                    val blocks: Array[Array[Inst]],
                    var blockIdx: Int,
                    var instIdx: Int)

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

  private val done   = mutable.Map.empty[Expand, Defn]
  private val todo   = mutable.Queue.empty[Task]
  private val insert = mutable.UnrolledBuffer.empty[Expand]

  /** Entry point of expander work-scheduling loop. */
  def loop(entry: Global): Seq[Defn] = {
    todo.enqueue(new Expand(entry, Seq(Type.Int, Type.Ptr)))

    while (!todo.isEmpty) {
      val task = todo.dequeue()
      task match {
        case task: Expand if !done.contains(task) =>
          start(task)
        case task: Resume if !done.contains(task.state.expand) =>
          resume(task.state)
        case _ =>
          ()
      }
    }

    insert.distinct.map(done)
  }

  def expand_?(name: Global, argtys: Seq[Type]): Option[Expand] = {
    val Type.Function(origintys, _) = top.nodes(name).asInstanceOf[Method].ty

    if (name.isTop) {
      Some(new Expand(name, origintys))

    } else {
      val exptys = origintys.zip(argtys).flatMap {
        case (origty, argty) =>
          top.glb(origty, argty)
      }

      if (exptys.size != origintys.size) {
        println(s"can't expand ${name.show} with ${argtys.map(_.show)}")
        println(s"  incomplete glb ${exptys.map(_.show)}")
        println(s"  wrt to origin ${origintys.map(_.show)}")
        None
      } else {
        Some(new Expand(name, exptys))
      }
    }
  }

  def request_?(expand: Expand): Option[Defn] =
    done
      .get(expand)
      .fold[Option[Defn]] {
        println(s"requesting new ${expand.expandedName.show}")
        todo.enqueue(expand)
        None
      } { defn =>
        println(s"requested done ${expand.expandedName.show}")
        Some(defn)
      }

  def start(expand: Expand): Unit = {
    println(s"starting ${expand.expandedName.show}")
    insert += expand

    val insts = expand.meth.insts

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

  def suspend(state: State): Unit = {
    println(s"suspending ${state.expand.expandedName.show}")

    todo.enqueue(new Resume(state))
  }

  def resume(state: State): Unit = {
    import state._

    println(s"resuming ${state.expand.expandedName.show}")

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
                  env(param.name) = HasType(exactify(ty))
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
            val argtys = args.map(arg => known(arg).ty)
            val sig    = Type.Function(argtys, exactify(origSig.retty))

            known(func) match {
              case GlobalOf(methName) =>
                val subexpand = expand_?(methName, argtys).get
                val result    = request_?(subexpand)
                if (result.isEmpty) return suspend(state)
                val callable = result.get.asInstanceOf[Defn.Callable]
                val sig      = callable.sig.asInstanceOf[Type.Function]
                val func     = Val.Global(callable.name, Type.Ptr)
                buf.let(name, Op.Call(sig, func, args, unwind))
                env(name) = HasType(exactify(sig.retty))

              case MethodOf(obj, methName) =>
                val impls = resolveMethod(known(obj).ty, methName)
                val subexpands =
                  impls.flatMap(impl => expand_?(impl.name, argtys))
                val results = subexpands.flatMap(request_?)

                subexpands match {
                  case Seq() =>
                    buf.unreachable
                    buf.label(fresh())
                  case Seq(subexpand) =>
                    val func = Val.Global(subexpand.expandedName, Type.Ptr)
                    buf.let(name, Op.Call(sig, func, args, unwind))
                  case expands =>
                    val expandedName =
                      expand_?(methName, argtys).get.expandedName
                    val meth =
                      buf.method(withTy(obj, known(obj).ty), expandedName)
                    buf.let(name, Op.Call(sig, meth, args, unwind))
                }

                env(name) = HasType(exactify(sig.retty))

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
      println(s"completing ${expand.expandedName.show}")

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

      done(expand) = if (insts.isEmpty) {
        Defn.Declare(attrs, name, sig)
      } else {
        Defn.Define(attrs, name, sig, insts)
      }

      println(s"done ${expand.expandedName.show}")
    }
  }

  def withTy(v: Val, ty: Type): Val = v match {
    case Val.Local(name, _) => Val.Local(name, ty)
    case _                  => v
  }

  def exactify(ty: Type): Type = ty match {
    case ClassRef(cls) if cls.range.size == 1 =>
      Type.Exact(cls.name)
    case _ =>
      ty
  }

  def classWithName(clsName: Global): Class =
    top.nodes(clsName).asInstanceOf[Class]

  /** Returns all possible resolutions of a given method on given type. */
  def resolveMethod(ty: Type, methName: Global): Seq[Val.Global] = {
    val meth  = top.nodes(methName).asInstanceOf[Method]
    val impls = mutable.UnrolledBuffer.empty[Val.Global]

    def add(v: Val): Unit = v match {
      case v: Val.Global => impls += v
      case _             => ()
    }

    if (meth.inClass) {
      if (meth.isStatic) {
        add(Val.Global(methName, Type.Ptr))
      } else {
        def addExactClass(cls: Class): Unit =
          add(cls.vtable.at(cls.vtable.index(meth)))
        def addWithSubclasses(cls: Class): Unit = {
          addExactClass(cls)
          cls.subclasses.foreach(addWithSubclasses)
        }

        ty match {
          case Type.Exact(ClassRef(cls)) => addExactClass(cls)
          case Type.Class(ClassRef(cls)) => addWithSubclasses(cls)
          case Type.Trait(_)             => addWithSubclasses(classWithName(Rt.Object.name))
          case _                         => util.unreachable
        }
      }
    } else if (meth.inTrait) {
      val sig = meth.name.id

      if (top.tables.traitInlineSigs.contains(sig)) {
        add(top.tables.traitInlineSigs(sig))
      } else {
        def addExactClass(cls: Class): Unit = {
          val sigid  = top.tables.traitDispatchSigs(sig)
          val offset = top.tables.dispatchOffset(sigid)
          add(top.tables.dispatchArray(offset + cls.range.start))
        }
        def addWithSubclasses(cls: Class): Unit = {
          addExactClass(cls)
          cls.subclasses.foreach(addWithSubclasses)
        }
        def addAllTraitImpls(): Unit =
          top.tables.traitDispatchImpls(sig).foreach(add)

        ty match {
          case Type.Exact(ClassRef(cls)) => addExactClass(cls)
          case Type.Class(ClassRef(cls)) => addWithSubclasses(cls)
          case Type.Trait(_)             => addAllTraitImpls()
          case _                         => util.unreachable
        }
      }
    } else {
      util.unreachable
    }

    impls.distinct
  }
}

object Expand {
  def apply(defns: Seq[Defn], dyns: Seq[String], entry: Global): Seq[Defn] = {
    val hoisted  = (new pass.ExternHoisting).onDefns(defns)
    val top      = analysis.ClassHierarchy(hoisted, dyns)
    val expander = new Expand()(top)
    expander.loop(entry)
  }
}
