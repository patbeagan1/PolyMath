package com.measures.volume

import com.measures.DoubleBase
import com.measures.UnitTypedFull
import com.measures.area.SquareMeter
import com.measures.area.UnitAreaTypedFull
import com.measures.distance.Meter

typealias UnitVolume<T> = UnitVolumeTypedFull<T>

interface UnitVolumeTypedFull<T : DoubleBase> : UnitTypedFull<T, Liter> {
    operator fun plus(other: UnitVolumeTypedFull<*>) =
        Liter(this.asBaseUnit().value + other.asBaseUnit().value)

    operator fun minus(other: UnitVolumeTypedFull<*>) =
        Liter(this.asBaseUnit().value - other.asBaseUnit().value)

    operator fun div(other: UnitAreaTypedFull<*>) =
        Meter(this.asBaseUnit().value / other.asBaseUnit().value)

    operator fun div(other: com.measures.distance.UnitDistanceTypedFull<*>) =
        SquareMeter(this.asBaseUnit().value / other.asBaseUnit().value)
}


