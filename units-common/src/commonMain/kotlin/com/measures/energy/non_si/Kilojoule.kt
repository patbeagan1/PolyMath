package com.measures.energy.non_si

import com.measures.charge.UnitCharge
import com.measures.energy.Joule
import com.measures.energy.UnitEnergy
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Kilojoule(override val value: Double) : UnitEnergy<Kilojoule> {
    override fun asType(d: Double) = Kilojoule(d)
    override fun asBaseUnit() = Joule(this.value * 1000.0)

    override operator fun plus(other: UnitEnergy<*>) = UnitEnergy.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitEnergy<*>) = UnitEnergy.Companion.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitEnergy.Companion.divUnit(this, other)
    override operator fun div(other: UnitCharge<*>) = UnitEnergy.Companion.divUnit(this, other)
}

fun UnitEnergy<*>.toKilojoule() = toUnit(Kilojoule(1.0))
