package com.measures.volume.uk_imp

import com.measures.area.UnitArea
import com.measures.area.SquareMeter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class InternationalFluidOunce(override val value: Double) : UnitVolume<InternationalFluidOunce> {
    override fun asType(d: Double) = InternationalFluidOunce(d)
    override fun asBaseUnit() = InternationalPint(value / 20).asBaseUnit()

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.divUnit(this, other)
}

fun UnitVolume<*>.toInternationalFluidOunce() = toUnit(InternationalFluidOunce(1.0))
