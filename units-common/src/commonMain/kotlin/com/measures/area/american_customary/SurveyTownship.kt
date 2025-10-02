package com.measures.area.american_customary

import com.measures.area.UnitArea
import com.measures.area.american_customary.SurveySection
import com.measures.distance.UnitDistance
import kotlin.jvm.JvmInline

@JvmInline
value class SurveyTownship(override val value: Double) : UnitArea<SurveyTownship> {
    override fun asType(d: Double) = SurveyTownship(d)
    override fun asBaseUnit() = SurveySection(value * 36.0).asBaseUnit()

    override operator fun plus(other: UnitArea<*>) = UnitArea.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitArea<*>) = UnitArea.Companion.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitArea.Companion.timesUnit(this, other)
    override operator fun div(other: UnitDistance<*>) = UnitArea.Companion.divUnit(this, other)
}

fun UnitArea<*>.toSurveyTownship() = toUnit(SurveyTownship(1.0))
