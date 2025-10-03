package com.measures.energy.metric

import com.measures.Consts
import com.measures.energy.Joule
import com.measures.energy.UnitEnergy
import com.measures.charge.UnitCharge
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Femtojoule(override val value: Double) : UnitEnergy<Femtojoule> {
    override fun asType(d: Double) = Femtojoule(d)
    override fun asBaseUnit() = Joule(value * Consts.FEMTO)

    override operator fun plus(other: UnitEnergy<*>) = UnitEnergy.plusUnit(this, other)
    override operator fun minus(other: UnitEnergy<*>) = UnitEnergy.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitEnergy.divUnit(this, other)
    override operator fun div(other: UnitCharge<*>) = UnitEnergy.divUnit(this, other)
}

fun UnitEnergy<*>.toFemtojoule() = toUnit(Femtojoule(1.0))
