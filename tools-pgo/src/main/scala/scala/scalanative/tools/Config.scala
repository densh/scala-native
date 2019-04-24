package scala.scalanative
package tools

import java.io.File
import nir.Global

import java.io.File

sealed trait Config {

  /** Entry point for linking. */
  def entry: Global

  /** Sequence of all NIR locations. */
  def paths: Seq[File]

  /** Directory to emit intermediate compilation results. */
  def workdir: File

  /** Target triple. */
  def target: String

  /** Compilation mode. */
  def mode: Mode

  /** Should stubs be linked? */
  def linkStubs: Boolean

  /** Is virtual dispatch profiling enabled? */
  def profileMode: ProfileMode

  /** Inline caching heuristic. */
  def inlineCachingMode: InlineCachingMode

  def pruneUntakenBranches: Boolean

  def profileDirectedInlining: Boolean

  def profileDirectedBlockPlacement: Boolean

  /** Create new config with given entry point. */
  def withEntry(value: Global): Config

  /** Create a new config with given nir paths. */
  def withPaths(value: Seq[File]): Config

  /** Create a new config with given directory. */
  def withWorkdir(value: File): Config

  /** Create a new config with given target triple. */
  def withTarget(value: String): Config

  /** Create a new config with given compilation mode. */
  def withMode(value: Mode): Config

  /** Create a new config with given behavior for stubs. */
  def withLinkStubs(value: Boolean): Config

  /** Create a new config with virtual dispatch profiling enabled or disabled */
  def withProfileMode(mode: ProfileMode): Config

  /** Inline caching heuristic. */
  def withInlineCachingMode(mode: InlineCachingMode): Config

  def withPruneUntakenBranches(value: Boolean): Config

  def withProfileDirectedInlining(value: Boolean): Config

  def withProfileDirectedBlockPlacement(value: Boolean): Config
}

object Config {

  /** Default empty config object. */
  val empty: Config =
    Impl(
      entry = Global.None,
      paths = Seq.empty,
      workdir = new File(""),
      target = "",
      mode = Mode.Debug,
      linkStubs = false,
      profileMode = NoProfile,
      inlineCachingMode = NoInlineCaching,
      pruneUntakenBranches = false,
      profileDirectedInlining = false,
      profileDirectedBlockPlacement = false
    )

  private final case class Impl(entry: Global,
                                paths: Seq[File],
                                workdir: File,
                                target: String,
                                mode: Mode,
                                linkStubs: Boolean,
                                profileMode: ProfileMode,
                                inlineCachingMode: InlineCachingMode,
                                pruneUntakenBranches: Boolean,
                                profileDirectedInlining: Boolean,
                                profileDirectedBlockPlacement: Boolean)
      extends Config {
    def withEntry(value: Global): Config =
      copy(entry = value)

    def withPaths(value: Seq[File]): Config =
      copy(paths = value)

    def withWorkdir(value: File): Config =
      copy(workdir = value)

    def withTarget(value: String): Config =
      copy(target = value)

    def withMode(value: Mode): Config =
      copy(mode = value)

    def withLinkStubs(value: Boolean): Config =
      copy(linkStubs = value)

    def withProfileMode(value: ProfileMode): Config =
      copy(profileMode = value)

    def withInlineCachingMode(value: InlineCachingMode): Config =
      copy(inlineCachingMode = value)

    def withPruneUntakenBranches(value: Boolean): Config =
      copy(pruneUntakenBranches = value)

    def withProfileDirectedInlining(value: Boolean): Config =
      copy(profileDirectedInlining = value)

    def withProfileDirectedBlockPlacement(value: Boolean): Config =
      copy(profileDirectedBlockPlacement = value)
  }
}
