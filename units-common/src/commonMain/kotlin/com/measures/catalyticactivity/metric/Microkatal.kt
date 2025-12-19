package com.measures.catalyticactivity.metric

import com.measures.Consts
import com.measures.catalyticactivity.Katal
import com.measures.catalyticactivity.UnitCatalyticActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Microkatal(override val value: Double) : UnitCatalyticActivity<Microkatal> {
    override fun asType(d: Double) = Microkatal(d)
    override fun asBaseUnit() = Katal(value * Consts.MICRO)

    override operator fun plus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.plusUnit(this, other)
    override operator fun minus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.minusUnit(this, other)
}

fun UnitCatalyticActivity<*>.toMicrokatal() = toUnit(Microkatal(1.0))
