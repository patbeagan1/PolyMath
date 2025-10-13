package com.measures.force

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.acceleration.MetersPerSecondPerSecond
import com.measures.acceleration.UnitAcceleration
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.energy.Joule
import com.measures.pressure.Pascal
import com.measures.weight.Kilogram
import com.measures.weight.UnitMass

interface UnitForce<T : DoubleBase> : UnitType<T, Newton> {
    operator fun plus(other: UnitForce<*>): Newton
    operator fun minus(other: UnitForce<*>): Newton
    operator fun div(other: UnitArea<*>): Pascal
    operator fun times(other: UnitDistance<*>): Joule
    operator fun div(other: UnitMass<*>): MetersPerSecondPerSecond
    operator fun div(other: UnitAcceleration<*>): Kilogram

    companion object {
        fun plusUnit(force: UnitForce<*>, other: UnitForce<*>): Newton =
            Newton(force.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(force: UnitForce<*>, other: UnitForce<*>): Newton =
            Newton(force.asBaseUnit().value - other.asBaseUnit().value)

        fun divUnit(force: UnitForce<*>, other: UnitArea<*>): Pascal =
            Pascal(force.asBaseUnit().value / other.asBaseUnit().value)

        // Force × Distance = Energy
        fun timesUnit(force: UnitForce<*>, other: UnitDistance<*>): Joule =
            Joule(force.asBaseUnit().value * other.asBaseUnit().value)

        // Force ÷ Mass = Acceleration
        fun divUnit(force: UnitForce<*>, other: UnitMass<*>): MetersPerSecondPerSecond =
            MetersPerSecondPerSecond(force.asBaseUnit().value / other.asBaseUnit().value)

        // Force ÷ Acceleration = Mass
        fun divUnit(force: UnitForce<*>, other: UnitAcceleration<*>): Kilogram =
            Kilogram(force.asBaseUnit().value / other.asBaseUnit().value)
    }
}
