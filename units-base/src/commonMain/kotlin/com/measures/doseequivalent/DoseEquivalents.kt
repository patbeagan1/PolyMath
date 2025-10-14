package com.measures.doseequivalent

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitDoseEquivalent<T : DoubleBase> : UnitType<T, Sievert> {
    operator fun plus(other: UnitDoseEquivalent<*>): Sievert
    operator fun minus(other: UnitDoseEquivalent<*>): Sievert

    companion object {
        fun plusUnit(doseEquivalent: UnitDoseEquivalent<*>, other: UnitDoseEquivalent<*>): Sievert =
            Sievert(doseEquivalent.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(doseEquivalent: UnitDoseEquivalent<*>, other: UnitDoseEquivalent<*>): Sievert =
            Sievert(doseEquivalent.asBaseUnit().value - other.asBaseUnit().value)
    }
}

