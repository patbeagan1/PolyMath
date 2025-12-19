package com.measures.catalyticactivity.metric

import com.measures.Consts
import com.measures.catalyticactivity.Katal
import com.measures.catalyticactivity.UnitCatalyticActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Kilokatal(override val value: Double) : UnitCatalyticActivity<Kilokatal> {
    override fun asType(d: Double) = Kilokatal(d)
    override fun asBaseUnit() = Katal(value * Consts.KILO)

    override operator fun plus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.plusUnit(this, other)
    override operator fun minus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.minusUnit(this, other)
}

fun UnitCatalyticActivity<*>.toKilokatal() = toUnit(Kilokatal(1.0))
