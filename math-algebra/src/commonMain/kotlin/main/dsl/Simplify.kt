package main.dsl

import dev.patbeagan.math.base.ExperimentalMathDSL
import main.dsl.expressions.ScalarAlgebra.*
import main.dsl.expressions.ScalarExpression
import main.dsl.mathnum.MathNum
import main.dsl.mathnum.Scalar.RealNum

/**
 * Simplifies an algebraic expression using tree rewriting.
 * 
 * This function applies algebraic simplification rules to simplify expressions:
 * - Evaluates constant expressions
 * - Applies identity rules (x + 0 = x, x * 1 = x)
 * - Applies zero rules (x * 0 = 0)
 * - Combines like terms
 * - Simplifies nested operations
 * 
 * @param expression The expression to simplify
<<<<<<< HEAD
 * @param logRules If true, prints the rules being applied during simplification
 * @return A simplified version of the expression
 */
@ExperimentalMathDSL
fun simplify(expression: ScalarExpression, logRules: Boolean = false): ScalarExpression {
    val wasLogging = TreeRewriter.isLoggingEnabled
    if (logRules && !wasLogging) {
        TreeRewriter.enableLogging()
    }
    try {
        return TreeRewriter.rewrite(expression)
    } finally {
        if (logRules && !wasLogging) {
            TreeRewriter.disableLogging()
        }
    }
=======
 * @return A simplified version of the expression
 */
@ExperimentalMathDSL
fun simplify(expression: ScalarExpression): ScalarExpression {
    return TreeRewriter.rewrite(expression)
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
}

/**
 * Legacy simplification function that processes expressions with right-to-left priority.
 * This function is kept for backward compatibility but now uses the tree rewriting system.
 * 
 * @param expression The expression to simplify
 * @return A simplified version of the expression
 */
@ExperimentalMathDSL
fun simplifyPriorityRight(expression: ScalarExpression): ScalarExpression {
    // Apply tree rewriting which handles simplification correctly
    return TreeRewriter.rewrite(expression)
}
