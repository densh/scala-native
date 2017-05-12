object Test {
  @noinline def bar(i: Int): Unit = if (i == 0) () else foo(i - 1)
  @noinline def foo(i: Int): Unit = if (i == 0) () else bar(i - 1)
  def main(args: Array[String]): Unit =
    while(true) {
      foo(100)
    }
}
