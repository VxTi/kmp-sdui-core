package nl.q42.ui.composition

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import nl.q42.common.*
import nl.q42.common.screen.Screen
import nl.q42.ui.components.*

@Composable
internal fun ServerDrivenScreen(screenResponse: ScreenResponse?) {
    if (screenResponse == null) {
        return
    }

    DynamicScreen(screenResponse.screen)
}

@Composable
internal fun DynamicScreen(screenResponse: Screen) {
    LazyColumn {
        items(
            items = screenResponse.content,
            key = { it.contentId }
        ) { element -> DrawableComponentSequence(element) }
    }
}

@Composable
internal fun DrawableComponentSequence(component: Component) {
    when (component) {
        is TextComponent -> TextComponentDrawable(component)
        is SpacerComponent -> SpacerDrawable(component)
        is SearchBarComponent -> SearchBarDrawable(component)
        is ButtonComponent -> ButtonDrawable(component)
        is ImageComponent -> ImageComponentDrawable(component)
    }
}