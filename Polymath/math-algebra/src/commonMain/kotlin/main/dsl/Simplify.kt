package main.dsl

import dev.patbeagan.math.base.ExperimentalMathDSL
import main.dsl.expressions.ScalarAlgebra.*
import main.dsl.expressions.ScalarExpression
import main.dsl.mathnum.MathNum
import main.dsl.mathnum.Scalar.RealNum

/**
 * Currently this does a pretty bad job of approximating
 */
@ExperimentalMathDSL
fun simplify(expression: ScalarExpression): ScalarExpression = when (expression) {
    is MathNum -> expression
    is UnaryOperation -> {
        if (simplify(expression.operand) is RealNum) {
            RealNum(expression.evaluate())
        } else {
            expression
        }
    }

    is BinaryOperation -> {
        val left = simplify(expression.left)
        val right = simplify(expression.right)
        if (left is RealNum && right is RealNum) {
            RealNum(expression.evaluate())
        } else {
            when (expression) {
                is Add -> Add(left, right)
                is Subtract -> Subtract(left, right)
                is Multiply -> Multiply(left, right)
                is Divide -> Divide(left, right)
                is Modulo -> Modulo(left, right)
                else -> expression
            }
        }
    }

    else -> expression
}

@ExperimentalMathDSL
fun simplifyPriorityRight(expression: ScalarExpression): ScalarExpression = when (expression) {
    is MathNum -> expression
    is UnaryOperation -> {
        if (simplifyPriorityRight(expression.operand) is RealNum) {
            RealNum(expression.evaluate())
        } else {
            expression
        }
    }

    is BinaryOperation -> {
        val left = simplifyPriorityRight(expression.right)
        val right = simplifyPriorityRight(expression.left)
        if (left is RealNum && right is RealNum) {
            RealNum(expression.evaluate())
        } else {
            when (expression) {
                is Add -> Add(left, right)
                is Subtract -> Subtract(left, right)
                is Multiply -> Multiply(left, right)
                is Divide -> Divide(left, right)
                is Modulo -> Modulo(left, right)
                else -> expression
            }
        }
    }

    else -> expression
}
