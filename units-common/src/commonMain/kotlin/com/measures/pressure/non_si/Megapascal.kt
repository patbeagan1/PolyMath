package com.measures.pressure.non_si

import com.measures.area.UnitArea
import com.measures.pressure.Pascal
import com.measures.pressure.UnitPressure
import kotlin.jvm.JvmInline

@JvmInline
value class Megapascal(override val value: Double) : UnitPressure<Megapascal> {
    override fun asType(d: Double) = Megapascal(d)
    override fun asBaseUnit() = Pascal(this.value * 1E6)

    override operator fun plus(other: UnitPressure<*>) = UnitPressure.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitPressure<*>) = UnitPressure.Companion.minusUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitPressure.Companion.timesUnit(this, other)
}

fun UnitPressure<*>.toMegapascal() = toUnit(Megapascal(1.0))
