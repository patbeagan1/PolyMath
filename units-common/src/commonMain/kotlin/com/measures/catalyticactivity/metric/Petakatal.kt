package com.measures.catalyticactivity.metric

import com.measures.Consts
import com.measures.catalyticactivity.Katal
import com.measures.catalyticactivity.UnitCatalyticActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Petakatal(override val value: Double) : UnitCatalyticActivity<Petakatal> {
    override fun asType(d: Double) = Petakatal(d)
    override fun asBaseUnit() = Katal(value * Consts.PETA)

    override operator fun plus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.plusUnit(this, other)
    override operator fun minus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.minusUnit(this, other)
}

fun UnitCatalyticActivity<*>.toPetakatal() = toUnit(Petakatal(1.0))
