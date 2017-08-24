import scalanative.profiling._
import scala.collection.mutable

//final case class Method(name: String) {
//  val blocks            = mutable.Map.empty[Int, Block]
//  var time              = 0
//  var count             = 0
//  override def toString = s"Method(name = $name, count = $count, time = $time)"
//}
//final case class Block(meth: Method, name: String) {
//  var time              = 0
//  var count             = 0
//  override def toString = s"Block(name = $name, count = $count, time = $time)"
//}

object Report extends App {
  val parser = LogParser(new java.io.File("profile.data"))
  val props  = new java.util.Properties()
  props.load(new java.io.FileInputStream("out.map"))

  //val methods = mutable.Map.empty[String, Method]
  //val blocks  = mutable.Map.empty[Int, Block]

  //def handle(id: Int, time: Int): Unit = {
  //  val Event.Block(id, time) = parser.next()

  //  val block =
  //    if (blocks.contains(id)) {
  //      blocks(id)
  //    } else {
  //      val prop       = props.getProperty(id.toString).split(",")
  //      val methodName = prop(0)
  //      val blockName  = prop(1)
  //      val meth =
  //        if (methods.contains(methodName)) {
  //          methods(methodName)
  //        } else {
  //          val method = new Method(methodName)
  //          methods(methodName) = method
  //          method
  //        }
  //      val block = new Block(meth, blockName)
  //      blocks(id) = block
  //      block
  //    }

  //  block.time += time
  //  block.count += 1
  //  block.meth.time += time
  //  block.meth.count += 1
  //}

  val edges = new java.util.HashMap[Long, Long]

  def handleEdge(from: Int, to: Int): Unit = {
    val key   = (from.toLong << 32) | (to.toLong & 0xffffffffL);
    val value = if (!edges.containsKey(key)) 1L else edges.get(key)
    edges.put(key, value)
  }

  var last = -1

  while (parser.hasNext) {
    val Event.Block(id, _) = parser.next()
    // handleEdge(last, id)
    // last = id
  }

  //var out = new java.io.PrintWriter("methods.csv")
  //out.write("t,c,name\n")
  //methods.values.foreach { meth =>
  //  out.write(s"${meth.time},${meth.count},${meth.name}\n")
  //}
  //out.close

  //out = new java.io.PrintWriter("blocks.csv")
  //out.write("t,c,name\n")
  //blocks.values.foreach { block =>
  //  out.write(s"${block.time},${block.count},${block.meth.name}:${block.name}\n")
  //}
  //out.close

  // out = new java.io.PrintWriter("edges.csv")
  // out.write("c,from,to\n")
  // edges.foreach {
  //   case (key, count) =>
  //     val fromId   = (key >> 32).toInt
  //     val fromName = props.getProperty(fromId.toString)
  //     val toId     = key.toInt
  //     val toName   = props.getProperty(toId.toString)
  //     out.write(s"$count,$fromName,$toName\n")
  // }
  // out.close
}
