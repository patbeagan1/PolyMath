package com.measures.force.non_si

import com.measures.acceleration.UnitAcceleration
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.force.Newton
import com.measures.force.UnitForce
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class PoundForce(override val value: Double) : UnitForce<PoundForce> {
override fun asType(d: Double) = PoundForce(d)
    override fun asBaseUnit() = Newton(this.value * 4.448222)

    override operator fun plus(other: UnitForce<*>) = UnitForce.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitForce<*>) = UnitForce.Companion.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitForce.Companion.divUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitForce.Companion.timesUnit(this, other)
    override operator fun div(other: UnitMass<*>) = UnitForce.Companion.divUnit(this, other)
    override operator fun div(other: UnitAcceleration<*>) = UnitForce.Companion.divUnit(this, other)
}

fun UnitForce<*>.toPoundForce() = toUnit(PoundForce(1.0))
