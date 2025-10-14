package com.measures.absorbeddose.metric

import com.measures.Consts
import com.measures.absorbeddose.Gray
import com.measures.absorbeddose.UnitAbsorbedDose
import kotlin.jvm.JvmInline

@JvmInline
value class Dekagray(override val value: Double) : UnitAbsorbedDose<Dekagray> {
    override fun asType(d: Double) = Dekagray(d)
    override fun asBaseUnit() = Gray(value * Consts.DEKA)

    override operator fun plus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.plusUnit(this, other)
    override operator fun minus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.minusUnit(this, other)
}

fun UnitAbsorbedDose<*>.toDekagray() = toUnit(Dekagray(1.0))
