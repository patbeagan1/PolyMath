package com.measures.catalyticactivity.metric

import com.measures.Consts
import com.measures.catalyticactivity.Katal
import com.measures.catalyticactivity.UnitCatalyticActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Millikatal(override val value: Double) : UnitCatalyticActivity<Millikatal> {
    override fun asType(d: Double) = Millikatal(d)
    override fun asBaseUnit() = Katal(value * Consts.MILLI)

    override operator fun plus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.plusUnit(this, other)
    override operator fun minus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.minusUnit(this, other)
}

fun UnitCatalyticActivity<*>.toMillikatal() = toUnit(Millikatal(1.0))
