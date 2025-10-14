package com.measures.activity.metric

import com.measures.Consts
import com.measures.activity.Becquerel
import com.measures.activity.UnitActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Attobecquerel(override val value: Double) : UnitActivity<Attobecquerel> {
    override fun asType(d: Double) = Attobecquerel(d)
    override fun asBaseUnit() = Becquerel(value * Consts.ATTO)

    override operator fun plus(other: UnitActivity<*>) = UnitActivity.plusUnit(this, other)
    override operator fun minus(other: UnitActivity<*>) = UnitActivity.minusUnit(this, other)
}

fun UnitActivity<*>.toAttobecquerel() = toUnit(Attobecquerel(1.0))
