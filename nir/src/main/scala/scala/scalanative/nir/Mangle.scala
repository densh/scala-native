package scala.scalanative
package nir

import scala.collection.mutable

object Mangle {
  def fieldSig(name: String): String = {
    val impl = new Impl
    impl.sb.str("F")
    impl.mangleIdent(name)
    impl.toString
  }

  def methodSig(name: String, types: Seq[Type]): String = {
    val impl = new Impl
    impl.sb.str("M")
    impl.mangleIdent(name)
    impl.mangleTypes(types)
    impl.toString
  }

  def initSig(types: Seq[Type]): String = {
    val impl = new Impl
    impl.sb.str("R")
    impl.mangleTypes(types)
    impl.toString
  }

  def duplicateSig(sig: String, types: Seq[Type]): String = {
    val impl = new Impl
    impl.sb.str("D")
    impl.sb.str(sig)
    impl.mangleTypes(types)
    impl.toString
  }

  def proxySig(sig: String): String = {
    val impl = new Impl
    impl.sb.str("P")
    impl.sb.str(sig)
    impl.toString
  }

  private class Impl() {
    val sb   = new util.ShowBuilder
    val prev = mutable.Map.empty[String, Int]

    override def toString: String =
      sb.toString

    def mangleTypes(types: Seq[Type]): Unit = {
      sb.rep(types)(mangleType)
      sb.str("E")
    }

    def mangleGlobal(n: Global): Unit = n match {
      case Global.None =>
        util.unreachable
      case Global.Top(id) =>
        mangleIdent(id)
      case Global.Member(g, id) =>
        mangleGlobal(g)
        mangleIdent(id)
    }

    def mangleType(ty: Type): Unit = ty match {
      case nir.Type.None         => util.unreachable
      case nir.Type.Void         => mangleIdent("scala.scalanative.native.Void")
      case nir.Type.Vararg       => mangleIdent("scala.scalanative.native.Vararg")
      case nir.Type.Ptr          => mangleIdent("scala.scalanative.native.Ptr")
      case nir.Type.Bool         => sb.str("z")
      case nir.Type.Char         => sb.str("c")
      case nir.Type.I(8, false)  => sb.str("Ub")
      case nir.Type.I(16, false) => sb.str("Us")
      case nir.Type.I(32, false) => sb.str("Ui")
      case nir.Type.I(64, false) => sb.str("Uj")
      case nir.Type.I(8, true)   => sb.str("b")
      case nir.Type.I(16, true)  => sb.str("s")
      case nir.Type.I(32, true)  => sb.str("i")
      case nir.Type.I(64, true)  => sb.str("j")
      case nir.Type.Float        => sb.str("f")
      case nir.Type.Double       => sb.str("d")
      case nir.Type.ArrayValue(ty, n) =>
        sb.str("A")
        mangleType(ty)
        sb.str("_")
        sb.str(n.toString)
        sb.str("E")
      case nir.Type.StructValue(Global.None, types) =>
        sb.str("S")
        mangleTypes(types)
        sb.str("E")
      case nir.Type.StructValue(name, _) =>
        mangleGlobal(name)
      case nir.Type.Function(args, ret) =>
        sb.str("R")
        mangleTypes(args :+ ret)
        sb.str("E")
      case nir.Type.Nothing => sb.str("t")
      case nir.Type.Unit    => sb.str("u")
      case nir.Type.Class(name) =>
        mangleGlobal(name)
      case nir.Type.Trait(name) =>
        mangleGlobal(name)
      case nir.Type.Module(name) =>
        mangleGlobal(name)
      case nir.Type.Array(ty) =>
        sb.str("A")
        mangleType(ty)
        sb.str("_")
      case _: nir.Type.Var =>
        util.unreachable
    }

    def mangleIdent(id: String): Unit =
      if (builtinTypes.contains(id)) {
        sb.str(builtinTypes(id))
      } else if (builtinMethods.contains(id)) {
        sb.str(builtinMethods(id))
      } else if (builtinOps.contains(id)) {
        sb.str("O")
        sb.str(builtinOps(id))
      } else if (id.startsWith("arr.")) {
        sb.str("A")
        mangleIdent(id.substring(4))
      } else if (prev.contains(id)) {
        sb.str("X")
        sb.str(prev(id))
        sb.str("E")
      } else {
        if (id.contains(".")) {
          sb.str("V")
          manglePath(id.split("\\."))
          if (id.endsWith(".")) {
            sb.str("0")
          }
          sb.str("E")
        } else if (id.contains("$")) {
          sb.str("W")
          manglePath(id.split("\\$"))
          if (id.endsWith("$")) {
            sb.str("0")
          }
          sb.str("E")
        } else {
          mangleUnqual(id)
        }
        if (!prev.contains(id)) {
          prev(id) = sb.size
        }
      }

    def mangleUnqual(s: String): Unit = {
      sb.str(s.size)
      sb.str(s)
    }

    def manglePath(parts: Seq[String]): Unit = {
      var i = parts.length - 1
      while (i > 0) {
        if (i <= longestBuiltinPrefixLength) {
          val prefix = parts.take(i)
          if (builtinPrefixes.contains(prefix)) {
            sb.str("P")
            sb.str(builtinPrefixes(prefix))
            manglePath(parts.drop(i))
            return
          }
        }
        i -= 1
      }
      parts.foreach(mangleIdent)
    }
  }

  private val builtinPrefixes = Seq(
    "java"                       -> "jj",
    "java.io"                    -> "ji",
    "java.lang"                  -> "jl",
    "java.math"                  -> "jm",
    "java.nio"                   -> "jn",
    "java.io"                    -> "ji",
    "java.nio"                   -> "jI",
    "java.nio.file"              -> "jf",
    "java.nio.file.attribute"    -> "jF",
    "java.nio.channel"           -> "jN",
    "java.nio.charset"           -> "jC",
    "java.util"                  -> "ju",
    "java.util.zip"              -> "jz",
    "java.util.jar"              -> "jJ",
    "java.util.regex"            -> "jr",
    "java.util.concurrent"       -> "jc",
    "java.text"                  -> "jt",
    "java.net"                   -> "jT",
    "scala"                      -> "ss",
    "scala.collection"           -> "sc",
    "scala.collection.immutable" -> "si",
    "scala.collection.mutable"   -> "sm",
    "scala.concurrent"           -> "sC",
    "scala.math"                 -> "sM",
    "scala.runtime"              -> "sr",
    "scala.util"                 -> "su",
    "scala.reflect"              -> "sR",
    "scala.scalanative"          -> "nn",
    "scala.scalanative.native"   -> "nN",
    "scala.scalanative.runtime"  -> "nr"
  ).map {
    case (pkg, code) =>
      (pkg.split("\\.").toSeq, code)
  }.toMap

  private val longestBuiltinPrefixLength =
    builtinPrefixes.keys.map(_.length).max

  private val builtinTypes = (Seq(
    "unit"                                   -> "u",
    "bool"                                   -> "z",
    "char"                                   -> "c",
    "i8"                                     -> "b",
    "i16"                                    -> "s",
    "i32"                                    -> "i",
    "i64"                                    -> "j",
    "f32"                                    -> "f",
    "f64"                                    -> "d",
    "BoxedUnit"                              -> "n",
    "java.lang.Object"                       -> "o",
    "java.lang.String"                       -> "r",
    "Predef"                                 -> "p",
    "scala.scalanative.runtime.UnitArray"    -> "Au",
    "scala.scalanative.runtime.BooleanArray" -> "Az",
    "scala.scalanative.runtime.CharArray"    -> "Ac",
    "scala.scalanative.runtime.ByteArray"    -> "Ab",
    "scala.scalanative.runtime.ShortArray"   -> "As",
    "scala.scalanative.runtime.IntArray"     -> "Ai",
    "scala.scalanative.runtime.LongArray"    -> "Aj",
    "scala.scalanative.runtime.FloatArray"   -> "Af",
    "scala.scalanative.runtime.DoubleArray"  -> "Ad",
    "scala.scalanative.runtime.ObjectArray"  -> "AO"
  ) ++ {
    (1 to 22).map { i =>
      ("scala.Tuple" + i, "t" + i)
    }
  } ++ {
    (0 to 22).map { i =>
      ("scala.Function" + i, "f" + i)
    }
  }).toMap

  private val builtinMethods = Seq[(String, String)](
    "apply"      -> "a",
    "equals"     -> "q",
    "hashCode"   -> "h",
    "toString"   -> "g",
    "update"     -> "x",
    "anon"       -> "v",
    "anonfun"    -> "w",
    "class"      -> "l",
    "underscore" -> "y"
  ).toMap

  private val builtinOps = Seq(
    "&&"      -> "aa",
    "&"       -> "aN",
    "+:"      -> "aP",
    ":+"      -> "ap",
    ":="      -> "as",
    "!"       -> "bg",
    "unary_!" -> "bG",
    "::"      -> "cs",
    ":::"     -> "CS",
    "^"       -> "eo",
    "^^"      -> "eO",
    "="       -> "ee",
    "=="      -> "eq",
    "==="     -> "eQ",
    ">="      -> "ge",
    ">"       -> "gt",
    "##"      -> "hs",
    "<="      -> "le",
    "<<"      -> "ls",
    "<"       -> "lt",
    "-"       -> "mi",
    "--="     -> "MI",
    "-="      -> "mI",
    "*"       -> "ml",
    "--"      -> "mm",
    "!="      -> "ne",
    "!=="     -> "nE",
    "unary_-" -> "ng",
    "unary_~" -> "nG",
    "|"       -> "or",
    "||"      -> "oR",
    "+"       -> "pl",
    "++:"     -> "Pl",
    "++="     -> "PL",
    "+="      -> "pL",
    "->"      -> "pm",
    "++"      -> "pp",
    "unary_+" -> "ps",
    "%"       -> "rm",
    ">>"      -> "rs",
    "<~"      -> "sQ",
    "~"       -> "sq",
    "~>"      -> "SQ",
    "~~"      -> "ss"
  ).toMap
}
