package com.measures.force.non_si

import com.measures.force.Newton
import com.measures.force.UnitForce
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.mass.UnitMass
import com.measures.acceleration.UnitAcceleration
import com.measures.energy.Joule
import com.measures.pressure.Pascal
import com.measures.acceleration.MetersPerSecondPerSecond
import com.measures.mass.Kilogram
import kotlin.jvm.JvmInline

@JvmInline
value class PoundForce(override val value: Double) : UnitForce<PoundForce> {
    override fun asType(d: Double) = PoundForce(d)
    override fun asBaseUnit() = Newton(value * 4.4482216152605)

    override operator fun plus(other: UnitForce<*>) = UnitForce.plusUnit(this, other)
    override operator fun minus(other: UnitForce<*>) = UnitForce.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>): Pascal = UnitForce.divUnit(this, other)
    override operator fun times(other: UnitDistance<*>): Joule = UnitForce.timesUnit(this, other)
    override operator fun div(other: UnitMass<*>): MetersPerSecondPerSecond = UnitForce.divUnit(this, other)
    override operator fun div(other: UnitAcceleration<*>): Kilogram = UnitForce.divUnit(this, other)
}

fun UnitForce<*>.toPoundForce() = toUnit(PoundForce(1.0))
