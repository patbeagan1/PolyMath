package com.measures.charge.non_si

import com.measures.charge.UnitCharge
import com.measures.charge.Coulomb
import com.measures.current.Ampere
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Amperehour(override val value: Double) : UnitCharge<Amperehour> {
    override fun asType(d: Double) = Amperehour(d)
    override fun asBaseUnit() = Coulomb(value * 3600.0)

    override operator fun plus(other: UnitCharge<*>) = UnitCharge.plusUnit(this, other)
    override operator fun minus(other: UnitCharge<*>) = UnitCharge.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>): Ampere = Ampere(this.value / other.asBaseUnit().value)
}

fun UnitCharge<*>.toAmperehour() = toUnit(Amperehour(1.0))
