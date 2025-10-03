package com.measures.misc.other

import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.volume.UnitVolume
import com.measures.volume.Liter
import kotlin.jvm.JvmInline

@JvmInline
value class TeaspoonCanadian(override val value: Double) : UnitVolume<TeaspoonCanadian> {
    override fun asType(d: Double) = TeaspoonCanadian(d)
    override fun asBaseUnit() = Liter(value * 0.000284131 * 1000)

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.Companion.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.Companion.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>) = UnitVolume.Companion.divUnit(this, other)
}

fun UnitVolume<*>.toTeaspoonCanadian() = toUnit(TeaspoonCanadian(1.0))
