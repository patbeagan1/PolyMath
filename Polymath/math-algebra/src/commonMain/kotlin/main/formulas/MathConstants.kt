package main.formulas

import main.dsl.mathnum.Constant
import kotlin.math.E
import kotlin.math.PI

interface MathConstants {
    val constPi get() = Constant("\\pi", PI)
    val constE get() = Constant("\\e", E)
}