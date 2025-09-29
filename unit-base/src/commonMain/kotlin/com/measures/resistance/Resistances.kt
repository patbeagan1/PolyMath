package com.measures.resistance

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.weight.UnitWeight
import com.measures.current.UnitCurrent
import com.measures.distance.UnitDistance
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

typealias UnitResistance<T> = UnitResistanceType<T>

interface UnitResistanceType<T : DoubleBase> : UnitType<T, Ohm>

@JvmInline
value class Ohm(override val value: Double) : UnitResistance<Ohm>, BaseUnit {
    override fun asType(d: Double) = Ohm(d)
    override fun asBaseUnit() = this
    
    companion object {
        fun from(mass: UnitWeight<*>, distance: UnitDistance<*>, current: UnitCurrent<*>, time: UnitTime<*>): Ohm {
            val massBase = mass.asBaseUnit()
            val distanceBase = distance.asBaseUnit()
            val currentBase = current.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Ohm(massBase.value * distanceBase.value * distanceBase.value / (currentBase.value * currentBase.value * timeBase.value * timeBase.value * timeBase.value))
        }
    }
}

operator fun UnitResistanceType<*>.plus(other: UnitResistanceType<*>): Ohm =
    Ohm(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitResistanceType<*>.minus(other: UnitResistanceType<*>): Ohm =
    Ohm(this.asBaseUnit().value - other.asBaseUnit().value)

fun UnitResistance<*>.toOhm() = this.asBaseUnit()

