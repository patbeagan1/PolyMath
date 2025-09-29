package com.measures.inductance

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.weight.UnitWeight
import com.measures.current.UnitCurrent
import com.measures.distance.UnitDistance
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

typealias UnitInductance<T> = UnitInductanceType<T>

interface UnitInductanceType<T : DoubleBase> : UnitType<T, Henry>

@JvmInline
value class Henry(override val value: Double) : UnitInductance<Henry>, BaseUnit {
    override fun asType(d: Double) = Henry(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitInductance<*>) = (this as UnitInductance<*>).plus(other)
    operator fun minus(other: UnitInductance<*>) = (this as UnitInductance<*>).minus(other)
    
    companion object {
        fun from(mass: UnitWeight<*>, distance: UnitDistance<*>, current: UnitCurrent<*>, time: UnitTime<*>): Henry {
            val massBase = mass.asBaseUnit()
            val distanceBase = distance.asBaseUnit()
            val currentBase = current.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Henry(massBase.value * distanceBase.value * distanceBase.value / (currentBase.value * currentBase.value * timeBase.value * timeBase.value))
        }
    }
}

operator fun UnitInductanceType<*>.plus(other: UnitInductanceType<*>): Henry =
    Henry(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitInductanceType<*>.minus(other: UnitInductanceType<*>): Henry =
    Henry(this.asBaseUnit().value - other.asBaseUnit().value)

fun UnitInductance<*>.toHenry() = this.asBaseUnit()

