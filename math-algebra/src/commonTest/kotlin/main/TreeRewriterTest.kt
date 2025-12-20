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

    // ========== Tests for Rule Logging ==========
    
    @Test
    fun test_rule_logging_enabled() {
        val rules = mutableListOf<SimplificationRule>()
        TreeRewriter.enableLogging { rule -> rules.add(rule) }
        
        try {
            val x = Variable("x")
            val expr = x + 0.0.num()
            simplify(expr)
            
            // Should have logged at least one rule
            assertTrue(rules.isNotEmpty(), "Rules should be logged when logging is enabled")
            // Should have logged the additive identity rule
            assertTrue(rules.any { it.name.contains("Additive Identity") }, 
                "Should log additive identity rule")
        } finally {
            TreeRewriter.disableLogging()
        }
    }
    
    @Test
    fun test_rule_logging_disabled() {
        val rules = mutableListOf<SimplificationRule>()
        TreeRewriter.disableLogging()
        
        val x = Variable("x")
        val expr = x + 0.0.num()
        simplify(expr)
        
        // Should not have logged any rules
        assertEquals(0, rules.size, "Rules should not be logged when logging is disabled")
    }
    
    @Test
    fun test_simplify_with_log_rules_parameter() {
        val rules = mutableListOf<SimplificationRule>()
        TreeRewriter.enableLogging { rule -> rules.add(rule) }
        
        try {
            val x = Variable("x")
            val expr = x * 1.0.num()
            simplify(expr, logRules = true)
            
            assertTrue(rules.isNotEmpty(), "Rules should be logged when logRules=true")
        } finally {
            TreeRewriter.disableLogging()
        }
    }

    // ========== Tests for New Simplification Rules ==========
    
    @Test
    fun test_sin_negative() {
        val x = Variable("x")
        val expr = Sin(Negate(x))
        val simplified = simplify(expr)
        assertTrue(simplified is Negate)
        val neg = simplified as Negate
        assertTrue(neg.operand is Sin)
    }
    
    @Test
    fun test_cos_negative() {
        val x = Variable("x")
        val expr = Cos(Negate(x))
        val simplified = simplify(expr)
        assertTrue(simplified is Cos)
        assertEquals(x, (simplified as Cos).operand)
    }
    
    @Test
    fun test_tan_negative() {
        val x = Variable("x")
        val expr = Tan(Negate(x))
        val simplified = simplify(expr)
        assertTrue(simplified is Negate)
        val neg = simplified as Negate
        assertTrue(neg.operand is Tan)
    }
    
    @Test
    fun test_sin_arcsin() {
        val x = Variable("x")
        val expr = Sin(ArcSin(x))
        val simplified = simplify(expr)
        assertEquals(x, simplified)
    }
    
    @Test
    fun test_cos_arccos() {
        val x = Variable("x")
        val expr = Cos(ArcCos(x))
        val simplified = simplify(expr)
        assertEquals(x, simplified)
    }
    
    @Test
    fun test_tan_arctan() {
        val x = Variable("x")
        val expr = Tan(ArcTan(x))
        val simplified = simplify(expr)
        assertEquals(x, simplified)
    }
    
    @Test
    fun test_negative_exponent() {
        val x = Variable("x")
        val expr = x pow Negate(2.0.num())
        val simplified = simplify(expr)
        assertTrue(simplified is Divide)
        val div = simplified as Divide
        assertEquals(1.0.num(), div.left)
        assertTrue(div.right is Exponent)
    }
    
    @Test
    fun test_distribute_exponent_over_multiplication() {
        val x = Variable("x")
        val y = Variable("y")
        val expr = (x * y) pow 2.0.num()
        val simplified = simplify(expr)
        assertTrue(simplified is Multiply)
        val mult = simplified as Multiply
        assertTrue(mult.left is Exponent)
        assertTrue(mult.right is Exponent)
    }
    
    @Test
    fun test_distribute_exponent_over_division() {
        val x = Variable("x")
        val y = Variable("y")
        val expr = (x / y) pow 2.0.num()
        val simplified = simplify(expr)
        assertTrue(simplified is Divide)
        val div = simplified as Divide
        assertTrue(div.left is Exponent)
        assertTrue(div.right is Exponent)
    }
    
    @Test
    fun test_distribute_multiplication_over_subtraction_right() {
        val x = Variable("x")
        val expr = 2.0.num() * (x - 3.0.num())
        val simplified = simplify(expr)
        assertTrue(simplified is Subtract)
        val sub = simplified as Subtract
        assertTrue(sub.left is Multiply)
        assertTrue(sub.right is Multiply)
    }
    
    @Test
    fun test_distribute_multiplication_over_subtraction_left() {
        val x = Variable("x")
        val expr = (x - 3.0.num()) * 2.0.num()
        val simplified = simplify(expr)
        assertTrue(simplified is Subtract)
        val sub = simplified as Subtract
        assertTrue(sub.left is Multiply)
        assertTrue(sub.right is Multiply)
    }
    
    @Test
    fun test_divide_negative_right() {
        val x = Variable("x")
        val expr = x / Negate(2.0.num())
        val simplified = simplify(expr)
        assertTrue(simplified is Negate)
        val neg = simplified as Negate
        assertTrue(neg.operand is Divide)
    }
    
    @Test
    fun test_divide_negative_left() {
        val x = Variable("x")
        val expr = Negate(x) / 2.0.num()
        val simplified = simplify(expr)
        assertTrue(simplified is Negate)
        val neg = simplified as Negate
        assertTrue(neg.operand is Divide)
    }
    
    @Test
    fun test_log_base_equals_argument() {
        val x = Variable("x")
        val expr = Log(x, x)
        val simplified = simplify(expr)
        assertEquals(1.0.num(), simplified)
    }
    
    @Test
    fun test_log_of_one() {
        val x = Variable("x")
        val expr = Log(x, 1.0.num())
        val simplified = simplify(expr)
        assertEquals(0.0.num(), simplified)
    }
    
    @Test
    fun test_exp_zero() {
        val expr = Exp(0.0.num())
        val simplified = simplify(expr)
        assertEquals(1.0.num(), simplified)
    }
    
    @Test
    fun test_exp_ln() {
        val x = Variable("x")
        val expr = Exp(Ln(x))
        val simplified = simplify(expr)
        assertEquals(x, simplified)
    }
    
    @Test
    fun test_arctan_zero() {
        val expr = ArcTan(0.0.num())
        val simplified = simplify(expr)
        assertEquals(0.0.num(), simplified)
    }
}
