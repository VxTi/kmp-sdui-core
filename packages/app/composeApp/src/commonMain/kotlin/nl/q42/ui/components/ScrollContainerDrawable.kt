package nl.q42.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nl.q42.ViewController
import nl.q42.common.ScrollableContainer
import nl.q42.ui.composition.DynamicContentList

@Composable
internal fun ScrollContainerDrawable(component: ScrollableContainer, controller: ViewController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            DynamicContentList(component.content, controller)
        }
    }
}