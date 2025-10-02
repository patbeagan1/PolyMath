package com.measures.distance

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.time.UnitTime
import com.measures.velocity.MetersPerSecond
import com.measures.volume.Liter

interface UnitDistance<T : DoubleBase> : UnitType<T, Meter> {
    operator fun plus(other: UnitDistance<*>): Meter
    operator fun minus(other: UnitDistance<*>): Meter
    operator fun times(other: UnitDistance<*>): SquareMeter
    operator fun times(other: UnitArea<*>): Liter
    operator fun div(other: UnitTime<*>): MetersPerSecond

    companion object {
        fun plusUnit(distance: UnitDistance<*>, other: UnitDistance<*>): Meter =
            Meter(distance.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(distance: UnitDistance<*>, other: UnitDistance<*>): Meter =
            Meter(distance.asBaseUnit().value - other.asBaseUnit().value)

        fun timesUnit(distance: UnitDistance<*>, other: UnitDistance<*>): SquareMeter =
            SquareMeter(distance.asBaseUnit().value * other.asBaseUnit().value)

        fun timesUnit(distance: UnitDistance<*>, other: UnitArea<*>): Liter =
            Liter(distance.asBaseUnit().value * other.asBaseUnit().value * 1000)

        // Distance ÷ Time = Velocity
        fun divUnit(distance: UnitDistance<*>, other: UnitTime<*>): MetersPerSecond =
            MetersPerSecond(distance.asBaseUnit().value / other.asBaseUnit().value)
    }
}
