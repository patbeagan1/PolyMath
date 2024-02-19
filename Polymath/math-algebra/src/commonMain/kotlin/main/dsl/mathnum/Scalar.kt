package main.dsl.mathnum

import kotlin.jvm.JvmInline
import kotlin.math.abs

sealed interface Scalar : MathNum {

    data object Undefined : Scalar {
        override fun evaluate(): Double = Double.NaN
        override fun toLatex(): String = "Undefined"
    }

    @JvmInline
    value class RealNum(val value: Double) : Scalar {
        override fun evaluate(): Double = value
        override fun toLatex(): String = if (value.toString().endsWith(".0")) "${value.toInt()}" else "$value"
        override fun toString(): String = value.toString()
    }

    data class RationalNum(val numerator: Long, val denominator: Long) : Scalar {
        init {
            require(denominator != 0L) { "Denominator cannot be zero." }
        }

        fun gcd(a: Long, b: Long): Long {
            return if (b == 0L) abs(a) else gcd(b, a % b)
        }

        private fun simplify(): RationalNum {
            val gcd = gcd(numerator, denominator)
            return RationalNum(numerator / gcd, denominator / gcd)
        }

        infix fun evalPlus(other: RationalNum): RationalNum {
            val commonDenominator = this.denominator * other.denominator
            val numeratorSum = this.numerator * other.denominator + other.numerator * this.denominator
            return RationalNum(numeratorSum, commonDenominator).simplify()
        }

        infix fun evalMinus(other: RationalNum): RationalNum {
            val commonDenominator = this.denominator * other.denominator
            val numeratorDifference = this.numerator * other.denominator - other.numerator * this.denominator
            return RationalNum(numeratorDifference, commonDenominator).simplify()
        }

        infix fun evalTimes(other: RationalNum): RationalNum {
            val numeratorNew = this.numerator * other.numerator
            val denominatorNew = this.denominator * other.denominator
            return RationalNum(numeratorNew, denominatorNew).simplify()
        }

        infix fun evalDiv(other: RationalNum): RationalNum {
            val numeratorNew = this.numerator * other.denominator
            val denominatorNew = this.denominator * other.numerator
            return RationalNum(numeratorNew, denominatorNew).simplify()
        }

        override fun evaluate(): Double = numerator.toDouble() / denominator
        override fun toLatex(): String = if (denominator == 1L) "$numerator" else "\\frac{$numerator}{$denominator}"
        fun toLatexSimple(): String = if (denominator == 1L) "$numerator" else "$numerator/$denominator"

        override fun equals(other: Any?): Boolean = when (other) {
            is Scalar -> {
                when (other) {
                    is IntegerNum -> other.value == this.numerator && this.denominator == 1L
                    is RationalNum -> other.numerator == this.numerator && other.denominator == this.denominator
                    is Constant -> other.value.evaluate() == evaluate()
                    is Variable -> other.value.evaluate() == evaluate()
                    is RealNum -> other.value == evaluate()
                    Undefined -> false
                }
            }

            else -> false
        }

        override fun hashCode(): Int {
            var result = numerator.hashCode()
            result = 31 * result + denominator.hashCode()
            return result
        }
    }

    @JvmInline
    value class IntegerNum(val value: Long) : Scalar {
        operator fun div(other: IntegerNum): RationalNum = RationalNum(this.value, other.value)
        override fun evaluate(): Double = value.toDouble()
        override fun toLatex(): String = value.toString()
        override fun toString(): String = value.toString()
    }
}