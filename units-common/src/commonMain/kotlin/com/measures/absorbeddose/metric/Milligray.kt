package com.measures.absorbeddose.metric

import com.measures.Consts
import com.measures.absorbeddose.Gray
import com.measures.absorbeddose.UnitAbsorbedDose
import kotlin.jvm.JvmInline

@JvmInline
value class Milligray(override val value: Double) : UnitAbsorbedDose<Milligray> {
    override fun asType(d: Double) = Milligray(d)
    override fun asBaseUnit() = Gray(value * Consts.MILLI)

    override operator fun plus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.plusUnit(this, other)
    override operator fun minus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.minusUnit(this, other)
}

fun UnitAbsorbedDose<*>.toMilligray() = toUnit(Milligray(1.0))
