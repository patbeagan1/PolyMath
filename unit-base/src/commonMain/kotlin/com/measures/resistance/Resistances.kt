package com.measures.resistance

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitTypedFull
import com.measures.weight.UnitWeight
import com.measures.distance.UnitDistance
import com.measures.current.UnitCurrent
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

typealias UnitResistance<T> = UnitResistanceTypedFull<T>

interface UnitResistanceTypedFull<T : DoubleBase> : UnitTypedFull<T, Ohm> {
    operator fun plus(other: UnitResistanceTypedFull<*>) =
        Ohm(this.asBaseUnit().value + other.asBaseUnit().value)

    operator fun minus(other: UnitResistanceTypedFull<*>) =
        Ohm(this.asBaseUnit().value - other.asBaseUnit().value)
}

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

fun UnitResistance<*>.toOhm() = this.asBaseUnit()

