package scala.scalanative
package interflow

import java.io.File
import scala.collection.mutable
import scalanative.nir._

trait Profile { self: Interflow =>

  def isProfileGuided(): Boolean =
    mode == build.Mode.PgoRelease

  lazy val (hotThreshold, coldThreshold) = {
    if (!isProfileGuided()) {
      (0L, 0L)
    } else {
      val ordered =
        linked.defns
          .collect {
            case defn: Defn.Define if defn.attrs.weight > 0 =>
              defn
          }
          .sortBy(-_.attrs.weight)
          .toArray
      def percentile(p: Double) =
        ordered((ordered.size * p).toInt + 1).attrs.weight
      val top10 =
        percentile(0.1D)
      val top90 =
        percentile(0.9D)

      (top10, top90)
    }
  }

  def isHot(name: Global): Boolean =
    linked.infos(name).attrs.weight >= hotThreshold

  def isCold(name: Global): Boolean =
    linked.infos(name).attrs.weight <= coldThreshold
}

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

  def parse(config: build.Config, linked: linker.Result): Seq[Defn] = {
    val entries   = mutable.Map.empty[String, Long]
    val meta      = new codegen.Metadata(linked, Seq.empty)
    val methodIds = meta.methodIds
    val typeNames = {
      val out = mutable.Map.empty[Long, Global]
      meta.classes.foreach { cls =>
        out(meta.ids(cls)) = cls.name
      }
      out
    }

    def readLines(): Unit = {
      val profile = config.profile.toFile
      val reader =
        new java.io.BufferedReader(new java.io.FileReader(profile))
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
      var edgeId     = 0L
      var callSiteId = 0L

      def nextEdgeId(): Long = {
        val result = edgeId
        edgeId += 1L
        result
      }

      def nextEdgeCount(): Long = {
        val edgeId = nextEdgeId()
        entries.getOrElse(s"method$methodId.edge$edgeId", 0L)
      }

      def nextCallSiteId(): Long = {
        val result = callSiteId
        callSiteId += 1L
        result
      }

      def nextCallSiteWeights(): List[(Global, Long)] = {
        val callSiteId = nextCallSiteId()
        val prefix     = s"method$methodId.callSite$callSiteId.type"
        val out        = mutable.ListBuffer.empty[(Global, Long)]
        entries.foreach {
          case (k, v) if k.startsWith(prefix) =>
            val typeId   = java.lang.Long.parseLong(k.substring(prefix.length))
            val typeName = typeNames(typeId)
            out += ((typeName, v))
          case _ =>
            ()
        }
        out.toList
      }

      insts.map {
        case Inst.Let(n, Op.Method(obj, s, _), unwind) =>
          Inst.Let(n, Op.Method(obj, s, nextCallSiteWeights()), unwind)
        case Inst.Let(n, Op.Dynmethod(obj, s, _), unwind) =>
          Inst.Let(n, Op.Dynmethod(obj, s, nextCallSiteWeights()), unwind)
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
      linked.defns.map {
        case defn: Defn.Define =>
          enrichDefn(defn)
        case defn =>
          defn
      }
    }

    try {
      readLines()
      enrichDefns()
    } catch {
      case exc: Exception =>
        println("failed to read profile data: " + exc.toString)
        linked.defns
    }
  }
}
