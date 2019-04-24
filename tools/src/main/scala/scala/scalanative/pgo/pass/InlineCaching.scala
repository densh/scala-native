package scala.scalanative
package pgo
package pass

import java.io.File
import scala.io.Source
import scala.collection.mutable
import scalanative.nir._, Inst.Let

/**
 * Inline caching based on information gathered at runtime.
 * Transforms polymorphic call sites to a sequence of type tests and static
 * dispatches. Falls back to virtual dispatch if all type tests fail.
 */
class InlineCaching(profile: File)(implicit linked: linker.Result)
    extends Pass {
  import InlineCaching._

  assert(profile.exists)
  val dispatchInfo =
    analysis.DispatchInfoParser(Source.fromFile(profile).mkString).map {
      case (key, values) =>
        val total = values.map(_._2).sum.toDouble
        val pvalues = values.sortBy(-_._2).map {
          case (typeId, count) =>
            (typeId, count / total, count)
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
        val sig = method.name.id
        if (top.tables.traitInlineSigs.contains(sig)) {
          top.tables.traitInlineSigs(sig)
        } else {
          val sigid  = top.tables.traitDispatchSigs(sig)
          val offset = top.tables.dispatchOffset(sigid)
          top.tables.dispatchArray(offset + typeId)
        }
      } else {
        util.unreachable
      }
    }

  private def selectCandidates(
      info: Seq[(TypeID, Double, Int)]): Seq[(TypeID, Double, Int)] =
    INLINE_CACHING_MODE match {
      case NoInlineCaching =>
        Seq.empty
      case InlineCacheAll =>
        info
      case InlineCacheN(n) =>
        info.take(n)
      case InlineCacheP(threshold) =>
        var left = 1.0D
        info.takeWhile {
          case (id, p, _) =>
            if (p / left >= threshold) {
              left -= p
              true
            } else {
              false
            }
        }
      case InlineCacheNP(n, threshold) =>
        if (info.size <= n) {
          info
        } else {
          var left = 1.0D
          info.takeWhile {
            case (id, p, _) =>
              if (p / left >= threshold) {
                left -= p
                true
              } else {
                false
              }
          }
        }
    }

  private def ic(
      name: Global,
      buf: Buffer,
      inst: Let,
      callop: Op.Call,
      meth: Op.Method,
      info: Seq[(TypeID, Double, Int)])(implicit fresh: Fresh): Unit = {
    import buf._

    val candidates = selectCandidates(info).map {
      case (id, p, count) =>
        (id, fresh(), fresh(), resolve(meth.name, id).get, count)
    }

    val fail, merge = fresh()

    def genCheck(check: Local,
                 typeId: Int,
                 succ: Local,
                 fail: Local,
                 warmth: Int) = {
      label(check, Seq.empty, warmth)
      val ty   = load(Type.Ptr, meth.obj)
      val id   = load(Type.Int, ty)
      val cond = comp(Comp.Ieq, Type.Int, id, Val.Int(typeId))
      branch(cond, Next(succ), Next(fail))
    }

    def genStatic(succ: Local, impl: Val, warmth: Int) = {
      label(succ, Seq.empty, warmth)
      val succres = let(callop.copy(ptr = impl))
      jump(merge, Seq(succres))
    }

    def genFail(warmth: Int) = {
      label(fail, Seq.empty, warmth)
      val failmeth = let(meth)
      val failres  = let(callop.copy(ptr = failmeth))
      jump(merge, Seq(failres))
    }

    val total     = info.map(_._3).sum
    var remaining = total

    def loop(candidates: Seq[(Int, Local, Local, Val, Int)]): Unit =
      candidates match {
        case Seq() =>
          genFail(remaining)
          label(merge, Seq(Val.Local(inst.name, callop.resty)), total)
        case Seq((id, check, succ, impl, count)) =>
          genCheck(check, id, succ, fail, remaining)
          genStatic(succ, impl, count)
          remaining -= count
          loop(Seq())
        case (id, check, succ, impl, count) +: (rest @ ((_, ncheck, _, _, _) +: _)) =>
          genCheck(check, id, succ, ncheck, remaining)
          genStatic(succ, impl, count)
          remaining -= count
          loop(rest)
      }

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
            .contains((defnId.toLong << 32) | local.id) =>
        val info = dispatchInfo((defnId.toLong << 32) | local.id)
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
  final val INLINE_CACHING_MODE = InlineCacheNP(2, 0.9)

  sealed abstract class InlineCachingMode
  final case object NoInlineCaching                 extends InlineCachingMode
  final case object InlineCacheAll                  extends InlineCachingMode
  final case class InlineCacheN(n: Int)             extends InlineCachingMode
  final case class InlineCacheP(p: Double)          extends InlineCachingMode
  final case class InlineCacheNP(n: Int, p: Double) extends InlineCachingMode

  override def apply(config: build.Config, linked: linker.Result) = {
    val build.UseProfile(profile) = config.profileMode
    new InlineCaching(profile)(linked)
  }
}
