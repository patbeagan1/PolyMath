package com.measures.luminousflux

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitLuminousFlux<T : DoubleBase> : UnitType<T, Lumen> {
    operator fun plus(other: UnitLuminousFlux<*>): Lumen
    operator fun minus(other: UnitLuminousFlux<*>): Lumen

    companion object {
        fun plusUnit(luminousFlux: UnitLuminousFlux<*>, other: UnitLuminousFlux<*>): Lumen =
            Lumen(luminousFlux.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(luminousFlux: UnitLuminousFlux<*>, other: UnitLuminousFlux<*>): Lumen =
            Lumen(luminousFlux.asBaseUnit().value - other.asBaseUnit().value)
    }
}

