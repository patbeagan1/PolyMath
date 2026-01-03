package main.dsl.expressions

import main.dsl.mathnum.Scalar.RealNum
import main.dsl.expressions.ScalarExpression.Position
import main.dsl.mathnum.*
import kotlin.math.*

//@JvmInline
//value class Likeness(val value: Set<NamedMathNum>)
//data class Term(val coefficient: RealNum, val likeness: Likeness)


/**
 * Next step here - define equations which will automatically balance
 *
 * Simplify
 * Solve
 */
interface ScalarAlgebra {
    sealed class Priority(private val priorityValue: Int) : Comparable<Priority> {
        data object Other : Priority(0)
        data object Add : Priority(1)
        data object Subtract : Priority(1)
        data object Multiply : Priority(2)
        data object Divide : Priority(2)
        data object Power : Priority(3)
        data object Root : Priority(3)
        data object ParensFunc : Priority(4)
        data object NumVar : Priority(5)

        override fun compareTo(other: Priority): Int = this.priorityValue.compareTo(other.priorityValue)
    }

    interface BinaryOperation : ScalarExpression {
        val left: ScalarExpression
        val right: ScalarExpression
    }

    interface UnaryOperation : ScalarExpression {
        val operand: ScalarExpression
    }

    data class Add(override val left: ScalarExpression, override val right: ScalarExpression) : BinaryOperation {
        override val priority: Priority = Priority.Add
        override fun evaluate() = left.evaluate() + right.evaluate()
        override fun toLatex() = "${left.toLatexPriority(this)} + ${right.toLatexPriority(this)}"
        override fun toTypst() = "${left.toTypstPriority(this)} + ${right.toTypstPriority(this)}"
    }

    data class Subtract(override val left: ScalarExpression, override val right: ScalarExpression) : BinaryOperation {
        override val priority: Priority = Priority.Subtract
        override fun evaluate() = left.evaluate() - right.evaluate()
        override fun toLatex() = "${left.toLatexPriority(this)} - ${right.toLatexPriority(this)}"
        override fun toTypst() = "${left.toTypstPriority(this)} - ${right.toTypstPriority(this)}"
    }

    data class Multiply(override val left: ScalarExpression, override val right: ScalarExpression) : BinaryOperation {
        override val priority: Priority = Priority.Multiply
        override fun evaluate() = left.evaluate() * right.evaluate()
        override fun toLatex() = "${left.toLatexPriority(this)} $symCdot ${right.toLatexPriority(this)}"
        override fun toTypst() = "${left.toTypstPriority(this)} * ${right.toTypstPriority(this)}"
    }

    data class Divide(override val left: ScalarExpression, override val right: ScalarExpression) : BinaryOperation {
        override val priority: Priority = Priority.Divide
        override fun evaluate(): Double {
            require(right.evaluate() != 0.0) // Not defined for divide by 0
            return left.evaluate() / right.evaluate()
        }

        override fun toLatex() = "\\frac{${left.toLatexPriority(this)}}{${right.toLatexPriority(this)}}"
        override fun toTypst() = "frac(${left.toTypstPriority(this)}, ${right.toTypstPriority(this)})"
    }

    data class Modulo(override val left: ScalarExpression, override val right: ScalarExpression) : BinaryOperation {
        override val priority: Priority = Priority.Divide
        override fun evaluate(): Double {
            require(right.evaluate() != 0.0) // Not defined for divide by 0
            return left.evaluate() % right.evaluate()
        }

        override fun toLatex() = "${left.toLatexPriority(this)} mod ${right.toLatexPriority(this)}"
        override fun toTypst() = "${left.toTypstPriority(this)} mod ${right.toTypstPriority(this)}"
    }

    data class Negate(override val operand: ScalarExpression) : UnaryOperation {
        constructor(operand: Number) : this(operand.toDouble().num())

        override val priority: Priority = Priority.Subtract
        override fun evaluate() = -operand.evaluate()
        override fun toLatex() = "-${operand.toLatexPriority(this)}"
        override fun toTypst() = "-${operand.toTypstPriority(this)}"
    }

    data class Exponent(override val left: ScalarExpression, override val right: ScalarExpression) : BinaryOperation {
        override val priority: Priority = Priority.Power
        override fun evaluate() = left.evaluate().pow(right.evaluate())
        override fun toLatex() = "${
            left.toLatexPriority(this, Position.Base)
        }^{${
            right.toLatexPriority(this, Position.Power)
        }}"
        override fun toTypst() = "${
            left.toTypstPriority(this, Position.Base)
        }^${
            right.toTypstPriority(this, Position.Power)
        }"

    }

    data class Sqrt(override val operand: ScalarExpression) : UnaryOperation {
        constructor(operand: Number) : this(operand.toDouble().num())

        override val priority: Priority = Priority.Root
        override fun evaluate() = sqrt(operand.evaluate())
        override fun toLatex() = "\\sqrt{${operand.toLatexPriority(this)}}"
        override fun toTypst() = "sqrt(${operand.toTypstPriority(this)})"
    }

    data class Sin(override val operand: ScalarExpression) : UnaryOperation {
        constructor(operand: Number) : this(operand.toDouble().num())

        override val priority: Priority = Priority.ParensFunc
        override fun evaluate() = sin(operand.evaluate())
        override fun toLatex() = "\\sin(${operand.toLatexPriority(this)})"
        override fun toTypst() = "sin(${operand.toTypstPriority(this)})"
    }

    data class Cos(override val operand: ScalarExpression) : UnaryOperation {
        constructor(operand: Number) : this(operand.toDouble().num())

        override val priority: Priority = Priority.ParensFunc
        override fun evaluate() = cos(operand.evaluate())
        override fun toLatex() = "\\cos(${operand.toLatexPriority(this)})"
        override fun toTypst() = "cos(${operand.toTypstPriority(this)})"
    }

    data class Tan(override val operand: ScalarExpression) : UnaryOperation {
        constructor(operand: Number) : this(operand.toDouble().num())

        override val priority: Priority = Priority.ParensFunc
        override fun evaluate() = tan(operand.evaluate())
        override fun toLatex() = "\\tan(${operand.toLatexPriority(this)})"
        override fun toTypst() = "tan(${operand.toTypstPriority(this)})"
    }

    data class Log(override val left: ScalarExpression, override val right: ScalarExpression) : BinaryOperation {
        override val priority: Priority = Priority.ParensFunc
        override fun evaluate() = log(right.evaluate(), left.evaluate())
        override fun toLatex() = "\\log_{${left.toLatexPriority(this)}}(${right.toLatexPriority(this)})"
        override fun toTypst() = "log_(${left.toTypstPriority(this)})(${right.toTypstPriority(this)})"
    }

    data class Ln(override val operand: ScalarExpression) : UnaryOperation {
        constructor(operand: Number) : this(operand.toDouble().num())

        override val priority: Priority = Priority.ParensFunc
        override fun evaluate() = ln(operand.evaluate())
        override fun toLatex() = "\\ln(${operand.toLatexPriority(this)})"
        override fun toTypst() = "ln(${operand.toTypstPriority(this)})"
    }

    data class Exp(override val operand: ScalarExpression) : UnaryOperation {
        constructor(operand: Number) : this(operand.toDouble().num())

        override val priority: Priority = Priority.ParensFunc
        override fun evaluate() = exp(operand.evaluate())
        override fun toLatex() = "e^{${operand.toLatexPriority(this)}}"
        override fun toTypst() = "e^${operand.toTypstPriority(this)}"
    }

    data class Abs(override val operand: ScalarExpression) : UnaryOperation {
        constructor(operand: Number) : this(operand.toDouble().num())

        override val priority: Priority = Priority.ParensFunc
        override fun evaluate() = abs(operand.evaluate())
        override fun toLatex() = "|${operand.toLatexPriority(this)}|"
        override fun toTypst() = "abs(${operand.toTypstPriority(this)})"
    }

    data class ArcSin(override val operand: ScalarExpression) : UnaryOperation {
        constructor(operand: Number) : this(operand.toDouble().num())

        override val priority: Priority = Priority.ParensFunc
        override fun evaluate() = asin(operand.evaluate())
        override fun toLatex() = "\\sin^{-1}(${operand.toLatexPriority(this)})"
        override fun toTypst() = "asin(${operand.toTypstPriority(this)})"
    }

    data class ArcCos(override val operand: ScalarExpression) : UnaryOperation {
        constructor(operand: Number) : this(operand.toDouble().num())

        override val priority: Priority = Priority.ParensFunc
        override fun evaluate() = acos(operand.evaluate())
        override fun toLatex() = "\\cos^{-1}(${operand.toLatexPriority(this)})"
        override fun toTypst() = "acos(${operand.toTypstPriority(this)})"
    }

    data class ArcTan(override val operand: ScalarExpression) : UnaryOperation {
        constructor(operand: Number) : this(operand.toDouble().num())

        override val priority: Priority = Priority.ParensFunc
        override fun evaluate() = atan(operand.evaluate())
        override fun toLatex() = "\\tan^{-1}(${operand.toLatexPriority(this)})"
        override fun toTypst() = "atan(${operand.toTypstPriority(this)})"
    }

    data class Sum(
        val variable: Variable,
        val lower: ScalarExpression,
        val upper: ScalarExpression,
        val expression: (ScalarExpression) -> ScalarExpression
    ) : ScalarExpression {
        override val priority: Priority = Priority.Other
        override fun evaluate(): Double {
            val lBound = lower.evaluate()
            val uBound = upper.evaluate()
            if (lBound.isNaN() || uBound.isNaN()) return Double.NaN

            return (lBound.toInt()..uBound.toInt()).sumOf {
                expression(RealNum(it.toDouble())).evaluate()
            }
        }

        override fun toLatex() = buildString {
            append("\\sum_{")
            append(variable.toLatex())
            append("=")
            append(lower.toLatex())
            append("}^{")
            append(upper.toLatex())
            append("}{")
            append(expression(variable).toLatex())
            append("}")
        }
        override fun toTypst() = buildString {
            append("sum_(")
            append(variable.toTypst())
            append("=")
            append(lower.toTypst())
            append(")^(")
            append(upper.toTypst())
            append(") ")
            append(expression(variable).toTypst())
        }
    }

    data class Product(
        val variable: Variable,
        val lower: ScalarExpression,
        val upper: ScalarExpression,
        val expression: (ScalarExpression) -> ScalarExpression
    ) : ScalarExpression {
        override val priority: Priority = Priority.Other
        override fun evaluate(): Double {
            val lBound = lower.evaluate()
            val uBound = upper.evaluate()
            if (lBound.isNaN() || uBound.isNaN()) return Double.NaN

            return (lBound.toInt()..uBound.toInt()).fold(1.0) { acc: Double, each: Int ->
                acc * expression(RealNum(each.toDouble())).evaluate()
            }
        }

        override fun toLatex() = buildString {
            append("\\prod_{")
            append(variable.toLatex())
            append("=")
            append(lower.toLatex())
            append("}^{")
            append(upper.toLatex())
            append("}{")
            append(expression(variable).toLatex())
            append("}")
        }
        override fun toTypst() = buildString {
            append("prod_(")
            append(variable.toTypst())
            append("=")
            append(lower.toTypst())
            append(")^(")
            append(upper.toTypst())
            append(") ")
            append(expression(variable).toTypst())
        }
    }

    data class Factorial(override val operand: ScalarExpression) : UnaryOperation {
        constructor(operand: Number) : this(operand.toDouble().num())

        override val priority: Priority = Priority.ParensFunc
        override fun evaluate(): Double = factorial(operand.evaluate().toInt())
        override fun toLatex() = "${operand.toLatexPriority(this)}!"
        override fun toTypst() = "${operand.toTypstPriority(this)}!"

        private fun factorial(n: Int): Double {
            require(n >= 0) { "Factorial is not defined for negative numbers." }
            return if (n <= 1) 1.0 else n * factorial(n - 1)
        }
    }

    data class Permutation(override val left: ScalarExpression, override val right: ScalarExpression) :
        BinaryOperation {
        override val priority: Priority = Priority.ParensFunc
        override fun evaluate(): Double {
            val nValue = mathNum(left.evaluate().toInt())
            val kValue = mathNum(right.evaluate().toInt())
            return Divide(
                Factorial(nValue),
                Factorial(
                    Subtract(
                        nValue,
                        kValue
                    )
                )
            ).evaluate()
        }

        override fun toLatex() = "{}^{${left.toLatexPriority(this)}}P_{${right.toLatexPriority(this)}}"
        override fun toTypst() = "P(${left.toTypstPriority(this)}, ${right.toTypstPriority(this)})"
    }


    data class Combination(override val left: ScalarExpression, override val right: ScalarExpression) :
        BinaryOperation {
        override val priority: Priority = Priority.ParensFunc
        override fun evaluate(): Double {
            val nValue = mathNum(left.evaluate().toInt())
            val kValue = mathNum(right.evaluate().toInt())
            return Divide(
                Factorial(nValue),
                Multiply(
                    Factorial(kValue),
                    Factorial(
                        Subtract(
                            nValue,
                            kValue
                        )
                    )
                )
            ).evaluate()
        }

        override fun toLatex() = "{}^{${left.toLatexPriority(this)}}C_{${right.toLatexPriority(this)}}"
        override fun toTypst() = "C(${left.toTypstPriority(this)}, ${right.toTypstPriority(this)})"
    }
}
