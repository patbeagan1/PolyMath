package com.measures.area.us_customary

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.volume.Liters
import kotlin.jvm.JvmInline

@JvmInline
value class USSquareYard(override val value: Double) : UnitArea<USSquareYard> {
    override fun asType(d: Double) = USSquareYard(d)
    override fun asBaseUnit() = USSquareFoot(value * 9).asBaseUnit()

    override fun plus(other: UnitArea<*>): SquareMeter = UnitArea.plusUnit(this, other)
    override fun minus(other: UnitArea<*>): SquareMeter = UnitArea.minusUnit(this, other)
    override fun times(other: UnitDistance<*>): Liters = UnitArea.timesUnit(this, other)
    override fun div(other: UnitDistance<*>): Meter = UnitArea.divUnit(this, other)
}

fun UnitArea<*>.toUSSquareYard() = toUnit(USSquareYard(1.0))
