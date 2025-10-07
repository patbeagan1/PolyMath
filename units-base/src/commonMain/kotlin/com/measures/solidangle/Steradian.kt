package com.measures.solidangle

import com.measures.BaseUnit
import kotlin.jvm.JvmInline

@JvmInline
value class Steradian(override val value: Double) : UnitSolidAngle<Steradian>, BaseUnit {
    override fun asType(d: Double) = Steradian(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitSolidAngle<*>) = UnitSolidAngle.plusUnit(this, other)
    override operator fun minus(other: UnitSolidAngle<*>) = UnitSolidAngle.minusUnit(this, other)
}

fun UnitSolidAngle<*>.toSteradian() = this.asBaseUnit()