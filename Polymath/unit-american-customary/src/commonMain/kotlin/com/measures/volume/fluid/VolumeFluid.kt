package com.measures.volume.fluid

import com.measures.volume.Liter
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

fun UnitVolume<*>.toUSCup() = toUnit(USCup(1.0))
fun UnitVolume<*>.toUSFluidBarrel() = toUnit(USFluidBarrel(1.0))
fun UnitVolume<*>.toUSFluidDram() = asBaseUnit().toUnit(USFluidDram(1.0))
fun UnitVolume<*>.toUSFluidGallon() = toUnit(USFluidGallon(1.0))
fun UnitVolume<*>.toUSFluidOunce() = toUnit(USFluidOunce(1.0))
fun UnitVolume<*>.toUSFluidPint() = toUnit(USFluidPint(1.0))
fun UnitVolume<*>.toUSFluidPottle() = toUnit(USFluidPottle(1.0))
fun UnitVolume<*>.toUSFluidQuart() = toUnit(USFluidQuart(1.0))
fun UnitVolume<*>.toUSGill() = toUnit(USGill(1.0))
fun UnitVolume<*>.toUSMinim() = toUnit(USMinim(1.0))
fun UnitVolume<*>.toUSOilBarrel() = toUnit(USOilBarrel(1.0))
fun UnitVolume<*>.toUSShot() = asBaseUnit().toUnit(USShot(1.0))
fun UnitVolume<*>.toTablespoon() = asBaseUnit().toUnit(USTablespoon(1.0))
fun UnitVolume<*>.toUSTeaspoon() = asBaseUnit().toUnit(USTeaspoon(1.0))

@JvmInline
value class USCup(override val value: Double) : UnitVolume<USCup> {
    override fun asType(d: Double) = USCup(d);
    override fun asBaseUnit() = USFluidPint(value / 2.0).asBaseUnit()
}

@JvmInline
value class USFluidBarrel(override val value: Double) : UnitVolume<USFluidBarrel> {
    override fun asType(d: Double) = USFluidBarrel(d);
    override fun asBaseUnit() = USFluidGallon(value * 31.5).asBaseUnit()
}

@JvmInline
value class USFluidDram(override val value: Double) : UnitVolume<USFluidDram> {
    override fun asType(d: Double) = USFluidDram(d);
    override fun asBaseUnit() = USTeaspoon(value * 0.75).asBaseUnit()
}

@JvmInline
value class USFluidGallon(override val value: Double) : UnitVolume<USFluidGallon> {
    override fun asType(d: Double) = USFluidGallon(d);
    override fun asBaseUnit() = USFluidQuart(value * 4.0).asBaseUnit()
}

@JvmInline
value class USFluidOunce(override val value: Double) : UnitVolume<USFluidOunce> {
    override fun asType(d: Double) = USFluidOunce(d);
    override fun asBaseUnit() = USCup(value / 8.0).asBaseUnit()
}

@JvmInline
value class USFluidPint(override val value: Double) : UnitVolume<USFluidPint> {
    override fun asType(d: Double) = USFluidPint(d);
    override fun asBaseUnit() = USFluidQuart(value / 2.0).asBaseUnit()
}

@JvmInline
value class USFluidPottle(override val value: Double) : UnitVolume<USFluidPottle> {
    override fun asType(d: Double) = USFluidPottle(d);
    override fun asBaseUnit() = USFluidQuart(value * 2.0).asBaseUnit()
}

@JvmInline
value class USFluidQuart(override val value: Double) : UnitVolume<USFluidQuart> {
    override fun asType(d: Double) = USFluidQuart(d);
    override fun asBaseUnit() = Liter(value * 0.946352946)
}

@JvmInline
value class USGill(override val value: Double) : UnitVolume<USGill> {
    override fun asType(d: Double) = USGill(d);
    override fun asBaseUnit() = USCup(value / 2.0).asBaseUnit()
}

@JvmInline
value class USHogshead(override val value: Double) : UnitVolume<USHogshead> {
    override fun asType(d: Double) = USHogshead(d);
    override fun asBaseUnit() = USFluidGallon(value * 63.0).asBaseUnit()
}

@JvmInline
value class USMinim(override val value: Double) : UnitVolume<USMinim> {
    override fun asType(d: Double) = USMinim(d);
    override fun asBaseUnit() = USFluidDram(value / 60.0).asBaseUnit()
}

@JvmInline
value class USOilBarrel(override val value: Double) : UnitVolume<USOilBarrel> {
    override fun asType(d: Double) = USOilBarrel(d);
    override fun asBaseUnit() = USFluidGallon(value * 42.0).asBaseUnit()
}

@JvmInline
value class USShot(override val value: Double) : UnitVolume<USShot> {
    override fun asType(d: Double) = USShot(d);
    override fun asBaseUnit() = USTablespoon(value * 1.5).asBaseUnit()
}

@JvmInline
value class USTablespoon(override val value: Double) : UnitVolume<USTablespoon> {
    override fun asType(d: Double) = USTablespoon(d);
    override fun asBaseUnit() = USFluidOunce(value / 2.0).asBaseUnit()
}

@JvmInline
value class USTeaspoon(override val value: Double) : UnitVolume<USTeaspoon> {
    override fun asType(d: Double) = USTeaspoon(d);
    override fun asBaseUnit() = USTablespoon(value / 3.0).asBaseUnit()
}
