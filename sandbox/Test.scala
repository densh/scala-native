object Test {
  def main(args: Array[String]): Unit = {
    scalanative.native.stdlib.malloc(32)
  }
}
