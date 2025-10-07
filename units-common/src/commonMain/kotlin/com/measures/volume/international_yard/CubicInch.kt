package com.measures.volume.international_yard

import com.measures.area.UnitArea
import com.measures.area.SquareMeter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class CubicInch(override val value: Double) : UnitVolume<CubicInch> {
    override fun asType(d: Double) = CubicInch(d)
    override fun asBaseUnit() = CubicMeter(value / 1000 * 16.387064)

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.divUnit(this, other)
}

fun UnitVolume<*>.toCubicInch() = toUnit(CubicInch(1.0))
