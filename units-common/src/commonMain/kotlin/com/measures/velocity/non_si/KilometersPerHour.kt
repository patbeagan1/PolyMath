package com.measures.velocity.non_si

import com.measures.time.UnitTime
import com.measures.velocity.UnitVelocity
import kotlin.jvm.JvmInline

@JvmInline
value class KilometersPerHour(override val value: Double) : UnitVelocity<KilometersPerHour> {
override fun asType(d: Double) = KilometersPerHour(d)
    override fun asBaseUnit() = com.measures.velocity.MetersPerSecond(this.value * 0.27778)

    override operator fun plus(other: UnitVelocity<*>) = UnitVelocity.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVelocity<*>) = UnitVelocity.Companion.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitVelocity.Companion.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitVelocity.Companion.divUnit(this, other)
}

fun UnitVelocity<*>.toKilometersPerHour() = toUnit(KilometersPerHour(1.0))
