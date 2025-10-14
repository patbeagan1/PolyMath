package com.measures.absorbeddose.metric

import com.measures.Consts
import com.measures.absorbeddose.Gray
import com.measures.absorbeddose.UnitAbsorbedDose
import kotlin.jvm.JvmInline

@JvmInline
value class Yoctogray(override val value: Double) : UnitAbsorbedDose<Yoctogray> {
    override fun asType(d: Double) = Yoctogray(d)
    override fun asBaseUnit() = Gray(value * Consts.YOCTO)

    override operator fun plus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.plusUnit(this, other)
    override operator fun minus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.minusUnit(this, other)
}

fun UnitAbsorbedDose<*>.toYoctogray() = toUnit(Yoctogray(1.0))
