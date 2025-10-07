package com.measures.power.non_si

import com.measures.power.Watt
import com.measures.power.UnitPower
import com.measures.time.UnitTime
import com.measures.energy.UnitEnergy
import kotlin.jvm.JvmInline

@JvmInline
value class Horsepower(override val value: Double) : UnitPower<Horsepower> {
    override fun asType(d: Double) = Horsepower(d)
    override fun asBaseUnit() = Watt(value * 745.6998715822702)

    override operator fun plus(other: UnitPower<*>) = UnitPower.plusUnit(this, other)
    override operator fun minus(other: UnitPower<*>) = UnitPower.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitPower.timesUnit(this, other)
}

fun UnitPower<*>.toHorsepower() = toUnit(Horsepower(1.0))
