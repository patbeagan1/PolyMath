package main.dsl.mathnum

import main.dsl.CanDisplay

data class Constant(
    override val glyph: String,
    val value: Scalar,
    override val name: String? = null
) : Scalar, CanDisplay {
    constructor(glyph: String, value: Long, longName: String? = null) : this(glyph, mathNum(value), longName)
    constructor(glyph: String, value: Double, longName: String? = null) : this(glyph, mathNum(value), longName)
    constructor(glyph: String, value: Int, longName: String? = null) : this(glyph, mathNum(value), longName)

    override fun toLatex() = glyph

    override fun evaluate(): Double = value.evaluate()

    override fun display() = buildString {
        if (value != Scalar.Undefined) {
            append(glyph)
            append(" = ")
            append(value.toString())
            if (name != null) {
                append(" ($name)")
            }
        } else {
            append(glyph)
            append(" is undefined")
        }
    }
}