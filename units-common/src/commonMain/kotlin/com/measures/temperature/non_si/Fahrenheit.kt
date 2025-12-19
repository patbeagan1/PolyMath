package com.measures.temperature.non_si

import com.measures.temperature.Kelvin
import com.measures.temperature.UnitTemperature
import kotlin.jvm.JvmInline

@JvmInline
value class Fahrenheit(override val value: Double) : UnitTemperature<Fahrenheit> {
    override fun asType(d: Double) = Fahrenheit(d)
    override fun asBaseUnit() = Kelvin((value - 32.0) / 1.8 + 273.15)

    operator fun plus(other: UnitTemperature<*>) = UnitTemperature.plusUnit(this, other)
    operator fun minus(other: UnitTemperature<*>) = UnitTemperature.minusUnit(this, other)
}

fun UnitTemperature<*>.toFahrenheit(): Fahrenheit {
    val kelvin = this.asBaseUnit().value
    return Fahrenheit((kelvin - 273.15) * 1.8 + 32.0)
}
