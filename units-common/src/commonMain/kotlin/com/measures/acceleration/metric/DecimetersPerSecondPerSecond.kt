package com.measures.acceleration.metric

import com.measures.Consts
import com.measures.acceleration.UnitAcceleration
import com.measures.acceleration.MetersPerSecondPerSecond
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import com.measures.force.Newton
import kotlin.jvm.JvmInline

@JvmInline
value class DecimetersPerSecondPerSecond(override val value: Double) : UnitAcceleration<DecimetersPerSecondPerSecond> {
    override fun asType(d: Double) = DecimetersPerSecondPerSecond(d)
    override fun asBaseUnit() = MetersPerSecondPerSecond(value * Consts.DECI)

    override operator fun plus(other: UnitAcceleration<*>) = UnitAcceleration.plusUnit(this, other)
    override operator fun minus(other: UnitAcceleration<*>) = UnitAcceleration.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitAcceleration.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitAcceleration.divUnit(this, other)
    override operator fun times(other: UnitMass<*>): Newton = UnitAcceleration.timesUnit(this, other)
}

fun UnitAcceleration<*>.toDecimetersPerSecondPerSecond() = toUnit(DecimetersPerSecondPerSecond(1.0))
