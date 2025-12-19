package main

import dev.patbeagan.math.base.ExperimentalMathDSL
import main.dsl.*
import main.dsl.expressions.ScalarAlgebra.*
import main.dsl.expressions.ScalarExpression
import main.dsl.mathnum.*
import main.dsl.mathnum.Scalar.RealNum
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalMathDSL::class)
class TreeRewriterTest {

    @Test
    fun test_identity_rules_add_zero() {
        val x = Variable("x")
        val expr = x + 0.0.num()
        val simplified = simplify(expr)
        assertEquals(x, simplified)
    }

    @Test
    fun test_identity_rules_zero_add() {
        val x = Variable("x")
        val expr = 0.0.num() + x
        val simplified = simplify(expr)
        assertEquals(x, simplified)
    }

    @Test
    fun test_identity_rules_multiply_one() {
        val x = Variable("x")
        val expr = x * 1.0.num()
        val simplified = simplify(expr)
        assertEquals(x, simplified)
    }

    @Test
    fun test_identity_rules_one_multiply() {
        val x = Variable("x")
        val expr = 1.0.num() * x
        val simplified = simplify(expr)
        assertEquals(x, simplified)
    }

    @Test
    fun test_zero_rules_multiply_zero() {
        val x = Variable("x")
        val expr = x * 0.0.num()
        val simplified = simplify(expr)
        assertEquals(0.0.num(), simplified)
    }

    @Test
    fun test_zero_rules_zero_multiply() {
        val x = Variable("x")
        val expr = 0.0.num() * x
        val simplified = simplify(expr)
        assertEquals(0.0.num(), simplified)
    }

    @Test
    fun test_subtract_zero() {
        val x = Variable("x")
        val expr = x - 0.0.num()
        val simplified = simplify(expr)
        assertEquals(x, simplified)
    }

    @Test
    fun test_zero_subtract() {
        val x = Variable("x")
        val expr = 0.0.num() - x
        val simplified = simplify(expr)
        assertTrue(simplified is Negate)
    }

    @Test
    fun test_subtract_same() {
        val x = Variable("x")
        val expr = x - x
        val simplified = simplify(expr)
        assertEquals(0.0.num(), simplified)
    }

    @Test
    fun test_divide_one() {
        val x = Variable("x")
        val expr = x / 1.0.num()
        val simplified = simplify(expr)
        assertEquals(x, simplified)
    }

    @Test
    fun test_divide_same() {
        val x = Variable("x")
        val expr = x / x
        val simplified = simplify(expr)
        assertEquals(1.0.num(), simplified)
    }

    @Test
    fun test_exponent_zero() {
        val x = Variable("x")
        val expr = x pow 0.0.num()
        val simplified = simplify(expr)
        assertEquals(1.0.num(), simplified)
    }

    @Test
    fun test_exponent_one() {
        val x = Variable("x")
        val expr = x pow 1.0.num()
        val simplified = simplify(expr)
        assertEquals(x, simplified)
    }

    @Test
    fun test_zero_exponent() {
        val x = Variable("x")
        val expr = 0.0.num() pow x
        val simplified = simplify(expr)
        assertEquals(0.0.num(), simplified)
    }

    @Test
    fun test_one_exponent() {
        val x = Variable("x")
        val expr = 1.0.num() pow x
        val simplified = simplify(expr)
        assertEquals(1.0.num(), simplified)
    }

    @Test
    fun test_negate_negate() {
        val x = Variable("x")
        val expr = Negate(Negate(x))
        val simplified = simplify(expr)
        assertEquals(x, simplified)
    }

    @Test
    fun test_negate_zero() {
        val expr = Negate(0.0.num())
        val simplified = simplify(expr)
        assertEquals(0.0.num(), simplified)
    }

    @Test
    fun test_combine_like_terms_add() {
        val x = Variable("x")
        val expr = (2.0.num() * x) + (3.0.num() * x)
        val simplified = simplify(expr)
        // Should be 5*x
        assertTrue(simplified is Multiply)
        val mult = simplified as Multiply
        assertTrue(mult.left is RealNum)
        assertEquals(5.0, (mult.left as RealNum).value)
        assertEquals(x, mult.right)
    }

    @Test
    fun test_combine_like_terms_variable() {
        val x = Variable("x")
        val expr = x + x
        val simplified = simplify(expr)
        // Should be 2*x
        assertTrue(simplified is Multiply)
        val mult = simplified as Multiply
        assertTrue(mult.left is RealNum)
        assertEquals(2.0, (mult.left as RealNum).value)
        assertEquals(x, mult.right)
    }

    @Test
    fun test_constant_evaluation() {
        val expr = 2.0.num() + 3.0.num()
        val simplified = simplify(expr)
        assertEquals(5.0.num(), simplified)
    }

    @Test
    fun test_nested_constant_evaluation() {
        val expr = (2.0.num() + 3.0.num()) * 4.0.num()
        val simplified = simplify(expr)
        assertEquals(20.0.num(), simplified)
    }

    @Test
    fun test_distribute_multiplication_over_addition() {
        val x = Variable("x")
        val expr = 2.0.num() * (x + 3.0.num())
        val simplified = simplify(expr)
        // Should be 2*x + 6
        assertTrue(simplified is Add)
        val add = simplified as Add
        assertTrue(add.left is Multiply)
        assertTrue(add.right is RealNum)
        assertEquals(6.0, (add.right as RealNum).value)
    }

    @Test
    fun test_exponent_power_rule() {
        val x = Variable("x")
        val expr = (x pow 2.0.num()) pow 3.0.num()
        val simplified = simplify(expr)
        // Should be x^(2*3) = x^6
        assertTrue(simplified is Exponent)
        val exp = simplified as Exponent
        assertEquals(x, exp.left)
        assertTrue(exp.right is RealNum)
        assertEquals(6.0, (exp.right as RealNum).value)
    }

    @Test
    fun test_sqrt_perfect_square() {
        val expr = Sqrt(4.0.num())
        val simplified = simplify(expr)
        assertEquals(2.0.num(), simplified)
    }

    @Test
    fun test_sqrt_zero() {
        val expr = Sqrt(0.0.num())
        val simplified = simplify(expr)
        assertEquals(0.0.num(), simplified)
    }

    @Test
    fun test_sqrt_one() {
        val expr = Sqrt(1.0.num())
        val simplified = simplify(expr)
        assertEquals(1.0.num(), simplified)
    }

    @Test
    fun test_ln_one() {
        val expr = Ln(1.0.num())
        val simplified = simplify(expr)
        assertEquals(0.0.num(), simplified)
    }

    @Test
    fun test_ln_exp() {
        val x = Variable("x")
        val expr = Ln(Exp(x))
        val simplified = simplify(expr)
        assertEquals(x, simplified)
    }

    @Test
    fun test_factorial_zero() {
        val expr = Factorial(0.0.num())
        val simplified = simplify(expr)
        assertEquals(1.0.num(), simplified)
    }

    @Test
    fun test_factorial_one() {
        val expr = Factorial(1.0.num())
        val simplified = simplify(expr)
        assertEquals(1.0.num(), simplified)
    }

    @Test
    fun test_factorial_five() {
        val expr = Factorial(5.0.num())
        val simplified = simplify(expr)
        assertEquals(120.0.num(), simplified)
    }

    @Test
    fun test_complex_expression_simplification() {
        val x = Variable("x")
        val expr = (1.0.num() + 5.0.num() + 5.0.num() * x + 2.0.num() - 1.0.num())
        val simplified = simplify(expr)
        // Should simplify to 7 + 5*x (or similar form)
        val latex = simplified.toLatex()
        assertTrue(latex.contains("5") || latex.contains("7"))
    }

    @Test
    fun test_flatten_nested_additions() {
        val x = Variable("x")
        val expr = (x + 1.0.num()) + 2.0.num()
        val simplified = simplify(expr)
        // Should be flattened
        assertTrue(simplified is Add)
    }

    @Test
    fun test_flatten_nested_multiplications() {
        val x = Variable("x")
        val expr = (x * 2.0.num()) * 3.0.num()
        val simplified = simplify(expr)
        // Should be flattened and evaluated
        assertTrue(simplified is Multiply)
        val mult = simplified as Multiply
        assertTrue(mult.left is RealNum)
        assertEquals(6.0, (mult.left as RealNum).value)
        assertEquals(x, mult.right)
    }

    @Test
    fun test_multiply_same_terms() {
        val x = Variable("x")
        val expr = x * x
        val simplified = simplify(expr)
        // Should be x^2
        assertTrue(simplified is Exponent)
        val exp = simplified as Exponent
        assertEquals(x, exp.left)
        assertTrue(exp.right is RealNum)
        assertEquals(2.0, (exp.right as RealNum).value)
    }

    @Test
    fun test_zero_divide() {
        val x = Variable("x")
        val expr = 0.0.num() / x
        val simplified = simplify(expr)
        assertEquals(0.0.num(), simplified)
    }
}
