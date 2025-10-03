package com.measures.temperature.non_si

import com.measures.temperature.UnitTemperature
import kotlin.jvm.JvmInline

@JvmInline
value class Rankine(override val value: Double) : UnitTemperature<Rankine> {
    override fun asType(d: Double) = Rankine(d)
    override fun asBaseUnit() = com.measures.temperature.Kelvin(this.value * 5.0 / 9.0)

    operator fun plus(other: UnitTemperature<*>) = UnitTemperature.Companion.plusUnit(this, other)
    operator fun minus(other: UnitTemperature<*>) = UnitTemperature.Companion.minusUnit(this, other)
}

fun UnitTemperature<*>.toRankine() = toUnit(Rankine(1.0))
