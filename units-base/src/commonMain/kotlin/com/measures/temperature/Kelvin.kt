package com.measures.temperature

import com.measures.BaseUnit
import kotlin.jvm.JvmInline

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

fun UnitTemperature<*>.toKelvin() = this.asBaseUnit()
