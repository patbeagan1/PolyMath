package com.measures.acceleration

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.force.Newton
import com.measures.force.UnitForce
import com.measures.velocity.UnitVelocity
import com.measures.velocity.MetersPerSecond
import com.measures.time.UnitTime
import com.measures.weight.UnitMass

interface UnitAcceleration<T : DoubleBase> : UnitType<T, MetersPerSecondPerSecond> 

operator fun UnitAcceleration<*>.plus(other: UnitAcceleration<*>): MetersPerSecondPerSecond =
    MetersPerSecondPerSecond(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitAcceleration<*>.minus(other: UnitAcceleration<*>): MetersPerSecondPerSecond =
    MetersPerSecondPerSecond(this.asBaseUnit().value - other.asBaseUnit().value)

// Acceleration × Time = Velocity
operator fun UnitAcceleration<*>.times(other: UnitTime<*>): MetersPerSecond =
    MetersPerSecond(this.asBaseUnit().value * other.asBaseUnit().value)

// Velocity ÷ Time = Acceleration
operator fun UnitVelocity<*>.div(other: UnitTime<*>): MetersPerSecondPerSecond =
    MetersPerSecondPerSecond(this.asBaseUnit().value / other.asBaseUnit().value)

operator fun UnitAcceleration<*>.times(other: UnitMass<*>): Newton =
    Newton(this.asBaseUnit().value * other.asBaseUnit().value)
