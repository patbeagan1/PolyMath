package com.measures.absorbeddose.metric

import com.measures.Consts
import com.measures.absorbeddose.Gray
import com.measures.absorbeddose.UnitAbsorbedDose
import kotlin.jvm.JvmInline

@JvmInline
value class Hectogray(override val value: Double) : UnitAbsorbedDose<Hectogray> {
    override fun asType(d: Double) = Hectogray(d)
    override fun asBaseUnit() = Gray(value * Consts.HECTO)

    override operator fun plus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.plusUnit(this, other)
    override operator fun minus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.minusUnit(this, other)
}

fun UnitAbsorbedDose<*>.toHectogray() = toUnit(Hectogray(1.0))
