package com.measures.area.metric

import com.measures.Consts
import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.volume.Liters
import kotlin.jvm.JvmInline

@JvmInline
value class DekameterSquared(override val value: Double) : UnitArea<DekameterSquared> {
    override fun asType(d: Double) = DekameterSquared(d)
    override fun asBaseUnit() = SquareMeter(value * Consts.DEKA)

    override fun plus(other: UnitArea<*>): SquareMeter = UnitArea.plusUnit(this, other)
    override fun minus(other: UnitArea<*>): SquareMeter = UnitArea.minusUnit(this, other)
    override fun times(other: UnitDistance<*>): Liters = UnitArea.timesUnit(this, other)
    override fun div(other: UnitDistance<*>): Meter = UnitArea.divUnit(this, other)
}

fun UnitArea<*>.toDekameterSquared() = toUnit(DekameterSquared(1.0))
