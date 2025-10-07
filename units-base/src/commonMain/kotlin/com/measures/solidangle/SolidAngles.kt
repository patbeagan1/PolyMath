package com.measures.solidangle

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitSolidAngle<T : DoubleBase> : UnitType<T, Steradian>{
    operator fun plus(other: UnitSolidAngle<*>): Steradian
    operator fun minus(other: UnitSolidAngle<*>): Steradian

    companion object {
        fun plusUnit(solidAngle: UnitSolidAngle<*>, other: UnitSolidAngle<*>): Steradian =
            Steradian(solidAngle.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(solidAngle: UnitSolidAngle<*>, other: UnitSolidAngle<*>): Steradian =
            Steradian(solidAngle.asBaseUnit().value - other.asBaseUnit().value)
    }
}
