package com.measures.volume.american_customary_fluid

import com.measures.area.UnitArea
import com.measures.area.SquareMeter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class USFluidGallon(override val value: Double) : UnitVolume<USFluidGallon> {
    override fun asType(d: Double) = USFluidGallon(d)
    override fun asBaseUnit() = Liter(value * 3.785411784)

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.divUnit(this, other)
}

fun UnitVolume<*>.toUSFluidGallon() = toUnit(USFluidGallon(1.0))
