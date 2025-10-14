package com.measures.catalyticactivity.metric

import com.measures.Consts
import com.measures.catalyticactivity.Katal
import com.measures.catalyticactivity.UnitCatalyticActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Zeptokatal(override val value: Double) : UnitCatalyticActivity<Zeptokatal> {
    override fun asType(d: Double) = Zeptokatal(d)
    override fun asBaseUnit() = Katal(value * Consts.ZEPTO)

    override operator fun plus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.plusUnit(this, other)
    override operator fun minus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.minusUnit(this, other)
}

fun UnitCatalyticActivity<*>.toZeptokatal() = toUnit(Zeptokatal(1.0))
