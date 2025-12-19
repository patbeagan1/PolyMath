package com.measures.area.uk_imperial

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.volume.Liters
import kotlin.jvm.JvmInline

@JvmInline
value class UKSquareMile(override val value: Double) : UnitArea<UKSquareMile> {
    override fun asType(d: Double) = UKSquareMile(d)
    override fun asBaseUnit() = UKSquareFoot(value * 27878400).asBaseUnit()

    override fun plus(other: UnitArea<*>): SquareMeter = UnitArea.plusUnit(this, other)
    override fun minus(other: UnitArea<*>): SquareMeter = UnitArea.minusUnit(this, other)
    override fun times(other: UnitDistance<*>): Liters = UnitArea.timesUnit(this, other)
    override fun div(other: UnitDistance<*>): Meter = UnitArea.divUnit(this, other)
}

fun UnitArea<*>.toUKSquareMile() = toUnit(UKSquareMile(1.0))
