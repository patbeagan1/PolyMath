package com.measures.volume.us_international_dry_1959

import com.measures.area.UnitArea
import com.measures.area.SquareMeter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class USBushel(override val value: Double) : UnitVolume<USBushel> {
    override fun asType(d: Double) = USBushel(d)
    override fun asBaseUnit() = USPeck(value * 4).asBaseUnit()

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.divUnit(this, other)
}

fun UnitVolume<*>.toUSBushel() = toUnit(USBushel(1.0))
