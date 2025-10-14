package com.measures.catalyticactivity.metric

import com.measures.Consts
import com.measures.catalyticactivity.Katal
import com.measures.catalyticactivity.UnitCatalyticActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Dekakatal(override val value: Double) : UnitCatalyticActivity<Dekakatal> {
    override fun asType(d: Double) = Dekakatal(d)
    override fun asBaseUnit() = Katal(value * Consts.DEKA)

    override operator fun plus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.plusUnit(this, other)
    override operator fun minus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.minusUnit(this, other)
}

fun UnitCatalyticActivity<*>.toDekakatal() = toUnit(Dekakatal(1.0))
