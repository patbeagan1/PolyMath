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

// Non-SI temperature units have been moved to units-common module

// Conversion functions using toUnit
fun UnitTemperature<*>.toKelvin() = this.asBaseUnit()
