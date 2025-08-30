package nl.q42.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kmp_sdui_core.app.composeapp.generated.resources.Res
import kmp_sdui_core.app.composeapp.generated.resources.ic_search
import nl.q42.ViewController
import nl.q42.common.SearchBarComponent
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun SearchBarDrawable(component: SearchBarComponent, controller: ViewController) {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        placeholder = { component.placeholder?.let { Text(it) } },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        leadingIcon = {
            Icon(
                contentDescription = "Search",
                painter = painterResource(Res.drawable.ic_search),
                tint = Color.Black
            )
        },
        shape = CircleShape,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedContainerColor = Color(0xFFA0A0A0),
            unfocusedContainerColor = Color(0xFFA0A0A0),
            disabledContainerColor = Color(0xFFA0A0A0),
            errorContainerColor = Color(0xFFA0A0A0)
        )
    )
}