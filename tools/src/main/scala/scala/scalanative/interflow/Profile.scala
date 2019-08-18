package scala.scalanative
package interflow

import java.io.File
import scala.collection.mutable
import scalanative.nir._

object Profile {
  private def countEdges(insts: Seq[Inst]): Int = {
    var count = 0
    insts.foreach {
      case _: Inst.If =>
        count += 2
      case inst: Inst.Switch =>
        count += inst.cases.size + 1
      case _ =>
        ()
    }
    count
  }

  private def countCallSites(insts: Seq[Inst]): Int = {
    var count = 0
    insts.map {
      case Inst.Let(n, _: Op.Method | _: Op.Dynmethod, _) =>
        count += 1
      case _ =>
        ()
    }
    count
  }

  def parse(profile: java.nio.file.Path, defns: Seq[Defn]): Seq[Defn] = {
    val entries   = mutable.Map.empty[String, Long]
    val methodIds = mutable.Map.empty[Global, Long]

    def initMethodIds(): Unit = {
      defns
        .collect {
          case defn: Defn.Define =>
            defn.name
        }
        .sortBy(_.show)
        .zipWithIndex
        .foreach {
          case (name, idx) =>
            methodIds(name) = idx
        }
    }

    def readLines(): Unit = {
      val reader =
        new java.io.BufferedReader(new java.io.FileReader(profile.toFile))
      try {
        var line = reader.readLine()
        while (line != null) {
          val Array(key, value) = line.split(" = ")
          entries(key) = java.lang.Long.parseLong(value)
          line = reader.readLine()
        }
      } finally {
        reader.close()
      }
    }

    def enrichInsts(methodId: Long, insts: Seq[Inst]): Seq[Inst] = {
      var edgeId = 0L

      def nextEdgeId(): Long = {
        val result = edgeId
        edgeId += 1L
        result
      }

      def nextEdgeCount(): Long = {
        val edgeId = nextEdgeId()
        entries.getOrElse(s"method$methodId.edge$edgeId", 0L)
      }

      insts.map {
        case Inst.If(value,
                     Next.Label(thenName, thenArgs, _),
                     Next.Label(elseName, elseArgs, _)) =>
          Inst.If(value,
                  Next(thenName, thenArgs, nextEdgeCount()),
                  Next(elseName, elseArgs, nextEdgeCount()))
        case Inst.Switch(value,
                         Next.Label(defaultName, defaultArgs, _),
                         cases) =>
          val enrichedDefault =
            Next(defaultName, defaultArgs, nextEdgeCount())
          val enrichedCases = cases.map {
            case Next.Case(caseVal, Next.Label(caseName, caseArgs, _)) =>
              Next.Case(caseVal, Next(caseName, caseArgs, nextEdgeCount()))
          }
          Inst.Switch(value, enrichedDefault, enrichedCases)
        case inst =>
          inst
      }
    }

    def enrichDefn(defn: Defn.Define): Defn.Define = {
      val methodId     = methodIds(defn.name)
      val callCountKey = s"method$methodId.callCount"

      if (entries.contains(callCountKey)) {
        val methodCallCount = entries(callCountKey)
        assert(entries(s"method$methodId.edgeCount") == countEdges(defn.insts))
        assert(
          entries(s"method$methodId.callSiteCount") == countCallSites(
            defn.insts))

        defn.copy(attrs = defn.attrs.copy(weight = methodCallCount),
                  insts = enrichInsts(methodId, defn.insts))
      } else {
        defn.copy(attrs = defn.attrs.copy(weight = 0))
      }
    }

    def enrichDefns(): Seq[Defn] = {
      defns.map {
        case defn: Defn.Define =>
          enrichDefn(defn)
        case defn =>
          defn
      }
    }

    try {
      initMethodIds()
      readLines()
      enrichDefns()
    } catch {
      case exc: Exception =>
        println("failed to read profile data: " + exc.toString)
        defns
    }
  }
}
