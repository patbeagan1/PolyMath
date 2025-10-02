package com.measures.energy

import com.measures.charge.UnitCharge
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

// Non-SI Energy Units
// These units are not part of the SI system and are moved to units-common

@JvmInline
value class Erg(override val value: Double) : UnitEnergy<Erg> {
    override fun asType(d: Double) = Erg(d)
    override fun asBaseUnit() = Joule(this.value * 1E-7)

    override operator fun plus(other: UnitEnergy<*>) = UnitEnergy.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitEnergy<*>) = UnitEnergy.Companion.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitEnergy.Companion.divUnit(this, other)
    override operator fun div(other: UnitCharge<*>) = UnitEnergy.Companion.divUnit(this, other)
}

@JvmInline
value class Calorie(override val value: Double) : UnitEnergy<Calorie> {
    override fun asType(d: Double) = Calorie(d)
    override fun asBaseUnit() = Joule(this.value * 4.184)

    override operator fun plus(other: UnitEnergy<*>) = UnitEnergy.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitEnergy<*>) = UnitEnergy.Companion.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitEnergy.Companion.divUnit(this, other)
    override operator fun div(other: UnitCharge<*>) = UnitEnergy.Companion.divUnit(this, other)
}

@JvmInline
value class Kilocalorie(override val value: Double) : UnitEnergy<Kilocalorie> {
    override fun asType(d: Double) = Kilocalorie(d)
    override fun asBaseUnit() = Joule(this.value * 4184.0)

    override operator fun plus(other: UnitEnergy<*>) = UnitEnergy.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitEnergy<*>) = UnitEnergy.Companion.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitEnergy.Companion.divUnit(this, other)
    override operator fun div(other: UnitCharge<*>) = UnitEnergy.Companion.divUnit(this, other)
}

@JvmInline
value class BritishThermalUnit(override val value: Double) : UnitEnergy<BritishThermalUnit> {
    override fun asType(d: Double) = BritishThermalUnit(d)
    override fun asBaseUnit() = Joule(this.value * 1055.056)

    override operator fun plus(other: UnitEnergy<*>) = UnitEnergy.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitEnergy<*>) = UnitEnergy.Companion.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitEnergy.Companion.divUnit(this, other)
    override operator fun div(other: UnitCharge<*>) = UnitEnergy.Companion.divUnit(this, other)
}

@JvmInline
value class KilowattHour(override val value: Double) : UnitEnergy<KilowattHour> {
    override fun asType(d: Double) = KilowattHour(d)
    override fun asBaseUnit() = Joule(this.value * 3.6E6)

    override operator fun plus(other: UnitEnergy<*>) = UnitEnergy.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitEnergy<*>) = UnitEnergy.Companion.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitEnergy.Companion.divUnit(this, other)
    override operator fun div(other: UnitCharge<*>) = UnitEnergy.Companion.divUnit(this, other)
}

@JvmInline
value class ElectronVolt(override val value: Double) : UnitEnergy<ElectronVolt> {
    override fun asType(d: Double) = ElectronVolt(d)
    override fun asBaseUnit() = Joule(this.value * 1.602176634E-19)

    override operator fun plus(other: UnitEnergy<*>) = UnitEnergy.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitEnergy<*>) = UnitEnergy.Companion.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitEnergy.Companion.divUnit(this, other)
    override operator fun div(other: UnitCharge<*>) = UnitEnergy.Companion.divUnit(this, other)
}

@JvmInline
value class Kilojoule(override val value: Double) : UnitEnergy<Kilojoule> {
    override fun asType(d: Double) = Kilojoule(d)
    override fun asBaseUnit() = Joule(this.value * 1000.0)

    override operator fun plus(other: UnitEnergy<*>) = UnitEnergy.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitEnergy<*>) = UnitEnergy.Companion.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitEnergy.Companion.divUnit(this, other)
    override operator fun div(other: UnitCharge<*>) = UnitEnergy.Companion.divUnit(this, other)
}

@JvmInline
value class Megajoule(override val value: Double) : UnitEnergy<Megajoule> {
    override fun asType(d: Double) = Megajoule(d)
    override fun asBaseUnit() = Joule(this.value * 1E6)

    override operator fun plus(other: UnitEnergy<*>) = UnitEnergy.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitEnergy<*>) = UnitEnergy.Companion.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitEnergy.Companion.divUnit(this, other)
    override operator fun div(other: UnitCharge<*>) = UnitEnergy.Companion.divUnit(this, other)
}

// Conversion functions for non-SI energy units
fun UnitEnergy<*>.toErg() = toUnit(Erg(1.0))
fun UnitEnergy<*>.toCalorie() = toUnit(Calorie(1.0))
fun UnitEnergy<*>.toKilocalorie() = toUnit(Kilocalorie(1.0))
fun UnitEnergy<*>.toBritishThermalUnit() = toUnit(BritishThermalUnit(1.0))
fun UnitEnergy<*>.toKilowattHour() = toUnit(KilowattHour(1.0))
fun UnitEnergy<*>.toElectronVolt() = toUnit(ElectronVolt(1.0))
fun UnitEnergy<*>.toKilojoule() = toUnit(Kilojoule(1.0))
fun UnitEnergy<*>.toMegajoule() = toUnit(Megajoule(1.0))
