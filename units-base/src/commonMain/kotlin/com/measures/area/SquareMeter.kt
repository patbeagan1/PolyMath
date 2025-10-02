package com.measures.area

import com.measures.BaseUnit
import com.measures.distance.UnitDistance
import kotlin.jvm.JvmInline

@JvmInline
value class SquareMeter(override val value: Double) : UnitArea<SquareMeter>, BaseUnit {
    override fun asType(d: Double): SquareMeter = SquareMeter(d)
    override fun asBaseUnit(): SquareMeter = this

    override operator fun plus(other: UnitArea<*>) = UnitArea.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitArea<*>) = UnitArea.Companion.minusUnit(this, other)
    override operator fun div(other: UnitDistance<*>) = UnitArea.Companion.divUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitArea.Companion.timesUnit(this, other)
}

fun UnitArea<*>.toSquareMeter() = this.asBaseUnit()