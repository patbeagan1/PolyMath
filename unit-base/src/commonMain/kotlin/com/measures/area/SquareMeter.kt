package com.measures.area

import com.measures.BaseUnit
import com.measures.distance.UnitDistance
import kotlin.jvm.JvmInline

@JvmInline
value class SquareMeter(override val value: Double) : UnitArea<SquareMeter>, BaseUnit {
    override fun asType(d: Double): SquareMeter = SquareMeter(d)
    override fun asBaseUnit(): SquareMeter = this

    override operator fun plus(other: UnitArea<*>) = (this as UnitArea<*>).plusUnit(other)
    override operator fun minus(other: UnitArea<*>) = (this as UnitArea<*>).minusUnit(other)
    override operator fun div(other: UnitDistance<*>) = (this as UnitArea<*>).divUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitArea<*>).timesUnit(other)
}

fun UnitArea<*>.toSquareMeter() = this.asBaseUnit()