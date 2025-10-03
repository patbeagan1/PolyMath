package com.measures.luminous.non_si

import com.measures.luminous.Candela
import com.measures.luminous.UnitLuminous
import kotlin.jvm.JvmInline

@JvmInline
value class Kilocandela(override val value: Double) : UnitLuminous<Kilocandela> {
override fun asType(d: Double) = Kilocandela(d)
    override fun asBaseUnit() = Candela(this.value * 1000.0)

    override operator fun plus(other: UnitLuminous<*>) = UnitLuminous.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitLuminous<*>) = UnitLuminous.Companion.minusUnit(this, other)
}

fun UnitLuminous<*>.toKilocandela() = toUnit(Kilocandela(1.0))
