package com.measures.charge

import com.measures.BaseUnit
import com.measures.current.Ampere
import com.measures.current.UnitCurrent
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Coulomb(override val value: Double) : UnitCharge<Coulomb>, BaseUnit {
    override fun asType(d: Double) = Coulomb(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitCharge<*>) = UnitCharge.plusUnit(this, other)
    override operator fun minus(other: UnitCharge<*>) = UnitCharge.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>): Ampere = Ampere(this.value / other.asBaseUnit().value)

    companion object {
        fun from(current: UnitCurrent<*>, time: UnitTime<*>): Coulomb {
            val currentBase = current.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Coulomb(currentBase.value * timeBase.value)
        }
    }
}

fun UnitCharge<*>.toCoulomb() = this.asBaseUnit()
