package com.measures.angle

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitAngle<T : DoubleBase> : UnitType<T, Radian> {
    operator fun plus(other: UnitAngle<*>): Radian
    operator fun minus(other: UnitAngle<*>): Radian

    companion object {
        fun plusUnit(angle: UnitAngle<*>, other: UnitAngle<*>): Radian =
            Radian(angle.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(angle: UnitAngle<*>, other: UnitAngle<*>): Radian =
            Radian(angle.asBaseUnit().value - other.asBaseUnit().value)
    }
}
