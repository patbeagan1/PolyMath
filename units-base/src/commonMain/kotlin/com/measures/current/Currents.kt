package com.measures.current

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.charge.Coulomb
import com.measures.time.UnitTime

interface UnitCurrent<T : DoubleBase> : UnitType<T, Ampere> {
    operator fun plus(other: UnitCurrent<*>): Ampere
    operator fun minus(other: UnitCurrent<*>): Ampere
    operator fun times(other: UnitTime<*>): Coulomb
}

fun UnitCurrent<*>.plusUnit(other: UnitCurrent<*>): Ampere =
    Ampere(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitCurrent<*>.minusUnit(other: UnitCurrent<*>): Ampere =
    Ampere(this.asBaseUnit().value - other.asBaseUnit().value)

// Current × Time = Charge
fun UnitCurrent<*>.timesUnit(other: UnitTime<*>): Coulomb =
    Coulomb(this.asBaseUnit().value * other.asBaseUnit().value)
