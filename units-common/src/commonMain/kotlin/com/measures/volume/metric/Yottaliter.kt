package com.measures.volume.metric

import com.measures.Consts
import com.measures.area.UnitArea
import com.measures.area.SquareMeter
import com.measures.distance.UnitDistance
import com.measures.volume.Liters
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class Yottaliter(override val value: Double) : UnitVolume<Yottaliter> {
    override fun asType(d: Double) = Yottaliter(d)
    override fun asBaseUnit() = Liters(value * Consts.YOTTA)

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.divUnit(this, other)
}

fun UnitVolume<*>.toYottaliter() = toUnit(Yottaliter(1.0))
