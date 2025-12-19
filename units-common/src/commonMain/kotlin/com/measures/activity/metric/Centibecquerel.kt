package com.measures.activity.metric

import com.measures.Consts
import com.measures.activity.Becquerel
import com.measures.activity.UnitActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Centibecquerel(override val value: Double) : UnitActivity<Centibecquerel> {
    override fun asType(d: Double) = Centibecquerel(d)
    override fun asBaseUnit() = Becquerel(value * Consts.CENTI)

    override operator fun plus(other: UnitActivity<*>) = UnitActivity.plusUnit(this, other)
    override operator fun minus(other: UnitActivity<*>) = UnitActivity.minusUnit(this, other)
}

fun UnitActivity<*>.toCentibecquerel() = toUnit(Centibecquerel(1.0))
