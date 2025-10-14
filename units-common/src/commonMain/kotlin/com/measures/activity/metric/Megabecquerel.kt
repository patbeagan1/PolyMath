package com.measures.activity.metric

import com.measures.Consts
import com.measures.activity.Becquerel
import com.measures.activity.UnitActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Megabecquerel(override val value: Double) : UnitActivity<Megabecquerel> {
    override fun asType(d: Double) = Megabecquerel(d)
    override fun asBaseUnit() = Becquerel(value * Consts.MEGA)

    override operator fun plus(other: UnitActivity<*>) = UnitActivity.plusUnit(this, other)
    override operator fun minus(other: UnitActivity<*>) = UnitActivity.minusUnit(this, other)
}

fun UnitActivity<*>.toMegabecquerel() = toUnit(Megabecquerel(1.0))
