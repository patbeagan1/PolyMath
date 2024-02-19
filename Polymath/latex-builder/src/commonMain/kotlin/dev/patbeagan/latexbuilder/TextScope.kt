package dev.patbeagan.latexbuilder

import dev.patbeagan.latexbuilder.TextScope.FormatType.*
import dev.patbeagan.latexbuilder.TextScope.InlineMathFormat.*
import dev.patbeagan.latexbuilder.TextScope.JustificationType.*
import dev.patbeagan.math.base.GenericSymbols

typealias Body = TextScope.() -> Unit

open class TextScope(
    config: TextScope.() -> Unit
) : BaseLatexScope(), GenericSymbols {

    init {
        config()
    }

    fun bold(text: String) = format(Bold, text)
    fun bold(body: Body) = format(Bold, body)

    fun italic(text: String) = format(Italic, text)
    fun italic(body: Body) = format(Italic, body)

    fun underline(text: String) = format(Underline, text)
    fun underline(body: Body) = format(Underline, body)

    fun format(type: FormatType, text: String): TextScope = format(type) { text(text) }
    fun format(type: FormatType, body: Body): TextScope = when (type) {
        Underline -> command("underline", TextScope(body))
        Italic -> command("textit", TextScope(body))
        Bold -> command("textbf", TextScope(body))
        Roman -> command("textrm", TextScope(body))
        SansSerif -> command("textsf", TextScope(body))
        Typewriter -> command("texttt", TextScope(body))
        Medium -> command("textmd", TextScope(body))
        Upright -> command("textup", TextScope(body))
        Slanted -> command("textsl", TextScope(body))
        SmallCaps -> command("textsc", TextScope(body))
        Emphasized -> command("emph", TextScope(body))
        DocumentFont -> command("textnormal", TextScope(body))
    }

    fun justification(type: JustificationType = FlushLeft, body: Body): TextScope = when (type) {
        Center -> beginEndScope("center", TextScope(body))
        FlushLeft -> beginEndScope("flushleft", TextScope(body))
        FlushRight -> beginEndScope("flushright", TextScope(body))
    }

    fun quotation(body: Body): TextScope = beginEndScope("quotation", TextScope(body))
    fun quote(body: Body): TextScope = beginEndScope("quote", TextScope(body))
    fun brackets(body: Body): TextScope = startStopScope("[", "]", TextScope(body))
    fun parentheses(body: Body): TextScope = startStopScope("(", ")", TextScope(body))

    fun text(text: String): TextScope = apply {
        append(text)
    }

    fun text(text: () -> String): TextScope = apply {
        append(text())
    }

    fun space() = apply { append(" ") }

    fun newline(): TextScope = apply {
        append("\\newline\n")
    }

    fun emptyline(): TextScope = apply {
        append("\n\n")
    }

    fun line(text: String) = line { text(text) }
    fun line(body: Body) = apply {
        append(TextScope(body).toString())
        newline()
    }

    fun para(body: Body) = apply {
        append("\n\n")
        text(TextScope(body).toString())
        append("\n\n")
    }

    fun para(s: String) = apply {
        append("\n\n")
        text(s)
        append("\n\n")
    }

    fun equation(equationContent: String): TextScope = apply {
        append("\n\\begin{equation}\n$equationContent\n\\end{equation}\n")
    }

    fun equation(body: MathScope.() -> Unit): TextScope = apply  {
        /*
         *    \[...\]
    \begin{displaymath}...\end{displaymath}
    \begin{equation}...\end{equation}
         */
        append("\n\\begin{equation}")
        append(MathScope(body).toString())
        append("""\end{equation}""")
    }

    fun inlineMath(
        format: InlineMathFormat = Begin,
        body: MathScope.() -> Unit
    ): TextScope = apply {
        when (format) {
            Paren -> append("\\(${MathScope(body)}\\)")
            Dollar -> append("\$ ${MathScope(body)} \$")
            Begin -> append("\\begin{math}${MathScope(body)}\\end{math}")
        }
    }

    fun itemize(vararg items: String): TextScope = apply {
        append("\\begin{itemize}\n")
        items.forEach { item -> append("\\item $item\n") }
        append("\\end{itemize}\n")
    }

    fun enumerate(vararg items: String): TextScope = apply {
        append("\\begin{enumerate}\n")
        items.forEach { item -> append("\\item $item\n") }
        append("\\end{enumerate}\n")
    }

    enum class FormatType {
        Underline, Italic, Bold,
        Roman, SansSerif, Typewriter, Medium, Upright,
        Slanted, SmallCaps, Emphasized, DocumentFont,
    }

    enum class JustificationType {
        Center, FlushLeft, FlushRight
    }

    enum class InlineMathFormat {
        Paren, Dollar, Begin
    }
}

fun TextScope.loremIpsum() = text(buildString {
    append("Lorem ipsum dolor sit amet, consectetur adipiscing elit,")
    append(" sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
    append(" Suspendisse ultrices gravida dictum fusce ut placerat orci nulla.")
    append(" Ac turpis egestas integer eget aliquet nibh. Laoreet suspendisse")
    append(" interdum consectetur libero. Non quam lacus suspendisse faucibus interdum")
    append(" posuere lorem. Ligula ullamcorper malesuada proin libero nunc consequat")
    append(" interdum varius. Pellentesque adipiscing commodo elit at imperdiet dui accumsan.")
    append(" Et leo duis ut diam quam nulla porttitor massa. Aliquam eleifend mi in nulla")
    append(" posuere sollicitudin aliquam. Facilisi morbi tempus iaculis urna id volutpat.")
    append(" Sit amet nisl purus in mollis nunc sed id. Non odio euismod lacinia at quis risus")
    append(" sed vulputate. Viverra aliquet eget sit amet tellus. Tempor orci eu lobortis")
    append(" elementum nibh tellus. Nulla at volutpat diam ut venenatis tellus in.")
})