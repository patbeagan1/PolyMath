package com.measures.power

import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

// Non-SI Power Units
// These units are not part of the SI system and are moved to units-common

@JvmInline
value class Horsepower(override val value: Double) : UnitPower<Horsepower> {
    override fun asType(d: Double) = Horsepower(d)
    override fun asBaseUnit() = Watt(this.value * 745.7)

    override operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plusUnit(other)
    override operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = timesUnit(this as UnitPower<*>, other)
}

@JvmInline
value class ErgPerSecond(override val value: Double) : UnitPower<ErgPerSecond> {
    override fun asType(d: Double) = ErgPerSecond(d)
    override fun asBaseUnit() = Watt(this.value * 1E-7)

    override operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plusUnit(other)
    override operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = timesUnit(this as UnitPower<*>, other)
}

@JvmInline
value class FootPoundPerSecond(override val value: Double) : UnitPower<FootPoundPerSecond> {
    override fun asType(d: Double) = FootPoundPerSecond(d)
    override fun asBaseUnit() = Watt(this.value * 1.355818)

    override operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plusUnit(other)
    override operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = timesUnit(this as UnitPower<*>, other)
}

@JvmInline
value class Milliwatt(override val value: Double) : UnitPower<Milliwatt> {
    override fun asType(d: Double) = Milliwatt(d)
    override fun asBaseUnit() = Watt(this.value * 0.001)

    override operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plusUnit(other)
    override operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = timesUnit(this as UnitPower<*>, other)
}

@JvmInline
value class Kilowatt(override val value: Double) : UnitPower<Kilowatt> {
    override fun asType(d: Double) = Kilowatt(d)
    override fun asBaseUnit() = Watt(this.value * 1000.0)

    override operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plusUnit(other)
    override operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = timesUnit(this as UnitPower<*>, other)
}

@JvmInline
value class Megawatt(override val value: Double) : UnitPower<Megawatt> {
    override fun asType(d: Double) = Megawatt(d)
    override fun asBaseUnit() = Watt(this.value * 1E6)

    override operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plusUnit(other)
    override operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = timesUnit(this as UnitPower<*>, other)
}

@JvmInline
value class Gigawatt(override val value: Double) : UnitPower<Gigawatt> {
    override fun asType(d: Double) = Gigawatt(d)
    override fun asBaseUnit() = Watt(this.value * 1E9)

    override operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plusUnit(other)
    override operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = timesUnit(this as UnitPower<*>, other)
}

// Conversion functions for non-SI power units
fun UnitPower<*>.toHorsepower() = toUnit(Horsepower(1.0))
fun UnitPower<*>.toErgPerSecond() = toUnit(ErgPerSecond(1.0))
fun UnitPower<*>.toFootPoundPerSecond() = toUnit(FootPoundPerSecond(1.0))
fun UnitPower<*>.toMilliwatt() = toUnit(Milliwatt(1.0))
fun UnitPower<*>.toKilowatt() = toUnit(Kilowatt(1.0))
fun UnitPower<*>.toMegawatt() = toUnit(Megawatt(1.0))
fun UnitPower<*>.toGigawatt() = toUnit(Gigawatt(1.0))
