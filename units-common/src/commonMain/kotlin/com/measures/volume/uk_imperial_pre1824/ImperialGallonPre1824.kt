package com.measures.volume.uk_imperial_pre1824

import com.measures.area.UnitArea
import com.measures.area.SquareMeter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class ImperialGallonPre1824(override val value: Double) : UnitVolume<ImperialGallonPre1824> {
    override fun asType(d: Double) = ImperialGallonPre1824(d)
    override fun asBaseUnit() = ImperialQuartPre1824(value * 4).asBaseUnit()

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.divUnit(this, other)
}

fun UnitVolume<*>.toImperialGallonPre1824() = toUnit(ImperialGallonPre1824(1.0))
