package com.measures.distance

import com.measures.DoubleBase
import com.measures.UnitTypedFull
import com.measures.area.SquareMeter
import com.measures.area.UnitAreaTypedFull
import com.measures.volume.Liter

typealias UnitDistance<T> = UnitDistanceTypedFull<T>

interface UnitDistanceTypedFull<T : DoubleBase> : UnitTypedFull<T, Meter> {
    operator fun plus(other: UnitDistanceTypedFull<*>) =
        Meter(this.asBaseUnit().value + other.asBaseUnit().value)

    operator fun minus(other: UnitDistanceTypedFull<*>) =
        Meter(this.asBaseUnit().value - other.asBaseUnit().value)

    operator fun times(other: UnitDistanceTypedFull<*>) =
        SquareMeter(this.asBaseUnit().value * other.asBaseUnit().value)

    operator fun times(other: UnitAreaTypedFull<*>) =
        Liter(this.asBaseUnit().value * other.asBaseUnit().value * 1000)
}

