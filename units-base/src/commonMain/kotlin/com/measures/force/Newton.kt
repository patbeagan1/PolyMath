package com.measures.force

import com.measures.BaseUnit
import com.measures.acceleration.UnitAcceleration
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.energy.Joule
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class Newton(override val value: Double) : UnitForce<Newton>, BaseUnit {
    override fun asType(d: Double) = Newton(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitForce<*>) = UnitForce.plusUnit(this, other)
    override operator fun minus(other: UnitForce<*>) = UnitForce.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitForce.divUnit(this, other)
    override operator fun times(other: UnitDistance<*>): Joule = UnitForce.timesUnit(this, other)
    override operator fun div(other: UnitMass<*>) = UnitForce.divUnit(this, other)
    override operator fun div(other: UnitAcceleration<*>) = UnitForce.divUnit(this, other)

    companion object {
        fun from(mass: UnitMass<*>, acceleration: UnitAcceleration<*>): Newton = mass * acceleration
    }
}

fun UnitForce<*>.toNewton() = this.asBaseUnit()
