package com.measures.power.non_si

import com.measures.power.Watt
import com.measures.power.UnitPower
import com.measures.time.UnitTime
import com.measures.energy.UnitEnergy
import kotlin.jvm.JvmInline

@JvmInline
value class ErgPerSecond(override val value: Double) : UnitPower<ErgPerSecond> {
    override fun asType(d: Double) = ErgPerSecond(d)
    override fun asBaseUnit() = Watt(value * 1e-7)

    override operator fun plus(other: UnitPower<*>) = UnitPower.plusUnit(this, other)
    override operator fun minus(other: UnitPower<*>) = UnitPower.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitPower.timesUnit(this, other)
}

fun UnitPower<*>.toErgPerSecond() = toUnit(ErgPerSecond(1.0))
