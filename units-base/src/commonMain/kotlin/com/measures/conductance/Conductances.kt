package com.measures.conductance

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitConductance<T : DoubleBase> : UnitType<T, Siemens> {
    operator fun plus(other: UnitConductance<*>): Siemens
    operator fun minus(other: UnitConductance<*>): Siemens

    companion object {
        fun plusUnit(conductance: UnitConductance<*>, other: UnitConductance<*>): Siemens =
            Siemens(conductance.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(conductance: UnitConductance<*>, other: UnitConductance<*>): Siemens =
            Siemens(conductance.asBaseUnit().value - other.asBaseUnit().value)
    }
}

