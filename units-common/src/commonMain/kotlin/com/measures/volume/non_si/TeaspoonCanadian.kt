package com.measures.volume.non_si

import com.measures.area.UnitArea
import com.measures.area.SquareMeter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class TeaspoonCanadian(override val value: Double) : UnitVolume<TeaspoonCanadian> {
    override fun asType(d: Double) = TeaspoonCanadian(d)
    override fun asBaseUnit() = Liter(value * 0.004735510416666667)

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.divUnit(this, other)
}

fun UnitVolume<*>.toTeaspoonCanadian() = toUnit(TeaspoonCanadian(1.0))
