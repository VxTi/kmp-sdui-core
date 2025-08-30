package nl.q42.common

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

/** * * * * * * * * * * * * * * * * * * * * * * * * * /
 * Primary definitions used by all components         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * **/
@Serializable
@OptIn(ExperimentalSerializationApi::class)
@JsonClassDiscriminator("objectType")
sealed class TypedObject {
    abstract val objectType: String;
}

@Serializable
sealed class Component(
    override val objectType: String
) : TypedObject() {
    abstract val contentId: String
}


@Serializable
sealed class Event(
    override val objectType: String
) : TypedObject()

object ComponentType {
    const val TEXT: String = "TEXT_COMPONENT"
    const val IMAGE: String = "IMAGE_COMPONENT"
    const val SPACER: String = "SPACER_COMPONENT"
    const val SEARCH_BAR: String = "SEARCH_BAR_COMPONENT"
    const val BUTTON: String = "BUTTON_COMPONENT"

    const val SCROLLABLE_CONTAINER: String = "SCROLLABLE_CONTAINER"
}

object ActionType {
    const val NAVIGATION: String = "navigate"
}

/** * * * * * * * * * * * * * * * * * * * * * * * * * /
 *               Action definitions                   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * **/
@Serializable
@SerialName(ActionType.NAVIGATION)
data class NavigationEvent(val path: String) : Event(ActionType.NAVIGATION)

/** * * * * * * * * * * * * * * * * * * * * * * * * * /
 *              Component definitions                 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * **/
@VersionDependable(until = 2)
@Serializable
@SerialName(ComponentType.SPACER)
data class SpacerComponent(
    val size: Int,
    override val contentId: String
) : Component(ComponentType.SPACER)

@Serializable
@SerialName(ComponentType.BUTTON)
data class ButtonComponent(
    val text: String,
    val variant: ButtonVariant,
    val interactionEvents: List<Event>,
    override val contentId: String
) : Component(ComponentType.BUTTON)

enum class ButtonVariant {
    NORMAL,
    SECONDARY,
    DISABLED,
}

@Serializable
@SerialName(ComponentType.TEXT)
data class TextComponent(
    val text: String,
    val color: TextColor,
    var size: TextSize,
    val formatting: TextFormatting,
    override val contentId: String
) : Component(ComponentType.TEXT) {
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

@Serializable
@SerialName(ComponentType.SEARCH_BAR)
data class SearchBarComponent(
    val placeholder: String?,
    override val contentId: String
) : Component(ComponentType.SEARCH_BAR)

@Serializable
@SerialName(ComponentType.IMAGE)
data class ImageComponent(
    val url: String,
    val alt: String?,
    val interactionEvents: List<Event>? = null,
    override val contentId: String,
) : Component(ComponentType.IMAGE)

@Serializable
@SerialName(ComponentType.SCROLLABLE_CONTAINER)
data class ScrollableContainer(
    val content: List<Component>,
    val direction: ScrollDirection = ScrollDirection.VERTICAL,
    override val contentId: String
): Component(ComponentType.SCROLLABLE_CONTAINER)

enum class ScrollDirection {
    HORIZONTAL,
    VERTICAL,
    BOTH
}