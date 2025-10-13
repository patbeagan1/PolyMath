package com.measures.area.metric

import com.measures.Consts
import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.volume.Liters
import kotlin.jvm.JvmInline

@JvmInline
value class MillimeterSquared(override val value: Double) : UnitArea<MillimeterSquared> {
    override fun asType(d: Double) = MillimeterSquared(d)
    override fun asBaseUnit() = SquareMeter(value * Consts.MILLI)

    override fun plus(other: UnitArea<*>): SquareMeter = UnitArea.plusUnit(this, other)
    override fun minus(other: UnitArea<*>): SquareMeter = UnitArea.minusUnit(this, other)
    override fun times(other: UnitDistance<*>): Liters = UnitArea.timesUnit(this, other)
    override fun div(other: UnitDistance<*>): Meter = UnitArea.divUnit(this, other)
}

fun UnitArea<*>.toMillimeterSquared() = toUnit(MillimeterSquared(1.0))
