package scala.scalanative
package interflow

import scala.util.{Try, Success, Failure}
import scalanative.nir._
import scalanative.linker._

trait Inline { self: Interflow =>
  def shallInline(name: Global, args: Seq[Val])(
      implicit state: State,
      linked: linker.Result): Boolean =
    done
      .get(name)
      .fold[Boolean] {
        false
      } { defn =>
        val isCtor = originalName(name) match {
          case Global.Member(_, _: Sig.Ctor) =>
            true
          case Global.Member(_, Sig.Method("$init$", _)) =>
            true
          case _ =>
            false
        }
        val isSmall =
          defn.insts.size <= 8
        val hasVirtualArgs =
          args.exists(_.isInstanceOf[Val.Virtual])
        val noInline =
          defn.attrs.inline == Attr.NoInline
        val hintInline =
          defn.attrs.inline == Attr.AlwaysInline || defn.attrs.inline == Attr.InlineHint
        val isRecursive =
          context.contains(s"inlining ${name.show}")
        val isBlacklisted =
          blacklist.contains(name)
        val calleeTooBig =
          defn.insts.size > 8192
        val callerTooBig =
          mergeProcessor.currentSize() > 8192

        val shall =
          isCtor || hintInline || isSmall || (mode == build.Mode.Release && hasVirtualArgs)
        val shallNot =
          noInline || isRecursive || isBlacklisted || calleeTooBig || callerTooBig

        if (shall) {
          if (shallNot) {
            log(s"not inlining ${name.show}, because:")
            if (noInline) { log("* has noinline attr") }
            if (isRecursive) { log("* is recursive") }
            if (isBlacklisted) { log("* is blacklisted") }
            if (callerTooBig) { log("* caller is too big") }
            if (calleeTooBig) { log("* callee is too big") }
          }
        } else {
          log(
            s"no reason to inline ${name.show}(${args.map(_.show).mkString(",")})")
        }

        shall && !shallNot
      }

  def inline(name: Global, args: Seq[Val])(implicit state: State,
                                           linked: linker.Result): Val =
    in(s"inlining ${name.show}") {
      val defn      = done(name)
      val processor = process(defn.insts.toArray, args, state, inline = true)

      val (insts, res, endState) = processor.toInlineInsts(state)

      state.emit ++= insts
      state.inherit(endState, res +: args)
      res
    }
}
