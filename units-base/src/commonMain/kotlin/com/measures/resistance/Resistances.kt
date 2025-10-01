package com.measures.resistance

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitResistance<T : DoubleBase> : UnitType<T, Ohm> {
    operator fun plus(other: UnitResistance<*>): Ohm
    operator fun minus(other: UnitResistance<*>): Ohm
}

 fun UnitResistance<*>.plusUnit(other: UnitResistance<*>): Ohm =
    Ohm(this.asBaseUnit().value + other.asBaseUnit().value)

 fun UnitResistance<*>.minusUnit(other: UnitResistance<*>): Ohm =
    Ohm(this.asBaseUnit().value - other.asBaseUnit().value)
