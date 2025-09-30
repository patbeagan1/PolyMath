package com.measures.weight

import kotlin.jvm.JvmInline

// Troy Weight Units
fun UnitMass<*>.toTroyPound() = toUnit(TroyPound(1.0))
fun UnitMass<*>.toTroyOunce() = toUnit(TroyOunce(1.0))
fun UnitMass<*>.toTroyPennyweight() = toUnit(TroyPennyweight(1.0))
fun UnitMass<*>.toTroyGrain() = toUnit(TroyGrain(1.0))

@JvmInline
value class TroyGrain(override val value: Double) : UnitMass<TroyGrain> {
    override fun asType(d: Double) = TroyGrain(d)
    override fun asBaseUnit() = TroyPennyweight(value / 24).asBaseUnit()
}

@JvmInline
value class TroyPennyweight(override val value: Double) : UnitMass<TroyPennyweight> {
    override fun asType(d: Double) = TroyPennyweight(d)
    override fun asBaseUnit() = TroyOunce(value / 20).asBaseUnit()
}

@JvmInline
value class TroyOunce(override val value: Double) : UnitMass<TroyOunce> {
    override fun asType(d: Double) = TroyOunce(d)
    override fun asBaseUnit() = Gram(value * 31.1034768).asBaseUnit()
}

@JvmInline
value class TroyPound(override val value: Double) : UnitMass<TroyPound> {
    override fun asType(d: Double) = TroyPound(d)
    override fun asBaseUnit() = TroyOunce(value * 12.0).asBaseUnit()
}
