package com.measures.volume.international_yard

import com.measures.area.UnitArea
import com.measures.area.SquareMeter
import com.measures.distance.UnitDistance
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class CubicFoot(override val value: Double) : UnitVolume<CubicFoot> {
    override fun asType(d: Double) = CubicFoot(d)
    override fun asBaseUnit() = CubicInch(value * 1728).asBaseUnit()

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.divUnit(this, other)
}

fun UnitVolume<*>.toCubicFoot() = toUnit(CubicFoot(1.0))
