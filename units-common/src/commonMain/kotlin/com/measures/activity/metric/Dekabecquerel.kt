package com.measures.activity.metric

import com.measures.Consts
import com.measures.activity.Becquerel
import com.measures.activity.UnitActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Dekabecquerel(override val value: Double) : UnitActivity<Dekabecquerel> {
    override fun asType(d: Double) = Dekabecquerel(d)
    override fun asBaseUnit() = Becquerel(value * Consts.DEKA)

    override operator fun plus(other: UnitActivity<*>) = UnitActivity.plusUnit(this, other)
    override operator fun minus(other: UnitActivity<*>) = UnitActivity.minusUnit(this, other)
}

fun UnitActivity<*>.toDekabecquerel() = toUnit(Dekabecquerel(1.0))
