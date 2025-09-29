package com.measures.weight

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import kotlin.jvm.JvmInline

typealias UnitWeight<T> = UnitWeightType<T>

interface UnitWeightType<T : DoubleBase> : UnitType<T, Gram>

@JvmInline
value class Gram(override val value: Double) : UnitWeight<Gram>, BaseUnit {
    override fun asType(d: Double) = Gram(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitWeight<*>) = (this as UnitWeight<*>).plus(other)
    operator fun minus(other: UnitWeight<*>) = (this as UnitWeight<*>).minus(other)
}

operator fun UnitWeightType<*>.plus(other: UnitWeightType<*>): Gram =
    Gram(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitWeightType<*>.minus(other: UnitWeightType<*>): Gram =
    Gram(this.asBaseUnit().value - other.asBaseUnit().value)

fun UnitWeight<*>.toGram() = this.asBaseUnit()