package native
package compiler
package pass

import scala.collection.mutable
import native.nir._
import native.util.ScopedVar, ScopedVar.scoped

/** Lowers strings values into intrinsified global constant.
 *
 *  For a  string value:
 *
 *      "..."
 *
 *  Becomes a pair of constants:
 *
 *      var @_str_$N_data: [i8 x ${str.length}] =
 *        c"..."
 *
 *      var @_str_$N: struct #string =
 *        struct #string { #type_of_string, ${str.length}, @_str_$N_data }
 *
 *  And the value itself is replace with:
 *
 *      cast[ptr i8] @_str_$N
 *
 *  Eliminates:
 *  - Val.String
 */
trait StringLowering extends Pass {
  override def onVal(value: Val) = super.onVal(value match {
    case Val.String(v) => Val.Zero(Type.Ptr(Type.I8))
    case _             => value
  })
}
