package com.measures.illuminance

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitIlluminance<T : DoubleBase> : UnitType<T, Lux> {
    operator fun plus(other: UnitIlluminance<*>): Lux
    operator fun minus(other: UnitIlluminance<*>): Lux

    companion object {
        fun plusUnit(illuminance: UnitIlluminance<*>, other: UnitIlluminance<*>): Lux =
            Lux(illuminance.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(illuminance: UnitIlluminance<*>, other: UnitIlluminance<*>): Lux =
            Lux(illuminance.asBaseUnit().value - other.asBaseUnit().value)
    }
}

