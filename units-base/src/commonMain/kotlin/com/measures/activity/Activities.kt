package com.measures.activity

import com.measures.DoubleBase
import com.measures.UnitType

interface UnitActivity<T : DoubleBase> : UnitType<T, Becquerel> {
    operator fun plus(other: UnitActivity<*>): Becquerel
    operator fun minus(other: UnitActivity<*>): Becquerel

    companion object {
        fun plusUnit(activity: UnitActivity<*>, other: UnitActivity<*>): Becquerel =
            Becquerel(activity.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(activity: UnitActivity<*>, other: UnitActivity<*>): Becquerel =
            Becquerel(activity.asBaseUnit().value - other.asBaseUnit().value)
    }
}

