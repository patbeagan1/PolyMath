package com.measures.area

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter

interface UnitArea<T : DoubleBase> : UnitType<T, SquareMeter>{
    operator fun plus(other: UnitArea<*>): SquareMeter
    operator fun minus(other: UnitArea<*>): SquareMeter
    operator fun times(other: UnitDistance<*>): Liter
    operator fun div(other: UnitDistance<*>): Meter

    companion object {
        fun divUnit(area: UnitArea<*>, other: UnitDistance<*>): Meter =
            Meter(area.asBaseUnit().value / other.asBaseUnit().value)

        fun plusUnit(area: UnitArea<*>, other: UnitArea<*>): SquareMeter =
            SquareMeter(area.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(area: UnitArea<*>, other: UnitArea<*>): SquareMeter =
            SquareMeter(area.asBaseUnit().value - other.asBaseUnit().value)

        fun timesUnit(area: UnitArea<*>, other: UnitDistance<*>): Liter =
            Liter(area.asBaseUnit().value * other.asBaseUnit().value * 1000)
    }
}
