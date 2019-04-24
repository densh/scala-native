package scala.scalanative
package pgo

import scala.collection.mutable
import nir._

/** Optimizer reporters can override one of the corresponding methods to
 *  get notified whenever one of the optimization events happens.
 */
object Optimizer {

  /** Run all of the passes on given assembly. */
  def apply(config: build.Config,
            linked: linker.Result): Seq[Defn] = {
    val driver = Driver(config)

    def loop(batchId: Int,
             batchDefns: Seq[Defn],
             passes: Seq[(Pass, Int)]): Seq[Defn] =
      passes match {
        case Seq() =>
          batchDefns

        case (pass: Pass, passId) +: rest =>
          val passResult = pass.onDefns(batchDefns)
          loop(batchId, passResult, rest)
      }

    util.partitionBy(linked.defns)(_.name).par
      .map {
        case (batchId, batchDefns) =>
          val passes = driver.passes.map(_.apply(config, linked))
          loop(batchId, batchDefns, passes.zipWithIndex)
      }
      .seq
      .flatten
      .toSeq
  }
}
