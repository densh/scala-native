package scala.scalanative
package optimizer
package inject

import scala.collection.mutable.Buffer
import analysis.ClassHierarchy.Top
import nir._

/** Introduces `main` function that sets up
 *  the runtime and calls the given entry point.
 */
class Main(config: tools.Config) extends Inject {
  import Main._

  override def apply(buf: Buffer[Defn]): Unit = {
    implicit val fresh = Fresh()
    val entry          = config.entry
    val entryMainTy =
      Type.Function(Seq(Type.Module(entry.top), ObjectArray), Type.Void)
    val entryMainName =
      Global.Member(entry, "main_scala.scalanative.runtime.ObjectArray_unit")
    val entryMain = Val.Global(entryMainName, Type.Ptr)

    val stackBottom = Val.Local(fresh(), Type.Ptr)

    val argc   = Val.Local(fresh(), Type.Int)
    val argv   = Val.Local(fresh(), Type.Ptr)
    val module = Val.Local(fresh(), Type.Module(entry.top))
    val rt     = Val.Local(fresh(), Rt)
    val arr    = Val.Local(fresh(), ObjectArray)
    val exc    = Val.Local(fresh(), nir.Rt.Object)
    val unwind = Next.Unwind(fresh())

    val profilingInit =
      if (config.enableProfiling)
        Inst.Let(
          Op.Call(profiling_initSig,
                  profiling_init,
                  Seq(Val.String(config.profilingLocation.getAbsolutePath)),
                  Next.None))
      else
        Inst.None
    val profilingDump =
      if (config.enableProfiling)
        Inst.Let(Op.Call(block_dumpSig, block_dump, Seq.empty, Next.None))
      else
        Inst.None

    buf += Defn.Define(
      Attrs.None,
      MainName,
      MainSig,
      Seq(
        Inst.Label(fresh(), Seq(argc, argv)),
        profilingInit,
        Inst.Let(stackBottom.name, Op.Stackalloc(Type.Ptr, Val.Long(0))),
        Inst.Let(
          Op.Store(Type.Ptr,
                   Val.Global(stackBottomName, Type.Ptr),
                   stackBottom)),
        Inst.Let(Op.Call(InitSig, Init, Seq(), unwind)),
        Inst.Let(rt.name, Op.Module(Rt.name, unwind)),
        Inst.Let(arr.name,
                 Op.Call(RtInitSig, RtInit, Seq(rt, argc, argv), unwind)),
        Inst.Let(module.name, Op.Module(entry.top, unwind)),
        Inst.Let(Op.Call(entryMainTy, entryMain, Seq(module, arr), unwind)),
        Inst.Let(Op.Call(RtLoopSig, RtLoop, Seq(module), unwind)),
        profilingDump,
        Inst.Ret(Val.Int(0)),
        Inst.Label(unwind.name, Seq(exc)),
        Inst.Let(
          Op.Call(PrintStackTraceSig, PrintStackTrace, Seq(exc), Next.None)),
        profilingDump,
        Inst.Ret(Val.Int(1))
      )
    )

    buf += Defn.Var(Attrs.None, stackBottomName, Type.Ptr, Val.Null)
  }
}

object Main extends InjectCompanion {

  val ObjectArray =
    Type.Class(Global.Top("scala.scalanative.runtime.ObjectArray"))

  val Rt =
    Type.Module(Global.Top("scala.scalanative.runtime.package$"))
  val RtInitSig =
    Type.Function(Seq(Rt, Type.Int, Type.Ptr), ObjectArray)
  val RtInit =
    Val.Global(
      Rt.name member "init_i32_ptr_scala.scalanative.runtime.ObjectArray",
      Type.Ptr)
  val RtLoopSig =
    Type.Function(Seq(Rt), Type.Unit)
  val RtLoop =
    Val.Global(Rt.name member "loop_unit", Type.Ptr)

  val MainName = Global.Top("main")
  val MainSig  = Type.Function(Seq(Type.Int, Type.Ptr), Type.Int)

  val ThrowableName = Global.Top("java.lang.Throwable")
  val Throwable     = Type.Class(ThrowableName)

  val PrintStackTraceSig =
    Type.Function(Seq(Throwable), Type.Unit)
  val PrintStackTraceName =
    Global.Member(ThrowableName, "printStackTrace_unit")
  val PrintStackTrace =
    Val.Global(PrintStackTraceName, Type.Ptr)

  val InitSig  = Type.Function(Seq(), Type.Unit)
  val Init     = Val.Global(Global.Top("scalanative_init"), Type.Ptr)
  val InitDecl = Defn.Declare(Attrs.None, Init.name, InitSig)

  val stackBottomName = Global.Top("__stack_bottom")

  val profiling_initSig = Type.Function(Seq(nir.Rt.String), Type.Void)
  val profiling_init    = Val.Global(Global.Top("profiling_init"), Type.Ptr)
  val profiling_initDecl =
    Defn.Declare(Attrs.None, profiling_init.name, profiling_initSig)

  val block_dumpSig  = Type.Function(Seq.empty, Type.Void)
  val block_dump     = Val.Global(Global.Top("block_dump"), Type.Ptr)
  val block_dumpDecl = Defn.Declare(Attrs.None, block_dump.name, block_dumpSig)

  override val depends =
    Seq(ObjectArray.name,
        Rt.name,
        RtInit.name,
        RtLoop.name,
        PrintStackTraceName)

  override val injects =
    Seq(InitDecl, block_dumpDecl, profiling_initDecl)

  override def apply(config: tools.Config, top: Top) =
    new Main(config)
}
