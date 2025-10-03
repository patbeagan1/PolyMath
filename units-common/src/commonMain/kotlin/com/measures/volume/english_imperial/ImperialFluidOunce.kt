package com.measures.volume.english_imperial

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class ImperialFluidOunce(override val value: Double) : UnitVolume<ImperialFluidOunce> {
override fun asType(d: Double) = ImperialFluidOunce(d)
    override fun asBaseUnit() = Liter(this.value * 28.4130625 / 1000)

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.Companion.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.Companion.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.Companion.divUnit(this, other)
}

fun UnitVolume<*>.toImperialFluidOunce() = toUnit(ImperialFluidOunce(1.0))
