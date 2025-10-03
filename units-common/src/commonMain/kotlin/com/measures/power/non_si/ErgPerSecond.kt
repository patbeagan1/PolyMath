package com.measures.power.non_si

import com.measures.power.UnitPower
import com.measures.power.Watt
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class ErgPerSecond(override val value: Double) : UnitPower<ErgPerSecond> {
    override fun asType(d: Double) = ErgPerSecond(d)
    override fun asBaseUnit() = Watt(this.value * 1E-7)

    override operator fun plus(other: UnitPower<*>) = UnitPower.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitPower<*>) = UnitPower.Companion.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitPower.Companion.timesUnit(this, other)
}

fun UnitPower<*>.toErgPerSecond() = toUnit(ErgPerSecond(1.0))
