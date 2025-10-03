package com.measures.weight

import com.measures.BaseUnit
import com.measures.acceleration.UnitAcceleration
import kotlin.jvm.JvmInline

@JvmInline
value class Gram(override val value: Double) : UnitMass<KiloGram>, BaseUnit {
    override fun asType(d: Double) = KiloGram(d)
    override fun asBaseUnit() = KiloGram(this.value / 1000.0)

    override operator fun plus(other: UnitMass<*>) = UnitMass.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitMass<*>) = UnitMass.Companion.minusUnit(this, other)
    override operator fun times(other: UnitAcceleration<*>) = UnitMass.Companion.timesUnit(this, other)
}

// Conversion functions using toUnit
fun UnitMass<*>.toGram() = toUnit(Gram(1.0))
