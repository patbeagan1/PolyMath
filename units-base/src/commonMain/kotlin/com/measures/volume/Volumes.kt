package com.measures.volume

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.Meter
import com.measures.distance.UnitDistance

interface UnitVolume<T : DoubleBase> : UnitType<T, Liters> {
    operator fun plus(other: UnitVolume<*>): Liters
    operator fun minus(other: UnitVolume<*>): Liters
    operator fun div(other: UnitArea<*>): Meter
    operator fun div(other: UnitDistance<*>): SquareMeter

    companion object {
        fun plusUnit(volume: UnitVolume<*>, other: UnitVolume<*>): Liters =
            Liters(volume.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(volume: UnitVolume<*>, other: UnitVolume<*>): Liters =
            Liters(volume.asBaseUnit().value - other.asBaseUnit().value)

        fun divUnit(volume: UnitVolume<*>, other: UnitArea<*>): Meter =
            Meter(volume.asBaseUnit().value / other.asBaseUnit().value)

        fun divUnit(volume: UnitVolume<*>, other: UnitDistance<*>): SquareMeter =
            SquareMeter(volume.asBaseUnit().value / other.asBaseUnit().value)
    }
}


