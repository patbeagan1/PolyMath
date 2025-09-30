package com.measures.energy

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.distance.UnitDistance
import com.measures.weight.UnitMass
import com.measures.time.UnitTime
import com.measures.power.Watt
import com.measures.charge.UnitCharge
import com.measures.distance.times
import com.measures.potential.Volt
import kotlin.jvm.JvmInline

typealias UnitEnergy<T> = UnitEnergyType<T>

interface UnitEnergyType<T : DoubleBase> : UnitType<T, Joule>

@JvmInline
value class Joule(override val value: Double) : UnitEnergy<Joule>, BaseUnit {
    override fun asType(d: Double) = Joule(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).plus(other)
    operator fun minus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).minus(other)
    operator fun div(other: UnitTime<*>) = (this as UnitEnergy<*>).div(other)
    
    companion object {
        fun from(mass: UnitMass<*>, distance: UnitDistance<*>, time: UnitTime<*>): Joule {
            // Energy = mass × distance² / time²
            // Using the operations available: we can multiply by Double values
            val distanceSquared = distance.times(distance)
            val timeSquared = time.times(time)

            val ratio = distanceSquared / timeSquared.asBaseUnit().value
            return mass * ratio.asBaseUnit().value
        }
    }
}

@JvmInline
value class Erg(override val value: Double) : UnitEnergy<Erg> {
    override fun asType(d: Double) = Erg(d)
    override fun asBaseUnit() = Joule(this.value * 1E-7)

    operator fun plus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).plus(other)
    operator fun minus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).minus(other)
    operator fun div(other: UnitTime<*>) = (this as UnitEnergy<*>).div(other)
    operator fun div(other: UnitCharge<*>) = (this as UnitEnergy<*>).div(other)
}

@JvmInline
value class Calorie(override val value: Double) : UnitEnergy<Calorie> {
    override fun asType(d: Double) = Calorie(d)
    override fun asBaseUnit() = Joule(this.value * 4.184)

    operator fun plus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).plus(other)
    operator fun minus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).minus(other)
    operator fun div(other: UnitTime<*>) = (this as UnitEnergy<*>).div(other)
    operator fun div(other: UnitCharge<*>) = (this as UnitEnergy<*>).div(other)
}

@JvmInline
value class Kilocalorie(override val value: Double) : UnitEnergy<Kilocalorie> {
    override fun asType(d: Double) = Kilocalorie(d)
    override fun asBaseUnit() = Joule(this.value * 4184.0)

    operator fun plus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).plus(other)
    operator fun minus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).minus(other)
    operator fun div(other: UnitTime<*>) = (this as UnitEnergy<*>).div(other)
    operator fun div(other: UnitCharge<*>) = (this as UnitEnergy<*>).div(other)
}

@JvmInline
value class BritishThermalUnit(override val value: Double) : UnitEnergy<BritishThermalUnit> {
    override fun asType(d: Double) = BritishThermalUnit(d)
    override fun asBaseUnit() = Joule(this.value * 1055.056)

    operator fun plus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).plus(other)
    operator fun minus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).minus(other)
    operator fun div(other: UnitTime<*>) = (this as UnitEnergy<*>).div(other)
    operator fun div(other: UnitCharge<*>) = (this as UnitEnergy<*>).div(other)
}

@JvmInline
value class KilowattHour(override val value: Double) : UnitEnergy<KilowattHour> {
    override fun asType(d: Double) = KilowattHour(d)
    override fun asBaseUnit() = Joule(this.value * 3.6E6)

    operator fun plus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).plus(other)
    operator fun minus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).minus(other)
    operator fun div(other: UnitTime<*>) = (this as UnitEnergy<*>).div(other)
    operator fun div(other: UnitCharge<*>) = (this as UnitEnergy<*>).div(other)
}

@JvmInline
value class ElectronVolt(override val value: Double) : UnitEnergy<ElectronVolt> {
    override fun asType(d: Double) = ElectronVolt(d)
    override fun asBaseUnit() = Joule(this.value * 1.602176634E-19)

    operator fun plus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).plus(other)
    operator fun minus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).minus(other)
    operator fun div(other: UnitTime<*>) = (this as UnitEnergy<*>).div(other)
    operator fun div(other: UnitCharge<*>) = (this as UnitEnergy<*>).div(other)
}

@JvmInline
value class Kilojoule(override val value: Double) : UnitEnergy<Kilojoule> {
    override fun asType(d: Double) = Kilojoule(d)
    override fun asBaseUnit() = Joule(this.value * 1000.0)

    operator fun plus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).plus(other)
    operator fun minus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).minus(other)
    operator fun div(other: UnitTime<*>) = (this as UnitEnergy<*>).div(other)
    operator fun div(other: UnitCharge<*>) = (this as UnitEnergy<*>).div(other)
}

@JvmInline
value class Megajoule(override val value: Double) : UnitEnergy<Megajoule> {
    override fun asType(d: Double) = Megajoule(d)
    override fun asBaseUnit() = Joule(this.value * 1E6)

    operator fun plus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).plus(other)
    operator fun minus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).minus(other)
    operator fun div(other: UnitTime<*>) = (this as UnitEnergy<*>).div(other)
    operator fun div(other: UnitCharge<*>) = (this as UnitEnergy<*>).div(other)
}

operator fun UnitEnergyType<*>.plus(other: UnitEnergyType<*>): Joule =
    Joule(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitEnergyType<*>.minus(other: UnitEnergyType<*>): Joule =
    Joule(this.asBaseUnit().value - other.asBaseUnit().value)

operator fun UnitEnergyType<*>.div(other: UnitTime<*>): Watt =
    Watt(this.asBaseUnit().value / other.asBaseUnit().value)

// Energy ÷ Charge = Potential
operator fun UnitEnergyType<*>.div(other: UnitCharge<*>): Volt =
    Volt(this.asBaseUnit().value / other.asBaseUnit().value)

// Conversion functions using toUnit
fun UnitEnergy<*>.toJoule() = this.asBaseUnit()
fun UnitEnergy<*>.toErg() = toUnit(Erg(1.0))
fun UnitEnergy<*>.toCalorie() = toUnit(Calorie(1.0))
fun UnitEnergy<*>.toKilocalorie() = toUnit(Kilocalorie(1.0))
fun UnitEnergy<*>.toBritishThermalUnit() = toUnit(BritishThermalUnit(1.0))
fun UnitEnergy<*>.toKilowattHour() = toUnit(KilowattHour(1.0))
fun UnitEnergy<*>.toElectronVolt() = toUnit(ElectronVolt(1.0))
fun UnitEnergy<*>.toKilojoule() = toUnit(Kilojoule(1.0))
fun UnitEnergy<*>.toMegajoule() = toUnit(Megajoule(1.0))

