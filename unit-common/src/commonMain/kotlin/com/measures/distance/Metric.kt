package com.measures.distance

import com.measures.Consts
import com.measures.area.UnitArea
import com.measures.time.UnitTime
import com.measures.velocity.MetersPerSecond
import kotlin.jvm.JvmInline

// Metric Distance Units with SI Prefixes
fun UnitDistance<*>.toAttometer() = toUnit(Attometer(1.0))
fun UnitDistance<*>.toCentimeter() = toUnit(Centimeter(1.0))
fun UnitDistance<*>.toDecimeter() = toUnit(Decimeter(1.0))
fun UnitDistance<*>.toDekameter() = toUnit(Dekameter(1.0))
fun UnitDistance<*>.toExameter() = toUnit(Exameter(1.0))
fun UnitDistance<*>.toFemtometer() = toUnit(Femtometer(1.0))
fun UnitDistance<*>.toGigameter() = toUnit(Gigameter(1.0))
fun UnitDistance<*>.toHectometer() = toUnit(Hectometer(1.0))
fun UnitDistance<*>.toKilometer() = toUnit(Kilometer(1.0))
fun UnitDistance<*>.toMegameter() = toUnit(Megameter(1.0))
fun UnitDistance<*>.toMicrometer() = toUnit(Micrometer(1.0))
fun UnitDistance<*>.toMillimeter() = toUnit(Millimeter(1.0))
fun UnitDistance<*>.toNanometer() = toUnit(Nanometer(1.0))
fun UnitDistance<*>.toPetameter() = toUnit(Petameter(1.0))
fun UnitDistance<*>.toPicometer() = toUnit(Picometer(1.0))
fun UnitDistance<*>.toTerameter() = toUnit(Terameter(1.0))
fun UnitDistance<*>.toYoctometer() = toUnit(Yoctometer(1.0))
fun UnitDistance<*>.toYottameter() = toUnit(Yottameter(1.0))
fun UnitDistance<*>.toZeptometer() = toUnit(Zeptometer(1.0))
fun UnitDistance<*>.toZettameter() = toUnit(Zettameter(1.0))

@JvmInline
value class Attometer(override val value: Double) : UnitDistance<Attometer> {
    override fun asType(d: Double) = Attometer(d)
    override fun asBaseUnit() = Meter(value * Consts.ATTO)

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond  = (this as UnitDistance<*>).divUnit(other)
}

@JvmInline
value class Centimeter(override val value: Double) : UnitDistance<Centimeter> {
    override fun asType(d: Double) = Centimeter(d)
    override fun asBaseUnit() = Meter(value * Consts.CENTI)

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond  = (this as UnitDistance<*>).divUnit(other)
}

@JvmInline
value class Decimeter(override val value: Double) : UnitDistance<Decimeter> {
    override fun asType(d: Double) = Decimeter(d)
    override fun asBaseUnit() = Meter(value * Consts.DECI)

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond  = (this as UnitDistance<*>).divUnit(other)
}

@JvmInline
value class Dekameter(override val value: Double) : UnitDistance<Dekameter> {
    override fun asType(d: Double) = Dekameter(d)
    override fun asBaseUnit() = Meter(value * Consts.DEKA)

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond  = (this as UnitDistance<*>).divUnit(other)
}

@JvmInline
value class Exameter(override val value: Double) : UnitDistance<Exameter> {
    override fun asType(d: Double) = Exameter(d)
    override fun asBaseUnit() = Meter(value * Consts.EXA)

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond  = (this as UnitDistance<*>).divUnit(other)
}

@JvmInline
value class Femtometer(override val value: Double) : UnitDistance<Femtometer> {
    override fun asType(d: Double) = Femtometer(d)
    override fun asBaseUnit() = Meter(value * Consts.FEMTO)

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond  = (this as UnitDistance<*>).divUnit(other)
}

@JvmInline
value class Gigameter(override val value: Double) : UnitDistance<Gigameter> {
    override fun asType(d: Double) = Gigameter(d)
    override fun asBaseUnit() = Meter(value * Consts.GIGA)

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond  = (this as UnitDistance<*>).divUnit(other)
}

@JvmInline
value class Hectometer(override val value: Double) : UnitDistance<Hectometer> {
    override fun asType(d: Double) = Hectometer(d)
    override fun asBaseUnit() = Meter(value * Consts.HECTO)

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond  = (this as UnitDistance<*>).divUnit(other)
}

@JvmInline
value class Kilometer(override val value: Double) : UnitDistance<Kilometer> {
    override fun asType(d: Double) = Kilometer(d)
    override fun asBaseUnit() = Meter(value * Consts.KILO)

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond  = (this as UnitDistance<*>).divUnit(other)
}

@JvmInline
value class Megameter(override val value: Double) : UnitDistance<Megameter> {
    override fun asType(d: Double) = Megameter(d)
    override fun asBaseUnit() = Meter(value * Consts.MEGA)

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond  = (this as UnitDistance<*>).divUnit(other)
}

@JvmInline
value class Micrometer(override val value: Double) : UnitDistance<Micrometer> {
    override fun asType(d: Double) = Micrometer(d)
    override fun asBaseUnit() = Meter(value * Consts.MICRO)

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond  = (this as UnitDistance<*>).divUnit(other)
}

@JvmInline
value class Millimeter(override val value: Double) : UnitDistance<Millimeter> {
    override fun asType(d: Double) = Millimeter(d)
    override fun asBaseUnit() = Meter(value * Consts.MILLI)

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond  = (this as UnitDistance<*>).divUnit(other)
}

@JvmInline
value class Nanometer(override val value: Double) : UnitDistance<Nanometer> {
    override fun asType(d: Double) = Nanometer(d)
    override fun asBaseUnit() = Meter(value * Consts.NANO)

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond  = (this as UnitDistance<*>).divUnit(other)
}

@JvmInline
value class Petameter(override val value: Double) : UnitDistance<Petameter> {
    override fun asType(d: Double) = Petameter(d)
    override fun asBaseUnit() = Meter(value * Consts.PETA)

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond  = (this as UnitDistance<*>).divUnit(other)
}

@JvmInline
value class Picometer(override val value: Double) : UnitDistance<Picometer> {
    override fun asType(d: Double) = Picometer(d)
    override fun asBaseUnit() = Meter(value * Consts.PICO)

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond  = (this as UnitDistance<*>).divUnit(other)
}

@JvmInline
value class Terameter(override val value: Double) : UnitDistance<Terameter> {
    override fun asType(d: Double) = Terameter(d)
    override fun asBaseUnit() = Meter(value * Consts.TERA)

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond  = (this as UnitDistance<*>).divUnit(other)
}

@JvmInline
value class Yoctometer(override val value: Double) : UnitDistance<Yoctometer> {
    override fun asType(d: Double) = Yoctometer(d)
    override fun asBaseUnit() = Meter(value * Consts.YOCTO)

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond  = (this as UnitDistance<*>).divUnit(other)
}

@JvmInline
value class Yottameter(override val value: Double) : UnitDistance<Yottameter> {
    override fun asType(d: Double) = Yottameter(d)
    override fun asBaseUnit() = Meter(value * Consts.YOTTA)

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond  = (this as UnitDistance<*>).divUnit(other)
}

@JvmInline
value class Zeptometer(override val value: Double) : UnitDistance<Zeptometer> {
    override fun asType(d: Double) = Zeptometer(d)
    override fun asBaseUnit() = Meter(value * Consts.ZEPTO)

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond  = (this as UnitDistance<*>).divUnit(other)
}

@JvmInline
value class Zettameter(override val value: Double) : UnitDistance<Zettameter> {
    override fun asType(d: Double) = Zettameter(d)
    override fun asBaseUnit() = Meter(value * Consts.ZETTA)

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond  = (this as UnitDistance<*>).divUnit(other)
}
