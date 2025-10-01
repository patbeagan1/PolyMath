package com.measures.solidangle

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitSolidAngle<T : DoubleBase> : UnitType<T, Steradian>{
    operator fun plus(other: UnitSolidAngle<*>): Steradian
    operator fun minus(other: UnitSolidAngle<*>): Steradian
}

fun UnitSolidAngle<*>.plusUnit(other: UnitSolidAngle<*>): Steradian =
    Steradian(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitSolidAngle<*>.minusUnit(other: UnitSolidAngle<*>): Steradian =
    Steradian(this.asBaseUnit().value - other.asBaseUnit().value)
