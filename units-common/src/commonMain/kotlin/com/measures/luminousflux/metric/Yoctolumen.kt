package com.measures.luminousflux.metric

import com.measures.Consts
import com.measures.luminousflux.Lumen
import com.measures.luminousflux.UnitLuminousFlux
import kotlin.jvm.JvmInline

@JvmInline
value class Yoctolumen(override val value: Double) : UnitLuminousFlux<Yoctolumen> {
    override fun asType(d: Double) = Yoctolumen(d)
    override fun asBaseUnit() = Lumen(value * Consts.YOCTO)

    override operator fun plus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.plusUnit(this, other)
    override operator fun minus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.minusUnit(this, other)
}

fun UnitLuminousFlux<*>.toYoctolumen() = toUnit(Yoctolumen(1.0))
