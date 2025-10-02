package com.measures.pressure

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.area.UnitArea
import com.measures.force.Newton

interface UnitPressure<T : DoubleBase> : UnitType<T, Pascal>{
    operator fun plus(other: UnitPressure<*>): Pascal
    operator fun minus(other: UnitPressure<*>): Pascal
    operator fun times(other: UnitArea<*>): Newton

    companion object {
        fun plusUnit(pressure: UnitPressure<*>, other: UnitPressure<*>): Pascal =
            Pascal(pressure.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(pressure: UnitPressure<*>, other: UnitPressure<*>): Pascal =
            Pascal(pressure.asBaseUnit().value - other.asBaseUnit().value)

        fun timesUnit(pressure: UnitPressure<*>, other: UnitArea<*>): Newton =
            Newton(pressure.asBaseUnit().value * other.asBaseUnit().value)
    }
}
