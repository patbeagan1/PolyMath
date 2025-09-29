package com.measures.weight

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import kotlin.jvm.JvmInline

typealias UnitWeight<T> = UnitWeightType<T>

interface UnitWeightType<T : DoubleBase> : UnitType<T, KiloGram>

@JvmInline
value class KiloGram(override val value: Double) : UnitWeight<KiloGram>, BaseUnit {
    override fun asType(d: Double) = KiloGram(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitWeight<*>) = (this as UnitWeight<*>).plus(other)
    operator fun minus(other: UnitWeight<*>) = (this as UnitWeight<*>).minus(other)
}

@JvmInline
value class Gram(override val value: Double) : UnitWeight<KiloGram>, BaseUnit {
    override fun asType(d: Double) = KiloGram(d)
    override fun asBaseUnit() = KiloGram(this.value / 1000.0)

    operator fun plus(other: UnitWeight<*>) = (this as UnitWeight<*>).plus(other)
    operator fun minus(other: UnitWeight<*>) = (this as UnitWeight<*>).minus(other)
}

operator fun UnitWeightType<*>.plus(other: UnitWeightType<*>): KiloGram =
    KiloGram(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitWeightType<*>.minus(other: UnitWeightType<*>): KiloGram =
    KiloGram(this.asBaseUnit().value - other.asBaseUnit().value)

fun UnitWeight<*>.toGram() = this.asBaseUnit()