package nl.q42

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import nl.q42.common.ScreenResponse
import nl.q42.common.screen.ScreenTab
import nl.q42.core.AppInstance
import nl.q42.core.ServerConnector
import nl.q42.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import nl.q42.ui.composition.DynamicScreen
import nl.q42.ui.composition.ServerDrivenScreen
import nl.q42.ui.composition.fetchScreen


val appInstance: AppInstance = AppInstance()
val serverConnector = ServerConnector(appInstance)

@Preview
@Composable
internal fun App() = AppTheme {
    var screenResponse by rememberSaveable { mutableStateOf<ScreenResponse?>(null) }
    var isLoading by rememberSaveable { mutableStateOf(true) }
    var error by rememberSaveable { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()
    var dragAmount by remember { mutableStateOf(0f) }

    var tabs by rememberSaveable { mutableStateOf<List<ScreenTab>>(listOf()) };
    var selectedTabId by rememberSaveable { mutableStateOf<String?>(null) }

    fun loadScreenForTab(tabId: String) {
        isLoading = true
        selectedTabId = tabId
        coroutineScope.launch {
            try {
                // Assuming your serverConnector.fetchScreen takes the tabId or a path derived from it
                screenResponse = serverConnector.fetchScreen(tabId) // Or derive path like "/$tabId"
                error = null
            } catch (e: Exception) {
                error = "Failed to load screen for $tabId: ${e.message}"
                println(error)
                screenResponse = null // Clear previous screen on error
            } finally {
                isLoading = false
            }
        }
    }

    fun reloadCurrentScreen() {
        val currentTab = selectedTabId
        if (currentTab != null) {
            loadScreenForTab(currentTab)
        } else if (tabs.isNotEmpty()) {
            // Fallback to the first tab if no tab is selected yet but tabs are available
            loadScreenForTab(tabs.first().screenIdentifier)
        } else {
            // Initial load or if no tabs are defined yet
            isLoading = true
            coroutineScope.launch {
                try {
                    val initialResponse = serverConnector.fetch<ScreenResponse>(appInstance, "/")
                    tabs = initialResponse.tabs ?: emptyList()
                    if (tabs.isNotEmpty() && selectedTabId == null) {
                        // If tabs are fetched and no tab was selected, select and load the first one
                        val firstTabId = tabs.first().screenIdentifier
                        selectedTabId = firstTabId
                        screenResponse = serverConnector.fetchScreen(firstTabId) // Fetch screen for the first tab
                    } else if (tabs.isEmpty()){
                        // If there are no tabs, load the default screen
                        screenResponse = initialResponse
                    }
                    error = null
                } catch (e: Exception) {
                    error = "Failed to load initial data: ${e.message}"
                    println(error)
                } finally {
                    isLoading = false
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        isLoading = true;
        try {
            screenResponse = serverConnector.fetch<ScreenResponse>(appInstance, "/");
        } catch (e: Exception) {
            error = "Failed to load initial screen: ${e.message}";
            println(error);
        } finally {
            isLoading = false;
        }

        tabs = (screenResponse?.tabs ?: tabs);
        selectedTabId = tabs.firstOrNull()?.screenIdentifier;
    }

    Scaffold(
        bottomBar = {
            if (tabs.isNotEmpty()) {
                NavigationBar {
                    tabs.forEach { tab ->
                        NavigationBarItem(
                            icon = {  },
                            label = { Text(tab.title) },
                            selected = selectedTabId == tab.screenIdentifier,
                            onClick = {
                                if (selectedTabId != tab.screenIdentifier) { // Avoid reloading if the same tab is clicked
                                    loadScreenForTab(tab.screenIdentifier)
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) { // Use Box to allow content to be placed correctly by Scaffold
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp) // Apply your main content padding here
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragEnd = {
                                if (dragAmount > 200) {
                                    reloadCurrentScreen() // Reload the current tab's screen
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
                } else if (screenResponse?.screen != null) { // Check if screen data is present
                    ServerDrivenScreen(screenResponse!!)
                } else if (tabs.isNotEmpty() && screenResponse?.screen == null && !isLoading) {
                    // This case handles when tabs are present, not loading, but no specific screen content is loaded (e.g. after an error on tab switch)
                    Text("Select a tab to view content or pull to refresh.")
                }
                else {
                    Spacer(modifier = Modifier.padding(vertical = 20.dp))
                    Text("No screen data available.")
                }
            }
        }
    }
}
