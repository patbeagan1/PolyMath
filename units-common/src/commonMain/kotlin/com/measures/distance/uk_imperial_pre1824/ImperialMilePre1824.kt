package com.measures.distance.uk_imperial_pre1824

import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.distance.Meter
import com.measures.time.UnitTime
import com.measures.velocity.MetersPerSecond
import kotlin.jvm.JvmInline

@JvmInline
value class ImperialMilePre1824(override val value: Double) : UnitDistance<ImperialMilePre1824> {
    override fun asType(d: Double) = ImperialMilePre1824(d)
    override fun asBaseUnit() = ImperialFootPre1824(value * 5280).asBaseUnit()

    override operator fun plus(other: UnitDistance<*>) = UnitDistance.plusUnit(this, other)
    override operator fun minus(other: UnitDistance<*>) = UnitDistance.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitDistance.timesUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitDistance.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond = UnitDistance.divUnit(this, other)
}

fun UnitDistance<*>.toImperialMilePre1824() = toUnit(ImperialMilePre1824(1.0))
