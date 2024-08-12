package com.mohamedrejeb.richeditor.parser.text

import com.mohamedrejeb.richeditor.parser.markdown.RichTextStateMarkdownParser
import kotlin.test.Test
import kotlin.test.assertEquals

class RichTextStateTextParserMarkdownTest {

    @Test
    fun testBold() {
        val markdown = "**Hello World!**"
        val expectedText = "Hello World!"

        val state = RichTextStateMarkdownParser.encode(markdown)
        val actualText = RichTextStateTextParser.decode(state)

        assertEquals(
            expected = expectedText,
            actual = actualText,
        )
    }

    @Test
    fun testBoldWithNestedItalic() {
        val markdown = "**Hello *World!***"
        val expectedText = "Hello World!"

        val state = RichTextStateMarkdownParser.encode(markdown)
        val actualText = RichTextStateTextParser.decode(state)

        assertEquals(
            expected = expectedText,
            actual = actualText,
        )
    }

    @Test
    fun testUnderline() {
        val markdown = "<u>Hello World!</u>"
        val expectedText = "Hello World!"

        val state = RichTextStateMarkdownParser.encode(markdown)
        val actualText = RichTextStateTextParser.decode(state)

        assertEquals(
            expected = expectedText,
            actual = actualText,
        )
    }

    @Test
    fun testLineThrough() {
        val markdown = "~~Hello World!~~"
        val expectedText = "Hello World!"

        val state = RichTextStateMarkdownParser.encode(markdown)
        val actualText = RichTextStateTextParser.decode(state)

        assertEquals(
            expected = expectedText,
            actual = actualText,
        )
    }

    @Test
    fun testLineBreak() {
        val markdown = """
            Hello
            <br>
            World!
        """.trimIndent()
        val expectedText = """
            Hello
            
            
            World!
        """.trimIndent()

        val state = RichTextStateMarkdownParser.encode(markdown)
        val actualText = RichTextStateTextParser.decode(state)

        assertEquals(
            expected = expectedText,
            actual = actualText,
        )
    }
}