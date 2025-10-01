package com.measures.area

import com.measures.distance.UnitDistance
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

// American Customary Area Units
fun UnitArea<*>.toSquareSurveyFoot() = toUnit(SquareSurveyFoot(1.0))
fun UnitArea<*>.toSquareSurveyChain() = toUnit(SquareSurveyChain(1.0))
fun UnitArea<*>.toSurveyAcre() = toUnit(SurveyAcre(1.0))
fun UnitArea<*>.toSurveySection() = toUnit(SurveySection(1.0))
fun UnitArea<*>.toSurveyTownship() = toUnit(SurveyTownship(1.0))

@JvmInline
value class SquareSurveyFoot(override val value: Double) : UnitArea<SquareSurveyFoot> {
    override fun asType(d: Double) = SquareSurveyFoot(d)
    override fun asBaseUnit() = SquareMeter(value * 0.09290341)

    override operator fun plus(other: UnitArea<*>) = (this as UnitArea<*>).plusUnit(other)
    override operator fun minus(other: UnitArea<*>) = (this as UnitArea<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitArea<*>).timesUnit(other)
    override operator fun div(other: UnitDistance<*>) = (this as UnitArea<*>).divUnit(other)
}

@JvmInline
value class SquareSurveyChain(override val value: Double) : UnitArea<SquareSurveyChain> {
    override fun asType(d: Double) = SquareSurveyChain(d)
    override fun asBaseUnit() = SquareSurveyFoot(value * 4356.0).asBaseUnit()

    override operator fun plus(other: UnitArea<*>) = (this as UnitArea<*>).plusUnit(other)
    override operator fun minus(other: UnitArea<*>) = (this as UnitArea<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitArea<*>).timesUnit(other)
    override operator fun div(other: UnitDistance<*>) = (this as UnitArea<*>).divUnit(other)
}

@JvmInline
value class SurveyAcre(override val value: Double) : UnitArea<SurveyAcre> {
    override fun asType(d: Double) = SurveyAcre(d)
    override fun asBaseUnit() = SquareSurveyChain(value * 10.0).asBaseUnit()

    override operator fun plus(other: UnitArea<*>) = (this as UnitArea<*>).plusUnit(other)
    override operator fun minus(other: UnitArea<*>) = (this as UnitArea<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitArea<*>).timesUnit(other)
    override operator fun div(other: UnitDistance<*>) = (this as UnitArea<*>).divUnit(other)
}

@JvmInline
value class SurveySection(override val value: Double) : UnitArea<SurveySection> {
    override fun asType(d: Double): SurveySection = SurveySection(d)
    override fun asBaseUnit() = SurveyAcre(value * 640).asBaseUnit()

    override operator fun plus(other: UnitArea<*>) = (this as UnitArea<*>).plusUnit(other)
    override operator fun minus(other: UnitArea<*>) = (this as UnitArea<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitArea<*>).timesUnit(other)
    override operator fun div(other: UnitDistance<*>) = (this as UnitArea<*>).divUnit(other)
}

@JvmInline
value class SurveyTownship(override val value: Double) : UnitArea<SurveyTownship> {
    override fun asType(d: Double) = SurveyTownship(d)
    override fun asBaseUnit() = SurveySection(value * 36.0).asBaseUnit()

    override operator fun plus(other: UnitArea<*>) = (this as UnitArea<*>).plusUnit(other)
    override operator fun minus(other: UnitArea<*>) = (this as UnitArea<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitArea<*>).timesUnit(other)
    override operator fun div(other: UnitDistance<*>) = (this as UnitArea<*>).divUnit(other)
}
