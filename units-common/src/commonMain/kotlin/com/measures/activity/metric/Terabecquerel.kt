package com.measures.activity.metric

import com.measures.Consts
import com.measures.activity.Becquerel
import com.measures.activity.UnitActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Terabecquerel(override val value: Double) : UnitActivity<Terabecquerel> {
    override fun asType(d: Double) = Terabecquerel(d)
    override fun asBaseUnit() = Becquerel(value * Consts.TERA)

    override operator fun plus(other: UnitActivity<*>) = UnitActivity.plusUnit(this, other)
    override operator fun minus(other: UnitActivity<*>) = UnitActivity.minusUnit(this, other)
}

fun UnitActivity<*>.toTerabecquerel() = toUnit(Terabecquerel(1.0))
