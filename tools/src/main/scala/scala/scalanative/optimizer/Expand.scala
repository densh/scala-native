package scala.scalanative
package optimizer

import scala.collection.mutable
import scalanative.nir._
import scalanative.optimizer.analysis.ControlFlow
import scalanative.optimizer.analysis.ClassHierarchy._
import scalanative.optimizer.analysis.ClassHierarchyExtractors._

sealed abstract class State {
  def ty: Type
}
final case class HasType(override val ty: Type) extends State
final case class MethodOf(obj: Val, meth: Global) extends State {
  def ty: Type = Type.Ptr
}
final case class GlobalOf(val name: Global) extends State {
  def ty: Type = Type.Ptr
}

// TODO: expand basic blocks
// TODO: infer retty as lub of all returns

class Expand(implicit top: Top) {
  private val out      = mutable.UnrolledBuffer.empty[Defn]
  private val expanded = mutable.Set.empty[Expansion]
  private val todo     = mutable.Stack.empty[Expansion]

  final case class Expansion(name: Global, argtys: Seq[Type]) {

    /** Returns new signature with given argtys. */
    def signature: Type = {
      val Type.Function(_, retty) = top.nodes(name).asInstanceOf[Method].ty
      Type.Function(argtys, retty)
    }

    /** Returns a new name for the expandsion of a method with given types. */
    def expandedName: Global = {
      val suffix = argtys.map(_.mangled).mkString("<",";",">")
      name match {
        case Global.Top(id)        => Global.Top(id + suffix)
        case Global.Member(in, id) => Global.Member(in, id + suffix)
      }
    }
  }

  def expand(entry: Global): Seq[Defn] = {
    val main = Expansion(entry, Seq(Type.Int, Type.Ptr))
    todo.push(main)

    while (!todo.isEmpty) {
      val expansion = todo.pop()
      if (!expanded.contains(expansion)) {
        out += expand(expansion)
        expanded += expansion
      }
    }

    out
  }

  def singleInhabited(ty: Type): Boolean = ty match {
    case ClassRef(cls) if cls.range.size == 1 =>
      true
    case _ =>
      false
  }

  def classWithTraitCount(trtName: Global): Int = {
    val trtId = top.nodes(trtName).asInstanceOf[Trait].id
    val table = top.tables.classHasTraitTable
    var count = 0
    var clsId = 0
    while (clsId < top.classes.size) {
      if (top.tables.classHasTraitTable(clsId)(trtId)) count += 1
      clsId += 1
    }
    count
  }

  /** Pick the most specific type out of the two, aka greatest lower bound. */
  def glb(ty1: Type, ty2: Type): Option[Type] = (ty1, ty2) match {
    case _ if ty1 == ty2 =>
      Some(ty1)
    case (_, TraitRef(_)) if ty1 == Rt.Object =>
      Some(ty2)
    case (TraitRef(_), ty2) if ty2 == Rt.Object =>
      Some(ty1)
    case (ClassRef(cls1), ClassRef(cls2)) =>
      if (cls1.id == cls2.id) {
        Some(ty1)
      } else if (cls1.is(cls2) && !cls2.is(cls1)) {
        Some(ty1)
      } else if (cls2.is(cls1) && !cls1.is(cls2)) {
        Some(ty2)
      } else {
        None
      }
    case (ClassRef(cls), TraitRef(trt)) =>
      if (cls.is(trt)) {
        Some(ty1)
      } else {
        None
      }
    case (TraitRef(trt), ClassRef(cls)) =>
      if (cls.is(trt)) {
        Some(ty2)
      } else {
        None
      }
    case (TraitRef(trt1), TraitRef(trt2)) =>
      if (trt1.is(trt2) && !trt2.is(trt1)) {
        Some(ty1)
      } else if (trt2.is(trt1) && !trt1.is(trt2)) {
        Some(ty2)
      } else if (trt1.is(trt1) && trt2.is(trt1)) {
        Some(ty1)
      } else {
        None
      }
    case (_: Type.RefKind, Type.Ptr) =>
      Some(ty1)
    case (Type.Ptr, _: Type.RefKind) =>
      Some(ty2)
    case (_: Type.RefKind, Type.Unit) =>
      Some(ty2)
    case (Type.Unit, _: Type.RefKind) =>
      Some(ty2)
    case (Type.Char, Type.Short) | (Type.Short, Type.Char) =>
      Some(Type.Char)
    case _ =>
      util.unsupported(s"glb(${ty1.show}, ${ty2.show})")
  }

  def enqueue(name: Global, argtys: Seq[Type]): Option[Expansion] = {
    val Type.Function(origintys, _) = top.nodes(name).asInstanceOf[Method].ty

    if (name.isTop) {
      val expansion = Expansion(name, origintys)
      todo.push(expansion)
      Some(expansion)

    } else {
      val exptys = origintys.zip(argtys).flatMap {
        case (origty, argty) =>
          glb(origty, argty)
      }

      if (exptys.size != origintys.size) {
        None
      } else {
        val expansion = Expansion(name, exptys)
        todo.push(expansion)
        Some(expansion)
      }
    }
  }

  def expand(expansion: Expansion): nir.Defn = {
    println(s"--- expanding ${expansion.expandedName.show}")

    val meth  = top.nodes(expansion.name).asInstanceOf[Method]
    val attrs = meth.attrs.copy(overrides = Seq.empty)

    if (meth.insts.isEmpty) {
      Defn.Declare(attrs, expansion.expandedName, expansion.signature)
    } else {
      val insts = expandInsts(expansion.name, meth.insts, expansion.argtys)
      Defn.Define(attrs, expansion.expandedName, expansion.signature, insts)
    }
  }

  def expandInsts(expName: Global, insts: Seq[Inst], argtys: Seq[Type]): Seq[nir.Inst] = {
    val env   = mutable.Map.empty[Local, State]
    val fresh = nir.Fresh(insts)
    val buf   = new nir.Buffer()(fresh)

    def known(v: Val): State = v match {
      case Val.Local(name, _)  => env(name)
      case Val.Global(name, _) => GlobalOf(name)
      case _                   => HasType(v.ty)
    }

    def withTy(v: Val, ty: Type): Val = v match {
      case Val.Local(name, _) => Val.Local(name, ty)
      case _                  => v
    }

    val cfg = ControlFlow.Graph(insts)
    cfg.foreach { block =>
      if (block ne cfg.entry) {
        block.params.foreach { param =>
          env(param.name) = HasType(param.ty)
        }
        buf += block.label
      } else {
        val params = mutable.UnrolledBuffer.empty[Val.Local]
        block.params.zip(argtys).foreach {
          case (param, ty) =>
            env(param.name) = HasType(ty)
            params += Val.Local(param.name, ty)
        }
        buf.label(block.label.name, params)
      }
      block.insts.foreach {
        case inst @ Inst.Let(name, op @ Op.Call(sig, func, args, unwind)) =>
          env(name) = HasType(op.resty)
          val argtys = args.map(arg => known(arg).ty)

          known(func) match {
            case GlobalOf(methName) =>
              val expansion = enqueue(methName, argtys).get
              val expanded = Val.Global(expansion.expandedName, Type.Ptr)
              buf.let(name, Op.Call(sig, expanded, args, unwind))

            case MethodOf(obj, methName) =>
              val single     = singleInhabited(known(obj).ty)
              val impls      = resolveMethod(known(obj).ty, methName)
              val expansions = impls.flatMap(impl => enqueue(impl.name, argtys))

              expansions match {
                case Seq() =>
                  buf.unreachable
                  buf.label(fresh())
                case Seq(expansion) =>
                  val expanded = Val.Global(expansion.expandedName, Type.Ptr)
                  buf.let(name, Op.Call(sig, expanded, args, unwind))
                case expansions =>
                  val expandedName = enqueue(methName, argtys).get.expandedName
                  val meth         = buf.method(withTy(obj, known(obj).ty), expandedName)
                  buf.let(name, Op.Call(sig, meth, args, unwind))
              }

            case _ =>
              buf += inst
          }

        case inst @ Inst.Let(name, op) =>
          op match {
            case Op.Method(obj, meth) =>
              env(name) = MethodOf(obj, meth)
            case _ =>
              env(name) = HasType(op.resty)
              buf += inst
          }

        case inst =>
          buf += inst
      }
    }

    buf.toSeq
  }

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
        def loop(cls: Class): Unit = {
          add(cls.vtable.at(cls.vtable.index(meth)))
          cls.subclasses.foreach(loop)
        }
        val clsName = ty match {
          case Type.Class(name)  => name
          case Type.Module(name) => name
          case Type.Trait(_)     => Rt.Object.name
          case _                 => util.unreachable
        }
        val cls = top.nodes(clsName).asInstanceOf[Class]
        loop(cls)
      }
    } else if (meth.inTrait) {
      val sig = meth.name.id

      if (top.tables.traitInlineSigs.contains(sig)) {
        add(top.tables.traitInlineSigs(sig))
      } else {
        ty match {
          case ClassRef(cls) if cls.range.size == 1 =>
            val sigid  = top.tables.traitDispatchSigs(sig)
            val offset = top.tables.dispatchOffset(sigid)
            add(top.tables.dispatchArray(offset + cls.range.start))
          case _ =>
            top.tables.traitDispatchImpls(sig).foreach(add)
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
    expander.expand(entry)
  }
}
