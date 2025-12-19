package com.measures.absorbeddose.metric

import com.measures.Consts
import com.measures.absorbeddose.Gray
import com.measures.absorbeddose.UnitAbsorbedDose
import kotlin.jvm.JvmInline

@JvmInline
value class Petagray(override val value: Double) : UnitAbsorbedDose<Petagray> {
    override fun asType(d: Double) = Petagray(d)
    override fun asBaseUnit() = Gray(value * Consts.PETA)

    override operator fun plus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.plusUnit(this, other)
    override operator fun minus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.minusUnit(this, other)
}

fun UnitAbsorbedDose<*>.toPetagray() = toUnit(Petagray(1.0))
