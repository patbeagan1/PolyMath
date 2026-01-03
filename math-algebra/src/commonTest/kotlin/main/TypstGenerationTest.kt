package main

import main.dsl.*
import main.dsl.expressions.ScalarAlgebra.*
import main.dsl.expressions.ScalarRelation.Equation
import main.dsl.mathnum.*
import kotlin.test.Test
import kotlin.test.assertEquals

class TypstGenerationTest {

    @Test
    fun testBasicArithmetic() {
        val expr = Add(mathNum(5), Multiply(mathNum(3), mathNum(2)))
        assertEquals("5 + 3 * 2", expr.toTypst())
    }

    @Test
    fun testFractions() {
        val expr = Divide(mathNum(1), mathNum(4))
        assertEquals("frac(1, 4)", expr.toTypst())
    }

    @Test
    fun testPowers() {
        val expr = Exponent(mathNum(5), mathNum(3))
        assertEquals("5^3", expr.toTypst())
    }

    @Test
    fun testSquareRoot() {
        val expr = Sqrt(mathNum(16))
        assertEquals("sqrt(16)", expr.toTypst())
    }

    @Test
    fun testTrigonometricFunctions() {
        val sinExpr = Sin(mathNum(90))
        assertEquals("sin(90)", sinExpr.toTypst())
        
        val cosExpr = Cos(mathNum(45))
        assertEquals("cos(45)", cosExpr.toTypst())
        
        val tanExpr = Tan(mathNum(30))
        assertEquals("tan(30)", tanExpr.toTypst())
    }

    @Test
    fun testLogarithms() {
        val lnExpr = Ln(mathNum(10))
        assertEquals("ln(10)", lnExpr.toTypst())
        
        val logExpr = Log(mathNum(2), mathNum(8))
        assertEquals("log_(2)(8)", logExpr.toTypst())
    }

    @Test
    fun testExponential() {
        val expr = Exp(mathNum(2))
        assertEquals("e^2", expr.toTypst())
    }

    @Test
    fun testVariables() {
        val x = Variable("x")
        assertEquals("x", x.toTypst())
        
        val y = Variable("y").withInstance("1")
        assertEquals("y_1", y.toTypst())
    }

    @Test
    fun testSummation() {
        val sumExpr = Sum(Variable("k"), mathNum(1), Variable("n")) {
            Variable("k")
        }
        assertEquals("sum_(k=1)^(n) k", sumExpr.toTypst())
    }

    @Test
    fun testProduct() {
        val productExpr = Product(Variable("k"), mathNum(1), Variable("n")) {
            Variable("k")
        }
        assertEquals("prod_(k=1)^(n) k", productExpr.toTypst())
    }

    @Test
    fun testEquations() {
        val eq = Equation(mathNum(5), Add(mathNum(3), mathNum(2)))
        assertEquals("5 = 3 + 2", eq.toTypst())
    }

    @Test
    fun testComplexExpression() {
        val expr = Add(
            Exp(mathNum(2)),
            ArcSin(Sin(mathNum(3)))
        )
        assertEquals("e^2 + asin(sin(3))", expr.toTypst())
    }

    @Test
    fun testFactorial() {
        val expr = Factorial(mathNum(5))
        assertEquals("5!", expr.toTypst())
    }

    @Test
    fun testPermutation() {
        val expr = Permutation(mathNum(5), mathNum(3))
        assertEquals("P(5, 3)", expr.toTypst())
    }

    @Test
    fun testCombination() {
        val expr = Combination(mathNum(5), mathNum(3))
        assertEquals("C(5, 3)", expr.toTypst())
    }

    @Test
    fun testBooleanExpressions() {
        val p = PropositionalCalculus.Fact("p")
        val q = PropositionalCalculus.Fact("q")
        
        val andExpr = p and q
        assertEquals("p and q", andExpr.toTypst())
        
        val orExpr = p or q
        assertEquals("p or q", orExpr.toTypst())
        
        val notExpr = p.not()
        assertEquals("not p", notExpr.toTypst())
        
        val impliesExpr = p implies q
        assertEquals("p => q", impliesExpr.toTypst())
    }

    @Test
    fun testPriorityHandling() {
        // Test that parentheses are added correctly based on priority
        val expr1 = Multiply(Add(mathNum(1), mathNum(2)), mathNum(3))
        assertEquals("(1 + 2) * 3", expr1.toTypst())
        
        val expr2 = Add(mathNum(1), Multiply(mathNum(2), mathNum(3)))
        assertEquals("1 + 2 * 3", expr2.toTypst())
        
        val expr3 = Exponent(Add(mathNum(1), mathNum(2)), mathNum(3))
        assertEquals("(1 + 2)^3", expr3.toTypst())
    }

    @Test
    fun testRationalNumbers() {
        val rational = RationalNum(1, 4)
        assertEquals("frac(1, 4)", rational.toTypst())
        
        val whole = RationalNum(5, 1)
        assertEquals("5", whole.toTypst())
    }
}
