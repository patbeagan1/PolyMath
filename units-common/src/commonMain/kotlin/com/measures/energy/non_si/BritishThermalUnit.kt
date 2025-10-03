package com.measures.energy.non_si

import com.measures.charge.UnitCharge
import com.measures.energy.Joule
import com.measures.energy.UnitEnergy
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class BritishThermalUnit(override val value: Double) : UnitEnergy<BritishThermalUnit> {
override fun asType(d: Double) = BritishThermalUnit(d)
    override fun asBaseUnit() = Joule(this.value * 1055.056)

    override operator fun plus(other: UnitEnergy<*>) = UnitEnergy.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitEnergy<*>) = UnitEnergy.Companion.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitEnergy.Companion.divUnit(this, other)
    override operator fun div(other: UnitCharge<*>) = UnitEnergy.Companion.divUnit(this, other)
}

fun UnitEnergy<*>.toBritishThermalUnit() = toUnit(BritishThermalUnit(1.0))
