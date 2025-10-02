package com.measures.charge

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.current.Ampere
import com.measures.time.UnitTime

interface UnitCharge<T : DoubleBase> : UnitType<T, Coulomb> {
    operator fun plus(other: UnitCharge<*>): Coulomb
    operator fun minus(other: UnitCharge<*>): Coulomb
    operator fun div(other: UnitTime<*>): Ampere

    companion object {
        fun plusUnit(charge: UnitCharge<*>, other: UnitCharge<*>): Coulomb =
            Coulomb(charge.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(charge: UnitCharge<*>, other: UnitCharge<*>): Coulomb =
            Coulomb(charge.asBaseUnit().value - other.asBaseUnit().value)

        fun divUnit(charge: UnitCharge<*>, other: UnitTime<*>): Ampere =
            Ampere(charge.asBaseUnit().value / other.asBaseUnit().value)
    }
}

