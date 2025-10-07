package com.measures.power

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.energy.Joule
import com.measures.time.UnitTime

interface UnitPower<T : DoubleBase> : UnitType<T, Watt> {
    operator fun plus(other: UnitPower<*>): Watt
    operator fun minus(other: UnitPower<*>): Watt
    operator fun times(other: UnitTime<*>): Joule

    companion object {
        fun plusUnit(power: UnitPower<*>, other: UnitPower<*>): Watt =
            Watt(power.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(power: UnitPower<*>, other: UnitPower<*>): Watt =
            Watt(power.asBaseUnit().value - other.asBaseUnit().value)

        fun timesUnit(power: UnitPower<*>, other: UnitTime<*>): Joule =
            Joule(power.asBaseUnit().value * other.asBaseUnit().value)
    }
}
