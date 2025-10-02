package com.measures.velocity

import com.measures.BaseUnit
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class MetersPerSecond(override val value: Double) : UnitVelocity<MetersPerSecond>, BaseUnit {
    override fun asType(d: Double) = MetersPerSecond(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitVelocity<*>) = UnitVelocity.plusUnit(this, other)
    override operator fun minus(other: UnitVelocity<*>) = UnitVelocity.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitVelocity.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitVelocity.divUnit(this, other)
}

fun UnitVelocity<*>.toMetersPerSecond() = this.asBaseUnit()
