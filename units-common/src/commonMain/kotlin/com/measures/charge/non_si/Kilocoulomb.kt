package com.measures.charge.non_si

import com.measures.charge.Coulomb
import com.measures.charge.UnitCharge
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Kilocoulomb(override val value: Double) : UnitCharge<Kilocoulomb> {
    override fun asType(d: Double) = Kilocoulomb(d)
    override fun asBaseUnit() = Coulomb(this.value * 1000.0)

    override operator fun plus(other: UnitCharge<*>) = UnitCharge.plusUnit(this, other)
    override operator fun minus(other: UnitCharge<*>) = UnitCharge.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitCharge.divUnit(this, other)
}

fun UnitCharge<*>.toKilocoulomb() = toUnit(Kilocoulomb(1.0))
