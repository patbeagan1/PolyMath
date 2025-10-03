package com.measures.misc.other

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline
@JvmInline
value class CupBreakfast(override val value: Double) : UnitVolume<CupBreakfast> {
    override fun asType(d: Double) = CupBreakfast(d)
    override fun asBaseUnit() = Liter(value * 0.000284131 * 1000)

    override fun plus(other: UnitVolume<*>): Liter = UnitVolume.Companion.plusUnit(this, other)
    override fun minus(other: UnitVolume<*>): Liter = UnitVolume.Companion.minusUnit(this, other)
    override fun div(other: UnitArea<*>): Meter = UnitVolume.Companion.divUnit(this, other)
    override fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.Companion.divUnit(this, other)
}

fun UnitVolume<*>.toCupBreakfast() = toUnit(CupBreakfast(1.0))
