package com.measures.power

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.distance.UnitDistance
import com.measures.energy.Joule
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

typealias UnitPower<T> = UnitPowerType<T>

interface UnitPowerType<T : DoubleBase> : UnitType<T, Watt> {
    operator fun plus(other: UnitPower<*>): Watt
    operator fun minus(other: UnitPower<*>): Watt
    operator fun times(other: UnitTime<*>): Joule
}

@JvmInline
value class Watt(override val value: Double) : UnitPower<Watt>, BaseUnit {
    override fun asType(d: Double) = Watt(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plusUnit(other)
    override operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = (this as UnitPower<*>).timesUnit(other)

    companion object {
        fun from(mass: UnitMass<*>, distance: UnitDistance<*>, time: UnitTime<*>): Watt =
            Joule.from(mass, distance, time) / time
    }
}

fun UnitPowerType<*>.plusUnit(other: UnitPowerType<*>): Watt =
    Watt(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitPowerType<*>.minusUnit(other: UnitPowerType<*>): Watt =
    Watt(this.asBaseUnit().value - other.asBaseUnit().value)

// Power × Time = Energy
fun UnitPowerType<*>.timesUnit(other: UnitTime<*>): Joule =
    Joule(this.asBaseUnit().value * other.asBaseUnit().value)

// Non-SI power units have been moved to unit-common module

// Conversion functions using toUnit
fun UnitPower<*>.toWatt() = this.asBaseUnit()

