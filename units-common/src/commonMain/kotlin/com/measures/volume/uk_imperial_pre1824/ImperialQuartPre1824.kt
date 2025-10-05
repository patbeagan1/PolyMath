package com.measures.volume.uk_imperial_pre1824

import com.measures.area.UnitArea
import com.measures.area.SquareMeter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class ImperialQuartPre1824(override val value: Double) : UnitVolume<ImperialQuartPre1824> {
    override fun asType(d: Double) = ImperialQuartPre1824(d)
    override fun asBaseUnit() = ImperialPintPre1824(value * 2).asBaseUnit()

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.divUnit(this, other)
}

fun UnitVolume<*>.toImperialQuartPre1824() = toUnit(ImperialQuartPre1824(1.0))
