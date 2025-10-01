package com.measures.distance

import com.measures.BaseUnit
import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.time.UnitTime
import com.measures.velocity.MetersPerSecond
import com.measures.volume.Liter
import kotlin.jvm.JvmInline

@JvmInline
value class Meter(override val value: Double) : UnitDistance<Meter>, BaseUnit {
    override fun asType(d: Double) = Meter(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond = (this as UnitDistance<*>).divUnit(other)
}

fun UnitDistance<*>.toMeter() = this.asBaseUnit()