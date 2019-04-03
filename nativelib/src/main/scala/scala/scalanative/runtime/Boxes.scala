package scala.scalanative
package runtime

import native._

object Boxes {
  @inline def boxToUByte(v: Byte): UByte    = new UByte(v)
  @inline def boxToUShort(v: Short): UShort = new UShort(v)
  @inline def boxToUInt(v: Int): UInt       = new UInt(v)
  @inline def boxToULong(v: Long): ULong    = new ULong(v)
  @inline def boxToPtr(v: RawPtr): Ptr[_]   = fromRawPtr(v)

  @inline def unboxToUByte(o: java.lang.Object): Byte =
    if (o == null) 0.toByte
    else o.asInstanceOf[UByte].underlying
  @inline def unboxToUShort(o: java.lang.Object): Short =
    if (o == null) 0.toShort
    else o.asInstanceOf[UShort].underlying
  @inline def unboxToUInt(o: java.lang.Object): Int =
    if (o == null) 0.toInt
    else o.asInstanceOf[UInt].underlying
  @inline def unboxToULong(o: java.lang.Object): Long =
    if (o == null) 0.toLong
    else o.asInstanceOf[ULong].underlying
  @inline def unboxToPtr(o: java.lang.Object): RawPtr =
    if (o == null) null
    else toRawPtr(o.asInstanceOf[Ptr[_]])
}
