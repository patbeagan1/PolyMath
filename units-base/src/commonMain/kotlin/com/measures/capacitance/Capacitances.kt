package com.measures.capacitance

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitCapacitance<T : DoubleBase> : UnitType<T, Farad> {
    operator fun plus(other: UnitCapacitance<*>): Farad
    operator fun minus(other: UnitCapacitance<*>): Farad

    companion object {
        fun plusUnit(capacitance: UnitCapacitance<*>, other: UnitCapacitance<*>): Farad =
            Farad(capacitance.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(capacitance: UnitCapacitance<*>, other: UnitCapacitance<*>): Farad =
            Farad(capacitance.asBaseUnit().value - other.asBaseUnit().value)
    }
}
