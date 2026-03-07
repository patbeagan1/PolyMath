package main.repl

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ParserTest {
    @Test
    fun testInfixAddition() {
        val parser = Parser("1 + 1")
        val result = parser.parse()
        assertEquals(2.0, result.evaluate(), 0.001)
    }
    
    @Test
    fun testInfixMultiplication() {
        val parser = Parser("2 * 3")
        val result = parser.parse()
        assertEquals(6.0, result.evaluate(), 0.001)
    }
    
    @Test
    fun testCommandNotationAdd() {
        val parser = Parser("add 1 1")
        val result = parser.parse()
        assertEquals(2.0, result.evaluate(), 0.001)
    }
    
    @Test
    fun testCommandNotationMultiply() {
        val parser = Parser("multiply 2 3")
        val result = parser.parse()
        assertEquals(6.0, result.evaluate(), 0.001)
    }
    
    @Test
    fun testComplexExpression() {
        val parser = Parser("1 + 2 * 3")
        val result = parser.parse()
        assertEquals(7.0, result.evaluate(), 0.001)
    }
    
    @Test
    fun testPower() {
        val parser = Parser("2 ^ 3")
        val result = parser.parse()
        assertEquals(8.0, result.evaluate(), 0.001)
    }
    
    @Test
    fun testSqrt() {
        val parser = Parser("sqrt 4")
        val result = parser.parse()
        assertEquals(2.0, result.evaluate(), 0.001)
    }
}
