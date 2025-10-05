package com.measures.volume.international_1959

import com.measures.area.UnitArea
import com.measures.area.SquareMeter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class InternationalFluidOunce1959(override val value: Double) : UnitVolume<InternationalFluidOunce1959> {
    override fun asType(d: Double) = InternationalFluidOunce1959(d)
    override fun asBaseUnit() = Liter(value * 0.0295735295625)

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.divUnit(this, other)
}

fun UnitVolume<*>.toInternationalFluidOunce1959() = toUnit(InternationalFluidOunce1959(1.0))
