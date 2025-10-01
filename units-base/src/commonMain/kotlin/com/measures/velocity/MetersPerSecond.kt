package com.measures.velocity

import com.measures.BaseUnit
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class MetersPerSecond(override val value: Double) : UnitVelocity<MetersPerSecond>, BaseUnit {
    override fun asType(d: Double) = MetersPerSecond(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).plusUnit(other)
    override operator fun minus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = (this as UnitVelocity<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>) = (this as UnitVelocity<*>).divUnit(other)
}

fun UnitVelocity<*>.toMetersPerSecond() = this.asBaseUnit()

// Non-SI velocity units have been moved to unit-common module
