package nl.q42.common.components

enum class TextColor {
    PRIMARY,
    SECONDARY,
    TERTIARY,
    DISABLED,
    ERROR,
    SUCCESS,
    INFO,
    CUSTOM;

    override fun toString(): String {
        return String.format("%d", this.ordinal)
    }

    companion object {
        fun from(value: Int): TextColor? {
            val values: Array<TextColor?> = TextColor.entries.toTypedArray()
            val withinRange = value >= 0 && value < values.size

            return if (!withinRange) TextColor.PRIMARY else
                values[value]
        }
    }
}
