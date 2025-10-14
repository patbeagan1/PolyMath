package com.measures.catalyticactivity.metric

import com.measures.Consts
import com.measures.catalyticactivity.Katal
import com.measures.catalyticactivity.UnitCatalyticActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Centikatal(override val value: Double) : UnitCatalyticActivity<Centikatal> {
    override fun asType(d: Double) = Centikatal(d)
    override fun asBaseUnit() = Katal(value * Consts.CENTI)

    override operator fun plus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.plusUnit(this, other)
    override operator fun minus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.minusUnit(this, other)
}

fun UnitCatalyticActivity<*>.toCentikatal() = toUnit(Centikatal(1.0))
