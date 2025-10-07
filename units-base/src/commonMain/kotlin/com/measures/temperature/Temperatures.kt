package com.measures.temperature

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitTemperature<T : DoubleBase> : UnitType<T, Kelvin> {
    companion object {
        fun plusUnit(temperature: UnitTemperature<*>, other: UnitTemperature<*>): Kelvin =
            Kelvin(temperature.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(temperature: UnitTemperature<*>, other: UnitTemperature<*>): Kelvin =
            Kelvin(temperature.asBaseUnit().value - other.asBaseUnit().value)
    }
}
