package com.measures.charge

import com.measures.charge.non_si.AmpereHour
import com.measures.charge.non_si.Kilocoulomb
import com.measures.charge.non_si.Microcoulomb
import com.measures.charge.non_si.MilliampereHour
import com.measures.charge.non_si.Millicoulomb
import com.measures.charge.non_si.Nanocoulomb
import com.measures.charge.non_si.Picocoulomb
import com.measures.current.UnitCurrent
import com.measures.energy.Joule
import com.measures.power.Watt
import com.measures.time.Second
import kotlin.test.Test
import kotlin.test.assertEquals

class UnitChargeTests {

    @Test
    fun testWatt() {
        val w = Watt(6.0)
        val joules: Joule = w.times(Second(2.0))

        // The result of multiplying 6.0 W * 2.0 s should be 12.0 J
        assertEquals(12.0, joules.value, 0.0001)
        assertEquals(Joule::class, joules::class)
    }
    
    @Test
    fun testCoulombAddition() {
        // Test basic Coulomb addition
        val coulomb1 = Coulomb(5.0)
        val coulomb2 = Coulomb(3.0)
        val result = coulomb1.plus(coulomb2)
        
        assertEquals(8.0, result.value, 0.0001)
        assertEquals(Coulomb::class, result::class)
    }
    
    @Test
    fun testCoulombSubtraction() {
        // Test basic Coulomb subtraction
        val coulomb1 = Coulomb(10.0)
        val coulomb2 = Coulomb(4.0)
        val result = coulomb1.minus(coulomb2)
        
        assertEquals(6.0, result.value, 0.0001)
        assertEquals(Coulomb::class, result::class)
    }
    
    @Test
    fun testCoulombNegativeResult() {
        // Test subtraction resulting in negative value
        val coulomb1 = Coulomb(2.0)
        val coulomb2 = Coulomb(5.0)
        val result = coulomb1.minus(coulomb2)
        
        assertEquals(-3.0, result.value, 0.0001)
        assertEquals(Coulomb::class, result::class)
    }
    
    @Test
    fun testCoulombZeroResult() {
        // Test subtraction resulting in zero
        val coulomb1 = Coulomb(5.0)
        val coulomb2 = Coulomb(5.0)
        val result = coulomb1.minus(coulomb2)
        
        assertEquals(0.0, result.value, 0.0001)
        assertEquals(Coulomb::class, result::class)
    }
    
    @Test
    fun testMixedUnitAddition() {
        // Test addition between different charge units
        val coulomb = Coulomb(1.0)
        val millicoulomb = Millicoulomb(500.0) // 0.5 Coulombs
        val result = coulomb.plus(millicoulomb)
        
        assertEquals(1.5, result.value, 0.0001)
        assertEquals(Coulomb::class, result::class)
    }
    
    @Test
    fun testMixedUnitSubtraction() {
        // Test subtraction between different charge units
        val coulomb = Coulomb(2.0)
        val microcoulomb = Microcoulomb(500000.0) // 0.5 Coulombs
        val result = coulomb - microcoulomb

        assertEquals(1.5, result.value, 0.0001)
        assertEquals(Coulomb::class, result::class)
    }
    
    @Test
    fun testKilocoulombOperations() {
        // Test operations with Kilocoulomb
        val kilocoulomb = Kilocoulomb(1.0) // 1000 Coulombs
        val coulomb = Coulomb(500.0)
        val result = kilocoulomb.plus(coulomb)

        kilocoulomb.div(Second(1.0)).let {
            UnitCurrent.minusUnit(it, it)
        }
        
        assertEquals(1500.0, result.value, 0.1)
        assertEquals(Coulomb::class, result::class)
    }
    
    @Test
    fun testAmpereHourOperations() {
        // Test operations with AmpereHour (1 Ah = 3600 C)
        val ampereHour = AmpereHour(1.0) // 3600 Coulombs
        val coulomb = Coulomb(1000.0)
        val result = ampereHour.minus(coulomb)
        
        assertEquals(2600.0, result.value, 0.1)
        assertEquals(Coulomb::class, result::class)
    }
    
    @Test
    fun testMilliampereHourOperations() {
        // Test operations with MilliampereHour (1 mAh = 3.6 C)
        val milliampereHour = MilliampereHour(1000.0) // 3600 Coulombs
        val coulomb = Coulomb(1000.0)
        val result = milliampereHour.plus(coulomb)
        
        assertEquals(4600.0, result.value, 0.1)
        assertEquals(Coulomb::class, result::class)
    }
    
    @Test
    fun testSmallUnitOperations() {
        // Test operations with very small units
        val nanocoulomb = Nanocoulomb(1000.0) // 1 microcoulomb
        val picocoulomb = Picocoulomb(500000.0) // 0.5 microcoulomb
        val result = nanocoulomb.plus(picocoulomb)
        
        assertEquals(1.5E-6, result.value, 1E-9)
        assertEquals(Coulomb::class, result::class)
    }
    
    @Test
    fun testZeroAddition() {
        // Test addition with zero
        val coulomb = Coulomb(5.0)
        val zero = Coulomb(0.0)
        val result = coulomb.plus(zero)
        
        assertEquals(5.0, result.value, 0.0001)
        assertEquals(Coulomb::class, result::class)
    }
    
    @Test
    fun testZeroSubtraction() {
        // Test subtraction of zero
        val coulomb = Coulomb(5.0)
        val zero = Coulomb(0.0)
        val result = coulomb.minus(zero)
        
        assertEquals(5.0, result.value, 0.0001)
        assertEquals(Coulomb::class, result::class)
    }
    
    @Test
    fun testNegativeChargeOperations() {
        // Test operations with negative charge values
        val negativeCoulomb = Coulomb(-3.0)
        val positiveCoulomb = Coulomb(2.0)
        val result = negativeCoulomb.plus(positiveCoulomb)
        
        assertEquals(-1.0, result.value, 0.0001)
        assertEquals(Coulomb::class, result::class)
    }
    
    @Test
    fun testLargeValueOperations() {
        // Test operations with large values
        val largeCoulomb = Coulomb(1E6) // 1 million Coulombs
        val smallCoulomb = Coulomb(1.0)
        val result = largeCoulomb.plus(smallCoulomb)
        
        assertEquals(1E6 + 1.0, result.value, 0.1)
        assertEquals(Coulomb::class, result::class)
    }
    
    @Test
    fun testPrecisionOperations() {
        // Test operations with high precision values
        val preciseCoulomb1 = Coulomb(1.23456789)
        val preciseCoulomb2 = Coulomb(0.987654321)
        val result = preciseCoulomb1.plus(preciseCoulomb2)
        
        assertEquals(2.222222211, result.value, 1E-8)
        assertEquals(Coulomb::class, result::class)
    }
}
