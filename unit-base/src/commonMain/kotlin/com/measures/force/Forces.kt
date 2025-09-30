package com.measures.force

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.acceleration.MetersPerSecondPerSecond
import com.measures.acceleration.UnitAcceleration
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.energy.Joule
import com.measures.pressure.Pascal
import com.measures.weight.KiloGram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

interface UnitForce<T : DoubleBase> : UnitType<T, Newton> {
    operator fun plus(other: UnitForce<*>): Newton
    operator fun minus(other: UnitForce<*>): Newton
    operator fun div(other: UnitArea<*>): Pascal
    operator fun times(other: UnitDistance<*>): Joule
    operator fun div(other: UnitMass<*>): MetersPerSecondPerSecond
    operator fun div(other: UnitAcceleration<*>): KiloGram
}

@JvmInline
value class Newton(override val value: Double) : UnitForce<Newton>, BaseUnit {
    override fun asType(d: Double) = Newton(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitForce<*>) = (this as UnitForce<*>).plusUnit(other)
    override operator fun minus(other: UnitForce<*>) = (this as UnitForce<*>).minusUnit(other)
    override operator fun div(other: UnitArea<*>) = (this as UnitForce<*>).divUnit(other)
    override operator fun times(other: UnitDistance<*>): Joule = (this as UnitForce<*>).timesUnit(other)
    override operator fun div(other: UnitMass<*>) = (this as UnitForce<*>).divUnit(other)
    override operator fun div(other: UnitAcceleration<*>) = (this as UnitForce<*>).divUnit(other)

    companion object {
        fun from(mass: UnitMass<*>, acceleration: UnitAcceleration<*>): Newton = mass * acceleration
    }
}

// Non-SI force units have been moved to units-common module

@JvmInline
value class Kilonewton(override val value: Double) : UnitForce<Kilonewton> {
    override fun asType(d: Double) = Kilonewton(d)
    override fun asBaseUnit() = Newton(this.value * 1000.0)

    override operator fun plus(other: UnitForce<*>) = (this as UnitForce<*>).plusUnit(other)
    override operator fun minus(other: UnitForce<*>) = (this as UnitForce<*>).minusUnit(other)
    override operator fun div(other: UnitArea<*>) = (this as UnitForce<*>).divUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitForce<*>).timesUnit(other)
    override operator fun div(other: UnitMass<*>) = (this as UnitForce<*>).divUnit(other)
    override operator fun div(other: UnitAcceleration<*>) = (this as UnitForce<*>).divUnit(other)
}

@JvmInline
value class Meganewton(override val value: Double) : UnitForce<Meganewton> {
    override fun asType(d: Double) = Meganewton(d)
    override fun asBaseUnit() = Newton(this.value * 1E6)

    override operator fun plus(other: UnitForce<*>) = (this as UnitForce<*>).plusUnit(other)
    override operator fun minus(other: UnitForce<*>) = (this as UnitForce<*>).minusUnit(other)
    override operator fun div(other: UnitArea<*>) = (this as UnitForce<*>).divUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitForce<*>).timesUnit(other)
    override operator fun div(other: UnitMass<*>) = (this as UnitForce<*>).divUnit(other)
    override operator fun div(other: UnitAcceleration<*>) = (this as UnitForce<*>).divUnit(other)
}

fun UnitForce<*>.plusUnit(other: UnitForce<*>): Newton =
    Newton(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitForce<*>.minusUnit(other: UnitForce<*>): Newton =
    Newton(this.asBaseUnit().value - other.asBaseUnit().value)

fun UnitForce<*>.divUnit(other: UnitArea<*>): Pascal =
    Pascal(this.asBaseUnit().value / other.asBaseUnit().value)

// Force × Distance = Energy
fun UnitForce<*>.timesUnit(other: UnitDistance<*>): Joule =
    Joule(this.asBaseUnit().value * other.asBaseUnit().value)

// Force ÷ Mass = Acceleration
fun UnitForce<*>.divUnit(other: UnitMass<*>): MetersPerSecondPerSecond =
    MetersPerSecondPerSecond(this.asBaseUnit().value / other.asBaseUnit().value)

// Force ÷ Acceleration = Mass
fun UnitForce<*>.divUnit(other: UnitAcceleration<*>): KiloGram =
    KiloGram(this.asBaseUnit().value / other.asBaseUnit().value)

// Conversion functions using toUnit
fun UnitForce<*>.toNewton() = this.asBaseUnit()
fun UnitForce<*>.toKilonewton() = toUnit(Kilonewton(1.0))
fun UnitForce<*>.toMeganewton() = toUnit(Meganewton(1.0))

