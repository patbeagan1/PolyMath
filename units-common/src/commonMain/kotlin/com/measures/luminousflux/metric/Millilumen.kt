package com.measures.luminousflux.metric

import com.measures.Consts
import com.measures.luminousflux.Lumen
import com.measures.luminousflux.UnitLuminousFlux
import kotlin.jvm.JvmInline

@JvmInline
value class Millilumen(override val value: Double) : UnitLuminousFlux<Millilumen> {
    override fun asType(d: Double) = Millilumen(d)
    override fun asBaseUnit() = Lumen(value * Consts.MILLI)

    override operator fun plus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.plusUnit(this, other)
    override operator fun minus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.minusUnit(this, other)
}

fun UnitLuminousFlux<*>.toMillilumen() = toUnit(Millilumen(1.0))
