package com.measures.luminousflux.metric

import com.measures.Consts
import com.measures.luminousflux.Lumen
import com.measures.luminousflux.UnitLuminousFlux
import kotlin.jvm.JvmInline

@JvmInline
value class Centilumen(override val value: Double) : UnitLuminousFlux<Centilumen> {
    override fun asType(d: Double) = Centilumen(d)
    override fun asBaseUnit() = Lumen(value * Consts.CENTI)

    override operator fun plus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.plusUnit(this, other)
    override operator fun minus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.minusUnit(this, other)
}

fun UnitLuminousFlux<*>.toCentilumen() = toUnit(Centilumen(1.0))
