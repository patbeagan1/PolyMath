package com.measures.luminous

import com.measures.BaseUnit
import kotlin.jvm.JvmInline

@JvmInline
value class Candela(override val value: Double) : UnitLuminous<Candela>, BaseUnit {
    override fun asType(d: Double) = Candela(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitLuminous<*>) = (this as UnitLuminous<*>).plusUnit(other)
    operator fun minus(other: UnitLuminous<*>) = (this as UnitLuminous<*>).minusUnit(other)
}