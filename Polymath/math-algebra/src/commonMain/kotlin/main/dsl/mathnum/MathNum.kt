package main.dsl.mathnum

import main.dsl.expressions.ScalarAlgebra
import main.dsl.expressions.ScalarExpression
import main.dsl.mathnum.Scalar.*

fun Int.num() = mathNum(this)
fun Long.num() = mathNum(this)
fun Double.num() = mathNum(this)

fun mathNum(i: Long) = IntegerNum(i)
fun mathNum(i: Double) = RealNum(i)
fun mathNum(i: Int) = IntegerNum(i.toLong())

sealed interface MathNum : ScalarExpression {
    override val priority: ScalarAlgebra.Priority get() = ScalarAlgebra.Priority.NumVar
}


