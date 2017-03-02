import scala.scalanative.runtime.GC

object Test {
  def main(args: Array[String]): Unit = {

    val arr = new Array[List](2)
    var i   = 0
    while (i < 20) {
      arr(i % 2) = create()
      i += 1
      GC.collect()
    }

  }

  def create(): List = {
    var l: List = Nil
    var i       = 0
    while (i < 3) {
      l = Cons(new Array[Int](i), l)
      i += 1
    }
    l
  }

  trait List
  case class Cons(value: Array[Int], next: List) extends List
  case object Nil                                extends List
}
