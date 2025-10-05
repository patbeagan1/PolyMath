package com.measures.pressure.non_si

import com.measures.pressure.Pascal
import com.measures.pressure.UnitPressure
import com.measures.area.UnitArea
import com.measures.force.UnitForce
import kotlin.jvm.JvmInline

@JvmInline
value class Atmosphere(override val value: Double) : UnitPressure<Atmosphere> {
    override fun asType(d: Double) = Atmosphere(d)
    override fun asBaseUnit() = Pascal(value * 101325.0)

    override operator fun plus(other: UnitPressure<*>) = UnitPressure.plusUnit(this, other)
    override operator fun minus(other: UnitPressure<*>) = UnitPressure.minusUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitPressure.timesUnit(this, other)
}

fun UnitPressure<*>.toAtmosphere() = toUnit(Atmosphere(1.0))
