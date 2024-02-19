package com.measures.other

import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import com.measures.volume.UnitVolume

fun UnitVolume<*>.toCupBreakfast() = toUnit(CupBreakfast(1.0))
fun UnitVolume<*>.toCupCanadian() = toUnit(CupCanadian(1.0))
fun UnitVolume<*>.toTablespoonCanadian() = toUnit(TablespoonCanadian(1.0))
fun UnitVolume<*>.toTeaspoonCanadian() = toUnit(TeaspoonCanadian(1.0))

fun UnitDistance<*>.toAngstroms() = asBaseUnit().toUnit(Angstroms(1.0))
fun UnitDistance<*>.toCapefeet() = toUnit(Capefeet(1.0))
fun UnitDistance<*>.toMicrons() = toUnit(Microns(1.0))
fun UnitDistance<*>.toMils() = toUnit(Mils(1.0))

@JvmInline
value class Angstroms(override val value: Double) : UnitDistance<Angstroms> {
    override fun asType(d: Double) = Angstroms(d)
    override fun asBaseUnit() = com.measures.distance.Meter(this.value * 1E-10)
}

@JvmInline
value class Capefeet(override val value: Double) : UnitDistance<Capefeet> {
    override fun asType(d: Double) = Capefeet(d)
    override fun asBaseUnit() = com.measures.distance.Meter(this.value * 0.314856)
}

@JvmInline
value class CupBreakfast(override val value: Double) : UnitVolume<CupBreakfast> {
    override fun asType(d: Double) = CupBreakfast(d)
    override fun asBaseUnit() = Liter(value * 0.000284131 * 1000)
}

@JvmInline
value class CupCanadian(override val value: Double) : UnitVolume<CupCanadian> {
    override fun asType(d: Double) = CupCanadian(d)
    override fun asBaseUnit() = Liter(value * 0.000227305 * 1000)
}

@JvmInline
value class Microns(override val value: Double) : UnitDistance<Microns> {
    override fun asType(d: Double) = Microns(d)
    override fun asBaseUnit() = com.measures.distance.Meter(this.value * 0.000001)
}

@JvmInline
value class Mils(override val value: Double) : UnitDistance<Mils> {
    override fun asType(d: Double) = Mils(d)
    override fun asBaseUnit() = com.measures.distance.Meter(this.value * 0.0000254)
}

@JvmInline
value class TablespoonCanadian(override val value: Double) : UnitVolume<TablespoonCanadian> {
    override fun asType(d: Double) = TablespoonCanadian(d)
    override fun asBaseUnit() = Liter(value * 1.42065E-05 * 1000)
}

@JvmInline
value class TeaspoonCanadian(override val value: Double) : UnitVolume<TeaspoonCanadian> {
    override fun asType(d: Double) = TeaspoonCanadian(d)
    override fun asBaseUnit() = Liter(value * 4.73551E-06 * 1000)
}

