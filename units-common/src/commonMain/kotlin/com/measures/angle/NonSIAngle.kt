package com.measures.angle

import kotlin.jvm.JvmInline
import kotlin.math.PI

// Non-SI Angle Units
// These units are not part of the SI system and are moved to units-common

@JvmInline
value class Degree(override val value: Double) : UnitAngle<Degree> {
    override fun asType(d: Double) = Degree(d)
    override fun asBaseUnit() = Radian(this.value * PI / 180.0)

    override operator fun plus(other: UnitAngle<*>) = (this as UnitAngle<*>).plusUnit(other)
    override operator fun minus(other: UnitAngle<*>) = (this as UnitAngle<*>).minusUnit(other)
}

// Conversion functions for non-SI angle units
fun UnitAngle<*>.toDegree() = Degree(this.asBaseUnit().value * 180.0 / PI)
