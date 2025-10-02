package com.measures

import com.measures.power.Watt
import com.measures.time.Second
import kotlin.jvm.JvmInline
import kotlin.test.Test
import kotlin.test.assertEquals

class SimpleValueClassTest {

    @Test
    fun testSimpleValueClassOperations() {
        // Test basic value class operations
        val second = Second(2.0)
        val watt = Watt(6.0)

        // Test basic property access
        assertEquals(2.0, second.value, 0.0001)
        assertEquals(6.0, watt.value, 0.0001)

        // Test simple multiplication
        val result = watt.value * second.value
        assertEquals(12.0, result, 0.0001)
    }

    @Test
    fun testValueClassCreation() {
        // Test creating value classes
        val second = Second(1.0)
        val watt = Watt(1.0)

        assertEquals(Second::class, second::class)
        assertEquals(Watt::class, watt::class)
    }

    interface B {
        fun b(): String {
            return "a"
        }
    }

    @JvmInline
    value class A(val a: String) : B {
        operator fun plus(other: A) = A(this.a + other.a)
    }

    @Test
    fun testValueClassPlus() {
        val a = A("a")
        val b = A("b")

        val c = a + b

        assertEquals("ab", c.a)
    }

    @Test
    fun testValueClassOverride() {
        val a = A("a")
        assertEquals("a", a.b())
    }
}
