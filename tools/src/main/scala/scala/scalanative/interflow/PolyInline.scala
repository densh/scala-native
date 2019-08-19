package scala.scalanative
package interflow

import scala.collection.mutable
import scalanative.nir._
import scalanative.linker._

trait PolyInline { self: Interflow =>
  import PolyInline.Candidate

  private def polyTargets(op: Op.Method)(
      implicit state: State): Seq[Candidate] = {
    val Op.Method(obj, sig, weights) = op

    val objty = obj match {
      case InstanceRef(ty) =>
        ty
      case _ =>
        obj.ty
    }

    val res = objty match {
      case ExactClassRef(cls, _) =>
        cls.resolve(sig).map(g => Candidate(cls, g)).toSeq
      case ScopeRef(scope) =>
        val targets = mutable.UnrolledBuffer.empty[Candidate]
        scope.implementors.foreach { cls =>
          if (cls.allocated) {
            cls.resolve(sig).foreach { g =>
              targets += Candidate(cls, g)
            }
          }
        }
        targets
      case _ =>
        Seq.empty
    }

    res.sortBy(_.cls.name)
  }

  def shallPolyInline(op: Op.Method, args: Seq[Val])(
      implicit state: State,
      linked: linker.Result): Boolean = {
    val targets    = polyTargets(op)
    val classCount = targets.map(_.cls).size
    val implCount  = targets.map(_.impl).distinct.size

    optLevel() match {
      case Opt.None =>
        false
      case Opt.Conservative =>
        classCount <= 8 && implCount == 2
      case Opt.Aggressive =>
        classCount <= 16 && implCount >= 2 && implCount <= 4
    }
  }

  def polyInline(op: Op.Method, args: Seq[Val])(implicit state: State,
                                                linked: linker.Result): Val = {
    import state.{emit, fresh, materialize}

    val obj     = materialize(op.obj)
    val margs   = args.map(materialize(_))
    val targets = polyTargets(op)
    val classes = targets.map(_.cls)
    val impls   = targets.map(_.impl).distinct

    val checkLabels = (1 until targets.size).map(_ => fresh()).toSeq
    val callLabels  = (1 to impls.size).map(_ => fresh()).toSeq
    val callLabelIndex =
      (0 until targets.size).map(i => impls.indexOf(targets(i).impl))
    val mergeLabel = fresh()

    val objty =
      emit.call(Rt.GetRawTypeTy, Rt.GetRawType, Seq(Val.Null, obj), Next.None)

    checkLabels.zipWithIndex.foreach {
      case (checkLabel, idx) =>
        if (idx > 0) {
          emit.label(checkLabel)
        }
        val cls = classes(idx)
        val isCls = emit.comp(Comp.Ieq,
                              Type.Ptr,
                              objty,
                              Val.Global(cls.name, Type.Ptr),
                              Next.None)
        if (idx < targets.size - 2) {
          emit.branch(isCls,
                      Next(callLabels(callLabelIndex(idx))),
                      Next(checkLabels(idx + 1)))
        } else {
          emit.branch(isCls,
                      Next(callLabels(callLabelIndex(idx))),
                      Next(callLabels(callLabelIndex(idx + 1))))
        }
    }

    val rettys = mutable.UnrolledBuffer.empty[Type]

    callLabels.zip(impls).foreach {
      case (callLabel, m) =>
        emit.label(callLabel, Seq.empty)
        val ty                           = originalFunctionType(m)
        val Type.Function(argtys, retty) = ty
        rettys += retty

        val cargs = margs.zip(argtys).map {
          case (value, argty) =>
            if (!Sub.is(value.ty, argty)) {
              emit.conv(Conv.Bitcast, argty, value, Next.None)
            } else {
              value
            }
        }
        val res = emit.call(ty, Val.Global(m, Type.Ptr), cargs, Next.None)
        emit.jump(Next.Label(mergeLabel, Seq(res), -1))
    }

    val result = Val.Local(fresh(), Sub.lub(rettys))
    emit.label(mergeLabel, Seq(result))

    result
  }
}

object PolyInline {
  final case class Candidate(cls: Class, impl: Global)
}
