package com.measures.velocity

import com.measures.BaseUnit
import com.measures.distance.Meter
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class MetersPerSecond(override val value: Double) : UnitVelocity<MetersPerSecond>, BaseUnit {
    override fun asType(d: Double) = MetersPerSecond(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).plus(other)
    operator fun minus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).minus(other)
    operator fun times(other: UnitTime<*>) = (this as UnitVelocity<*>).times(other)
}

fun UnitVelocity<*>.toMetersPerSecond() = this.asBaseUnit()
