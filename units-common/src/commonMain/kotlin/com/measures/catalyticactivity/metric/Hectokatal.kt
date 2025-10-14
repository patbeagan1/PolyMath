package com.measures.catalyticactivity.metric

import com.measures.Consts
import com.measures.catalyticactivity.Katal
import com.measures.catalyticactivity.UnitCatalyticActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Hectokatal(override val value: Double) : UnitCatalyticActivity<Hectokatal> {
    override fun asType(d: Double) = Hectokatal(d)
    override fun asBaseUnit() = Katal(value * Consts.HECTO)

    override operator fun plus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.plusUnit(this, other)
    override operator fun minus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.minusUnit(this, other)
}

fun UnitCatalyticActivity<*>.toHectokatal() = toUnit(Hectokatal(1.0))
