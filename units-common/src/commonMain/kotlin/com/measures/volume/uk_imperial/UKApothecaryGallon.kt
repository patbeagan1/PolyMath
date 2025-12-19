package com.measures.volume.uk_imperial

import com.measures.area.UnitArea
import com.measures.area.SquareMeter
import com.measures.distance.UnitDistance
import com.measures.volume.Liters
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class UKApothecaryGallon(override val value: Double) : UnitVolume<UKApothecaryGallon> {
    override fun asType(d: Double) = UKApothecaryGallon(d)
    override fun asBaseUnit() = UKApothecaryPint(value * 8).asBaseUnit()

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.divUnit(this, other)
}

fun UnitVolume<*>.toUKApothecaryGallon() = toUnit(UKApothecaryGallon(1.0))
