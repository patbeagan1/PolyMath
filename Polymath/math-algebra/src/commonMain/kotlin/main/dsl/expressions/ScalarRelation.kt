package main.dsl.expressions

import main.dsl.expressions.ScalarAlgebra.*
import main.dsl.orderCanonically
import kotlin.math.abs

sealed interface ScalarRelation<T> {
    val left: ScalarExpression
    val right: ScalarExpression
    fun isValid(): Boolean
    fun toLatex(): String

    fun _step(l: ScalarExpression, r: ScalarExpression): T
    fun applyOperation(action: (ScalarExpression) -> ScalarExpression) = _step(action(left), action(right))
    fun evaluate() = left.evaluate() to right.evaluate()

    fun add(other: ScalarExpression) = _step(Add(this.left, other), Add(this.right, other))
    fun subtract(other: ScalarExpression) = _step(Subtract(this.left, other), Subtract(this.right, other))
    fun multiply(other: ScalarExpression) = _step(Multiply(this.left, other), Multiply(this.right, other))
    fun divide(other: ScalarExpression) = _step(Divide(this.left, other), Divide(this.right, other))
    fun modulo(other: ScalarExpression) = _step(Modulo(this.left, other), Modulo(this.right, other))
    fun exponent(other: ScalarExpression) = _step(Exponent(this.left, other), Exponent(this.right, other))
    fun log(other: ScalarExpression) = _step(Log(this.left, other), Log(this.right, other))

    fun explain(): String {
        val variableStatus = (left.getDisplayableValues() + right.getDisplayableValues())
            .orderCanonically()
            .joinToString("\n") { it.display() }
        return buildString {
            append("Given\n")
            append(variableStatus)
            append("\n\n")
            append(toLatex())
            append("\n\nyields ")
            append("${evaluate()}")
        }
    }

    fun walk(action: (ScalarExpression) -> Unit) {
        left.walk(action)
        right.walk(action)
    }

    data class LessThan(
        override val left: ScalarExpression,
        override val right: ScalarExpression
    ) : ScalarRelation<LessThan> {
        override fun _step(l: ScalarExpression, r: ScalarExpression): LessThan = LessThan(l, r)
        override fun isValid(): Boolean = left.evaluate().compareTo(right.evaluate()) < 0
        override fun toLatex(): String = "${left.toLatex()} < ${right.toLatex()}"
    }

    data class GreaterThan(
        override val left: ScalarExpression,
        override val right: ScalarExpression
    ) : ScalarRelation<GreaterThan> {
        override fun _step(l: ScalarExpression, r: ScalarExpression): GreaterThan = GreaterThan(l, r)
        override fun isValid(): Boolean = left.evaluate().compareTo(right.evaluate()) > 0
        override fun toLatex(): String = "${left.toLatex()} > ${right.toLatex()}"
    }

    data class LessThanOrEqual(
        override val left: ScalarExpression,
        override val right: ScalarExpression
    ) : ScalarRelation<LessThanOrEqual> {
        override fun _step(l: ScalarExpression, r: ScalarExpression): LessThanOrEqual = LessThanOrEqual(l, r)
        override fun isValid(): Boolean = left.evaluate().compareTo(right.evaluate()) <= 0
        override fun toLatex(): String = "${left.toLatex()} <= ${right.toLatex()}"
    }

    data class GreaterThanOrEqual(
        override val left: ScalarExpression,
        override val right: ScalarExpression
    ) : ScalarRelation<GreaterThanOrEqual> {
        override fun _step(l: ScalarExpression, r: ScalarExpression): GreaterThanOrEqual = GreaterThanOrEqual(l, r)
        override fun isValid(): Boolean = left.evaluate().compareTo(right.evaluate()) >= 0
        override fun toLatex(): String = "${left.toLatex()} >= ${right.toLatex()}"
    }

    data class Equation(
        override val left: ScalarExpression,
        override val right: ScalarExpression
    ) : ScalarRelation<Equation> {
        override fun _step(l: ScalarExpression, r: ScalarExpression): Equation = Equation(l, r)
        override fun isValid(): Boolean = left.evaluate().compareTo(right.evaluate()) == 0
        override fun toLatex(): String = "${left.toLatex()} = ${right.toLatex()}"

        /**
         * Accounts for a little bit of rounding when evaluating equations.
         *
         * Starts with a high degree of specificity, but it can be made as accurate or inaccurate as you like.
         */
        fun validateApprox(marginOfError: Double = 1e-15): Boolean =
            abs(left.evaluate() - right.evaluate()) < marginOfError

        fun evaluateGet(): Result<Double> = if (isValid()) {
            Result.success(evaluate().second)
        } else {
            Result.failure(EquationIsNotValid())
        }

        class EquationIsNotValid : Exception()
    }
}