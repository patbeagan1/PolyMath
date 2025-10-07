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

    companion object {
        fun plusUnit(volume: UnitVolume<*>, other: UnitVolume<*>): Liter =
            Liter(volume.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(volume: UnitVolume<*>, other: UnitVolume<*>): Liter =
            Liter(volume.asBaseUnit().value - other.asBaseUnit().value)

        fun divUnit(volume: UnitVolume<*>, other: UnitArea<*>): Meter =
            Meter(volume.asBaseUnit().value / other.asBaseUnit().value)

        fun divUnit(volume: UnitVolume<*>, other: UnitDistance<*>): SquareMeter =
            SquareMeter(volume.asBaseUnit().value / other.asBaseUnit().value)
    }
}


