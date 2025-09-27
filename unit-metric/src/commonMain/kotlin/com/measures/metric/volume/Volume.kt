package com.measures.metric.volume

import com.measures.Consts
import com.measures.volume.Liter
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

fun UnitVolume<*>.toAttoliter() = toUnit(Attoliter(1.0))
fun UnitVolume<*>.toCentiliter() = toUnit(Centiliter(1.0))
fun UnitVolume<*>.toCubicCentimeter() = toUnit(CubicCentimeter(1.0))
fun UnitVolume<*>.toCubicMeter() = toUnit(CubicMeter(1.0))
fun UnitVolume<*>.toDeciliter() = toUnit(Deciliter(1.0))
fun UnitVolume<*>.toDekaliter() = toUnit(Dekaliter(1.0))
fun UnitVolume<*>.toExaliter() = toUnit(Exaliter(1.0))
fun UnitVolume<*>.toFemtoliter() = toUnit(Femtoliter(1.0))
fun UnitVolume<*>.toGigaliter() = toUnit(Gigaliter(1.0))
fun UnitVolume<*>.toHectoliter() = toUnit(Hectoliter(1.0))
fun UnitVolume<*>.toKiloliter() = toUnit(Kiloliter(1.0))
fun UnitVolume<*>.toMegaliter() = toUnit(Megaliter(1.0))
fun UnitVolume<*>.toMicroliter() = toUnit(Microliter(1.0))
fun UnitVolume<*>.toMilliliter() = toUnit(Milliliter(1.0))
fun UnitVolume<*>.toNanoliter() = toUnit(Nanoliter(1.0))
fun UnitVolume<*>.toPetaliter() = toUnit(Petaliter(1.0))
fun UnitVolume<*>.toPicoliter() = toUnit(Picoliter(1.0))
fun UnitVolume<*>.toTeraliter() = toUnit(Teraliter(1.0))
fun UnitVolume<*>.toYoctoliter() = toUnit(Yoctoliter(1.0))
fun UnitVolume<*>.toYottaliter() = toUnit(Yottaliter(1.0))
fun UnitVolume<*>.toZeptoliter() = toUnit(Zeptoliter(1.0))
fun UnitVolume<*>.toZettaliter() = toUnit(Zettaliter(1.0))

@JvmInline
value class Attoliter(override val value: Double) : UnitVolume<Attoliter> {
    override fun asType(d: Double) = Attoliter(d);
    override fun asBaseUnit() = Liter(value * Consts.ATTO)
}

@JvmInline
value class Centiliter(override val value: Double) : UnitVolume<Centiliter> {
    override fun asType(d: Double) = Centiliter(d);
    override fun asBaseUnit() = Liter(value * Consts.CENTI)
}

@JvmInline
value class CubicCentimeter(override val value: Double) : UnitVolume<CubicCentimeter> {
    override fun asType(d: Double) = CubicCentimeter(d)
    override fun asBaseUnit() = Liter(value * 0.000001 * 1000)
}

@JvmInline
value class CubicMeter(override val value: Double) : UnitVolume<CubicMeter> {
    override fun asType(d: Double) = CubicMeter(d)
    override fun asBaseUnit() = Liter(value * 1 * 1000)
}

@JvmInline
value class Deciliter(override val value: Double) : UnitVolume<Deciliter> {
    override fun asType(d: Double) = Deciliter(d);
    override fun asBaseUnit() = Liter(value * Consts.DECI)
}

@JvmInline
value class Dekaliter(override val value: Double) : UnitVolume<Dekaliter> {
    override fun asType(d: Double) = Dekaliter(d);
    override fun asBaseUnit() = Liter(value * Consts.DEKA)
}

@JvmInline
value class Exaliter(override val value: Double) : UnitVolume<Exaliter> {
    override fun asType(d: Double) = Exaliter(d);
    override fun asBaseUnit() = Liter(value * Consts.EXA)
}

@JvmInline
value class Femtoliter(override val value: Double) : UnitVolume<Femtoliter> {
    override fun asType(d: Double) = Femtoliter(d);
    override fun asBaseUnit() = Liter(value * Consts.FEMTO)
}

@JvmInline
value class Gigaliter(override val value: Double) : UnitVolume<Gigaliter> {
    override fun asType(d: Double) = Gigaliter(d);
    override fun asBaseUnit() = Liter(value * Consts.GIGA)
}

@JvmInline
value class Hectoliter(override val value: Double) : UnitVolume<Hectoliter> {
    override fun asType(d: Double) = Hectoliter(d);
    override fun asBaseUnit() = Liter(value * Consts.HECTO)
}

@JvmInline
value class Kiloliter(override val value: Double) : UnitVolume<Kiloliter> {
    override fun asType(d: Double) = Kiloliter(d);
    override fun asBaseUnit() = Liter(value * Consts.KILO)
}

@JvmInline
value class Megaliter(override val value: Double) : UnitVolume<Megaliter> {
    override fun asType(d: Double) = Megaliter(d);
    override fun asBaseUnit() = Liter(value * Consts.MEGA)
}

@JvmInline
value class Microliter(override val value: Double) : UnitVolume<Microliter> {
    override fun asType(d: Double) = Microliter(d);
    override fun asBaseUnit() = Liter(value * Consts.MICRO)
}

@JvmInline
value class Milliliter(override val value: Double) : UnitVolume<Milliliter> {
    override fun asType(d: Double) = Milliliter(d);
    override fun asBaseUnit() = Liter(value * Consts.MILLI)
}

@JvmInline
value class Nanoliter(override val value: Double) : UnitVolume<Nanoliter> {
    override fun asType(d: Double) = Nanoliter(d);
    override fun asBaseUnit() = Liter(value * Consts.NANO)
}

@JvmInline
value class Petaliter(override val value: Double) : UnitVolume<Petaliter> {
    override fun asType(d: Double) = Petaliter(d);
    override fun asBaseUnit() = Liter(value * Consts.PETA)
}

@JvmInline
value class Picoliter(override val value: Double) : UnitVolume<Picoliter> {
    override fun asType(d: Double) = Picoliter(d);
    override fun asBaseUnit() = Liter(value * Consts.PICO)
}

@JvmInline
value class Teraliter(override val value: Double) : UnitVolume<Teraliter> {
    override fun asType(d: Double) = Teraliter(d);
    override fun asBaseUnit() = Liter(value * Consts.TERA)
}

@JvmInline
value class Yoctoliter(override val value: Double) : UnitVolume<Yoctoliter> {
    override fun asType(d: Double) = Yoctoliter(d);
    override fun asBaseUnit() = Liter(value * Consts.YOCTO)
}

@JvmInline
value class Yottaliter(override val value: Double) : UnitVolume<Yottaliter> {
    override fun asType(d: Double) = Yottaliter(d);
    override fun asBaseUnit() = Liter(value * Consts.YOTTA)
}

@JvmInline
value class Zeptoliter(override val value: Double) : UnitVolume<Zeptoliter> {
    override fun asType(d: Double) = Zeptoliter(d);
    override fun asBaseUnit() = Liter(value * Consts.ZEPTO)
}

@JvmInline
value class Zettaliter(override val value: Double) : UnitVolume<Zettaliter> {
    override fun asType(d: Double) = Zettaliter(d);
    override fun asBaseUnit() = Liter(value * Consts.ZETTA)
}

