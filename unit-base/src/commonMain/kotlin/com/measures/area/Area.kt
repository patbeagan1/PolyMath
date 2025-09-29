package com.measures.area

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter

interface UnitArea<T : DoubleBase> : UnitType<T, SquareMeter>

operator fun UnitArea<*>.div(other: UnitDistance<*>): Meter =
    Meter(this.asBaseUnit().value / other.asBaseUnit().value)

operator fun UnitArea<*>.plus(other: UnitArea<*>): SquareMeter =
    SquareMeter(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitArea<*>.minus(other: UnitArea<*>): SquareMeter =
    SquareMeter(this.asBaseUnit().value - other.asBaseUnit().value)

operator fun UnitArea<*>.times(other: UnitDistance<*>): Liter =
    Liter(this.asBaseUnit().value * other.asBaseUnit().value * 1000)
