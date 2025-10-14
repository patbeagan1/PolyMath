package com.measures.absorbeddose.metric

import com.measures.Consts
import com.measures.absorbeddose.Gray
import com.measures.absorbeddose.UnitAbsorbedDose
import kotlin.jvm.JvmInline

@JvmInline
value class Picogray(override val value: Double) : UnitAbsorbedDose<Picogray> {
    override fun asType(d: Double) = Picogray(d)
    override fun asBaseUnit() = Gray(value * Consts.PICO)

    override operator fun plus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.plusUnit(this, other)
    override operator fun minus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.minusUnit(this, other)
}

fun UnitAbsorbedDose<*>.toPicogray() = toUnit(Picogray(1.0))
