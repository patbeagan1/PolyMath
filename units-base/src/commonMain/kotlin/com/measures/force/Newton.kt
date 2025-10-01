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

    override operator fun plus(other: UnitForce<*>) = (this as UnitForce<*>).plusUnit(other)
    override operator fun minus(other: UnitForce<*>) = (this as UnitForce<*>).minusUnit(other)
    override operator fun div(other: UnitArea<*>) = (this as UnitForce<*>).divUnit(other)
    override operator fun times(other: UnitDistance<*>): Joule = (this as UnitForce<*>).timesUnit(other)
    override operator fun div(other: UnitMass<*>) = (this as UnitForce<*>).divUnit(other)
    override operator fun div(other: UnitAcceleration<*>) = (this as UnitForce<*>).divUnit(other)

    companion object {
        fun from(mass: UnitMass<*>, acceleration: UnitAcceleration<*>): Newton = mass * acceleration
    }
}

fun UnitForce<*>.toNewton() = this.asBaseUnit()
