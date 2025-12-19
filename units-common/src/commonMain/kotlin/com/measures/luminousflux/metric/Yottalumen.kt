package com.measures.luminousflux.metric

import com.measures.Consts
import com.measures.luminousflux.Lumen
import com.measures.luminousflux.UnitLuminousFlux
import kotlin.jvm.JvmInline

@JvmInline
value class Yottalumen(override val value: Double) : UnitLuminousFlux<Yottalumen> {
    override fun asType(d: Double) = Yottalumen(d)
    override fun asBaseUnit() = Lumen(value * Consts.YOTTA)

    override operator fun plus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.plusUnit(this, other)
    override operator fun minus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.minusUnit(this, other)
}

fun UnitLuminousFlux<*>.toYottalumen() = toUnit(Yottalumen(1.0))
