package com.measures.volume

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.Meter

typealias UnitVolume<T> = UnitVolumeType<T>

interface UnitVolumeType<T : DoubleBase> : UnitType<T, Liter>


operator fun UnitVolumeType<*>.plus(other: UnitVolumeType<*>): Liter =
    Liter(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitVolumeType<*>.minus(other: UnitVolumeType<*>): Liter =
    Liter(this.asBaseUnit().value - other.asBaseUnit().value)

operator fun UnitVolumeType<*>.div(other: UnitArea<*>): Meter =
    Meter(this.asBaseUnit().value / other.asBaseUnit().value)

operator fun UnitVolumeType<*>.div(other: com.measures.distance.UnitDistanceType<*>): SquareMeter =
    SquareMeter(this.asBaseUnit().value / other.asBaseUnit().value)


