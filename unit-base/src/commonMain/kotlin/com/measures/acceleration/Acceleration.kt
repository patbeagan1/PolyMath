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

 fun UnitAcceleration<*>.plusUnit(other: UnitAcceleration<*>): MetersPerSecondPerSecond =
    MetersPerSecondPerSecond(this.asBaseUnit().value + other.asBaseUnit().value)

 fun UnitAcceleration<*>.minusUnit(other: UnitAcceleration<*>): MetersPerSecondPerSecond =
    MetersPerSecondPerSecond(this.asBaseUnit().value - other.asBaseUnit().value)

// Acceleration × Time = Velocity
 fun UnitAcceleration<*>.timesUnit(other: UnitTime<*>): MetersPerSecond =
    MetersPerSecond(this.asBaseUnit().value * other.asBaseUnit().value)

// Velocity ÷ Time = Acceleration
 fun UnitVelocity<*>.divUnit(other: UnitTime<*>): MetersPerSecondPerSecond =
    MetersPerSecondPerSecond(this.asBaseUnit().value / other.asBaseUnit().value)

 fun UnitAcceleration<*>.timesUnit(other: UnitMass<*>): Newton =
    Newton(this.asBaseUnit().value * other.asBaseUnit().value)
