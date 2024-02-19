package main.formulas

import main.dsl.mathnum.Constant

interface PhysicsConstants {
    val symSpeedOfLight get() = Constant("c", 299_792_458.0)
}