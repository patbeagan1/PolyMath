package com.measures.resistance

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitResistance<T : DoubleBase> : UnitType<T, Ohm> {
    operator fun plus(other: UnitResistance<*>): Ohm
    operator fun minus(other: UnitResistance<*>): Ohm

    companion object {
        fun plusUnit(resistance: UnitResistance<*>, other: UnitResistance<*>): Ohm =
            Ohm(resistance.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(resistance: UnitResistance<*>, other: UnitResistance<*>): Ohm =
            Ohm(resistance.asBaseUnit().value - other.asBaseUnit().value)
    }
}
