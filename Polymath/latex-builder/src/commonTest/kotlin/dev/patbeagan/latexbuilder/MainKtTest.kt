package dev.patbeagan.latexbuilder

import dev.patbeagan.latexbuilder.TextScope.FormatType.Bold
import dev.patbeagan.latexbuilder.TextScope.FormatType.Italic
import dev.patbeagan.latexbuilder.TextScope.InlineMathFormat.Paren
import dev.patbeagan.math.base.Dictionary
import dev.patbeagan.math.geometry.geometryScope
import main.dsl.mathnum.Variable
import physicsAcousticsScope
import physicsOpticsScope
import kotlin.test.Test
import kotlin.test.assertEquals

class MainKtTest {
    @Test
    fun fractions_integrals_matrices() {
        val expression = texDocument {
            content {
                equation {
                    fraction(
                        {
                            integral("0", "1") {
                                literal("x^2")
                            }
                        }, {
                            sqrt {
                                literal("a^2 + b^2")
                            }
                        }
                    )
                    summation("i=1", "n") {
                        literal("i")
                    }
                    matrix(listOf(
                        { literal("1 & 0") },
                        { literal("0 & 1") }
                    ))
                }
            }
        }

        assertEquals(
            """
\begin{equation}\frac{\int_{0}^{1} x^2\, dx}{\sqrt{a^2 + b^2}}\sum_{i=1}^{n} i
\begin{bmatrix}1 & 0\\0 & 1\\\end{bmatrix}\end{equation}""",
            expression
        )
    }

    @Test
    fun equations_alignment_brackets_formatting() {

        val expression = texDocument {
            content {
                equation {
                    this.literal("E").literal(" = ").literal("mc^2")
                }

                equation {
                    literal("a")
                    literal(" &= ")
                    literal("b + c")
                }

                equation {
                    literal("d")
                    literal(" &= ")
                    vector {
                        literal("e")
                    }
                }

                bold {
                    text("This is bold")
                }
            }
        }

        assertEquals(
            """
\begin{equation}E = mc^2\end{equation}
\begin{equation}a &= b + c\end{equation}
\begin{equation}d &= \vec{e}\end{equation}\textbf{This is bold}""",
            expression
        )
    }

    @Test
    fun basic_document_extensions() {
        val document = texDocument {
            article(TopMatterScope {
                usePackage("amsmath")
                title("My TeX Document")
                author("John Doe")
                date("\\today")
            }) {
                section("Introduction") {
                    text("This is the introduction of my document.")
                    subsection("Background") {
                        text("Here is some background.")
                        itemize(
                            "First item",
                            "Second item",
                            "Third item"
                        )
                    }
                    subsection("Details") {
                        enumerate(
                            "Detail 1",
                            "Detail 2",
                            "Detail 3"
                        )
                    }
                }
            }
        }

        assertEquals(
            """\documentclass{article}
\usepackage{amsmath}
\title{My TeX Document}
\author{John Doe}
\date{\today}
\maketitle
\begin{document}

\section{Introduction}
This is the introduction of my document.
\subsection{Background}
Here is some background.\begin{itemize}
\item First item
\item Second item
\item Third item
\end{itemize}

\subsection{Details}
\begin{enumerate}
\item Detail 1
\item Detail 2
\item Detail 3
\end{enumerate}
\end{document}
""", document
        )
    }

    @Test
    fun figures_tables_references() {
        val document = texLegacy {
            beginDocument()

            section("Figures and Tables")
            text("Here is a figure:")
            figure("path/to/image.png", "Sample Image", "sampleimg")
            text("Refer to Figure ")
            reference("fig:sampleimg")
            text(" for more details.")
            table(
                headers = listOf("Header1", "Header2"),
                rows = listOf(
                    listOf("Data1", "Data2"),
                    listOf("Data3", "Data4")
                ),
                label = "sampletable"
            )
            text("Refer to Table ")
            reference("tab:sampletable")
            text(" above.")
            footnote("This is a footnote.")
            text("Click ")
            hyperlink("https://www.example.com", "here")
            text(" to visit our website.")

            endDocument()
        }

        println(document)
        // Outputs a structured TeX document
    }

    @Test
    fun abstract_paragraph_bibliography() {
        val document = texLegacy {
            documentClass("article")
            usePackage("amsmath")

            title("My Advanced TeX Document")
            author("John Doe")
            date("\\today")
            makeTitle()

            beginDocument()
            abstract("This document demonstrates some higher-level DSL methods for common TeX usage patterns.")
            section("Introduction")
            paragraph("This is the introduction of the document.")
            figureWithCaption("path/to/image.png", "A Beautiful Image", "img1")
            simpleTable(
                headers = listOf("Header1", "Header2"),
                rows = listOf(
                    listOf("Data1", "Data2"),
                    listOf("Data3", "Data4")
                )
            )
            paragraph("We can also include references easily!")
            text("For more details, see ")
            hyperlink("https://www.example.com", "this source")
            text(".")
            section("Bibliography")
            bibliographyEntry("source1", "Author A. Title A. Journal A. Year A.")
            bibliographyEntry("source2", "Author B. Title B. Journal B. Year B.")
            endDocument()
        }

        println(document)
        // Outputs a structured TeX document with high-level functions
    }

    @Test
    fun paragraphs_keywords_chapters() {
        val document = texLegacy {
            documentClass("book") // For thesis-style formatting
            usePackage("amsmath")
            title("Advanced TeX Document with DSL")
            multipleAuthors(
                "John Doe" to "University A",
                "Jane Smith" to "University B"
            )
            date("\\today")
            beginDocument()
            makeTitle()
            abstract("This is an abstract for a thesis-style document using an advanced TeX DSL.")
            thesisChapter("Introduction") {
                paragraph("This is the introductory chapter of the thesis.")
                figureWithCaption("path/to/image.png", "Sample Image", "imgIntro")
            }
            thesisChapter("Methods") {
                paragraph("Here we describe our methods.")
                simpleTable(
                    headers = listOf("Parameter", "Value"),
                    rows = listOf(
                        listOf("Alpha", "0.05"),
                        listOf("Beta", "0.25")
                    )
                )
            }
            keywords("TeX", "DSL", "Kotlin")
            acknowledgements("We thank our colleagues and mentors for their invaluable insights.")
            appendix("Supplementary Data") {
                paragraph("In this appendix, we provide supplementary data and figures.")
            }
            // ...
            endDocument()
        }

        println(document)
    }

    @Test
    fun article_types() {
        val document = texLegacy {
            thesis("Advanced TeX Document with DSL", "John Doe") {
                thesisChapter("Methodology") {
                    paragraph("Here we describe our advanced methods.")
                }
            }
        }
        println(document)
    }

    @Test
    fun test_scopes() {
        val document = texDocument {
            book(TopMatterScope {
                title("Advanced TeX Document with DSL")
                author("John Doe")
            }) {
                chapter("Introduction") {
                    section("Background") {
                        text("This is the background information.")
                        subsection("Importance") {
                            text("Here we discuss the importance of our topic.")
                        }
                        subsection("Second thing of importance") {
                            text("Discussion about the second thing of importance")
                        }
                    }
                }
            }
        }
        println(document)
    }

    @Test
    fun test_builder_method() {
        DocumentScope()
            .abstract { text("abstract") }
            .content {
                equation("E = MC").toString().also { println(it) }
            }
    }

    @Test
    fun test_greek_letters() {
        texDocument {
            documentClass("article")
            body {
                listOf(
                    symAlpha, symBeta, symGamma, symRho, symSigma, symDelta,
                    symEpsilon, symZeta, symEta, symTheta, symIota,
                    symKappa, symLambda, symMu, symNu, symXi, symOmicron, symPi, symTau, symUpsilon,
                    symPhi, symChi, symPsi, symOmega,
                ).forEach {
                    line(it)
                }
            }
        }.also { println(it) }
    }

    @Test
    fun test_physics_optics_scope() {
        texDocument {
            documentClass("article")
            body {
                Dictionary.physicsOpticsScope {
                    line("Snell's Law")
                    equation(snellsLaw().toLatex())
                    line("Light speed in Medium")
                    equation(lightSpeedInMedium().toLatex())
                    line("waveEquation")
                    equation(waveEquation().toLatex())
                    line("lensFormula")
                    equation(lensFormula().toLatex())
                    line("lensPowerFormula")
                    equation(lensPowerFormula().toLatex())
                }
            }
        }.also { println(it) }
    }

    @Test
    fun test_physics_acoustics_scope() {
        texDocument {
            documentClass("article")
            body {
                Dictionary.physicsAcousticsScope {
                    line("waveEquation")
                    equation(waveEquation().toLatex())
                    line("intensityFormula")
                    equation(intensityFormula().toLatex())
                    line("speedOfSoundInMedium")
                    equation(speedOfSoundInMedium().toLatex())
                    line("decibelLevelFormula")
                    equation(decibelLevelFormula().toLatex())
                    line("pressureVariation")
                    equation(pressureVariation().toLatex())
                }
            }
        }.also { println(it) }
    }

    @Test
    fun test_geometry_scope() {
        texDocument {
            documentClass("article")
            body {
                Dictionary.geometryScope {
//                    text(
//                        GeometryShapeD2.Square().squareArea(10.0).explain()
//                    )
//                    text(
//                        GeometryShapeD2.EquilateralTriangle().area(10.0, 7.0).explain()
//                    )
                }
            }
        }.also { println(it) }
    }

    @Test
    fun test_new_document_builder___article_version() {
        println(texDocument {
            documentClass("article")
            body {
                line {
                    text {
                        "The well known Pythagorean theorem "
                    }.inlineMath(Paren) {
                        literal("x^2 + y^2 = z^2")
                    }
                }
                line("was proved to be invalid for other exponents. ")
                line("Meaning the next equation has no integer solutions:")
                line("Printing out a couple of greek letters $symAlpha, $symEpsilon, $symBeta.")
                equation("x^n + y^n = z^n")
            }
        })
    }

    @Test
    fun test_math_chomsky_hierarchy() {
        assertEquals("""\documentclass{article}
\begin{document}
The well known Pythagorean theorem \(x^2 + y^2 = z^2\)\newline
was proved to be invalid for other exponents. \newline
Meaning the next equation has no integer solutions:\newline
Printing out a couple of greek letters \alpha, \epsilon, \beta.\newline

\begin{equation}
x^n + y^n = z^n
\end{equation}

\begin{equation}\{a^{n} \cdot b^{n} \cdot c^{n}|n<0\}\end{equation}\end{document}
""", texDocument {
            documentClass("article")
            body {
                line {
                    text {
                        "The well known Pythagorean theorem "
                    }.inlineMath(Paren) {
                        literal("x^2 + y^2 = z^2")
                    }
                }
                line("was proved to be invalid for other exponents. ")
                line("Meaning the next equation has no integer solutions:")
                line("Printing out a couple of greek letters $symAlpha, $symEpsilon, $symBeta.")
                equation("x^n + y^n = z^n")

                equation {
                    val a = Variable("a")
                    val b = Variable("b")
                    val c = Variable("c")
                    val n = Variable("n")
                    suchThat(
                        {
                            literal(n.glyph)
                            literal("<")
                            literal("0")
                        },
                        { literal(((a pow n) * (b pow n) * (c pow n)).toLatex()) }
                    )
                }
            }
        })
    }

    @Test
    fun testing_lorem_ipsum___article_mode_rendering() {
        val actual = (texDocument {
            report(TopMatterScope {
                title("Lorem Ipsum testing article")
                author("Hello world")
                date(today())
            }) {
                para { loremIpsum() }
                para { loremIpsum() }
                para {
                    text("As you can see, the pythagorean theorem is ").inlineMath {
                        literal("a^2 + b^2 = c^2")
                    }
                    text("It is one of the most famous theorems in history.")
                }
                para { loremIpsum() }

                equation {
                    literal("a &= b + c")
                }

                equation {
                    literal("d &= ")
                    vector {
                        literal("efg")
                    }
                }

                para { loremIpsum() }
            }
        })

        val expected = """\documentclass{report}
\title{Lorem Ipsum testing article}
\author{Hello world}
\date{\today}
\maketitle
\begin{document}


Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Suspendisse ultrices gravida dictum fusce ut placerat orci nulla. Ac turpis egestas integer eget aliquet nibh. Laoreet suspendisse interdum consectetur libero. Non quam lacus suspendisse faucibus interdum posuere lorem. Ligula ullamcorper malesuada proin libero nunc consequat interdum varius. Pellentesque adipiscing commodo elit at imperdiet dui accumsan. Et leo duis ut diam quam nulla porttitor massa. Aliquam eleifend mi in nulla posuere sollicitudin aliquam. Facilisi morbi tempus iaculis urna id volutpat. Sit amet nisl purus in mollis nunc sed id. Non odio euismod lacinia at quis risus sed vulputate. Viverra aliquet eget sit amet tellus. Tempor orci eu lobortis elementum nibh tellus. Nulla at volutpat diam ut venenatis tellus in.



Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Suspendisse ultrices gravida dictum fusce ut placerat orci nulla. Ac turpis egestas integer eget aliquet nibh. Laoreet suspendisse interdum consectetur libero. Non quam lacus suspendisse faucibus interdum posuere lorem. Ligula ullamcorper malesuada proin libero nunc consequat interdum varius. Pellentesque adipiscing commodo elit at imperdiet dui accumsan. Et leo duis ut diam quam nulla porttitor massa. Aliquam eleifend mi in nulla posuere sollicitudin aliquam. Facilisi morbi tempus iaculis urna id volutpat. Sit amet nisl purus in mollis nunc sed id. Non odio euismod lacinia at quis risus sed vulputate. Viverra aliquet eget sit amet tellus. Tempor orci eu lobortis elementum nibh tellus. Nulla at volutpat diam ut venenatis tellus in.



As you can see, the pythagorean theorem is \begin{math}a^2 + b^2 = c^2\end{math}It is one of the most famous theorems in history.



Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Suspendisse ultrices gravida dictum fusce ut placerat orci nulla. Ac turpis egestas integer eget aliquet nibh. Laoreet suspendisse interdum consectetur libero. Non quam lacus suspendisse faucibus interdum posuere lorem. Ligula ullamcorper malesuada proin libero nunc consequat interdum varius. Pellentesque adipiscing commodo elit at imperdiet dui accumsan. Et leo duis ut diam quam nulla porttitor massa. Aliquam eleifend mi in nulla posuere sollicitudin aliquam. Facilisi morbi tempus iaculis urna id volutpat. Sit amet nisl purus in mollis nunc sed id. Non odio euismod lacinia at quis risus sed vulputate. Viverra aliquet eget sit amet tellus. Tempor orci eu lobortis elementum nibh tellus. Nulla at volutpat diam ut venenatis tellus in.


\begin{equation}a &= b + c\end{equation}
\begin{equation}d &= \vec{efg}\end{equation}

Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Suspendisse ultrices gravida dictum fusce ut placerat orci nulla. Ac turpis egestas integer eget aliquet nibh. Laoreet suspendisse interdum consectetur libero. Non quam lacus suspendisse faucibus interdum posuere lorem. Ligula ullamcorper malesuada proin libero nunc consequat interdum varius. Pellentesque adipiscing commodo elit at imperdiet dui accumsan. Et leo duis ut diam quam nulla porttitor massa. Aliquam eleifend mi in nulla posuere sollicitudin aliquam. Facilisi morbi tempus iaculis urna id volutpat. Sit amet nisl purus in mollis nunc sed id. Non odio euismod lacinia at quis risus sed vulputate. Viverra aliquet eget sit amet tellus. Tempor orci eu lobortis elementum nibh tellus. Nulla at volutpat diam ut venenatis tellus in.

\end{document}
"""
        assertEquals(expected, actual)
    }

    @Test
    fun test_new_document_builder() {
        val document = texDocument {
            book(TopMatterScope {
                title("Advanced TeX Document with DSL")
                author("John Doe")
            }) {
                part("Part test") {
                    para {
                        text("hello. ")
                            .parentheses { text("This is in parentheses") }
                            .text(". And this text is not.")
                    }
                    para(buildString {
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
                    para(buildString {
                        append("Ipsum dolor sit amet consectetur adipiscing elit duis tristique sollicitudin.")
                        append(" Augue neque gravida in fermentum et sollicitudin ac orci phasellus.")
                        append(" Nisi porta lorem mollis aliquam ut porttitor leo a. At urna")
                        append(" condimentum mattis pellentesque id nibh tortor id aliquet. Iaculis urna")
                        append(" id volutpat lacus laoreet non curabitur. Gravida rutrum quisque non")
                        append(" tellus orci ac auctor augue. Pulvinar elementum integer enim neque volutpat")
                        append(" ac tincidunt. Mauris ultrices eros in cursus turpis massa tincidunt dui.")
                        append(" In pellentesque massa placerat duis ultricies lacus sed. Sit amet volutpat")
                        append(" consequat mauris nunc congue nisi vitae suscipit. Duis ultricies lacus sed")
                        append(" turpis tincidunt id aliquet risus feugiat. Semper feugiat nibh sed pulvinar")
                        append(" proin gravida hendrerit lectus a. Accumsan sit amet nulla facilisi morbi")
                        append(" tempus iaculis urna id. Vitae congue eu consequat ac felis. Aliquam vestibulum")
                        append(" morbi blandit cursus risus. Dignissim enim sit amet venenatis urna cursus.")
                        append(" Pellentesque elit eget gravida cum. Ut placerat orci nulla pellentesque")
                        append(" dignissim. In egestas erat imperdiet sed euismod nisi porta. Diam volutpat")
                        append(" commodo sed egestas egestas fringilla.")
                    })
                    quotation {
                        text(buildString {
                            append("Morbi tristique senectus et netus et malesuada fames ac. Eu turpis egestas pretium")
                            append(" aenean pharetra magna. Felis bibendum ut tristique et egestas quis.")
                            append(" Senectus et netus et malesuada fames ac turpis egestas. Accumsan lacus")
                            append(" vel facilisis volutpat est velit egestas dui. Egestas egestas")
                            append(" fringilla phasellus faucibus scelerisque eleifend donec. Augue neque")
                            append(" gravida in fermentum et sollicitudin ac orci. Massa tincidunt dui")
                            append(" ut ornare. Purus faucibus ornare suspendisse sed nisi lacus. Sit")
                            append(" amet nisl suscipit adipiscing bibendum est ultricies integer quis.")
                            append(" Et netus et malesuada fames ac turpis egestas sed tempus. Interdum varius")
                            append(" sit amet mattis vulputate enim. Mi sit amet mauris commodo quis imperdiet")
                            append(" massa tincidunt nunc. Aenean euismod elementum nisi quis eleifend.")
                        })
                    }
                    para(buildString {
                        append("Nullam non nisi est sit amet facilisis magna etiam. ")
                        append("Arcu cursus vitae congue mauris rhoncus aenean vel. ")
                        append("Vitae tempus quam pellentesque nec nam aliquam sem. Duis ut diam quam nulla ")
                        append("porttitor massa id neque aliquam. Hac habitasse platea dictumst quisque sagittis. ")
                        append("Platea dictumst quisque sagittis purus sit amet volutpat. Ornare lectus sit amet ")
                        append("est placerat. Arcu bibendum at varius vel pharetra vel. Rhoncus dolor purus non")
                        append(" enim praesent elementum. Auctor urna nunc id cursus metus. Et malesuada fames")
                        append(" ac turpis. Nulla malesuada pellentesque elit eget gravida cum sociis natoque")
                        append(" penatibus.")
                    })
                    para(buildString {
                        append("Dolor magna eget est lorem ipsum dolor sit amet. Pharetra pharetra massa massa ultricies ")
                        append("mi quis hendrerit. Porttitor lacus luctus accumsan tortor. Tellus cras ")
                        append("adipiscing enim eu turpis. Posuere ac ut consequat semper viverra. Tincidunt ")
                        append("ornare massa eget egestas. Massa ultricies mi quis hendrerit dolor magna eget. ")
                        append("Quis viverra nibh cras pulvinar mattis nunc sed blandit libero. Ipsum dolor sit ")
                        append("amet consectetur. Fermentum posuere urna nec tincidunt praesent semper feugiat.")
                    })

                    para {
                        bold {
                            italic { text("testing b/i text") }
                            text("testing b text")
                        }
                    }

                    italic("Testing i text")
                    text("Testing normal text")


                    chapter("Introduction") {
                        section("Background") {
                            text("This is the background information.")
                            newline()
                            newline()
                            text("There is something")
                                .format(Bold) { text(" very ") }
                                .text("important, that I need to")
                                .format(Italic) { text(" share ") }
                                .text("with the headmaster as soon as possible.")


                            equation {
                                fraction(
                                    {
                                        integral("0", "1") {
                                            literal("x^2")
                                        }
                                    }, {
                                        sqrt {
                                            literal("a^2 + b^2")
                                        }
                                    }
                                )
                            }
                            equation {
                                summation("i=1", "n") {
                                    literal("i")
                                }
                            }
                            equation {
                                matrix(listOf(
                                    { literal("1 & 0") },
                                    { literal("0 & 1") }
                                ))
                            }

                            subsection("Subsection Title") {
                                text("Here we discuss the importance of our topic.")
                            }


                            equation {
                                integral("0", "7") {
                                    fraction({
                                        literal("300 + x")
                                    }, {
                                        literal("6")
                                    })
                                }
                            }
                            equation("E = mc^2")
                            equation("a &= b + c")
                            equation("d &= e - f")
                        }
                    }

                }

            }
        }
        println(document)
    }
}

