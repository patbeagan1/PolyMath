package dev.patbeagan.latexbuilder

class AMSMathScope(config: AMSMathScope.() -> Unit) : BaseLatexScope() {
    init {
        config()
    }

    fun align(vararg lines: String) = apply {
        append("\n\\begin{align}\n")
        lines.forEach { line ->
            append("$line \\\\\n")
        }
        append("\\end{align}\n")
    }

    fun align(vararg equations: MathScope.() -> Unit) = apply {
        append("\n\\begin{align}")
        equations.forEach { eq ->
            append(MathScope(eq).toString())
            append("""\\""")
        }
        append("""\end{align}""")
    }
}