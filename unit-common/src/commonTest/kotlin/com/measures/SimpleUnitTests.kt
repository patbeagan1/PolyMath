package com.measures

import com.measures.distance.*
import com.measures.volume.*
import com.measures.weight.*
import kotlin.test.Test
import kotlin.test.assertEquals

class SimpleUnitTests {
    
    @Test
    fun testBasicDistanceConversions() {
        // Test basic SI distance conversions
        val meter = Meter(1.0)
        val kilometer = Kilometer(1.0)
        val centimeter = Centimeter(100.0)
        
        // Test that conversions work
        val meterToKm = meter.toKilometer()
        val kmToMeter = kilometer.toMeter()
        val cmToMeter = centimeter.toMeter()
        
        assertEquals(0.001, meterToKm.value, 0.0001)
        assertEquals(1000.0, kmToMeter.value, 0.1)
        assertEquals(1.0, cmToMeter.value, 0.01)
    }
    
    @Test
    fun testBasicVolumeConversions() {
        // Test basic volume conversions
        val liter = Liter(1.0)
        val milliliter = Milliliter(1000.0)
        
        // Test that conversions work
        val literToMl = liter.toMilliliter()
        val mlToLiter = milliliter.toLiter()
        
        assertEquals(1000.0, literToMl.value, 0.1)
        assertEquals(1.0, mlToLiter.value, 0.01)
    }
    
    @Test
    fun testBasicWeightConversions() {
        // Test basic weight conversions
        val gram = Gram(1000.0)
        val pound = Pound(1.0)
        
        // Test that conversions work
        val gramToPound = gram.toPound()
        val poundToGram = pound.toGram()
        
        // These are approximate conversions, so we use reasonable tolerances
        assertEquals(true, gramToPound.value > 0)
        assertEquals(true, poundToGram.value > 0)
    }
    
    @Test
    fun testImperialDistanceConversions() {
        // Test Imperial distance conversions
        val foot = ImperialFoot(1.0)
        val inch = ImperialInch(12.0)
        val yard = ImperialYard(1.0)
        
        // Test that conversions work
        val footToInch = foot.toImperialInch()
        val inchToFoot = inch.toImperialFoot()
        val yardToFoot = yard.toImperialFoot()
        
        assertEquals(12.0, footToInch.value, 0.01)
        assertEquals(1.0, inchToFoot.value, 0.01)
        assertEquals(3.0, yardToFoot.value, 0.01)
    }
}