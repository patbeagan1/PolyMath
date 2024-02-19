package main.dsl.mathnum

import main.dsl.CanDisplay
import main.dsl.expressions.ScalarExpression

data class Variable(
    override val glyph: String,
    var value: Scalar = Scalar.Undefined,
    override val name: String? = null,
    var instance: String? = null,
    private val isBound: Boolean = false // determines whether it shows up in fn signature
) : Scalar, CanDisplay {
    constructor(glyph: String, value: Long, longName: String? = null) : this(glyph, mathNum(value), longName)
    constructor(glyph: String, value: Double, longName: String? = null) : this(glyph, mathNum(value), longName)
    constructor(glyph: String, value: Int, longName: String? = null) : this(glyph, mathNum(value), longName)

    fun setTo(value: Double) = apply { this.value = mathNum(value) }
    fun setTo(value: Int) = apply { this.value = mathNum(value) }
    fun setTo(value: Long) = apply { this.value = mathNum(value) }
    fun withInstance(value: String) = apply { this.instance = value }
    fun standsFor(expression: ScalarExpression) = setTo(expression.evaluate()) isEqualTo expression

    override fun toLatex() = if (instance == null) glyph else "${glyph}_${instance}"
    override fun evaluate(): Double = value.evaluate()

    override fun display() = buildString {
        if (value != Scalar.Undefined) {
            append(toLatex())
            append(" = ")
            append(value.toString())
            if (name != null) {
                append(" ($name)")
            }
        } else {
            append(toLatex())
            append(" is undefined")
        }
    }
}