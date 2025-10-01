package com.measures.current

import com.measures.BaseUnit
import com.measures.Consts
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.charge.Coulomb
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

typealias UnitCurrent<T> = UnitCurrentType<T>

interface UnitCurrentType<T : DoubleBase> : UnitType<T, Ampere>

fun UnitCurrentType<*>.plusUnit(other: UnitCurrentType<*>): Ampere =
    Ampere(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitCurrentType<*>.minusUnit(other: UnitCurrentType<*>): Ampere =
    Ampere(this.asBaseUnit().value - other.asBaseUnit().value)

// Current × Time = Charge
fun UnitCurrentType<*>.timesUnit(other: UnitTime<*>): Coulomb =
    Coulomb(this.asBaseUnit().value * other.asBaseUnit().value)

@JvmInline
value class Ampere(override val value: Double) : UnitCurrent<Ampere>, BaseUnit {
    override fun asType(d: Double) = Ampere(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitCurrent<*>) = (this as UnitCurrent<*>).plusUnit(other)
    operator fun minus(other: UnitCurrent<*>) = (this as UnitCurrent<*>).minusUnit(other)
    operator fun times(other: UnitTime<*>) = (this as UnitCurrent<*>).timesUnit(other)
}

// Non-SI current units have been moved to unit-common module

// Conversion functions using toUnit
fun UnitCurrent<*>.toAmpere() = this.asBaseUnit()
