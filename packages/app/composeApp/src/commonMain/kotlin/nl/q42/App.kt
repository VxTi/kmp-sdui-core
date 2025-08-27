package nl.q42

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import nl.q42.common.ScreenResponse
import nl.q42.core.AppInstance
import nl.q42.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import nl.q42.ui.composition.DynamicScreen
import nl.q42.ui.composition.fetchScreen


val appInstance: AppInstance = AppInstance()

@Preview
@Composable
internal fun App() = AppTheme {
    var screenResponse by rememberSaveable { mutableStateOf<ScreenResponse?>(null) }
    var isLoading by rememberSaveable { mutableStateOf(true) }
    var error by rememberSaveable { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()
    var dragAmount by remember { mutableStateOf(0f) }

    fun reloadScreen(appInstance: AppInstance) {
        isLoading = true
        try {
            coroutineScope.launch {
                screenResponse = fetchScreen("home", appInstance)
                isLoading = false
                error = null
                println("Received screen: $screenResponse")
            }
        } catch (e: Exception) {
            error = "Failed to load screen: ${e.message}"
            println(error)
            isLoading = false
        }
    }

    LaunchedEffect(Unit) {
        reloadScreen(appInstance)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        if (dragAmount > 200) {
                            reloadScreen(appInstance)
                        }
                        dragAmount = 0f
                    },
                    onDrag = { _, drag -> dragAmount = drag.y }
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else if (error != null) {
            Text("Error: $error")
        } else if (screenResponse != null) {
            DynamicScreen(screenResponse!!)
        } else {
            Spacer( modifier = Modifier.padding(vertical = 10.dp))
            Text("No screen data available.")
        }
    }
}
