import com.measures.distance.UnitDistance
import com.measures.international.distance.InternationalYard

fun UnitDistance<*>.toInternationalCable() = toUnit(InternationalCable(1.0))
fun UnitDistance<*>.toInternationalFathom() = toUnit(InternationalFathom(1.0))
fun UnitDistance<*>.toInternationalNauticalMile() = toUnit(InternationalNauticalMile(1.0))

@JvmInline
value class InternationalCable(override val value: Double) : UnitDistance<InternationalCable> {
    override fun asType(d: Double) = InternationalCable(d);
    override fun asBaseUnit() = InternationalFathom(value * 120.0).asBaseUnit()
}

@JvmInline
value class InternationalFathom(override val value: Double) : UnitDistance<InternationalFathom> {
    override fun asType(d: Double) = InternationalFathom(d);
    override fun asBaseUnit() = InternationalYard(value * 2.0).asBaseUnit()
}

@JvmInline
value class InternationalNauticalMile(override val value: Double) : UnitDistance<InternationalNauticalMile> {
    override fun asType(d: Double) = InternationalNauticalMile(d);
    override fun asBaseUnit() = InternationalCable(value * 8.439).asBaseUnit()
}


