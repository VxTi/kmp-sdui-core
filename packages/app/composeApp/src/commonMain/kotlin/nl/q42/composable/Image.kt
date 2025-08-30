package nl.q42.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import nl.q42.ViewController
import nl.q42.common.ImageComponent

@Composable
internal fun ImageComponentDrawable(component: ImageComponent, controller: ViewController) {
    AsyncImage(
        model = component.url,
        contentDescription = component.alt,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.fillMaxWidth(),
    )
}