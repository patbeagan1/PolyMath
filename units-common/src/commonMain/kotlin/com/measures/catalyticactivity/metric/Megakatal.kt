package com.measures.catalyticactivity.metric

import com.measures.Consts
import com.measures.catalyticactivity.Katal
import com.measures.catalyticactivity.UnitCatalyticActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Megakatal(override val value: Double) : UnitCatalyticActivity<Megakatal> {
    override fun asType(d: Double) = Megakatal(d)
    override fun asBaseUnit() = Katal(value * Consts.MEGA)

    override operator fun plus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.plusUnit(this, other)
    override operator fun minus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.minusUnit(this, other)
}

fun UnitCatalyticActivity<*>.toMegakatal() = toUnit(Megakatal(1.0))
