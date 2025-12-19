package com.measures.catalyticactivity.metric

import com.measures.Consts
import com.measures.catalyticactivity.Katal
import com.measures.catalyticactivity.UnitCatalyticActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Picokatal(override val value: Double) : UnitCatalyticActivity<Picokatal> {
    override fun asType(d: Double) = Picokatal(d)
    override fun asBaseUnit() = Katal(value * Consts.PICO)

    override operator fun plus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.plusUnit(this, other)
    override operator fun minus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.minusUnit(this, other)
}

fun UnitCatalyticActivity<*>.toPicokatal() = toUnit(Picokatal(1.0))
