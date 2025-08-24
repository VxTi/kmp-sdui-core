package nl.q42.common.components

enum class TextFormatting {
    NORMAL,
    BOLD,
    ITALIC,
    UNDERLINE;

    fun from(value: Int): TextFormatting? {
        val values: Array<TextFormatting?> = TextFormatting.entries.toTypedArray()
        val withinRange = value >= 0 && value < values.size

        return if (!withinRange) TextFormatting.NORMAL else
            values[value]
    }
}
