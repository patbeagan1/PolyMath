package com.measures.charge

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.current.Ampere
import com.measures.current.UnitCurrent
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

interface UnitCharge<T : DoubleBase> : UnitType<T, Coulomb> {
    fun asUnitCharge(): UnitCharge<T> = this
}

@JvmInline
value class Coulomb(override val value: Double) : UnitCharge<Coulomb>, BaseUnit {
    override fun asType(d: Double) = Coulomb(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitCharge<*>) = (this as UnitCharge<*>).plusUnit(other)
    operator fun minus(other: UnitCharge<*>) = (this as UnitCharge<*>).minusUnit(other)

    companion object {
        fun from(current: UnitCurrent<*>, time: UnitTime<*>): Coulomb {
            val currentBase = current.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Coulomb(currentBase.value * timeBase.value)
        }
    }
}

fun UnitCharge<*>.plusUnit(other: UnitCharge<*>): Coulomb =
    Coulomb(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitCharge<*>.minusUnit(other: UnitCharge<*>): Coulomb =
    Coulomb(this.asBaseUnit().value - other.asBaseUnit().value)

// Charge ÷ Time = Current
fun UnitCharge<*>.divUnit(other: UnitTime<*>): Ampere =
    Ampere(this.asBaseUnit().value / other.asBaseUnit().value)

// Non-SI charge units have been moved to unit-common module

// Conversion functions using toUnit
fun UnitCharge<*>.toCoulomb() = this.asBaseUnit()

