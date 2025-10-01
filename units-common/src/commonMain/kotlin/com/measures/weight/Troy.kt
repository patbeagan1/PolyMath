package com.measures.weight

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
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

    override fun plus(other: UnitMass<*>): KiloGram = (this as UnitMass<*>).plusUnit(other)
    override fun minus(other: UnitMass<*>): KiloGram = (this as UnitMass<*>).minusUnit(other)
    override fun times(other: UnitAcceleration<*>): Newton = (this as UnitMass<*>).timesUnit(other)
}

@JvmInline
value class TroyPennyweight(override val value: Double) : UnitMass<TroyPennyweight> {
    override fun asType(d: Double) = TroyPennyweight(d)
    override fun asBaseUnit() = TroyOunce(value / 20).asBaseUnit()

    override fun plus(other: UnitMass<*>): KiloGram = (this as UnitMass<*>).plusUnit(other)
    override fun minus(other: UnitMass<*>): KiloGram = (this as UnitMass<*>).minusUnit(other)
    override fun times(other: UnitAcceleration<*>): Newton = (this as UnitMass<*>).timesUnit(other)
}

@JvmInline
value class TroyOunce(override val value: Double) : UnitMass<TroyOunce> {
    override fun asType(d: Double) = TroyOunce(d)
    override fun asBaseUnit() = Gram(value * 31.1034768).asBaseUnit()

    override fun plus(other: UnitMass<*>): KiloGram = (this as UnitMass<*>).plusUnit(other)
    override fun minus(other: UnitMass<*>): KiloGram = (this as UnitMass<*>).minusUnit(other)
    override fun times(other: UnitAcceleration<*>): Newton = (this as UnitMass<*>).timesUnit(other)
}

@JvmInline
value class TroyPound(override val value: Double) : UnitMass<TroyPound> {
    override fun asType(d: Double) = TroyPound(d)
    override fun asBaseUnit() = TroyOunce(value * 12.0).asBaseUnit()

    override fun plus(other: UnitMass<*>): KiloGram = (this as UnitMass<*>).plusUnit(other)
    override fun minus(other: UnitMass<*>): KiloGram = (this as UnitMass<*>).minusUnit(other)
    override fun times(other: UnitAcceleration<*>): Newton = (this as UnitMass<*>).timesUnit(other)
}
