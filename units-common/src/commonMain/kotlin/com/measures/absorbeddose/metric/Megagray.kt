package com.measures.absorbeddose.metric

import com.measures.Consts
import com.measures.absorbeddose.Gray
import com.measures.absorbeddose.UnitAbsorbedDose
import kotlin.jvm.JvmInline

@JvmInline
value class Megagray(override val value: Double) : UnitAbsorbedDose<Megagray> {
    override fun asType(d: Double) = Megagray(d)
    override fun asBaseUnit() = Gray(value * Consts.MEGA)

    override operator fun plus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.plusUnit(this, other)
    override operator fun minus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.minusUnit(this, other)
}

fun UnitAbsorbedDose<*>.toMegagray() = toUnit(Megagray(1.0))
