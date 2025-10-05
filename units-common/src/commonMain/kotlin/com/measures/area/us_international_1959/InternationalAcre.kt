package com.measures.area.us_international_1959

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import kotlin.jvm.JvmInline

@JvmInline
value class InternationalAcre(override val value: Double) : UnitArea<InternationalAcre> {
    override fun asType(d: Double) = InternationalAcre(d)
    override fun asBaseUnit() = SquareMeter(value * 4046.8564224)

    override fun plus(other: UnitArea<*>): SquareMeter = UnitArea.plusUnit(this, other)
    override fun minus(other: UnitArea<*>): SquareMeter = UnitArea.minusUnit(this, other)
    override fun times(other: UnitDistance<*>): Liter = UnitArea.timesUnit(this, other)
    override fun div(other: UnitDistance<*>): Meter = UnitArea.divUnit(this, other)
}

fun UnitArea<*>.toInternationalAcre() = toUnit(InternationalAcre(1.0))
