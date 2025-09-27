package com.measures.area

import com.measures.DoubleBase
import com.measures.UnitTypedFull
import com.measures.volume.Liter

typealias UnitArea<T> = UnitAreaTypedFull<T>

interface UnitAreaTypedFull<T : DoubleBase> : UnitTypedFull<T, SquareMeter> {
    operator fun plus(other: UnitAreaTypedFull<*>): SquareMeter =
        SquareMeter(this.asBaseUnit().value + other.asBaseUnit().value)

    operator fun minus(other: UnitAreaTypedFull<*>): SquareMeter =
        SquareMeter(this.asBaseUnit().value - other.asBaseUnit().value)

    operator fun div(other: com.measures.distance.UnitDistanceTypedFull<*>): com.measures.distance.Meter =
        com.measures.distance.Meter(this.asBaseUnit().value / other.asBaseUnit().value)

    operator fun times(other: com.measures.distance.UnitDistanceTypedFull<*>): Liter =
        Liter(this.asBaseUnit().value * other.asBaseUnit().value * 1000)
}
