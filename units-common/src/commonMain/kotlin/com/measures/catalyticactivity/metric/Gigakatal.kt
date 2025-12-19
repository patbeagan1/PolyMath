package com.measures.catalyticactivity.metric

import com.measures.Consts
import com.measures.catalyticactivity.Katal
import com.measures.catalyticactivity.UnitCatalyticActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Gigakatal(override val value: Double) : UnitCatalyticActivity<Gigakatal> {
    override fun asType(d: Double) = Gigakatal(d)
    override fun asBaseUnit() = Katal(value * Consts.GIGA)

    override operator fun plus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.plusUnit(this, other)
    override operator fun minus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.minusUnit(this, other)
}

fun UnitCatalyticActivity<*>.toGigakatal() = toUnit(Gigakatal(1.0))
