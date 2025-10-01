package com.measures.weight

@JvmInline
value class KiloGram(override val value: Double) : UnitMass<KiloGram>, BaseUnit {
    override fun asType(d: Double) = KiloGram(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitMass<*>) = (this as UnitMass<*>).plusUnit(other)
    override operator fun minus(other: UnitMass<*>) = (this as UnitMass<*>).minusUnit(other)
    override operator fun times(other: UnitAcceleration<*>) = (this as UnitMass<*>).timesUnit(other)
}