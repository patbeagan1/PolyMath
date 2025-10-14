package com.measures.absorbeddose

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitAbsorbedDose<T : DoubleBase> : UnitType<T, Gray> {
    operator fun plus(other: UnitAbsorbedDose<*>): Gray
    operator fun minus(other: UnitAbsorbedDose<*>): Gray

    companion object {
        fun plusUnit(absorbedDose: UnitAbsorbedDose<*>, other: UnitAbsorbedDose<*>): Gray =
            Gray(absorbedDose.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(absorbedDose: UnitAbsorbedDose<*>, other: UnitAbsorbedDose<*>): Gray =
            Gray(absorbedDose.asBaseUnit().value - other.asBaseUnit().value)
    }
}

