package com.measures.potential.non_si

import com.measures.charge.UnitCharge
import com.measures.potential.UnitPotential
import com.measures.potential.Volt
import kotlin.jvm.JvmInline

@JvmInline
value class Millivolt(override val value: Double) : UnitPotential<Millivolt> {
    override fun asType(d: Double) = Millivolt(d)
    override fun asBaseUnit() = Volt(this.value * 0.001)

    override operator fun plus(other: UnitPotential<*>) = UnitPotential.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitPotential<*>) = UnitPotential.Companion.minusUnit(this, other)
    override operator fun times(other: UnitCharge<*>) = UnitPotential.Companion.timesUnit(this, other)
}

fun UnitPotential<*>.toMillivolt() = toUnit(Millivolt(1.0))
