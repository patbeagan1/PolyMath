package com.measures.power.metric

import com.measures.Consts
import com.measures.power.Watt
import com.measures.power.UnitPower
import com.measures.time.UnitTime
import com.measures.energy.UnitEnergy
import kotlin.jvm.JvmInline

@JvmInline
value class Hectowatt(override val value: Double) : UnitPower<Hectowatt> {
    override fun asType(d: Double) = Hectowatt(d)
    override fun asBaseUnit() = Watt(value * Consts.HECTO)

    override operator fun plus(other: UnitPower<*>) = UnitPower.plusUnit(this, other)
    override operator fun minus(other: UnitPower<*>) = UnitPower.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitPower.timesUnit(this, other)
}

fun UnitPower<*>.toHectowatt() = toUnit(Hectowatt(1.0))
