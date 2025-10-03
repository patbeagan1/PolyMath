package com.measures.volume.american_customary_fluid

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline
@JvmInline
value class USFluidPottle(override val value: Double) : UnitVolume<USFluidPottle> {
    override fun asType(d: Double) = USFluidPottle(d)
    override fun asBaseUnit() = USFluidQuart(value * 2.0).asBaseUnit()

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.Companion.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.Companion.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.Companion.divUnit(this, other)
}

fun UnitVolume<*>.toUSFluidPottle() = toUnit(USFluidPottle(1.0))
