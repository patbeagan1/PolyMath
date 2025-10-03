package com.measures.volume.english_imperial

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline
@JvmInline
value class ImperialGallon(override val value: Double) : UnitVolume<ImperialGallon> {
    override fun asType(d: Double) = ImperialGallon(d)
    override fun asBaseUnit() = ImperialFluidOunce(this.value * 160.0).asBaseUnit()

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.Companion.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.Companion.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.Companion.divUnit(this, other)
}

fun UnitVolume<*>.toImperialGallon() = toUnit(ImperialGallon(1.0))
