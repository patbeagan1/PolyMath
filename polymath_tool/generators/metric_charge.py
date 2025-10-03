#!/usr/bin/env python3
"""
Charge unit generation (Coulomb).
"""

from .common import get_measures_base


def generate_charge() -> int:
    """Generate charge units (Coulomb)."""
    base_dir = get_measures_base() / "charge" / "metric"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        # Complete metric charge units using Consts values for full SI greek set from Base.kt
        ("Yottacoulomb", "Coulomb(value * Consts.YOTTA)"),
        ("Zettacoulomb", "Coulomb(value * Consts.ZETTA)"),
        ("Exacoulomb", "Coulomb(value * Consts.EXA)"),
        ("Petacoulomb", "Coulomb(value * Consts.PETA)"),
        ("Teracoulomb", "Coulomb(value * Consts.TERA)"),
        ("Gigacoulomb", "Coulomb(value * Consts.GIGA)"),
        ("Megacoulomb", "Coulomb(value * Consts.MEGA)"),
        ("Kilocoulomb", "Coulomb(value * Consts.KILO)"),
        ("Hectocoulomb", "Coulomb(value * Consts.HECTO)"),
        ("Decacoulomb", "Coulomb(value * Consts.DEKA)"),
        ("Decicoulomb", "Coulomb(value * Consts.DECI)"),
        ("Centicoulomb", "Coulomb(value * Consts.CENTI)"),
        ("Millicoulomb", "Coulomb(value * Consts.MILLI)"),
        ("Microcoulomb", "Coulomb(value * Consts.MICRO)"),
        ("Nanocoulomb", "Coulomb(value * Consts.NANO)"),
        ("Picocoulomb", "Coulomb(value * Consts.PICO)"),
        ("Femtocoulomb", "Coulomb(value * Consts.FEMTO)"),
        ("Attocoulomb", "Coulomb(value * Consts.ATTO)"),
        ("Zeptocoulomb", "Coulomb(value * Consts.ZEPTO)"),
        ("Yoctocoulomb", "Coulomb(value * Consts.YOCTO)"),
    ]
    
    template = """package com.measures.charge.metric

import com.measures.Consts
import com.measures.charge.UnitCharge
import com.measures.charge.Coulomb
import com.measures.current.Ampere
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitCharge<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = {base_conversion}

    override operator fun plus(other: UnitCharge<*>) = UnitCharge.plusUnit(this, other)
    override operator fun minus(other: UnitCharge<*>) = UnitCharge.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>): Ampere = Ampere(this.value / other.asBaseUnit().value)
}}

fun UnitCharge<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, base_conversion in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, base_conversion=base_conversion)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0
