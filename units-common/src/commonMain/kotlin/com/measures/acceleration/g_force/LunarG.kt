package com.measures.acceleration.g_force

import com.measures.acceleration.UnitAcceleration
import com.measures.acceleration.MetersPerSecondPerSecond
import com.measures.time.UnitTime
import com.measures.mass.UnitMass
import com.measures.force.Newton
import kotlin.jvm.JvmInline

@JvmInline
value class LunarG(override val value: Double) : UnitAcceleration<LunarG> {
    override fun asType(d: Double) = LunarG(d)
    override fun asBaseUnit() = MetersPerSecondPerSecond(value * 1.62)

    override operator fun plus(other: UnitAcceleration<*>) = UnitAcceleration.plusUnit(this, other)
    override operator fun minus(other: UnitAcceleration<*>) = UnitAcceleration.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitAcceleration.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitAcceleration.divUnit(this, other)
    override operator fun times(other: UnitMass<*>): Newton = UnitAcceleration.timesUnit(this, other)
}

fun UnitAcceleration<*>.toLunarG() = toUnit(LunarG(1.0))
