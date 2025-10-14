package com.measures.activity.metric

import com.measures.Consts
import com.measures.activity.Becquerel
import com.measures.activity.UnitActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Yoctobecquerel(override val value: Double) : UnitActivity<Yoctobecquerel> {
    override fun asType(d: Double) = Yoctobecquerel(d)
    override fun asBaseUnit() = Becquerel(value * Consts.YOCTO)

    override operator fun plus(other: UnitActivity<*>) = UnitActivity.plusUnit(this, other)
    override operator fun minus(other: UnitActivity<*>) = UnitActivity.minusUnit(this, other)
}

fun UnitActivity<*>.toYoctobecquerel() = toUnit(Yoctobecquerel(1.0))
