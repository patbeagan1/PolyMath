package com.measures.force.cgs

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
value class Dynes(override val value: Double) : UnitForce<Dynes> {
    override fun asType(d: Double) = Dynes(d)
    override fun asBaseUnit() = Newton(value / 100_000)

    override operator fun plus(other: UnitForce<*>) = UnitForce.plusUnit(this, other)
    override operator fun minus(other: UnitForce<*>) = UnitForce.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>): Pascal = UnitForce.divUnit(this, other)
    override operator fun times(other: UnitDistance<*>): Joule = UnitForce.timesUnit(this, other)
    override operator fun div(other: UnitMass<*>): MetersPerSecondPerSecond = UnitForce.divUnit(this, other)
    override operator fun div(other: UnitAcceleration<*>): Kilogram = UnitForce.divUnit(this, other)
}

fun UnitForce<*>.toDynes() = toUnit(Dynes(1.0))
