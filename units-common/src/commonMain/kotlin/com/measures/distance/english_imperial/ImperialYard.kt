package com.measures.distance.english_imperial

import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.distance.english_imperial.ImperialFoot
import com.measures.distance.Meter
import com.measures.time.UnitTime
import com.measures.velocity.MetersPerSecond
import kotlin.jvm.JvmInline

@JvmInline
value class ImperialYard(override val value: Double) : UnitDistance<ImperialYard> {
    override fun asType(d: Double) = ImperialYard(d)
    override fun asBaseUnit() = ImperialFoot(this.value * 3.0).asBaseUnit()

    override operator fun plus(other: UnitDistance<*>) = UnitDistance.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitDistance<*>) = UnitDistance.Companion.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitDistance.Companion.timesUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitDistance.Companion.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond = UnitDistance.Companion.divUnit(this, other)
}

fun UnitDistance<*>.toImperialYard() = toUnit(ImperialYard(1.0))
