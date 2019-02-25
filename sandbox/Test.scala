object Test {
  def gridValues(grid: IndexedSeq[String]) = {
    grid.filter(c => "0.".contains(c))
  }

  def main(args: Array[String]): Unit = {
    gridValues("".map(_.toString))
  }
}
