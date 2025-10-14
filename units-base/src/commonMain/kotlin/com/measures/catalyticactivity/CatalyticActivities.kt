package com.measures.catalyticactivity

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitCatalyticActivity<T : DoubleBase> : UnitType<T, Katal> {
    operator fun plus(other: UnitCatalyticActivity<*>): Katal
    operator fun minus(other: UnitCatalyticActivity<*>): Katal

    companion object {
        fun plusUnit(catalyticActivity: UnitCatalyticActivity<*>, other: UnitCatalyticActivity<*>): Katal =
            Katal(catalyticActivity.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(catalyticActivity: UnitCatalyticActivity<*>, other: UnitCatalyticActivity<*>): Katal =
            Katal(catalyticActivity.asBaseUnit().value - other.asBaseUnit().value)
    }
}

