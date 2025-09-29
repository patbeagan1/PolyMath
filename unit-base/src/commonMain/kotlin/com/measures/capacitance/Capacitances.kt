package com.measures.capacitance

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.current.UnitCurrent
import com.measures.time.UnitTime
import com.measures.weight.UnitWeight
import com.measures.distance.UnitDistance
import kotlin.jvm.JvmInline

typealias UnitCapacitance<T> = UnitCapacitanceType<T>

interface UnitCapacitanceType<T : DoubleBase> : UnitType<T, Farad>

@JvmInline
value class Farad(override val value: Double) : UnitCapacitance<Farad>, BaseUnit {
    override fun asType(d: Double) = Farad(d)
    override fun asBaseUnit() = this
    
    companion object {
        fun from(current: UnitCurrent<*>, time: UnitTime<*>, mass: UnitWeight<*>, distance: UnitDistance<*>): Farad {
            val currentBase = current.asBaseUnit()
            val timeBase = time.asBaseUnit()
            val massBase = mass.asBaseUnit()
            val distanceBase = distance.asBaseUnit()
            return Farad(currentBase.value * currentBase.value * timeBase.value * timeBase.value * timeBase.value * timeBase.value / (massBase.value * distanceBase.value * distanceBase.value))
        }
    }
}

operator fun UnitCapacitanceType<*>.plus(other: UnitCapacitanceType<*>): Farad =
    Farad(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitCapacitanceType<*>.minus(other: UnitCapacitanceType<*>): Farad =
    Farad(this.asBaseUnit().value - other.asBaseUnit().value)

fun UnitCapacitance<*>.toFarad() = this.asBaseUnit()
