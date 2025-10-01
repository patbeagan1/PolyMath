package com.measures.amount

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitAmount<T : DoubleBase> : UnitType<T, Mole> {
    operator fun plus(other: UnitAmount<*>): Mole
    operator fun minus(other: UnitAmount<*>): Mole
}

fun UnitAmount<*>.plusUnit(other: UnitAmount<*>): Mole =
    Mole(this.asBaseUnit().value + other.asBaseUnit().value)

 fun UnitAmount<*>.minusUnit(other: UnitAmount<*>): Mole =
    Mole(this.asBaseUnit().value - other.asBaseUnit().value)
