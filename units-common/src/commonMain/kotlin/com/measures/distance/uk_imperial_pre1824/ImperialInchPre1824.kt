package com.measures.distance.uk_imperial_pre1824

import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.distance.Meter
import com.measures.time.UnitTime
import com.measures.velocity.MetersPerSecond
import kotlin.jvm.JvmInline

@JvmInline
value class ImperialInchPre1824(override val value: Double) : UnitDistance<ImperialInchPre1824> {
    override fun asType(d: Double) = ImperialInchPre1824(d)
    override fun asBaseUnit() = ImperialFootPre1824(value / 12).asBaseUnit()

    override operator fun plus(other: UnitDistance<*>) = UnitDistance.plusUnit(this, other)
    override operator fun minus(other: UnitDistance<*>) = UnitDistance.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitDistance.timesUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitDistance.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond = UnitDistance.divUnit(this, other)
}

fun UnitDistance<*>.toImperialInchPre1824() = toUnit(ImperialInchPre1824(1.0))
