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
  val props   = new java.util.Properties()
  props.load(new java.io.FileInputStream("out.map"))

  import java.util.concurrent._
  import java.util.concurrent.atomic._

  val counts  = new ConcurrentHashMap[Int, LongAdder]
  val times   = new ConcurrentHashMap[Int, LongAdder]
  val longadd = new java.util.function.Function[Int, LongAdder] {
    def apply(key: Int): LongAdder = new LongAdder
  }

  streams.foreach { events =>
    events.foreach { event =>
      val Event(id, time) = event
      counts.computeIfAbsent(id, longadd).increment()
      times.computeIfAbsent(id, longadd).add(time)
    }
  }

  // var out = new java.io.PrintWriter("methods.csv")
  // out.write("t,c,name\n")
  // methods.values.foreach { meth =>
  //   out.write(s"${meth.time},${meth.count},${meth.name}\n")
  // }
  // out.close

  // out = new java.io.PrintWriter("blocks.csv")
  // out.write("t,c,name\n")
  // blocks.values.foreach { block =>
  //   out.write(s"${block.time},${block.count},${block.meth.name}:${block.name}\n")
  // }
  // out.close
}
