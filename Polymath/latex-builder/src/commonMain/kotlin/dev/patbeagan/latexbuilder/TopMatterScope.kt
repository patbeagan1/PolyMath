package dev.patbeagan.latexbuilder

class TopMatterScope(
    config: TopMatterScope.() -> Unit
) : BaseLatexScope() {

    init {
        config()
    }

    fun usePackage(pkg: String): TopMatterScope = apply {
        append("\\usepackage{$pkg}\n")
    }

    fun title(title: String): TopMatterScope = apply {
        append("\\title{$title}\n")
    }

    fun author(author: String): TopMatterScope = apply {
        append("\\author{$author}\n")
    }

    fun date(value: String): TopMatterScope = apply {
        append("\\date{$value}\n")
    }
}