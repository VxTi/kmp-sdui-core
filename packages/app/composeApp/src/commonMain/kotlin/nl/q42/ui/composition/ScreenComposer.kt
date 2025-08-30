package nl.q42.ui.composition

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import nl.q42.ViewController
import nl.q42.common.*
import nl.q42.common.screen.Screen
import nl.q42.ui.components.*

@Composable
internal fun ServerDrivenScreen(screen: Screen?, controller: ViewController) {
    if (screen == null) {
        return
    }

    DynamicContentList(screen.content, controller)
}

@Composable
internal fun DynamicContentList(components: List<Component>, controller: ViewController) {
    Column {
        reduceDuplicateComponents(components)
            .forEach { element -> DrawableComponentSequence(element, controller) }
    }
}

// Removes components with duplicate contentId, keeping only the first occurrence
fun reduceDuplicateComponents(components: List<Component>): List<Component> {
    return components.groupBy { it.contentId }
        .filter { it.value.size == 1 }
        .flatMap { it.value }
}


@Composable
internal fun DrawableComponentSequence(component: Component, controller: ViewController) {
    when (component) {
        is TextComponent -> TextComponentDrawable(component, controller)
        is SpacerComponent -> SpacerDrawable(component, controller)
        is SearchBarComponent -> SearchBarDrawable(component, controller)
        is ButtonComponent -> ButtonDrawable(component, controller)
        is ImageComponent -> ImageComponentDrawable(component,controller)
        is ScrollableContainer -> ScrollContainerDrawable(component,controller)
    }
}