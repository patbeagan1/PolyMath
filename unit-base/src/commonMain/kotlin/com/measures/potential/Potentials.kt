package com.measures.potential

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.weight.UnitWeight
import com.measures.current.UnitCurrent
import com.measures.distance.UnitDistance
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

typealias UnitPotential<T> = UnitPotentialType<T>

interface UnitPotentialType<T : DoubleBase> : UnitType<T, Volt>

@JvmInline
value class Volt(override val value: Double) : UnitPotential<Volt>, BaseUnit {
    override fun asType(d: Double) = Volt(d)
    override fun asBaseUnit() = this
    
    companion object {
        fun from(mass: UnitWeight<*>, distance: UnitDistance<*>, current: UnitCurrent<*>, time: UnitTime<*>): Volt {
            val massBase = mass.asBaseUnit()
            val distanceBase = distance.asBaseUnit()
            val currentBase = current.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Volt(massBase.value * distanceBase.value * distanceBase.value / (currentBase.value * timeBase.value * timeBase.value * timeBase.value))
        }
    }
}

@JvmInline
value class Millivolt(override val value: Double) : UnitPotential<Millivolt> {
    override fun asType(d: Double) = Millivolt(d)
    override fun asBaseUnit() = Volt(this.value * 0.001)
}

@JvmInline
value class Microvolt(override val value: Double) : UnitPotential<Microvolt> {
    override fun asType(d: Double) = Microvolt(d)
    override fun asBaseUnit() = Volt(this.value * 1E-6)
}

@JvmInline
value class Kilovolt(override val value: Double) : UnitPotential<Kilovolt> {
    override fun asType(d: Double) = Kilovolt(d)
    override fun asBaseUnit() = Volt(this.value * 1000.0)
}

@JvmInline
value class Megavolt(override val value: Double) : UnitPotential<Megavolt> {
    override fun asType(d: Double) = Megavolt(d)
    override fun asBaseUnit() = Volt(this.value * 1E6)
}

@JvmInline
value class Gigavolt(override val value: Double) : UnitPotential<Gigavolt> {
    override fun asType(d: Double) = Gigavolt(d)
    override fun asBaseUnit() = Volt(this.value * 1E9)
}

operator fun UnitPotentialType<*>.plus(other: UnitPotentialType<*>): Volt =
    Volt(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitPotentialType<*>.minus(other: UnitPotentialType<*>): Volt =
    Volt(this.asBaseUnit().value - other.asBaseUnit().value)

// Conversion functions using toUnit
fun UnitPotential<*>.toVolt() = this.asBaseUnit()
fun UnitPotential<*>.toMillivolt() = toUnit(Millivolt(1.0))
fun UnitPotential<*>.toMicrovolt() = toUnit(Microvolt(1.0))
fun UnitPotential<*>.toKilovolt() = toUnit(Kilovolt(1.0))
fun UnitPotential<*>.toMegavolt() = toUnit(Megavolt(1.0))
fun UnitPotential<*>.toGigavolt() = toUnit(Gigavolt(1.0))

