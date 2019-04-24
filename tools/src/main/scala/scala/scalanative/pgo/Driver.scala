package scala.scalanative
package pgo

sealed trait Driver {

  /** Companion of all the passes in the driver's pipeline. */
  def passes: Seq[PassCompanion]

  /** Create a copy with given passes. */
  def withPasses(passes: Seq[PassCompanion]): Driver
}

object Driver {

  /** Create driver with default pipeline for this configuration. */
  def apply(config: build.Config): Driver = {
    def prof = config.profileMode match {
      case build.NoProfile =>
        Seq()
      case build.UseProfile(file) =>
        Seq(
          pass.RecoverBlockWarmth,
          pass.InlineCaching,
          pass.PruneUntakenPaths,
          pass.DeadBlockElimination
        )
      case build.CollectProfile(file) =>
        Seq(pass.Profiling)
    }
    new Impl(prof)
  }

  /** Create an empty pass-lesss driver. */
  def empty: Driver =
    new Impl(Seq.empty)

  private final class Impl(val passes: Seq[PassCompanion]) extends Driver {
    def withPasses(passes: Seq[PassCompanion]): Driver =
      new Impl(passes)
  }

}
