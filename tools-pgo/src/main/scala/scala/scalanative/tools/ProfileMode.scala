package scala.scalanative
package tools

sealed abstract class ProfileMode
final case object NoProfile                         extends ProfileMode
final case class CollectProfile(file: java.io.File) extends ProfileMode
final case class UseProfile(file: java.io.File)     extends ProfileMode
