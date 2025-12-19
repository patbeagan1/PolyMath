package com.measures.catalyticactivity.metric

import com.measures.Consts
import com.measures.catalyticactivity.Katal
import com.measures.catalyticactivity.UnitCatalyticActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Yoctokatal(override val value: Double) : UnitCatalyticActivity<Yoctokatal> {
    override fun asType(d: Double) = Yoctokatal(d)
    override fun asBaseUnit() = Katal(value * Consts.YOCTO)

    override operator fun plus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.plusUnit(this, other)
    override operator fun minus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.minusUnit(this, other)
}

fun UnitCatalyticActivity<*>.toYoctokatal() = toUnit(Yoctokatal(1.0))
