package com.measures.catalyticactivity.metric

import com.measures.Consts
import com.measures.catalyticactivity.Katal
import com.measures.catalyticactivity.UnitCatalyticActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Terakatal(override val value: Double) : UnitCatalyticActivity<Terakatal> {
    override fun asType(d: Double) = Terakatal(d)
    override fun asBaseUnit() = Katal(value * Consts.TERA)

    override operator fun plus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.plusUnit(this, other)
    override operator fun minus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.minusUnit(this, other)
}

fun UnitCatalyticActivity<*>.toTerakatal() = toUnit(Terakatal(1.0))
