package scala.scalanative
package optimizer
package inject

import scala.collection.mutable.Buffer
import analysis.ClassHierarchy.{Top, Class}
import nir._

class ModuleAccessor(implicit top: Top, fresh: Fresh) extends Inject {
  def apply(buf: Buffer[Defn]): Unit = {
    top.classes.foreach { cls =>
      if (cls.isModule) {
        val entry      = fresh()
        val existing   = fresh()
        val initialize = fresh()

        val clsName = cls.name
        val clsTy   = cls.ty
        val slot    = Val.Local(fresh(), Type.Ptr)
        val self    = Val.Local(fresh(), clsTy)
        val cond    = Val.Local(fresh(), Type.Bool)
        val alloc   = Val.Local(fresh(), clsTy)

        val initCall = if (isStaticModule(clsName)) {
          Inst.None
        } else {
          val initSig = Type.Function(Seq(Type.Class(clsName)), Type.Void)
          val init    = Val.Global(clsName member "<init>", Type.Ptr)

          Inst.Let(Op.Call(initSig, init, Seq(alloc), Next.None))
        }

        val loadName = clsName member "load"
        val loadSig  = Type.Function(Seq(), clsTy)
        val loadDefn = Defn.Define(
          Attrs.None,
          loadName,
          loadSig,
          Seq(
            Inst.Label(entry, Seq()),
            Inst.Let(slot.name,
                     Op.Elem(Type.Ptr,
                             Val.Global(Global.Top("__modules"), Type.Ptr),
                             Seq(Val.Int(top.moduleArray.index(cls))))),
            Inst.Let(self.name, Op.Load(clsTy, slot)),
            Inst.Let(cond.name, Op.Comp(Comp.Ine, Rt.Object, self, Val.Null)),
            Inst.If(cond, Next(existing), Next(initialize)),
            Inst.Label(existing, Seq()),
            Inst.Ret(self),
            Inst.Label(initialize, Seq()),
            Inst.Let(alloc.name, Op.Classalloc(clsName)),
            Inst.Let(Op.Store(clsTy, slot, alloc)),
            initCall,
            Inst.Ret(alloc)
          )
        )

        buf += loadDefn
      }
    }
  }

  def isStaticModule(name: Global): Boolean =
    top.nodes(name).isInstanceOf[Class] &&
      (!top.nodes.contains(name member "init"))
}

object ModuleAccessor extends InjectCompanion {
  override def apply(config: tools.Config, top: Top) =
    new ModuleAccessor()(top, top.fresh)
}
