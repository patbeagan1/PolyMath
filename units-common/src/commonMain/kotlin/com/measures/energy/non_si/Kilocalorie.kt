package com.measures.energy.non_si

import com.measures.charge.UnitCharge
import com.measures.energy.Joule
import com.measures.energy.UnitEnergy
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Kilocalorie(override val value: Double) : UnitEnergy<Kilocalorie> {
    override fun asType(d: Double) = Kilocalorie(d)
    override fun asBaseUnit() = Joule(this.value * 4184.0)

    override operator fun plus(other: UnitEnergy<*>) = UnitEnergy.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitEnergy<*>) = UnitEnergy.Companion.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitEnergy.Companion.divUnit(this, other)
    override operator fun div(other: UnitCharge<*>) = UnitEnergy.Companion.divUnit(this, other)
}

fun UnitEnergy<*>.toKilocalorie() = toUnit(Kilocalorie(1.0))
