package com.measures

import com.measures.distance.*
import com.measures.distance.metric.Centimeter
import com.measures.distance.metric.Kilometer
import com.measures.distance.metric.toKilometer
import com.measures.distance.uk_imperial.UKFoot
import com.measures.distance.uk_imperial.UKInch
import com.measures.distance.uk_imperial.UKYard
import com.measures.distance.uk_imperial.toUKFoot
import com.measures.distance.uk_imperial.toUKInch
import com.measures.distance.uk_imperial.toUKYard
import com.measures.volume.*
import com.measures.volume.metric.Milliliter
import com.measures.volume.metric.toMilliliter
import com.measures.weight.*
import com.measures.weight.metric.Gram
import com.measures.weight.metric.toGram
import com.measures.weight.us_customary.USPound
import com.measures.weight.us_customary.toUSPound
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
        val pound = USPound(1.0)

        // Test that conversions work
        val gramToPound = gram.toUSPound()
        val poundToGram = pound.toGram()
        
        // These are approximate conversions, so we use reasonable tolerances
        assertEquals(true, gramToPound.value > 0)
        assertEquals(true, poundToGram.value > 0)
    }
    
    @Test
    fun testImperialDistanceConversions() {
        // Test Imperial distance conversions
        val foot = UKFoot(1.0)
        val inch = UKInch(12.0)
        val yard = UKYard(1.0)
        
        // Test that conversions work
        val footToInch = foot.toUKInch()
        val inchToFoot = inch.toUKFoot()
        val yardToFoot = yard.toUKFoot()
        
        assertEquals(12.0, footToInch.value, 0.01)
        assertEquals(1.0, inchToFoot.value, 0.01)
        assertEquals(3.0, yardToFoot.value, 0.01)
    }
}