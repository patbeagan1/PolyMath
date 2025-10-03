package com.measures.angle

import kotlin.jvm.JvmInline
import kotlin.math.PI

@JvmInline
value class Degree(override val value: Double) : UnitAngle<Degree> {
    override fun asType(d: Double) = Degree(d)
    override fun asBaseUnit() = Radian(this.value * PI / 180.0)

    override operator fun plus(other: UnitAngle<*>) = UnitAngle.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitAngle<*>) = UnitAngle.Companion.minusUnit(this, other)
}

fun UnitAngle<*>.toDegree() = Degree(this.asBaseUnit().value * 180.0 / PI)
