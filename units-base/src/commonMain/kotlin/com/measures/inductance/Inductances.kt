package com.measures.inductance

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitInductance<T : DoubleBase> : UnitType<T, Henry> {
    operator fun plus(other: UnitInductance<*>): Henry
    operator fun minus(other: UnitInductance<*>): Henry

    companion object {
        fun plusUnit(inductance: UnitInductance<*>, other: UnitInductance<*>): Henry =
            Henry(inductance.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(inductance: UnitInductance<*>, other: UnitInductance<*>): Henry =
            Henry(inductance.asBaseUnit().value - other.asBaseUnit().value)
    }
}
