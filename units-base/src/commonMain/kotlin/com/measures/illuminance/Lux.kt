package com.measures.illuminance

import com.measures.BaseUnit
import kotlin.jvm.JvmInline

@JvmInline
value class Lux(override val value: Double) : UnitIlluminance<Lux>, BaseUnit {
    override fun asType(d: Double) = Lux(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitIlluminance<*>) = UnitIlluminance.plusUnit(this, other)
    override operator fun minus(other: UnitIlluminance<*>) = UnitIlluminance.minusUnit(this, other)
}

fun UnitIlluminance<*>.toLux() = this.asBaseUnit()

