package main.dsl

import dev.patbeagan.math.base.ExperimentalMathDSL
import main.dsl.expressions.ScalarAlgebra.*
import main.dsl.expressions.ScalarExpression
import main.dsl.mathnum.MathNum
import main.dsl.mathnum.Scalar
import main.dsl.mathnum.Scalar.RealNum
import main.dsl.mathnum.Scalar.Undefined.pow
import main.dsl.mathnum.Variable
import kotlin.math.abs

/**
 * Tree rewriting system for simplifying algebraic expressions.
 * 
 * This module provides a rule-based tree rewriting system that can apply
 * various algebraic simplification rules to expression trees.
 */
@ExperimentalMathDSL
object TreeRewriter {
    
    /**
     * Rewrites an expression tree by applying simplification rules.
     * This function recursively applies all available rewriting rules until
     * no more simplifications can be made.
     */
    fun rewrite(expression: ScalarExpression): ScalarExpression {
        var current = expression
        var previous: ScalarExpression?
        var iterations = 0
        val maxIterations = 100 // Prevent infinite loops
        
        do {
            previous = current
            current = applyAllRules(current)
            iterations++
        } while (current != previous && iterations < maxIterations)
        
        return current
    }
    
    /**
     * Applies all rewriting rules to an expression.
     */
    private fun applyAllRules(expression: ScalarExpression): ScalarExpression {
        return when (expression) {
            is MathNum -> expression
            
            is UnaryOperation -> rewriteUnary(expression)
            
            is BinaryOperation -> rewriteBinary(expression)
            
            else -> expression
        }
    }
    
    /**
     * Rewrites unary operations.
     */
    private fun rewriteUnary(expression: UnaryOperation): ScalarExpression {
        val simplifiedOperand = rewrite(expression.operand)
        
        return when (expression) {
            is Negate -> rewriteNegate(simplifiedOperand)
            is Sqrt -> rewriteSqrt(simplifiedOperand)
            is Sin -> rewriteSin(simplifiedOperand)
            is Cos -> rewriteCos(simplifiedOperand)
            is Tan -> rewriteTan(simplifiedOperand)
            is Ln -> rewriteLn(simplifiedOperand)
            is Exp -> rewriteExp(simplifiedOperand)
            is Abs -> rewriteAbs(simplifiedOperand)
            is ArcSin -> rewriteArcSin(simplifiedOperand)
            is ArcCos -> rewriteArcCos(simplifiedOperand)
            is ArcTan -> rewriteArcTan(simplifiedOperand)
            is Factorial -> rewriteFactorial(simplifiedOperand)
            else -> expression
        }
    }
    
    /**
     * Rewrites binary operations.
     */
    private fun rewriteBinary(expression: BinaryOperation): ScalarExpression {
        val simplifiedLeft = rewrite(expression.left)
        val simplifiedRight = rewrite(expression.right)
        
        return when (expression) {
            is Add -> rewriteAdd(simplifiedLeft, simplifiedRight)
            is Subtract -> rewriteSubtract(simplifiedLeft, simplifiedRight)
            is Multiply -> rewriteMultiply(simplifiedLeft, simplifiedRight)
            is Divide -> rewriteDivide(simplifiedLeft, simplifiedRight)
            is Modulo -> rewriteModulo(simplifiedLeft, simplifiedRight)
            is Exponent -> rewriteExponent(simplifiedLeft, simplifiedRight)
            is Log -> rewriteLog(simplifiedLeft, simplifiedRight)
            else -> expression
        }
    }
    
    // ========== Unary Operation Rules ==========
    
    private fun rewriteNegate(operand: ScalarExpression): ScalarExpression {
        // -(-x) = x
        if (operand is Negate) {
            return operand.operand
        }
        // -0 = 0
        if (isZero(operand)) {
            return RealNum(0.0)
        }
        // If operand is a constant, evaluate it
        if (operand is RealNum) {
            return RealNum(-operand.value)
        }
        return Negate(operand)
    }
    
    private fun rewriteSqrt(operand: ScalarExpression): ScalarExpression {
        if (operand is RealNum) {
            val value = operand.value
            if (value == 0.0) return RealNum(0.0)
            if (value == 1.0) return RealNum(1.0)
            // Check if it's a perfect square
            val sqrtValue = kotlin.math.sqrt(value)
            if (abs(sqrtValue - sqrtValue.toInt().toDouble()) < 1e-10) {
                return RealNum(sqrtValue)
            }
        }
        // sqrt(x^2) = |x| (simplified to x for now, could be enhanced)
        if (operand is Exponent && isTwo(operand.right)) {
            return Abs(operand.left)
        }
        return Sqrt(operand)
    }
    
    private fun rewriteSin(operand: ScalarExpression): ScalarExpression {
        if (operand is RealNum) {
            return RealNum(kotlin.math.sin(operand.value))
        }
        return Sin(operand)
    }
    
    private fun rewriteCos(operand: ScalarExpression): ScalarExpression {
        if (operand is RealNum) {
            return RealNum(kotlin.math.cos(operand.value))
        }
        return Cos(operand)
    }
    
    private fun rewriteTan(operand: ScalarExpression): ScalarExpression {
        if (operand is RealNum) {
            return RealNum(kotlin.math.tan(operand.value))
        }
        return Tan(operand)
    }
    
    private fun rewriteLn(operand: ScalarExpression): ScalarExpression {
        if (operand is RealNum) {
            val value = operand.value
            if (value == 1.0) return RealNum(0.0)
            if (value <= 0.0) return operand // ln(0) or negative is undefined
            return RealNum(kotlin.math.ln(value))
        }
        // ln(e^x) = x
        if (operand is Exp) {
            return rewrite(operand.operand)
        }
        return Ln(operand)
    }
    
    private fun rewriteExp(operand: ScalarExpression): ScalarExpression {
        if (operand is RealNum) {
            return RealNum(kotlin.math.exp(operand.value))
        }
        return Exp(operand)
    }
    
    private fun rewriteAbs(operand: ScalarExpression): ScalarExpression {
        if (operand is RealNum) {
            return RealNum(kotlin.math.abs(operand.value))
        }
        // |x| where x is always positive can be simplified
        // This is a placeholder for more advanced analysis
        return Abs(operand)
    }
    
    private fun rewriteArcSin(operand: ScalarExpression): ScalarExpression {
        if (operand is RealNum) {
            return RealNum(kotlin.math.asin(operand.value))
        }
        return ArcSin(operand)
    }
    
    private fun rewriteArcCos(operand: ScalarExpression): ScalarExpression {
        if (operand is RealNum) {
            return RealNum(kotlin.math.acos(operand.value))
        }
        return ArcCos(operand)
    }
    
    private fun rewriteArcTan(operand: ScalarExpression): ScalarExpression {
        if (operand is RealNum) {
            return RealNum(kotlin.math.atan(operand.value))
        }
        return ArcTan(operand)
    }
    
    private fun rewriteFactorial(operand: ScalarExpression): ScalarExpression {
        if (operand is RealNum) {
            val value = operand.value.toInt()
            if (value == 0 || value == 1) return RealNum(1.0)
            if (value < 0) return operand // Undefined
            // Calculate factorial
            var result = 1.0
            for (i in 2..value) {
                result *= i
            }
            return RealNum(result)
        }
        return Factorial(operand)
    }
    
    // ========== Binary Operation Rules ==========
    
    private fun rewriteAdd(left: ScalarExpression, right: ScalarExpression): ScalarExpression {
        // x + 0 = x
        if (isZero(right)) {
            return left
        }
        // 0 + x = x
        if (isZero(left)) {
            return right
        }
        // Both are constants
        if (left is RealNum && right is RealNum) {
            return RealNum(left.value + right.value)
        }
        // Combine like terms: x + x = 2*x
        if (left == right) {
            return Multiply(RealNum(2.0), left)
        }
        // Combine numeric coefficients: (a*x) + (b*x) = (a+b)*x
        val combined = combineLikeTerms(left, right) { l, r -> RealNum(l + r) }
        if (combined != null) return combined
        
        // Handle subtraction as addition of negation: a + (-b) = a - b
        if (right is Negate) {
            return rewriteSubtract(left, right.operand)
        }
        if (left is Negate) {
            return rewriteSubtract(right, left.operand)
        }
        
        // Flatten nested additions: (a + b) + c = a + b + c
        // Note: operands are already simplified by rewrite() calls at the start of rewriteBinary
        if (left is Add) {
            // Try to combine constants: if left.right and right are both constants, combine them
            if (left.right is RealNum && right is RealNum) {
                val combined = RealNum(left.right.value + right.value)
                return Add(left.left, combined)
            }
            // Also check if left.left is a constant and right is a constant
            if (left.left is RealNum && right is RealNum) {
                val combined = RealNum(left.left.value + right.value)
                return Add(combined, left.right)
            }
            return Add(left.left, Add(left.right, right))
        }
        if (right is Add) {
            // Try to combine constants: if left and right.left are both constants, combine them
            if (left is RealNum && right.left is RealNum) {
                val combined = RealNum(left.value + right.left.value)
                return Add(combined, right.right)
            }
            // Also check if left is a constant and right.right is a constant
            if (left is RealNum && right.right is RealNum) {
                val combined = RealNum(left.value + right.right.value)
                return Add(combined, right.left)
            }
            return Add(Add(left, right.left), right.right)
        }
        
        return Add(left, right)
    }
    
    private fun rewriteSubtract(left: ScalarExpression, right: ScalarExpression): ScalarExpression {
        // x - 0 = x
        if (isZero(right)) {
            return left
        }
        // 0 - x = -x
        if (isZero(left)) {
            return Negate(right)
        }
        // x - x = 0
        if (left == right) {
            return RealNum(0.0)
        }
        // Both are constants
        if (left is RealNum && right is RealNum) {
            return RealNum(left.value - right.value)
        }
        // x - (-y) = x + y
        if (right is Negate) {
            return rewriteAdd(left, right.operand)
        }
        // Combine like terms: (a*x) - (b*x) = (a-b)*x
        val combined = combineLikeTerms(left, right) { l, r -> RealNum(l - r) }
        if (combined != null) return combined
        // x - y = x + (-y)
        // This can help with combining like terms
        return Subtract(left, right)
    }
    
    private fun rewriteMultiply(left: ScalarExpression, right: ScalarExpression): ScalarExpression {
        // x * 0 = 0
        if (isZero(left) || isZero(right)) {
            return RealNum(0.0)
        }
        // x * 1 = x
        if (isOne(right)) {
            return left
        }
        // 1 * x = x
        if (isOne(left)) {
            return right
        }
        // Both are constants
        if (left is RealNum && right is RealNum) {
            return RealNum(left.value * right.value)
        }
        // Combine like terms: x * x = x^2
        if (left == right) {
            return Exponent(left, RealNum(2.0))
        }
        // Combine numeric coefficients: (a*x) * (b*x) = (a*b)*x^2
        // But this is more complex, so we'll handle it in a simpler way
        
        // Flatten nested multiplications: (a * b) * c = a * b * c
        // Note: operands are already simplified by rewrite() calls at the start of rewriteBinary
        if (left is Multiply) {
            // If right is a constant and left.right is a constant, evaluate them first
            if (right is RealNum && left.right is RealNum) {
                val newConstant = RealNum(left.right.value * right.value)
                return Multiply(left.left, newConstant)
            }
            // If right is a constant and left.left is a constant, evaluate them first
            if (right is RealNum && left.left is RealNum) {
                val newConstant = RealNum(left.left.value * right.value)
                return Multiply(left.right, newConstant)
            }
            return Multiply(left.left, Multiply(left.right, right))
        }
        if (right is Multiply) {
            // If left is a constant and right.left is a constant, evaluate them first
            if (left is RealNum && right.left is RealNum) {
                val newConstant = RealNum(left.value * right.left.value)
                return Multiply(newConstant, right.right)
            }
            // If left is a constant and right.right is a constant, evaluate them first
            if (left is RealNum && right.right is RealNum) {
                val newConstant = RealNum(left.value * right.right.value)
                return Multiply(newConstant, right.left)
            }
            return Multiply(Multiply(left, right.left), right.right)
        }
        
        // Distribute multiplication over addition: a * (b + c) = a*b + a*c
        // Note: operands are already simplified, so we can construct the new structure directly
        if (right is Add) {
            return Add(
                Multiply(left, right.left),
                Multiply(left, right.right)
            )
        }
        if (left is Add) {
            return Add(
                Multiply(left.left, right),
                Multiply(left.right, right)
            )
        }
        
        // Normalize: put constants on the left when possible
        if (right is RealNum && left !is RealNum) {
            return Multiply(right, left)
        }
        return Multiply(left, right)
    }
    
    private fun rewriteDivide(left: ScalarExpression, right: ScalarExpression): ScalarExpression {
        // 0 / x = 0 (x != 0)
        if (isZero(left) && !isZero(right)) {
            return RealNum(0.0)
        }
        // x / 1 = x
        if (isOne(right)) {
            return left
        }
        // x / x = 1
        if (left == right && !isZero(right)) {
            return RealNum(1.0)
        }
        // Both are constants
        if (left is RealNum && right is RealNum) {
            if (right.value == 0.0) return left // Division by zero handled elsewhere
            return RealNum(left.value / right.value)
        }
        // (a * x) / (b * x) = a / b (if x != 0)
        // This is complex and would require pattern matching
        
        return Divide(left, right)
    }
    
    private fun rewriteModulo(left: ScalarExpression, right: ScalarExpression): ScalarExpression {
        if (left is RealNum && right is RealNum) {
            if (right.value == 0.0) return left // Modulo by zero handled elsewhere
            return RealNum(left.value % right.value)
        }
        return Modulo(left, right)
    }
    
    private fun rewriteExponent(left: ScalarExpression, right: ScalarExpression): ScalarExpression {
        // x^0 = 1
        if (isZero(right)) {
            return RealNum(1.0)
        }
        // x^1 = x
        if (isOne(right)) {
            return left
        }
        // 0^x = 0 (x > 0)
        if (isZero(left) && !isZero(right)) {
            return RealNum(0.0)
        }
        // 1^x = 1
        if (isOne(left)) {
            return RealNum(1.0)
        }
        // Both are constants
        if (left is RealNum && right is RealNum) {
            return RealNum(left.pow( right.value).evaluate())
        }
        // (x^a)^b = x^(a*b)
        if (left is Exponent) {
            return rewrite(Exponent(left.left, rewriteMultiply(left.right, right)))
        }
        // (a*b)^c = a^c * b^c (for some cases)
        // This is complex and would require careful handling
        
        return Exponent(left, right)
    }
    
    private fun rewriteLog(base: ScalarExpression, argument: ScalarExpression): ScalarExpression {
        if (base is RealNum && argument is RealNum) {
            val baseValue = base.value
            val argValue = argument.value
            if (baseValue > 0 && baseValue != 1.0 && argValue > 0) {
                return RealNum(kotlin.math.log(argValue, baseValue))
            }
        }
        return Log(base, argument)
    }
    
    // ========== Helper Functions ==========
    
    /**
     * Checks if an expression represents zero.
     */
    private fun isZero(expression: ScalarExpression): Boolean {
        return when (expression) {
            is RealNum -> expression.value == 0.0
            is Scalar.IntegerNum -> expression.value == 0L
            else -> false
        }
    }
    
    /**
     * Checks if an expression represents one.
     */
    private fun isOne(expression: ScalarExpression): Boolean {
        return when (expression) {
            is RealNum -> expression.value == 1.0
            is Scalar.IntegerNum -> expression.value == 1L
            else -> false
        }
    }
    
    /**
     * Checks if an expression represents two.
     */
    private fun isTwo(expression: ScalarExpression): Boolean {
        return when (expression) {
            is RealNum -> expression.value == 2.0
            is Scalar.IntegerNum -> expression.value == 2L
            else -> false
        }
    }
    
    /**
     * Attempts to combine like terms in an addition or subtraction.
     * For example: 2*x + 3*x = 5*x
     */
    private fun combineLikeTerms(
        left: ScalarExpression,
        right: ScalarExpression,
        operation: (Double, Double) -> ScalarExpression = { l, r -> RealNum(l + r) }
    ): ScalarExpression? {
        // Extract coefficient and variable from expressions like c*x
        fun extractCoefficientAndVariable(expr: ScalarExpression): Pair<ScalarExpression?, ScalarExpression?>? {
            return when (expr) {
                is Multiply -> {
                   val result =  when {
                        expr.left is RealNum -> expr.left to expr.right
                        expr.right is RealNum -> expr.right to expr.left
                        else -> null
                    }
                    result?.let { (coeff, varExpr) ->
                        coeff to varExpr
                    }
                }
                is Variable -> RealNum(1.0) to expr
                else -> null
            }
        }
        
        val leftParts = extractCoefficientAndVariable(left)
        val rightParts = extractCoefficientAndVariable(right)
        
        if (leftParts != null && rightParts != null) {
            val (leftCoeff, leftVar) = leftParts
            val (rightCoeff, rightVar) = rightParts
            
            // Check if variables match
            if (leftVar != null && rightVar != null && leftVar == rightVar) {
                val leftCoeffValue = when (leftCoeff) {
                    is RealNum -> leftCoeff.value
                    is Scalar.IntegerNum -> leftCoeff.value.toDouble()
                    else -> 1.0
                }
                val rightCoeffValue = when (rightCoeff) {
                    is RealNum -> rightCoeff.value
                    is Scalar.IntegerNum -> rightCoeff.value.toDouble()
                    else -> 1.0
                }
                val newCoeff = operation(leftCoeffValue, rightCoeffValue)
                return Multiply(newCoeff, leftVar)
            }
        }
        
        return null
    }
}
