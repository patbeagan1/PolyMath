package com.measures.flux

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.weight.UnitWeight
import com.measures.current.UnitCurrent
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

typealias UnitFlux<T> = UnitFluxType<T>

interface UnitFluxType<T : DoubleBase> : UnitType<T, Weber>

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

operator fun UnitFluxType<*>.plus(other: UnitFluxType<*>): Weber =
    Weber(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitFluxType<*>.minus(other: UnitFluxType<*>): Weber =
    Weber(this.asBaseUnit().value - other.asBaseUnit().value)

fun UnitFlux<*>.toWeber() = this.asBaseUnit()

