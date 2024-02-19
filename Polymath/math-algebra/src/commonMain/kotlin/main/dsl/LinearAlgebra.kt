package main.dsl

import main.dsl.expressions.ScalarAlgebra
import main.dsl.mathnum.MathNum
import kotlin.jvm.JvmInline

interface LinearAlgebra {
//    @JvmInline
//    value class Tensor(private val vv: Array<out MathNum>) : MathNum {
//        override val priority: ScalarAlgebra.Priority get() = ScalarAlgebra.Priority.NumVar
//
//        override fun evaluate(): Double = Double.NaN
//
//        override fun toLatex(): String = buildString {
//            append("\\left[\\begin{array}{c}")
//            append(vv.joinToString(",") {
//                it.toLatex()
//            })
//            append("\\end{array}\\right]")
//        }
//
//        override fun toString() = buildString {
//            append("[")
//            appendLine()
//            append(vv.joinToString(",") { it.toString() })
//            append("]")
//            appendLine()
//        }
//
//        companion object {
//            fun <T : MathNum> of(vararg values: T): Tensor = Tensor(values)
//        }
//    }
}