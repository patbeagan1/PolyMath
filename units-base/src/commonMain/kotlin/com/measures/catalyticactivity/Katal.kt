package com.measures.catalyticactivity

import com.measures.BaseUnit
import kotlin.jvm.JvmInline

@JvmInline
value class Katal(override val value: Double) : UnitCatalyticActivity<Katal>, BaseUnit {
    override fun asType(d: Double) = Katal(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.plusUnit(this, other)
    override operator fun minus(other: UnitCatalyticActivity<*>) = UnitCatalyticActivity.minusUnit(this, other)
}

fun UnitCatalyticActivity<*>.toKatal() = this.asBaseUnit()

