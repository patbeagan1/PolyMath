package com.measures.power

import com.measures.BaseUnit
import com.measures.distance.UnitDistance
import com.measures.energy.Joule
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class Watt(override val value: Double) : UnitPower<Watt>, BaseUnit {
    override fun asType(d: Double) = Watt(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plusUnit(other)
    override operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = timesUnit(this, other)

    companion object {
        fun from(mass: UnitMass<*>, distance: UnitDistance<*>, time: UnitTime<*>): Watt =
            Joule.Companion.from(mass, distance, time) / time
    }
}

fun UnitPower<*>.toWatt() = this.asBaseUnit()