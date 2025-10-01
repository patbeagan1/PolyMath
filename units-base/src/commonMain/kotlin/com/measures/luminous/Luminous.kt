package com.measures.luminous

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitLuminous<T : DoubleBase> : UnitType<T, Candela> {
    operator fun plus(other: UnitLuminous<*>): Candela
    operator fun minus(other: UnitLuminous<*>): Candela
}

fun UnitLuminous<*>.plusUnit(other: UnitLuminous<*>): Candela =
    Candela(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitLuminous<*>.minusUnit(other: UnitLuminous<*>): Candela =
    Candela(this.asBaseUnit().value - other.asBaseUnit().value)
