package com.measures.distance

import kotlin.jvm.JvmInline

fun UnitDistance<*>.toImperialThou() = toUnit(ImperialThou(1.0))
fun UnitDistance<*>.toImperialBarleycorn() = toUnit(ImperialBarleycorn(1.0))
fun UnitDistance<*>.toImperialInch() = toUnit(ImperialInch(1.0))
fun UnitDistance<*>.toImperialHand() = toUnit(ImperialHand(1.0))
fun UnitDistance<*>.toImperialFoot() = toUnit(ImperialFoot(1.0))
fun UnitDistance<*>.toImperialYard() = toUnit(ImperialYard(1.0))
fun UnitDistance<*>.toImperialChain() = toUnit(ImperialChain(1.0))
fun UnitDistance<*>.toImperialFurlong() = toUnit(ImperialFurlong(1.0))
fun UnitDistance<*>.toImperialMile() = toUnit(ImperialMile(1.0))
fun UnitDistance<*>.toImperialLeague() = toUnit(ImperialLeague(1.0))

@JvmInline
value class ImperialThou(override val value: Double) : UnitDistance<ImperialThou> {
    override fun asType(d: Double) = ImperialThou(d)
    override fun asBaseUnit() = ImperialFoot(this.value / 12000.0).asBaseUnit()
}

@JvmInline
value class ImperialBarleycorn(override val value: Double) : UnitDistance<ImperialBarleycorn> {
    override fun asType(d: Double) = ImperialBarleycorn(d)
    override fun asBaseUnit() = ImperialFoot(this.value / 36.0).asBaseUnit()
}

@JvmInline
value class ImperialInch(override val value: Double) : UnitDistance<ImperialInch> {
    override fun asType(d: Double) = ImperialInch(d)
    override fun asBaseUnit() = ImperialFoot(this.value / 12.0).asBaseUnit()
}

@JvmInline
value class ImperialHand(override val value: Double) : UnitDistance<ImperialHand> {
    override fun asType(d: Double) = ImperialHand(d)
    override fun asBaseUnit() = ImperialFoot(this.value / 3.0).asBaseUnit()
}

@JvmInline
value class ImperialFoot(override val value: Double) : UnitDistance<ImperialFoot> {
    override fun asType(d: Double) = ImperialFoot(d)
    override fun asBaseUnit() = Meter(this.value * 0.3048).asBaseUnit()
}

@JvmInline
value class ImperialYard(override val value: Double) : UnitDistance<ImperialYard> {
    override fun asType(d: Double) = ImperialYard(d)
    override fun asBaseUnit() = ImperialFoot(this.value * 3.0).asBaseUnit()
}

@JvmInline
value class ImperialChain(override val value: Double) : UnitDistance<ImperialChain> {
    override fun asType(d: Double) = ImperialChain(d)
    override fun asBaseUnit() = ImperialFoot(this.value * 66.0).asBaseUnit()
}

@JvmInline
value class ImperialFurlong(override val value: Double) : UnitDistance<ImperialFurlong> {
    override fun asType(d: Double) = ImperialFurlong(d)
    override fun asBaseUnit() = ImperialFoot(this.value * 660.0).asBaseUnit()
}

@JvmInline
value class ImperialMile(override val value: Double) : UnitDistance<ImperialMile> {
    override fun asType(d: Double) = ImperialMile(d)
    override fun asBaseUnit() = ImperialFoot(this.value * 5280).asBaseUnit()
}

@JvmInline
value class ImperialLeague(override val value: Double) : UnitDistance<ImperialLeague> {
    override fun asType(d: Double) = ImperialLeague(d)
    override fun asBaseUnit() = ImperialFoot(this.value * 15840).asBaseUnit()
}

