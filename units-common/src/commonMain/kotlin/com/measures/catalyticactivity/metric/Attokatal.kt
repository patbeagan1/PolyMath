package com.measures.catalyticactivity.metric

import com.measures.Consts
import com.measures.catalyticactivity.Katal
import com.measures.catalyticactivity.UnitCatalyticActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Attokatal(override val value: Double) : UnitCatalyticActivity<Attokatal> {
    override fun asType(d: Double) = Attokatal(d)
    override fun asBaseUnit() = Katal(value * Consts.ATTO)

    override operator fun plus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.plusUnit(this, other)
    override operator fun minus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.minusUnit(this, other)
}

fun UnitCatalyticActivity<*>.toAttokatal() = toUnit(Attokatal(1.0))
