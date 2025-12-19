package com.measures.volume.uk_imperial

import com.measures.area.UnitArea
import com.measures.area.SquareMeter
import com.measures.distance.UnitDistance
import com.measures.volume.Liters
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class UKQuart(override val value: Double) : UnitVolume<UKQuart> {
    override fun asType(d: Double) = UKQuart(d)
    override fun asBaseUnit() = UKGallon(value / 4).asBaseUnit()

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.divUnit(this, other)
}

fun UnitVolume<*>.toUKQuart() = toUnit(UKQuart(1.0))
