package scala.scalanative
package nir
package parser

import fastparse.all._
import Base._

object Op extends Base[nir.Op] {

  import Base.IgnoreWhitespace._

  private val unwind: P[Next] =
    P(Next.parser.?).map(_.getOrElse(nir.Next.None))
  private val targ: P[Type] =
    P("[" ~ Type.parser ~ "]")

  val Call =
    P(
      "call" ~ targ ~ Val.parser ~ "(" ~ Val.parser
        .rep(sep = ",") ~ ")" ~ unwind).map {
      case (ty, f, args, unwind) => nir.Op.Call(ty, f, args, unwind)
    }
  val Load =
    P("volatile".!.? ~ "load" ~ targ ~ Val.parser map {
      case (volatile, ty, ptr) =>
        nir.Op.Load(ty, ptr, isVolatile = volatile.nonEmpty)
    })
  val Store =
    P("volatile".!.? ~ "store" ~ targ ~ Val.parser ~ "," ~ Val.parser map {
      case (volatile, ty, ptr, value) =>
        nir.Op.Store(ty, ptr, value, isVolatile = volatile.nonEmpty)
    })
  val Elem =
    P("elem" ~ targ ~ Val.parser ~ "," ~ Val.parser.rep(sep = ",") map {
      case (ty, ptr, indices) => nir.Op.Elem(ty, ptr, indices)
    })
  val Extract =
    P("extract" ~ Val.parser ~ "," ~ int.rep(sep = ",") map {
      case (aggr, indices) => nir.Op.Extract(aggr, indices)
    })
  val Insert =
    P("insert" ~ Val.parser ~ "," ~ Val.parser ~ "," ~ int.rep(sep = ",") map {
      case (aggr, value, indices) => nir.Op.Insert(aggr, value, indices)
    })
  val Stackalloc =
    P("stackalloc" ~ targ ~ Val.parser.? map {
      case (ty, n) => nir.Op.Stackalloc(ty, n getOrElse nir.Val.None)
    })
  val Bin =
    P(nir.parser.Bin.parser ~ targ ~ Val.parser ~ "," ~ Val.parser map {
      case (name, ty, l, r) => nir.Op.Bin(name, ty, l, r)
    })
  val Comp =
    P(nir.parser.Comp.parser ~ targ ~ Val.parser ~ "," ~ Val.parser map {
      case (name, ty, l, r) => nir.Op.Comp(name, ty, l, r)
    })
  val Conv =
    P(nir.parser.Conv.parser ~ targ ~ Val.parser map {
      case (name, ty, v) => nir.Op.Conv(name, ty, v)
    })
  val Select =
    P("select" ~ Val.parser ~ "," ~ Val.parser ~ "," ~ Val.parser map {
      case (cond, thenp, elsep) => nir.Op.Select(cond, thenp, elsep)
    })
  val Classalloc = P("classalloc" ~ Global.parser map (nir.Op.Classalloc(_)))
  val Fieldload =
    P("fieldload" ~ targ ~ Val.parser ~ "," ~ Global.parser map {
      case (ty, v, n) => nir.Op.Fieldload(ty, v, n)
    })
  val Fieldstore =
    P("fieldstore" ~ targ ~ Val.parser ~ "," ~ Global.parser ~ "," ~ Val.parser map {
      case (ty, v1, n, v2) => nir.Op.Fieldstore(ty, v1, n, v2)
    })
  val Method =
    P("method" ~ Val.parser ~ "," ~ Global.parser map {
      case (value, name) => nir.Op.Method(value, name)
    })
  val Dynmethod =
    P("dynmethod" ~ Val.parser ~ "," ~ Base.stringLit map {
      case (obj, signature) => nir.Op.Dynmethod(obj, signature)
    })
  val Module = P("module" ~ Global.parser ~ unwind).map {
    case (name, unwind) =>
      nir.Op.Module(name, unwind)
  }
  val As =
    P("as" ~ targ ~ Val.parser map {
      case (ty, value) => nir.Op.As(ty, value)
    })
  val Is =
    P("is" ~ targ ~ Val.parser map {
      case (ty, value) => nir.Op.Is(ty, value)
    })
  val Copy   = P("copy" ~ Val.parser map (nir.Op.Copy(_)))
  val Sizeof = P("sizeof" ~ targ map (nir.Op.Sizeof(_)))
  val Closure =
    P("closure" ~ targ ~ Val.parser.rep(sep = ",") map {
      case (ty, fun +: captures) => nir.Op.Closure(ty, fun, captures)
    })
  val Box = P("box" ~ targ ~ Val.parser map {
    case (ty, obj) => nir.Op.Box(ty, obj)
  })
  val Unbox = P("unbox" ~ targ ~ Val.parser map {
    case (ty, obj) => nir.Op.Unbox(ty, obj)
  })
  override val parser: P[nir.Op] =
    Call | Load | Store | Elem | Extract | Insert | Stackalloc | Bin | Comp | Conv | Select | Classalloc | Fieldload | Fieldstore | Method | Module | As | Is | Copy | Sizeof | Closure | Box | Unbox
}
