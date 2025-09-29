package com.measures.distance

import com.measures.BaseUnit
import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.volume.Liter
import kotlin.jvm.JvmInline

@JvmInline
value class Meter(override val value: Double) : UnitDistance<Meter>, BaseUnit {
    override fun asType(d: Double) = Meter(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plus(other)
    operator fun minus(other: UnitDistance<*>): Meter = (this as UnitDistance<*>).minus(other)
    operator fun times(other: UnitDistance<*>): SquareMeter = (this as UnitDistance<*>).times(other)
    operator fun times(other: UnitArea<*>): Liter = (this as UnitDistance<*>).times(other)
}

fun UnitDistance<*>.toMeter() = this.asBaseUnit()