package com.measures.activity.metric

import com.measures.Consts
import com.measures.activity.Becquerel
import com.measures.activity.UnitActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Picobecquerel(override val value: Double) : UnitActivity<Picobecquerel> {
    override fun asType(d: Double) = Picobecquerel(d)
    override fun asBaseUnit() = Becquerel(value * Consts.PICO)

    override operator fun plus(other: UnitActivity<*>) = UnitActivity.plusUnit(this, other)
    override operator fun minus(other: UnitActivity<*>) = UnitActivity.minusUnit(this, other)
}

fun UnitActivity<*>.toPicobecquerel() = toUnit(Picobecquerel(1.0))
