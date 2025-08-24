package nl.q42

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.q42.common.ScreenResponse
import nl.q42.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import nl.q42.core.DynamicScreen
import nl.q42.core.fetchScreen

@Preview
@Composable
internal fun App() = AppTheme {
    var screenResponse by remember { mutableStateOf<ScreenResponse?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        isLoading = true
        try {
            // Ensure your local server is running and accessible from the emulator/device
            // For Android emulator, localhost is 10.0.2.2
            // For iOS simulator or physical devices, use your machine's local IP address
            screenResponse = fetchScreen("home")
        } catch (e: Exception) {
            error = "Failed to load screen: ${e.message}"
            println(error)
        } finally {
            println("Received screen: $screenResponse")
            isLoading = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else if (error != null) {
            Text("Error: $error")
        } else if (screenResponse != null) {
            DynamicScreen(screenResponse)
        } else {
            Text("No screen data available.")
        }
    }
}
