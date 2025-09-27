package main.dsl

import main.dsl.mathnum.Constant
import main.dsl.mathnum.MathFunction
import main.dsl.mathnum.Variable


fun <T> Iterable<T>.orderCanonically(): List<T> where
        T : CanDisplay = this
    .sortedBy { display -> display.glyph }
    .sortedBy { display -> (display as? Variable)?.instance ?: "" }
    .sortedBy {
        when (it::class) {
            Constant::class -> 0
            Variable::class -> 1
            MathFunction::class -> 2
            else -> 3
        }
    }

interface CanDisplay {
    val name: String?
    val glyph: String?

    fun display(): String
}