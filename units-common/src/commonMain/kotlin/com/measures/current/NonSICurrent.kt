package com.measures.current

import com.measures.Consts
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Milliampere(override val value: Double) : UnitCurrent<Milliampere> {
    override fun asType(d: Double) = Milliampere(d)
    override fun asBaseUnit() = Ampere(this.value * Consts.MILLI)

    override operator fun plus(other: UnitCurrent<*>) = UnitCurrent.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitCurrent<*>) = UnitCurrent.Companion.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitCurrent.Companion.timesUnit(this, other)
}

@JvmInline
value class Microampere(override val value: Double) : UnitCurrent<Microampere> {
    override fun asType(d: Double) = Microampere(d)
    override fun asBaseUnit() = Ampere(this.value * Consts.MICRO)

    override operator fun plus(other: UnitCurrent<*>) = UnitCurrent.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitCurrent<*>) = UnitCurrent.Companion.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitCurrent.Companion.timesUnit(this, other)
}

@JvmInline
value class Kiloampere(override val value: Double) : UnitCurrent<Kiloampere> {
    override fun asType(d: Double) = Kiloampere(d)
    override fun asBaseUnit() = Ampere(this.value * Consts.KILO)

    override operator fun plus(other: UnitCurrent<*>) = UnitCurrent.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitCurrent<*>) = UnitCurrent.Companion.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitCurrent.Companion.timesUnit(this, other)
}

@JvmInline
value class Megaampere(override val value: Double) : UnitCurrent<Megaampere> {
    override fun asType(d: Double) = Megaampere(d)
    override fun asBaseUnit() = Ampere(this.value * Consts.MEGA)

    override operator fun plus(other: UnitCurrent<*>) = UnitCurrent.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitCurrent<*>) = UnitCurrent.Companion.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitCurrent.Companion.timesUnit(this, other)
}

// Conversion functions using toUnit
fun UnitCurrent<*>.toMilliampere() = toUnit(Milliampere(1.0))
fun UnitCurrent<*>.toMicroampere() = toUnit(Microampere(1.0))
fun UnitCurrent<*>.toKiloampere() = toUnit(Kiloampere(1.0))
fun UnitCurrent<*>.toMegaampere() = toUnit(Megaampere(1.0))
