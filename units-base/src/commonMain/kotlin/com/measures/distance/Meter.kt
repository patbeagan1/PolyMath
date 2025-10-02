package com.measures.distance

import com.measures.BaseUnit
import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.time.UnitTime
import com.measures.velocity.MetersPerSecond
import com.measures.volume.Liter
import kotlin.jvm.JvmInline

@JvmInline
value class Meter(override val value: Double) : UnitDistance<Meter>, BaseUnit {
    override fun asType(d: Double) = Meter(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitDistance<*>) = UnitDistance.plusUnit(this, other)
    override operator fun minus(other: UnitDistance<*>) = UnitDistance.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitDistance.timesUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitDistance.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond = UnitDistance.divUnit(this, other)
}

fun UnitDistance<*>.toMeter() = this.asBaseUnit()