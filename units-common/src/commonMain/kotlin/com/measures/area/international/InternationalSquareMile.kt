package com.measures.area.international

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import kotlin.jvm.JvmInline

@JvmInline
value class InternationalSquareMile(override val value: Double) : UnitArea<InternationalSquareMile> {
    override fun asType(d: Double) = InternationalSquareMile(d)
    override fun asBaseUnit() = InternationalSquareFoot(value * 27878400).asBaseUnit()

    override fun plus(other: UnitArea<*>): SquareMeter = UnitArea.plusUnit(this, other)
    override fun minus(other: UnitArea<*>): SquareMeter = UnitArea.minusUnit(this, other)
    override fun times(other: UnitDistance<*>): Liter = UnitArea.timesUnit(this, other)
    override fun div(other: UnitDistance<*>): Meter = UnitArea.divUnit(this, other)
}

fun UnitArea<*>.toInternationalSquareMile() = toUnit(InternationalSquareMile(1.0))
