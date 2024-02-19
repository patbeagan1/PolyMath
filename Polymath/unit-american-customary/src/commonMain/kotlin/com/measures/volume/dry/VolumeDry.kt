package com.measures.volume.dry

import com.measures.volume.Liter
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

fun UnitVolume<*>.toUSDryBarrel() = toUnit(USDryBarrel(1.0))
fun UnitVolume<*>.toUSDryBushel() = toUnit(USDryBushel(1.0))
fun UnitVolume<*>.toUSDryGallon() = toUnit(USDryGallon(1.0))
fun UnitVolume<*>.toUSDryPeck() = toUnit(USDryPeck(1.0))
fun UnitVolume<*>.toUSDryPint() = toUnit(USDryPint(1.0))
fun UnitVolume<*>.toUSDryQuart() = toUnit(USDryQuart(1.0))

@JvmInline
value class USDryBarrel(override val value: Double) : UnitVolume<USDryBarrel> {
    override fun asType(d: Double) = USDryBarrel(d)
    override fun asBaseUnit() = Liter(value * 0.115628199 * 1000)
}

@JvmInline
value class USDryBushel(override val value: Double) : UnitVolume<USDryBushel> {
    override fun asType(d: Double) = USDryBushel(d)
    override fun asBaseUnit() = Liter(value * 0.03523907 * 1000)
}

@JvmInline
value class USDryGallon(override val value: Double) : UnitVolume<USDryGallon> {
    override fun asType(d: Double) = USDryGallon(d)
    override fun asBaseUnit() = Liter(value * 0.004404884 * 1000)
}

@JvmInline
value class USDryPeck(override val value: Double) : UnitVolume<USDryPeck> {
    override fun asType(d: Double) = USDryPeck(d)
    override fun asBaseUnit() = Liter(value * 0.008809768 * 1000)
}

@JvmInline
value class USDryPint(override val value: Double) : UnitVolume<USDryPint> {
    override fun asType(d: Double) = USDryPint(d)
    override fun asBaseUnit() = Liter(value * 0.00055061 * 1000)
}

@JvmInline
value class USDryQuart(override val value: Double) : UnitVolume<USDryQuart> {
    override fun asType(d: Double) = USDryQuart(d)
    override fun asBaseUnit() = Liter(value * 0.001101221 * 1000)
}

