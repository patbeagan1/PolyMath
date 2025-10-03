package com.measures.area.metric

import com.measures.Consts
import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import kotlin.jvm.JvmInline

@JvmInline
value class FemtometerSquared(override val value: Double) : UnitArea<FemtometerSquared> {
    override fun asType(d: Double) = FemtometerSquared(d)
    override fun asBaseUnit() = SquareMeter(value * Consts.FEMTO)

    override fun plus(other: UnitArea<*>): SquareMeter = UnitArea.plusUnit(this, other)
    override fun minus(other: UnitArea<*>): SquareMeter = UnitArea.minusUnit(this, other)
    override fun times(other: UnitDistance<*>): Liter = UnitArea.timesUnit(this, other)
    override fun div(other: UnitDistance<*>): Meter = UnitArea.divUnit(this, other)
}

fun UnitArea<*>.toFemtometerSquared() = toUnit(FemtometerSquared(1.0))
