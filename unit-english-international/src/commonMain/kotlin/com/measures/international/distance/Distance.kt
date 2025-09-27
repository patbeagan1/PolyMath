package com.measures.international.distance

import com.measures.distance.Meter
import com.measures.distance.UnitDistance

fun UnitDistance<*>.toInternationalFoot() = toUnit(InternationalFoot(1.0))
fun UnitDistance<*>.toInternationalInch() = toUnit(InternationalInch(1.0))
fun UnitDistance<*>.toInternationalMile() = toUnit(InternationalMile(1.0))
fun UnitDistance<*>.toInternationalPica() = toUnit(InternationalPica(1.0))
fun UnitDistance<*>.toInternationalPoint() = toUnit(InternationalPoint(1.0))
fun UnitDistance<*>.toInternationalYard() = toUnit(InternationalYard(1.0))

@JvmInline
value class InternationalFoot(override val value: Double) : UnitDistance<InternationalFoot> {
    override fun asType(d: Double) = InternationalFoot(d);
    override fun asBaseUnit() = InternationalYard(value / 3.0).asBaseUnit()
}

@JvmInline
value class InternationalInch(override val value: Double) : UnitDistance<InternationalInch> {
    override fun asType(d: Double) = InternationalInch(d);
    override fun asBaseUnit() = InternationalFoot(value / 12.0).asBaseUnit()
}

@JvmInline
value class InternationalMile(override val value: Double) : UnitDistance<InternationalMile> {
    override fun asType(d: Double) = InternationalMile(d);
    override fun asBaseUnit() = InternationalYard(value * 1760.0).asBaseUnit()
}

@JvmInline
value class InternationalPica(override val value: Double) : UnitDistance<InternationalPica> {
    override fun asType(d: Double) = InternationalPica(d);
    override fun asBaseUnit() = InternationalInch(value / 6.0).asBaseUnit()
}

@JvmInline
value class InternationalPoint(override val value: Double) : UnitDistance<InternationalPoint> {
    override fun asType(d: Double) = InternationalPoint(d);
    override fun asBaseUnit() = InternationalPica(value / 12.0).asBaseUnit()
}

@JvmInline
value class InternationalYard(override val value: Double) : UnitDistance<InternationalYard> {
    override fun asType(d: Double) = InternationalYard(d);
    override fun asBaseUnit() = Meter(0.9144 * value)
}


