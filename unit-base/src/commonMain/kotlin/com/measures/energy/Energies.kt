package com.measures.energy

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitTypedFull
import com.measures.weight.UnitWeight
import com.measures.distance.UnitDistance
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

typealias UnitEnergy<T> = UnitEnergyTypedFull<T>

interface UnitEnergyTypedFull<T : DoubleBase> : UnitTypedFull<T, Joule> {
    operator fun plus(other: UnitEnergyTypedFull<*>) =
        Joule(this.asBaseUnit().value + other.asBaseUnit().value)

    operator fun minus(other: UnitEnergyTypedFull<*>) =
        Joule(this.asBaseUnit().value - other.asBaseUnit().value)
}

@JvmInline
value class Joule(override val value: Double) : UnitEnergy<Joule>, BaseUnit {
    override fun asType(d: Double) = Joule(d)
    override fun asBaseUnit() = this
    
    companion object {
        fun from(mass: UnitWeight<*>, distance: UnitDistance<*>, time: UnitTime<*>): Joule {
            val massBase = mass.asBaseUnit()
            val distanceBase = distance.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Joule(massBase.value * distanceBase.value * distanceBase.value / (timeBase.value * timeBase.value))
        }
    }
}

@JvmInline
value class Erg(override val value: Double) : UnitEnergy<Erg> {
    override fun asType(d: Double) = Erg(d)
    override fun asBaseUnit() = Joule(this.value * 1E-7)
}

@JvmInline
value class Calorie(override val value: Double) : UnitEnergy<Calorie> {
    override fun asType(d: Double) = Calorie(d)
    override fun asBaseUnit() = Joule(this.value * 4.184)
}

@JvmInline
value class Kilocalorie(override val value: Double) : UnitEnergy<Kilocalorie> {
    override fun asType(d: Double) = Kilocalorie(d)
    override fun asBaseUnit() = Joule(this.value * 4184.0)
}

@JvmInline
value class BritishThermalUnit(override val value: Double) : UnitEnergy<BritishThermalUnit> {
    override fun asType(d: Double) = BritishThermalUnit(d)
    override fun asBaseUnit() = Joule(this.value * 1055.056)
}

@JvmInline
value class KilowattHour(override val value: Double) : UnitEnergy<KilowattHour> {
    override fun asType(d: Double) = KilowattHour(d)
    override fun asBaseUnit() = Joule(this.value * 3.6E6)
}

@JvmInline
value class ElectronVolt(override val value: Double) : UnitEnergy<ElectronVolt> {
    override fun asType(d: Double) = ElectronVolt(d)
    override fun asBaseUnit() = Joule(this.value * 1.602176634E-19)
}

@JvmInline
value class Kilojoule(override val value: Double) : UnitEnergy<Kilojoule> {
    override fun asType(d: Double) = Kilojoule(d)
    override fun asBaseUnit() = Joule(this.value * 1000.0)
}

@JvmInline
value class Megajoule(override val value: Double) : UnitEnergy<Megajoule> {
    override fun asType(d: Double) = Megajoule(d)
    override fun asBaseUnit() = Joule(this.value * 1E6)
}

// Conversion functions using toUnit
fun UnitEnergy<*>.toJoule() = this.asBaseUnit()
fun UnitEnergy<*>.toErg() = toUnit(Erg(1.0))
fun UnitEnergy<*>.toCalorie() = toUnit(Calorie(1.0))
fun UnitEnergy<*>.toKilocalorie() = toUnit(Kilocalorie(1.0))
fun UnitEnergy<*>.toBritishThermalUnit() = toUnit(BritishThermalUnit(1.0))
fun UnitEnergy<*>.toKilowattHour() = toUnit(KilowattHour(1.0))
fun UnitEnergy<*>.toElectronVolt() = toUnit(ElectronVolt(1.0))
fun UnitEnergy<*>.toKilojoule() = toUnit(Kilojoule(1.0))
fun UnitEnergy<*>.toMegajoule() = toUnit(Megajoule(1.0))

