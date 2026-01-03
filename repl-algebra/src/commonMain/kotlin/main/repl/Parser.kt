package main.repl

import main.dsl.expressions.ScalarAlgebra
import main.dsl.expressions.ScalarExpression
import main.dsl.mathnum.*
import main.dsl.simplify

/**
 * Parser for algebraic expressions that supports both infix notation (e.g., "1 + 1")
 * and command notation (e.g., "add 1").
 */
class Parser(private val input: String) {
    private var pos = 0
    private val length = input.length
    
    /**
     * Parses the input string into a ScalarExpression.
     * Supports both infix notation and command notation.
     */
    fun parse(): ScalarExpression {
        val trimmed = input.trim()
        if (trimmed.isEmpty()) {
            throw ParseException("Empty input")
        }
        
        // Try command notation first (starts with a verb)
        if (trimmed.matches(Regex("^[a-zA-Z]+.*"))) {
            return parseCommand(trimmed)
        }
        
        // Otherwise parse as infix notation
        pos = 0
        val result = parseExpression()
        if (pos < length) {
            throw ParseException("Unexpected character at position $pos: '${input[pos]}'")
        }
        return result
    }
    
    /**
     * Parses command notation like "add 1", "multiply 2", etc.
     */
    private fun parseCommand(input: String): ScalarExpression {
        val parts = input.trim().split(Regex("\\s+"), limit = 3)
        if (parts.isEmpty()) {
            throw ParseException("Empty command")
        }
        
        val command = parts[0].lowercase()
        val args = parts.drop(1)
        
        return when (command) {
            "add", "+" -> {
                if (args.size < 2) throw ParseException("'add' requires at least 2 arguments")
                args.map { parseSingleExpression(it) }.reduce { acc, expr -> ScalarAlgebra.Add(acc, expr) }
            }
            "subtract", "sub", "-" -> {
                if (args.size < 2) throw ParseException("'subtract' requires at least 2 arguments")
                args.map { parseSingleExpression(it) }.reduce { acc, expr -> ScalarAlgebra.Subtract(acc, expr) }
            }
            "multiply", "mul", "*" -> {
                if (args.size < 2) throw ParseException("'multiply' requires at least 2 arguments")
                args.map { parseSingleExpression(it) }.reduce { acc, expr -> ScalarAlgebra.Multiply(acc, expr) }
            }
            "divide", "div", "/" -> {
                if (args.size < 2) throw ParseException("'divide' requires at least 2 arguments")
                args.map { parseSingleExpression(it) }.reduce { acc, expr -> ScalarAlgebra.Divide(acc, expr) }
            }
            "power", "pow", "^" -> {
                if (args.size < 2) throw ParseException("'power' requires 2 arguments")
                ScalarAlgebra.Exponent(parseSingleExpression(args[0]), parseSingleExpression(args[1]))
            }
            "sqrt" -> {
                if (args.isEmpty()) throw ParseException("'sqrt' requires 1 argument")
                ScalarAlgebra.Sqrt(parseSingleExpression(args[0]))
            }
            "sin" -> {
                if (args.isEmpty()) throw ParseException("'sin' requires 1 argument")
                ScalarAlgebra.Sin(parseSingleExpression(args[0]))
            }
            "cos" -> {
                if (args.isEmpty()) throw ParseException("'cos' requires 1 argument")
                ScalarAlgebra.Cos(parseSingleExpression(args[0]))
            }
            "tan" -> {
                if (args.isEmpty()) throw ParseException("'tan' requires 1 argument")
                ScalarAlgebra.Tan(parseSingleExpression(args[0]))
            }
            "ln" -> {
                if (args.isEmpty()) throw ParseException("'ln' requires 1 argument")
                ScalarAlgebra.Ln(parseSingleExpression(args[0]))
            }
            "exp" -> {
                if (args.isEmpty()) throw ParseException("'exp' requires 1 argument")
                ScalarAlgebra.Exp(parseSingleExpression(args[0]))
            }
            "abs" -> {
                if (args.isEmpty()) throw ParseException("'abs' requires 1 argument")
                ScalarAlgebra.Abs(parseSingleExpression(args[0]))
            }
            "negate", "neg" -> {
                if (args.isEmpty()) throw ParseException("'negate' requires 1 argument")
                ScalarAlgebra.Negate(parseSingleExpression(args[0]))
            }
            "factorial", "fact" -> {
                if (args.isEmpty()) throw ParseException("'factorial' requires 1 argument")
                ScalarAlgebra.Factorial(parseSingleExpression(args[0]))
            }
            else -> {
                // If it's not a command, try parsing as a single expression
                parseSingleExpression(input)
            }
        }
    }
    
    /**
     * Parses a single expression (used by command parser).
     */
    private fun parseSingleExpression(expr: String): ScalarExpression {
        val parser = Parser(expr)
        parser.pos = 0
        return parser.parseExpression()
    }
    
    /**
     * Parses an expression using infix notation.
     * Handles operator precedence: +, - (lowest) < *, / < ^ (highest)
     */
    private fun parseExpression(): ScalarExpression {
        return parseAdditive()
    }
    
    private fun parseAdditive(): ScalarExpression {
        var left = parseMultiplicative()
        
        while (pos < length) {
            skipWhitespace()
            when {
                peek() == '+' -> {
                    pos++
                    skipWhitespace()
                    val right = parseMultiplicative()
                    left = ScalarAlgebra.Add(left, right)
                }
                peek() == '-' && !isPartOfNumber() -> {
                    pos++
                    skipWhitespace()
                    val right = parseMultiplicative()
                    left = ScalarAlgebra.Subtract(left, right)
                }
                else -> break
            }
        }
        
        return left
    }
    
    private fun parseMultiplicative(): ScalarExpression {
        var left = parsePower()
        
        while (pos < length) {
            skipWhitespace()
            when {
                peek() == '*' -> {
                    pos++
                    skipWhitespace()
                    val right = parsePower()
                    left = ScalarAlgebra.Multiply(left, right)
                }
                peek() == '/' -> {
                    pos++
                    skipWhitespace()
                    val right = parsePower()
                    left = ScalarAlgebra.Divide(left, right)
                }
                peek() == '%' -> {
                    pos++
                    skipWhitespace()
                    val right = parsePower()
                    left = ScalarAlgebra.Modulo(left, right)
                }
                else -> break
            }
        }
        
        return left
    }
    
    private fun parsePower(): ScalarExpression {
        var left = parseUnary()
        
        while (pos < length) {
            skipWhitespace()
            if (peek() == '^') {
                pos++
                skipWhitespace()
                val right = parseUnary()
                left = ScalarAlgebra.Exponent(left, right)
            } else {
                break
            }
        }
        
        return left
    }
    
    private fun parseUnary(): ScalarExpression {
        skipWhitespace()
        
        if (pos >= length) {
            throw ParseException("Unexpected end of input")
        }
        
        when (peek()) {
            '-' -> {
                pos++
                skipWhitespace()
                return ScalarAlgebra.Negate(parseUnary())
            }
            '+' -> {
                pos++
                skipWhitespace()
                return parseUnary()
            }
        }
        
        return parsePrimary()
    }
    
    private fun parsePrimary(): ScalarExpression {
        skipWhitespace()
        
        if (pos >= length) {
            throw ParseException("Unexpected end of input")
        }
        
        when (peek()) {
            '(' -> {
                pos++
                val expr = parseExpression()
                skipWhitespace()
                if (pos >= length || peek() != ')') {
                    throw ParseException("Expected ')'")
                }
                pos++
                return expr
            }
            in '0'..'9', '.' -> {
                return parseNumber()
            }
            in 'a'..'z', in 'A'..'Z', '_' -> {
                return parseIdentifier()
            }
            else -> {
                throw ParseException("Unexpected character: '${peek()}'")
            }
        }
    }
    
    private fun parseNumber(): ScalarExpression {
        val start = pos
        var hasDot = false
        
        if (peek() == '.') {
            hasDot = true
            pos++
        }
        
        while (pos < length && (peek().isDigit() || peek() == '.')) {
            if (peek() == '.') {
                if (hasDot) break
                hasDot = true
            }
            pos++
        }
        
        val numberStr = input.substring(start, pos)
        val number = numberStr.toDoubleOrNull()
            ?: throw ParseException("Invalid number: $numberStr")
        
        return if (hasDot) {
            Scalar.RealNum(number)
        } else {
            Scalar.IntegerNum(number.toLong())
        }
    }
    
    private fun parseIdentifier(): ScalarExpression {
        val start = pos
        
        while (pos < length && (peek().isLetterOrDigit() || peek() == '_')) {
            pos++
        }
        
        val identifier = input.substring(start, pos)
        
        // Check if it's a function call
        skipWhitespace()
        if (pos < length && peek() == '(') {
            return parseFunctionCall(identifier)
        }
        
        // Otherwise it's a variable
        return Variable(identifier)
    }
    
    private fun parseFunctionCall(name: String): ScalarExpression {
        pos++ // consume '('
        skipWhitespace()
        
        val args = mutableListOf<ScalarExpression>()
        
        if (pos < length && peek() != ')') {
            args.add(parseExpression())
            while (pos < length && peek() == ',') {
                pos++
                skipWhitespace()
                args.add(parseExpression())
            }
        }
        
        skipWhitespace()
        if (pos >= length || peek() != ')') {
            throw ParseException("Expected ')'")
        }
        pos++
        
        return when (name.lowercase()) {
            "sqrt" -> {
                if (args.size != 1) throw ParseException("sqrt requires 1 argument")
                ScalarAlgebra.Sqrt(args[0])
            }
            "sin" -> {
                if (args.size != 1) throw ParseException("sin requires 1 argument")
                ScalarAlgebra.Sin(args[0])
            }
            "cos" -> {
                if (args.size != 1) throw ParseException("cos requires 1 argument")
                ScalarAlgebra.Cos(args[0])
            }
            "tan" -> {
                if (args.size != 1) throw ParseException("tan requires 1 argument")
                ScalarAlgebra.Tan(args[0])
            }
            "ln" -> {
                if (args.size != 1) throw ParseException("ln requires 1 argument")
                ScalarAlgebra.Ln(args[0])
            }
            "exp" -> {
                if (args.size != 1) throw ParseException("exp requires 1 argument")
                ScalarAlgebra.Exp(args[0])
            }
            "abs" -> {
                if (args.size != 1) throw ParseException("abs requires 1 argument")
                ScalarAlgebra.Abs(args[0])
            }
            "arcsin", "asin" -> {
                if (args.size != 1) throw ParseException("arcsin requires 1 argument")
                ScalarAlgebra.ArcSin(args[0])
            }
            "arccos", "acos" -> {
                if (args.size != 1) throw ParseException("arccos requires 1 argument")
                ScalarAlgebra.ArcCos(args[0])
            }
            "arctan", "atan" -> {
                if (args.size != 1) throw ParseException("arctan requires 1 argument")
                ScalarAlgebra.ArcTan(args[0])
            }
            "factorial", "fact" -> {
                if (args.size != 1) throw ParseException("factorial requires 1 argument")
                ScalarAlgebra.Factorial(args[0])
            }
            "log" -> {
                if (args.size != 2) throw ParseException("log requires 2 arguments")
                ScalarAlgebra.Log(args[0], args[1])
            }
            else -> {
                throw ParseException("Unknown function: $name")
            }
        }
    }
    
    private fun peek(): Char = if (pos < length) input[pos] else '\u0000'
    
    private fun skipWhitespace() {
        while (pos < length && input[pos].isWhitespace()) {
            pos++
        }
    }
    
    private fun isPartOfNumber(): Boolean {
        if (pos >= length) return false
        val nextPos = pos + 1
        return nextPos < length && (input[nextPos].isDigit() || input[nextPos] == '.')
    }
}

class ParseException(message: String) : Exception(message)
