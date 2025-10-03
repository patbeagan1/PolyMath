package com.measures.charge.non_si

import com.measures.charge.Coulomb
import com.measures.charge.UnitCharge
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class AmpereHour(override val value: Double) : UnitCharge<AmpereHour> {
override fun asType(d: Double) = AmpereHour(d)
    override fun asBaseUnit() = Coulomb(this.value * 3600.0)

    override operator fun plus(other: UnitCharge<*>) = UnitCharge.plusUnit(this, other)
    override operator fun minus(other: UnitCharge<*>) = UnitCharge.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitCharge.divUnit(this, other)
}

fun UnitCharge<*>.toAmpereHour() = toUnit(AmpereHour(1.0))
