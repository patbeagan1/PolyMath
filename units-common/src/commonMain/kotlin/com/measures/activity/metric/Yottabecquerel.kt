package com.measures.activity.metric

import com.measures.Consts
import com.measures.activity.Becquerel
import com.measures.activity.UnitActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Yottabecquerel(override val value: Double) : UnitActivity<Yottabecquerel> {
    override fun asType(d: Double) = Yottabecquerel(d)
    override fun asBaseUnit() = Becquerel(value * Consts.YOTTA)

    override operator fun plus(other: UnitActivity<*>) = UnitActivity.plusUnit(this, other)
    override operator fun minus(other: UnitActivity<*>) = UnitActivity.minusUnit(this, other)
}

fun UnitActivity<*>.toYottabecquerel() = toUnit(Yottabecquerel(1.0))
