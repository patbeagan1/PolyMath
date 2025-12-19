package com.measures.Volume.cgs

import com.measures.area.UnitArea
import com.measures.area.SquareMeter
import com.measures.distance.UnitDistance
import com.measures.volume.Liters
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class CubicCentimeters(override val value: Double) : UnitVolume<CubicCentimeters> {
    override fun asType(d: Double) = CubicCentimeters(d)
    override fun asBaseUnit() = Liters(value / 1000)

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.divUnit(this, other)
}

fun UnitVolume<*>.toCubicCentimeters() = toUnit(CubicCentimeters(1.0))
