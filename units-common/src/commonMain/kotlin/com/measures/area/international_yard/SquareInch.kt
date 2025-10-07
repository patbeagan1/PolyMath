package com.measures.area.international_yard

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import kotlin.jvm.JvmInline

@JvmInline
value class SquareInch(override val value: Double) : UnitArea<SquareInch> {
    override fun asType(d: Double) = SquareInch(d)
    override fun asBaseUnit() = SquareYard(value / 1296).asBaseUnit()

    override fun plus(other: UnitArea<*>): SquareMeter = UnitArea.plusUnit(this, other)
    override fun minus(other: UnitArea<*>): SquareMeter = UnitArea.minusUnit(this, other)
    override fun times(other: UnitDistance<*>): Liter = UnitArea.timesUnit(this, other)
    override fun div(other: UnitDistance<*>): Meter = UnitArea.divUnit(this, other)
}

fun UnitArea<*>.toSquareInch() = toUnit(SquareInch(1.0))
