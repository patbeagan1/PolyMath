package com.measures.fluxdensity

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitFluxDensity<T : DoubleBase> : UnitType<T, Tesla> {
    operator fun plus(other: UnitFluxDensity<*>): Tesla
    operator fun minus(other: UnitFluxDensity<*>): Tesla
}

fun UnitFluxDensity<*>.plusUnit(other: UnitFluxDensity<*>): Tesla =
    Tesla(this.asBaseUnit().value + other.asBaseUnit().value)

 fun UnitFluxDensity<*>.minusUnit(other: UnitFluxDensity<*>): Tesla =
    Tesla(this.asBaseUnit().value - other.asBaseUnit().value)
