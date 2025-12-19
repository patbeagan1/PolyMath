package com.measures.activity.metric

import com.measures.Consts
import com.measures.activity.Becquerel
import com.measures.activity.UnitActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Petabecquerel(override val value: Double) : UnitActivity<Petabecquerel> {
    override fun asType(d: Double) = Petabecquerel(d)
    override fun asBaseUnit() = Becquerel(value * Consts.PETA)

    override operator fun plus(other: UnitActivity<*>) = UnitActivity.plusUnit(this, other)
    override operator fun minus(other: UnitActivity<*>) = UnitActivity.minusUnit(this, other)
}

fun UnitActivity<*>.toPetabecquerel() = toUnit(Petabecquerel(1.0))
