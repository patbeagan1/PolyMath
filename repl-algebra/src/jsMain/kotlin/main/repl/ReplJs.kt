package main.repl

import kotlin.js.JsExport

/**
 * JavaScript/Web entry point for the algebra REPL.
 * This provides a simple API that can be used from web pages.
 */
@JsExport
object ReplJs {
    private val repl = AlgebraRepl()
    
    /**
     * Evaluates an input string and returns the result.
     * This is the main entry point for JavaScript/Web usage.
     */
    fun evaluate(input: String): String {
        return repl.processInput(input)
    }
    
    /**
     * Gets the current expression as LaTeX.
     */
    fun getCurrentLatex(): String? {
        return repl.getCurrentExpression()?.toLatex()
    }
    
    /**
     * Gets the current expression as Graphviz DOT format.
     */
    fun getCurrentGraphviz(): String? {
        return repl.getCurrentExpression()?.toGraphviz()
    }
    
    /**
     * Simplifies the current expression.
     */
    fun simplify(): String {
        val expr = repl.getCurrentExpression()
        return if (expr != null) {
            repl.processInput(":simplify")
        } else {
            "No expression to simplify"
        }
    }
    
    /**
     * Gets the current expression value.
     */
    fun evaluateCurrent(): Double? {
        return try {
            repl.getCurrentExpression()?.evaluate()
        } catch (e: Exception) {
            null
        }
    }
}
