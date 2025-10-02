package com.measures.acceleration

import com.measures.BaseUnit
import com.measures.distance.UnitDistance
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class MetersPerSecondPerSecond(override val value: Double) : UnitAcceleration<MetersPerSecondPerSecond>,
    BaseUnit {
    override fun asType(d: Double) = MetersPerSecondPerSecond(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitAcceleration<*>) = UnitAcceleration.plusUnit(this, other)
    override operator fun minus(other: UnitAcceleration<*>) = UnitAcceleration.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitAcceleration.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitAcceleration.divUnit(this, other)
    override operator fun times(other: UnitMass<*>) = UnitAcceleration.timesUnit(this, other)


    companion object {
        fun from(distance: UnitDistance<*>, time: UnitTime<*>): MetersPerSecondPerSecond {
            // Acceleration = distance / (time * time)
            return MetersPerSecondPerSecond(distance.asBaseUnit().value / (time.asBaseUnit().value * time.asBaseUnit().value))
        }
    }
}

fun UnitAcceleration<*>.toMetersPerSecondPerSecond() = this.asBaseUnit()



