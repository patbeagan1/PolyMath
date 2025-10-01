package com.measures.solidangle

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import kotlin.jvm.JvmInline

typealias UnitSolidAngle<T> = UnitSolidAngleType<T>

interface UnitSolidAngleType<T : DoubleBase> : UnitType<T, Steradian>

@JvmInline
value class Steradian(override val value: Double) : UnitSolidAngle<Steradian>, BaseUnit {
    override fun asType(d: Double) = Steradian(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitSolidAngle<*>) = (this as UnitSolidAngle<*>).minusUnit(other)
    operator fun minus(other: UnitSolidAngle<*>) = (this as UnitSolidAngle<*>).minusUnit(other)
}

fun UnitSolidAngleType<*>.plusUnit(other: UnitSolidAngleType<*>): Steradian =
    Steradian(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitSolidAngleType<*>.minusUnit(other: UnitSolidAngleType<*>): Steradian =
    Steradian(this.asBaseUnit().value - other.asBaseUnit().value)

fun UnitSolidAngle<*>.toSteradian() = this.asBaseUnit()
