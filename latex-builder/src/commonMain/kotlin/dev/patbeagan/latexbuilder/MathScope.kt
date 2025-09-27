package dev.patbeagan.latexbuilder

open class MathScope(
    config: MathScope.() -> Unit
) : BaseLatexScope() {

    init {
        config()
    }

    fun literal(sym: String): MathScope = apply {
        append(sym)
    }

    fun pow(exp: MathScope.() -> Unit, base: MathScope.() -> Unit) {
        append(MathScope(base).toString() + "^" + MathScope(exp).toString())
    }

    fun fraction(numerator: MathScope.() -> Unit, denominator: MathScope.() -> Unit): MathScope {
        append("\\frac{")
        append(MathScope(numerator).toString())
        append("}{")
        append(MathScope(denominator).toString())
        append("}")
        return this
    }

    fun fraction(numerator: String, denominator: String): MathScope {
        append("""\frac{""")
        append(numerator)
        append("}{")
        append(denominator)
        append("}")
        return this
    }

    fun suchThat(conditions: MathScope.() -> Unit, body: MathScope.() -> Unit): MathScope = apply {
        append("\\{")
        append(MathScope(body).toString())
        append("|")
        append(MathScope(conditions).toString())
        append("\\}")
    }

    fun sqrt(body: MathScope.() -> Unit): MathScope = command("sqrt", MathScope(body))
    fun vector(body: MathScope.() -> Unit): MathScope = command("vec", MathScope(body))

    fun integral(from: String, to: String, body: MathScope.() -> Unit): MathScope = startStopScope(
        """\int_{$from}^{$to} """,
        """\, dx""",
        MathScope(body)
    )

    fun brackets(body: MathScope.() -> Unit): MathScope = startStopScope("\\left[", "\\right]", MathScope(body))
    fun braces(body: MathScope.() -> Unit): MathScope = startStopScope("\\left{", "\\right}", MathScope(body))
    fun parentheses(body: MathScope.() -> Unit): MathScope = startStopScope("\\left(", "\\right)", MathScope(body))

    fun summation(from: String, to: String, body: MathScope.() -> Unit): MathScope {
        append("""\sum_{$from}^{$to} """)
        append(MathScope(body).toString())
        return this
    }

    fun matrix(rows: List<MathScope.() -> Unit>): MathScope {
        append("\n\\begin{bmatrix}")
        rows.forEach { row ->
            append(MathScope(row).toString())
            append("""\\""")
        }
        append("""\end{bmatrix}""")
        return this
    }
}