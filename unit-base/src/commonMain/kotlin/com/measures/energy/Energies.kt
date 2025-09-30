package com.measures.energy

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.charge.UnitCharge
import com.measures.distance.UnitDistance
import com.measures.potential.Volt
import com.measures.power.Watt
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

typealias UnitEnergy<T> = UnitEnergyType<T>

interface UnitEnergyType<T : DoubleBase> : UnitType<T, Joule>

@JvmInline
value class Joule(override val value: Double) : UnitEnergy<Joule>, BaseUnit {
    override fun asType(d: Double) = Joule(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).plusUnit(other)
    operator fun minus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).minusUnit(other)
    operator fun div(other: UnitTime<*>) = (this as UnitEnergy<*>).divUnit(other)

    companion object {
        fun from(mass: UnitMass<*>, distance: UnitDistance<*>, time: UnitTime<*>): Joule {
            // Energy = mass × distance² / time²
            // Using the operations available: we can multiply by Double values
            val distanceSquared = distance.times(distance).asBaseUnit().value
            val timeSquared = time.asBaseUnit().value * time.asBaseUnit().value

            val ratio = distanceSquared / timeSquared
            return Joule(mass.asBaseUnit().value * ratio)
        }
    }
}

// Non-SI energy units have been moved to units-common module

@JvmInline
value class Kilojoule(override val value: Double) : UnitEnergy<Kilojoule> {
    override fun asType(d: Double) = Kilojoule(d)
    override fun asBaseUnit() = Joule(this.value * 1000.0)

    operator fun plus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).plusUnit(other)
    operator fun minus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).minusUnit(other)
    operator fun div(other: UnitTime<*>) = (this as UnitEnergy<*>).divUnit(other)
    operator fun div(other: UnitCharge<*>) = (this as UnitEnergy<*>).divUnit(other)
}

@JvmInline
value class Megajoule(override val value: Double) : UnitEnergy<Megajoule> {
    override fun asType(d: Double) = Megajoule(d)
    override fun asBaseUnit() = Joule(this.value * 1E6)

    operator fun plus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).plusUnit(other)
    operator fun minus(other: UnitEnergy<*>) = (this as UnitEnergy<*>).minusUnit(other)
    operator fun div(other: UnitTime<*>) = (this as UnitEnergy<*>).divUnit(other)
    operator fun div(other: UnitCharge<*>) = (this as UnitEnergy<*>).divUnit(other)
}

 fun UnitEnergyType<*>.plusUnit(other: UnitEnergyType<*>): Joule =
    Joule(this.asBaseUnit().value + other.asBaseUnit().value)

 fun UnitEnergyType<*>.minusUnit(other: UnitEnergyType<*>): Joule =
    Joule(this.asBaseUnit().value - other.asBaseUnit().value)

 fun UnitEnergyType<*>.divUnit(other: UnitTime<*>): Watt =
    Watt(this.asBaseUnit().value / other.asBaseUnit().value)

// Energy ÷ Charge = Potential
 fun UnitEnergyType<*>.divUnit(other: UnitCharge<*>): Volt =
    Volt(this.asBaseUnit().value / other.asBaseUnit().value)

// Conversion functions using toUnit
fun UnitEnergy<*>.toJoule() = this.asBaseUnit()
fun UnitEnergy<*>.toKilojoule() = toUnit(Kilojoule(1.0))
fun UnitEnergy<*>.toMegajoule() = toUnit(Megajoule(1.0))

