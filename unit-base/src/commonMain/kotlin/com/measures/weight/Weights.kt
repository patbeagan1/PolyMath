package com.measures.weight

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import kotlin.jvm.JvmInline

interface UnitMass<T : DoubleBase> : UnitType<T, KiloGram> {
    operator fun plus(other: UnitMass<*>): KiloGram
    operator fun minus(other: UnitMass<*>): KiloGram
    operator fun times(other: UnitAcceleration<*>): Newton
}

@JvmInline
value class KiloGram(override val value: Double) : UnitMass<KiloGram>, BaseUnit {
    override fun asType(d: Double) = KiloGram(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitMass<*>) = (this as UnitMass<*>).plusUnit(other)
    override operator fun minus(other: UnitMass<*>) = (this as UnitMass<*>).minusUnit(other)
    override operator fun times(other: UnitAcceleration<*>) = (this as UnitMass<*>).timesUnit(other)
}

fun UnitMass<*>.plusUnit(other: UnitMass<*>): KiloGram =
    KiloGram(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitMass<*>.minusUnit(other: UnitMass<*>): KiloGram =
    KiloGram(this.asBaseUnit().value - other.asBaseUnit().value)

// Weight × Acceleration = Force (mass × acceleration = force)
fun UnitMass<*>.timesUnit(other: UnitAcceleration<*>): Newton =
    Newton(this.asBaseUnit().value * other.asBaseUnit().value)

// Non-SI weight units have been moved to unit-common module