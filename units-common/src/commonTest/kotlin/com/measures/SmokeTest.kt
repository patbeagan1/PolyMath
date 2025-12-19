package com.measures

import com.measures.area.SquareMeter
import com.measures.charge.Coulomb
import com.measures.charge.metric.Kilocoulomb
import com.measures.charge.metric.Microcoulomb
import com.measures.charge.metric.Millicoulomb
import com.measures.charge.metric.Nanocoulomb
import com.measures.charge.metric.Picocoulomb
import com.measures.charge.non_si.Amperehour
import com.measures.current.UnitCurrent
import com.measures.distance.international_yard.Fathom
import com.measures.distance.international_yard.Foot
import com.measures.distance.international_yard.Inch
import com.measures.distance.international_yard.Mile
import com.measures.distance.international_yard.NauticalMile
import com.measures.distance.international_yard.Yard
import com.measures.distance.international_yard.toInch
import com.measures.distance.international_yard.toMile
import com.measures.distance.international_yard.toYard
import com.measures.distance.metric.Centimeter
import com.measures.distance.metric.Kilometer
import com.measures.distance.metric.toKilometer
import com.measures.distance.metric.toMillimeter
import com.measures.distance.non_si.Microns
import com.measures.distance.uk_imperial.UKInch
import com.measures.distance.uk_imperial.toUKHand
import com.measures.distance.us_customary.*
import com.measures.energy.Joule
import com.measures.power.Watt
import com.measures.temperature.non_si.Celsius
import com.measures.temperature.non_si.toFahrenheit
import com.measures.time.Second
import com.measures.volume.Liters
import com.measures.volume.metric.Attoliter
import com.measures.volume.metric.Milliliter
import com.measures.volume.metric.toMegaliter
import com.measures.volume.metric.toMilliliter
import com.measures.volume.toLiter
import com.measures.volume.uk_imperial.UKGallon
import com.measures.volume.uk_imperial.toUKFluidOunce
import com.measures.volume.us_customary_fluid.USFluidGallon
import com.measures.volume.us_customary_fluid.USFluidOunce
import com.measures.volume.us_customary_fluid.toUSFluidGallon
import com.measures.weight.metric.Gram
import com.measures.weight.metric.toGram
import com.measures.weight.us_customary.USPound
import com.measures.weight.us_customary.toUSGrain
import com.measures.weight.us_customary.toUSOunce
import kotlin.test.Test
import kotlin.test.assertEquals

class SmokeTest {

    private val distances = listOf(
        com.measures.distance.Meter(1.0) + com.measures.distance.Meter(2.0),
//    Meters(1.0) / Meters(2.0),
        Kilometer(3.0).asBaseUnit().toInch(),
        Centimeter(200.0) - Centimeter(1.0),

        Kilometer(1.0) + com.measures.distance.Meter(1.0),
        (Kilometer(1.0) + com.measures.distance.Meter(1.0)).toKilometer(),
        com.measures.distance.Meter(2.5).toUnit(Centimeter(1.0)),
        Inch(4.0).asBaseUnit().toInch(),
        Centimeter(4.0).toInch(),
        Centimeter(2.54).toInch(),
        Inch(400.0).asBaseUnit().toKilometer(),
        Foot(1.0).toInch(),
        Foot(3.0).toYard(),
        (SquareMeter(1.0) / Foot(1.0)).toYard(),
//        Yard(1.0).toCapefeet(),
//        (Capefeet(2.0) + com.measures.distance.Meter(6.0) - Inch(5.0)).toFoot(),
        Microns(20.0).toMillimeter(),
        USSurveyMile(1.0).toMile(),
        USSurveyLeague(1.0).toMile(),
        UKInch(2.0).toUKHand()
    ).map { it to it }
    private val volumes = listOf(
        Liters(4.0).toMilliliter() + Milliliter(6.0),
        USFluidOunce(1.0).toUKFluidOunce(),
        USFluidGallon(1.0).toLiter(),
        Attoliter(10000.0).toUSFluidGallon(),
        UKGallon(1.0).toUSFluidGallon(),
        Liters(1.0).toUSFluidGallon()
    ).map { it to it }
    private val other = listOf(
        com.measures.distance.Meter(2.0) * com.measures.distance.Meter(2.0),
        SquareMeter(4.0) / com.measures.distance.Meter(2.0),
        SquareMeter(4.0) * com.measures.distance.Meter(2.0),
        (USSurveyFurlong(1.0) * USSurveyFurlong(1.0) * USSurveyFurlong(1.0)).toMegaliter()
    ).map { it to it }

    @Test
    fun testTemperatures () {
        val celsius = Celsius(100.0)
        val fahrenheit = celsius.toFahrenheit()
        assertEquals( 212.0, fahrenheit.value)

        val kelvin = celsius.asBaseUnit()
        assertEquals(373.15, kelvin.value, )
    }

    @Test
    fun run() {
        distances.printNumbered()
        volumes.printNumbered()
        other.printNumbered()

        listOf(
            USPoint(1.0),
            USPica(1.0),
            Inch(1.0),
            Foot(1.0),
            Yard(1.0),
            Mile(1.0),
            USSurveyLink(1.0),
            USSurveyFoot(1.0),
            USSurveyRod(1.0),
            USSurveyChain(1.0),
            USSurveyFurlong(1.0),
            USSurveyMile(1.0),
            USSurveyLeague(1.0),
            Fathom(1.0),
            USCable(1.0),
            NauticalMile(1.0)
        ).map {
            it to it.asBaseUnit()
        }.sortedBy {
            it.second.value
        }.printNumbered()

        listOf(
            Gram(1.0).toUSGrain(),
            USPound(1.0).toUSOunce(),
//            TroyPound(1.0).toPound(),
//            TroyPennyweight(1.0)
        ).map {
            it to it
        }.sortedBy {
            it.second.toGram().value
        }.printNumbered()
    }

    private fun List<Pair<UnitType<*, *>, UnitType<*, *>>>.printNumbered() {
        forEachIndexed { index, it ->
            println("$index\t${it.first::class.simpleName}\t${it.second}")
        }
        println()
    }

}

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
        val ampereHour = Amperehour(1.0) // 3600 Coulombs
        val coulomb = Coulomb(1000.0)
        val result = ampereHour.minus(coulomb)

        assertEquals(2600.0, result.value, 0.1)
        assertEquals(Coulomb::class, result::class)
    }

//    @Test
//    fun testMilliampereHourOperations() {
//        // Test operations with MilliampereHour (1 mAh = 3.6 C)
//        val milliampereHour = Milliamperehour(1000.0) // 3600 Coulombs
//        val coulomb = Coulomb(1000.0)
//        val result = milliampereHour.plus(coulomb)
//
//        assertEquals(4600.0, result.value, 0.1)
//        assertEquals(Coulomb::class, result::class)
//    }

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