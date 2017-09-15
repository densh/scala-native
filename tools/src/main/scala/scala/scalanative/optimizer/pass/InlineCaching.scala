package scala.scalanative
package optimizer
package pass

import java.io.File
import scala.io.Source
import scala.collection.mutable
import scalanative.tools._
import scalanative.optimizer.analysis.{LocalScope, LocalRef}
import analysis.ClassHierarchy._
import analysis.ClassHierarchyExtractors._
import analysis.ControlFlow, ControlFlow.Block
import nir._, Inst.Let

/**
 * Inline caching based on information gathered at runtime.
 * Transforms polymorphic call sites to a sequence of type tests and static
 * dispatches. Falls back to virtual dispatch if all type tests fail.
 */
class InlineCaching(profile: File)(implicit top: Top) extends Pass {
  import InlineCaching._

  assert(profile.exists)
  val dispatchInfo =
    analysis.DispatchInfoParser(Source.fromFile(profile).mkString).map {
      case (key, values) =>
        val total = values.map(_._2).sum.toDouble
        val pvalues = values.sortBy(-_._2).map {
          case (typeId, count) =>
            (typeId, count / total)
        }
        (key, pvalues)
    }

  type TypeID = Int
  type Count  = Int

  private def resolve(meth: Global, typeId: TypeID): Option[Val] =
    top.classWithId(typeId).map { scope =>
      val cls    = scope.asInstanceOf[Class]
      val method = top.nodes(meth).asInstanceOf[Method]

      if (method.inClass) {
        val index = cls.vtable.index(method)
        assert(
          index >= 0,
          s"method ${method.name.show} can not be found in vtable of ${cls.name.show} = ${cls.vtable}")
        cls.vtable.at(index)
      } else if (method.inTrait) {
        val sigid  = top.tables.traitMethodSigs(method.name.id)
        val offset = top.tables.dispatchOffset(sigid)
        top.tables.dispatchArray(offset + typeId)
      } else {
        util.unreachable
      }
    }

  private def ic(name: Global,
                 buf: Buffer,
                 inst: Let,
                 callop: Op.Call,
                 meth: Op.Method,
                 info: Seq[(TypeID, Double)])(implicit fresh: Fresh): Unit = {
    import buf._

    val applicable = {
      var left = 1.0D
      info.takeWhile { case (id, p) =>
        if (p/left > THRESHOLD) {
          left -= p
          true
        } else {
          false
        }
      }
    }

    val candidates = applicable.map { case (id, p) =>
      (id, fresh(), fresh(), resolve(meth.name, id).get)
    }

    val fail, merge = fresh()

    def genCheck(check: Local, typeId: Int, succ: Local, fail: Local) = {
      label(check)
      val ty   = load(Type.Ptr, meth.obj)
      val id   = load(Type.Int, ty)
      val cond = comp(Comp.Ieq, Type.Int, id, Val.Int(typeId))
      branch(cond, Next(succ), Next(fail))
    }

    def genStatic(succ: Local, impl: Val) = {
      label(succ)
      val succres = let(callop.copy(ptr = impl))
      jump(merge, Seq(succres))
    }

    def genFail() = {
      label(fail)
      val failmeth = let(meth)
      val failres  = let(callop.copy(ptr = failmeth))
      jump(merge, Seq(failres))
    }

    def loop(candidates: Seq[(Int, Local, Local, Val)]): Unit =
      candidates match {
        case Seq() =>
          genFail()
          label(merge, Seq(Val.Local(inst.name, callop.resty)))
        case Seq((id, check, succ, impl)) =>
          genCheck(check, id, succ, fail)
          genStatic(succ, impl)
          loop(Seq())
        case (id, check, succ, impl) +: (rest @ ((_, ncheck, _, _) +: _)) =>
          genCheck(check, id, succ, ncheck)
          genStatic(succ, impl)
          loop(rest)
      }

    println(name.show + " " + candidates.size)
    if (candidates.nonEmpty) {
      val precond = comp(Comp.Ine, Type.Ptr, meth.obj, Val.Null)
      branch(precond, Next(candidates.head._2), Next(fail))
      loop(candidates)
    } else {
      buf += inst
    }
  }

  private def onMethod(defn: Defn.Define): Seq[Inst] = {
    val insts  = defn.insts
    val defnId = top.nodes(defn.name).id

    implicit val scope = LocalScope(insts)
    implicit val fresh = Fresh(insts)
    val buf            = new Buffer

    insts.foreach {
      case inst @ Let(_,
                      call @ Op.Call(
                        _,
                        LocalRef(
                          meth @ Op.Method(Val.Local(local, _),
                                           MethodRef(scoperef, methref))),
                        _,
                        _))
          if (methref.isVirtual || scoperef.isInstanceOf[Trait]) && dispatchInfo
            .contains((defnId << 32) | local.id) =>
        val info = dispatchInfo((defnId << 32) | local.id)
        ic(defn.name, buf, inst, call, meth, info)
      case inst =>
        buf += inst
    }

    buf.toSeq
  }

  override def onDefn(defn: Defn): Defn = defn match {
    case defn: Defn.Define if top.nodes.contains(defn.name) =>
      defn.copy(insts = onMethod(defn))

    case _ =>
      defn
  }
}

object InlineCaching extends PassCompanion {
  val MAX_CANDIDATES = 4
  val THRESHOLD = 0.76

  override def apply(config: tools.Config, top: Top) = {
    val UseProfile(profile) = config.profileMode
    new InlineCaching(profile)(top)
  }
}
