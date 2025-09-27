package dev.patbeagan.latexbuilder

class BookScope(
    config: BookScope.() -> Unit
) : Headings.PartScope({}) {

    init {
        config()
    }

    fun part(title: String, body: Headings.PartScope.() -> Unit): BookScope = apply {
        append("\n\\part{$title}\n")
        append(Headings.PartScope(body).toString())
    }
}

interface Headings {
    open class PartScope(
        config: PartScope.() -> Unit
    ) : ChapterScope({}) {

        init {
            config()
        }

        fun chapter(title: String, body: ChapterScope.() -> Unit): PartScope = apply {
            append("\n\\chapter{$title}\n")
            append(ChapterScope(body).toString())
        }
    }

    open class ChapterScope(
        config: ChapterScope.() -> Unit
    ) : SectionScope() {

        init {
            config()
        }

        fun section(title: String, body: SectionScope.() -> Unit): ChapterScope = apply {
            append("\n\\section{$title}\n")
            append(SectionScope().apply(body).toString())
        }
    }

    open class SectionScope : SubsectionScope() {
        fun subsection(title: String, body: SubsectionScope.() -> Unit): SectionScope = apply {
            append("\n\\subsection{$title}\n")
            append(SubsectionScope().apply(body).toString())
        }
    }

    open class SubsectionScope : SubSubSectionScope() {
        fun subsubsection(title: String, body: SubSubSectionScope.() -> Unit): SubsectionScope = apply {
            append("\n\\subsubsection{$title}\n")
            append(SubSubSectionScope().apply(body).toString())
        }
    }

    open class SubSubSectionScope : ParagraphScope() {
        fun paragraph(title: String, body: ParagraphScope.() -> Unit): SubSubSectionScope = apply {
            append("\n\\paragraph{$title}\n")
            append(ParagraphScope().apply(body).toString())
        }
    }

    open class ParagraphScope : SubParagraphScope() {
        fun subparagraph(title: String, body: SubParagraphScope.() -> Unit): ParagraphScope = apply {
            append("\n\\subsubsection{$title}\n")
            append(SubParagraphScope().apply(body).toString())
        }
    }

    open class SubParagraphScope : TextScope({})
}