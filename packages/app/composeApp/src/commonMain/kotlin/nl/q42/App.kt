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
import io.ktor.client.request.*
import nl.q42.common.ScreenResponse
import nl.q42.common.components.ComponentTypes
import nl.q42.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import nl.q42.common.RequestHeader
import nl.q42.common.components.TextComponent
import nl.q42.common.components.TextFormatting
import nl.q42.common.core.AppIdentity
import nl.q42.common.core.Locale


private val httpClient = HttpClient {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            isLenient = true
        })
    }
}

suspend fun fetchScreen(id: String): ScreenResponse? {
    return try {
        httpClient.get("http://10.0.2.2:8080/screen") {
          parameter("id", id)
            header(RequestHeader.HEADER_APP_LOCALE, Locale.NL_NL.value)
            header(RequestHeader.HEADER_APP_VERSION, 1)
            header(RequestHeader.HEADER_APP_IDENTITY, AppIdentity.calculateAppIdentity(Locale.NL_NL, 1))
        }.body<ScreenResponse>()
    } catch (e: Exception) {
        // Handle exceptions (e.g., network error, parsing error)
        println("Error fetching screen: ${e.message}")
        null
    }
}

@Composable
internal fun DynamicScreen(screenResponse: ScreenResponse?) {
    if (screenResponse == null) {
        Text("Error loading screen or no data.")
        return
    }

    Column(modifier = Modifier.padding(16.dp)) {
        screenResponse.screen.content.forEach { element ->
            when (element.type) {
                ComponentTypes.TEXT -> {
                    element.let {
                        val textComponent = it as TextComponent
                        textComponent.text?.let { text ->
                            Text(
                                text = text,
                                style = when (textComponent.formatting) {
                                    TextFormatting.BOLD -> MaterialTheme.typography.titleLarge
                                    TextFormatting.ITALIC -> MaterialTheme.typography.titleMedium
                                    TextFormatting.UNDERLINE -> MaterialTheme.typography.titleSmall
                                    TextFormatting.NORMAL -> MaterialTheme.typography.titleLarge
                                },
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
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
