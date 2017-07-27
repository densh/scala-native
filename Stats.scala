package scala.scalanative
package nir
package serialization

import scala.collection.mutable

object Stats {
  val measurements = mutable.Map.empty[String, Long]
  def time[T](key: String)(f: => T): T = {
    import System.nanoTime
    val start = nanoTime()
    val res = f
    val end = nanoTime()
    val delta: Long = end - start
    if (!measurements.contains(key)) {
      measurements(key) = 0L
    }
    measurements(key) = measurements(key) + delta
    res
  }
  def print(): Unit = {
    println("--- Stats")
    measurements.foreach { case (key, time) =>
      println(key + ": " + (time / 1000000D).toString + " ms")
    }
  }
  def clear(): Unit = measurements.clear()
}


