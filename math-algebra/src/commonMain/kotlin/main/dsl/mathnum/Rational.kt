package main.dsl.mathnum

import main.dsl.mathnum.Scalar.RationalNum
import kotlin.math.abs
import kotlin.math.floor

fun Double.toRational(error: Double = 1.0E-6): RationalNum {
    var numeratorCurrent = 1L
    var numeratorPrevious = 0L
    var denominatorCurrent = 0L
    var denominatorPrevious = 1L
    val value = this
    var b = value

    do {
        val floorValue = floor(b)

        numeratorCurrent = (floorValue * numeratorCurrent + numeratorPrevious)
            .toLong()
            .also { numeratorPrevious = numeratorCurrent }

        denominatorCurrent = (floorValue * denominatorCurrent + denominatorPrevious)
            .toLong()
            .also { denominatorPrevious = denominatorCurrent }

        b = 1 / (b - floorValue)
    } while (abs(value - numeratorCurrent.toDouble() / denominatorCurrent) > value * error)

    return RationalNum(numeratorCurrent, denominatorCurrent)
}
