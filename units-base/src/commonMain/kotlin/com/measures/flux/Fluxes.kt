package com.measures.flux

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitFlux<T : DoubleBase> : UnitType<T, Weber> {
    operator fun plus(other: UnitFlux<*>): Weber
    operator fun minus(other: UnitFlux<*>): Weber
}

fun UnitFlux<*>.plusUnit(other: UnitFlux<*>): Weber =
    Weber(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitFlux<*>.minusUnit(other: UnitFlux<*>): Weber =
    Weber(this.asBaseUnit().value - other.asBaseUnit().value)
