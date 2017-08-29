import java.util.concurrent._
import java.util.concurrent.atomic._

import scalanative.profiling._
import scala.collection.mutable

object CountReport extends App {
  val streams = LogParser(new java.io.File("profile.data"))
  var ids = new java.util.concurrent.atomic.AtomicInteger

  type CountMap = mutable.Map[Int, Long]

  val counts = streams.par.map { fn =>
    val id = ids.getAndIncrement
    val start = System.nanoTime()
    val counts = mutable.Map.empty[Int, Long]
    val buffer = fn()
    var done = false
    while (!done) {
      try {
        val id = buffer.getInt
        counts(id) = if (counts.contains(id)) counts(id) + 1L else 1L
      } catch {
        case _: Exception =>
          done = true
      }
    }
    val end = System.nanoTime()
    if (id % 100 == 0) {
      val t = (end - start) / 1000000.0D
      println("done " + id + "(" + t + " ms)")
    }
    counts
  }.fold[CountMap](mutable.Map.empty) {
    case (counts1, counts2) =>
      counts1 ++ counts2
  }

  val props = new java.util.Properties()
  props.load(new java.io.FileInputStream("out.map"))

  var out = new java.io.PrintWriter("blocks.csv")
  out.write("t,c,name\n")
  counts.foreach {
    case (id, count) =>
      val name = props.getProperty(id.toString)
      out.write(s"$count,$name\n")
  }
  out.close

  val methodcounts = mutable.Map.empty[String, Long]
  counts.foreach {
    case (id, count) =>
      val meth = props.getProperty(id.toString).split(",").head
      val old  = if (!methodcounts.contains(meth)) 0 else methodcounts(meth)
      methodcounts(meth) = old + count
  }

  out = new java.io.PrintWriter("methods.csv")
  out.write("t,c,name\n")
  methodcounts.foreach {
    case (name, count) =>
      out.write(s"$count,$name\n")
  }
  out.close
}
