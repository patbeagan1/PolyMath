package com.measures

import com.measures.acceleration.MetersPerSecondPerSecond
import com.measures.area.SquareMeter
import com.measures.current.Ampere
import com.measures.distance.Meter
import com.measures.energy.Joule
import com.measures.force.Newton
import com.measures.mass.Kilogram
import com.measures.pressure.Pascal
import com.measures.time.Second
import com.measures.velocity.MetersPerSecond
import com.measures.volume.Liters

/**
 * Covers the full basis of physical quantities using the SI units.
 *
 * Contains conversions to typesafe units for the most common units.
 */
data class PhysicalQuantity(
    val magnitude: Double,
    val dimension: PhysicalDimension
) {
    fun isCommensurableTo(other: PhysicalQuantity) = dimension == other.dimension

    operator fun plus(other: PhysicalQuantity): PhysicalQuantity? = if (isCommensurableTo(other)) {
        PhysicalQuantity(this.magnitude + other.magnitude, dimension)
    } else null

    operator fun minus(other: PhysicalQuantity): PhysicalQuantity? = if (isCommensurableTo(other)) {
        PhysicalQuantity(this.magnitude - other.magnitude, dimension)
    } else null

    operator fun times(other: PhysicalQuantity): PhysicalQuantity = PhysicalQuantity(
        magnitude * other.magnitude,
        dimension + other.dimension
    )

    operator fun div(other: PhysicalQuantity): PhysicalQuantity = PhysicalQuantity(
        magnitude * other.magnitude,
        dimension - other.dimension
    )

    class WrongUnitException(
        val dimensionGiven: PhysicalDimension,
        val dimensionTarget: PhysicalDimension
    ) : Exception() {
        override val message: String?
            get() = "Expected $dimensionGiven to be $dimensionTarget"
    }

    val inMeters: Meter
        get() = convert(PhysicalDimension.length) { Meter(magnitude) }

    val inSquareMeters: SquareMeter
        get() = convert(PhysicalDimension.area) { SquareMeter(magnitude) }

    val inLeters: Liters
        get() = convert(PhysicalDimension.volume) { Liters(magnitude * 1000) }

    val inSeconds: Second
        get() = convert(PhysicalDimension.time) { Second(magnitude) }

    val inMetersPerSecond: MetersPerSecond
        get() = convert(PhysicalDimension.velocity) { MetersPerSecond(magnitude) }

    val inMetersPerSecondPerSecond: MetersPerSecondPerSecond
        get() = convert(PhysicalDimension.acceleration) { MetersPerSecondPerSecond(magnitude) }

    val inNewtons: Newton
        get() = convert(PhysicalDimension.force) { Newton(magnitude) }

    val inPascals: Pascal
        get() = convert(PhysicalDimension.pressure) { Pascal(magnitude) }

    val inKilograms: Kilogram
        get() = convert(PhysicalDimension.mass) { Kilogram(magnitude) }

    val inAmperes: Ampere
        get() = convert(PhysicalDimension.electricCurrent) { Ampere(magnitude) }

    val inJoules: Joule
        get() = convert(PhysicalDimension.energy) { Joule(magnitude) }

    private fun <R> convert(
        givenDimension: PhysicalDimension,
        onConvert: () -> R
    ): R = if (this.dimension == givenDimension) {
        onConvert()
    } else {
        throw WrongUnitException(this.dimension, givenDimension)
    }

    /**
     * Some systems like CGS might have a fractional basis, so this needs to be a float
     */
    data class PhysicalDimension(
        val timeExp: Float  = 0f, // (T),
        val lengthExp: Float  = 0f, // (L),
        val massExp: Float  = 0f, // (M),
        val electricCurrentExp: Float  = 0f, // (I),
        val absoluteTemperatureExp: Float  = 0f, // (Θ),
        val amountOfSubstanceExp: Float  = 0f, // (N)
        val luminousIntensityExp: Float  = 0f, // (J).
        val isAngular: Boolean = false,
    ) {
        operator fun plus(other: PhysicalDimension): PhysicalDimension = PhysicalDimension(
            this.timeExp + other.timeExp,
            this.lengthExp + other.lengthExp,
            this.massExp + other.massExp,
            this.electricCurrentExp + other.electricCurrentExp,
            this.absoluteTemperatureExp + other.absoluteTemperatureExp,
            this.amountOfSubstanceExp + other.amountOfSubstanceExp,
            this.luminousIntensityExp + other.luminousIntensityExp,
        )

        operator fun minus(other: PhysicalDimension): PhysicalDimension = PhysicalDimension(
            this.timeExp - other.timeExp,
            this.lengthExp - other.lengthExp,
            this.massExp - other.massExp,
            this.electricCurrentExp - other.electricCurrentExp,
            this.absoluteTemperatureExp - other.absoluteTemperatureExp,
            this.amountOfSubstanceExp - other.amountOfSubstanceExp,
            this.luminousIntensityExp - other.luminousIntensityExp,
        )

        fun representedInSIDimension() = buildString {
            if (timeExp != 0f) {
                append("T")
                append(timeExp)
            }
            if (lengthExp != 0f) {
                append("L")
                append(lengthExp)
            }
            if (massExp != 0f) {
                append("M")
                append(massExp)
            }
            if (electricCurrentExp != 0f) {
                append("I")
                append(electricCurrentExp)
            }
            if (absoluteTemperatureExp != 0f) {
                append("Θ")
                append(absoluteTemperatureExp)
            }
            if (amountOfSubstanceExp != 0f) {
                append("N")
                append(amountOfSubstanceExp)
            }
            if (luminousIntensityExp != 0f) {
                append("J")
                append(luminousIntensityExp)
            }
        }

        override fun toString(): String {
            fun inSI() = "SI(${representedInSIDimension()})"
            return when {
                this == absoluteTemperature -> "AbsoluteTemperature ${inSI()}"
                this == amountOfSubstance -> "AmountOfSubstance ${inSI()}"
                this == electricCurrent -> "ElectricCurrent ${inSI()}"
                this == length -> "Length ${inSI()}"
                this == energy -> "Energy ${inSI()}"
                this == area -> "Area ${inSI()}"
                this == volume -> "Volume ${inSI()}"
                this == luminousIntensity -> "LuminousIntensity ${inSI()}"
                this == mass -> "Mass ${inSI()}"
                this == density -> "Density ${inSI()}"
                this == velocity -> "Velocity ${inSI()}"
                this == power -> "Power ${inSI()}"
                this == momentum -> "Momentum ${inSI()}"
                this == acceleration -> "Acceleration ${inSI()}"
                this == force -> "Force ${inSI()}"
                this == pressure -> "Pressure ${inSI()}"
                this == time -> "Time ${inSI()}"
                else -> inSI()
            }
        }

        fun of(amount: Double) = PhysicalQuantity(amount, this)

        companion object {
            val absoluteTemperature = PhysicalDimension(absoluteTemperatureExp = 1f)
            val amountOfSubstance = PhysicalDimension(amountOfSubstanceExp = 1f)
            val electricCurrent = PhysicalDimension(electricCurrentExp = 1f)
            val length = PhysicalDimension(lengthExp = 1f)
            val energy = PhysicalDimension(timeExp = -2f, lengthExp = 2f, massExp = 1f)
            val area = PhysicalDimension(lengthExp = 2f)
            val volume = PhysicalDimension(lengthExp = 3f)
            val luminousIntensity = PhysicalDimension(luminousIntensityExp = 1f)
            val mass = PhysicalDimension(massExp = 1f)
            val density = PhysicalDimension(massExp = 1f, lengthExp = -3f)
            val velocity = PhysicalDimension(timeExp = -1f, lengthExp = 1f)
            val power = PhysicalDimension(timeExp = -1f, lengthExp = 1f, massExp = 1f)
            val momentum = PhysicalDimension(timeExp = -1f, lengthExp = 2f, massExp = 1f)
            val acceleration = PhysicalDimension(timeExp = -2f, lengthExp = 1f)
            val force = PhysicalDimension(timeExp = -2f, lengthExp = 1f, massExp = 1f)
            val pressure = PhysicalDimension(timeExp = -2f, lengthExp = -1f, massExp = 1f)
            val time = PhysicalDimension(timeExp = 1f)
        }
    }

    companion object {
        // alias
        fun distance(magnitude: Double) = length(magnitude)

        fun absoluteTemperature(magnitude: Double) = PhysicalDimension.absoluteTemperature.of(magnitude)
        fun amountOfSubstance(magnitude: Double) = PhysicalDimension.amountOfSubstance.of(magnitude)
        fun electricCurrent(magnitude: Double) = PhysicalDimension.electricCurrent.of(magnitude)
        fun length(magnitude: Double) = PhysicalDimension.length.of(magnitude)
        fun energy(magnitude: Double) = PhysicalDimension.energy.of(magnitude)
        fun area(magnitude: Double) = PhysicalDimension.area.of(magnitude)
        fun volume(magnitude: Double) = PhysicalDimension.volume.of(magnitude)
        fun luminousIntensity(magnitude: Double) = PhysicalDimension.luminousIntensity.of(magnitude)
        fun mass(magnitude: Double) = PhysicalDimension.mass.of(magnitude)
        fun density(magnitude: Double) = PhysicalDimension.density.of(magnitude)
        fun velocity(magnitude: Double) = PhysicalDimension.velocity.of(magnitude)
        fun power(magnitude: Double) = PhysicalDimension.power.of(magnitude)
        fun momentum(magnitude: Double) = PhysicalDimension.momentum.of(magnitude)
        fun acceleration(magnitude: Double) = PhysicalDimension.acceleration.of(magnitude)
        fun force(magnitude: Double) = PhysicalDimension.force.of(magnitude)
        fun pressure(magnitude: Double) = PhysicalDimension.pressure.of(magnitude)
        fun time(magnitude: Double) = PhysicalDimension.time.of(magnitude)
    }
}