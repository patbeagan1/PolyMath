package com.measures.pressure.metric

import com.measures.Consts
import com.measures.pressure.Pascal
import com.measures.pressure.UnitPressure
import com.measures.area.UnitArea
import com.measures.force.UnitForce
import kotlin.jvm.JvmInline

@JvmInline
value class Terapascal(override val value: Double) : UnitPressure<Terapascal> {
    override fun asType(d: Double) = Terapascal(d)
    override fun asBaseUnit() = Pascal(value * Consts.TERA)

    override operator fun plus(other: UnitPressure<*>) = UnitPressure.plusUnit(this, other)
    override operator fun minus(other: UnitPressure<*>) = UnitPressure.minusUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitPressure.timesUnit(this, other)
}

fun UnitPressure<*>.toTerapascal() = toUnit(Terapascal(1.0))
