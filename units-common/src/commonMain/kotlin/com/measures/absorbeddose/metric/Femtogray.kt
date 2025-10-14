package com.measures.absorbeddose.metric

import com.measures.Consts
import com.measures.absorbeddose.Gray
import com.measures.absorbeddose.UnitAbsorbedDose
import kotlin.jvm.JvmInline

@JvmInline
value class Femtogray(override val value: Double) : UnitAbsorbedDose<Femtogray> {
    override fun asType(d: Double) = Femtogray(d)
    override fun asBaseUnit() = Gray(value * Consts.FEMTO)

    override operator fun plus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.plusUnit(this, other)
    override operator fun minus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.minusUnit(this, other)
}

fun UnitAbsorbedDose<*>.toFemtogray() = toUnit(Femtogray(1.0))
