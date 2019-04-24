package scala.scalanative.build

/** Compilation mode. Either of the two:
 *
 *  * Debug mode. Most optimizations are turned off
 *    to get the best linking speed. This mode is the
 *    default and is preferred for iterative development.
 *
 *  * Release mode. Runs all the optimizations but
 *    may take substantially longer to link the application.
 *
 *  Additional compilation modes might be added in the future.
 *
 *  @param name name of the compilation mode
 */
sealed abstract class Mode private (val name: String) {
  override def toString: String = name
}
object Mode {
  private[scalanative] final case object Baseline extends Mode("baseline")
  private[scalanative] final case object Debug    extends Mode("debug")
  private[scalanative] sealed trait Release
  private[scalanative] final case object ReleaseFast
      extends Mode("release-fast")
      with Release
  private[scalanative] final case object ReleaseFull
      extends Mode("release-full")
      with Release
  private[scalanative] final case object PgoInstrument
      extends Mode("pgo-instrument")
  private[scalanative] final case object PgoRelease
      extends Mode("pgo-release")
      with Release

  /** Debug compilation mode. */
  def baseline: Mode = Baseline

  /** Release compilation mode. */
  def release: Mode = ReleaseFull

  /** Release compilation mode that's still fast to compile. */
  def releaseFast: Mode = ReleaseFast

  /** Release compilation mode that's uses full set of optimizations. */
  def releaseFull: Mode = ReleaseFull

  /** Default compilation mode. */
  def default: Mode = Baseline

  /** Get a compilation mode with given name. */
  def apply(name: String): Mode = name match {
    case "baseline" =>
      Baseline
    case "debug" =>
      Debug
    case "release" =>
      ReleaseFull
    case "release-fast" =>
      ReleaseFast
    case "release-full" =>
      ReleaseFull
    case "pgo-instrument" =>
      PgoInstrument
    case "pgo-release" =>
      PgoRelease
    case value =>
      throw new IllegalArgumentException(s"Unknown mode: '$value'")
  }
}
