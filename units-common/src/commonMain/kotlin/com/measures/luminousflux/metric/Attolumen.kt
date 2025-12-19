package com.measures.luminousflux.metric

import com.measures.Consts
import com.measures.luminousflux.Lumen
import com.measures.luminousflux.UnitLuminousFlux
import kotlin.jvm.JvmInline

@JvmInline
value class Attolumen(override val value: Double) : UnitLuminousFlux<Attolumen> {
    override fun asType(d: Double) = Attolumen(d)
    override fun asBaseUnit() = Lumen(value * Consts.ATTO)

    override operator fun plus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.plusUnit(this, other)
    override operator fun minus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.minusUnit(this, other)
}

fun UnitLuminousFlux<*>.toAttolumen() = toUnit(Attolumen(1.0))
