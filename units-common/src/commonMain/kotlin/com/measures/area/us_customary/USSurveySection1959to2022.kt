package com.measures.area.us_customary

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import kotlin.jvm.JvmInline

@JvmInline
value class USSurveySection1959to2022(override val value: Double) : UnitArea<USSurveySection1959to2022> {
    override fun asType(d: Double) = USSurveySection1959to2022(d)
    override fun asBaseUnit() = USSquareSurveyFoot1959to2022(value * 27878400).asBaseUnit()

    override fun plus(other: UnitArea<*>): SquareMeter = UnitArea.plusUnit(this, other)
    override fun minus(other: UnitArea<*>): SquareMeter = UnitArea.minusUnit(this, other)
    override fun times(other: UnitDistance<*>): Liter = UnitArea.timesUnit(this, other)
    override fun div(other: UnitDistance<*>): Meter = UnitArea.divUnit(this, other)
}

fun UnitArea<*>.toUSSurveySection1959to2022() = toUnit(USSurveySection1959to2022(1.0))
