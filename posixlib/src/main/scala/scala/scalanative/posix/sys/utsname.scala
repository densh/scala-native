package scala.scalanative.posix.sys

import scala.scalanative.native._

@extern
object utsname {
  type _256        = Nat.Digit[Nat._2, Nat.Digit[Nat._5, Nat._6]]
  private type str = CArray[Byte, _256]
  type utsname     = CStruct5[str, str, str, str, str]
  @extern def uname(utsname: Ptr[utsname]): CInt = extern
}

object uname {
  implicit class utsnameOps(val c: Ptr[utsname.utsname]) {
    def sysname  = fromCString(c._1.asInstanceOf[Ptr[CChar]])
    def nodename = fromCString(c._2.asInstanceOf[Ptr[CChar]])
    def release  = fromCString(c._3.asInstanceOf[Ptr[CChar]])
    def version  = fromCString(c._4.asInstanceOf[Ptr[CChar]])
    def machine  = fromCString(c._5.asInstanceOf[Ptr[CChar]])
  }
}
