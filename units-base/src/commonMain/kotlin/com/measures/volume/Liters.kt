package com.measures.volume

import com.measures.BaseUnit
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import kotlin.jvm.JvmInline

@JvmInline
value class Liters(override val value: Double) : UnitVolume<Liters>, BaseUnit {
    override fun asType(d: Double) = Liters(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitVolume<*>) = (this as UnitVolume<*>).plus(other)
    override operator fun minus(other: UnitVolume<*>) = (this as UnitVolume<*>).minus(other)
    override operator fun div(other: UnitArea<*>) = (this as UnitVolume<*>).div(other)
    override operator fun div(other: UnitDistance<*>) = (this as UnitVolume<*>).div(other)
}

fun UnitVolume<*>.toLiter() = this.asBaseUnit()