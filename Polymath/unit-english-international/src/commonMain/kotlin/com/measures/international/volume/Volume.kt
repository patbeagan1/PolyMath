package com.measures.international.volume

import com.measures.volume.Liter
import com.measures.volume.UnitVolume

fun UnitVolume<*>.toInternationalCubicFoot() = toUnit(InternationalCubicFoot(1.0))
fun UnitVolume<*>.toInternationalCubicInch() = toUnit(InternationalCubicInch(1.0))
fun UnitVolume<*>.toInternationalCubicYard() = toUnit(InternationalCubicYard(1.0))

@JvmInline
value class InternationalCubicInch(override val value: Double) : UnitVolume<InternationalCubicInch> {
    override fun asType(d: Double) = InternationalCubicInch(d)
    override fun asBaseUnit() = Liter(value * 1.63871E-05 * 1000)
}

@JvmInline
value class InternationalCubicFoot(override val value: Double) : UnitVolume<InternationalCubicFoot> {
    override fun asType(d: Double) = InternationalCubicFoot(d)
    override fun asBaseUnit() = InternationalCubicInch(value * 1728).asBaseUnit()
}

@JvmInline
value class InternationalCubicYard(override val value: Double) : UnitVolume<InternationalCubicYard> {
    override fun asType(d: Double) = InternationalCubicYard(d)
    override fun asBaseUnit() = InternationalCubicFoot(value * 27).asBaseUnit()
}

