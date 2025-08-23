package nl.q42.common.components

class TextComponent(
    var text: String?,
    var color: TextColor?,
    var formatting: TextFormatting?,

    contentId: String?
) : Component(ComponentTypes.TEXT, contentId) {
    constructor(text: String?, contentId: String?) : this(
        text,
        TextColor.PRIMARY,
        TextFormatting.NORMAL,
        contentId
    )
}
