package com.measures

import com.measures.time.Second
import com.measures.power.Watt
import com.measures.energy.Joule
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
}
