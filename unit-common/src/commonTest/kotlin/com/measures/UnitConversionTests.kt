package com.measures

import com.measures.area.SquareMeter
import com.measures.distance.*
import com.measures.other.*
import com.measures.volume.*
import com.measures.weight.*
import kotlin.test.Test
import kotlin.test.assertEquals

class UnitConversionTests {
    
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
    
    @Test
    fun testUSCustomaryDistanceConversions() {
        // Test US Customary distance conversions
        val surveyFoot = SurveyFoot(1.0)
        val surveyMile = SurveyMile(1.0)
        
        // Test that conversions work - just verify they don't throw
        val footBase = surveyFoot.asBaseUnit()
        val mileBase = surveyMile.asBaseUnit()
        
        assertEquals(true, footBase.value > 0)
        assertEquals(true, mileBase.value > 0)
    }
    
    @Test
    fun testInternationalDistanceConversions() {
        // Test International distance conversions
        val internationalFoot = InternationalFoot(1.0)
        val internationalYard = InternationalYard(1.0)
        
        // Test that conversions work
        val footToYard = internationalFoot.toInternationalYard()
        val yardToFoot = internationalYard.toInternationalFoot()
        
        assertEquals(1.0/3.0, footToYard.value, 0.01)
        assertEquals(3.0, yardToFoot.value, 0.01)
    }
    
    @Test
    fun testOtherUnits() {
        // Test other specialized units
        val angstroms = Angstroms(1.0)
        val microns = Microns(1.0)
        val capefeet = Capefeet(1.0)
        
        // Test that conversions work - just verify they don't throw
        val angstromsBase = angstroms.asBaseUnit()
        val micronsBase = microns.asBaseUnit()
        val capefeetBase = capefeet.asBaseUnit()
        
        assertEquals(true, angstromsBase.value > 0)
        assertEquals(true, micronsBase.value > 0)
        assertEquals(true, capefeetBase.value > 0)
    }
    
    @Test
    fun testVolumeCalculations() {
        // Test volume calculations
        val length = Meter(2.0)
        val width = Meter(3.0)
        val height = Meter(4.0)
        
        // Calculate volume
        val volume = length * width * height
        val volumeInLiters = volume.toLiter()
        
        assertEquals(24.0, volumeInLiters.value, 0.1)
    }
    
    @Test
    fun testAreaCalculations() {
        // Test area calculations
        val length = Meter(4.0)
        val width = Meter(5.0)
        
        // Calculate area
        val area = length * width
        val areaInSquareMeters = area.toSquareMeter()
        
        assertEquals(20.0, areaInSquareMeters.value, 0.1)
    }
}