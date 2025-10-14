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

    val asLength: Meter
        get() = convert(PhysicalDimension.length) { Meter(magnitude) }

    val asArea: SquareMeter
        get() = convert(PhysicalDimension.area) { SquareMeter(magnitude) }

    val asVolume: Liters
        get() = convert(PhysicalDimension.volume) { Liters(magnitude * 1000) }

    val asTime: Second
        get() = convert(PhysicalDimension.time) { Second(magnitude) }

    val asVelocity: MetersPerSecond
        get() = convert(PhysicalDimension.velocity) { MetersPerSecond(magnitude) }

    val asAcceleration: MetersPerSecondPerSecond
        get() = convert(PhysicalDimension.acceleration) { MetersPerSecondPerSecond(magnitude) }

    val asForce: Newton
        get() = convert(PhysicalDimension.force) { Newton(magnitude) }

    val asPressure: Pascal
        get() = convert(PhysicalDimension.pressure) { Pascal(magnitude) }

    val asMass: Kilogram
        get() = convert(PhysicalDimension.mass) { Kilogram(magnitude) }

    val asCurrent: Ampere
        get() = convert(PhysicalDimension.electricCurrent) { Ampere(magnitude) }

    val asEnergy: Joule
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
                this == temperature -> "AbsoluteTemperature ${inSI()}"
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
            /**
             * Acceleration (SI unit: meter per second squared, m·s⁻²)
             */
            val acceleration = PhysicalDimension(timeExp = -2f, lengthExp = 1f)

            /**
             * Amount of Substance (SI unit: mole, mol)
             */
            val amountOfSubstance = PhysicalDimension(amountOfSubstanceExp = 1f)

            /**
             * Angle (SI unit: radian, rad; dimensionless)
             */
            val angle = PhysicalDimension()

            /**
             * Area (SI unit: square meter, m²)
             */
            val area = PhysicalDimension(lengthExp = 2f)

            /**
             * Capacitance (SI unit: farad, F; m⁻²·kg⁻¹·s⁴·A²)
             */
            val capacitance = PhysicalDimension(timeExp = 4f, lengthExp = -2f, massExp = -1f, electricCurrentExp = 2f)

            /**
             * Electric Charge (SI unit: coulomb, C; s·A)
             */
            val charge = PhysicalDimension(timeExp = 1f, electricCurrentExp = 1f)

            /**
             * Electric Current (SI unit: ampere, A)
             */
            val current = PhysicalDimension(electricCurrentExp = 1f)

            /**
             * Density (SI derived unit: kilogram per cubic meter, kg·m⁻³)
             */
            val density = PhysicalDimension(massExp = 1f, lengthExp = -3f)

            /**
             * Electric Current (alias, SI unit: ampere, A)
             */
            val electricCurrent = PhysicalDimension(electricCurrentExp = 1f)

            /**
             * Energy (SI unit: joule, J; m²·kg·s⁻²)
             */
            val energy = PhysicalDimension(timeExp = -2f, lengthExp = 2f, massExp = 1f)

            /**
             * Magnetic Flux (SI unit: weber, Wb; m²·kg·s⁻²·A⁻¹)
             */
            val flux = PhysicalDimension(timeExp = -2f, lengthExp = 2f, massExp = 1f, electricCurrentExp = -1f)

            /**
             * Magnetic Flux Density (SI unit: tesla, T; kg·s⁻²·A⁻¹)
             */
            val fluxDensity = PhysicalDimension(timeExp = -2f, massExp = 1f, electricCurrentExp = -1f)

            /**
             * Force (SI unit: newton, N; m·kg·s⁻²)
             */
            val force = PhysicalDimension(timeExp = -2f, lengthExp = 1f, massExp = 1f)

            /**
             * Frequency (SI unit: hertz, Hz; s⁻¹)
             */
            val frequency = PhysicalDimension(timeExp = -1f)

            /**
             * Inductance (SI unit: henry, H; m²·kg·s⁻²·A⁻²)
             */
            val inductance = PhysicalDimension(timeExp = -2f, lengthExp = 2f, massExp = 1f, electricCurrentExp = -2f)

            /**
             * Length (SI unit: meter, m)
             */
            val length = PhysicalDimension(lengthExp = 1f)

            /**
             * Luminous Intensity (SI unit: candela, cd)
             */
            val luminous = PhysicalDimension(luminousIntensityExp = 1f)

            /**
             * Luminous Intensity (alias, SI unit: candela, cd)
             */
            val luminousIntensity = PhysicalDimension(luminousIntensityExp = 1f)

            /**
             * Mass (SI unit: kilogram, kg)
             */
            val mass = PhysicalDimension(massExp = 1f)

            /**
             * Momentum (SI derived unit: kilogram meter per second, kg·m·s⁻¹)
             */
            val momentum = PhysicalDimension(timeExp = -1f, lengthExp = 2f, massExp = 1f)

            /**
             * Electric Potential (SI unit: volt, V; m²·kg·s⁻³·A⁻¹)
             */
            val potential = PhysicalDimension(timeExp = -3f, lengthExp = 2f, massExp = 1f, electricCurrentExp = -1f)

            /**
             * Power (SI unit: watt, W; m²·kg·s⁻³)
             */
            val power = PhysicalDimension(timeExp = -1f, lengthExp = 1f, massExp = 1f)

            /**
             * Pressure (SI unit: pascal, Pa; m⁻¹·kg·s⁻²)
             */
            val pressure = PhysicalDimension(timeExp = -2f, lengthExp = -1f, massExp = 1f)

            /**
             * Resistance (SI unit: ohm, Ω; m²·kg·s⁻³·A⁻²)
             */
            val resistance = PhysicalDimension(timeExp = -3f, lengthExp = 2f, massExp = 1f, electricCurrentExp = -2f)

            /**
             * Solid Angle (SI unit: steradian, sr; dimensionless)
             */
            val solidAngle = PhysicalDimension()

            /**
             * Temperature (SI unit: kelvin, K)
             */
            val temperature = PhysicalDimension(absoluteTemperatureExp = 1f)

            /**
             * Time (SI unit: second, s)
             */
            val time = PhysicalDimension(timeExp = 1f)

            /**
             * Velocity (SI unit: meter per second, m·s⁻¹)
             */
            val velocity = PhysicalDimension(timeExp = -1f, lengthExp = 1f)

            /**
             * Volume (SI unit: cubic meter, m³)
             */
            val volume = PhysicalDimension(lengthExp = 3f)

            // alias
            val distance = length
        }
    }

    companion object {
        fun <T : DoubleBase> from(unit: UnitAmount<T>): PhysicalQuantity =
            PhysicalDimension.amountOfSubstance.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitAngle<T>) =
            PhysicalDimension.angle.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitArea<T>) =
            PhysicalDimension.area.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitCapacitance<T>) =
            PhysicalDimension.capacitance.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitCharge<T>) =
            PhysicalDimension.charge.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitCurrent<T>) =
            PhysicalDimension.current.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitDistance<T>) =
            PhysicalDimension.distance.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitEnergy<T>) =
            PhysicalDimension.energy.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitFlux<T>) =
            PhysicalDimension.flux.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitFluxDensity<T>) =
            PhysicalDimension.fluxDensity.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitForce<T>) =
            PhysicalDimension.force.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitFrequency<T>) =
            PhysicalDimension.frequency.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitInductance<T>) =
            PhysicalDimension.inductance.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitLuminous<T>) =
            PhysicalDimension.luminous.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitMass<T>) =
            PhysicalDimension.mass.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitPotential<T>) =
            PhysicalDimension.potential.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitPower<T>) =
            PhysicalDimension.power.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitPressure<T>) =
            PhysicalDimension.pressure.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitResistance<T>) =
            PhysicalDimension.resistance.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitSolidAngle<T>) =
            PhysicalDimension.solidAngle.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitTemperature<T>) =
            PhysicalDimension.temperature.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitTime<T>) =
            PhysicalDimension.time.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitVelocity<T>) =
            PhysicalDimension.velocity.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitVolume<T>) =
            PhysicalDimension.volume.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitAcceleration<T>) =
            PhysicalDimension.acceleration.of(unit.asBaseUnit().value)

        fun temperature(magnitude: Double) = PhysicalDimension.temperature.of(magnitude)
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
    }
}