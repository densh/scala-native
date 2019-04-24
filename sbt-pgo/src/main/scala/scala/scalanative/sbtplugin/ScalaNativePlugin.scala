package scala.scalanative
package sbtplugin

import scalanative.tools
import ScalaNativePluginInternal._

import sbt._

object ScalaNativePlugin extends AutoPlugin {
  override def requires: Plugins = plugins.JvmPlugin

  val autoImport = AutoImport

  object AutoImport extends NativeCross {

    val ScalaNativeCrossVersion = sbtplugin.ScalaNativeCrossVersion

    val nativeVersion = nir.Versions.current

    val nativeClang =
      taskKey[File]("Location of the clang compiler.")

    val nativeClangPP =
      taskKey[File]("Location of the clang++ compiler.")

    val nativeCompileOptions =
      taskKey[Seq[String]](
        "Additional options are passed to clang during compilation.")

    val nativeLinkingOptions =
      taskKey[Seq[String]](
        "Additional options that are passed to clang during linking.")

    val nativeLinkStubs =
      settingKey[Boolean]("Whether to link `@stub` methods, or ignore them.")

    val nativeLink =
      taskKey[File]("Generates native binary without running it.")

    val nativeExternalDependencies =
      taskKey[Seq[String]]("List all external dependencies at link time.")

    val nativeAvailableDependencies =
      taskKey[Seq[String]]("List all symbols available at link time")

    val nativeMissingDependencies =
      taskKey[Seq[String]]("List all symbols not available at link time")

    val nativeMode =
      settingKey[String]("Compilation mode, either \"debug\" or \"release\".")

    val nativeGC =
      settingKey[String]("GC choice, either \"none\", \"boehm\" or \"immix\".")

    val nativeProfileMode =
      settingKey[ProfileMode]("")

    val nativeInlineCachingMode =
      settingKey[InlineCachingMode]("")

    val nativePruneUntakenBranches =
      settingKey[Boolean]("")

    val nativeProfileDirectedInlining =
      settingKey[Boolean]("")

    val nativeProfileDirectedBlockPlacement =
      settingKey[Boolean]("")

    type ProfileMode = tools.ProfileMode
    val NoProfile = tools.NoProfile
    def CollectProfile(file: java.io.File): ProfileMode =
      tools.CollectProfile(file)
    def UseProfile(file: java.io.File): ProfileMode = tools.UseProfile(file)

    type InlineCachingMode = tools.InlineCachingMode
    val NoInlineCaching                            = tools.NoInlineCaching
    val InlineCacheAll                             = tools.InlineCacheAll
    def InlineCacheN(n: Int): InlineCachingMode    = tools.InlineCacheN(n)
    def InlineCacheP(p: Double): InlineCachingMode = tools.InlineCacheP(p)
    def InlineCacheNP(n: Int, p: Double): InlineCachingMode =
      tools.InlineCacheNP(n, p)
  }

  override def globalSettings: Seq[Setting[_]] =
    ScalaNativePluginInternal.scalaNativeGlobalSettings

  override def projectSettings: Seq[Setting[_]] =
    ScalaNativePluginInternal.scalaNativeProjectSettings
}
