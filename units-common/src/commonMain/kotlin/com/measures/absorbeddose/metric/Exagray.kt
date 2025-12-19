package com.measures.absorbeddose.metric

import com.measures.Consts
import com.measures.absorbeddose.Gray
import com.measures.absorbeddose.UnitAbsorbedDose
import kotlin.jvm.JvmInline

@JvmInline
value class Exagray(override val value: Double) : UnitAbsorbedDose<Exagray> {
    override fun asType(d: Double) = Exagray(d)
    override fun asBaseUnit() = Gray(value * Consts.EXA)

    override operator fun plus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.plusUnit(this, other)
    override operator fun minus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.minusUnit(this, other)
}

fun UnitAbsorbedDose<*>.toExagray() = toUnit(Exagray(1.0))
