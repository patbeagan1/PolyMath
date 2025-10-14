package com.measures.activity.metric

import com.measures.Consts
import com.measures.activity.Becquerel
import com.measures.activity.UnitActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Microbecquerel(override val value: Double) : UnitActivity<Microbecquerel> {
    override fun asType(d: Double) = Microbecquerel(d)
    override fun asBaseUnit() = Becquerel(value * Consts.MICRO)

    override operator fun plus(other: UnitActivity<*>) = UnitActivity.plusUnit(this, other)
    override operator fun minus(other: UnitActivity<*>) = UnitActivity.minusUnit(this, other)
}

fun UnitActivity<*>.toMicrobecquerel() = toUnit(Microbecquerel(1.0))
