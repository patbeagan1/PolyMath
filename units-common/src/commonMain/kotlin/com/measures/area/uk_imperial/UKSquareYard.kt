package com.measures.area.uk_imperial

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import kotlin.jvm.JvmInline

@JvmInline
value class UKSquareYard(override val value: Double) : UnitArea<UKSquareYard> {
    override fun asType(d: Double) = UKSquareYard(d)
    override fun asBaseUnit() = SquareUKFoot(value * 9).asBaseUnit()

    override fun plus(other: UnitArea<*>): SquareMeter = UnitArea.plusUnit(this, other)
    override fun minus(other: UnitArea<*>): SquareMeter = UnitArea.minusUnit(this, other)
    override fun times(other: UnitDistance<*>): Liter = UnitArea.timesUnit(this, other)
    override fun div(other: UnitDistance<*>): Meter = UnitArea.divUnit(this, other)
}

fun UnitArea<*>.toUKSquareYard() = toUnit(UKSquareYard(1.0))
