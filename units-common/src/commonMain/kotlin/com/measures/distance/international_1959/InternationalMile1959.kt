package com.measures.distance.international_1959

import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.distance.Meter
import com.measures.time.UnitTime
import com.measures.velocity.MetersPerSecond
import kotlin.jvm.JvmInline

@JvmInline
value class InternationalMile1959(override val value: Double) : UnitDistance<InternationalMile1959> {
    override fun asType(d: Double) = InternationalMile1959(d)
    override fun asBaseUnit() = InternationalFoot1959(value * 5280).asBaseUnit()

    override operator fun plus(other: UnitDistance<*>) = UnitDistance.plusUnit(this, other)
    override operator fun minus(other: UnitDistance<*>) = UnitDistance.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitDistance.timesUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitDistance.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond = UnitDistance.divUnit(this, other)
}

fun UnitDistance<*>.toInternationalMile1959() = toUnit(InternationalMile1959(1.0))
