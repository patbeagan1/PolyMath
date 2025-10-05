package com.measures.pressure.non_si

import com.measures.pressure.Pascal
import com.measures.pressure.UnitPressure
import com.measures.area.UnitArea
import com.measures.force.UnitForce
import kotlin.jvm.JvmInline

@JvmInline
value class PoundsPerSquareInch(override val value: Double) : UnitPressure<PoundsPerSquareInch> {
    override fun asType(d: Double) = PoundsPerSquareInch(d)
    override fun asBaseUnit() = Pascal(value * 6894.757293168361)

    override operator fun plus(other: UnitPressure<*>) = UnitPressure.plusUnit(this, other)
    override operator fun minus(other: UnitPressure<*>) = UnitPressure.minusUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitPressure.timesUnit(this, other)
}

fun UnitPressure<*>.toPoundsPerSquareInch() = toUnit(PoundsPerSquareInch(1.0))
