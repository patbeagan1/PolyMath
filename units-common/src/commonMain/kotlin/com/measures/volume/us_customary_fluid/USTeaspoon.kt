package com.measures.volume.us_customary_fluid

import com.measures.area.UnitArea
import com.measures.area.SquareMeter
import com.measures.distance.UnitDistance
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class USTeaspoon(override val value: Double) : UnitVolume<USTeaspoon> {
    override fun asType(d: Double) = USTeaspoon(d)
    override fun asBaseUnit() = USFluidOunce(value / 6).asBaseUnit()

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.divUnit(this, other)
}

fun UnitVolume<*>.toUSTeaspoon() = toUnit(USTeaspoon(1.0))
