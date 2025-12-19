package com.measures.absorbeddose.metric

import com.measures.Consts
import com.measures.absorbeddose.Gray
import com.measures.absorbeddose.UnitAbsorbedDose
import kotlin.jvm.JvmInline

@JvmInline
value class Decigray(override val value: Double) : UnitAbsorbedDose<Decigray> {
    override fun asType(d: Double) = Decigray(d)
    override fun asBaseUnit() = Gray(value * Consts.DECI)

    override operator fun plus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.plusUnit(this, other)
    override operator fun minus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.minusUnit(this, other)
}

fun UnitAbsorbedDose<*>.toDecigray() = toUnit(Decigray(1.0))
