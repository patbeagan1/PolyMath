package com.measures.weight

import com.measures.BaseUnit
import com.measures.acceleration.UnitAcceleration
import kotlin.jvm.JvmInline

@JvmInline
value class KiloGram(override val value: Double) : UnitMass<KiloGram>, BaseUnit {
    override fun asType(d: Double) = KiloGram(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitMass<*>) = UnitMass.plusUnit(this, other)
    override operator fun minus(other: UnitMass<*>) = UnitMass.minusUnit(this, other)
    override operator fun times(other: UnitAcceleration<*>) = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toKiloGram() = this.asBaseUnit()