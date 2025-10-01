package com.measures.inductance

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitInductance<T : DoubleBase> : UnitType<T, Henry> {
    operator fun plus(other: UnitInductance<*>): Henry
    operator fun minus(other: UnitInductance<*>): Henry
}

fun UnitInductance<*>.plusUnit(other: UnitInductance<*>): Henry =
    Henry(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitInductance<*>.minusUnit(other: UnitInductance<*>): Henry =
    Henry(this.asBaseUnit().value - other.asBaseUnit().value)
