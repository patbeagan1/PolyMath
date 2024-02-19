package main.dsl.expressions

import dev.patbeagan.math.base.GenericSymbols
import main.dsl.CanDisplay
import main.dsl.LinearAlgebra
import main.dsl.expressions.ScalarExpression.EquateDirection.*
import main.dsl.mathnum.*

interface ScalarExpression : GenericSymbols {
    val priority: ScalarAlgebra.Priority
    fun evaluate(): Double
    fun toLatex(): String

    fun toMathFunction(glyph: String?, name: String?) = MathFunction(this, glyph, name)

    fun ScalarExpression.toLatexPriority(
        parent: ScalarExpression,
        position: Position = Position.NotApplicable
    ): String = when {
        this.priority < parent.priority -> {
            // items listed here have inverted priorities
            // they need to happen out of the standard PEMDAS order of operations.
            // however, the latex for certain operations covers this inversion.
            // the default case is to include parens around them - but sometimes this is not needed,
            // like the content under a square root.
            when (parent.priority) {
                ScalarAlgebra.Priority.ParensFunc ->
                    // these come with parens already included.
                    toLatex()

                ScalarAlgebra.Priority.Root ->
                    // root symbols instead of parens
                    toLatex()

                ScalarAlgebra.Priority.Divide ->
                    // the top and bottom of the bar don't need parens
                    toLatex()

                ScalarAlgebra.Priority.Power -> when (position) {
                    Position.Power ->
                        // superscript instead of parens
                        toLatex()

                    Position.Base ->
                        // if this is a const, var or literal, we can drop the parens.
                        if (this !is MathNum) "(${toLatex()})" else toLatex()

                    else -> throw IllegalArgumentException()
                }

                else -> "(${toLatex()})"
            }
        }

        else -> toLatex()
    }

    enum class Position {
        NotApplicable, Power, Base
    }

    infix fun isLessThan(other: ScalarExpression) = ScalarRelation.LessThan(this, other)
    infix fun isGreaterThan(other: ScalarExpression) = ScalarRelation.GreaterThan(this, other)
    infix fun isLessThanOrEqualTo(other: ScalarExpression) = ScalarRelation.LessThanOrEqual(this, other)
    infix fun isGreaterThanOrEqualTo(other: ScalarExpression) = ScalarRelation.GreaterThanOrEqual(this, other)
    infix fun isEqualTo(other: ScalarExpression) = ScalarRelation.Equation(this, other)

    infix operator fun plus(other: ScalarExpression) = ScalarAlgebra.Add(this, other)
    infix operator fun minus(other: ScalarExpression) = ScalarAlgebra.Subtract(this, other)
    infix operator fun times(other: ScalarExpression) = ScalarAlgebra.Multiply(this, other)
    infix operator fun div(other: ScalarExpression) = ScalarAlgebra.Divide(this, other)
    infix operator fun rem(other: ScalarExpression) = mod(other)
    infix fun mod(other: ScalarExpression) = ScalarAlgebra.Modulo(this, other)
    infix fun pow(other: ScalarExpression) = ScalarAlgebra.Exponent(this, other)
    fun squared() = ScalarAlgebra.Exponent(this, mathNum(2.0))
    fun cubed() = ScalarAlgebra.Exponent(this, mathNum(3.0))

    fun getOperands(): Set<ScalarExpression> = getOperandsExcept { false }

    fun getOperandsExcept(shouldSkip: (ScalarExpression) -> Boolean = { false }): Set<ScalarExpression> {
        val operands = mutableSetOf<ScalarExpression>()
        walk(shouldSkip = shouldSkip) { operands.add(it) }
        return operands
    }

    fun getVariables(): Set<Variable> = getOperands().filterIsInstanceTo(mutableSetOf())
    fun getFunctions(): Set<MathFunction> = getOperands().filterIsInstanceTo(mutableSetOf())
    fun getConstants(): Set<Scalar.RealNum> = getOperands().filterIsInstanceTo(mutableSetOf())
    fun getDisplayableValues(): Set<CanDisplay> = getOperands().filterIsInstanceTo(mutableSetOf())

    fun equate(glyph: String? = null, direction: EquateDirection = Right): ScalarRelation.Equation {
        val e = if (glyph == null) {
            Scalar.RealNum(this.evaluate())
        } else {
            Variable(glyph = glyph, this.evaluate())
        }

        return when (direction) {
            Left -> ScalarRelation.Equation(e, this)
            Right -> ScalarRelation.Equation(this, e)
        }
    }

    enum class EquateDirection { Left, Right }

    fun descendants(depth: Int = Int.MAX_VALUE): List<ScalarExpression> {
        val c = mutableListOf<ScalarExpression>()
        walk(depthMax = depth) { c.add(it) }
        return c
    }

    fun walk(
        action: (ScalarExpression) -> Unit
    ) = walk(this, Int.MAX_VALUE, 0, { false }, action)

    fun walk(
        depthMax: Int = Int.MAX_VALUE,
        shouldSkip: (ScalarExpression) -> Boolean = { false },
        action: (ScalarExpression) -> Unit
    ) = walk(this, depthMax, 0, shouldSkip, action)

    private fun walk(
        expression: ScalarExpression,
        depthMax: Int,
        depth: Int,
        shouldSkip: (ScalarExpression) -> Boolean = { false },
        action: (ScalarExpression) -> Unit
    ) {
        expression.also(action)

        when {
            depth == depthMax -> return
            depth > 1 && shouldSkip(expression) -> return
        }

        when (expression) {
            is Scalar, /*is LinearAlgebra.Tensor*/ -> Unit

            is ScalarAlgebra.UnaryOperation -> {
                walk(expression.operand, depthMax, depth + 1, shouldSkip, action)
            }

            is ScalarAlgebra.BinaryOperation -> {
                walk(expression.left, depthMax, depth + 1, shouldSkip, action)
                walk(expression.right, depthMax, depth + 1, shouldSkip, action)
            }

            is ScalarAlgebra.Product -> {
                walk(expression.lower, depthMax, depth + 1, shouldSkip, action)
                walk(expression.upper, depthMax, depth + 1, shouldSkip, action)
                walk(expression.variable, depthMax, depth + 1, shouldSkip, action)
            }

            is ScalarAlgebra.Sum -> {
                walk(expression.lower, depthMax, depth + 1, shouldSkip, action)
                walk(expression.upper, depthMax, depth + 1, shouldSkip, action)
                walk(expression.variable, depthMax, depth + 1, shouldSkip, action)
            }

            is MathFunction -> {
                walk(expression.expression, depthMax, depth + 1, shouldSkip, action)
            }
        }
    }

    fun assignVariables(vararg incomingVarMap: Pair<String, Double>): ScalarExpression {
        val varMap = mapOf(*incomingVarMap)
        getVariables().forEach { v ->
            if (v.glyph in varMap) {
                varMap[v.glyph]?.let { v.value = mathNum(it) }
            }
        }
        return this
    }
}