package nl.q42.components

import androidx.compose.runtime.Composable
import nl.q42.common.components.SearchBar
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
import org.jetbrains.compose.resources.painterResource
import sdui_cmp_poc.app.composeapp.generated.resources.Res
import sdui_cmp_poc.app.composeapp.generated.resources.ic_search

@Composable
internal fun SearchBarDrawable(component: SearchBar) {
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
        shape = CircleShape, // <-- Rounded shape applied here
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