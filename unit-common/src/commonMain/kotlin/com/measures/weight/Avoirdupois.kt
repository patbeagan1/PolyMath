package com.measures.weight

import kotlin.jvm.JvmInline

// Avoirdupois Weight Units
fun UnitMass<*>.toDram() = toUnit(Dram(1.0))
fun UnitMass<*>.toGrain() = toUnit(Grain(1.0))
fun UnitMass<*>.toLongHundredWeight() = toUnit(LongHundredWeight(1.0))
fun UnitMass<*>.toLongTon() = toUnit(LongTon(1.0))
fun UnitMass<*>.toOunce() = toUnit(Ounce(1.0))
fun UnitMass<*>.toPound() = toUnit(Pound(1.0))
fun UnitMass<*>.toShortQuarter() = toUnit(ShortQuarter(1.0))
fun UnitMass<*>.toLongQuarter() = toUnit(LongQuarter(1.0))
fun UnitMass<*>.toShortHundredWeight() = toUnit(ShortHundredWeight(1.0))
fun UnitMass<*>.toStone() = toUnit(Stone(1.0))
fun UnitMass<*>.toShortTon() = toUnit(ShortTon(1.0))

@JvmInline
value class Dram(override val value: Double) : UnitMass<Dram> {
    override fun asType(d: Double) = Dram(d)
    override fun asBaseUnit() = Pound(value / 256).asBaseUnit()
}

@JvmInline
value class Grain(override val value: Double) : UnitMass<Grain> {
    override fun asType(d: Double) = Grain(d)
    override fun asBaseUnit() = Pound(value / 7000).asBaseUnit()
}

@JvmInline
value class LongHundredWeight(override val value: Double) : UnitMass<LongHundredWeight> {
    override fun asType(d: Double) = LongHundredWeight(d)
    override fun asBaseUnit() = Pound(value * 112).asBaseUnit()
}

@JvmInline
value class LongTon(override val value: Double) : UnitMass<LongTon> {
    override fun asType(d: Double) = LongTon(d)
    override fun asBaseUnit() = Pound(value * 2240).asBaseUnit()
}

@JvmInline
value class Ounce(override val value: Double) : UnitMass<Ounce> {
    override fun asType(d: Double) = Ounce(d)
    override fun asBaseUnit() = Pound(value / 16.0).asBaseUnit()
}

@JvmInline
value class Pound(override val value: Double) : UnitMass<Pound> {
    override fun asType(d: Double) = Pound(d)
    override fun asBaseUnit() = Gram(value * 28.35).asBaseUnit()
}

@JvmInline
value class ShortQuarter(override val value: Double) : UnitMass<ShortQuarter> {
    override fun asType(d: Double) = ShortQuarter(d)
    override fun asBaseUnit() = Pound(value * 25).asBaseUnit()
}

@JvmInline
value class LongQuarter(override val value: Double) : UnitMass<LongQuarter> {
    override fun asType(d: Double) = LongQuarter(d)
    override fun asBaseUnit() = Pound(value * 28).asBaseUnit()
}

@JvmInline
value class ShortHundredWeight(override val value: Double) : UnitMass<ShortHundredWeight> {
    override fun asType(d: Double) = ShortHundredWeight(d)
    override fun asBaseUnit() = Pound(value * 100).asBaseUnit()
}

@JvmInline
value class Stone(override val value: Double) : UnitMass<Stone> {
    override fun asType(d: Double) = Stone(d)
    override fun asBaseUnit() = Pound(value * 14).asBaseUnit()
}

@JvmInline
value class ShortTon(override val value: Double) : UnitMass<ShortTon> {
    override fun asType(d: Double) = ShortTon(d)
    override fun asBaseUnit() = Pound(value * 2000).asBaseUnit()
}
