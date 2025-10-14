package com.measures.activity.metric

import com.measures.Consts
import com.measures.activity.Becquerel
import com.measures.activity.UnitActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Gigabecquerel(override val value: Double) : UnitActivity<Gigabecquerel> {
    override fun asType(d: Double) = Gigabecquerel(d)
    override fun asBaseUnit() = Becquerel(value * Consts.GIGA)

    override operator fun plus(other: UnitActivity<*>) = UnitActivity.plusUnit(this, other)
    override operator fun minus(other: UnitActivity<*>) = UnitActivity.minusUnit(this, other)
}

fun UnitActivity<*>.toGigabecquerel() = toUnit(Gigabecquerel(1.0))
