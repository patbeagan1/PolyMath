package main

import main.dsl.expressions.PropositionalCalculus
import main.dsl.mathnum.MathNum
import main.dsl.mathnum.mathNum
import main.dsl.mathnum.num
import kotlin.jvm.JvmInline
import kotlin.test.Test

class BooleanAlgebraDSLTest {

    @Test
    fun simple() {
        val p = PropositionalCalculus.Fact("p", name = "It is raining")
        val q = PropositionalCalculus.Fact("q", name = "It is cloudy")
        val v = p implies q

        println(v.toLatex())
        println(v.evaluate())
    }

    @Test
    fun rendering() {
        val p = PropositionalCalculus.Fact("p")
        val q = PropositionalCalculus.Fact("q")

        println((p and q).toLatex())
        println((p and q.not()).toLatex())
        println((p or q).toLatex())
        println((p xor (q and q) xor p).toLatex())
        println((p nand q).toLatex())
        println((p implies q and (q implies p)).toLatex())
    }
}

@JvmInline
value class BinaryNumber(private val value: String) {
    fun toDecimal() = mathNum(Util.binaryToDecimal(value))

    companion object {
        fun from(number: MathNum) = BinaryNumber(Util.decimalToBinary(number.evaluate()))

    }

    private object Util {

        fun binaryToDecimal(binary: String): Double {
            var n = binary
            var d = 0.0
            var i = 0.0

            while (n.isNotEmpty()) {
                val lastDigit = n.last().toString().toDouble()
                n = n.dropLast(1)
                d += lastDigit * i * i
                i++
            }

            return d
        }

        fun decimalToBinary(decimal: Double): String {
            val integerPart = decimal.toInt()
            val fractionalPart = decimal - integerPart

            val integerBinary = decimalToBinary(integerPart)

            var fractionalBinary = ""
            var fractional = fractionalPart
            var precision = 20 // Adjust the precision as needed

            while (fractional > 0 && precision > 0) {
                fractional *= 2
                fractionalBinary += fractional.toInt().toString()
                fractional -= fractional.toInt()
                precision--
            }

            return if (fractionalBinary.isNotEmpty()) {
                "$integerBinary.${fractionalBinary}"
            } else {
                integerBinary
            }
        }

        fun decimalToBinary(decimal: Int): String {
            if (decimal == 0) {
                return "0"
            }

            var num = decimal
            val binaryStringBuilder = StringBuilder()

            while (num > 0) {
                val remainder = num % 2
                binaryStringBuilder.append(remainder)
                num /= 2
            }

            return binaryStringBuilder.reverse().toString()
        }
    }
}

class A {
    @Test
    fun main() {
        val decimalNumber = 47 // Replace with your desired decimal number
        val binaryRepresentation = BinaryNumber.from(decimalNumber.num())
        println("Binary representation of $decimalNumber is $binaryRepresentation")

        mathNum(17).let { BinaryNumber.from(it) }.also { println(it) }.also { println(it.toDecimal()) }
    }
}