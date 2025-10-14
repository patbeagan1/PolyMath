package com.measures.absorbeddose.metric

import com.measures.Consts
import com.measures.absorbeddose.Gray
import com.measures.absorbeddose.UnitAbsorbedDose
import kotlin.jvm.JvmInline

@JvmInline
value class Attogray(override val value: Double) : UnitAbsorbedDose<Attogray> {
    override fun asType(d: Double) = Attogray(d)
    override fun asBaseUnit() = Gray(value * Consts.ATTO)

    override operator fun plus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.plusUnit(this, other)
    override operator fun minus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.minusUnit(this, other)
}

fun UnitAbsorbedDose<*>.toAttogray() = toUnit(Attogray(1.0))
