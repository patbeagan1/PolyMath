package com.measures.power.non_si

import com.measures.power.Watt
import com.measures.power.UnitPower
import com.measures.time.UnitTime
import com.measures.energy.UnitEnergy
import kotlin.jvm.JvmInline

@JvmInline
value class FootPoundPerSecond(override val value: Double) : UnitPower<FootPoundPerSecond> {
    override fun asType(d: Double) = FootPoundPerSecond(d)
    override fun asBaseUnit() = Watt(value * 1.3558179483314004)

    override operator fun plus(other: UnitPower<*>) = UnitPower.plusUnit(this, other)
    override operator fun minus(other: UnitPower<*>) = UnitPower.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitPower.timesUnit(this, other)
}

fun UnitPower<*>.toFootPoundPerSecond() = toUnit(FootPoundPerSecond(1.0))
