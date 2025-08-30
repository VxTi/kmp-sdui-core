package nl.q42

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.q42.ui.components.MenuBarDrawable
import nl.q42.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import nl.q42.ui.composition.ServerDrivenScreen


@Preview
@Composable
internal fun App(viewController: ViewController) = AppTheme {

    var isLoading by rememberSaveable { mutableStateOf(true) }

    val screen by viewController.screen.collectAsState()

    LaunchedEffect(Unit) {
        isLoading = true;
        viewController.fetchInitialScreen();
        isLoading = false;
    }

    Scaffold(
        bottomBar = { MenuBarDrawable(viewController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier.fillMaxSize().padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isLoading) {
                    CircularProgressIndicator()
                } else if (screen != null) {
                    ServerDrivenScreen(screen, viewController)
                } else {
                    Text(
                        "No screen data available.",
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            }
        }
    }
}
