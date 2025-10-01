package com.measures.angle

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitAngle<T : DoubleBase> : UnitType<T, Radian> {
    operator fun plus(other: UnitAngle<*>): Radian
    operator fun minus(other: UnitAngle<*>): Radian
}

fun UnitAngle<*>.plusUnit(other: UnitAngle<*>): Radian =
    Radian(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitAngle<*>.minusUnit(other: UnitAngle<*>): Radian =
    Radian(this.asBaseUnit().value - other.asBaseUnit().value)
