package main.repl

import kotlin.test.Test
import kotlin.test.assertTrue

class ReplTest {
    @Test
    fun testReplBasicExpression() {
        val repl = AlgebraRepl()
        val result = repl.processInput("1 + 1")
        assertTrue(result.contains("2"))
    }
    
    @Test
    fun testReplSimplify() {
        val repl = AlgebraRepl()
        repl.processInput("1 + 1")
        val result = repl.processInput(":simplify")
        assertTrue(result.contains("Simplified"))
    }
    
    @Test
    fun testReplLatex() {
        val repl = AlgebraRepl()
        repl.processInput("x + 1")
        val result = repl.processInput(":latex")
        assertTrue(result.isNotEmpty())
    }
    
    @Test
    fun testReplTree() {
        val repl = AlgebraRepl()
        repl.processInput("1 + 2 * 3")
        val result = repl.processInput(":tree")
        assertTrue(result.contains("+") || result.contains("Add"))
    }
}
