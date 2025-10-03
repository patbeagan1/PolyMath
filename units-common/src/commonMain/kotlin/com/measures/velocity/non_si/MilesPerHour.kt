package com.measures.velocity.non_si

import com.measures.time.UnitTime
import com.measures.velocity.UnitVelocity
import kotlin.jvm.JvmInline

@JvmInline
value class MilesPerHour(override val value: Double) : UnitVelocity<MilesPerHour> {
override fun asType(d: Double) = MilesPerHour(d)
    override fun asBaseUnit() = com.measures.velocity.MetersPerSecond(this.value * 0.44704)

    override operator fun plus(other: UnitVelocity<*>) = UnitVelocity.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVelocity<*>) = UnitVelocity.Companion.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitVelocity.Companion.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitVelocity.Companion.divUnit(this, other)
}

fun UnitVelocity<*>.toMilesPerHour() = toUnit(MilesPerHour(1.0))
