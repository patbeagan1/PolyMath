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
<<<<<<< HEAD
 * Represents a simplification rule that was applied during expression rewriting.
 */
data class SimplificationRule(
    val name: String,
    val description: String,
    val before: ScalarExpression,
    val after: ScalarExpression
) {
    override fun toString(): String = "$name: $description"
}

/**
=======
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
 * Tree rewriting system for simplifying algebraic expressions.
 * 
 * This module provides a rule-based tree rewriting system that can apply
 * various algebraic simplification rules to expression trees.
 */
@ExperimentalMathDSL
object TreeRewriter {
    
    /**
<<<<<<< HEAD
     * Callback function type for rule application logging.
     */
    typealias RuleLogger = (SimplificationRule) -> Unit
    
    /**
     * Default rule logger that prints to standard output.
     */
    private val defaultLogger: RuleLogger = { rule ->
        println("  Applying rule: ${rule.name} - ${rule.description}")
        println("    Before: ${rule.before.toLatex()}")
        println("    After:  ${rule.after.toLatex()}")
    }
    
    /**
     * Current rule logger. Set to null to disable logging.
     */
    var ruleLogger: RuleLogger? = null
        private set
    
    /**
     * Checks if rule logging is currently enabled.
     */
    val isLoggingEnabled: Boolean
        get() = ruleLogger != null
    
    /**
     * Enables rule logging with the default logger (prints to stdout).
     */
    fun enableLogging() {
        ruleLogger = defaultLogger
    }
    
    /**
     * Enables rule logging with a custom logger.
     */
    fun enableLogging(logger: RuleLogger) {
        ruleLogger = logger
    }
    
    /**
     * Disables rule logging.
     */
    fun disableLogging() {
        ruleLogger = null
    }
    
    /**
     * Logs a rule application if logging is enabled.
     */
    private fun logRule(name: String, description: String, before: ScalarExpression, after: ScalarExpression) {
        ruleLogger?.invoke(SimplificationRule(name, description, before, after))
    }
    
    /**
=======
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
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
<<<<<<< HEAD
        val original = Negate(operand)
        // -(-x) = x
        if (operand is Negate) {
            val result = operand.operand
            logRule("Double Negation", "-(-x) = x", original, result)
            return result
        }
        // -0 = 0
        if (isZero(operand)) {
            val result = RealNum(0.0)
            logRule("Negate Zero", "-0 = 0", original, result)
            return result
        }
        // If operand is a constant, evaluate it
        if (operand is RealNum) {
            val result = RealNum(-operand.value)
            logRule("Evaluate Negation", "Evaluate -(${operand.value})", original, result)
            return result
        }
        return original
    }
    
    private fun rewriteSqrt(operand: ScalarExpression): ScalarExpression {
        val original = Sqrt(operand)
        if (operand is RealNum) {
            val value = operand.value
            if (value == 0.0) {
                val result = RealNum(0.0)
                logRule("Sqrt Zero", "√0 = 0", original, result)
                return result
            }
            if (value == 1.0) {
                val result = RealNum(1.0)
                logRule("Sqrt One", "√1 = 1", original, result)
                return result
            }
            // Check if it's a perfect square
            val sqrtValue = kotlin.math.sqrt(value)
            if (abs(sqrtValue - sqrtValue.toInt().toDouble()) < 1e-10) {
                val result = RealNum(sqrtValue)
                logRule("Perfect Square", "√${value.toInt()} = ${sqrtValue.toInt()}", original, result)
                return result
=======
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
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
            }
        }
        // sqrt(x^2) = |x| (simplified to x for now, could be enhanced)
        if (operand is Exponent && isTwo(operand.right)) {
<<<<<<< HEAD
            val result = Abs(operand.left)
            logRule("Sqrt of Square", "√(x²) = |x|", original, result)
            return result
        }
        return original
    }
    
    private fun rewriteSin(operand: ScalarExpression): ScalarExpression {
        val original = Sin(operand)
        if (operand is RealNum) {
            val result = RealNum(kotlin.math.sin(operand.value))
            logRule("Evaluate Sin", "sin(${operand.value})", original, result)
            return result
        }
        // sin(0) = 0
        if (isZero(operand)) {
            val result = RealNum(0.0)
            logRule("Sin Zero", "sin(0) = 0", original, result)
            return result
        }
        // sin(-x) = -sin(x)
        if (operand is Negate) {
            val result = Negate(Sin(operand.operand))
            logRule("Sin Negative", "sin(-x) = -sin(x)", original, result)
            return result
        }
        // sin(arcsin(x)) = x
        if (operand is ArcSin) {
            val result = operand.operand
            logRule("Sin ArcSin", "sin(arcsin(x)) = x", original, result)
            return result
        }
        return original
    }
    
    private fun rewriteCos(operand: ScalarExpression): ScalarExpression {
        val original = Cos(operand)
        if (operand is RealNum) {
            val result = RealNum(kotlin.math.cos(operand.value))
            logRule("Evaluate Cos", "cos(${operand.value})", original, result)
            return result
        }
        // cos(0) = 1
        if (isZero(operand)) {
            val result = RealNum(1.0)
            logRule("Cos Zero", "cos(0) = 1", original, result)
            return result
        }
        // cos(-x) = cos(x)
        if (operand is Negate) {
            val result = Cos(operand.operand)
            logRule("Cos Negative", "cos(-x) = cos(x)", original, result)
            return result
        }
        // cos(arccos(x)) = x
        if (operand is ArcCos) {
            val result = operand.operand
            logRule("Cos ArcCos", "cos(arccos(x)) = x", original, result)
            return result
        }
        return original
    }
    
    private fun rewriteTan(operand: ScalarExpression): ScalarExpression {
        val original = Tan(operand)
        if (operand is RealNum) {
            val result = RealNum(kotlin.math.tan(operand.value))
            logRule("Evaluate Tan", "tan(${operand.value})", original, result)
            return result
        }
        // tan(0) = 0
        if (isZero(operand)) {
            val result = RealNum(0.0)
            logRule("Tan Zero", "tan(0) = 0", original, result)
            return result
        }
        // tan(-x) = -tan(x)
        if (operand is Negate) {
            val result = Negate(Tan(operand.operand))
            logRule("Tan Negative", "tan(-x) = -tan(x)", original, result)
            return result
        }
        // tan(arctan(x)) = x
        if (operand is ArcTan) {
            val result = operand.operand
            logRule("Tan ArcTan", "tan(arctan(x)) = x", original, result)
            return result
        }
        return original
    }
    
    private fun rewriteLn(operand: ScalarExpression): ScalarExpression {
        val original = Ln(operand)
        if (operand is RealNum) {
            val value = operand.value
            if (value == 1.0) {
                val result = RealNum(0.0)
                logRule("Ln One", "ln(1) = 0", original, result)
                return result
            }
            if (value <= 0.0) return original // ln(0) or negative is undefined
            val result = RealNum(kotlin.math.ln(value))
            logRule("Evaluate Ln", "ln($value)", original, result)
            return result
        }
        // ln(e^x) = x
        if (operand is Exp) {
            val result = rewrite(operand.operand)
            logRule("Ln of Exp", "ln(e^x) = x", original, result)
            return result
        }
        return original
    }
    
    private fun rewriteExp(operand: ScalarExpression): ScalarExpression {
        val original = Exp(operand)
        if (operand is RealNum) {
            val result = RealNum(kotlin.math.exp(operand.value))
            logRule("Evaluate Exp", "e^${operand.value}", original, result)
            return result
        }
        // e^0 = 1
        if (isZero(operand)) {
            val result = RealNum(1.0)
            logRule("Exp Zero", "e^0 = 1", original, result)
            return result
        }
        // e^ln(x) = x
        if (operand is Ln) {
            val result = operand.operand
            logRule("Exp of Ln", "e^(ln(x)) = x", original, result)
            return result
        }
        return original
    }
    
    private fun rewriteAbs(operand: ScalarExpression): ScalarExpression {
        val original = Abs(operand)
        if (operand is RealNum) {
            val result = RealNum(kotlin.math.abs(operand.value))
            logRule("Evaluate Abs", "|${operand.value}|", original, result)
            return result
        }
        // |x| where x is always positive can be simplified
        // This is a placeholder for more advanced analysis
        return original
    }
    
    private fun rewriteArcSin(operand: ScalarExpression): ScalarExpression {
        val original = ArcSin(operand)
        if (operand is RealNum) {
            val result = RealNum(kotlin.math.asin(operand.value))
            logRule("Evaluate ArcSin", "arcsin(${operand.value})", original, result)
            return result
        }
        return original
    }
    
    private fun rewriteArcCos(operand: ScalarExpression): ScalarExpression {
        val original = ArcCos(operand)
        if (operand is RealNum) {
            val result = RealNum(kotlin.math.acos(operand.value))
            logRule("Evaluate ArcCos", "arccos(${operand.value})", original, result)
            return result
        }
        return original
    }
    
    private fun rewriteArcTan(operand: ScalarExpression): ScalarExpression {
        val original = ArcTan(operand)
        if (operand is RealNum) {
            val result = RealNum(kotlin.math.atan(operand.value))
            logRule("Evaluate ArcTan", "arctan(${operand.value})", original, result)
            return result
        }
        // arctan(0) = 0
        if (isZero(operand)) {
            val result = RealNum(0.0)
            logRule("ArcTan Zero", "arctan(0) = 0", original, result)
            return result
        }
        return original
    }
    
    private fun rewriteFactorial(operand: ScalarExpression): ScalarExpression {
        val original = Factorial(operand)
        if (operand is RealNum) {
            val value = operand.value.toInt()
            if (value == 0 || value == 1) {
                val result = RealNum(1.0)
                logRule("Factorial Zero/One", "$value! = 1", original, result)
                return result
            }
            if (value < 0) return original // Undefined
=======
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
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
            // Calculate factorial
            var result = 1.0
            for (i in 2..value) {
                result *= i
            }
<<<<<<< HEAD
            val resultExpr = RealNum(result)
            logRule("Evaluate Factorial", "$value! = $result", original, resultExpr)
            return resultExpr
        }
        return original
=======
            return RealNum(result)
        }
        return Factorial(operand)
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
    }
    
    // ========== Binary Operation Rules ==========
    
    private fun rewriteAdd(left: ScalarExpression, right: ScalarExpression): ScalarExpression {
<<<<<<< HEAD
        val original = Add(left, right)
        // x + 0 = x
        if (isZero(right)) {
            logRule("Additive Identity Right", "x + 0 = x", original, left)
=======
        // x + 0 = x
        if (isZero(right)) {
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
            return left
        }
        // 0 + x = x
        if (isZero(left)) {
<<<<<<< HEAD
            logRule("Additive Identity Left", "0 + x = x", original, right)
=======
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
            return right
        }
        // Both are constants
        if (left is RealNum && right is RealNum) {
<<<<<<< HEAD
            val result = RealNum(left.value + right.value)
            logRule("Evaluate Addition", "${left.value} + ${right.value} = ${result.value}", original, result)
            return result
        }
        // Combine like terms: x + x = 2*x
        if (left == right) {
            val result = Multiply(RealNum(2.0), left)
            logRule("Combine Like Terms", "x + x = 2*x", original, result)
            return result
        }
        // Combine numeric coefficients: (a*x) + (b*x) = (a+b)*x
        val combined = combineLikeTerms(left, right) { l, r -> RealNum(l + r) }
        if (combined != null) {
            logRule("Combine Like Terms with Coefficients", "(a*x) + (b*x) = (a+b)*x", original, combined)
            return combined
        }
        
        // Handle subtraction as addition of negation: a + (-b) = a - b
        if (right is Negate) {
            val result = rewriteSubtract(left, right.operand)
            logRule("Add Negative", "a + (-b) = a - b", original, result)
            return result
        }
        if (left is Negate) {
            val result = rewriteSubtract(right, left.operand)
            logRule("Add Negative", "(-a) + b = b - a", original, result)
            return result
=======
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
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
        }
        
        // Flatten nested additions: (a + b) + c = a + b + c
        // Note: operands are already simplified by rewrite() calls at the start of rewriteBinary
        if (left is Add) {
            // Try to combine constants: if left.right and right are both constants, combine them
            if (left.right is RealNum && right is RealNum) {
                val combined = RealNum(left.right.value + right.value)
<<<<<<< HEAD
                val result = Add(left.left, combined)
                logRule("Flatten and Combine Constants", "(a + b) + c where b and c are constants", original, result)
                return result
=======
                return Add(left.left, combined)
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
            }
            // Also check if left.left is a constant and right is a constant
            if (left.left is RealNum && right is RealNum) {
                val combined = RealNum(left.left.value + right.value)
<<<<<<< HEAD
                val result = Add(combined, left.right)
                logRule("Flatten and Combine Constants", "(a + b) + c where a and c are constants", original, result)
                return result
            }
            val result = Add(left.left, Add(left.right, right))
            logRule("Flatten Addition", "(a + b) + c = a + (b + c)", original, result)
            return result
=======
                return Add(combined, left.right)
            }
            return Add(left.left, Add(left.right, right))
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
        }
        if (right is Add) {
            // Try to combine constants: if left and right.left are both constants, combine them
            if (left is RealNum && right.left is RealNum) {
                val combined = RealNum(left.value + right.left.value)
<<<<<<< HEAD
                val result = Add(combined, right.right)
                logRule("Flatten and Combine Constants", "a + (b + c) where a and b are constants", original, result)
                return result
=======
                return Add(combined, right.right)
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
            }
            // Also check if left is a constant and right.right is a constant
            if (left is RealNum && right.right is RealNum) {
                val combined = RealNum(left.value + right.right.value)
<<<<<<< HEAD
                val result = Add(combined, right.left)
                logRule("Flatten and Combine Constants", "a + (b + c) where a and c are constants", original, result)
                return result
            }
            val result = Add(Add(left, right.left), right.right)
            logRule("Flatten Addition", "a + (b + c) = (a + b) + c", original, result)
            return result
        }
        
        return original
    }
    
    private fun rewriteSubtract(left: ScalarExpression, right: ScalarExpression): ScalarExpression {
        val original = Subtract(left, right)
        // x - 0 = x
        if (isZero(right)) {
            logRule("Subtract Zero", "x - 0 = x", original, left)
=======
                return Add(combined, right.left)
            }
            return Add(Add(left, right.left), right.right)
        }
        
        return Add(left, right)
    }
    
    private fun rewriteSubtract(left: ScalarExpression, right: ScalarExpression): ScalarExpression {
        // x - 0 = x
        if (isZero(right)) {
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
            return left
        }
        // 0 - x = -x
        if (isZero(left)) {
<<<<<<< HEAD
            val result = Negate(right)
            logRule("Zero Subtract", "0 - x = -x", original, result)
            return result
        }
        // x - x = 0
        if (left == right) {
            val result = RealNum(0.0)
            logRule("Subtract Same", "x - x = 0", original, result)
            return result
        }
        // Both are constants
        if (left is RealNum && right is RealNum) {
            val result = RealNum(left.value - right.value)
            logRule("Evaluate Subtraction", "${left.value} - ${right.value} = ${result.value}", original, result)
            return result
        }
        // x - (-y) = x + y
        if (right is Negate) {
            val result = rewriteAdd(left, right.operand)
            logRule("Subtract Negative", "x - (-y) = x + y", original, result)
            return result
        }
        // Combine like terms: (a*x) - (b*x) = (a-b)*x
        val combined = combineLikeTerms(left, right) { l, r -> RealNum(l - r) }
        if (combined != null) {
            logRule("Combine Like Terms Subtraction", "(a*x) - (b*x) = (a-b)*x", original, combined)
            return combined
        }
        // x - y = x + (-y)
        // This can help with combining like terms
        return original
    }
    
    private fun rewriteMultiply(left: ScalarExpression, right: ScalarExpression): ScalarExpression {
        val original = Multiply(left, right)
        // x * 0 = 0
        if (isZero(left) || isZero(right)) {
            val result = RealNum(0.0)
            logRule("Multiply Zero", "x * 0 = 0", original, result)
            return result
        }
        // x * 1 = x
        if (isOne(right)) {
            logRule("Multiplicative Identity Right", "x * 1 = x", original, left)
=======
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
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
            return left
        }
        // 1 * x = x
        if (isOne(left)) {
<<<<<<< HEAD
            logRule("Multiplicative Identity Left", "1 * x = x", original, right)
=======
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
            return right
        }
        // Both are constants
        if (left is RealNum && right is RealNum) {
<<<<<<< HEAD
            val result = RealNum(left.value * right.value)
            logRule("Evaluate Multiplication", "${left.value} * ${right.value} = ${result.value}", original, result)
            return result
        }
        // Combine like terms: x * x = x^2
        if (left == right) {
            val result = Exponent(left, RealNum(2.0))
            logRule("Square Same Term", "x * x = x²", original, result)
            return result
=======
            return RealNum(left.value * right.value)
        }
        // Combine like terms: x * x = x^2
        if (left == right) {
            return Exponent(left, RealNum(2.0))
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
        }
        // Combine numeric coefficients: (a*x) * (b*x) = (a*b)*x^2
        // But this is more complex, so we'll handle it in a simpler way
        
        // Flatten nested multiplications: (a * b) * c = a * b * c
        // Note: operands are already simplified by rewrite() calls at the start of rewriteBinary
        if (left is Multiply) {
            // If right is a constant and left.right is a constant, evaluate them first
            if (right is RealNum && left.right is RealNum) {
                val newConstant = RealNum(left.right.value * right.value)
<<<<<<< HEAD
                val result = Multiply(left.left, newConstant)
                logRule("Flatten and Combine Constants", "(a * b) * c where b and c are constants", original, result)
                return result
=======
                return Multiply(left.left, newConstant)
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
            }
            // If right is a constant and left.left is a constant, evaluate them first
            if (right is RealNum && left.left is RealNum) {
                val newConstant = RealNum(left.left.value * right.value)
<<<<<<< HEAD
                val result = Multiply(left.right, newConstant)
                logRule("Flatten and Combine Constants", "(a * b) * c where a and c are constants", original, result)
                return result
            }
            val result = Multiply(left.left, Multiply(left.right, right))
            logRule("Flatten Multiplication", "(a * b) * c = a * (b * c)", original, result)
            return result
=======
                return Multiply(left.right, newConstant)
            }
            return Multiply(left.left, Multiply(left.right, right))
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
        }
        if (right is Multiply) {
            // If left is a constant and right.left is a constant, evaluate them first
            if (left is RealNum && right.left is RealNum) {
                val newConstant = RealNum(left.value * right.left.value)
<<<<<<< HEAD
                val result = Multiply(newConstant, right.right)
                logRule("Flatten and Combine Constants", "a * (b * c) where a and b are constants", original, result)
                return result
=======
                return Multiply(newConstant, right.right)
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
            }
            // If left is a constant and right.right is a constant, evaluate them first
            if (left is RealNum && right.right is RealNum) {
                val newConstant = RealNum(left.value * right.right.value)
<<<<<<< HEAD
                val result = Multiply(newConstant, right.left)
                logRule("Flatten and Combine Constants", "a * (b * c) where a and c are constants", original, result)
                return result
            }
            val result = Multiply(Multiply(left, right.left), right.right)
            logRule("Flatten Multiplication", "a * (b * c) = (a * b) * c", original, result)
            return result
=======
                return Multiply(newConstant, right.left)
            }
            return Multiply(Multiply(left, right.left), right.right)
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
        }
        
        // Distribute multiplication over addition: a * (b + c) = a*b + a*c
        // Note: operands are already simplified, so we can construct the new structure directly
        if (right is Add) {
<<<<<<< HEAD
            val result = Add(
                Multiply(left, right.left),
                Multiply(left, right.right)
            )
            logRule("Distribute Multiplication", "a * (b + c) = a*b + a*c", original, result)
            return result
        }
        if (left is Add) {
            val result = Add(
                Multiply(left.left, right),
                Multiply(left.right, right)
            )
            logRule("Distribute Multiplication", "(a + b) * c = a*c + b*c", original, result)
            return result
        }
        // Distribute multiplication over subtraction: a * (b - c) = a*b - a*c
        if (right is Subtract) {
            val result = Subtract(
                Multiply(left, right.left),
                Multiply(left, right.right)
            )
            logRule("Distribute Multiplication Over Subtraction", "a * (b - c) = a*b - a*c", original, result)
            return result
        }
        if (left is Subtract) {
            val result = Subtract(
                Multiply(left.left, right),
                Multiply(left.right, right)
            )
            logRule("Distribute Multiplication Over Subtraction", "(a - b) * c = a*c - b*c", original, result)
            return result
=======
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
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
        }
        
        // Normalize: put constants on the left when possible
        if (right is RealNum && left !is RealNum) {
<<<<<<< HEAD
            val result = Multiply(right, left)
            logRule("Normalize Constants", "Put constants on the left", original, result)
            return result
        }
        return original
    }
    
    private fun rewriteDivide(left: ScalarExpression, right: ScalarExpression): ScalarExpression {
        val original = Divide(left, right)
        // 0 / x = 0 (x != 0)
        if (isZero(left) && !isZero(right)) {
            val result = RealNum(0.0)
            logRule("Zero Divide", "0 / x = 0 (x != 0)", original, result)
            return result
        }
        // x / 1 = x
        if (isOne(right)) {
            logRule("Divide One", "x / 1 = x", original, left)
=======
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
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
            return left
        }
        // x / x = 1
        if (left == right && !isZero(right)) {
<<<<<<< HEAD
            val result = RealNum(1.0)
            logRule("Divide Same", "x / x = 1", original, result)
            return result
        }
        // Both are constants
        if (left is RealNum && right is RealNum) {
            if (right.value == 0.0) return original // Division by zero handled elsewhere
            val result = RealNum(left.value / right.value)
            logRule("Evaluate Division", "${left.value} / ${right.value} = ${result.value}", original, result)
            return result
=======
            return RealNum(1.0)
        }
        // Both are constants
        if (left is RealNum && right is RealNum) {
            if (right.value == 0.0) return left // Division by zero handled elsewhere
            return RealNum(left.value / right.value)
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
        }
        // (a * x) / (b * x) = a / b (if x != 0)
        // This is complex and would require pattern matching
        
<<<<<<< HEAD
        // x / (-y) = -(x / y)
        if (right is Negate) {
            val result = Negate(Divide(left, right.operand))
            logRule("Divide Negative", "x / (-y) = -(x / y)", original, result)
            return result
        }
        // (-x) / y = -(x / y)
        if (left is Negate) {
            val result = Negate(Divide(left.operand, right))
            logRule("Divide Negative", "(-x) / y = -(x / y)", original, result)
            return result
        }
        
        return original
    }
    
    private fun rewriteModulo(left: ScalarExpression, right: ScalarExpression): ScalarExpression {
        val original = Modulo(left, right)
        if (left is RealNum && right is RealNum) {
            if (right.value == 0.0) return original // Modulo by zero handled elsewhere
            val result = RealNum(left.value % right.value)
            logRule("Evaluate Modulo", "${left.value} mod ${right.value} = ${result.value}", original, result)
            return result
        }
        return original
    }
    
    private fun rewriteExponent(left: ScalarExpression, right: ScalarExpression): ScalarExpression {
        val original = Exponent(left, right)
        // x^0 = 1
        if (isZero(right)) {
            val result = RealNum(1.0)
            logRule("Exponent Zero", "x^0 = 1", original, result)
            return result
        }
        // x^1 = x
        if (isOne(right)) {
            logRule("Exponent One", "x^1 = x", original, left)
=======
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
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
            return left
        }
        // 0^x = 0 (x > 0)
        if (isZero(left) && !isZero(right)) {
<<<<<<< HEAD
            val result = RealNum(0.0)
            logRule("Zero Exponent", "0^x = 0 (x > 0)", original, result)
            return result
        }
        // 1^x = 1
        if (isOne(left)) {
            val result = RealNum(1.0)
            logRule("One Exponent", "1^x = 1", original, result)
            return result
        }
        // Both are constants
        if (left is RealNum && right is RealNum) {
            val result = RealNum(left.pow(right.value).evaluate())
            logRule("Evaluate Exponent", "${left.value}^${right.value} = ${result.value}", original, result)
            return result
        }
        // (x^a)^b = x^(a*b)
        if (left is Exponent) {
            val result = rewrite(Exponent(left.left, rewriteMultiply(left.right, right)))
            logRule("Power of Power", "(x^a)^b = x^(a*b)", original, result)
            return result
        }
        // (a*b)^c = a^c * b^c (when c is a constant)
        if (left is Multiply && right is RealNum) {
            val result = Multiply(
                Exponent(left.left, right),
                Exponent(left.right, right)
            )
            logRule("Distribute Exponent", "(a*b)^c = a^c * b^c", original, result)
            return result
        }
        // (a/b)^c = a^c / b^c (when c is a constant)
        if (left is Divide && right is RealNum) {
            val result = Divide(
                Exponent(left.left, right),
                Exponent(left.right, right)
            )
            logRule("Distribute Exponent Over Division", "(a/b)^c = a^c / b^c", original, result)
            return result
        }
        // x^(-a) = 1 / x^a
        if (right is Negate) {
            val result = Divide(RealNum(1.0), Exponent(left, right.operand))
            logRule("Negative Exponent", "x^(-a) = 1 / x^a", original, result)
            return result
        }
        // e^(ln(x)) = x (already handled in rewriteExp, but also handle e^(a*ln(x)) = x^a)
        // This is complex and would require pattern matching, skip for now
        
        return original
    }
    
    private fun rewriteLog(base: ScalarExpression, argument: ScalarExpression): ScalarExpression {
        val original = Log(base, argument)
=======
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
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
        if (base is RealNum && argument is RealNum) {
            val baseValue = base.value
            val argValue = argument.value
            if (baseValue > 0 && baseValue != 1.0 && argValue > 0) {
<<<<<<< HEAD
                val result = RealNum(kotlin.math.log(argValue, baseValue))
                logRule("Evaluate Log", "log_${baseValue}($argValue) = ${result.value}", original, result)
                return result
            }
        }
        // log_b(b) = 1
        if (base == argument && !isZero(base) && !isOne(base)) {
            val result = RealNum(1.0)
            logRule("Log Base Equals Argument", "log_b(b) = 1", original, result)
            return result
        }
        // log_b(1) = 0
        if (isOne(argument) && !isZero(base) && !isOne(base)) {
            val result = RealNum(0.0)
            logRule("Log of One", "log_b(1) = 0", original, result)
            return result
        }
        return original
=======
                return RealNum(kotlin.math.log(argValue, baseValue))
            }
        }
        return Log(base, argument)
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
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
