package com.measures.angle

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import kotlin.jvm.JvmInline
import kotlin.math.PI

typealias UnitAngle<T> = UnitAngleType<T>

interface UnitAngleType<T : DoubleBase> : UnitType<T, Radian>

@JvmInline
value class Radian(override val value: Double) : UnitAngle<Radian>, BaseUnit {
    override fun asType(d: Double) = Radian(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitAngle<*>) = (this as UnitAngle<*>).plusUnit(other)
    operator fun minus(other: UnitAngle<*>) = (this as UnitAngle<*>).minusUnit(other)
}

// Non-SI angle units have been moved to units-common module

 fun UnitAngleType<*>.plusUnit(other: UnitAngleType<*>): Radian =
    Radian(this.asBaseUnit().value + other.asBaseUnit().value)

 fun UnitAngleType<*>.minusUnit(other: UnitAngleType<*>): Radian =
    Radian(this.asBaseUnit().value - other.asBaseUnit().value)

fun UnitAngle<*>.toRadian() = this.asBaseUnit()
