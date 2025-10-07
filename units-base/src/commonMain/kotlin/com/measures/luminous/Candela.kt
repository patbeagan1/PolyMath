package com.measures.luminous

import com.measures.BaseUnit
import kotlin.jvm.JvmInline

@JvmInline
value class Candela(override val value: Double) : UnitLuminous<Candela>, BaseUnit {
    override fun asType(d: Double) = Candela(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitLuminous<*>) = UnitLuminous.plusUnit(this, other)
    override operator fun minus(other: UnitLuminous<*>) = UnitLuminous.minusUnit(this, other)
}

fun UnitLuminous<*>.toCandela() = this.asBaseUnit()
