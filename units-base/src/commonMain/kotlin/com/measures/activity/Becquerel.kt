package com.measures.activity

import com.measures.BaseUnit
import kotlin.jvm.JvmInline

@JvmInline
value class Becquerel(override val value: Double) : UnitActivity<Becquerel>, BaseUnit {
    override fun asType(d: Double) = Becquerel(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitActivity<*>) = UnitActivity.plusUnit(this, other)
    override operator fun minus(other: UnitActivity<*>) = UnitActivity.minusUnit(this, other)
}

fun UnitActivity<*>.toBecquerel() = this.asBaseUnit()

