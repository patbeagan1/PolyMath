package com.measures.absorbeddose.metric

import com.measures.Consts
import com.measures.absorbeddose.Gray
import com.measures.absorbeddose.UnitAbsorbedDose
import kotlin.jvm.JvmInline

@JvmInline
value class Zettagray(override val value: Double) : UnitAbsorbedDose<Zettagray> {
    override fun asType(d: Double) = Zettagray(d)
    override fun asBaseUnit() = Gray(value * Consts.ZETTA)

    override operator fun plus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.plusUnit(this, other)
    override operator fun minus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.minusUnit(this, other)
}

fun UnitAbsorbedDose<*>.toZettagray() = toUnit(Zettagray(1.0))
