package nl.q42.ui.composition

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import nl.q42.common.*
import nl.q42.ui.components.*

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
            when (element) {
                is TextComponent -> TextComponentDrawable(element)
                is SpacerComponent -> SpacerDrawable(element)
                is SearchBarComponent -> SearchBarDrawable(element)
                is ButtonComponent -> ButtonDrawable(element)
            }
        }
    }
}