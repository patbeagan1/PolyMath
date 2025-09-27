package com.measures.angle

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitTypedFull
import kotlin.jvm.JvmInline
import kotlin.math.PI

typealias UnitAngle<T> = UnitAngleTypedFull<T>

interface UnitAngleTypedFull<T : DoubleBase> : UnitTypedFull<T, Radian> {
    operator fun plus(other: UnitAngleTypedFull<*>) =
        Radian(this.asBaseUnit().value + other.asBaseUnit().value)

    operator fun minus(other: UnitAngleTypedFull<*>) =
        Radian(this.asBaseUnit().value - other.asBaseUnit().value)
}

@JvmInline
value class Radian(override val value: Double) : UnitAngle<Radian>, BaseUnit {
    override fun asType(d: Double) = Radian(d)
    override fun asBaseUnit() = this
}

@JvmInline
value class Degree(override val value: Double) : UnitAngle<Degree> {
    override fun asType(d: Double) = Degree(d)
    override fun asBaseUnit() = Radian(this.value * PI / 180.0)
}

fun UnitAngle<*>.toRadian() = this.asBaseUnit()
fun UnitAngle<*>.toDegree() = Degree(this.asBaseUnit().value * 180.0 / PI)
