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
  val parser = LogParser(new java.io.File("profile.data"))
  val props  = new java.util.Properties()
  props.load(new java.io.FileInputStream("out.map"))

  val methods = mutable.Map.empty[String, Method]
  val blocks  = mutable.Map.empty[Int, Block]

  while (parser.hasNext) {
    val Event.Block(id, time) = parser.next()

    val block =
      if (blocks.contains(id)) {
        blocks(id)
      } else {
        val prop       = props.getProperty(id.toString).split(",")
        val methodName = prop(0)
        val blockName  = prop(1)
        val meth =
          if (methods.contains(methodName)) {
            methods(methodName)
          } else {
            val method = new Method(methodName)
            methods(methodName) = method
            method
          }
        val block = new Block(meth, blockName)
        blocks(id) = block
        block
      }

    block.time += time
    block.count += 1
    block.meth.time += time
    block.meth.count += 1
  }

  var out = new java.io.PrintWriter("methods.csv")
  out.write("t,c,name\n")
  methods.values.foreach { meth =>
    out.write(s"${meth.time},${meth.count},${meth.name}\n")
  }
  out.close

  out = new java.io.PrintWriter("blocks.csv")
  out.write("t,c,name\n")
  blocks.values.foreach { block =>
    out.write(s"${block.time},${block.count},${block.meth.name}:${block.name}")
  }
  out.close
}
