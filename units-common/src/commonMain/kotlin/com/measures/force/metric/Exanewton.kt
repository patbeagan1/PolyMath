package com.measures.force.metric

import com.measures.Consts
import com.measures.force.Newton
import com.measures.force.UnitForce
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.weight.UnitMass
import com.measures.acceleration.UnitAcceleration
import com.measures.energy.Joule
import com.measures.pressure.Pascal
import com.measures.acceleration.MetersPerSecondPerSecond
import com.measures.weight.Kilogram
import kotlin.jvm.JvmInline

@JvmInline
value class Exanewton(override val value: Double) : UnitForce<Exanewton> {
    override fun asType(d: Double) = Exanewton(d)
    override fun asBaseUnit() = Newton(value * Consts.EXA)

    override operator fun plus(other: UnitForce<*>) = UnitForce.plusUnit(this, other)
    override operator fun minus(other: UnitForce<*>) = UnitForce.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>): Pascal = UnitForce.divUnit(this, other)
    override operator fun times(other: UnitDistance<*>): Joule = UnitForce.timesUnit(this, other)
    override operator fun div(other: UnitMass<*>): MetersPerSecondPerSecond = UnitForce.divUnit(this, other)
    override operator fun div(other: UnitAcceleration<*>): Kilogram = UnitForce.divUnit(this, other)
}

fun UnitForce<*>.toExanewton() = toUnit(Exanewton(1.0))
