package com.measures.volume.american_customary_dry

import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline
@JvmInline
value class USDryQuart(override val value: Double) : UnitVolume<USDryQuart> {
    override fun asType(d: Double) = USDryQuart(d)
    override fun asBaseUnit() = Liter(value * 0.001101221 * 1000)

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.Companion.minusUnit(this, other)
    override operator fun div(other: UnitDistance<*>) = UnitVolume.Companion.divUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.Companion.divUnit(this, other)
}

fun UnitVolume<*>.toUSDryQuart() = toUnit(USDryQuart(1.0))
