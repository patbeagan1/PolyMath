package com.measures.luminousflux.metric

import com.measures.Consts
import com.measures.luminousflux.Lumen
import com.measures.luminousflux.UnitLuminousFlux
import kotlin.jvm.JvmInline

@JvmInline
value class Petalumen(override val value: Double) : UnitLuminousFlux<Petalumen> {
    override fun asType(d: Double) = Petalumen(d)
    override fun asBaseUnit() = Lumen(value * Consts.PETA)

    override operator fun plus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.plusUnit(this, other)
    override operator fun minus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.minusUnit(this, other)
}

fun UnitLuminousFlux<*>.toPetalumen() = toUnit(Petalumen(1.0))
