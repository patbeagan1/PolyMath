package com.measures.power

import com.measures.BaseUnit
import com.measures.distance.UnitDistance
import com.measures.energy.Joule
import com.measures.time.UnitTime
import com.measures.mass.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class Watt(override val value: Double) : UnitPower<Watt>, BaseUnit {
    override fun asType(d: Double) = Watt(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitPower<*>) = UnitPower.plusUnit(this, other)
    override operator fun minus(other: UnitPower<*>) = UnitPower.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitPower.timesUnit(this, other)

    companion object {
        fun from(mass: UnitMass<*>, distance: UnitDistance<*>, time: UnitTime<*>): Watt =
            Joule.from(mass, distance, time) / time
    }
}

fun UnitPower<*>.toWatt() = this.asBaseUnit()