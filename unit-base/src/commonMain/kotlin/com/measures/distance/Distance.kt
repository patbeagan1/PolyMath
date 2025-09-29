package com.measures.distance

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.volume.Liter

typealias UnitDistance<T> = UnitDistanceType<T>

interface UnitDistanceType<T : DoubleBase> : UnitType<T, Meter>


operator fun UnitDistanceType<*>.plus(other: UnitDistanceType<*>): Meter =
    Meter(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitDistanceType<*>.minus(other: UnitDistanceType<*>): Meter =
    Meter(this.asBaseUnit().value - other.asBaseUnit().value)

operator fun UnitDistanceType<*>.times(other: UnitDistanceType<*>): SquareMeter =
    SquareMeter(this.asBaseUnit().value * other.asBaseUnit().value)

operator fun UnitDistanceType<*>.times(other: UnitArea<*>): Liter =
    Liter(this.asBaseUnit().value * other.asBaseUnit().value * 1000)