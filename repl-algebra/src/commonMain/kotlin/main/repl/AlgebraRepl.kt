package main.repl

import main.dsl.expressions.ScalarExpression
import main.dsl.simplify

/**
 * REPL for polymath algebra that can visualize, modify, and simplify expressions.
 */
class AlgebraRepl {
    private var currentExpression: ScalarExpression? = null
    private val variables = mutableMapOf<String, Double>()
    
    /**
     * Processes a command or expression input.
     * Returns a result string to display.
     */
    fun processInput(input: String): String {
        val trimmed = input.trim()
        
        if (trimmed.isEmpty()) {
            return "Empty input"
        }
        
        // Handle REPL commands
        if (trimmed.startsWith(":")) {
            return handleCommand(trimmed.substring(1).trim())
        }
        
        try {
            // Parse the input
            val parser = Parser(trimmed)
            val expression = parser.parse()
            
            // Apply variable assignments
            if (variables.isNotEmpty()) {
                val varPairs = variables.map { it.key to it.value }.toTypedArray()
                expression.assignVariables(*varPairs)
            }
            
            // Store as current expression
            currentExpression = expression
            
            return buildString {
                appendLine("Expression: ${expression.toLatex()}")
                appendLine("Value: ${expression.evaluate()}")
            }
        } catch (e: ParseException) {
            return "Parse error: ${e.message}"
        } catch (e: Exception) {
            return "Error: ${e.message}"
        }
    }
    
    /**
     * Handles REPL commands (prefixed with :)
     */
    private fun handleCommand(cmd: String): String {
        val parts = cmd.split(Regex("\\s+"), limit = 2)
        val command = parts[0].lowercase()
        val args = if (parts.size > 1) parts[1] else ""
        
        return when (command) {
            "help", "h" -> {
                """
                |Available commands:
                |  :help, :h              - Show this help message
                |  :simplify, :s          - Simplify the current expression
                |  :visualize, :v         - Show the expression tree (Graphviz)
                |  :latex, :l             - Show LaTeX representation
                |  :eval, :e              - Evaluate the current expression
                |  :set <var> <value>     - Set a variable value
                |  :clear                 - Clear the current expression
                |  :vars                  - Show all variable assignments
                |  :tree                  - Show text representation of the tree
                |
                |You can also enter expressions directly:
                |  Infix notation: 1 + 1, 2 * 3, x^2
                |  Command notation: add 1 1, multiply 2 3
                """.trimMargin()
            }
            "simplify", "s" -> {
                val expr = currentExpression ?: return "No expression to simplify"
                val simplified = simplify(expr)
                currentExpression = simplified
                buildString {
                    appendLine("Original: ${expr.toLatex()}")
                    appendLine("Simplified: ${simplified.toLatex()}")
                    appendLine("Value: ${simplified.evaluate()}")
                }
            }
            "visualize", "v" -> {
                val expr = currentExpression ?: return "No expression to visualize"
                expr.toGraphviz()
            }
            "latex", "l" -> {
                val expr = currentExpression ?: return "No expression"
                expr.toLatex()
            }
            "eval", "e" -> {
                val expr = currentExpression ?: return "No expression to evaluate"
                try {
                    expr.evaluate().toString()
                } catch (e: Exception) {
                    "Evaluation error: ${e.message}"
                }
            }
            "set" -> {
                val setParts = args.split(Regex("\\s+"), limit = 2)
                if (setParts.size != 2) {
                    return "Usage: :set <variable> <value>"
                }
                val varName = setParts[0]
                val value = setParts[1].toDoubleOrNull()
                    ?: return "Invalid number: ${setParts[1]}"
                variables[varName] = value
                "Set $varName = $value"
            }
            "clear" -> {
                currentExpression = null
                "Expression cleared"
            }
            "vars" -> {
                if (variables.isEmpty()) {
                    "No variables set"
                } else {
                    variables.entries.joinToString("\n") { "${it.key} = ${it.value}" }
                }
            }
            "tree" -> {
                val expr = currentExpression ?: return "No expression"
                treeToString(expr)
            }
            else -> {
                "Unknown command: $command. Type :help for available commands."
            }
        }
    }
    
    /**
     * Converts an expression tree to a readable string representation.
     */
    private fun treeToString(expr: ScalarExpression, indent: String = ""): String {
        return when (expr) {
            is main.dsl.mathnum.Scalar.RealNum -> "$indent${expr.value}"
            is main.dsl.mathnum.Scalar.IntegerNum -> "$indent${expr.value}"
            is main.dsl.mathnum.Variable -> "$indent${expr.glyph} = ${expr.evaluate()}"
            is main.dsl.expressions.ScalarAlgebra.Add -> {
                buildString {
                    appendLine("$indent+ (Add)")
                    appendLine(treeToString(expr.left, "$indent  ├─ "))
                    appendLine(treeToString(expr.right, "$indent  └─ "))
                }
            }
            is main.dsl.expressions.ScalarAlgebra.Subtract -> {
                buildString {
                    appendLine("$indent- (Subtract)")
                    appendLine(treeToString(expr.left, "$indent  ├─ "))
                    appendLine(treeToString(expr.right, "$indent  └─ "))
                }
            }
            is main.dsl.expressions.ScalarAlgebra.Multiply -> {
                buildString {
                    appendLine("$indent* (Multiply)")
                    appendLine(treeToString(expr.left, "$indent  ├─ "))
                    appendLine(treeToString(expr.right, "$indent  └─ "))
                }
            }
            is main.dsl.expressions.ScalarAlgebra.Divide -> {
                buildString {
                    appendLine("$indent/ (Divide)")
                    appendLine(treeToString(expr.left, "$indent  ├─ "))
                    appendLine(treeToString(expr.right, "$indent  └─ "))
                }
            }
            is main.dsl.expressions.ScalarAlgebra.Exponent -> {
                buildString {
                    appendLine("$indent^ (Power)")
                    appendLine(treeToString(expr.left, "$indent  ├─ "))
                    appendLine(treeToString(expr.right, "$indent  └─ "))
                }
            }
            is main.dsl.expressions.ScalarAlgebra.Negate -> {
                buildString {
                    appendLine("$indent- (Negate)")
                    appendLine(treeToString(expr.operand, "$indent  └─ "))
                }
            }
            is main.dsl.expressions.ScalarAlgebra.Sqrt -> {
                buildString {
                    appendLine("$indent√ (Sqrt)")
                    appendLine(treeToString(expr.operand, "$indent  └─ "))
                }
            }
            is main.dsl.expressions.ScalarAlgebra.Sin -> {
                buildString {
                    appendLine("$indent sin")
                    appendLine(treeToString(expr.operand, "$indent  └─ "))
                }
            }
            is main.dsl.expressions.ScalarAlgebra.Cos -> {
                buildString {
                    appendLine("$indentcos")
                    appendLine(treeToString(expr.operand, "$indent  └─ "))
                }
            }
            is main.dsl.expressions.ScalarAlgebra.Tan -> {
                buildString {
                    appendLine("$indenttan")
                    appendLine(treeToString(expr.operand, "$indent  └─ "))
                }
            }
            is main.dsl.expressions.ScalarAlgebra.Ln -> {
                buildString {
                    appendLine("$indentln")
                    appendLine(treeToString(expr.operand, "$indent  └─ "))
                }
            }
            is main.dsl.expressions.ScalarAlgebra.Exp -> {
                buildString {
                    appendLine("$indentexp")
                    appendLine(treeToString(expr.operand, "$indent  └─ "))
                }
            }
            is main.dsl.expressions.ScalarAlgebra.Abs -> {
                buildString {
                    appendLine("$indent| | (Abs)")
                    appendLine(treeToString(expr.operand, "$indent  └─ "))
                }
            }
            is main.dsl.expressions.ScalarAlgebra.Factorial -> {
                buildString {
                    appendLine("$indent! (Factorial)")
                    appendLine(treeToString(expr.operand, "$indent  └─ "))
                }
            }
            else -> {
                "$indent${expr::class.simpleName}"
            }
        }
    }
    
    /**
     * Gets the current expression.
     */
    fun getCurrentExpression(): ScalarExpression? = currentExpression
    
    /**
     * Sets the current expression.
     */
    fun setExpression(expr: ScalarExpression) {
        currentExpression = expr
    }
}
