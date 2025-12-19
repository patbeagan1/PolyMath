package com.measures.temperature.non_si

import com.measures.temperature.Kelvin
import com.measures.temperature.UnitTemperature
import kotlin.jvm.JvmInline

@JvmInline
value class Celsius(override val value: Double) : UnitTemperature<Celsius> {
    override fun asType(d: Double) = Celsius(d)
    override fun asBaseUnit() = Kelvin(value + 273.15)

    operator fun plus(other: UnitTemperature<*>) = UnitTemperature.plusUnit(this, other)
    operator fun minus(other: UnitTemperature<*>) = UnitTemperature.minusUnit(this, other)
}

fun UnitTemperature<*>.toCelsius(): Celsius {
    val kelvin = this.asBaseUnit().value
    return Celsius(kelvin - 273.15)
}
