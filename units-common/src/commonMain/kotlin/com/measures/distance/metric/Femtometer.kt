package com.measures.distance.metric

import com.measures.Consts
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.distance.Meter
import com.measures.time.UnitTime
import com.measures.velocity.MetersPerSecond
import kotlin.jvm.JvmInline

@JvmInline
value class Femtometer(override val value: Double) : UnitDistance<Femtometer> {
    override fun asType(d: Double) = Femtometer(d)
    override fun asBaseUnit() = Meter(value * Consts.FEMTO)

    override operator fun plus(other: UnitDistance<*>) = UnitDistance.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitDistance<*>) = UnitDistance.Companion.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitDistance.Companion.timesUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitDistance.Companion.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond = UnitDistance.Companion.divUnit(this, other)
}

fun UnitDistance<*>.toFemtometer() = toUnit(Femtometer(1.0))
