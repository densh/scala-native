import java.util.concurrent._
import java.util.concurrent.atomic._

import scalanative.profiling._
import scala.collection.mutable

final case class Method(name: String) {
  val blocks            = mutable.Map.empty[Int, Block]
  var time              = 0
  var count             = 0
  override def toString = s"Method(name = $name, count = $count, time = $time)"
}

final case class Block(meth: Method, name: String) {
  var time              = 0
  var count             = 0
  override def toString = s"Block(name = $name, count = $count, time = $time)"
}

object Report extends App {
  val streams = LogParser(new java.io.File("profile.data"))
  var ids = new java.util.concurrent.atomic.AtomicInteger

  type ILMap = mutable.Map[Int, Long]

  val (counts, times) = streams.par.map { fn =>
    val id = ids.getAndIncrement
    val start = System.nanoTime()
    val counts = mutable.Map.empty[Int, Long]
    val times  = mutable.Map.empty[Int, Long]
    val buffer = fn()
    var done = false
    while (!done) {
      try {
        val id   = buffer.getInt
        val time = buffer.getInt
        counts(id) = if (counts.contains(id)) counts(id) + 1L else 1L
        times(id)  = if (times.contains(id)) times(id) + time else time
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
    (counts, times)
  }.fold[(ILMap, ILMap)]((mutable.Map.empty, mutable.Map.empty)) {
    case ((counts1, times1), (counts2, times2)) =>
      (counts1 ++ counts2, times1 ++ times2)
  }

  // var out = new java.io.PrintWriter("methods.csv")
  // out.write("t,c,name\n")
  // methods.values.foreach { meth =>
  //   out.write(s"${meth.time},${meth.count},${meth.name}\n")
  // }
  // out.close

  val props = new java.util.Properties()
  props.load(new java.io.FileInputStream("out.map"))

  var out = new java.io.PrintWriter("blocks.csv")
  out.write("t,c,name\n")
  counts.foreach {
    case (id, count) =>
      val name = props.getProperty(id.toString)
      val time = times(id)
      out.write(s"$time,$count,$name\n")
  }
  out.close

  val methodcounts = mutable.Map.empty[String, Long]
  val methodtimes  = mutable.Map.empty[String, Long]
  counts.foreach {
    case (id, count) =>
      val meth = props.getProperty(id.toString).split(",").head
      if (!methodcounts.contains(meth)) {
        methodcounts(meth) = 0
        methodtimes(meth) = 0
      }
      methodcounts(meth) += count
      methodtimes(meth) += times(id)
  }

  out = new java.io.PrintWriter("methods.csv")
  out.write("t,c,name\n")
  methodcounts.foreach {
    case (name, count) =>
      val time = methodtimes(name)
      out.write(s"$time,$count,$name\n")
  }
  out.close
}
