package com.measures.acceleration.non_si

import com.measures.acceleration.UnitAcceleration
import com.measures.acceleration.MetersPerSecondPerSecond
import com.measures.force.Newton
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class FeetPerSecondPerSecond(override val value: Double) : UnitAcceleration<FeetPerSecondPerSecond> {
    override fun asType(d: Double) = FeetPerSecondPerSecond(d)
    override fun asBaseUnit() = MetersPerSecondPerSecond(this.value * 0.3048)

    override operator fun plus(other: UnitAcceleration<*>) = UnitAcceleration.plusUnit(this, other)
    override operator fun minus(other: UnitAcceleration<*>) = UnitAcceleration.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitAcceleration.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitAcceleration.divUnit(this, other)
    override operator fun times(other: UnitMass<*>): Newton = Newton(this.asBaseUnit().value * other.asBaseUnit().value * 100)
}

fun UnitAcceleration<*>.toFeetPerSecondPerSecond() = toUnit(FeetPerSecondPerSecond(1.0))
