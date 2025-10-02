package com.measures.inductance

import com.measures.BaseUnit
import com.measures.current.UnitCurrent
import com.measures.distance.UnitDistance
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class Henry(override val value: Double) : UnitInductance<Henry>, BaseUnit {
    override fun asType(d: Double) = Henry(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitInductance<*>) = UnitInductance.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitInductance<*>) = UnitInductance.Companion.minusUnit(this, other)

    companion object {
        fun from(mass: UnitMass<*>, distance: UnitDistance<*>, current: UnitCurrent<*>, time: UnitTime<*>): Henry {
            val massBase = mass.asBaseUnit()
            val distanceBase = distance.asBaseUnit()
            val currentBase = current.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Henry(massBase.value * distanceBase.value * distanceBase.value / (currentBase.value * currentBase.value * timeBase.value * timeBase.value))
        }
    }
}

fun UnitInductance<*>.toHenry() = this.asBaseUnit()
