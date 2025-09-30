package com.measures.volume

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.Meter
import com.measures.distance.UnitDistance

interface UnitVolume<T : DoubleBase> : UnitType<T, Liter> {
    operator fun plus(other: UnitVolume<*>): Liter
    operator fun minus(other: UnitVolume<*>): Liter
    operator fun div(other: UnitArea<*>): Meter
    operator fun div(other: UnitDistance<*>): SquareMeter
}

fun UnitVolume<*>.plusUnit(other: UnitVolume<*>): Liter =
    Liter(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitVolume<*>.minusUnit(other: UnitVolume<*>): Liter =
    Liter(this.asBaseUnit().value - other.asBaseUnit().value)

fun UnitVolume<*>.divUnit(other: UnitArea<*>): Meter =
    Meter(this.asBaseUnit().value / other.asBaseUnit().value)

fun UnitVolume<*>.divUnit(other: UnitDistance<*>): SquareMeter =
    SquareMeter(this.asBaseUnit().value / other.asBaseUnit().value)


