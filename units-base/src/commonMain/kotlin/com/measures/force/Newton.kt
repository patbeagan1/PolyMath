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

    override operator fun plus(other: UnitForce<*>) = UnitForce.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitForce<*>) = UnitForce.Companion.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitForce.Companion.divUnit(this, other)
    override operator fun times(other: UnitDistance<*>): Joule = UnitForce.Companion.timesUnit(this, other)
    override operator fun div(other: UnitMass<*>) = UnitForce.Companion.divUnit(this, other)
    override operator fun div(other: UnitAcceleration<*>) = UnitForce.Companion.divUnit(this, other)

    companion object {
        fun from(mass: UnitMass<*>, acceleration: UnitAcceleration<*>): Newton = mass * acceleration
    }
}

fun UnitForce<*>.toNewton() = this.asBaseUnit()
