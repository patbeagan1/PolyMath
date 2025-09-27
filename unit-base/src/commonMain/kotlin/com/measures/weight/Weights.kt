package com.measures.weight

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitTypedFull
import kotlin.jvm.JvmInline

typealias UnitWeight<T> = UnitWeightTypedFull<T>

interface UnitWeightTypedFull<T : DoubleBase> : UnitTypedFull<T, Gram> {
    operator fun plus(other: UnitWeightTypedFull<*>) =
        Gram(this.asBaseUnit().value + other.asBaseUnit().value)

    operator fun minus(other: UnitWeightTypedFull<*>) =
        Gram(this.asBaseUnit().value - other.asBaseUnit().value)
}

@JvmInline
value class Gram(override val value: Double) : UnitWeight<Gram>, BaseUnit {
    override fun asType(d: Double) = Gram(d)
    override fun asBaseUnit() = this
}

fun UnitWeight<*>.toGram() = this.asBaseUnit()