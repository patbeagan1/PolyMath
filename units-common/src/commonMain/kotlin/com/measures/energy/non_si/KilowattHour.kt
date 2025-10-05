package com.measures.energy.non_si

import com.measures.energy.Joule
import com.measures.energy.UnitEnergy
import com.measures.charge.UnitCharge
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class KilowattHour(override val value: Double) : UnitEnergy<KilowattHour> {
    override fun asType(d: Double) = KilowattHour(d)
    override fun asBaseUnit() = Joule(value * 3600000.0)

    override operator fun plus(other: UnitEnergy<*>) = UnitEnergy.plusUnit(this, other)
    override operator fun minus(other: UnitEnergy<*>) = UnitEnergy.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitEnergy.divUnit(this, other)
    override operator fun div(other: UnitCharge<*>) = UnitEnergy.divUnit(this, other)
}

fun UnitEnergy<*>.toKilowattHour() = toUnit(KilowattHour(1.0))
