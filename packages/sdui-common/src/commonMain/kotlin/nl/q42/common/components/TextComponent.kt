package nl.q42.common.components

class TextComponent(
    var text: String?,
    var color: TextColor = TextColor.PRIMARY,
    var formatting: TextFormatting = TextFormatting.NORMAL,
    contentId: String
) : Component(ComponentTypes.TEXT, contentId) {
}
