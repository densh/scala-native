package scala.scalanative
package linker

import scala.collection.mutable
import scalanative.nir._
import scalanative.nir.serialization._
import scalanative.io.VirtualDirectory
import scalanative.util.Scope

import ReflectiveProxy._

sealed trait Linker {

  /** Link the whole world under closed world assumption. */
  def link(entries: Seq[Global]): Result
}

object Linker {

  /** Create a new linker given tools configuration. */
  def apply(config: tools.Config,
            reporter: Reporter = Reporter.empty): Linker =
    new Impl(config, reporter)

  private final class Impl(config: tools.Config, reporter: Reporter)
      extends Linker {
    import reporter._

    def link(entries: Seq[Global]): Result = Scope { implicit in =>
      val readers    = new java.util.HashSet[BinaryReader]
      val resolved   = new java.util.HashSet[Global]
      val unresolved = new java.util.HashSet[Global]
      val direct     = new java.util.Stack[Global]
      var conditional =
        new java.util.HashMap[Global, java.util.HashSet[Global]]
      val weaks      = new java.util.HashSet[Global]
      val signatures = new java.util.HashSet[String]
      val links      = new java.util.HashSet[String]
      //val dyndefns    = mutable.Set.empty[Global]

      val paths =
        config.paths.par.map(p => ClassPath(VirtualDirectory.real(p))).seq

      def processed(g: Global) =
        resolved.contains(g) || unresolved.contains(g) || g.isIntrinsic

      def findReader(global: Global) =
        paths.collectFirst {
          case path if path.contains(global) =>
            path.reader(global)
        }.flatten

      def foreach[T](iter: java.util.Iterator[T])(f: T => Unit): Unit = {
        while (iter.hasNext) {
          f(iter.next())
        }
      }

      def toSeq[T: reflect.ClassTag](iter: java.util.Iterator[T]): Seq[T] = {
        val buf = scala.collection.mutable.UnrolledBuffer.empty[T]
        while (iter.hasNext) {
          buf += iter.next
        }
        buf
      }

      def resolve(workitem: Global): Unit = {
        resolved.add(workitem)
        if (conditional.containsKey(workitem)) {
          foreach(conditional.get(workitem).iterator)(direct.push)
          conditional.remove(workitem)
        }
        onResolved(workitem)
      }

      def unresolve(workitem: Global): Unit = {
        unresolved.add(workitem)
        if (conditional.containsKey(workitem)) {
          conditional.remove(workitem)
        }
        onUnresolved(workitem)
      }

      def process() = {
        while (!direct.empty) {
          val workitem = direct.pop()
          if (!processed(workitem)) {
            val reader = findReader(workitem)

            reader
              .flatMap(_.touch(workitem))
              .fold[Unit] {
                unresolve(workitem)
              } {
                case (inner, deps) =>
                  readers.add(reader.get)
                  resolve(workitem)
                  inner.foreach(resolve)

                  // Comparing new signatures with already collected weak dependencies
                  //info.sigs
                  //  .flatMap(signature =>
                  //    weaks.collect {
                  //      case weak if Global.genSignature(weak) == signature =>
                  //        weak
                  //  })
                  //  .foreach { global =>
                  //    direct.push(global)
                  //    dyndefns += global
                  //  }

                  deps.foreach {
                    case Dep.Direct(dep) =>
                      direct.push(dep)
                      onDirectDependency(workitem, dep)

                    case Dep.Conditional(dep, cond) =>
                      if (!processed(dep)) {
                        if (resolved.contains(cond)) {
                          direct.push(dep)
                        } else if (unresolved.contains(cond)) {
                          ()
                        } else {
                          val set =
                            if (conditional.containsKey(cond)) {
                              conditional.get(cond)
                            } else {
                              val newSet = new java.util.HashSet[Global]
                              conditional.put(cond, newSet)
                              newSet
                            }
                          set.add(dep)
                        }
                      }
                      onConditionalDependency(workitem, dep, cond)

                    case Dep.Weak(global) =>
                      // comparing new dependencies with all signatures
                      //if (signatures(Global.genSignature(global))) {
                      //  direct.push(global)
                      //  onDirectDependency(workitem, global)
                      //  dyndefns += global
                      //}
                      weaks.add(global)

                    case Dep.Wildcard(sig) =>
                      signatures.add(sig)

                    case Dep.Link(name) =>
                      links.add(name)
                  }
              }
          }
        }
      }

      onStart()

      entries.foreach { entry =>
        direct.push(entry)
        onEntry(entry)
      }

      process()

      //val reflectiveProxies =
      //  genAllReflectiveProxies(dyndefns, defns)
      //val defnss = defns ++ reflectiveProxies

      val defns =
        toSeq(readers.iterator).par
          .map { reader =>
            reader.deserialize
          }
          .seq
          .flatten

      onComplete()

      Result(toSeq(unresolved.iterator),
             toSeq(links.iterator).map(Attr.Link(_)),
             defns.toSeq,
             toSeq(signatures.iterator))
    }
  }
}
