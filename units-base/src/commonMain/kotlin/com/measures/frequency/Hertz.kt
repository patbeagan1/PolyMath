package com.measures.frequency

import com.measures.BaseUnit
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Hertz(override val value: Double) : UnitFrequency<Hertz>, BaseUnit {
    override fun asType(d: Double) = Hertz(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitFrequency<*>) = UnitFrequency.plusUnit(this, other)
    override operator fun minus(other: UnitFrequency<*>) = UnitFrequency.minusUnit(this, other)
    override operator fun inv() = UnitFrequency.invUnit(this)

    companion object {
        fun from(time: UnitTime<*>): Hertz = Hertz(1.0 / time.asBaseUnit().value)
    }
}

fun UnitFrequency<*>.toHertz() = this.asBaseUnit()
