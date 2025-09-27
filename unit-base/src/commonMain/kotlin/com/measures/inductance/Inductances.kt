package com.measures.inductance

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitTypedFull
import com.measures.weight.UnitWeight
import com.measures.distance.UnitDistance
import com.measures.current.UnitCurrent
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

typealias UnitInductance<T> = UnitInductanceTypedFull<T>

interface UnitInductanceTypedFull<T : DoubleBase> : UnitTypedFull<T, Henry> {
    operator fun plus(other: UnitInductanceTypedFull<*>) =
        Henry(this.asBaseUnit().value + other.asBaseUnit().value)

    operator fun minus(other: UnitInductanceTypedFull<*>) =
        Henry(this.asBaseUnit().value - other.asBaseUnit().value)
}

@JvmInline
value class Henry(override val value: Double) : UnitInductance<Henry>, BaseUnit {
    override fun asType(d: Double) = Henry(d)
    override fun asBaseUnit() = this
    
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

fun UnitInductance<*>.toHenry() = this.asBaseUnit()

