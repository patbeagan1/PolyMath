package com.measures.luminous

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitLuminous<T : DoubleBase> : UnitType<T, Candela> {
    operator fun plus(other: UnitLuminous<*>): Candela
    operator fun minus(other: UnitLuminous<*>): Candela

    companion object {
        fun plusUnit(luminous: UnitLuminous<*>, other: UnitLuminous<*>): Candela =
            Candela(luminous.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(luminous: UnitLuminous<*>, other: UnitLuminous<*>): Candela =
            Candela(luminous.asBaseUnit().value - other.asBaseUnit().value)
    }
}
