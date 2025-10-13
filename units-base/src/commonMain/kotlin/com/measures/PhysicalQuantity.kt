package com.measures

import com.measures.acceleration.MetersPerSecondPerSecond
import com.measures.acceleration.UnitAcceleration
import com.measures.amount.UnitAmount
import com.measures.angle.UnitAngle
import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.capacitance.UnitCapacitance
import com.measures.charge.UnitCharge
import com.measures.current.Ampere
import com.measures.current.UnitCurrent
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.energy.Joule
import com.measures.energy.UnitEnergy
import com.measures.flux.UnitFlux
import com.measures.fluxdensity.UnitFluxDensity
import com.measures.force.Newton
import com.measures.force.UnitForce
import com.measures.frequency.UnitFrequency
import com.measures.inductance.UnitInductance
import com.measures.luminous.UnitLuminous
import com.measures.mass.Kilogram
import com.measures.mass.UnitMass
import com.measures.potential.UnitPotential
import com.measures.power.UnitPower
import com.measures.pressure.Pascal
import com.measures.pressure.UnitPressure
import com.measures.resistance.UnitResistance
import com.measures.solidangle.UnitSolidAngle
import com.measures.temperature.UnitTemperature
import com.measures.time.Second
import com.measures.time.UnitTime
import com.measures.velocity.MetersPerSecond
import com.measures.velocity.UnitVelocity
import com.measures.volume.Liters
import com.measures.volume.UnitVolume

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
        val timeExp: Float = 0f, // (T),
        val lengthExp: Float = 0f, // (L),
        val massExp: Float = 0f, // (M),
        val electricCurrentExp: Float = 0f, // (I),
        val absoluteTemperatureExp: Float = 0f, // (Θ),
        val amountOfSubstanceExp: Float = 0f, // (N)
        val luminousIntensityExp: Float = 0f, // (J).
        val isAngular: Boolean = false, // todo not satisfied with a boolean - needs to account for steradians
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
                this == flux -> "Flux ${inSI()}"
                this == angle -> "Angle ${inSI()}"
                this == amount -> "Amount ${inSI()}"
                this == charge -> "Charge ${inSI()}"
                this == current -> "Current ${inSI()}"
                this == distance -> "Distance ${inSI()}"
                this == luminous -> "Luminous ${inSI()}"
                this == frequency -> "Frequency ${inSI()}"
                this == potential -> "Potential ${inSI()}"
                this == inductance -> "Inductance ${inSI()}"
                this == resistance -> "Resistance ${inSI()}"
                this == solidAngle -> "SolidAngle ${inSI()}"
                this == capacitance -> "Capacitance ${inSI()}"
                this == fluxDensity -> "FluxDensity ${inSI()}"
                this == temperature -> "Temperature ${inSI()}"
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

            val flux = PhysicalDimension(/* todo */)
            val angle = PhysicalDimension(/* todo */)
            val amount = PhysicalDimension(/* todo */)
            val charge = PhysicalDimension(/* todo */)
            val current = PhysicalDimension(/* todo */)
            val luminous = PhysicalDimension(/* todo */)
            val frequency = PhysicalDimension(/* todo */)
            val potential = PhysicalDimension(/* todo */)
            val inductance = PhysicalDimension(/* todo */)
            val resistance = PhysicalDimension(/* todo */)
            val solidAngle = PhysicalDimension(/* todo */)
            val capacitance = PhysicalDimension(/* todo */)
            val fluxDensity = PhysicalDimension(/* todo */)
            val temperature = PhysicalDimension(/* todo */)

            // alias
            val distance = length
        }
    }

    companion object {
        fun <T : DoubleBase> from(unit: UnitAmount<T>) = PhysicalDimension.amount.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitAngle<T>) = PhysicalDimension.angle.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitArea<T>) = PhysicalDimension.area.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitCapacitance<T>) = PhysicalDimension.capacitance.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitCharge<T>) = PhysicalDimension.charge.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitCurrent<T>) = PhysicalDimension.current.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitDistance<T>) = PhysicalDimension.distance.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitEnergy<T>) = PhysicalDimension.energy.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitFlux<T>) = PhysicalDimension.flux.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitFluxDensity<T>) = PhysicalDimension.fluxDensity.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitForce<T>) = PhysicalDimension.force.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitFrequency<T>) = PhysicalDimension.frequency.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitInductance<T>) = PhysicalDimension.inductance.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitLuminous<T>) = PhysicalDimension.luminous.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitMass<T>) = PhysicalDimension.mass.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitPotential<T>) = PhysicalDimension.potential.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitPower<T>) = PhysicalDimension.power.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitPressure<T>) = PhysicalDimension.pressure.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitResistance<T>) = PhysicalDimension.resistance.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitSolidAngle<T>) = PhysicalDimension.solidAngle.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitTemperature<T>) = PhysicalDimension.temperature.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitTime<T>) = PhysicalDimension.time.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitVelocity<T>) = PhysicalDimension.velocity.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitVolume<T>) = PhysicalDimension.volume.of(unit.asBaseUnit().value)
        fun <T : DoubleBase> from(unit: UnitAcceleration<T>) =
            PhysicalDimension.acceleration.of(unit.asBaseUnit().value)

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
        fun flux(magnitude: Double) = PhysicalDimension.flux.of(magnitude)
        fun angle(magnitude: Double) = PhysicalDimension.angle.of(magnitude)
        fun amount(magnitude: Double) = PhysicalDimension.amount.of(magnitude)
        fun charge(magnitude: Double) = PhysicalDimension.charge.of(magnitude)
        fun current(magnitude: Double) = PhysicalDimension.current.of(magnitude)
        fun distance(magnitude: Double) = PhysicalDimension.distance.of(magnitude)
        fun luminous(magnitude: Double) = PhysicalDimension.luminous.of(magnitude)
        fun frequency(magnitude: Double) = PhysicalDimension.frequency.of(magnitude)
        fun potential(magnitude: Double) = PhysicalDimension.potential.of(magnitude)
        fun inductance(magnitude: Double) = PhysicalDimension.inductance.of(magnitude)
        fun resistance(magnitude: Double) = PhysicalDimension.resistance.of(magnitude)
        fun solidAngle(magnitude: Double) = PhysicalDimension.solidAngle.of(magnitude)
        fun capacitance(magnitude: Double) = PhysicalDimension.capacitance.of(magnitude)
        fun fluxDensity(magnitude: Double) = PhysicalDimension.fluxDensity.of(magnitude)
        fun temperature(magnitude: Double) = PhysicalDimension.temperature.of(magnitude)
    }
}