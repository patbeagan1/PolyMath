package com.measures.luminousflux.metric

import com.measures.Consts
import com.measures.luminousflux.Lumen
import com.measures.luminousflux.UnitLuminousFlux
import kotlin.jvm.JvmInline

@JvmInline
value class Teralumen(override val value: Double) : UnitLuminousFlux<Teralumen> {
    override fun asType(d: Double) = Teralumen(d)
    override fun asBaseUnit() = Lumen(value * Consts.TERA)

    override operator fun plus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.plusUnit(this, other)
    override operator fun minus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.minusUnit(this, other)
}

fun UnitLuminousFlux<*>.toTeralumen() = toUnit(Teralumen(1.0))
