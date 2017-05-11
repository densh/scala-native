package scala.scalanative
package optimizer
package pass

import scala.collection.mutable
import scalanative.optimizer.analysis.{LocalScope, LocalRef}
import scalanative.optimizer.analysis.ClassHierarchy.{Top, Class}
import scalanative.optimizer.analysis.ClassHierarchyExtractors.FieldRef
import scalanative.nir._, Inst._, Op._

/** Inserts safepoint probes before every return. */
class WriteBarrierInsertion(implicit top: Top, fresh: Fresh) extends Pass {
  import WriteBarrierInsertion._

  override def onDefn(defn: Defn): Defn =
    if (defn.name == ObjectArrayUpdateName) {
      println(defn.show)
      defn
    } else {
      super.onDefn(defn)
    }

  override def onInsts(insts: Seq[Inst]): Seq[Inst] = {
    implicit val scope = LocalScope(insts)
    val buf            = new nir.Buffer
    import buf._

    insts.foreach {
      case inst @ Let(n,
                      Store(ty: Type.RefKind,
                            LocalRef(Field(obj, FieldRef(cls: Class, fld))),
                            value,
                            _)) =>
        buf += inst

      case inst =>
        buf += inst
    }

    buf.toSeq
  }
}

object WriteBarrierInsertion extends PassCompanion {
  val ObjectArrayName = Global.Top("scala.scalanative.runtime.ObjectArray")
  val ObjectArrayUpdateName =
    Global.Member(ObjectArrayName, "update_i32_java.lang.Object_unit")

  override def apply(config: tools.Config, top: Top) =
    new WriteBarrierInsertion()(top, top.fresh)
}
