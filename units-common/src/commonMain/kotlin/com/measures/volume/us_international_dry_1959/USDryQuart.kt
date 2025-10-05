package com.measures.volume.us_international_dry_1959

import com.measures.area.UnitArea
import com.measures.area.SquareMeter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class USDryQuart(override val value: Double) : UnitVolume<USDryQuart> {
    override fun asType(d: Double) = USDryQuart(d)
    override fun asBaseUnit() = Liter(value * 1.101220942715)

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.divUnit(this, other)
}

fun UnitVolume<*>.toUSDryQuart() = toUnit(USDryQuart(1.0))
