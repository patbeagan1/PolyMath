package com.measures.area.international_yard

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.volume.Liters
import kotlin.jvm.JvmInline

@JvmInline
value class SquareFoot(override val value: Double) : UnitArea<SquareFoot> {
    override fun asType(d: Double) = SquareFoot(d)
    override fun asBaseUnit() = SquareYard(value / 9).asBaseUnit()

    override fun plus(other: UnitArea<*>): SquareMeter = UnitArea.plusUnit(this, other)
    override fun minus(other: UnitArea<*>): SquareMeter = UnitArea.minusUnit(this, other)
    override fun times(other: UnitDistance<*>): Liters = UnitArea.timesUnit(this, other)
    override fun div(other: UnitDistance<*>): Meter = UnitArea.divUnit(this, other)
}

fun UnitArea<*>.toSquareFoot() = toUnit(SquareFoot(1.0))
