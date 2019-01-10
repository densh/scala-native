import scalanative.native._
import scalanative.libc._

object Test extends App {
  def test(n: String)(f: => Unit): Unit = f

  var effects                    = List.empty[String]
  def println(str: String): Unit = effects = effects :+ str
  def clean(): Unit              = effects = Nil
  def assertEffects(effects: String*)(f: => Unit) = {
    this.effects = Nil
    f
    assert(this.effects == effects.toList)
  }

  def a1(): Unit = println("a1")
  def a2(): Unit = println("a2")
  def a3(): Unit = println("a3")

  def i1: Int = { println("i1"); 1 }
  def i2: Int = { println("i2"); 2 }
  def i3: Int = { println("i3"); 3 }

  def e1: Int = { println("e1"); throw new Exception() }

  def t3(i: => Int): Int = {
    println("t3")
    try {
      try { return i } finally { a1() }
    } catch {
      case _: Throwable =>
        try { i2 } finally { a2() } // no cleanup version
    } finally {
      a3()
    }
  }

  test("t3.e1") {
    assertEffects("t3", "e1", "a1", "i2", "a2", "a3") {
      assert(t3(e1) == 2)
    }
  }
}
