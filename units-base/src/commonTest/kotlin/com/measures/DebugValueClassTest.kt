package com.measures

import com.measures.time.Second
import com.measures.power.Watt
import com.measures.energy.Joule
import kotlin.test.Test
import kotlin.test.assertEquals

class DebugValueClassTest {
    
    @Test
    fun testDebugUnitOperations() {
        val second = Second(2.0)
        val watt = Watt(6.0)
        
        // Debug: Check individual values
        println("Second value: ${second.value}")
        println("Watt value: ${watt.value}")
        
        // Debug: Check asBaseUnit calls
        val secondBase = second.asBaseUnit()
        val wattBase = watt.asBaseUnit()
        println("Second base value: ${secondBase.value}")
        println("Watt base value: ${wattBase.value}")
        
        // Debug: Check the operation
        val joules: Joule = watt.times(second)
        println("Joules value: ${joules.value}")
        println("Expected: 12.0")
        
        assertEquals(12.0, joules.value, 0.0001)
    }
}
