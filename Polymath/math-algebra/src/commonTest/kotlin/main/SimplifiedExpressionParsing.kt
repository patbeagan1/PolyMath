package main

import kotlin.test.Test

class SimplifiedExpressionParsing {
    object TestExpressions {
        sealed class Expression

        data class BinaryExpression(
            val left: Expression,
            val operator: (Double, Double) -> Double,
            val right: Expression
        ) : Expression() {
            operator fun plus(other: Expression) = BinaryExpression(this, Double::plus, other)
        }


        data class UnaryExpression(val operand: Expression, val operator: (Double) -> Double) : Expression()

        data class NumberNode(val value: Double) : Expression()

        data class VariableNode(val name: String) : Expression()

        fun consolidateTree(expression: Expression): Expression = when (expression) {
            is NumberNode -> expression
            is VariableNode -> expression // Can't evaluate variables without bindings
            is UnaryExpression -> {
                val consolidatedOperand = consolidateTree(expression.operand)
                if (consolidatedOperand is NumberNode) {
                    NumberNode(expression.operator(consolidatedOperand.value))
                } else {
                    UnaryExpression(consolidatedOperand, expression.operator)
                }
            }

            is BinaryExpression -> {
                val left = consolidateTree(expression.left)
                val right = consolidateTree(expression.right)
                if (left is NumberNode && right is NumberNode) {
                    NumberNode(expression.operator(left.value, right.value))
                } else {
                    BinaryExpression(left, expression.operator, right)
                }
            }
        }
    }

    @Test
    fun simple_expressions_work_as_desired() {
        val expression = TestExpressions.BinaryExpression(
            TestExpressions.VariableNode("a"),
            Double::plus,
            TestExpressions.BinaryExpression(
                TestExpressions.NumberNode(5.0),
                Double::plus,
                TestExpressions.BinaryExpression(
                    TestExpressions.NumberNode(3.0),
                    Double::times,
                    TestExpressions.NumberNode(2.0)
                )
            )
        ) + TestExpressions.NumberNode(7.0)
        val consolidated = TestExpressions.consolidateTree(expression)
        println(consolidated)

    }
}