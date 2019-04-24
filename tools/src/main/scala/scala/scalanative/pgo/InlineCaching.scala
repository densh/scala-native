package scala.scalanative
package pgo

import java.io.File
import scala.io.Source
import scala.collection.mutable
import scalanative.linker._
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
    parseDispatchInfo(profile).map {
      case (key, values) =>
        val total = values.map(_._2).sum.toDouble
        val pvalues = values.sortBy(-_._2).map {
          case (typeId, count) =>
            val typeName = linked.fromIds(typeId)
            (typeName, count / total, count)
        }
        (key, pvalues)
    }

  type Count  = Int

  private def resolve(typeName: Global, sig: Sig): Option[Val] =
    linked.infos.get(typeName).collect {
      case info: linker.Class =>
        info.resolve(sig).map(Val.Global(_, Type.Ptr))
    }.flatten

  private def selectCandidates(
      info: Seq[(Global, Double, Int)]): Seq[(Global, Double, Int)] =
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
      info: Seq[(Global, Double, Int)])(implicit fresh: Fresh): Unit = {
    import buf._

    val candidates = selectCandidates(info).map {
      case (id, p, count) =>
        (id, fresh(), fresh(), resolve(id, meth.sig).get, count)
    }

    val fail, merge = fresh()

    def genCheck(check: Local,
                 typeName: Global,
                 succ: Local,
                 fail: Local,
                 warmth: Int) = {
      label(check, Seq.empty, warmth)
      val ty = call(Rt.GetRawTypeTy, Rt.GetRawType, Seq(Val.Null, meth.obj), Next.None)
      val cond = comp(Comp.Ieq, Type.Ptr, ty, Val.Global(typeName, Type.Ptr), Next.None)
      branch(cond, Next(succ), Next(fail))
    }

    def genStatic(succ: Local, impl: Val, warmth: Int) = {
      label(succ, Seq.empty, warmth)
      val succres = let(callop.copy(ptr = impl), Next.None)
      jump(merge, Seq(succres))
    }

    def genFail(warmth: Int) = {
      label(fail, Seq.empty, warmth)
      val failmeth = let(meth, Next.None)
      val failres  = let(callop.copy(ptr = failmeth), Next.None)
      jump(merge, Seq(failres))
    }

    val total     = info.map(_._3).sum
    var remaining = total

    def loop(candidates: Seq[(Global, Local, Local, Val, Int)]): Unit =
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
      val precond = comp(Comp.Ine, Type.Ptr, meth.obj, Val.Null, Next.None)
      branch(precond, Next(candidates.head._2), Next(fail))
      loop(candidates)
    } else {
      buf += inst
    }
  }

  def onDefn(defn: Defn.Define): Seq[Inst] = {
    val insts  = defn.insts

    implicit val scope = LocalScope(insts)
    implicit val fresh = Fresh(insts)
    val buf            = new Buffer

    insts.foreach {
      case inst @ Let(_,
                      call @ Op.Call(
                        _,
                        LocalRef(
                          meth @ Op.Method(Val.Local(local, _), sig)),
                        _), Next.None)
          if linked.ids.contains(defn.name)
          && dispatchInfo.contains(linked.ids(defn.name)) =>
        val info = dispatchInfo(linked.ids(defn.name))
        ic(defn.name, buf, inst, call, meth, info)
      case inst =>
        buf += inst
    }

    buf.toSeq
  }

  def onDefns(defns: Seq[Defn]): Seq[Defn] = defns.map {
    case defn: Defn.Define if linked.infos.contains(defn.name) =>
      defn.copy(insts = onDefn(defn))
    case defn =>
      defn
  }
}

object InlineCaching extends PassCompanion {
  final val INLINE_CACHING_MODE: InlineCachingMode = InlineCacheNP(2, 0.9)

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



  // private val IgnoreWhitespace = WhitespaceApi.Wrapper {
  //   import fastparse.all._
  //   NoTrace(CharIn(Seq(' ', '\t', '\n')).rep)
  // }
  // import IgnoreWhitespace._

  // val number: P[Int] = P(CharIn('0' to '9').rep(1).!.map(_.toInt))

  // val dispatchHeader: P[Long] =
  //   P("=" ~ "`" ~ CharsWhile(_ != '`').! ~ "`" ~ ":") map {
  //     case name => name.toLong
  //   }

  // val dispatchMethod: P[(Long, Seq[(Int, Int)])] =
  //   dispatchHeader ~ (number ~ "(" ~ number ~ ")").rep(1) map {
  //     case (header, entries) =>
  //       (header, entries)
  //   }

  // val dispatchInfo: P[Map[Long, Seq[(Int, Int)]]] =
  //   dispatchMethod.rep ~ End map (_.toMap)

  def parseDispatchInfo(profile: File): Map[Int, Seq[(Int, Int)]] = {
    val in = Source.fromFile(profile).mkString
    ???
  }
    // dispatchInfo.parse(in) match {
    //   case Parsed.Success(info, _) => info
    //   case Parsed.Failure(_, _, _) => Map.empty
    // }
}
