package com.measures.catalyticactivity.metric

import com.measures.Consts
import com.measures.catalyticactivity.Katal
import com.measures.catalyticactivity.UnitCatalyticActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Exakatal(override val value: Double) : UnitCatalyticActivity<Exakatal> {
    override fun asType(d: Double) = Exakatal(d)
    override fun asBaseUnit() = Katal(value * Consts.EXA)

    override operator fun plus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.plusUnit(this, other)
    override operator fun minus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.minusUnit(this, other)
}

fun UnitCatalyticActivity<*>.toExakatal() = toUnit(Exakatal(1.0))
