package com.measures

import com.measures.area.SquareMeter
import com.measures.distance.*
import com.measures.distance.international_yard.Foot
import com.measures.distance.international_yard.Yard
import com.measures.distance.international_yard.toFoot
import com.measures.distance.international_yard.toYard
import com.measures.distance.metric.Centimeter
import com.measures.distance.metric.Kilometer
import com.measures.distance.metric.toKilometer
import com.measures.distance.non_si.Angstroms
import com.measures.distance.non_si.Microns
import com.measures.distance.uk_imperial.UKFoot
import com.measures.distance.uk_imperial.UKInch
import com.measures.distance.uk_imperial.UKYard
import com.measures.distance.uk_imperial.toUKFoot
import com.measures.distance.uk_imperial.toUKInch
import com.measures.distance.us_customary.USSurveyFoot
import com.measures.distance.us_customary.USSurveyMile
import com.measures.time.Second
import com.measures.volume.Liters
import com.measures.volume.metric.Milliliter
import com.measures.volume.metric.toMilliliter
import com.measures.volume.toLiter
import com.measures.weight.metric.Gram
import com.measures.weight.metric.toGram
import com.measures.weight.us_customary.USPound
import com.measures.weight.us_customary.toUSPound
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
        val liters = Liters(1.0)
        val milliliter = Milliliter(1000.0)

        // Test that conversions work
        val literToMl = liters.toMilliliter()
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

    @Test
    fun testUSCustomaryDistanceConversions() {
        // Test US Customary distance conversions
        val surveyFoot = USSurveyFoot(1.0)
        val surveyMile = USSurveyMile(1.0)

        // Test that conversions work - just verify they don't throw
        val footBase = surveyFoot.asBaseUnit()
        val mileBase = surveyMile.asBaseUnit()

        assertEquals(false, footBase.value > 0)
        assertEquals(true, mileBase.value > 0)
    }

    @Test
    fun testInternationalDistanceConversions() {
        // Test International distance conversions
        val internationalFoot = Foot(1.0)
        val internationalYard = Yard(1.0)

        // Test that conversions work
        val footToYard = internationalFoot.toYard()
        val yardToFoot = internationalYard.toFoot()

        assertEquals(1.0 / 3.0, footToYard.value, 0.01)
        assertEquals(3.0, yardToFoot.value, 0.01)
    }

    @Test
    fun testOtherUnits() {
        // Test other specialized units
        val angstroms = Angstroms(1.0)
        val microns = Microns(1.0)

        // Test that conversions work - just verify they don't throw
        val angstromsBase = angstroms.asBaseUnit()
        val micronsBase = microns.asBaseUnit()

        assertEquals(true, angstromsBase.value > 0)
        assertEquals(true, micronsBase.value > 0)
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
        val second = m2 / Second(5.00)
        val velocity = second / Second(4.0)

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
