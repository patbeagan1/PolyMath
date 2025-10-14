package com.measures.luminousflux.metric

import com.measures.Consts
import com.measures.luminousflux.Lumen
import com.measures.luminousflux.UnitLuminousFlux
import kotlin.jvm.JvmInline

@JvmInline
value class Zettalumen(override val value: Double) : UnitLuminousFlux<Zettalumen> {
    override fun asType(d: Double) = Zettalumen(d)
    override fun asBaseUnit() = Lumen(value * Consts.ZETTA)

    override operator fun plus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.plusUnit(this, other)
    override operator fun minus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.minusUnit(this, other)
}

fun UnitLuminousFlux<*>.toZettalumen() = toUnit(Zettalumen(1.0))
