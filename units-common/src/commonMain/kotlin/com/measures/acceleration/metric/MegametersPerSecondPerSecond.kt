package com.measures.acceleration.metric

import com.measures.Consts
import com.measures.acceleration.UnitAcceleration
import com.measures.acceleration.MetersPerSecondPerSecond
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import com.measures.force.Newton
import kotlin.jvm.JvmInline

@JvmInline
value class MegametersPerSecondPerSecond(override val value: Double) : UnitAcceleration<MegametersPerSecondPerSecond> {
    override fun asType(d: Double) = MegametersPerSecondPerSecond(d)
    override fun asBaseUnit() = MetersPerSecondPerSecond(value * Consts.MEGA)

    override operator fun plus(other: UnitAcceleration<*>) = UnitAcceleration.plusUnit(this, other)
    override operator fun minus(other: UnitAcceleration<*>) = UnitAcceleration.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitAcceleration.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitAcceleration.divUnit(this, other)
    override operator fun times(other: UnitMass<*>): Newton = UnitAcceleration.timesUnit(this, other)
}

fun UnitAcceleration<*>.toMegametersPerSecondPerSecond() = toUnit(MegametersPerSecondPerSecond(1.0))
