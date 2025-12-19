package com.measures.absorbeddose.metric

import com.measures.Consts
import com.measures.absorbeddose.Gray
import com.measures.absorbeddose.UnitAbsorbedDose
import kotlin.jvm.JvmInline

@JvmInline
value class Yottagray(override val value: Double) : UnitAbsorbedDose<Yottagray> {
    override fun asType(d: Double) = Yottagray(d)
    override fun asBaseUnit() = Gray(value * Consts.YOTTA)

    override operator fun plus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.plusUnit(this, other)
    override operator fun minus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.minusUnit(this, other)
}

fun UnitAbsorbedDose<*>.toYottagray() = toUnit(Yottagray(1.0))
