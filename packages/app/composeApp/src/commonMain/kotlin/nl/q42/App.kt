package nl.q42

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import nl.q42.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import nl.q42.common.screen.Screen;

private val httpClient = HttpClient {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            isLenient = true
        })
    }
}

suspend fun fetchScreen(url: String): Screen? {
    return try {
        httpClient.get(url).body<Screen>()
    } catch (e: Exception) {
        // Handle exceptions (e.g., network error, parsing error)
        println("Error fetching screen: ${e.message}")
        null
    }
}

@Composable
internal fun DynamicScreen(screenResponse: Screen?) {
    if (screenResponse == null) {
        Text("Error loading screen or no data.")
        return
    }

    Column(modifier = Modifier.padding(16.dp)) {
        screenResponse.screen.forEach { element ->
            when (element.type) {
                "text" -> {
                    element.content?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                }
                // TODO: Add cases for other element types
                else -> {
                    Text("Unsupported element type: ${element.type}")
                }
            }
        }
    }
}

@Preview
@Composable
internal fun App() = AppTheme {
    var screenResponse by remember { mutableStateOf<Screen?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        isLoading = true
        try {
            // Ensure your local server is running and accessible from the emulator/device
            // For Android emulator, localhost is 10.0.2.2
            // For iOS simulator or physical devices, use your machine's local IP address
            screenResponse = fetchScreen("http://10.0.2.2:8080/screen?id=home")
        } catch (e: Exception) {
            error = "Failed to load screen: ${e.message}"
            println(error)
        } finally {
            isLoading = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
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
