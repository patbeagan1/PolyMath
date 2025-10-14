package com.measures.activity.metric

import com.measures.Consts
import com.measures.activity.Becquerel
import com.measures.activity.UnitActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Nanobecquerel(override val value: Double) : UnitActivity<Nanobecquerel> {
    override fun asType(d: Double) = Nanobecquerel(d)
    override fun asBaseUnit() = Becquerel(value * Consts.NANO)

    override operator fun plus(other: UnitActivity<*>) = UnitActivity.plusUnit(this, other)
    override operator fun minus(other: UnitActivity<*>) = UnitActivity.minusUnit(this, other)
}

fun UnitActivity<*>.toNanobecquerel() = toUnit(Nanobecquerel(1.0))
