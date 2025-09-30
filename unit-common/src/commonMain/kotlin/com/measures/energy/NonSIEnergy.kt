package com.measures.energy

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.charge.UnitCharge
import com.measures.time.UnitTime
import com.measures.power.Watt
import com.measures.potential.Volt
import kotlin.jvm.JvmInline

// Non-SI Energy Units
// These units are not part of the SI system and are moved to units-common

@JvmInline
value class Erg(override val value: Double) : UnitEnergy<Erg> {
    override fun asType(d: Double) = Erg(d)
    override fun asBaseUnit() = com.measures.energy.Joule(this.value * 1E-7)

    operator fun plus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).plusUnit(other)
    operator fun minus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).minusUnit(other)
    operator fun div(other: UnitTime<*>) = (this as UnitEnergy<*>).divUnit(other)
    operator fun div(other: UnitCharge<*>) = (this as UnitEnergy<*>).divUnit(other)
}

@JvmInline
value class Calorie(override val value: Double) : UnitEnergy<Calorie> {
    override fun asType(d: Double) = Calorie(d)
    override fun asBaseUnit() = com.measures.energy.Joule(this.value * 4.184)

    operator fun plus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).plusUnit(other)
    operator fun minus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).minusUnit(other)
    operator fun div(other: UnitTime<*>) = (this as UnitEnergy<*>).divUnit(other)
    operator fun div(other: UnitCharge<*>) = (this as UnitEnergy<*>).divUnit(other)
}

@JvmInline
value class Kilocalorie(override val value: Double) : UnitEnergy<Kilocalorie> {
    override fun asType(d: Double) = Kilocalorie(d)
    override fun asBaseUnit() = com.measures.energy.Joule(this.value * 4184.0)

    operator fun plus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).plusUnit(other)
    operator fun minus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).minusUnit(other)
    operator fun div(other: UnitTime<*>) = (this as UnitEnergy<*>).divUnit(other)
    operator fun div(other: UnitCharge<*>) = (this as UnitEnergy<*>).divUnit(other)
}

@JvmInline
value class BritishThermalUnit(override val value: Double) : UnitEnergy<BritishThermalUnit> {
    override fun asType(d: Double) = BritishThermalUnit(d)
    override fun asBaseUnit() = com.measures.energy.Joule(this.value * 1055.056)

    operator fun plus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).plusUnit(other)
    operator fun minus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).minusUnit(other)
    operator fun div(other: UnitTime<*>) = (this as UnitEnergy<*>).divUnit(other)
    operator fun div(other: UnitCharge<*>) = (this as UnitEnergy<*>).divUnit(other)
}

@JvmInline
value class KilowattHour(override val value: Double) : UnitEnergy<KilowattHour> {
    override fun asType(d: Double) = KilowattHour(d)
    override fun asBaseUnit() = com.measures.energy.Joule(this.value * 3.6E6)

    operator fun plus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).plusUnit(other)
    operator fun minus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).minusUnit(other)
    operator fun div(other: UnitTime<*>) = (this as UnitEnergy<*>).divUnit(other)
    operator fun div(other: UnitCharge<*>) = (this as UnitEnergy<*>).divUnit(other)
}

@JvmInline
value class ElectronVolt(override val value: Double) : UnitEnergy<ElectronVolt> {
    override fun asType(d: Double) = ElectronVolt(d)
    override fun asBaseUnit() = com.measures.energy.Joule(this.value * 1.602176634E-19)

    operator fun plus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).plusUnit(other)
    operator fun minus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).minusUnit(other)
    operator fun div(other: UnitTime<*>) = (this as UnitEnergy<*>).divUnit(other)
    operator fun div(other: UnitCharge<*>) = (this as UnitEnergy<*>).divUnit(other)
}

// Conversion functions for non-SI energy units
fun UnitEnergy<*>.toErg() = toUnit(Erg(1.0))
fun UnitEnergy<*>.toCalorie() = toUnit(Calorie(1.0))
fun UnitEnergy<*>.toKilocalorie() = toUnit(Kilocalorie(1.0))
fun UnitEnergy<*>.toBritishThermalUnit() = toUnit(BritishThermalUnit(1.0))
fun UnitEnergy<*>.toKilowattHour() = toUnit(KilowattHour(1.0))
fun UnitEnergy<*>.toElectronVolt() = toUnit(ElectronVolt(1.0))
