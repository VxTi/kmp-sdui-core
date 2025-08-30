package nl.q42.ui.components

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import nl.q42.ViewController

@Composable
internal actual fun MenuBarDrawable(controller: ViewController) {

    val tabs by controller.tabs.collectAsState();
    val selectedTabIndex by controller.selectedTabIndex.collectAsState();

    val coroutineScope = rememberCoroutineScope()

    NavigationBar {
        tabs.forEachIndexed { index, tab ->
            NavigationBarItem(
                icon = { },
                label = { Text(tab.title) },
                selected = selectedTabIndex == index,
                onClick = {
                    if (selectedTabIndex != index) {
                        controller.setSelectedTabIndex(index);
                    }
                }
            )
        }
    }
}