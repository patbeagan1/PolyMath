package com.measures.flux

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitTypedFull
import com.measures.weight.UnitWeight
import com.measures.distance.UnitDistance
import com.measures.current.UnitCurrent
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

typealias UnitFlux<T> = UnitFluxTypedFull<T>

interface UnitFluxTypedFull<T : DoubleBase> : UnitTypedFull<T, Weber> {
    operator fun plus(other: UnitFluxTypedFull<*>) =
        Weber(this.asBaseUnit().value + other.asBaseUnit().value)

    operator fun minus(other: UnitFluxTypedFull<*>) =
        Weber(this.asBaseUnit().value - other.asBaseUnit().value)
}

@JvmInline
value class Weber(override val value: Double) : UnitFlux<Weber>, BaseUnit {
    override fun asType(d: Double) = Weber(d)
    override fun asBaseUnit() = this
    
    companion object {
        fun from(mass: UnitWeight<*>, distance: UnitDistance<*>, current: UnitCurrent<*>, time: UnitTime<*>): Weber {
            val massBase = mass.asBaseUnit()
            val distanceBase = distance.asBaseUnit()
            val currentBase = current.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Weber(massBase.value * distanceBase.value * distanceBase.value / (currentBase.value * timeBase.value * timeBase.value))
        }
    }
}

fun UnitFlux<*>.toWeber() = this.asBaseUnit()

