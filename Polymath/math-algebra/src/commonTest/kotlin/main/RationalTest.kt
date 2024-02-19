package main

import main.dsl.mathnum.Scalar.RationalNum
import main.dsl.mathnum.mathNum
import main.dsl.mathnum.toRational
import kotlin.test.Test
import kotlin.test.assertEquals

class RationalTest {
    @Test
    fun arithmetic_works() {
        val r1 = RationalNum(1, 2)
        val r2 = RationalNum(2, 3)

        assertEquals(
            "1/2 + 2/3 = 7/6",
            "${r1.toLatexSimple()} + ${r2.toLatexSimple()} = ${(r1 evalPlus r2).toLatexSimple()}"
        )
        assertEquals(
            "1/2 - 2/3 = -1/6",
            "${r1.toLatexSimple()} - ${r2.toLatexSimple()} = ${(r1 evalMinus r2).toLatexSimple()}"
        )
        assertEquals(
            "1/2 * 2/3 = 1/3",
            "${r1.toLatexSimple()} * ${r2.toLatexSimple()} = ${(r1 evalTimes r2).toLatexSimple()}"
        )
        assertEquals(
            "1/2 / 2/3 = 3/4",
            "${r1.toLatexSimple()} / ${r2.toLatexSimple()} = ${(r1 evalDiv r2).toLatexSimple()}"
        )
    }

    @Test
    fun more_digits_of_pi() {
        assertEquals(
            "245850922/78256779",
            3.1415926535897932384626433832795028841971693993751058209
                .toRational(1.0e-50)
                .toLatexSimple()
        )
    }

    @Test
    fun first_digits_of_pi() {
        assertEquals("RationalNum(numerator=355, denominator=113)", 3.14159.toRational().toString())
    }

    @Test
    fun rational_addition_works_within_math_framework() {
        assertEquals("1/4", (mathNum(1) / mathNum(4)).toLatexSimple())
    }

    @Test
    fun rational_addition_works_within_math_framework_latex() {
        assertEquals("\\frac{1}{4}", (mathNum(1) / mathNum(4)).toLatex())
    }

    @Test
    fun rational_and_irrational_work() {
        val r1 = mathNum(1) / mathNum(3)
        val r2 = mathNum(1) / mathNum(4)
        val a = r1 + r2
        assertEquals("\\frac{1}{3} + \\frac{1}{4}", a.toLatex())
        assertEquals(0.5833333333333333, a.evaluate())
        assertEquals(mathNum(7) / mathNum(12), r1 evalPlus r2)
    }
}