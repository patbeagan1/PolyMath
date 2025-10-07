package com.measures.acceleration.g_force

import com.measures.acceleration.UnitAcceleration
import com.measures.acceleration.MetersPerSecondPerSecond
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import com.measures.force.Newton
import kotlin.jvm.JvmInline

@JvmInline
value class VenusianG(override val value: Double) : UnitAcceleration<VenusianG> {
    override fun asType(d: Double) = VenusianG(d)
    override fun asBaseUnit() = MetersPerSecondPerSecond(value * 8.87)

    override operator fun plus(other: UnitAcceleration<*>) = UnitAcceleration.plusUnit(this, other)
    override operator fun minus(other: UnitAcceleration<*>) = UnitAcceleration.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitAcceleration.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitAcceleration.divUnit(this, other)
    override operator fun times(other: UnitMass<*>): Newton = UnitAcceleration.timesUnit(this, other)
}

fun UnitAcceleration<*>.toVenusianG() = toUnit(VenusianG(1.0))
