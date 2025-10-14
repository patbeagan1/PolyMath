package com.measures.activity.metric

import com.measures.Consts
import com.measures.activity.Becquerel
import com.measures.activity.UnitActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Exabecquerel(override val value: Double) : UnitActivity<Exabecquerel> {
    override fun asType(d: Double) = Exabecquerel(d)
    override fun asBaseUnit() = Becquerel(value * Consts.EXA)

    override operator fun plus(other: UnitActivity<*>) = UnitActivity.plusUnit(this, other)
    override operator fun minus(other: UnitActivity<*>) = UnitActivity.minusUnit(this, other)
}

fun UnitActivity<*>.toExabecquerel() = toUnit(Exabecquerel(1.0))
