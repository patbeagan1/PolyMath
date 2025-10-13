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

    data class PhysicalDimension(
        val timeExp: Int = 0, // (T),
        val lengthExp: Int = 0, // (L),
        val massExp: Int = 0, // (M),
        val electricCurrentExp: Int = 0, // (I),
        val absoluteTemperatureExp: Int = 0, // (Θ),
        val amountOfSubstanceExp: Int = 0, // (N)
        val luminousIntensityExp: Int = 0, // (J).
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

            if (timeExp != 0) {
                append("T")
                append(timeExp)
            }
            if (lengthExp != 0) {
                append("L")
                append(lengthExp)
            }
            if (massExp != 0) {
                append("M")
                append(massExp)
            }
            if (electricCurrentExp != 0) {
                append("I")
                append(electricCurrentExp)
            }
            if (absoluteTemperatureExp != 0) {
                append("Θ")
                append(absoluteTemperatureExp)
            }
            if (amountOfSubstanceExp != 0) {
                append("N")
                append(amountOfSubstanceExp)
            }
            if (luminousIntensityExp != 0) {
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

        companion object {
            val absoluteTemperature = PhysicalDimension(absoluteTemperatureExp = 1)
            val amountOfSubstance = PhysicalDimension(amountOfSubstanceExp = 1)
            val electricCurrent = PhysicalDimension(electricCurrentExp = 1)
            val length = PhysicalDimension(lengthExp = 1)
            val energy = PhysicalDimension(timeExp = -2, lengthExp = 2, massExp = 1)
            val area = PhysicalDimension(lengthExp = 2)
            val volume = PhysicalDimension(lengthExp = 3)
            val luminousIntensity = PhysicalDimension(luminousIntensityExp = 1)
            val mass = PhysicalDimension(massExp = 1)
            val density = PhysicalDimension(massExp = 1, lengthExp = -3)
            val velocity = PhysicalDimension(timeExp = -1, lengthExp = 1)
            val power = PhysicalDimension(timeExp = -1, lengthExp = 1, massExp = 1)
            val momentum = PhysicalDimension(timeExp = -1, lengthExp = 2, massExp = 1)
            val acceleration = PhysicalDimension(timeExp = -2, lengthExp = 1)
            val force = PhysicalDimension(timeExp = -2, lengthExp = 1, massExp = 1)
            val pressure = PhysicalDimension(timeExp = -2, lengthExp = -1, massExp = 1)
            val time = PhysicalDimension(timeExp = 1)
        }
    }

    companion object {

        fun time(magnitude: Double) = PhysicalQuantity(
            magnitude,
            PhysicalDimension(timeExp = 1)
        )

        fun distance(magnitude: Double) = length(magnitude)
        fun length(magnitude: Double) = PhysicalQuantity(
            magnitude,
            PhysicalDimension(lengthExp = 1)
        )

        fun mass(magnitude: Double) = PhysicalQuantity(
            magnitude,
            PhysicalDimension(massExp = 1)
        )

        fun force(magnitude: Double) = PhysicalQuantity(
            magnitude,
            PhysicalDimension(timeExp = -2, lengthExp = 1, massExp = 1)
        )

        fun electricCurrent(magnitude: Double) = PhysicalQuantity(
            magnitude,
            PhysicalDimension(electricCurrentExp = 1)
        )

        fun absoluteTemperature(magnitude: Double) = PhysicalQuantity(
            magnitude,
            PhysicalDimension(absoluteTemperatureExp = 1)
        )

        fun amountOfSubstance(magnitude: Double) = PhysicalQuantity(
            magnitude,
            PhysicalDimension(amountOfSubstanceExp = 1)
        )

        fun luminousIntensity(magnitude: Double) = PhysicalQuantity(
            magnitude,
            PhysicalDimension(luminousIntensityExp = 1)
        )
    }
}