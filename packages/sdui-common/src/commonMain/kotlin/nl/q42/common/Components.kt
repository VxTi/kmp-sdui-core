package nl.q42.common

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

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
sealed class ServerComponent(
    override val objectType: String
) : TypedObject() {
    abstract val contentId: String
}

@Serializable
sealed class Event(
    override val objectType: String
) : TypedObject()

@Serializable
sealed class ListItem(
    override val objectType: String,
) : TypedObject() {
    abstract val itemId: String
    abstract val events: List<Event>
}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                 Event definitions                   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */
@Serializable
@SerialName(ActionType.NAVIGATION)
data class NavigationEvent(val path: String) : Event(ActionType.NAVIGATION)

/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *               Component definitions                 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */
@Serializable
@SerialName(ComponentType.SPACER)
data class SpacerComponent(
    val size: Int,
    override val contentId: String
) : ServerComponent(ComponentType.SPACER)

@Serializable
@SerialName(ComponentType.BUTTON)
data class ButtonComponent(
    val text: String,
    val variant: ButtonVariant,
    val interactionEvents: List<Event>,
    override val contentId: String
) : ServerComponent(ComponentType.BUTTON)

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
) : ServerComponent(ComponentType.TEXT) {
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
) : ServerComponent(ComponentType.SEARCH_BAR)

@Serializable
@SerialName(ComponentType.IMAGE)
@RequiresAppVersion(min = 3)
data class ImageComponent(
    val url: String,
    val alt: String,
    val interactionEvents: List<Event>? = null,
    override val contentId: String,
) : ServerComponent(ComponentType.IMAGE)

@Serializable
@SerialName(ComponentType.SCROLLABLE_CONTAINER)
data class ScrollableContainer(
    val content: List<ServerComponent>,
    val direction: ScrollDirection = ScrollDirection.VERTICAL,
    override val contentId: String
) : ServerComponent(ComponentType.SCROLLABLE_CONTAINER)

enum class ScrollDirection {
    HORIZONTAL,
    VERTICAL,
    BOTH
}

@Serializable
@SerialName(ComponentType.LIST_ITEM_CONTAINER)
data class ListItemContainer(
    val items: List<ListItem>,
    val title: String?,
    override val contentId: String
) : ServerComponent(ComponentType.LIST_ITEM_CONTAINER)

@Serializable
@SerialName(ListItemType.TRANSACTION_ITEM)
data class TransactionListItem(
    val merchantName: String,
    val transactionDate: String,
    val amount: String,
    val currency: CurrencyType,
    val merchantThumbnailUrl: String? = null,
    override val events: List<Event> = emptyList(),
    override val itemId: String
): ListItem(ListItemType.TRANSACTION_ITEM)

enum class CurrencyType {
    EUR,
    USD,
    CAD,
    GBP
}