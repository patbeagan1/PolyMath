package com.measures

import com.measures.time.Second
import com.measures.power.Watt
import com.measures.energy.Joule
import kotlin.test.Test
import kotlin.test.assertEquals

class ComplexValueClassTest {
    
    @Test
    fun testUnitOperations() {
        // Test the specific operations that are failing
        val second = Second(2.0)
        val watt = Watt(6.0)
        
        // Test the timesUnit operation that's failing
        val joules: Joule = watt * second

        assertEquals(12.0, joules.value, 0.0001)
        assertEquals(Joule::class, joules::class)
    }
    
    @Test
    fun testDirectOperation() {
        // Test direct operation without going through the unit system
        val second = Second(2.0)
        val watt = Watt(6.0)
        
        // This should work - direct multiplication
        val result = watt.value * second.value
        assertEquals(12.0, result, 0.0001)
    }
}
