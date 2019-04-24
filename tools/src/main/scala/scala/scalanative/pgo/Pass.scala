package scala.scalanative
package pgo

import nir._

trait Pass {

  def onDefns(defns: Seq[Defn]): Seq[Defn]
}
