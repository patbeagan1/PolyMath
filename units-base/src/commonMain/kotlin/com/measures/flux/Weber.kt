package com.measures.flux

import com.measures.BaseUnit
import com.measures.current.UnitCurrent
import com.measures.distance.UnitDistance
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class Weber(override val value: Double) : UnitFlux<Weber>, BaseUnit {
    override fun asType(d: Double) = Weber(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitFlux<*>) = UnitFlux.plusUnit(this, other)
    override operator fun minus(other: UnitFlux<*>) = UnitFlux.minusUnit(this, other)

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

fun UnitFlux<*>.toWeber() = this.asBaseUnit()
