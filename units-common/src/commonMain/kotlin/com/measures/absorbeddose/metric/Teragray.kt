package com.measures.absorbeddose.metric

import com.measures.Consts
import com.measures.absorbeddose.Gray
import com.measures.absorbeddose.UnitAbsorbedDose
import kotlin.jvm.JvmInline

@JvmInline
value class Teragray(override val value: Double) : UnitAbsorbedDose<Teragray> {
    override fun asType(d: Double) = Teragray(d)
    override fun asBaseUnit() = Gray(value * Consts.TERA)

    override operator fun plus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.plusUnit(this, other)
    override operator fun minus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.minusUnit(this, other)
}

fun UnitAbsorbedDose<*>.toTeragray() = toUnit(Teragray(1.0))
