package com.measures.fluxdensity

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.weight.UnitMass
import com.measures.current.UnitCurrent
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

typealias UnitFluxDensity<T> = UnitFluxDensityType<T>

interface UnitFluxDensityType<T : DoubleBase> : UnitType<T, Tesla>

@JvmInline
value class Tesla(override val value: Double) : UnitFluxDensity<Tesla>, BaseUnit {
    override fun asType(d: Double) = Tesla(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitFluxDensity<*>) = (this as UnitFluxDensity<*>).plusUnit(other)
    operator fun minus(other: UnitFluxDensity<*>) = (this as UnitFluxDensity<*>).minusUnit(other)
    
    companion object {
        fun from(mass: UnitMass<*>, current: UnitCurrent<*>, time: UnitTime<*>): Tesla {
            val massBase = mass.asBaseUnit()
            val currentBase = current.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Tesla(massBase.value / (currentBase.value * timeBase.value * timeBase.value))
        }
    }
}

 fun UnitFluxDensityType<*>.plusUnit(other: UnitFluxDensityType<*>): Tesla =
    Tesla(this.asBaseUnit().value + other.asBaseUnit().value)

 fun UnitFluxDensityType<*>.minusUnit(other: UnitFluxDensityType<*>): Tesla =
    Tesla(this.asBaseUnit().value - other.asBaseUnit().value)

fun UnitFluxDensity<*>.toTesla() = this.asBaseUnit()

