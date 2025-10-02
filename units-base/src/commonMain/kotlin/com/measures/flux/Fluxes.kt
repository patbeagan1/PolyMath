package com.measures.flux

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitFlux<T : DoubleBase> : UnitType<T, Weber> {
    operator fun plus(other: UnitFlux<*>): Weber
    operator fun minus(other: UnitFlux<*>): Weber

    companion object {
        fun plusUnit(flux: UnitFlux<*>, other: UnitFlux<*>): Weber =
            Weber(flux.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(flux: UnitFlux<*>, other: UnitFlux<*>): Weber =
            Weber(flux.asBaseUnit().value - other.asBaseUnit().value)
    }
}
