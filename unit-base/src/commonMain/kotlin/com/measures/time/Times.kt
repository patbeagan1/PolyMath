package com.measures.time

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import kotlin.jvm.JvmInline

typealias UnitTime<T> = UnitTimeType<T>

interface UnitTimeType<T : DoubleBase> : UnitType<T, Second>

@JvmInline
value class Second(override val value: Double) : UnitTime<Second>, BaseUnit {
    override fun asType(d: Double) = Second(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitTime<*>) = (this as UnitTime<*>).plus(other)
    operator fun minus(other: UnitTime<*>) = (this as UnitTime<*>).minus(other)
    operator fun div(other: UnitTime<*>) = (this as UnitTime<*>).div(other)
}

@JvmInline
value class Minute(override val value: Double) : UnitTime<Minute> {
    override fun asType(d: Double) = Minute(d)
    override fun asBaseUnit() = Second(this.value * 60.0)

    operator fun plus(other: UnitTime<*>) = (this as UnitTime<*>).plus(other)
    operator fun minus(other: UnitTime<*>) = (this as UnitTime<*>).minus(other)
}

@JvmInline
value class Hour(override val value: Double) : UnitTime<Hour> {
    override fun asType(d: Double) = Hour(d)
    override fun asBaseUnit() = Second(this.value * 3600.0)

    operator fun plus(other: UnitTime<*>) = (this as UnitTime<*>).plus(other)
    operator fun minus(other: UnitTime<*>) = (this as UnitTime<*>).minus(other)
}

@JvmInline
value class Day(override val value: Double) : UnitTime<Day> {
    override fun asType(d: Double) = Day(d)
    override fun asBaseUnit() = Second(this.value * 86400.0)

    operator fun plus(other: UnitTime<*>) = (this as UnitTime<*>).plus(other)
    operator fun minus(other: UnitTime<*>) = (this as UnitTime<*>).minus(other)
}

@JvmInline
value class Week(override val value: Double) : UnitTime<Week> {
    override fun asType(d: Double) = Week(d)
    override fun asBaseUnit() = Second(this.value * 604800.0)

    operator fun plus(other: UnitTime<*>) = (this as UnitTime<*>).plus(other)
    operator fun minus(other: UnitTime<*>) = (this as UnitTime<*>).minus(other)
}

@JvmInline
value class Millisecond(override val value: Double) : UnitTime<Millisecond> {
    override fun asType(d: Double) = Millisecond(d)
    override fun asBaseUnit() = Second(this.value * 0.001)

    operator fun plus(other: UnitTime<*>) = (this as UnitTime<*>).plus(other)
    operator fun minus(other: UnitTime<*>) = (this as UnitTime<*>).minus(other)
    operator fun div(other: UnitTime<*>) = (this as UnitTime<*>).div(other)
}

@JvmInline
value class Microsecond(override val value: Double) : UnitTime<Microsecond> {
    override fun asType(d: Double) = Microsecond(d)
    override fun asBaseUnit() = Second(this.value * 1E-6)

    operator fun plus(other: UnitTime<*>) = (this as UnitTime<*>).plus(other)
    operator fun minus(other: UnitTime<*>) = (this as UnitTime<*>).minus(other)
    operator fun div(other: UnitTime<*>) = (this as UnitTime<*>).div(other)
}

@JvmInline
value class Nanosecond(override val value: Double) : UnitTime<Nanosecond> {
    override fun asType(d: Double) = Nanosecond(d)
    override fun asBaseUnit() = Second(this.value * 1E-9)

    operator fun plus(other: UnitTime<*>) = (this as UnitTime<*>).plus(other)
    operator fun minus(other: UnitTime<*>) = (this as UnitTime<*>).minus(other)
    operator fun div(other: UnitTime<*>) = (this as UnitTime<*>).div(other)
}

operator fun UnitTimeType<*>.plus(other: UnitTimeType<*>): Second =
    Second(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitTimeType<*>.minus(other: UnitTimeType<*>): Second =
    Second(this.asBaseUnit().value - other.asBaseUnit().value)

operator fun UnitTimeType<*>.div(other: UnitTimeType<*>): Double =
    this.asBaseUnit().value / other.asBaseUnit().value

// Conversion functions using toUnit
fun UnitTime<*>.toSecond() = this.asBaseUnit()
fun UnitTime<*>.toMinute() = toUnit(Minute(1.0))
fun UnitTime<*>.toHour() = toUnit(Hour(1.0))
fun UnitTime<*>.toDay() = toUnit(Day(1.0))
fun UnitTime<*>.toWeek() = toUnit(Week(1.0))
fun UnitTime<*>.toMillisecond() = toUnit(Millisecond(1.0))
fun UnitTime<*>.toMicrosecond() = toUnit(Microsecond(1.0))
fun UnitTime<*>.toNanosecond() = toUnit(Nanosecond(1.0))
