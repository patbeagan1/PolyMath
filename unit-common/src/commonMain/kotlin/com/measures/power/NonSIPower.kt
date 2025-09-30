package com.measures.power

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.energy.Joule
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

// Non-SI Power Units
// These units are not part of the SI system and are moved to units-common

@JvmInline
value class Horsepower(override val value: Double) : UnitPower<Horsepower> {
    override fun asType(d: Double) = Horsepower(d)
    override fun asBaseUnit() = com.measures.power.Watt(this.value * 745.7)

    override operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plusUnit(other)
    override operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = (this as UnitPower<*>).timesUnit(other)
}

@JvmInline
value class ErgPerSecond(override val value: Double) : UnitPower<ErgPerSecond> {
    override fun asType(d: Double) = ErgPerSecond(d)
    override fun asBaseUnit() = com.measures.power.Watt(this.value * 1E-7)

    override operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plusUnit(other)
    override operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = (this as UnitPower<*>).timesUnit(other)
}

@JvmInline
value class FootPoundPerSecond(override val value: Double) : UnitPower<FootPoundPerSecond> {
    override fun asType(d: Double) = FootPoundPerSecond(d)
    override fun asBaseUnit() = com.measures.power.Watt(this.value * 1.355818)

    override operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plusUnit(other)
    override operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = (this as UnitPower<*>).timesUnit(other)
}

// Conversion functions for non-SI power units
fun UnitPower<*>.toHorsepower() = toUnit(Horsepower(1.0))
fun UnitPower<*>.toErgPerSecond() = toUnit(ErgPerSecond(1.0))
fun UnitPower<*>.toFootPoundPerSecond() = toUnit(FootPoundPerSecond(1.0))
