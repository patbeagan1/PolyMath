package com.measures.acceleration

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.force.Newton
import com.measures.velocity.UnitVelocity
import com.measures.velocity.MetersPerSecond
import com.measures.time.UnitTime
import com.measures.mass.UnitMass

interface UnitAcceleration<T : DoubleBase> : UnitType<T, MetersPerSecondPerSecond> {
    operator fun plus(other: UnitAcceleration<*>): MetersPerSecondPerSecond
    operator fun minus(other: UnitAcceleration<*>): MetersPerSecondPerSecond
    operator fun times(other: UnitTime<*>): MetersPerSecond
    operator fun div(other: UnitTime<*>): MetersPerSecondPerSecond
    operator fun times(other: UnitMass<*>): Newton

    companion object {
        fun plusUnit(acceleration: UnitAcceleration<*>, other: UnitAcceleration<*>): MetersPerSecondPerSecond =
            MetersPerSecondPerSecond(acceleration.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(acceleration: UnitAcceleration<*>, other: UnitAcceleration<*>): MetersPerSecondPerSecond =
            MetersPerSecondPerSecond(acceleration.asBaseUnit().value - other.asBaseUnit().value)

        fun timesUnit(acceleration: UnitAcceleration<*>, other: UnitTime<*>): MetersPerSecond =
            MetersPerSecond(acceleration.asBaseUnit().value * other.asBaseUnit().value)

        fun divUnit(velocity: UnitVelocity<*>, other: UnitTime<*>): MetersPerSecondPerSecond =
            MetersPerSecondPerSecond(velocity.asBaseUnit().value / other.asBaseUnit().value)

        fun divUnit(acceleration: UnitAcceleration<*>, other: UnitTime<*>): MetersPerSecondPerSecond =
            MetersPerSecondPerSecond(acceleration.asBaseUnit().value / other.asBaseUnit().value)

        fun timesUnit(acceleration: UnitAcceleration<*>, other: UnitMass<*>): Newton =
            Newton(acceleration.asBaseUnit().value * other.asBaseUnit().value)
    }
}
