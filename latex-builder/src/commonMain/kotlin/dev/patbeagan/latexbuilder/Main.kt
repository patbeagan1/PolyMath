package dev.patbeagan.latexbuilder

@DslMarker
annotation class TeXMarker

@TeXMarker
class TeXBuilder {
    private val content = StringBuilder()

    fun text(value: String): TeXBuilder {
        content.append(value)
        return this
    }

    fun bold(body: TeXBuilder.() -> Unit): TeXBuilder {
        content.append("\\textbf{")
        TeXBuilder().apply(body).let {
            content.append(it.toString())
        }
        content.append("}")
        return this
    }

    fun italics(body: TeXBuilder.() -> Unit): TeXBuilder {
        content.append("\\textit{")
        TeXBuilder().apply(body).let {
            content.append(it.toString())
        }
        content.append("}")
        return this
    }

    fun brackets(body: TeXBuilder.() -> Unit): TeXBuilder {
        content.append("\\left[")
        TeXBuilder().apply(body).let {
            content.append(it.toString())
        }
        content.append("\\right]")
        return this
    }

    fun parentheses(body: TeXBuilder.() -> Unit): TeXBuilder {
        content.append("\\left(")
        TeXBuilder().apply(body).let {
            content.append(it.toString())
        }
        content.append("\\right)")
        return this
    }

    fun vector(body: TeXBuilder.() -> Unit): TeXBuilder {
        content.append("\\vec{")
        TeXBuilder().apply(body).let {
            content.append(it.toString())
        }
        content.append("}")
        return this
    }

    fun math(body: TexMathBuilder.() -> Unit): TeXBuilder {
        TexMathBuilder().body()
        return this
    }

    fun rawTeX(value: String): TeXBuilder {
        content.append(value)
        return this
    }

    fun documentClass(cls: String): TeXBuilder {
        content.append("\\documentclass{$cls}\n")
        return this
    }

    fun usePackage(pkg: String): TeXBuilder {
        content.append("\\usepackage{$pkg}\n")
        return this
    }

    fun title(value: String): TeXBuilder {
        content.append("\\title{$value}\n")
        return this
    }

    fun author(value: String): TeXBuilder {
        content.append("\\author{$value}\n")
        return this
    }

    fun date(value: String): TeXBuilder {
        content.append("\\date{$value}\n")
        return this
    }

    fun beginDocument(): TeXBuilder {
        content.append("\\begin{document}\n")
        return this
    }

    fun endDocument(): TeXBuilder {
        content.append("\\end{document}\n")
        return this
    }

    fun makeTitle(): TeXBuilder {
        content.append("\\maketitle\n")
        return this
    }

    fun section(title: String): TeXBuilder {
        content.append("\\section{$title}\n")
        return this
    }

    fun subsection(title: String): TeXBuilder {
        content.append("\\subsection{$title}\n")
        return this
    }

    fun itemize(vararg items: String): TeXBuilder {
        content.append("\\begin{itemize}\n")
        items.forEach { item ->
            content.append("\\item $item\n")
        }
        content.append("\\end{itemize}\n")
        return this
    }

    fun enumerate(vararg items: String): TeXBuilder {
        content.append("\\begin{enumerate}\n")
        items.forEach { item ->
            content.append("\\item $item\n")
        }
        content.append("\\end{enumerate}\n")
        return this
    }

    fun figure(path: String, caption: String, label: String? = null): TeXBuilder {
        content.append("\\begin{figure}\n")
        content.append("\\centering\n")
        content.append("\\includegraphics{$path}\n")
        content.append("\\caption{$caption}\n")
        label?.let {
            content.append("\\label{fig:$it}\n")
        }
        content.append("\\end{figure}\n")
        return this
    }

    fun table(headers: List<String>, rows: List<List<String>>, label: String? = null): TeXBuilder {
        content.append("\\begin{table}\n")
        content.append("\\centering\n")
        content.append("\\begin{tabular}{${"|".repeat(headers.size)}}\n")
        content.append(headers.joinToString(" & ") + "\\\\\n\\hline\n")
        rows.forEach { row ->
            content.append(row.joinToString(" & ") + "\\\\\n")
        }
        content.append("\\end{tabular}\n")
        label?.let {
            content.append("\\label{tab:$it}\n")
        }
        content.append("\\end{table}\n")
        return this
    }

    fun label(key: String): TeXBuilder {
        content.append("\\label{$key}\n")
        return this
    }

    fun reference(key: String): TeXBuilder {
        content.append("~\\ref{$key}\n")
        return this
    }

    fun cite(key: String): TeXBuilder {
        content.append("\\cite{$key}\n")
        return this
    }

    fun footnote(note: String): TeXBuilder {
        content.append("\\footnote{$note}\n")
        return this
    }

    fun hyperlink(url: String, text: String): TeXBuilder {
        content.append("\\href{$url}{$text}\n")
        return this
    }

    fun r(key: String): Any {
        return "\\ref{$key}"
        return this
    }

    fun abstract(text: String): TeXBuilder {
        section("Abstract")
        this.text(text)
        return this
    }

    fun figureWithCaption(path: String, caption: String, label: String? = null): TeXBuilder {
        figure(path, caption, label)
        text("See Figure ${r(label ?: "")} below.")
        return this
    }

    fun simpleTable(headers: List<String>, rows: List<List<String>>): TeXBuilder {
        table(headers, rows, null)
        return this
    }

    fun paragraph(text: String): TeXBuilder {
        this.text("\n\n$text\n\n")
        return this
    }

    fun bibliographyEntry(label: String, text: String): TeXBuilder {
        rawTeX("\\bibitem{$label} $text")
        return this
    }

    fun keywords(vararg words: String): TeXBuilder {
        section("Keywords")
        text(words.joinToString(", "))
        return this
    }

    fun acknowledgements(text: String): TeXBuilder {
        section("Acknowledgements")
        this.text(text)
        return this
    }

    fun thesisChapter(title: String, content: TeXBuilder.() -> Unit): TeXBuilder {
        this.content.append("\\chapter{$title}\n")
        this.apply(content)
        return this
    }

    fun authorAffiliation(author: String, affiliation: String): TeXBuilder {
        text("$author\\\\")
        text("$affiliation\\\\")
        return this
    }

    fun multipleAuthors(vararg authors: Pair<String, String>): TeXBuilder {
        authors.forEach { (author, affiliation) ->
            authorAffiliation(author, affiliation)
        }
        return this
    }

    fun appendix(title: String, content: TeXBuilder.() -> Unit): TeXBuilder {
        this.content.append("\\appendix\n\\section{$title}\n")
        this.apply(content)
        return this
    }

    fun genericBook(title: String, author: String, body: TeXBuilder.() -> Unit): TeXBuilder {
        documentClass("book")
        this.title(title)
        this.author(author)
        beginDocument()
        makeTitle()
        body()
        endDocument()
        return this
    }

    fun report(title: String, author: String, body: TeXBuilder.() -> Unit): TeXBuilder {
        documentClass("report")
        this.title(title)
        this.author(author)
        beginDocument()
        makeTitle()
        abstract("Provide an abstract here.")
        body()
        acknowledgements("Thank your peers here.")
        endDocument()
        return this
    }

    fun thesis(title: String, author: String, body: TeXBuilder.() -> Unit): TeXBuilder {
        documentClass("report")
        this.title(title)
        this.author(author)
        beginDocument()
        makeTitle()
        abstract("Provide an abstract for your thesis here.")
        thesisChapter("Introduction") {}
        body()  // Main content like chapters, references, etc.
        acknowledgements("Thank your advisors and colleagues here.")
        appendix("Supplementary Data") {}
        endDocument()
        return this
    }

    fun article(title: String, author: String, body: TeXBuilder.() -> Unit) {
        documentClass("article")
        this.title(title)
        this.author(author)
        beginDocument()
        makeTitle()
        abstract("Provide an abstract for your article here.")
        body()  // Main content, references, etc.
        endDocument()
    }

    inner class BookBuilder {
        fun chapter(title: String, body: ChapterBuilder.() -> Unit) {
            content.append("\\chapter{$title}\n")
            ChapterBuilder().body()
        }
    }

    inner class ChapterBuilder {
        fun section(title: String, body: SectionBuilder.() -> Unit) {
            content.append("\\section{$title}\n")
            SectionBuilder().body()
        }

        fun text(text: String) {
            content.append(text + "\n")
        }
    }

    inner class SectionBuilder {
        fun subsection(title: String, body: SubsectionBuilder.() -> Unit) {
            content.append("\\subsection{$title}\n")
            SubsectionBuilder().body()
        }

        fun text(text: String) {
            content.append(text + "\n")
        }
    }

    inner class SubsectionBuilder {
        fun text(text: String) {
            content.append(text + "\n")
        }
    }

    fun book(title: String, author: String, body: BookBuilder.() -> Unit) {
        documentClass("book")
        this.title(title)
        this.author(author)
        beginDocument()
        makeTitle()
        BookBuilder().body()
        endDocument()
    }

    override fun toString(): String = content.toString()
}

fun texLegacy(body: TeXBuilder.() -> Unit): String {
    return TeXBuilder().apply(body).toString()
}
