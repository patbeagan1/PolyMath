package com.measures.distance

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.volume.Liter

interface UnitDistance<T : DoubleBase> : UnitType<T, Meter> 

operator fun UnitDistance<*>.plus(other: UnitDistance<*>): Meter =
    Meter(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitDistance<*>.minus(other: UnitDistance<*>): Meter =
    Meter(this.asBaseUnit().value - other.asBaseUnit().value)

operator fun UnitDistance<*>.times(other: UnitDistance<*>): SquareMeter =
    SquareMeter(this.asBaseUnit().value * other.asBaseUnit().value)

operator fun UnitDistance<*>.times(other: UnitArea<*>): Liter =
    Liter(this.asBaseUnit().value * other.asBaseUnit().value * 1000)
