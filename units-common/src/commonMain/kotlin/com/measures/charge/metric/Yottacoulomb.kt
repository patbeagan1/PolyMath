package com.measures.charge.metric

import com.measures.Consts
import com.measures.charge.UnitCharge
import com.measures.charge.Coulomb
import com.measures.current.Ampere
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Yottacoulomb(override val value: Double) : UnitCharge<Yottacoulomb> {
    override fun asType(d: Double) = Yottacoulomb(d)
    override fun asBaseUnit() = Coulomb(value * Consts.YOTTA)

    override operator fun plus(other: UnitCharge<*>) = UnitCharge.plusUnit(this, other)
    override operator fun minus(other: UnitCharge<*>) = UnitCharge.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>): Ampere = Ampere(this.value / other.asBaseUnit().value)
}

fun UnitCharge<*>.toYottacoulomb() = toUnit(Yottacoulomb(1.0))
