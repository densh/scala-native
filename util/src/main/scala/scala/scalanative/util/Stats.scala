package scala.scalanative
package nir
package serialization

import scala.collection.mutable

object Stats {
  val times = mutable.Map.empty[String, Long]
  val counters = mutable.Map.empty[String, Long]
  def time[T](key: String)(f: => T): T = {
    import System.nanoTime
    val start = nanoTime()
    val res   = f
    val end   = nanoTime()
    times(key) = times.getOrElse(key, 0L) + (end - start)
    res
  }
  def count(key: String): Unit = {
    counters(key) = counters.getOrElse(key, 0L) + 1L
  }
  def print(): Unit = {
    println("--- Stats")
    times.toSeq.sortBy(_._1).foreach {
      case (key, time) =>
        println(key + ": " + (time / 1000000D).toString + " ms")
    }
    counters.toSeq.sortBy(_._1).foreach {
      case (key, n) =>
        println(key + ": " + n + " times")
    }
  }
  def clear(): Unit = {
    times.clear()
    counters.clear()
  }
}
