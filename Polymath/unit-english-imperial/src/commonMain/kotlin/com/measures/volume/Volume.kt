package com.measures.volume

import kotlin.jvm.JvmInline

fun UnitVolume<*>.toImperialFluidOunce() = toUnit(ImperialFluidOunce(1.0))
fun UnitVolume<*>.toImperialGill() = toUnit(ImperialGill(1.0))
fun UnitVolume<*>.toImperialPint() = toUnit(ImperialPint(1.0))
fun UnitVolume<*>.toImperialQuart() = toUnit(ImperialQuart(1.0))
fun UnitVolume<*>.toImperialGallon() = toUnit(ImperialGallon(1.0))

@JvmInline
value class ImperialFluidOunce(override val value: Double) : UnitVolume<ImperialFluidOunce> {
    override fun asType(d: Double) = ImperialFluidOunce(d)
    override fun asBaseUnit() = Liter(this.value * 28.4130625 / 1000)
}

@JvmInline
value class ImperialGill(override val value: Double) : UnitVolume<ImperialGill> {
    override fun asType(d: Double) = ImperialGill(d)
    override fun asBaseUnit() = ImperialFluidOunce(this.value * 5.0).asBaseUnit()
}

@JvmInline
value class ImperialPint(override val value: Double) : UnitVolume<ImperialPint> {
    override fun asType(d: Double) = ImperialPint(d)
    override fun asBaseUnit() = ImperialFluidOunce(this.value * 20.0).asBaseUnit()
}

@JvmInline
value class ImperialQuart(override val value: Double) : UnitVolume<ImperialQuart> {
    override fun asType(d: Double) = ImperialQuart(d)
    override fun asBaseUnit() = ImperialFluidOunce(this.value * 40.0).asBaseUnit()
}

@JvmInline
value class ImperialGallon(override val value: Double) : UnitVolume<ImperialGallon> {
    override fun asType(d: Double) = ImperialGallon(d)
    override fun asBaseUnit() = ImperialFluidOunce(this.value * 160.0).asBaseUnit()
}

