package com.measures.area

import com.measures.BaseUnit
import com.measures.distance.UnitDistance
import kotlin.jvm.JvmInline

@JvmInline
value class SquareMeter(override val value: Double) : UnitArea<SquareMeter>, BaseUnit {
    override fun asType(d: Double): SquareMeter = SquareMeter(d)
    override fun asBaseUnit(): SquareMeter = this

    operator fun plus(other: UnitArea<*>) = (this as UnitArea<*>).plus(other)
    operator fun minus(other: UnitArea<*>) = (this as UnitArea<*>).minus(other)
    operator fun div(other: UnitDistance<*>) = (this as UnitArea<*>).div(other)
    operator fun times(other: UnitDistance<*>) = (this as UnitArea<*>).times(other)
}

fun UnitArea<*>.toSquareMeter() = this.asBaseUnit()