package com.measures.misc.other

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline
@JvmInline
value class TeaspoonCanadian(override val value: Double) : UnitVolume<TeaspoonCanadian> {
    override fun asType(d: Double) = TeaspoonCanadian(d)
    override fun asBaseUnit() = Liter(value * 4.73551E-06 * 1000)

    override fun plus(other: UnitVolume<*>): Liter = UnitVolume.Companion.plusUnit(this, other)
    override fun minus(other: UnitVolume<*>): Liter = UnitVolume.Companion.minusUnit(this, other)
    override fun div(other: UnitArea<*>): Meter = UnitVolume.Companion.divUnit(this, other)
    override fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.Companion.divUnit(this, other)
}

fun UnitVolume<*>.toTeaspoonCanadian() = toUnit(TeaspoonCanadian(1.0))
