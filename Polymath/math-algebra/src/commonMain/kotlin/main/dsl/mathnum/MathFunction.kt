package main.dsl.mathnum

import main.dsl.expressions.ScalarAlgebra
import main.dsl.CanDisplay
import main.dsl.expressions.ScalarExpression
import main.dsl.orderCanonically

data class MathFunction(
    val expression: ScalarExpression,
    override val glyph: String? = null,
    override val name: String? = null
) : ScalarExpression, CanDisplay {
    override val priority: ScalarAlgebra.Priority = ScalarAlgebra.Priority.ParensFunc
    override fun evaluate(): Double = expression.evaluate()
    override fun toLatex(): String = when {
        glyph != null -> asGlyphSyntax()
        name != null -> asNamedFunctionSyntax()
        else -> asAnonymousFunctionSyntax()
    }

    override fun display(): String = listOfNotNull(
        glyph?.let { asGlyphSyntax() },
        name?.let { asNamedFunctionSyntax() } ?: asAnonymousFunctionSyntax(),
        evaluate()
    ).joinToString(" = ")

    fun asGlyphSyntax() = "$glyph"
    fun asNamedFunctionSyntax() = "$name(${getCurrentVariablesForDisplay()}) = ${expression.toLatex()}"
    fun asAnonymousFunctionSyntax() = "(${getCurrentVariablesForDisplay()}) \\mapsto ${expression.toLatex()}"

    private fun getCurrentVariablesForDisplay() = expression
        .getOperandsExcept { it is MathFunction }
        .filterIsInstance<Variable>()
        .also { println(it) }
        .orderCanonically()
        .joinToString(",") { it.toLatex() }
}