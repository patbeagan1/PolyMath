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

    operator fun plus(other: UnitAngle<*>) = (this as UnitAngle<*>).plus(other)
    operator fun minus(other: UnitAngle<*>) = (this as UnitAngle<*>).minus(other)
}

@JvmInline
value class Degree(override val value: Double) : UnitAngle<Degree> {
    override fun asType(d: Double) = Degree(d)
    override fun asBaseUnit() = Radian(this.value * PI / 180.0)

    operator fun plus(other: UnitAngle<*>) = (this as UnitAngle<*>).plus(other)
    operator fun minus(other: UnitAngle<*>) = (this as UnitAngle<*>).minus(other)
}

operator fun UnitAngleType<*>.plus(other: UnitAngleType<*>): Radian =
    Radian(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitAngleType<*>.minus(other: UnitAngleType<*>): Radian =
    Radian(this.asBaseUnit().value - other.asBaseUnit().value)

fun UnitAngle<*>.toRadian() = this.asBaseUnit()
fun UnitAngle<*>.toDegree() = Degree(this.asBaseUnit().value * 180.0 / PI)
