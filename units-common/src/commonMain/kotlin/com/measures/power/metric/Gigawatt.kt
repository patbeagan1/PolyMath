package com.measures.power.metric

import com.measures.Consts
import com.measures.power.Watt
import com.measures.power.UnitPower
import com.measures.time.UnitTime
import com.measures.energy.UnitEnergy
import kotlin.jvm.JvmInline

@JvmInline
value class Gigawatt(override val value: Double) : UnitPower<Gigawatt> {
    override fun asType(d: Double) = Gigawatt(d)
    override fun asBaseUnit() = Watt(value * Consts.GIGA)

    override operator fun plus(other: UnitPower<*>) = UnitPower.plusUnit(this, other)
    override operator fun minus(other: UnitPower<*>) = UnitPower.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitPower.timesUnit(this, other)
}

fun UnitPower<*>.toGigawatt() = toUnit(Gigawatt(1.0))
