package com.measures.absorbeddose.metric

import com.measures.Consts
import com.measures.absorbeddose.Gray
import com.measures.absorbeddose.UnitAbsorbedDose
import kotlin.jvm.JvmInline

@JvmInline
value class Zeptogray(override val value: Double) : UnitAbsorbedDose<Zeptogray> {
    override fun asType(d: Double) = Zeptogray(d)
    override fun asBaseUnit() = Gray(value * Consts.ZEPTO)

    override operator fun plus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.plusUnit(this, other)
    override operator fun minus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.minusUnit(this, other)
}

fun UnitAbsorbedDose<*>.toZeptogray() = toUnit(Zeptogray(1.0))
