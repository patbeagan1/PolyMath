package com.measures.activity.metric

import com.measures.Consts
import com.measures.activity.Becquerel
import com.measures.activity.UnitActivity
import kotlin.jvm.JvmInline

@JvmInline
value class Kilobecquerel(override val value: Double) : UnitActivity<Kilobecquerel> {
    override fun asType(d: Double) = Kilobecquerel(d)
    override fun asBaseUnit() = Becquerel(value * Consts.KILO)

    override operator fun plus(other: UnitActivity<*>) = UnitActivity.plusUnit(this, other)
    override operator fun minus(other: UnitActivity<*>) = UnitActivity.minusUnit(this, other)
}

fun UnitActivity<*>.toKilobecquerel() = toUnit(Kilobecquerel(1.0))
