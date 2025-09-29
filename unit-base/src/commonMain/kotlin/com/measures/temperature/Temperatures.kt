package com.measures.temperature

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import kotlin.jvm.JvmInline

typealias UnitTemperature<T> = UnitTemperatureType<T>

interface UnitTemperatureType<T : DoubleBase> : UnitType<T, Kelvin>

@JvmInline
value class Kelvin(override val value: Double) : UnitTemperature<Kelvin>, BaseUnit {
    override fun asType(d: Double) = Kelvin(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitTemperature<*>) = (this as UnitTemperature<*>).plus(other)
    operator fun minus(other: UnitTemperature<*>) = (this as UnitTemperature<*>).minus(other)
}

@JvmInline
value class Celsius(override val value: Double) : UnitTemperature<Celsius> {
    override fun asType(d: Double) = Celsius(d)
    override fun asBaseUnit() = Kelvin(this.value + 273.15)

    operator fun plus(other: UnitTemperature<*>) = (this as UnitTemperature<*>).plus(other)
    operator fun minus(other: UnitTemperature<*>) = (this as UnitTemperature<*>).minus(other)
}

@JvmInline
value class Fahrenheit(override val value: Double) : UnitTemperature<Fahrenheit> {
    override fun asType(d: Double) = Fahrenheit(d)
    override fun asBaseUnit() = Kelvin((this.value - 32.0) * 5.0 / 9.0 + 273.15)

    operator fun plus(other: UnitTemperature<*>) = (this as UnitTemperature<*>).plus(other)
    operator fun minus(other: UnitTemperature<*>) = (this as UnitTemperature<*>).minus(other)
}

@JvmInline
value class Rankine(override val value: Double) : UnitTemperature<Rankine> {
    override fun asType(d: Double) = Rankine(d)
    override fun asBaseUnit() = Kelvin(this.value * 5.0 / 9.0)

    operator fun plus(other: UnitTemperature<*>) = (this as UnitTemperature<*>).plus(other)
    operator fun minus(other: UnitTemperature<*>) = (this as UnitTemperature<*>).minus(other)
}

operator fun UnitTemperatureType<*>.plus(other: UnitTemperatureType<*>): Kelvin =
    Kelvin(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitTemperatureType<*>.minus(other: UnitTemperatureType<*>): Kelvin =
    Kelvin(this.asBaseUnit().value - other.asBaseUnit().value)

// Conversion functions using toUnit
fun UnitTemperature<*>.toKelvin() = this.asBaseUnit()
fun UnitTemperature<*>.toCelsius() = toUnit(Celsius(1.0))
fun UnitTemperature<*>.toFahrenheit() = toUnit(Fahrenheit(1.0))
fun UnitTemperature<*>.toRankine() = toUnit(Rankine(1.0))
