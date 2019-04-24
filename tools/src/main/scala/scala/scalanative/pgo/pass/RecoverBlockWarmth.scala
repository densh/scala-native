package scala.scalanative
package pgo
package pass

import java.io.File
import scala.io.Source
import scala.collection.mutable
import scalanative.nir._

class RecoverBlockWarmth(freqProfile: File)(implicit linked: linker.Result) extends Pass {
  private val freqMap = {
    assert(freqProfile.exists)
    var res    = mutable.Map.empty[Long, Long]
    val reader = new java.io.BufferedReader(new java.io.FileReader(freqProfile))
    var line   = reader.readLine()
    while (line != null) {
      if (line.contains("=")) {
        val parts = line.split("=")
        res(parts(0).toLong) = parts(1).toLong
      }
      line = reader.readLine()
    }
    res
  }

  override def onDefn(defn: Defn): Defn = defn match {
    case defn: Defn.Define if top.nodes.contains(defn.name) =>
      val defnId = top.nodes(defn.name).id
      val newinsts = defn.insts.map {
        case Inst.Label(name, params, _) =>
          val warmth =
            freqMap.get((defnId.toLong << 32) | name.id).getOrElse(0L)
          Inst.Label(name, params, warmth)
        case inst =>
          inst
      }
      defn.copy(insts = newinsts)
    case _ =>
      defn
  }
}

object RecoverBlockWarmth extends PassCompanion {
  override def apply(config: build.Config, linked: linker.Result) = {
    val build.UseProfile(profile) = config.profileMode
    val freqProfile         = new File(profile.getAbsolutePath + ".freq")
    new RecoverBlockWarmth(freqProfile)(linked)
  }
}
