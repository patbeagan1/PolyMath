package com.measures.catalyticactivity.metric

import com.measures.Consts
import com.measures.catalyticactivity.Katal
import com.measures.catalyticactivity.UnitCatalyticActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Yottakatal(override val value: Double) : UnitCatalyticActivity<Yottakatal> {
    override fun asType(d: Double) = Yottakatal(d)
    override fun asBaseUnit() = Katal(value * Consts.YOTTA)

    override operator fun plus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.plusUnit(this, other)
    override operator fun minus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.minusUnit(this, other)
}

fun UnitCatalyticActivity<*>.toYottakatal() = toUnit(Yottakatal(1.0))
