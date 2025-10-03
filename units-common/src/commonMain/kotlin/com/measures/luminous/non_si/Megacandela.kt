package com.measures.luminous.non_si

import com.measures.luminous.Candela
import com.measures.luminous.UnitLuminous
import kotlin.jvm.JvmInline

@JvmInline
value class Megacandela(override val value: Double) : UnitLuminous<Megacandela> {
override fun asType(d: Double) = Megacandela(d)
    override fun asBaseUnit() = Candela(this.value * 1E6)

    override operator fun plus(other: UnitLuminous<*>) = UnitLuminous.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitLuminous<*>) = UnitLuminous.Companion.minusUnit(this, other)
}

fun UnitLuminous<*>.toMegacandela() = toUnit(Megacandela(1.0))
