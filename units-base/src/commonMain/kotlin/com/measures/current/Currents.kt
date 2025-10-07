package com.measures.current

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.charge.Coulomb
import com.measures.time.UnitTime

interface UnitCurrent<T : DoubleBase> : UnitType<T, Ampere> {
    operator fun plus(other: UnitCurrent<*>): Ampere
    operator fun minus(other: UnitCurrent<*>): Ampere
    operator fun times(other: UnitTime<*>): Coulomb

    companion object {
        fun plusUnit(current: UnitCurrent<*>, other: UnitCurrent<*>): Ampere =
            Ampere(current.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(current: UnitCurrent<*>, other: UnitCurrent<*>): Ampere =
            Ampere(current.asBaseUnit().value - other.asBaseUnit().value)

        // Current × Time = Charge
        fun timesUnit(current: UnitCurrent<*>, other: UnitTime<*>): Coulomb =
            Coulomb(current.asBaseUnit().value * other.asBaseUnit().value)
    }
}
