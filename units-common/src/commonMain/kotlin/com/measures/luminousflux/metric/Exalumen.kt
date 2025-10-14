package com.measures.luminousflux.metric

import com.measures.Consts
import com.measures.luminousflux.Lumen
import com.measures.luminousflux.UnitLuminousFlux
import kotlin.jvm.JvmInline

@JvmInline
value class Exalumen(override val value: Double) : UnitLuminousFlux<Exalumen> {
    override fun asType(d: Double) = Exalumen(d)
    override fun asBaseUnit() = Lumen(value * Consts.EXA)

    override operator fun plus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.plusUnit(this, other)
    override operator fun minus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.minusUnit(this, other)
}

fun UnitLuminousFlux<*>.toExalumen() = toUnit(Exalumen(1.0))
