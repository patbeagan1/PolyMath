package com.measures.angle

import com.measures.BaseUnit
import kotlin.jvm.JvmInline

@JvmInline
value class Radian(override val value: Double) : UnitAngle<Radian>, BaseUnit {
    override fun asType(d: Double) = Radian(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitAngle<*>) = (this as UnitAngle<*>).plusUnit(other)
    override operator fun minus(other: UnitAngle<*>) = (this as UnitAngle<*>).minusUnit(other)
}

fun UnitAngle<*>.toRadian() = this.asBaseUnit()
