package com.measures.flux

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.weight.UnitMass
import com.measures.current.UnitCurrent
import com.measures.distance.UnitDistance
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

typealias UnitFlux<T> = UnitFluxType<T>

interface UnitFluxType<T : DoubleBase> : UnitType<T, Weber>

@JvmInline
value class Weber(override val value: Double) : UnitFlux<Weber>, BaseUnit {
    override fun asType(d: Double) = Weber(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitFlux<*>) = (this as UnitFlux<*>).minusUnit(other)
    operator fun minus(other: UnitFlux<*>) = (this as UnitFlux<*>).minusUnit(other)
    
    companion object {
        fun from(mass: UnitMass<*>, distance: UnitDistance<*>, current: UnitCurrent<*>, time: UnitTime<*>): Weber {
            // Flux = Energy / Current
            // Energy = mass × distance² / time²
            // Flux = (mass × distance² / time²) / current = mass × distance² / (current × time²)
            val massValue = mass.asBaseUnit().value
            val distanceValue = distance.asBaseUnit().value
            val currentValue = current.asBaseUnit().value
            val timeValue = time.asBaseUnit().value
            return Weber(massValue * distanceValue * distanceValue / (currentValue * timeValue * timeValue))
        }
    }
}

 fun UnitFluxType<*>.plusUnit(other: UnitFluxType<*>): Weber =
    Weber(this.asBaseUnit().value + other.asBaseUnit().value)

 fun UnitFluxType<*>.minusUnit(other: UnitFluxType<*>): Weber =
    Weber(this.asBaseUnit().value - other.asBaseUnit().value)

fun UnitFlux<*>.toWeber() = this.asBaseUnit()

