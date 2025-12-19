package com.measures.absorbeddose

import com.measures.BaseUnit
import kotlin.jvm.JvmInline

@JvmInline
value class Gray(override val value: Double) : UnitAbsorbedDose<Gray>, BaseUnit {
    override fun asType(d: Double) = Gray(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.plusUnit(this, other)
    override operator fun minus(other: UnitAbsorbedDose<*>) = UnitAbsorbedDose.minusUnit(this, other)
}

fun UnitAbsorbedDose<*>.toGray() = this.asBaseUnit()

