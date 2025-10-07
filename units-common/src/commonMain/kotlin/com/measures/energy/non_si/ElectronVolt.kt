package com.measures.energy.non_si

import com.measures.energy.Joule
import com.measures.energy.UnitEnergy
import com.measures.charge.UnitCharge
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class ElectronVolt(override val value: Double) : UnitEnergy<ElectronVolt> {
    override fun asType(d: Double) = ElectronVolt(d)
    override fun asBaseUnit() = Joule(value * 1.602176634e-19)

    override operator fun plus(other: UnitEnergy<*>) = UnitEnergy.plusUnit(this, other)
    override operator fun minus(other: UnitEnergy<*>) = UnitEnergy.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitEnergy.divUnit(this, other)
    override operator fun div(other: UnitCharge<*>) = UnitEnergy.divUnit(this, other)
}

fun UnitEnergy<*>.toElectronVolt() = toUnit(ElectronVolt(1.0))
