package com.measures.power

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.distance.UnitDistance
import com.measures.energy.Joule
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

typealias UnitPower<T> = UnitPowerType<T>

interface UnitPowerType<T : DoubleBase> : UnitType<T, Watt> {
    operator fun plus(other: UnitPower<*>): Watt
    operator fun minus(other: UnitPower<*>): Watt
    operator fun times(other: UnitTime<*>): Joule
}

@JvmInline
value class Watt(override val value: Double) : UnitPower<Watt>, BaseUnit {
    override fun asType(d: Double) = Watt(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plusUnit(other)
    override operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = (this as UnitPower<*>).timesUnit(other)

    companion object {
        fun from(mass: UnitMass<*>, distance: UnitDistance<*>, time: UnitTime<*>): Watt =
            Joule.from(mass, distance, time) / time
    }
}

@JvmInline
value class Milliwatt(override val value: Double) : UnitPower<Milliwatt> {
    override fun asType(d: Double) = Milliwatt(d)
    override fun asBaseUnit() = Watt(this.value * 0.001)

    override operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plusUnit(other)
    override operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = (this as UnitPower<*>).timesUnit(other)
}

@JvmInline
value class Kilowatt(override val value: Double) : UnitPower<Kilowatt> {
    override fun asType(d: Double) = Kilowatt(d)
    override fun asBaseUnit() = Watt(this.value * 1000.0)

    override operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plusUnit(other)
    override operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = (this as UnitPower<*>).timesUnit(other)
}

@JvmInline
value class Megawatt(override val value: Double) : UnitPower<Megawatt> {
    override fun asType(d: Double) = Megawatt(d)
    override fun asBaseUnit() = Watt(this.value * 1E6)

    override operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plusUnit(other)
    override operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = (this as UnitPower<*>).timesUnit(other)
}

@JvmInline
value class Gigawatt(override val value: Double) : UnitPower<Gigawatt> {
    override fun asType(d: Double) = Gigawatt(d)
    override fun asBaseUnit() = Watt(this.value * 1E9)

    override operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plusUnit(other)
    override operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = (this as UnitPower<*>).timesUnit(other)
}

fun UnitPowerType<*>.plusUnit(other: UnitPowerType<*>): Watt =
    Watt(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitPowerType<*>.minusUnit(other: UnitPowerType<*>): Watt =
    Watt(this.asBaseUnit().value - other.asBaseUnit().value)

// Power × Time = Energy
fun UnitPowerType<*>.timesUnit(other: UnitTime<*>): Joule =
    Joule(this.asBaseUnit().value * other.asBaseUnit().value)

@JvmInline
value class Horsepower(override val value: Double) : UnitPower<Horsepower> {
    override fun asType(d: Double) = Horsepower(d)
    override fun asBaseUnit() = Watt(this.value * 745.7)

    override operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plusUnit(other)
    override operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = (this as UnitPower<*>).timesUnit(other)
}

@JvmInline
value class ErgPerSecond(override val value: Double) : UnitPower<ErgPerSecond> {
    override fun asType(d: Double) = ErgPerSecond(d)
    override fun asBaseUnit() = Watt(this.value * 1E-7)

    override operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plusUnit(other)
    override operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = (this as UnitPower<*>).timesUnit(other)
}

@JvmInline
value class FootPoundPerSecond(override val value: Double) : UnitPower<FootPoundPerSecond> {
    override fun asType(d: Double) = FootPoundPerSecond(d)
    override fun asBaseUnit() = Watt(this.value * 1.355818)

    override operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plusUnit(other)
    override operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = (this as UnitPower<*>).timesUnit(other)
}

// Conversion functions using toUnit
fun UnitPower<*>.toWatt() = this.asBaseUnit()
fun UnitPower<*>.toMilliwatt() = toUnit(Milliwatt(1.0))
fun UnitPower<*>.toKilowatt() = toUnit(Kilowatt(1.0))
fun UnitPower<*>.toMegawatt() = toUnit(Megawatt(1.0))
fun UnitPower<*>.toGigawatt() = toUnit(Gigawatt(1.0))
fun UnitPower<*>.toHorsepower() = toUnit(Horsepower(1.0))
fun UnitPower<*>.toErgPerSecond() = toUnit(ErgPerSecond(1.0))
fun UnitPower<*>.toFootPoundPerSecond() = toUnit(FootPoundPerSecond(1.0))

