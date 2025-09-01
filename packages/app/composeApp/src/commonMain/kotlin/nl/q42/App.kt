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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nl.q42.composable.NavigationBar
import nl.q42.composable.ServerDrivenScreen
import nl.q42.composable.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
@Composable
internal fun App(viewController: ViewController) = AppTheme {

    val isLoading by viewController.externallyLoading.collectAsStateWithLifecycle()
    val screen by viewController.screen.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { viewController.fetchInitialScreen(); }

    Scaffold(
        bottomBar = { NavigationBar(viewController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier.fillMaxSize().padding(12.dp),
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
