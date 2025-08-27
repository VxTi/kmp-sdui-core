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
sealed class TypedEntity {
    abstract val entityType: String
}

@Serializable
@OptIn(ExperimentalSerializationApi::class)
@JsonClassDiscriminator("entityType")
sealed class Component(
    override val entityType: String
) : TypedEntity() {
    abstract val contentId: String
}

@Serializable
@OptIn(ExperimentalSerializationApi::class)
@JsonClassDiscriminator("entityType")
sealed class Action(
    override val entityType: String
) : TypedEntity()

object ComponentTypes {
    const val TEXT: String = "TEXT_COMPONENT"
    const val IMAGE: String = "IMAGE_COMPONENT"
    const val SPACER: String = "SPACER_COMPONENT"
    const val SEARCH_BAR: String = "SEARCH_BAR_COMPONENT"
    const val BUTTON: String = "BUTTON_COMPONENT"
}

object ActionTypes {
    const val NAVIGATION: String = "navigate"
}

interface Actionable {

}

/** * * * * * * * * * * * * * * * * * * * * * * * * * /
 *               Action definitions                   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * **/
@Serializable
@SerialName(ActionTypes.NAVIGATION)
data class NavigationAction(val path: String) :
    Action(ActionTypes.NAVIGATION)

/** * * * * * * * * * * * * * * * * * * * * * * * * * /
 *              Component definitions                 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * **/
@VersionDependable(until = 2)
@Serializable
@SerialName(ComponentTypes.SPACER)
data class SpacerComponent(
    val size: Int,
    override val contentId: String
) :
    Component(ComponentTypes.SPACER)

@Serializable
@SerialName(ComponentTypes.BUTTON)
data class ButtonComponent(
    val text: String,
    val variant: ButtonVariant,
    val actions: List<Action>,
    override val contentId: String
) : Component(ComponentTypes.BUTTON)

enum class ButtonVariant {
    NORMAL,
    SECONDARY,
    DISABLED,
}

@Serializable
@SerialName(ComponentTypes.TEXT)
data class TextComponent(
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

@Serializable
@SerialName(ComponentTypes.SEARCH_BAR)
data class SearchBarComponent(val placeholder: String, override val contentId: String) :
    Component(ComponentTypes.SEARCH_BAR)

val SDUIPolymorphicSerializer = SerializersModule {
    polymorphic(Component::class) {
        subclass(SpacerComponent::class, SpacerComponent.serializer())
        subclass(ButtonComponent::class, ButtonComponent.serializer())
        subclass(TextComponent::class, TextComponent.serializer())
        subclass(SearchBarComponent::class, SearchBarComponent.serializer())
    }
    polymorphic(Action::class) {
        subclass(NavigationAction::class, NavigationAction.serializer())
    }
}