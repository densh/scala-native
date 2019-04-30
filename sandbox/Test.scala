object Test {
  val x = new C

  class C { var value: Any = 0 }

  @noinline def mutate(x: C): Unit = x.value = 42

  def main(args: Array[String]): Unit = {
    mutate(x)
  }
}
