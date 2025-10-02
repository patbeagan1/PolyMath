package com.measures.volume

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import kotlin.jvm.JvmInline

// English International Volume Units
fun UnitVolume<*>.toInternationalCubicFoot() = toUnit(InternationalCubicFoot(1.0))
fun UnitVolume<*>.toInternationalCubicInch() = toUnit(InternationalCubicInch(1.0))
fun UnitVolume<*>.toInternationalCubicYard() = toUnit(InternationalCubicYard(1.0))

@JvmInline
value class InternationalCubicInch(override val value: Double) : UnitVolume<InternationalCubicInch> {
    override fun asType(d: Double) = InternationalCubicInch(d)
    override fun asBaseUnit() = Liter(value * 1.63871E-05 * 1000)

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.Companion.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.Companion.divUnit(this, other)
    override operator  fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.Companion.divUnit(this, other)
}

@JvmInline
value class InternationalCubicFoot(override val value: Double) : UnitVolume<InternationalCubicFoot> {
    override fun asType(d: Double) = InternationalCubicFoot(d)
    override fun asBaseUnit() = InternationalCubicInch(value * 1728).asBaseUnit()

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.Companion.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.Companion.divUnit(this, other)
    override operator  fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.Companion.divUnit(this, other)
}

@JvmInline
value class InternationalCubicYard(override val value: Double) : UnitVolume<InternationalCubicYard> {
    override fun asType(d: Double) = InternationalCubicYard(d)
    override fun asBaseUnit() = InternationalCubicFoot(value * 27).asBaseUnit()

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.Companion.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.Companion.divUnit(this, other)
    override operator  fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.Companion.divUnit(this, other)
}
