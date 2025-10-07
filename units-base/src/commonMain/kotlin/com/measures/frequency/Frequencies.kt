package com.measures.frequency

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.time.Second

interface UnitFrequency<T : DoubleBase> : UnitType<T, Hertz> {
    operator fun plus(other: UnitFrequency<*>): Hertz
    operator fun minus(other: UnitFrequency<*>): Hertz
    operator fun inv(): Second

    companion object {
        fun plusUnit(frequency: UnitFrequency<*>, other: UnitFrequency<*>): Hertz =
            Hertz(frequency.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(frequency: UnitFrequency<*>, other: UnitFrequency<*>): Hertz =
            Hertz(frequency.asBaseUnit().value - other.asBaseUnit().value)

        fun invUnit(frequency: UnitFrequency<*>): Second =
            Second(1.0 / frequency.asBaseUnit().value)
    }
}
