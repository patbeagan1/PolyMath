package com.measures.acceleration.non_si

import com.measures.acceleration.UnitAcceleration
import com.measures.acceleration.MetersPerSecondPerSecond
import com.measures.force.Newton
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class GForce(override val value: Double) : UnitAcceleration<GForce> {
    override fun asType(d: Double) = GForce(d)
    override fun asBaseUnit() = MetersPerSecondPerSecond(this.value * 9.80665)

    override operator fun plus(other: UnitAcceleration<*>) = UnitAcceleration.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitAcceleration<*>) = UnitAcceleration.Companion.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitAcceleration.Companion.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitAcceleration.Companion.divUnit(this, other)
    override operator fun times(other: UnitMass<*>): Newton = Newton(this.asBaseUnit().value * other.asBaseUnit().value * 100)
}

fun UnitAcceleration<*>.toGForce() = toUnit(GForce(1.0))
