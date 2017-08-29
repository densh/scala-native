import java.util.concurrent._
import java.util.concurrent.atomic._

import scalanative.profiling._
import scala.collection.mutable

object Report extends App {
  val streams = LogParser(new java.io.File("profile.data"))
  var ids = new java.util.concurrent.atomic.AtomicInteger

  type CountMap    = mutable.Map[Int, Long]
  type TransferMap = mutable.Map[(Int, Int, Int, Int, Int, Int, Int, Int), Long]

  val (counts, transfers) = streams.par.map { fn =>
    val id = ids.getAndIncrement
    val start = System.nanoTime()
    val counts = mutable.Map.empty[Int, Long]
    val transfers = mutable.Map.empty[(Int, Int, Int, Int, Int, Int, Int, Int), Long]
    val buffer = fn()
    var done = false
    var last7 = buffer.getInt
    var last6 = buffer.getInt
    var last5 = buffer.getInt
    var last4 = buffer.getInt
    var last3 = buffer.getInt
    var last2 = buffer.getInt
    var last1 = buffer.getInt
    counts(last7) = 1
    counts(last6) = 1
    counts(last5) = 1
    counts(last4) = 1
    counts(last3) = 1
    counts(last2) = 1
    counts(last1) = 1
    while (!done) {
      try {
        val id = buffer.getInt
        counts(id) = if (counts.contains(id)) counts(id) + 1L else 1L
        val transfer = (last7, last6, last5, last4, last3, last2, last1, id)
        transfers(transfer) = if (transfers.contains(transfer)) transfers(transfer) + 1L else 1L
        last7 = last6
        last6 = last5
        last5 = last4
        last4 = last3
        last3 = last2
        last2 = last1
        last1 = id
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
    (counts, transfers)
  }.fold[(CountMap, TransferMap)]((mutable.Map.empty, mutable.Map.empty)) {
    case ((c1, t1), (c2, t2)) =>
      (c1 ++ c2, t1 ++ t2)
  }

  val props = new java.util.Properties()
  props.load(new java.io.FileInputStream("out.map"))

  var out = new java.io.PrintWriter("blocks.csv")
  out.write("c,name\n")
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
  out.write("c,name\n")
  methodcounts.foreach {
    case (name, count) =>
      out.write(s"$count,$name\n")
  }
  out.close

  out = new java.io.PrintWriter("transfers.csv")
  out.write("c,a,b,c,d,e,f,g,h\n")
  transfers.foreach {
    case ((a, b, c, d, e, f, g, h), count) =>
      val aName = props.getProperty(a.toString).replace(",", ":")
      val bName = props.getProperty(b.toString).replace(",", ":")
      val cName = props.getProperty(c.toString).replace(",", ":")
      val dName = props.getProperty(d.toString).replace(",", ":")
      val eName = props.getProperty(e.toString).replace(",", ":")
      val fName = props.getProperty(f.toString).replace(",", ":")
      val gName = props.getProperty(g.toString).replace(",", ":")
      val hName = props.getProperty(h.toString).replace(",", ":")
      out.write(s"$count,$aName,$bName,$cName,$dName,$eName,$fName,$gName,$hName\n")
  }
  out.close
}
