package com.measures.charge

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.current.Ampere
import com.measures.time.UnitTime

interface UnitCharge<T : DoubleBase> : UnitType<T, Coulomb> {
    operator fun plus(other: UnitCharge<*>): Coulomb
    operator fun minus(other: UnitCharge<*>): Coulomb
    operator fun div(other: UnitTime<*>): Ampere
}

fun UnitCharge<*>.plusUnit(other: UnitCharge<*>): Coulomb =
    Coulomb(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitCharge<*>.minusUnit(other: UnitCharge<*>): Coulomb =
    Coulomb(this.asBaseUnit().value - other.asBaseUnit().value)

fun UnitCharge<*>.divUnit(other: UnitTime<*>): Ampere =
    Ampere(this.asBaseUnit().value / other.asBaseUnit().value)
