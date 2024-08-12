package com.mohamedrejeb.richeditor.parser.text

import com.mohamedrejeb.richeditor.parser.html.RichTextStateHtmlParser
import kotlin.test.Test
import kotlin.test.assertEquals

class RichTextStateTextParserHtmlTest {

    @Test
    fun testRemoveHtmlTextExtraSpaces() {
        val html = """
            Hello       World!      Welcome to 
            
             Compose Rich Text Editor!
        """.trimIndent()
        val expectedText = """
            Hello       World!      Welcome to 
            
             Compose Rich Text Editor!
        """.trimIndent()

        val state = RichTextStateHtmlParser.encode(html)
        val actualText = RichTextStateTextParser.decode(state)

        assertEquals(
            expected = expectedText,
            actual = actualText,
        )
    }

    @Test
    fun testParsingSimpleHtmlWithBrBackAndForth() {
        val html = "<br><p>Hello World&excl;</p>"
        val expectedText = """
            
            Hello World!
        """.trimIndent()

        val state = RichTextStateHtmlParser.encode(html)
        val actualText = RichTextStateTextParser.decode(state)

        assertEquals(
            expected = expectedText,
            actual = actualText,
        )
    }

    @Test
    fun testHtmlWithImage() {
        val html = """
            <!DOCTYPE html>
            <html>
            <body>

            <h1>The img element</h1>

            <img src="https://picsum.photos/200/300" alt="Girl in a jacket" width="500" height="600">

            </body>
            </html>
        """.trimIndent()
        val expectedText = """
            The img element
        """.trimIndent()

        val state = RichTextStateHtmlParser.encode(html)
        val actualText = RichTextStateTextParser.decode(state)
        assertEquals(
            expected = expectedText,
            actual = actualText,
        )
    }

    @Test
    fun testHtmlWithBrAndImage() {
        val html = """
            <!DOCTYPE html>
            <html>
            <body>

            <h1>The img element</h1>
            <br>
            <img src="https://picsum.photos/200/300" alt="Girl in a jacket" width="500" height="600">

            </body>
            </html>
        """.trimIndent()
        val expectedText = """
            The img element
            
        """.trimIndent()

        val state = RichTextStateHtmlParser.encode(html)
        val actualText = RichTextStateTextParser.decode(state)

        assertEquals(
            expected = expectedText,
            actual = actualText,
        )
    }

    @Test
    fun testHtmlWithEmptyBlockElements1() {
        val html = """
            <!DOCTYPE html>
            <html>
            <body>

            <p><p>dd  dd<span> second</span></p></p>

            </body>
            </html>
        """.trimIndent()
        val expectedText = """
            dd  dd second
        """.trimIndent()

        val state = RichTextStateHtmlParser.encode(html)
        val actualText = RichTextStateTextParser.decode(state)

        assertEquals(
            expected = expectedText,
            actual = actualText,
        )
    }

    @Test
    fun testHtmlWithEmptyBlockElements2() {
        val html =
            """
                <!DOCTYPE html>
                <html>
                <body>
    
                <p><p><p> second</p></p></p>
    
                </body>
                </html>
            """.trimIndent()
        val expectedText = """
            second
        """.trimIndent()

        val state = RichTextStateHtmlParser.encode(html)
        val actualText = RichTextStateTextParser.decode(state)

        assertEquals(
            expected = expectedText,
            actual = actualText,
        )
    }

    @Test
    fun testBrInMiddleOrParagraph() {
        val html = """
            <h1>Hello<br>World!</h1>
        """.trimIndent()
        val expectedText = """
            Hello
            World!
        """.trimIndent()

        val state = RichTextStateHtmlParser.encode(html)
        val actualText = RichTextStateTextParser.decode(state)

        assertEquals(
            expected = expectedText,
            actual = actualText,
        )
    }
}