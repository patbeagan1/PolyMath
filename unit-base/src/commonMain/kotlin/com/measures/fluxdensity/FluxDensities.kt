package com.measures.fluxdensity

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitTypedFull
import com.measures.weight.UnitWeight
import com.measures.current.UnitCurrent
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

typealias UnitFluxDensity<T> = UnitFluxDensityTypedFull<T>

interface UnitFluxDensityTypedFull<T : DoubleBase> : UnitTypedFull<T, Tesla> {
    operator fun plus(other: UnitFluxDensityTypedFull<*>) =
        Tesla(this.asBaseUnit().value + other.asBaseUnit().value)

    operator fun minus(other: UnitFluxDensityTypedFull<*>) =
        Tesla(this.asBaseUnit().value - other.asBaseUnit().value)
}

@JvmInline
value class Tesla(override val value: Double) : UnitFluxDensity<Tesla>, BaseUnit {
    override fun asType(d: Double) = Tesla(d)
    override fun asBaseUnit() = this
    
    companion object {
        fun from(mass: UnitWeight<*>, current: UnitCurrent<*>, time: UnitTime<*>): Tesla {
            val massBase = mass.asBaseUnit()
            val currentBase = current.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Tesla(massBase.value / (currentBase.value * timeBase.value * timeBase.value))
        }
    }
}

fun UnitFluxDensity<*>.toTesla() = this.asBaseUnit()

