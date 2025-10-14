package com.measures.catalyticactivity.metric

import com.measures.Consts
import com.measures.catalyticactivity.Katal
import com.measures.catalyticactivity.UnitCatalyticActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Nanokatal(override val value: Double) : UnitCatalyticActivity<Nanokatal> {
    override fun asType(d: Double) = Nanokatal(d)
    override fun asBaseUnit() = Katal(value * Consts.NANO)

    override operator fun plus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.plusUnit(this, other)
    override operator fun minus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.minusUnit(this, other)
}

fun UnitCatalyticActivity<*>.toNanokatal() = toUnit(Nanokatal(1.0))
