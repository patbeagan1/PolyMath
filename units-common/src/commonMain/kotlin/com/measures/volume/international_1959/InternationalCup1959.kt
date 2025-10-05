package com.measures.volume.international_1959

import com.measures.area.UnitArea
import com.measures.area.SquareMeter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class InternationalCup1959(override val value: Double) : UnitVolume<InternationalCup1959> {
    override fun asType(d: Double) = InternationalCup1959(d)
    override fun asBaseUnit() = InternationalFluidOunce1959(value * 8).asBaseUnit()

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.divUnit(this, other)
}

fun UnitVolume<*>.toInternationalCup1959() = toUnit(InternationalCup1959(1.0))
