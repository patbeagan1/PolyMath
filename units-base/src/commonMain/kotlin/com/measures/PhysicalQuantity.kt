package com.measures

import com.measures.absorbeddose.Gray
import com.measures.absorbeddose.UnitAbsorbedDose
import com.measures.acceleration.MetersPerSecondPerSecond
import com.measures.acceleration.UnitAcceleration
import com.measures.activity.Becquerel
import com.measures.activity.UnitActivity
import com.measures.amount.Mole
import com.measures.amount.UnitAmount
import com.measures.angle.Radian
import com.measures.angle.UnitAngle
import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.capacitance.Farad
import com.measures.capacitance.UnitCapacitance
import com.measures.catalyticactivity.Katal
import com.measures.catalyticactivity.UnitCatalyticActivity
import com.measures.charge.Coulomb
import com.measures.charge.UnitCharge
import com.measures.conductance.Siemens
import com.measures.conductance.UnitConductance
import com.measures.current.Ampere
import com.measures.current.UnitCurrent
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.doseequivalent.Sievert
import com.measures.doseequivalent.UnitDoseEquivalent
import com.measures.energy.Joule
import com.measures.energy.UnitEnergy
import com.measures.flux.UnitFlux
import com.measures.flux.Weber
import com.measures.fluxdensity.Tesla
import com.measures.fluxdensity.UnitFluxDensity
import com.measures.force.Newton
import com.measures.force.UnitForce
import com.measures.frequency.Hertz
import com.measures.frequency.UnitFrequency
import com.measures.illuminance.Lux
import com.measures.illuminance.UnitIlluminance
import com.measures.inductance.Henry
import com.measures.inductance.UnitInductance
import com.measures.luminous.Candela
import com.measures.luminous.UnitLuminous
import com.measures.luminousflux.Lumen
import com.measures.luminousflux.UnitLuminousFlux
import com.measures.mass.Kilogram
import com.measures.mass.UnitMass
import com.measures.potential.UnitPotential
import com.measures.potential.Volt
import com.measures.power.UnitPower
import com.measures.power.Watt
import com.measures.pressure.Pascal
import com.measures.pressure.UnitPressure
import com.measures.resistance.Ohm
import com.measures.resistance.UnitResistance
import com.measures.solidangle.Steradian
import com.measures.solidangle.UnitSolidAngle
import com.measures.temperature.Kelvin
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
            get() = "Expected '$dimensionGiven' to be '$dimensionTarget'"
    }

    val asLength: Meter
        get() = convert(PhysicalDimension.length) { Meter(magnitude) }

    val asArea: SquareMeter
        get() = convert(PhysicalDimension.area) { SquareMeter(magnitude) }

    val asVolume: Liters
        // this is technically not correct, as the SI unit is cubic meters
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

    val asPower: Watt
        get() = convert(PhysicalDimension.power) { Watt(magnitude) }

    val asFrequency: Hertz
        get() = convert(PhysicalDimension.frequency) { Hertz(magnitude) }

    val asAmountOfSubstance: Mole
        get() = convert(PhysicalDimension.amountOfSubstance) { Mole(magnitude) }

    val asLuminousIntensity: Candela
        get() = convert(PhysicalDimension.luminousIntensity) { Candela(magnitude) }

    val asTemperature: Kelvin
        get() = convert(PhysicalDimension.temperature) { Kelvin(magnitude) }

    val asCharge: Coulomb
        get() = convert(PhysicalDimension.charge) { Coulomb(magnitude) }

    val asPotential: Volt
        get() = convert(PhysicalDimension.electricPotential) { Volt(magnitude) }

    val asResistance: Ohm
        get() = convert(PhysicalDimension.electricResistance) { Ohm(magnitude) }

    val asCapacitance: Farad
        get() = convert(PhysicalDimension.capacitance) { Farad(magnitude) }

    val asInductance: Henry
        get() = convert(PhysicalDimension.inductance) { Henry(magnitude) }

    val asFlux: Weber
        get() = convert(PhysicalDimension.flux) { Weber(magnitude) }

    val asFluxDensity: Tesla
        get() = convert(PhysicalDimension.fluxDensity) { Tesla(magnitude) }

    val asAngle: Radian
        get() = convert(PhysicalDimension.angle) { Radian(magnitude) }

    val asSolidAngle: Steradian
        get() = convert(PhysicalDimension.solidAngle) { Steradian(magnitude) }

    val asConductance: Siemens
        get() = convert(PhysicalDimension.conductance) { Siemens(magnitude) }

    val asLuminousFlux: Lumen
        get() = convert(PhysicalDimension.luminousFlux) { Lumen(magnitude) }

    val asIlluminance: Lux
        get() = convert(PhysicalDimension.illuminance) { Lux(magnitude) }

    val asActivity: Becquerel
        get() = convert(PhysicalDimension.activity) { Becquerel(magnitude) }

    val asAbsorbedDose: Gray
        get() = convert(PhysicalDimension.absorbedDose) { Gray(magnitude) }

    val asDoseEquivalent: Sievert
        get() = convert(PhysicalDimension.doseEquivalent) { Sievert(magnitude) }

    val asCatalyticActivity: Katal
        get() = convert(PhysicalDimension.catalyticActivity) { Katal(magnitude) }

    private fun <R> convert(
        givenDimension: PhysicalDimension,
        onConvert: () -> R
    ): R = if (this.dimension.copy(tag = "") == givenDimension.copy(tag = "")) {
        // we're giving users the benefit of the doubt here by having them determine the unit, ignoring the tag
        // this is necessary because some units like becquerel and hertz have the same SI signature.
        // both the unit and the physical quantity must be specified to be unambiguous.
        onConvert()
    } else {
        throw WrongUnitException(this.dimension, givenDimension)
    }

    data class Ratio(
        val unit: SIUnit,
        val power: Float,
        val tag: String
    ) {
        enum class SIUnit {
            None,
            Time,
            Length,
            Mass,
            ElectricCurrent,
            AbsoluteTemperature,
            AmountOfSubstance,
            LuminousIntensity
        }

        companion object {
            val radian = Ratio(SIUnit.Length, 1f, "Radian")
            val steradian = Ratio(SIUnit.Length, 2f, "Steradian")
            val perRadian = Ratio(SIUnit.Length, -1f, "PerRadian")
            val perSteradian = Ratio(SIUnit.Length, -2f, "PerSteradian")
        }
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
        val ratio: Map<Ratio, Float> = emptyMap(),
        /**
         * It is important to emphasize that each physical quantity has only one coherent SI unit, even
         * though this unit can be expressed in different forms by using some of the special names and
         * symbols.
         *
         * The converse, however, is not true, because in general several different quantities may share
         * the same SI unit. For example, for the quantity heat capacity as well as for the quantity
         * entropy the SI unit is joule per kelvin. Similarly, for the base quantity electric current as well
         * as the derived quantity magnetomotive force the SI unit is the ampere. It is therefore
         * important not to use the unit alone to specify the quantity. This applies not only to technical
         * texts, but also, for example, to measuring instruments (i.e. the instrument read-out needs to
         * indicate both the unit and the quantity measured).
         * In practice, with certain quantities, preference is given to the use of certain special unit names
         * to facilitate the distinction between different quantities having the same dimension. When
         * using this freedom, one may recall the process by which this quantity is defined. For example,
         * the quantity torque is the cross product of a position vector and a force vector. The SI unit is
         * newton metre. Even though torque has the same dimension as energy (SI unit joule), the joule
         * is never used for expressing torque.
         *
         * The SI unit of frequency is hertz, the SI unit of angular velocity and angular frequency is
         * radian per second. The SI unit of activity is becquerel, implying counts per second. The use
         * of the different names emphasizes the different nature of the quantities concerned. It is
         * especially important to carefully distinguish frequencies from angular frequencies, because
         * by definition their numerical values differ by a factor1 of 2π. Ignoring this fact may cause an
         * error of 2π. Note that in some countries, frequency values are conventionally expressed using
         * “cycle/s” (“cps”) or “revolution/s” (“rev/s”) instead of the SI unit Hz, although “cycle”,
         * “cps”, “revolution” and “rev” are not units in the SI. Note also that it is common, although
         * not recommended, to use the term frequency for quantities expressed in rad/s. Because of
         * this, it is recommended that quantities called “frequency”, “angular frequency”, and “angular
         * velocity” always be given explicit units of Hz or rad/s and not s−1.
         */
        val tag: String
    ) {
        operator fun plus(other: PhysicalDimension): PhysicalDimension = PhysicalDimension(
            this.timeExp + other.timeExp,
            this.lengthExp + other.lengthExp,
            this.massExp + other.massExp,
            this.electricCurrentExp + other.electricCurrentExp,
            this.absoluteTemperatureExp + other.absoluteTemperatureExp,
            this.amountOfSubstanceExp + other.amountOfSubstanceExp,
            this.luminousIntensityExp + other.luminousIntensityExp,
            ratio.ratioOperation(other.ratio) { a, b -> a + b },
            tag = "Unknown"
        )

        operator fun minus(other: PhysicalDimension): PhysicalDimension = PhysicalDimension(
            this.timeExp - other.timeExp,
            this.lengthExp - other.lengthExp,
            this.massExp - other.massExp,
            this.electricCurrentExp - other.electricCurrentExp,
            this.absoluteTemperatureExp - other.absoluteTemperatureExp,
            this.amountOfSubstanceExp - other.amountOfSubstanceExp,
            this.luminousIntensityExp - other.luminousIntensityExp,
            ratio.ratioOperation(other.ratio) { a, b -> a - b },
            tag = "Unknown"
        )

        fun Map<Ratio, Float>.ratioOperation(
            other: Map<Ratio, Float>,
            operation: (first: Float, second: Float) -> Float
        ): Map<Ratio, Float> {
            return this.toMutableMap().let { map ->
                other.entries.forEach { (key, valueOther) ->
                    val valuePrev = map.getOrPut(key) { 0f }
                    map.put(key, operation(valuePrev, valueOther))
                }
                map
            }
        }

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
            for (i in ratio) {
                append(i.key.tag)
                append(i.value)
            }
        }

        override fun toString(): String {
            fun inSI() = "SI(\"$tag\" ${representedInSIDimension()})"
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
                this == angularVelocity -> "AngularVelocity ${inSI()}"
                this == power -> "Power ${inSI()}"
                this == momentum -> "Momentum ${inSI()}"
                this == acceleration -> "Acceleration ${inSI()}"
                this == force -> "Force ${inSI()}"
                this == pressure -> "Pressure ${inSI()}"
                this == time -> "Time ${inSI()}"
                this == flux -> "Flux ${inSI()}"
                this == angle -> "Angle ${inSI()}"
                this == charge -> "Charge ${inSI()}"
                this == electricCurrent -> "Current ${inSI()}"
                this == distance -> "Distance ${inSI()}"
                this == luminousIntensity -> "LuminousIntensity ${inSI()}"
                this == frequency -> "Frequency ${inSI()}"
                this == electricPotential -> "Potential ${inSI()}"
                this == inductance -> "Inductance ${inSI()}"
                this == electricResistance -> "Resistance ${inSI()}"
                this == solidAngle -> "SolidAngle ${inSI()}"
                this == capacitance -> "Capacitance ${inSI()}"
                this == fluxDensity -> "FluxDensity ${inSI()}"
                this == temperature -> "Temperature ${inSI()}"
                this == conductance -> "Conductance ${inSI()}"
                this == luminousFlux -> "LuminousFlux ${inSI()}"
                this == illuminance -> "Illuminance ${inSI()}"
                this == activity -> "Activity ${inSI()}"
                this == absorbedDose -> "AbsorbedDose ${inSI()}"
                this == doseEquivalent -> "DoseEquivalent ${inSI()}"
                this == catalyticActivity -> "CatalyticActivity ${inSI()}"
                else -> inSI()
            }
        }

        fun of(amount: Double) = PhysicalQuantity(amount, this)

        companion object {
            /**
             * Acceleration (SI unit: meter per second squared, m·s⁻²)
             */
            val acceleration =
                PhysicalDimension(timeExp = -2f, lengthExp = 1f, tag = "Meter per Second Squared")

            /**
             * Amount of Substance (SI unit: mole, mol)
             */
            val amountOfSubstance =
                PhysicalDimension(amountOfSubstanceExp = 1f, tag = "Mole")

            /**
             * Angle (SI unit: radian, rad; dimensionless)
             */
            val angle: PhysicalDimension = PhysicalDimension(ratio = mapOf(Ratio.radian to 1f), tag = "Radian")

            /**
             * Area (SI unit: square meter, m²)
             */
            val area =
                PhysicalDimension(lengthExp = 2f, tag = "Square Meter")

            /**
             * Capacitance (SI unit: farad, F; m⁻²·kg⁻¹·s⁴·A²)
             */
            val capacitance =
                PhysicalDimension(timeExp = 4f, lengthExp = -2f, massExp = -1f, electricCurrentExp = 2f, tag = "Farad")

            /**
             * Electric Charge (SI unit: coulomb, C; s·A)
             */
            val charge =
                PhysicalDimension(timeExp = 1f, electricCurrentExp = 1f, tag = "Coulomb")

            /**
             * Density (SI derived unit: kilogram per cubic meter, kg·m⁻³)
             */
            val density =
                PhysicalDimension(massExp = 1f, lengthExp = -3f, tag = "Kilogram per Cubic Meter")

            /**
             * Electric Current (alias, SI unit: ampere, A)
             */
            val electricCurrent =
                PhysicalDimension(electricCurrentExp = 1f, tag = "Ampere")

            /**
             * Energy (SI unit: joule, J; m²·kg·s⁻²)
             */
            val energy =
                PhysicalDimension(timeExp = -2f, lengthExp = 2f, massExp = 1f, tag = "Joule")

            /**
             * Magnetic Flux (SI unit: weber, Wb; m²·kg·s⁻²·A⁻¹)
             */
            val flux =
                PhysicalDimension(timeExp = -2f, lengthExp = 2f, massExp = 1f, electricCurrentExp = -1f, tag = "Weber")

            /**
             * Magnetic Flux Density (SI unit: tesla, T; kg·s⁻²·A⁻¹)
             */
            val fluxDensity =
                PhysicalDimension(timeExp = -2f, massExp = 1f, electricCurrentExp = -1f, tag = "Tesla")

            /**
             * Force (SI unit: newton, N; m·kg·s⁻²)
             */
            val force =
                PhysicalDimension(timeExp = -2f, lengthExp = 1f, massExp = 1f, tag = "Newton")

            /**
             * Frequency (SI unit: hertz, Hz; s⁻¹)
             */
            val frequency =
                PhysicalDimension(timeExp = -1f, tag = "Hertz")

            /**
             * Inductance (SI unit: henry, H; m²·kg·s⁻²·A⁻²)
             */
            val inductance =
                PhysicalDimension(timeExp = -2f, lengthExp = 2f, massExp = 1f, electricCurrentExp = -2f, tag = "Henry")

            /**
             * Length (SI unit: meter, m)
             */
            val length =
                PhysicalDimension(lengthExp = 1f, tag = "Meter")

            /**
             * Luminous Intensity (alias, SI unit: candela, cd)
             */
            val luminousIntensity =
                PhysicalDimension(luminousIntensityExp = 1f, tag = "Candela")

            /**
             * Mass (SI unit: kilogram, kg)
             */
            val mass =
                PhysicalDimension(massExp = 1f, tag = "Kilogram")

            /**
             * Momentum (SI derived unit: kilogram meter per second, kg·m·s⁻¹)
             */
            val momentum =
                PhysicalDimension(timeExp = -1f, lengthExp = 2f, massExp = 1f, tag = "Kilogram Meter per Second")

            /**
             * Electric Potential (SI unit: volt, V; m²·kg·s⁻³·A⁻¹)
             */
            val electricPotential =
                PhysicalDimension(timeExp = -3f, lengthExp = 2f, massExp = 1f, electricCurrentExp = -1f, tag = "Volt")

            /**
             * Power (SI unit: watt, W; m²·kg·s⁻³)
             */
            val power =
                PhysicalDimension(timeExp = -1f, lengthExp = 1f, massExp = 1f, tag = "Watt")

            /**
             * Pressure (SI unit: pascal, Pa; m⁻¹·kg·s⁻²)
             */
            val pressure =
                PhysicalDimension(timeExp = -2f, lengthExp = -1f, massExp = 1f, tag = "Pascal")

            /**
             * Resistance (SI unit: ohm, Ω; m²·kg·s⁻³·A⁻²)
             */
            val electricResistance =
                PhysicalDimension(timeExp = -3f, lengthExp = 2f, massExp = 1f, electricCurrentExp = -2f, tag = "Ohm")

            /**
             * Solid Angle (SI unit: steradian, sr; dimensionless)
             */
            val solidAngle: PhysicalDimension =
                PhysicalDimension(ratio = mapOf(Ratio.steradian to 1f), tag = "Steradian")

            /**
             * Temperature (SI unit: kelvin, K)
             */
            val temperature =
                PhysicalDimension(absoluteTemperatureExp = 1f, tag = "Kelvin")

            /**
             * Time (SI unit: second, s)
             */
            val time =
                PhysicalDimension(timeExp = 1f, tag = "Second")

            /**
             * Velocity (SI unit: meter per second, m·s⁻¹)
             */
            val velocity =
                PhysicalDimension(timeExp = -1f, lengthExp = 1f, tag = "Meter per Second")

            /**
             * Velocity (SI unit: radian per second, rad·s⁻¹)
             */
            val angularVelocity =
                PhysicalDimension(timeExp = -1f, ratio = mapOf(Ratio.radian to 1f), tag = "Radian per Second")

            /**
             * Volume (SI unit: cubic meter, m³)
             */
            val volume =
                PhysicalDimension(lengthExp = 3f, tag = "Cubic Meter")

            /**
             * Conductance (SI unit: siemens, S; kg⁻¹·m⁻²·s³·A²)
             */
            val conductance =
                PhysicalDimension(timeExp = 3f, lengthExp = -2f, massExp = -1f, electricCurrentExp = 2f, tag = "Siemen")

            /**
             * Luminous Flux (SI unit: lumen, lm; cd·sr)
             */
            val luminousFlux =
                PhysicalDimension(luminousIntensityExp = 1f, tag = "Lumen")

            /**
             * Illuminance (SI unit: lux, lx; cd·sr·m⁻²)
             */
            val illuminance =
                PhysicalDimension(lengthExp = -2f, luminousIntensityExp = 1f, tag = "Lux")

            /**
             * Activity (SI unit: becquerel, Bq; s⁻¹)
             */
            val activity =
                PhysicalDimension(timeExp = -1f, tag = "Becquerel")

            /**
             * Absorbed Dose (SI unit: gray, Gy; m²·s⁻²)
             */
            val absorbedDose =
                PhysicalDimension(timeExp = -2f, lengthExp = 2f, tag = "Gray")

            /**
             * Dose Equivalent (SI
             * unit: sievert, Sv; m²·s⁻²)
             */
            val doseEquivalent =
                PhysicalDimension(timeExp = -2f, lengthExp = 2f, tag = "Sievert")

            /**
             * Catalytic Activity (SI unit: katal, kat; mol·s⁻¹)
             */
            val catalyticActivity =
                PhysicalDimension(timeExp = -1f, amountOfSubstanceExp = 1f, tag = "Katal")

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
            PhysicalDimension.electricCurrent.of(unit.asBaseUnit().value)

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
            PhysicalDimension.luminousIntensity.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitMass<T>) =
            PhysicalDimension.mass.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitPotential<T>) =
            PhysicalDimension.electricPotential.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitPower<T>) =
            PhysicalDimension.power.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitPressure<T>) =
            PhysicalDimension.pressure.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitResistance<T>) =
            PhysicalDimension.electricResistance.of(unit.asBaseUnit().value)

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

        fun <T : DoubleBase> from(unit: UnitConductance<T>) =
            PhysicalDimension.conductance.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitLuminousFlux<T>) =
            PhysicalDimension.luminousFlux.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitIlluminance<T>) =
            PhysicalDimension.illuminance.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitActivity<T>) =
            PhysicalDimension.activity.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitAbsorbedDose<T>) =
            PhysicalDimension.absorbedDose.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitDoseEquivalent<T>) =
            PhysicalDimension.doseEquivalent.of(unit.asBaseUnit().value)

        fun <T : DoubleBase> from(unit: UnitCatalyticActivity<T>) =
            PhysicalDimension.catalyticActivity.of(unit.asBaseUnit().value)

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
        fun angularVelocity(magnitude: Double) = PhysicalDimension.angularVelocity.of(magnitude)
        fun power(magnitude: Double) = PhysicalDimension.power.of(magnitude)
        fun momentum(magnitude: Double) = PhysicalDimension.momentum.of(magnitude)
        fun acceleration(magnitude: Double) = PhysicalDimension.acceleration.of(magnitude)
        fun force(magnitude: Double) = PhysicalDimension.force.of(magnitude)
        fun pressure(magnitude: Double) = PhysicalDimension.pressure.of(magnitude)
        fun time(magnitude: Double) = PhysicalDimension.time.of(magnitude)
        fun flux(magnitude: Double) = PhysicalDimension.flux.of(magnitude)
        fun angle(magnitude: Double) = PhysicalDimension.angle.of(magnitude)
        fun charge(magnitude: Double) = PhysicalDimension.charge.of(magnitude)
        fun current(magnitude: Double) = PhysicalDimension.electricCurrent.of(magnitude)
        fun distance(magnitude: Double) = PhysicalDimension.distance.of(magnitude)
        fun luminous(magnitude: Double) = PhysicalDimension.luminousIntensity.of(magnitude)
        fun frequency(magnitude: Double) = PhysicalDimension.frequency.of(magnitude)
        fun potential(magnitude: Double) = PhysicalDimension.electricPotential.of(magnitude)
        fun inductance(magnitude: Double) = PhysicalDimension.inductance.of(magnitude)
        fun resistance(magnitude: Double) = PhysicalDimension.electricResistance.of(magnitude)
        fun solidAngle(magnitude: Double) = PhysicalDimension.solidAngle.of(magnitude)
        fun capacitance(magnitude: Double) = PhysicalDimension.capacitance.of(magnitude)
        fun fluxDensity(magnitude: Double) = PhysicalDimension.fluxDensity.of(magnitude)
        fun conductance(magnitude: Double) = PhysicalDimension.conductance.of(magnitude)
        fun luminousFlux(magnitude: Double) = PhysicalDimension.luminousFlux.of(magnitude)
        fun illuminance(magnitude: Double) = PhysicalDimension.illuminance.of(magnitude)
        fun activity(magnitude: Double) = PhysicalDimension.activity.of(magnitude)
        fun absorbedDose(magnitude: Double) = PhysicalDimension.absorbedDose.of(magnitude)
        fun doseEquivalent(magnitude: Double) = PhysicalDimension.doseEquivalent.of(magnitude)
        fun catalyticActivity(magnitude: Double) = PhysicalDimension.catalyticActivity.of(magnitude)
    }
}