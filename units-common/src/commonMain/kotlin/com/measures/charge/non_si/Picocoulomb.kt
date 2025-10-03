package com.measures.charge.non_si

import com.measures.charge.Coulomb
import com.measures.charge.UnitCharge
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Picocoulomb(override val value: Double) : UnitCharge<Picocoulomb> {
override fun asType(d: Double) = Picocoulomb(d)
    override fun asBaseUnit() = Coulomb(this.value * 1E-12)

    override operator fun plus(other: UnitCharge<*>) = UnitCharge.plusUnit(this, other)
    override operator fun minus(other: UnitCharge<*>) = UnitCharge.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitCharge.divUnit(this, other)
}

fun UnitCharge<*>.toPicocoulomb() = toUnit(Picocoulomb(1.0))
