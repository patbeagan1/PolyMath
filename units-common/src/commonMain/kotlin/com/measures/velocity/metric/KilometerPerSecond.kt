package com.measures.velocity.metric

import com.measures.Consts
import com.measures.velocity.MetersPerSecond
import com.measures.velocity.UnitVelocity
import com.measures.time.UnitTime
import com.measures.distance.UnitDistance
import kotlin.jvm.JvmInline

@JvmInline
value class KilometerPerSecond(override val value: Double) : UnitVelocity<KilometerPerSecond> {
    override fun asType(d: Double) = KilometerPerSecond(d)
    override fun asBaseUnit() = MetersPerSecond(value * Consts.KILO)

    override operator fun plus(other: UnitVelocity<*>) = UnitVelocity.plusUnit(this, other)
    override operator fun minus(other: UnitVelocity<*>) = UnitVelocity.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitVelocity.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitVelocity.divUnit(this, other)
}

fun UnitVelocity<*>.toKilometerPerSecond() = toUnit(KilometerPerSecond(1.0))
