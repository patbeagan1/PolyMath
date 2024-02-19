package dev.patbeagan.latexbuilder

fun texDocument(body: DocumentScope.() -> Unit): String =
    DocumentScope().apply(body).toString()

@DslMarker
annotation class TeXDocumentMarker

@TeXDocumentMarker
class DocumentScope : BaseLatexScope() {

    fun documentClass(type: String): DocumentScope = apply {
        append("\\documentclass{$type}\n")
    }

    fun beginDocument(): DocumentScope = apply {
        append("\\begin{document}\n")
    }

    fun endDocument(): DocumentScope = apply {
        append("\\end{document}\n")
    }

    fun makeTitle(): DocumentScope = apply {
        append("\\maketitle\n")
    }

    fun content(body: Body): DocumentScope = apply {
        append(TextScope(body).toString())
    }

    fun book(topMatter: TopMatterScope, scope: BookScope.() -> Unit) {
        documentClass("book")
        append(topMatter.toString())
        makeTitle()
        beginDocument()
        append(BookScope(scope).toString())
        endDocument()
    }

    fun report(topMatter: TopMatterScope, scope: Headings.PartScope.() -> Unit) {
        documentClass("report")
        append(topMatter.toString())
        makeTitle()
        beginDocument()
        append(Headings.PartScope(scope).toString())
        endDocument()
    }

    fun article(topMatter: TopMatterScope, scope: Headings.ChapterScope.() -> Unit) {
        documentClass("article")
        append(topMatter.toString())
        makeTitle()
        beginDocument()
        append(Headings.ChapterScope(scope).toString())
        endDocument()
    }

    fun body(body: Body) {
        beginDocument()
        content(body)
        endDocument()
    }

    fun abstract(title: String? = null, body: AbstractSectionScope.() -> Unit): DocumentScope = apply {
        if (title != null) {
            append("""\renewcommand{\abstractname}{${title}}""")
        }
        append("""\begin{abstract}""")
        append(AbstractSectionScope().apply(body).toString())
        append("""\end{abstract}""")
    }
}
