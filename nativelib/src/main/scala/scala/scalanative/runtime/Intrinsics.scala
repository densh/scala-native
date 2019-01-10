package scala.scalanative
package runtime

import scalanative.native.{CSize, Word}

object Intrinsics {

  /** Intrinsified unsigned devision on ints. */
  def divUInt(l: Int, r: Int): Int = intrinsic

  /** Intrinsified unsigned devision on longs. */
  def divULong(l: Long, r: Long): Long = intrinsic

  /** Intrinsified unsigned remainder on ints. */
  def remUInt(l: Int, r: Int): Int = intrinsic

  /** Intrinsified unsigned remainder on longs. */
  def remULong(l: Long, r: Long): Long = intrinsic

  /** Intrinsified byte to unsigned int converstion. */
  def byteToUInt(b: Byte): Int = intrinsic

  /** Intrinsified byte to unsigned long conversion. */
  def byteToULong(b: Byte): Long = intrinsic

  /** Intrinsified short to unsigned int conversion. */
  def shortToUInt(v: Short): Int = intrinsic

  /** Intrinsified short to unsigned long conversion. */
  def shortToULong(v: Short): Long = intrinsic

  /** Intrinsified int to unsigned long conversion. */
  def intToULong(v: Int): Long = intrinsic

  /** Intrinsified raw memory load of boolean. */
  def loadBoolean(rawptr: RawPtr): Boolean = intrinsic

  /** Intrinsified raw memory load of char. */
  def loadChar(rawptr: RawPtr): Char = intrinsic

  /** Intrinsified raw memory load of byte. */
  def loadByte(rawptr: RawPtr): Byte = intrinsic

  /** Intrinsified raw memory load of short. */
  def loadShort(rawptr: RawPtr): Short = intrinsic

  /** Intrinsified raw memory load of int. */
  def loadInt(rawptr: RawPtr): Int = intrinsic

  /** Intrinsified raw memory load of long. */
  def loadLong(rawptr: RawPtr): Long = intrinsic

  /** Intrinsified raw memory load of float. */
  def loadFloat(rawptr: RawPtr): Float = intrinsic

  /** Intrinsified raw memory load of double. */
  def loadDouble(rawptr: RawPtr): Double = intrinsic

  /** Intrinsified raw memory load of rawptr. */
  def loadRawPtr(rawptr: RawPtr): RawPtr = intrinsic

  /** Intrinsified raw memory load of object. */
  def loadObject(rawptr: RawPtr): Object = intrinsic

  /** Intrinsified raw memory store of boolean. */
  def storeBoolean(rawptr: RawPtr, value: Boolean): Unit = intrinsic

  /** Intrinsified raw memory store of char. */
  def storeChar(rawptr: RawPtr, value: Char): Unit = intrinsic

  /** Intrinsified raw memory store of byte. */
  def storeByte(rawptr: RawPtr, value: Byte): Unit = intrinsic

  /** Intrinsified raw memory store of short. */
  def storeShort(rawptr: RawPtr, value: Short): Unit = intrinsic

  /** Intrinsified raw memory store of int. */
  def storeInt(rawptr: RawPtr, value: Int): Unit = intrinsic

  /** Intrinsified raw memory store of long. */
  def storeLong(rawptr: RawPtr, value: Long): Unit = intrinsic

  /** Intrinsified raw memory store of float. */
  def storeFloat(rawptr: RawPtr, value: Float): Unit = intrinsic

  /** Intrinsified raw memory store of double. */
  def storeDouble(rawptr: RawPtr, value: Double): Unit = intrinsic

  /** Intrinsified raw memory store of rawptr. */
  def storeRawPtr(rawptr: RawPtr, value: RawPtr): Unit = intrinsic

  /** Intrinsified raw memory store of object. */
  def storeObject(rawptr: RawPtr, value: Object): Unit = intrinsic

  /** Intrinsified computation of derived raw pointer. */
  def elemRawPtr(rawptr: RawPtr, offset: Word): RawPtr = intrinsic

  def castRawPtrToObject(rawptr: RawPtr): Object = intrinsic

  def castObjectToRawPtr(obj: Object): RawPtr = intrinsic

  def castIntToFloat(int: Int): Float = intrinsic

  def castFloatToInt(float: Float): Int = intrinsic

  def castLongToDouble(long: Long): Double = intrinsic

  def castDoubleToLong(double: Double): Long = intrinsic

  def castRawPtrToInt(rawptr: RawPtr): Int = intrinsic

  def castRawPtrToLong(rawptr: RawPtr): Long = intrinsic

  def castIntToRawPtr(int: Int): RawPtr = intrinsic

  def castLongToRawPtr(int: Long): RawPtr = intrinsic

  def stackalloc(size: CSize): RawPtr = intrinsic
}
