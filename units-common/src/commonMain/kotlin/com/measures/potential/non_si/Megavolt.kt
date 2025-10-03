package com.measures.potential.non_si

import com.measures.charge.UnitCharge
import com.measures.potential.UnitPotential
import com.measures.potential.Volt
import kotlin.jvm.JvmInline

@JvmInline
value class Megavolt(override val value: Double) : UnitPotential<Megavolt> {
override fun asType(d: Double) = Megavolt(d)
    override fun asBaseUnit() = Volt(this.value * 1E6)

    override operator fun plus(other: UnitPotential<*>) = UnitPotential.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitPotential<*>) = UnitPotential.Companion.minusUnit(this, other)
    override operator fun times(other: UnitCharge<*>) = UnitPotential.Companion.timesUnit(this, other)
}

fun UnitPotential<*>.toMegavolt() = toUnit(Megavolt(1.0))
