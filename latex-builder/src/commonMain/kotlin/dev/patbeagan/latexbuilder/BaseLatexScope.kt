package dev.patbeagan.latexbuilder

@TeXDocumentMarker
open class BaseLatexScope(
    private val content: StringBuilder = StringBuilder()
) : Appendable by content {
    fun add(text: String) = apply { append(text) }
    override fun toString(): String = content.toString()

    fun today() = """\today"""
}

private fun <OuterScope : BaseLatexScope, InnerScope : BaseLatexScope> OuterScope.latexScope(
    prefix: String,
    postfix: String,
    body: InnerScope
) = apply {
    append(prefix)
    append(body.toString())
    append(postfix)
}

fun <OuterScope : BaseLatexScope, InnerScope : BaseLatexScope> OuterScope.command(
    keyword: String,
    option: InnerScope
): OuterScope = latexScope(
    prefix = "\\$keyword{",
    postfix = "}",
    option
)

fun <OuterScope : BaseLatexScope, InnerScope : BaseLatexScope> OuterScope.beginEndScope(
    scopeName: String,
    innerScope: InnerScope
): OuterScope = latexScope(
    "\n\\begin{$scopeName}",
    "\\end{$scopeName}\n",
    innerScope
)

fun <OuterScope : BaseLatexScope, InnerScope : BaseLatexScope> OuterScope.startStopScope(
    beginScopeName: String,
    endScopeName: String,
    innerScope: InnerScope
): OuterScope = latexScope(
    beginScopeName,
    endScopeName,
    innerScope
)
