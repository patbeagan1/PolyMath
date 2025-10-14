package com.measures.absorbeddose.metric

import com.measures.Consts
import com.measures.absorbeddose.Gray
import com.measures.absorbeddose.UnitAbsorbedDose
import kotlin.jvm.JvmInline

@JvmInline
value class Microgray(override val value: Double) : UnitAbsorbedDose<Microgray> {
    override fun asType(d: Double) = Microgray(d)
    override fun asBaseUnit() = Gray(value * Consts.MICRO)

    override operator fun plus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.plusUnit(this, other)
    override operator fun minus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.minusUnit(this, other)
}

fun UnitAbsorbedDose<*>.toMicrogray() = toUnit(Microgray(1.0))
