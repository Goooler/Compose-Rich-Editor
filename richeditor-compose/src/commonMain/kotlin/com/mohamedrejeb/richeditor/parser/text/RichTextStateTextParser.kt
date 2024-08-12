package com.mohamedrejeb.richeditor.parser.text

import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastForEachIndexed
import com.mohamedrejeb.richeditor.model.RichSpan
import com.mohamedrejeb.richeditor.model.RichTextState
import com.mohamedrejeb.richeditor.paragraph.type.ParagraphType.Companion.startText
import com.mohamedrejeb.richeditor.parser.RichTextStateParser

internal object RichTextStateTextParser : RichTextStateParser<String> {
    override fun encode(input: String): RichTextState {
        error("Don't support encoding from text to RichTextState.")
    }

    override fun decode(richTextState: RichTextState): String = buildString {
        val richParagraphList = richTextState.richParagraphList
        richParagraphList.fastForEachIndexed { i, richParagraph ->
            append(richParagraph.type.startText)

            appendRichSpanList(
                richSpanList = richParagraph.children,
            )

            if (i != richParagraphList.lastIndex) {
                appendLine()
            }
        }
    }

    private fun StringBuilder.appendRichSpanList(
        richSpanList: List<RichSpan>,
    ) {
        richSpanList.fastForEach { richSpanStyle ->
            append(richSpanStyle.text)

            if (richSpanStyle.children.isNotEmpty())
                appendRichSpanList(
                    richSpanList = richSpanStyle.children,
                )
        }
    }
}