package com.measures.activity.metric

import com.measures.Consts
import com.measures.activity.Becquerel
import com.measures.activity.UnitActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Femtobecquerel(override val value: Double) : UnitActivity<Femtobecquerel> {
    override fun asType(d: Double) = Femtobecquerel(d)
    override fun asBaseUnit() = Becquerel(value * Consts.FEMTO)

    override operator fun plus(other: UnitActivity<*>) = UnitActivity.plusUnit(this, other)
    override operator fun minus(other: UnitActivity<*>) = UnitActivity.minusUnit(this, other)
}

fun UnitActivity<*>.toFemtobecquerel() = toUnit(Femtobecquerel(1.0))
