package com.measures.fluxdensity

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitFluxDensity<T : DoubleBase> : UnitType<T, Tesla> {
    operator fun plus(other: UnitFluxDensity<*>): Tesla
    operator fun minus(other: UnitFluxDensity<*>): Tesla

    companion object {
        fun plusUnit(fluxDensity: UnitFluxDensity<*>, other: UnitFluxDensity<*>): Tesla =
            Tesla(fluxDensity.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(fluxDensity: UnitFluxDensity<*>, other: UnitFluxDensity<*>): Tesla =
            Tesla(fluxDensity.asBaseUnit().value - other.asBaseUnit().value)
    }
}
