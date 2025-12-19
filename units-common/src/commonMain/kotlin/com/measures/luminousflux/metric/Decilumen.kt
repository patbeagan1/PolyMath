package com.measures.luminousflux.metric

import com.measures.Consts
import com.measures.luminousflux.Lumen
import com.measures.luminousflux.UnitLuminousFlux
import kotlin.jvm.JvmInline

@JvmInline
value class Decilumen(override val value: Double) : UnitLuminousFlux<Decilumen> {
    override fun asType(d: Double) = Decilumen(d)
    override fun asBaseUnit() = Lumen(value * Consts.DECI)

    override operator fun plus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.plusUnit(this, other)
    override operator fun minus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.minusUnit(this, other)
}

fun UnitLuminousFlux<*>.toDecilumen() = toUnit(Decilumen(1.0))
