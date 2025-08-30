package nl.q42.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.q42.ViewController
import nl.q42.common.SpacerComponent

@Composable
internal fun SpacerDrawable(component: SpacerComponent, controller: ViewController) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height((4 * component.size).dp)
    )
}