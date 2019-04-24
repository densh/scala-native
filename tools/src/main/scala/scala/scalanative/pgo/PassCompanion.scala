package scala.scalanative
package pgo

trait PassCompanion {

  /** Instantiate the given pass. */
  def apply(config: build.Config, linked: linker.Result): Pass
}

