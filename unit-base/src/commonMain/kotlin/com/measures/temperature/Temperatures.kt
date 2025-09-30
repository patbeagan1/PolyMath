package com.measures.temperature

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import kotlin.jvm.JvmInline

typealias UnitTemperature<T> = UnitTemperatureType<T>

interface UnitTemperatureType<T : DoubleBase> : UnitType<T, Kelvin>

fun UnitTemperatureType<*>.plusUnit(other: UnitTemperatureType<*>): Kelvin =
    Kelvin(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitTemperatureType<*>.minusUnit(other: UnitTemperatureType<*>): Kelvin =
    Kelvin(this.asBaseUnit().value - other.asBaseUnit().value)

/**
 * The Kelvin scale, proposed by William Thomson (Lord Kelvin) in 1848,
 * is the SI base unit for thermodynamic temperature and starts at absolute zero,
 * the theoretical point where all molecular motions cease.
 */
@JvmInline
value class Kelvin(override val value: Double) : UnitTemperature<Kelvin>, BaseUnit {
    override fun asType(d: Double) = Kelvin(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitTemperature<*>) = (this as UnitTemperature<*>).plusUnit(other)
    operator fun minus(other: UnitTemperature<*>) = (this as UnitTemperature<*>).minusUnit(other)
}

/**
 * The Celsius scale (originally called centigrade) was introduced by Anders Celsius in 1742,
 * defining 0°C as the freezing point and 100°C as the boiling point of water.
 */
@JvmInline
value class Celsius(override val value: Double) : UnitTemperature<Celsius> {
    override fun asType(d: Double) = Celsius(d)
    override fun asBaseUnit() = Kelvin(this.value + 273.15)

    operator fun plus(other: UnitTemperature<*>) = (this as UnitTemperature<*>).plusUnit(other)
    operator fun minus(other: UnitTemperature<*>) = (this as UnitTemperature<*>).minusUnit(other)
}

/**
 * The Fahrenheit scale was created in 1724, by Daniel Gabriel Fahrenheit, and is still used in some countries today.
 */
@JvmInline
value class Fahrenheit(override val value: Double) : UnitTemperature<Fahrenheit> {
    override fun asType(d: Double) = Fahrenheit(d)
    override fun asBaseUnit() = Kelvin((this.value - 32.0) * 5.0 / 9.0 + 273.15)

    operator fun plus(other: UnitTemperature<*>) = (this as UnitTemperature<*>).plusUnit(other)
    operator fun minus(other: UnitTemperature<*>) = (this as UnitTemperature<*>).minusUnit(other)
}

/**
 * The Rankine scale, introduced by William John Macquorn Rankine in 1859,
 * is an absolute temperature scale used primarily in engineering fields in the United States.
 */
@JvmInline
value class Rankine(override val value: Double) : UnitTemperature<Rankine> {
    override fun asType(d: Double) = Rankine(d)
    override fun asBaseUnit() = Kelvin(this.value * 5.0 / 9.0)

    operator fun plus(other: UnitTemperature<*>) = (this as UnitTemperature<*>).plusUnit(other)
    operator fun minus(other: UnitTemperature<*>) = (this as UnitTemperature<*>).minusUnit(other)
}

// Conversion functions using toUnit
fun UnitTemperature<*>.toKelvin() = this.asBaseUnit()
fun UnitTemperature<*>.toCelsius() = toUnit(Celsius(1.0))
fun UnitTemperature<*>.toFahrenheit() = toUnit(Fahrenheit(1.0))
fun UnitTemperature<*>.toRankine() = toUnit(Rankine(1.0))
