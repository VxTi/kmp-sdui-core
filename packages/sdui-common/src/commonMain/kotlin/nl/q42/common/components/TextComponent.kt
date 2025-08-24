package nl.q42.common.components

import kotlinx.serialization.*

@Serializable
@SerialName(ComponentTypes.TEXT)
class TextComponent(
    val text: String,
    val color: TextColor,
    var size: TextSize,
    val formatting: TextFormatting,
    override val contentId: String
) : Component(ComponentTypes.TEXT) {
    constructor(
        text: String,
        contentId: String,
        color: TextColor = TextColor.PRIMARY,
        size: TextSize = TextSize.NORMAL,
        formatting: TextFormatting = TextFormatting.NORMAL
    ) : this(text, color, size, formatting, contentId)
}

enum class TextColor {
    PRIMARY,
    SECONDARY,
    TERTIARY,
    DISABLED,
    ERROR,
    SUCCESS,
    INFO,
    CUSTOM;
}

enum class TextFormatting {
    NORMAL,
    BOLD,
    ITALIC,
    UNDERLINE;
}

enum class TextSize {
    EXTRA_SMALL,
    SMALL,
    NORMAL,
    LARGE,
    EXTRA_LARGE,
}