package com.measures.distance.uk_imperial_pre1824

import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.distance.Meter
import com.measures.time.UnitTime
import com.measures.velocity.MetersPerSecond
import kotlin.jvm.JvmInline

@JvmInline
value class ImperialFootPre1824(override val value: Double) : UnitDistance<ImperialFootPre1824> {
    override fun asType(d: Double) = ImperialFootPre1824(d)
    override fun asBaseUnit() = Meter(value * 0.3048)

    override operator fun plus(other: UnitDistance<*>) = UnitDistance.plusUnit(this, other)
    override operator fun minus(other: UnitDistance<*>) = UnitDistance.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitDistance.timesUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitDistance.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond = UnitDistance.divUnit(this, other)
}

fun UnitDistance<*>.toImperialFootPre1824() = toUnit(ImperialFootPre1824(1.0))
