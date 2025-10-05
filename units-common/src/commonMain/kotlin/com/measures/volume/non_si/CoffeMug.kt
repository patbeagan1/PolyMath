package com.measures.volume.non_si

import com.measures.area.UnitArea
import com.measures.area.SquareMeter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class CoffeMug(override val value: Double) : UnitVolume<CoffeMug> {
    override fun asType(d: Double) = CoffeMug(d)
    override fun asBaseUnit() = Liter(value * 0.35488235475)

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.divUnit(this, other)
}

fun UnitVolume<*>.toCoffeMug() = toUnit(CoffeMug(1.0))
