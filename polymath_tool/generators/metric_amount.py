#!/usr/bin/env python3
"""
Metric amount unit generation.
"""

from .common import get_measures_base


def generate_metric_amount() -> int:
    """Generate metric amount units (mole)."""
    base_dir = get_measures_base() / "amount" / "metric"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("Mole", "Mole(value * 1.0)"),  # Base unit
        ("Millimole", "Mole(value * 0.001)"),
        ("Micromole", "Mole(value * 0.000001)"),
        ("Nanomole", "Mole(value * 0.000000001)"),
        ("Picomole", "Mole(value * 0.000000000001)"),
        ("Kilomole", "Mole(value * 1000)"),
    ]
    
    template = """package com.measures.amount.metric

import com.measures.amount.UnitAmount
import com.measures.amount.Mole
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitAmount<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = {base_conversion}
}}

fun UnitAmount<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, base_conversion in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, base_conversion=base_conversion)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0
