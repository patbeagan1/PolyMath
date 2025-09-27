package com.measures.weight

import kotlin.jvm.JvmInline

fun UnitWeight<*>.toTroyPound() = toUnit(TroyPound(1.0))
fun UnitWeight<*>.toTroyOunce() = toUnit(TroyOunce(1.0))
fun UnitWeight<*>.toTroyPennyweight() = toUnit(TroyPennyweight(1.0))
fun UnitWeight<*>.toTroyGrain() = toUnit(TroyGrain(1.0))

@JvmInline
value class TroyGrain(override val value: Double) : UnitWeight<TroyGrain> {
    override fun asType(d: Double) = TroyGrain(d)
    override fun asBaseUnit() = TroyPennyweight(value / 24).asBaseUnit()
}

@JvmInline
value class TroyPennyweight(override val value: Double) : UnitWeight<TroyPennyweight> {
    override fun asType(d: Double) = TroyPennyweight(d)
    override fun asBaseUnit() = TroyOunce(value / 20).asBaseUnit()
}

@JvmInline
value class TroyOunce(override val value: Double) : UnitWeight<TroyOunce> {
    override fun asType(d: Double) = TroyOunce(d)
    override fun asBaseUnit() = Gram(value * 31.1034768)
}

@JvmInline
value class TroyPound(override val value: Double) : UnitWeight<TroyPound> {
    override fun asType(d: Double) = TroyPound(d)
    override fun asBaseUnit() = TroyOunce(value * 12.0).asBaseUnit()
}
