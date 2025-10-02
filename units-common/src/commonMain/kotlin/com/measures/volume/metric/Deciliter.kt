package com.measures.volume.metric

import com.measures.Consts
import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.volume.UnitVolume
import com.measures.volume.Liter
import kotlin.jvm.JvmInline

@JvmInline
value class Deciliter(override val value: Double) : UnitVolume<Deciliter> {
    override fun asType(d: Double) = Deciliter(d)
    override fun asBaseUnit() = Liter(value * Consts.DECI)

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.Companion.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.Companion.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.Companion.divUnit(this, other)
}

fun UnitVolume<*>.toDeciliter() = toUnit(Deciliter(1.0))
