package nl.q42.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nl.q42.ViewController
import nl.q42.common.ScrollableContainer

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