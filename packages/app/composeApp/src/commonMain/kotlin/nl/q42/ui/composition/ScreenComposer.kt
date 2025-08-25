package nl.q42.ui.composition

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import nl.q42.common.ScreenResponse
import nl.q42.common.components.*
import nl.q42.ui.components.SearchBarDrawable
import nl.q42.ui.components.SpacerDrawable
import nl.q42.ui.components.TextComponentDrawable

@Composable
internal fun DynamicScreen(screenResponse: ScreenResponse?) {
    if (screenResponse == null) {
        return
    }

    LazyColumn {
        items(
            items = screenResponse.screen.content,
            key = { it.contentId }
        ) { element ->
            when (element.type) {
                ComponentTypes.TEXT -> TextComponentDrawable(element as TextComponent)
                ComponentTypes.SPACER -> SpacerDrawable(element as SpacerComponent)
                ComponentTypes.SEARCH_BAR -> SearchBarDrawable(element as SearchBar)
            }
        }
    }
}