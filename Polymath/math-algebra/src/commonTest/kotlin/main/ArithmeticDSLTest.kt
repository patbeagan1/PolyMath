package main

import ColorPlaceholders
import dev.patbeagan.math.base.ExperimentalMathDSL
import main.dsl.*
import main.dsl.expressions.ScalarAlgebra.*
import main.dsl.expressions.ScalarExpression.EquateDirection.Left
import main.dsl.expressions.ScalarRelation.Equation
import main.dsl.expressions.ScalarExpression
import main.dsl.mathnum.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ArithmeticDSLTest : ColorPlaceholders {

    @Test
    fun dynamicVariables() {

        val varA = Variable("B")
        val equ = Add(varA, Multiply(varA, mathNum(2.0)))
        val result = (1..3).map {
            varA.setTo(it)
            equ.evaluate()
        }
        assertEquals(3.0, result[0])
        assertEquals(6.0, result[1])
        assertEquals(9.0, result[2])
        assertEquals(
            "B + B \\cdot 2\n" +
                    " where B = 3", equ.toLatex() + "\n where ${varA.glyph} = ${varA.value}"
        )
    }

    @Test
    fun calculatingProducts() {
        val product2 = Product(Variable("j"), mathNum(1), mathNum(10)) {
            Add(mathNum(1), it)
        }

        assertEquals(39916800.0, product2.evaluate())  // Output: ?
        assertEquals("\\prod_{j=1}^{10}{1 + j}", product2.toLatex())  // Output: \prod_{k=1}^{n}{k}
    }

    @Test
    fun products() {
        val productExpr = Product(Variable("k"), mathNum(1), Variable("n")) {
            Variable("k")
        } // Represents: Π from k=1 to n of k
        // LaTeX for the product
        assertEquals(Double.NaN, productExpr.evaluate())  // Output: NAN
        assertEquals("\\prod_{k=1}^{n}{k}", productExpr.toLatex())  // Output: \prod_{k=1}^{n}{k}
    }

    @Test
    fun calculatingSums() {
        val sum2 = Sum(Variable("j"), mathNum(1), mathNum(10)) {
            Add(mathNum(1), it)
        }

        assertEquals(65.0, sum2.evaluate())
        assertEquals("\\sum_{j=1}^{10}{1 + j}", sum2.toLatex())
    }

    @Test
    fun sums_evaluate_correctly() {
        val k = Variable("k", 6.0)
        val n = Variable("n", 5)
        val sumExpr = Sum(k, mathNum(1), n) { k } // Represents: Σ from k=1 to n of k

        // LaTeX for the summation
        assertEquals(30.0, sumExpr.evaluate())  // Output: \sum_{k=1}^{n}{k}
        assertEquals("\\sum_{k=1}^{n}{k}", sumExpr.toLatex())  // Output: \sum_{k=1}^{n}{k}
    }

    @Test
    fun sums_evalute_correcly_NaN() {
        val k = Variable("k")
        val n = Variable("n")
        val sumExpr = Sum(k, mathNum(1), n) { k } // Represents: Σ from k=1 to n of k

        // LaTeX for the summation
        assertEquals(Double.NaN, sumExpr.evaluate())  // Output: \sum_{k=1}^{n}{k}
        assertEquals("\\sum_{k=1}^{n}{k}", sumExpr.toLatex())  // Output: \sum_{k=1}^{n}{k}
    }

    @Test
    fun complexExpressions() {
        val complexExpr = Add(
            Exp(mathNum(2.0)), ArcSin(Sin(mathNum(3)))
        )
        // Represents: e^2 + sin^(-1)(sin(3))
        assertEquals(7.5306487525204435, complexExpr.evaluate())  // Output: e^{2} + \sin^{-1}(\sin(3.0))
        assertEquals(
            "e^{2} + \\sin^{-1}(\\sin(3))",
            complexExpr.toLatex()
        )  // Output: e^{2} + \sin^{-1}(\sin(3.0))
    }

    @Test
    fun exponents() {
        val expr2 = Add(Exponent(mathNum(5), mathNum(3)), Sin(mathNum(90.0)))
        // Represents: 5^3 + sin(90)

        assertEquals(125.89399666360056, expr2.evaluate())  // Output: 125.0
        assertEquals("5^{3} + \\sin(90)", expr2.toLatex())  // Output: 5^{3} + \sin(90.0)
    }

    @Test
    fun basicArithmetic() {
        // For ensuring order of operations:
        // 1. Perform Multiplications and Divisions first
        // 2. Then perform Additions and Subtractions
        val expr = Add(Multiply(mathNum(5), mathNum(3)), Subtract(mathNum(2.0), mathNum(1)))
        // Represents: 5 * 3 + (2 - 1)

        assertEquals(16.0, expr.evaluate())  // Output should be 16.0
        assertEquals("5 \\cdot 3 + 2 - 1", expr.toLatex())
    }

    @Test
    fun equations() {
        val expr = Add(Multiply(mathNum(5), mathNum(3)), Subtract(mathNum(2.0), mathNum(1)))
        val e = Equation(Variable("x"), expr)
            .applyOperation { Add(it, mathNum(5)) }
            .applyOperation { Subtract(it, mathNum(5)) }

        assertEquals("x + 5 - 5 = 5 \\cdot 3 + 2 - 1 + 5 - 5", e.toLatex())
    }

    @Test
    fun equations_evaluate_correctly() {
        assertEquals(true, Equation(mathNum(1) + mathNum(5), mathNum(6)).isValid())
        assertEquals(false, Equation(Variable("v") + mathNum(5), mathNum(6)).isValid())
    }

    @Test
    fun equations_with_proper_parenthesizing() {
        val expr = Add(Multiply(Subtract(mathNum(5), mathNum(5)), mathNum(3)), Subtract(mathNum(2.0), mathNum(1)))
        assertEquals(1.0, expr.evaluate().also { println(it) })
        assertEquals("(5 - 5) \\cdot 3 + 2 - 1", expr.toLatex())
    }

    @Test
    fun equations_with_proper_parenthesizing_2() {
        val expr = Add(
            Multiply(Subtract(mathNum(5), Subtract(mathNum(5), Multiply(mathNum(5), mathNum(5)))), mathNum(3)),
            Subtract(mathNum(2.0), mathNum(1))
        )
        assertEquals(76.0, expr.evaluate().also { println(it) })
        assertEquals("(5 - 5 - 5 \\cdot 5) \\cdot 3 + 2 - 1", expr.toLatex())
    }

    @Test
    fun equations_with_proper_parenthesizing_3() {
        val expr1 = mathNum(3) / mathNum(4)
        val expr2 = mathNum(3) * (mathNum(1) / mathNum(4))
        val expr = Equation(expr1, expr2)
        assertEquals("\\frac{3}{4} = 3 \\cdot \\frac{1}{4}", expr.toLatex())
    }

    @Test
    fun with_infix_arithmetic_functions() {

        fun testWithParens() {
            val five = mathNum(5)
            val seventeen = mathNum(17)

            val a = Variable("A")

            val add = (five + a) * seventeen
            val expr = add + add

            assertEquals("(5 + A) \\cdot 17", add.toLatex())
            assertEquals("(5 + A) \\cdot 17 + (5 + A) \\cdot 17", expr.toLatex())
            assertEquals(
                "Add(left=Multiply(left=Add(left=5, right=Variable(glyph=A, value=Undefined, name=null, instance=null, isBound=false)), right=17), right=Multiply(left=Add(left=5, right=Variable(glyph=A, value=Undefined, name=null, instance=null, isBound=false)), right=17))",
                expr.toString()
            )
        }
        testWithParens()

        fun testWithoutParens() {
            val five = mathNum(5)
            val seventeen = mathNum(17)

            val a = Variable("A")

            val add = five + a * seventeen
            val expr = add + add

            assertEquals("5 + A \\cdot 17", add.toLatex())
            assertEquals("5 + A \\cdot 17 + 5 + A \\cdot 17", expr.toLatex())
            assertEquals(
                """Add(left=Add(left=5, right=Multiply(left=Variable(glyph=A, value=Undefined, name=null, instance=null, isBound=false), right=17)), right=Add(left=5, right=Multiply(left=Variable(glyph=A, value=Undefined, name=null, instance=null, isBound=false), right=17)))""",
                expr.toString()
            )
        }
        testWithoutParens()
    }

    @Test
    fun equations_walk() {
        val expr = Add(Multiply(mathNum(5), mathNum(3)), Subtract(mathNum(2.0), mathNum(1)))
        val e = Equation(Variable("x"), expr)
            .applyOperation { Add(it, mathNum(5)) }
            .applyOperation { Subtract(it, mathNum(5)) }

        val l = mutableListOf<String>()
        e.walk { l.add(it.toString()) }

        assertEquals(
            """[Subtract(left=Add(left=Variable(glyph=x, value=Undefined, name=null, instance=null, isBound=false), right=5), right=5), Add(left=Variable(glyph=x, value=Undefined, name=null, instance=null, isBound=false), right=5), Variable(glyph=x, value=Undefined, name=null, instance=null, isBound=false), 5, 5, Subtract(left=Add(left=Add(left=Multiply(left=5, right=3), right=Subtract(left=2.0, right=1)), right=5), right=5), Add(left=Add(left=Multiply(left=5, right=3), right=Subtract(left=2.0, right=1)), right=5), Add(left=Multiply(left=5, right=3), right=Subtract(left=2.0, right=1)), Multiply(left=5, right=3), 5, 3, Subtract(left=2.0, right=1), 2.0, 1, 5, 5]""",
            l.toString()
        )
    }

    @Test
    fun permutations_work() {
        val n = Variable("n")
        val k = Variable("k")
        val expr = Permutation(n, k)

        assertEquals("{}^{n}P_{k}", expr.toLatex())
    }

    @Test
    fun combinations_work() {
        val n = Variable("n")
        val k = Variable("k")
        val expr = Combination(n, k)

        assertEquals("{}^{n}C_{k}", expr.toLatex())
    }

    @Test
    fun solving_math_function_questions() {
        val a = Variable("a", 1)
        val b = Variable("b", 2)
        val c = Variable("c", 3)
        val n = Variable("n", 4)
        val expr = (a pow n) * (Log(b, n) + Cos(mathNum(5))) * (c pow n)
        val equation = expr.equate()
        assertEquals(true, equation.isValid())
        assertEquals("a^{n} \\cdot (\\log_{b}(n) + \\cos(5)) \\cdot c^{n} = 184.97663702252132", equation.toLatex())
        assertEquals(184.97663702252132, expr.evaluate())
    }

    @Test
    fun solving_an_expression_with_a_set_of_variables___plug_and_play() {
        val a = Variable("a", 1)
        val b = Variable("b", 2)
        val c = Variable("c", 3)
        val n = Variable("n", 4)
        val expr = (a pow n) * (b pow n) * (c pow n)
        val equation = expr.equate()
        assertEquals(true, equation.isValid())
        assertEquals(1296.0, expr.evaluate())
        assertEquals(
            """Given
a = 1
b = 2
c = 3
n = 4

a^{n} \cdot b^{n} \cdot c^{n} = 1296

yields (1296.0, 1296.0)""", equation.explain()
        )
    }

    @Test
    fun solving_a_pythagorean_equation() {
        val a = Variable("a", 5)
        val b = Variable("b", 12.1)
        val expr = Sqrt((a pow mathNum(2)) + (b pow mathNum(2)))
        val equation = expr.equate()
        println(equation.explain())
        println(mathNum((equation.right.evaluate())) pow mathNum(2))
    }

    @Test
    fun ensuring_sqrt_and_square_are_inverses_of_each_other() {
        val expr = Equation(mathNum(5), Sqrt(mathNum(5)).squared())
        assertTrue(expr.validateApprox())
    }

    @Test
    fun testing_evaluateGet() {
        val expr = Equation(mathNum(5), 5.num())
        assertEquals(5.0, expr.evaluateGet().getOrNull())
    }

    @Test
    fun testing_basic_math_operations() {
        assertEquals(10.0, (mathNum(3) + mathNum(7)).evaluate())
        assertEquals(10.0, (mathNum(5) * mathNum(2)).evaluate())
        assertEquals(10.0, (mathNum(13) - mathNum(3)).evaluate())
        assertEquals(10.0, (mathNum(100) / mathNum(10)).evaluate())
        assertEquals(7.0, (mathNum(107) % mathNum(10)).evaluate())
    }

    @Test
    fun testing_function_order_of_operations_prints_correctly() {
        assertEquals("\\cos(2 \\cdot 3)", (Cos(mathNum(2) * mathNum(3))).toLatex())
    }

    @Test
    fun testing_vars_and_consts() {
        val m = Variable("m")
        val c = Variable("c")
        assertEquals("E = m \\cdot c^{2}", (m * c.squared()).equate("E", Left).toLatex())
    }

    @Test
    fun testing_solve_for() {
        fun subject(): ScalarExpression {
            val a = Variable("a")
            val b = Constant("b", 10.0)
            return a * b.squared()
        }

        val s = subject()
        assertEquals(
            """Given
b = 10.0
a is undefined

a \cdot b^{2} = NaN

yields (NaN, NaN)""", s.equate().explain()
        )

        s.assignVariables("a" to 2.0, "b" to 3.0)
        assertEquals(
            """Given
b = 10
a = 2

a \cdot b^{2} = 200

yields (200.0, 200.0)""", s.equate().explain()
        )

        assertEquals(
            200.0,
            subject().assignVariables("a" to 2.0).evaluate()
        )
    }

    @Test
    fun equations_balancing_simpler_syntax() {
        val expr = mathNum(5) * mathNum(3) + 2.0.num() - 1.num()

        val e = expr
            .equate("x", Left)
            .add(mathNum(5))
            .subtract(mathNum(5))

        assertEquals("x + 5 - 5 = 5 \\cdot 3 + 2 - 1 + 5 - 5", e.toLatex())
    }


    @OptIn(ExperimentalMathDSL::class)
    @Test
    fun simplify_expression() {
        val expr = 1.num() + 5.num() + mathNum(5) * Variable("x") + 2.0.num() - 1.num()
        val s = expr
            .let(::simplifyPriorityRight)
            .let(::simplify)
            .also(::println)

        assertEquals("6 + 5 \\cdot x + 1", s.toLatex())
    }

    @Test
    fun complex_equation_rendering() {
        val x = Variable("x", 5)
        val e = Equation(
            (1.num() + x) pow 3.0.num(),
            mathNum(1) + mathNum(3) * x + mathNum(3) * x.squared() + x.pow(mathNum(3.0))
        )
        assertEquals(
            """Given
x = 5

(1 + x)^{3} = 1 + 3 \cdot x + 3 \cdot x^{2} + x^{3}

yields (216.0, 216.0)""", e.explain()
        )
        assertTrue(e.isValid())
    }

    @Test
    fun complex_equation_rendering_2() {
        val phi = Variable("\\phi", 3)
        val e = Constant("e", 2.71)
        val pi = Constant("\\pi", 3.14)

        val s2 = Sqrt(phi * Sqrt(5.num())) - phi
        val s3 = e pow (mathNum(2) / mathNum(5) * pi)
        val s4 = mathNum(1) / (s2 * s3)
        val expr = Variable("T").standsFor(s4)

        assertEquals(
            """Given
T = -0.6973133301810529
\phi = 3.0
\pi = 3.14
e = 2.71

T = \frac{1}{(\sqrt{\phi \cdot \sqrt{5}} - \phi) \cdot e^{\frac{2}{5} \cdot \pi}}

yields (-0.6973133301810529, -0.6973133301810529)""", expr.explain()
        )
        assertTrue(expr.isValid())
    }

    @Test
    fun relations_work() {
        assertTrue((mathNum(1) isLessThan mathNum(2)).isValid())
        assertTrue((mathNum(2) isGreaterThan mathNum(1)).isValid())
        assertTrue((mathNum(2) isGreaterThanOrEqualTo mathNum(1)).isValid())
        assertTrue((mathNum(2) isGreaterThanOrEqualTo mathNum(2)).isValid())
        assertTrue((mathNum(1) isLessThanOrEqualTo mathNum(2)).isValid())
        assertTrue((mathNum(1) isLessThanOrEqualTo mathNum(1)).isValid())
        assertTrue((mathNum(1) isEqualTo mathNum(1)).isValid())
    }

    @Test
    fun functions_work() {
        val x = Variable("x")
        val y = Variable("y")
        val f = MathFunction(x * y.squared(), "f", "myFunction")
        println(f.toLatex())

        val g = MathFunction((x + 2.num()) / y.squared())
        println(g.toLatex())

        println(f.assignVariables("x" to 2.0, "y" to 2.0).equate("z").explain())
    }
}