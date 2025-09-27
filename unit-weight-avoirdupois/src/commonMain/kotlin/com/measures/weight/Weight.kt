package com.measures.weight

import kotlin.jvm.JvmInline

fun UnitWeight<*>.toDram() = toUnit(Dram(1.0))
fun UnitWeight<*>.toGrain() = toUnit(Grain(1.0))
fun UnitWeight<*>.toLongHundredWeight() = toUnit(LongHundredWeight(1.0))
fun UnitWeight<*>.toLongTon() = toUnit(LongTon(1.0))
fun UnitWeight<*>.toOunce() = toUnit(Ounce(1.0))
fun UnitWeight<*>.toPound() = toUnit(Pound(1.0))
fun UnitWeight<*>.toShortQuarter() = toUnit(ShortQuarter(1.0))
fun UnitWeight<*>.toLongQuarter() = toUnit(LongQuarter(1.0))
fun UnitWeight<*>.toShortHundredWeight() = toUnit(ShortHundredWeight(1.0))
fun UnitWeight<*>.toStone() = toUnit(Stone(1.0))
fun UnitWeight<*>.toShortTon() = toUnit(ShortTon(1.0))

@JvmInline
value class Dram(override val value: Double) : UnitWeight<Dram> {
    override fun asType(d: Double) = Dram(d)
    override fun asBaseUnit() = Pound(value / 256).asBaseUnit()
}

@JvmInline
value class Grain(override val value: Double) : UnitWeight<Grain> {
    override fun asType(d: Double) = Grain(d)
    override fun asBaseUnit() = Pound(value / 7000).asBaseUnit()
}

@JvmInline
value class LongHundredWeight(override val value: Double) : UnitWeight<LongHundredWeight> {
    override fun asType(d: Double) = LongHundredWeight(d)
    override fun asBaseUnit() = Pound(value * 112).asBaseUnit()
}

@JvmInline
value class LongTon(override val value: Double) : UnitWeight<LongTon> {
    override fun asType(d: Double) = LongTon(d)
    override fun asBaseUnit() = Pound(value * 2240).asBaseUnit()
}

@JvmInline
value class Ounce(override val value: Double) : UnitWeight<Ounce> {
    override fun asType(d: Double) = Ounce(d)
    override fun asBaseUnit() = Pound(value / 16.0).asBaseUnit()
}

@JvmInline
value class Pound(override val value: Double) : UnitWeight<Pound> {
    override fun asType(d: Double) = Pound(d)
    override fun asBaseUnit() = Gram(value * 28.35)
}

@JvmInline
value class ShortQuarter(override val value: Double) : UnitWeight<ShortQuarter> {
    override fun asType(d: Double) = ShortQuarter(d)
    override fun asBaseUnit() = Pound(value * 25).asBaseUnit()
}

@JvmInline
value class LongQuarter(override val value: Double) : UnitWeight<LongQuarter> {
    override fun asType(d: Double) = LongQuarter(d)
    override fun asBaseUnit() = Pound(value * 28).asBaseUnit()
}

@JvmInline
value class ShortHundredWeight(override val value: Double) : UnitWeight<ShortHundredWeight> {
    override fun asType(d: Double) = ShortHundredWeight(d)
    override fun asBaseUnit() = Pound(value * 100).asBaseUnit()
}

@JvmInline
value class Stone(override val value: Double) : UnitWeight<Stone> {
    override fun asType(d: Double) = Stone(d)
    override fun asBaseUnit() = Pound(value * 14).asBaseUnit()
}

@JvmInline
value class ShortTon(override val value: Double) : UnitWeight<ShortTon> {
    override fun asType(d: Double) = ShortTon(d)
    override fun asBaseUnit() = Pound(value * 2000).asBaseUnit()
}

