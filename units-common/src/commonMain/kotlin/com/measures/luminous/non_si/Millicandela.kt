package com.measures.luminous.non_si

import com.measures.luminous.Candela
import com.measures.luminous.UnitLuminous
import kotlin.jvm.JvmInline

@JvmInline
value class Millicandela(override val value: Double) : UnitLuminous<Millicandela> {
    override fun asType(d: Double) = Millicandela(d)
    override fun asBaseUnit() = Candela(this.value * 0.001)

    override operator fun plus(other: UnitLuminous<*>) = UnitLuminous.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitLuminous<*>) = UnitLuminous.Companion.minusUnit(this, other)
}

fun UnitLuminous<*>.toMillicandela() = toUnit(Millicandela(1.0))
