package scala.scalanative
package optimizer
package analysis

import scala.collection.mutable
import ClassHierarchy._
import nir._
import VirtualTree._

final class VirtualTree(cls: Class) {
  val exact: mutable.Map[String, Val] =
    cls.parent.fold(mutable.Map.empty[String, Val])(_.vtree.exact.clone())
  val subclass: mutable.Map[String, Seq[Val]] =
    mutable.Map.empty[String, Seq[Val]]
  def isVirtual(sig: String): Boolean =
    subclass(sig).size > 1
}

object VirtualTree {
  def fill(top: Top): Unit = {
    top.classes.foreach { cls =>
      val vtree = new VirtualTree(cls)
      cls.methods.foreach { meth =>
        val sig = meth.name.id
        if (!meth.isConcrete) {
          vtree.exact(sig) = Val.None
        } else {
          vtree.exact(sig) = meth.value
        }
      }
      cls.vtree = vtree
    }

    top.classes.foreach { cls =>
      cls.vtree.exact.foreach {
        case (sig, impl) =>
          val impls = mutable.UnrolledBuffer(impl)
          def loop(subcls: Class): Unit = {
            impls += subcls.vtree.exact(sig)
            subcls.subclasses.foreach(loop)
          }
          loop(cls)
          cls.vtree.subclass(sig) = impls.distinct
      }
    }
  }
}
