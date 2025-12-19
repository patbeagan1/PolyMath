package com.measures.activity.metric

import com.measures.Consts
import com.measures.activity.Becquerel
import com.measures.activity.UnitActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Millibecquerel(override val value: Double) : UnitActivity<Millibecquerel> {
    override fun asType(d: Double) = Millibecquerel(d)
    override fun asBaseUnit() = Becquerel(value * Consts.MILLI)

    override operator fun plus(other: UnitActivity<*>) = UnitActivity.plusUnit(this, other)
    override operator fun minus(other: UnitActivity<*>) = UnitActivity.minusUnit(this, other)
}

fun UnitActivity<*>.toMillibecquerel() = toUnit(Millibecquerel(1.0))
