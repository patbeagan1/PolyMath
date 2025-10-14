package com.measures.catalyticactivity.metric

import com.measures.Consts
import com.measures.catalyticactivity.Katal
import com.measures.catalyticactivity.UnitCatalyticActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Decikatal(override val value: Double) : UnitCatalyticActivity<Decikatal> {
    override fun asType(d: Double) = Decikatal(d)
    override fun asBaseUnit() = Katal(value * Consts.DECI)

    override operator fun plus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.plusUnit(this, other)
    override operator fun minus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.minusUnit(this, other)
}

fun UnitCatalyticActivity<*>.toDecikatal() = toUnit(Decikatal(1.0))
