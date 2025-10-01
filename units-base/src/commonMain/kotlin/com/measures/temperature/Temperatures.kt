package com.measures.temperature

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitTemperature<T : DoubleBase> : UnitType<T, Kelvin>

fun UnitTemperature<*>.plusUnit(other: UnitTemperature<*>): Kelvin =
    Kelvin(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitTemperature<*>.minusUnit(other: UnitTemperature<*>): Kelvin =
    Kelvin(this.asBaseUnit().value - other.asBaseUnit().value)
