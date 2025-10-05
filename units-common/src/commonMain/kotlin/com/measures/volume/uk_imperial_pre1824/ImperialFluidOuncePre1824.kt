package com.measures.volume.uk_imperial_pre1824

import com.measures.area.UnitArea
import com.measures.area.SquareMeter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class ImperialFluidOuncePre1824(override val value: Double) : UnitVolume<ImperialFluidOuncePre1824> {
    override fun asType(d: Double) = ImperialFluidOuncePre1824(d)
    override fun asBaseUnit() = Liter(value * 0.0284130625)

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.divUnit(this, other)
}

fun UnitVolume<*>.toImperialFluidOuncePre1824() = toUnit(ImperialFluidOuncePre1824(1.0))
