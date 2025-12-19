package com.measures.absorbeddose.metric

import com.measures.Consts
import com.measures.absorbeddose.Gray
import com.measures.absorbeddose.UnitAbsorbedDose
import kotlin.jvm.JvmInline

@JvmInline
value class Kilogray(override val value: Double) : UnitAbsorbedDose<Kilogray> {
    override fun asType(d: Double) = Kilogray(d)
    override fun asBaseUnit() = Gray(value * Consts.KILO)

    override operator fun plus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.plusUnit(this, other)
    override operator fun minus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.minusUnit(this, other)
}

fun UnitAbsorbedDose<*>.toKilogray() = toUnit(Kilogray(1.0))
