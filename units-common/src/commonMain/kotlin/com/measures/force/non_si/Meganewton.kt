package com.measures.force.non_si

import com.measures.acceleration.MetersPerSecondPerSecond
import com.measures.acceleration.UnitAcceleration
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.energy.Joule
import com.measures.force.Newton
import com.measures.force.UnitForce
import com.measures.pressure.Pascal
import com.measures.weight.KiloGram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class Meganewton(override val value: Double) : UnitForce<Meganewton> {
    override fun asType(d: Double) = Meganewton(d)
    override fun asBaseUnit() = Newton(value * 1.0)

    override operator fun plus(other: UnitForce<*>) = UnitForce.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitForce<*>) = UnitForce.Companion.minusUnit(this, other)
    override fun div(other: UnitArea<*>): Pascal = UnitForce.divUnit(this, other)
    override fun times(other: UnitDistance<*>): Joule = UnitForce.timesUnit(this, other)
    override fun div(other: UnitMass<*>): MetersPerSecondPerSecond = UnitForce.divUnit(this, other)
    override fun div(other: UnitAcceleration<*>): KiloGram = UnitForce.divUnit(this, other)
}

fun UnitForce<*>.toMeganewton() = toUnit(Meganewton(1.0))
