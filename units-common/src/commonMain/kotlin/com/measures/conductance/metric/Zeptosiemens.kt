package com.measures.conductance.metric

import com.measures.Consts
import com.measures.conductance.Siemens
import com.measures.conductance.UnitConductance
import kotlin.jvm.JvmInline

@JvmInline
value class Zeptosiemens(override val value: Double) : UnitConductance<Zeptosiemens> {
    override fun asType(d: Double) = Zeptosiemens(d)
    override fun asBaseUnit() = Siemens(value * Consts.ZEPTO)

    override operator fun plus(other: UnitConductance<*>) = UnitConductance.plusUnit(this, other)
    override operator fun minus(other: UnitConductance<*>) = UnitConductance.minusUnit(this, other)
}

fun UnitConductance<*>.toZeptosiemens() = toUnit(Zeptosiemens(1.0))
