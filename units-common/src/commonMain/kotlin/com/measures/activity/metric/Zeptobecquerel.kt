package com.measures.activity.metric

import com.measures.Consts
import com.measures.activity.Becquerel
import com.measures.activity.UnitActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Zeptobecquerel(override val value: Double) : UnitActivity<Zeptobecquerel> {
    override fun asType(d: Double) = Zeptobecquerel(d)
    override fun asBaseUnit() = Becquerel(value * Consts.ZEPTO)

    override operator fun plus(other: UnitActivity<*>) = UnitActivity.plusUnit(this, other)
    override operator fun minus(other: UnitActivity<*>) = UnitActivity.minusUnit(this, other)
}

fun UnitActivity<*>.toZeptobecquerel() = toUnit(Zeptobecquerel(1.0))
