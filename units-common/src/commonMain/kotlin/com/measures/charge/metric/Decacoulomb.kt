package com.measures.charge.metric

import com.measures.Consts
import com.measures.charge.UnitCharge
import com.measures.charge.Coulomb
import com.measures.current.Ampere
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Decacoulomb(override val value: Double) : UnitCharge<Decacoulomb> {
    override fun asType(d: Double) = Decacoulomb(d)
    override fun asBaseUnit() = Coulomb(value * Consts.DEKA)

    override operator fun plus(other: UnitCharge<*>) = UnitCharge.plusUnit(this, other)
    override operator fun minus(other: UnitCharge<*>) = UnitCharge.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>): Ampere = Ampere(this.value / other.asBaseUnit().value)
}

fun UnitCharge<*>.toDecacoulomb() = toUnit(Decacoulomb(1.0))
