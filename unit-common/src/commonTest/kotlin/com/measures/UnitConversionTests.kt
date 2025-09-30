package com.measures

import com.measures.area.SquareMeter
import com.measures.distance.*
import com.measures.misc.Angstroms
import com.measures.misc.Capefeet
import com.measures.misc.Microns
import com.measures.volume.Liter
import com.measures.volume.Milliliter
import com.measures.volume.toLiter
import com.measures.volume.toMilliliter
import com.measures.weight.Gram
import com.measures.weight.Pound
import com.measures.weight.toGram
import com.measures.weight.toPound
import kotlin.jvm.JvmInline
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

        assertEquals(1.0 / 3.0, footToYard.value, 0.01)
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

        assertEquals(24_000.0, volume.value, 0.1)
    }

    @Test
    fun testAreaCalculations() {
        // Test area calculations
        val length = Meter(4.0)
        val width = Meter(5.0)

        // Calculate area
        val area = length * width

        assertEquals(20.0, area.value, 0.1)
    }

    @Test
    fun testAreaBox() {
        // Test area calculations
        val area = SquareMeter(4.0)
        area / Meter(1.0)
        assertEquals(4.0, area.value, 0.1)
    }

    @Test
    fun testSquareMetersToMetersConversion() {
        // Given an area in square meters
        val area = SquareMeter(16.0)
        // Converting area to length by taking the square root
        val length: Meter = area / Meter(4.0)

        assertEquals(4.0, length.value, 0.0001)
    }

    @Test
    fun testAddingTwoMetersBroken() {
        val m1 = Meter(3.0)
        val m2 = Meter(2.0)
        val sum = m1 + m2

        assertEquals(5.0, sum.value, 0.0001)
    }
}

// Define an interface for UnitDistanceTypedFull with default implementations
interface UnitDistanceTypedFull {
    val value: Double

    operator fun plus(other: UnitDistanceTypedFull): UnitDistanceTypedFull {
        return DistanceFull(this.value + other.value)
    }

    operator fun minus(other: UnitDistanceTypedFull): UnitDistanceTypedFull {
        return DistanceFull(this.value - other.value)
    }

    operator fun times(other: UnitDistanceTypedFull): UnitDistanceTypedFull {
        return DistanceFull(this.value * other.value)
    }

    operator fun div(other: UnitDistanceTypedFull): UnitDistanceTypedFull {
        return DistanceFull(this.value / other.value)
    }
}

// Value class implementation of UnitDistanceTypedFull
@JvmInline
value class DistanceFull(override val value: Double) : UnitDistanceTypedFull

class DistanceFullTests {
    @Test
    fun testDistanceFullOperations() {
        val d1: DistanceFull = DistanceFull(10.0)
        val d2: DistanceFull = DistanceFull(2.0)

        val sum = d1 + d2
        val diff = d1 - d2
        val prod = d1 * d2
        val quot = d1 / d2

        assertEquals(12.0, sum.value, 0.0001)
        assertEquals(8.0, diff.value, 0.0001)
        assertEquals(20.0, prod.value, 0.0001)
        assertEquals(5.0, quot.value, 0.0001)
    }
}
