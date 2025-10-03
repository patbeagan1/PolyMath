package com.measures.volume.english_imperial

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline
@JvmInline
value class ImperialPint(override val value: Double) : UnitVolume<ImperialPint> {
    override fun asType(d: Double) = ImperialPint(d)
    override fun asBaseUnit() = ImperialFluidOunce(this.value * 20.0).asBaseUnit()

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.Companion.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.Companion.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.Companion.divUnit(this, other)
}

fun UnitVolume<*>.toImperialPint() = toUnit(ImperialPint(1.0))
