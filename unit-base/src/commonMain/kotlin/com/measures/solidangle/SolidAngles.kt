package com.measures.solidangle

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitTypedFull
import kotlin.jvm.JvmInline

typealias UnitSolidAngle<T> = UnitSolidAngleTypedFull<T>

interface UnitSolidAngleTypedFull<T : DoubleBase> : UnitTypedFull<T, Steradian> {
    operator fun plus(other: UnitSolidAngleTypedFull<*>) =
        Steradian(this.asBaseUnit().value + other.asBaseUnit().value)

    operator fun minus(other: UnitSolidAngleTypedFull<*>) =
        Steradian(this.asBaseUnit().value - other.asBaseUnit().value)
}

@JvmInline
value class Steradian(override val value: Double) : UnitSolidAngle<Steradian>, BaseUnit {
    override fun asType(d: Double) = Steradian(d)
    override fun asBaseUnit() = this
}

fun UnitSolidAngle<*>.toSteradian() = this.asBaseUnit()
