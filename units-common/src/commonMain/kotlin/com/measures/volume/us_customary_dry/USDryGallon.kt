package com.measures.volume.us_customary_dry

import com.measures.area.UnitArea
import com.measures.area.SquareMeter
import com.measures.distance.UnitDistance
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class USDryGallon(override val value: Double) : UnitVolume<USDryGallon> {
    override fun asType(d: Double) = USDryGallon(d)
    override fun asBaseUnit() = USDryPint(value * 8).asBaseUnit()

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.divUnit(this, other)
}

fun UnitVolume<*>.toUSDryGallon() = toUnit(USDryGallon(1.0))
