package com.measures.time

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.frequency.Hertz

interface UnitTime<T : DoubleBase> : UnitType<T, Second> {
    operator fun plus(other: UnitTime<*>): Second
    operator fun minus(other: UnitTime<*>): Second
    operator fun inv(): Hertz

    companion object {
        fun plusUnit(time: UnitTime<*>, other: UnitTime<*>): Second =
            Second(time.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(time: UnitTime<*>, other: UnitTime<*>): Second =
            Second(time.asBaseUnit().value - other.asBaseUnit().value)

        // Time to Frequency conversion: 1/time = frequency
        fun invUnit(time: UnitTime<*>): Hertz =
            Hertz(1.0 / time.asBaseUnit().value)
    }
}
