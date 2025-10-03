package com.measures.area.american_customary

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class SquareFoot(override val value: Double) : UnitArea<SquareFoot> {
    override fun asType(d: Double) = SquareFoot(d)
    override fun asBaseUnit() = SquareMeter(value * 0.09290304)

    override fun plus(other: UnitArea<*>): SquareMeter = UnitArea.plusUnit(this, other)
    override fun minus(other: UnitArea<*>): SquareMeter = UnitArea.minusUnit(this, other)
    override fun times(other: UnitDistance<*>): UnitVolume<*> = UnitArea.timesUnit(this, other)
}

fun UnitArea<*>.toSquareFoot() = toUnit(SquareFoot(1.0))
