package com.measures.temperature.non_si

import com.measures.temperature.UnitTemperature
import kotlin.jvm.JvmInline

@JvmInline
value class Fahrenheit(override val value: Double) : UnitTemperature<Fahrenheit> {
override fun asType(d: Double) = Fahrenheit(d)
    override fun asBaseUnit() = com.measures.temperature.Kelvin((this.value - 32.0) * 5.0 / 9.0 + 273.15)

    operator fun plus(other: UnitTemperature<*>) = UnitTemperature.Companion.plusUnit(this, other)
    operator fun minus(other: UnitTemperature<*>) = UnitTemperature.Companion.minusUnit(this, other)
}

fun UnitTemperature<*>.toFahrenheit() = toUnit(Fahrenheit(1.0))
