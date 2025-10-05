package com.measures.volume.international_1959

import com.measures.area.UnitArea
import com.measures.area.SquareMeter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class InternationalGallon1959(override val value: Double) : UnitVolume<InternationalGallon1959> {
    override fun asType(d: Double) = InternationalGallon1959(d)
    override fun asBaseUnit() = InternationalQuart1959(value * 4).asBaseUnit()

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.divUnit(this, other)
}

fun UnitVolume<*>.toInternationalGallon1959() = toUnit(InternationalGallon1959(1.0))
