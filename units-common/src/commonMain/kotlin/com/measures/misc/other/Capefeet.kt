package com.measures.misc.other

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.time.UnitTime
import com.measures.velocity.MetersPerSecond
import com.measures.volume.Liter
import kotlin.jvm.JvmInline

@JvmInline
value class Capefeet(override val value: Double) : UnitDistance<Capefeet> {
override fun asType(d: Double) = Capefeet(d)
    override fun asBaseUnit() = com.measures.distance.Meter(this.value * 0.314856)

    override fun plus(other: UnitDistance<*>): Meter = UnitDistance.Companion.plusUnit(this, other)
    override fun minus(other: UnitDistance<*>): Meter = UnitDistance.Companion.minusUnit(this, other)
    override fun times(other: UnitDistance<*>): SquareMeter = UnitDistance.Companion.timesUnit(this, other)
    override fun times(other: UnitArea<*>): Liter = UnitDistance.Companion.timesUnit(this, other)
    override fun div(other: UnitTime<*>): MetersPerSecond = UnitDistance.Companion.divUnit(this, other)
}

fun UnitDistance<*>.toCapefeet() = toUnit(Capefeet(1.0))
