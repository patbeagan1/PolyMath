package com.measures.distance.international_1959

import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.distance.Meter
import com.measures.time.UnitTime
import com.measures.velocity.MetersPerSecond
import kotlin.jvm.JvmInline

@JvmInline
value class InternationalFoot1959(override val value: Double) : UnitDistance<InternationalFoot1959> {
    override fun asType(d: Double) = InternationalFoot1959(d)
    override fun asBaseUnit() = Meter(value * 0.3048)

    override operator fun plus(other: UnitDistance<*>) = UnitDistance.plusUnit(this, other)
    override operator fun minus(other: UnitDistance<*>) = UnitDistance.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitDistance.timesUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitDistance.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond = UnitDistance.divUnit(this, other)
}

fun UnitDistance<*>.toInternationalFoot1959() = toUnit(InternationalFoot1959(1.0))
