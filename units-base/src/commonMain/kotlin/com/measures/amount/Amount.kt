package com.measures.amount

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitAmount<T : DoubleBase> : UnitType<T, Mole> {
    operator fun plus(other: UnitAmount<*>): Mole
    operator fun minus(other: UnitAmount<*>): Mole

    companion object {
        fun plusUnit(amount: UnitAmount<*>, other: UnitAmount<*>): Mole =
            Mole(amount.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(amount: UnitAmount<*>, other: UnitAmount<*>): Mole =
            Mole(amount.asBaseUnit().value - other.asBaseUnit().value)
    }
}
