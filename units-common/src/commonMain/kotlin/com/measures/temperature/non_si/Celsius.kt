package com.measures.temperature.non_si

import com.measures.temperature.UnitTemperature
import kotlin.jvm.JvmInline

@JvmInline
value class Celsius(override val value: Double) : UnitTemperature<Celsius> {
override fun asType(d: Double) = Celsius(d)
    override fun asBaseUnit() = com.measures.temperature.Kelvin(this.value + 273.15)

    operator fun plus(other: UnitTemperature<*>) = UnitTemperature.Companion.plusUnit(this, other)
    operator fun minus(other: UnitTemperature<*>) = UnitTemperature.Companion.minusUnit(this, other)
}

fun UnitTemperature<*>.toCelsius() = toUnit(Celsius(1.0))
