package com.measures.velocity

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.acceleration.MetersPerSecondPerSecond
import com.measures.distance.Meter
import com.measures.time.UnitTime

interface UnitVelocity<T : DoubleBase> : UnitType<T, MetersPerSecond> {
    operator fun plus(other: UnitVelocity<*>): MetersPerSecond
    operator fun minus(other: UnitVelocity<*>): MetersPerSecond
    operator fun times(other: UnitTime<*>): Meter
    operator fun div(other: UnitTime<*>): MetersPerSecondPerSecond

    companion object {
        fun plusUnit(velocity: UnitVelocity<*>, other: UnitVelocity<*>): MetersPerSecond =
            MetersPerSecond(velocity.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(velocity: UnitVelocity<*>, other: UnitVelocity<*>): MetersPerSecond =
            MetersPerSecond(velocity.asBaseUnit().value - other.asBaseUnit().value)

        fun timesUnit(velocity: UnitVelocity<*>, other: UnitTime<*>): Meter =
            Meter(velocity.asBaseUnit().value * other.asBaseUnit().value)

        fun divUnit(velocity: UnitVelocity<*>, other: UnitTime<*>): MetersPerSecondPerSecond =
            MetersPerSecondPerSecond(velocity.asBaseUnit().value / other.asBaseUnit().value)
    }
}