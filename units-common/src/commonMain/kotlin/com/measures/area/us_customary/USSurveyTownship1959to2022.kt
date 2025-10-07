package com.measures.area.us_customary

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import kotlin.jvm.JvmInline

@JvmInline
value class USSurveyTownship1959to2022(override val value: Double) : UnitArea<USSurveyTownship1959to2022> {
    override fun asType(d: Double) = USSurveyTownship1959to2022(d)
    override fun asBaseUnit() = USSquareSurveyFoot1959to2022(value * 1003622400).asBaseUnit()

    override fun plus(other: UnitArea<*>): SquareMeter = UnitArea.plusUnit(this, other)
    override fun minus(other: UnitArea<*>): SquareMeter = UnitArea.minusUnit(this, other)
    override fun times(other: UnitDistance<*>): Liter = UnitArea.timesUnit(this, other)
    override fun div(other: UnitDistance<*>): Meter = UnitArea.divUnit(this, other)
}

fun UnitArea<*>.toUSSurveyTownship1959to2022() = toUnit(USSurveyTownship1959to2022(1.0))
