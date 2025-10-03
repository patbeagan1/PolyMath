package com.measures.velocity.non_si

import com.measures.time.UnitTime
import com.measures.velocity.UnitVelocity
import kotlin.jvm.JvmInline

@JvmInline
value class CentimetersPerSecond(override val value: Double) : UnitVelocity<CentimetersPerSecond> {
override fun asType(d: Double) = CentimetersPerSecond(d)
    override fun asBaseUnit() = com.measures.velocity.MetersPerSecond(this.value * 0.01)

    override operator fun plus(other: UnitVelocity<*>) = UnitVelocity.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVelocity<*>) = UnitVelocity.Companion.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitVelocity.Companion.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitVelocity.Companion.divUnit(this, other)
}

fun UnitVelocity<*>.toCentimetersPerSecond() = toUnit(CentimetersPerSecond(1.0))
