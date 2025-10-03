package com.measures.volume.american_customary_dry

import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class USDryGallon(override val value: Double) : UnitVolume<USDryGallon> {
override fun asType(d: Double) = USDryGallon(d)
    override fun asBaseUnit() = Liter(value * 0.004404884 * 1000)

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.Companion.minusUnit(this, other)
    override operator fun div(other: UnitDistance<*>) = UnitVolume.Companion.divUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.Companion.divUnit(this, other)
}

fun UnitVolume<*>.toUSDryGallon() = toUnit(USDryGallon(1.0))
