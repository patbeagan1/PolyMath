package com.measures.pressure

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.area.UnitArea
import com.measures.force.Newton

interface UnitPressure<T : DoubleBase> : UnitType<T, Pascal>{
    operator fun plus(other: UnitPressure<*>): Pascal
    operator fun minus(other: UnitPressure<*>): Pascal
    operator fun times(other: UnitArea<*>): Newton
}

fun UnitPressure<*>.plusUnit(other: UnitPressure<*>): Pascal =
    Pascal(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitPressure<*>.minusUnit(other: UnitPressure<*>): Pascal =
    Pascal(this.asBaseUnit().value - other.asBaseUnit().value)

fun UnitPressure<*>.timesUnit(other: UnitArea<*>): Newton =
    Newton(this.asBaseUnit().value * other.asBaseUnit().value)
