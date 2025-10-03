package com.measures.charge.non_si

import com.measures.charge.Coulomb
import com.measures.charge.UnitCharge
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Millicoulomb(override val value: Double) : UnitCharge<Millicoulomb> {
    override fun asType(d: Double) = Millicoulomb(d)
    override fun asBaseUnit() = Coulomb(this.value * 0.001)

    override operator fun plus(other: UnitCharge<*>) = UnitCharge.plusUnit(this, other)
    override operator fun minus(other: UnitCharge<*>) = UnitCharge.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitCharge.divUnit(this, other)
}

fun UnitCharge<*>.toMillicoulomb() = toUnit(Millicoulomb(1.0))
