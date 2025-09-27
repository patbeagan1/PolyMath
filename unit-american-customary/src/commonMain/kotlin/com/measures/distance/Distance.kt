package com.measures.distance

import kotlin.jvm.JvmInline

fun UnitDistance<*>.toSurveyChain() = toUnit(SurveyChain(1.0))
fun UnitDistance<*>.toSurveyFoot() = toUnit(SurveyFoot(1.0))
fun UnitDistance<*>.toSurveyFurlong() = toUnit(SurveyFurlong(1.0))
fun UnitDistance<*>.toSurveyLeague() = toUnit(SurveyLeague(1.0))
fun UnitDistance<*>.toLink() = toUnit(SurveyLink(1.0))
fun UnitDistance<*>.toSurveyMile() = toUnit(SurveyMile(1.0))
fun UnitDistance<*>.toSurveyRod() = toUnit(SurveyRod(1.0))

@JvmInline
value class SurveyChain(override val value: Double) : UnitDistance<SurveyChain> {
    override fun asType(d: Double) = SurveyChain(d);
    override fun asBaseUnit() = SurveyRod(4.0 * value).asBaseUnit()
}

@JvmInline
value class SurveyFoot(override val value: Double) : UnitDistance<SurveyFoot> {
    override fun asType(d: Double) = SurveyFoot(d);
    override fun asBaseUnit() = Meter(value * (1200.0 / 3937.0))
}

@JvmInline
value class SurveyFurlong(override val value: Double) : UnitDistance<SurveyFurlong> {
    override fun asType(d: Double) = SurveyFurlong(d);
    override fun asBaseUnit() = SurveyChain(value * 10.0).asBaseUnit()
}

@JvmInline
value class SurveyLeague(override val value: Double) : UnitDistance<SurveyLeague> {
    override fun asType(d: Double) = SurveyLeague(d);
    override fun asBaseUnit() = SurveyMile(value * 3.0).asBaseUnit()
}

@JvmInline
value class SurveyLink(override val value: Double) : UnitDistance<SurveyLink> {
    override fun asType(d: Double) = SurveyLink(d);
    override fun asBaseUnit() = SurveyFoot(value * (33.0 / 50.0)).asBaseUnit()
}

@JvmInline
value class SurveyMile(override val value: Double) : UnitDistance<SurveyMile> {
    override fun asType(d: Double) = SurveyMile(d);
    override fun asBaseUnit() = SurveyFurlong(value * 8.0).asBaseUnit()
}

@JvmInline
value class SurveyRod(override val value: Double) : UnitDistance<SurveyRod> {
    override fun asType(d: Double) = SurveyRod(d);
    override fun asBaseUnit() = SurveyLink(value * 25.0).asBaseUnit()
}


