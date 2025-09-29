package com.measures.volume

import com.measures.BaseUnit
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import kotlin.jvm.JvmInline

@JvmInline
value class Liter(override val value: Double) : UnitVolume<Liter>, BaseUnit {
    override fun asType(d: Double) = Liter(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitVolume<*>) = (this as UnitVolume<*>).plus(other)
    operator fun minus(other: UnitVolume<*>) = (this as UnitVolume<*>).minus(other)
    operator fun div(other: UnitArea<*>) = (this as UnitVolume<*>).div(other)
    operator fun div(other: UnitDistance<*>) = (this as UnitVolume<*>).div(other)
}

fun UnitVolume<*>.toLiter() = this.asBaseUnit()