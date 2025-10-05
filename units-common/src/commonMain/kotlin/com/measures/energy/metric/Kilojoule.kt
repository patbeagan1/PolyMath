package com.measures.energy.metric

import com.measures.Consts
import com.measures.energy.Joule
import com.measures.energy.UnitEnergy
import com.measures.charge.UnitCharge
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Kilojoule(override val value: Double) : UnitEnergy<Kilojoule> {
    override fun asType(d: Double) = Kilojoule(d)
    override fun asBaseUnit() = Joule(value * Consts.KILO)

    override operator fun plus(other: UnitEnergy<*>) = UnitEnergy.plusUnit(this, other)
    override operator fun minus(other: UnitEnergy<*>) = UnitEnergy.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitEnergy.divUnit(this, other)
    override operator fun div(other: UnitCharge<*>) = UnitEnergy.divUnit(this, other)
}

fun UnitEnergy<*>.toKilojoule() = toUnit(Kilojoule(1.0))
