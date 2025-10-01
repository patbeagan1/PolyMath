package com.measures.capacitance

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitCapacitance<T : DoubleBase> : UnitType<T, Farad> {
    operator fun plus(other: UnitCapacitance<*>): Farad
    operator fun minus(other: UnitCapacitance<*>): Farad
}

fun UnitCapacitance<*>.plusUnit(other: UnitCapacitance<*>): Farad =
    Farad(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitCapacitance<*>.minusUnit(other: UnitCapacitance<*>): Farad =
    Farad(this.asBaseUnit().value - other.asBaseUnit().value)
