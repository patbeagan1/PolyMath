package com.measures.charge

import com.measures.BaseUnit
import com.measures.current.UnitCurrent
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

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