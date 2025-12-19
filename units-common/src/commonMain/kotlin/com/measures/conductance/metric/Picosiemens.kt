package com.measures.conductance.metric

import com.measures.Consts
import com.measures.conductance.Siemens
import com.measures.conductance.UnitConductance
import kotlin.jvm.JvmInline

@JvmInline
value class Picosiemens(override val value: Double) : UnitConductance<Picosiemens> {
    override fun asType(d: Double) = Picosiemens(d)
    override fun asBaseUnit() = Siemens(value * Consts.PICO)

    override operator fun plus(other: UnitConductance<*>) = UnitConductance.plusUnit(this, other)
    override operator fun minus(other: UnitConductance<*>) = UnitConductance.minusUnit(this, other)
}

fun UnitConductance<*>.toPicosiemens() = toUnit(Picosiemens(1.0))
