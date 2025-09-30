package com.measures.storage

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitStorage<T : DoubleBase> : UnitType<T, Byte> {
    operator fun plus(other: UnitStorage<*>): Byte
    operator fun minus(other: UnitStorage<*>): Byte
}

fun UnitStorage<*>.plusUnit(other: UnitStorage<*>): Byte =
    Byte(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitStorage<*>.minusUnit(other: UnitStorage<*>): Byte =
    Byte(this.asBaseUnit().value - other.asBaseUnit().value)
