package com.measures.activity.metric

import com.measures.Consts
import com.measures.activity.Becquerel
import com.measures.activity.UnitActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Decibecquerel(override val value: Double) : UnitActivity<Decibecquerel> {
    override fun asType(d: Double) = Decibecquerel(d)
    override fun asBaseUnit() = Becquerel(value * Consts.DECI)

    override operator fun plus(other: UnitActivity<*>) = UnitActivity.plusUnit(this, other)
    override operator fun minus(other: UnitActivity<*>) = UnitActivity.minusUnit(this, other)
}

fun UnitActivity<*>.toDecibecquerel() = toUnit(Decibecquerel(1.0))
