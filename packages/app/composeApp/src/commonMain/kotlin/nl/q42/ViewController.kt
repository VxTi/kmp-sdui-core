package nl.q42

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking
import nl.q42.common.ScreenResponse
import nl.q42.common.screen.Screen
import nl.q42.common.screen.ScreenTab
import nl.q42.core.AppInstance
import nl.q42.core.ServerConnector

internal class ViewController(
    val appInstance: AppInstance,
    val serverConnector: ServerConnector = ServerConnector(appInstance)
) {

    private val mutableExternallyLoading = MutableStateFlow(false);
    private val mutableTabs = MutableStateFlow<List<ScreenTab>>(emptyList())
    private val mutableTabIndex = MutableStateFlow(0);
    private val mutableScreen = MutableStateFlow<Screen?>(null);

    private val cachedScreens = mapOf<String, Screen?>().toMutableMap()

    val tabs: StateFlow<List<ScreenTab>> = mutableTabs.asStateFlow()
    val screen: StateFlow<Screen?> = mutableScreen.asStateFlow();
    val selectedTabIndex: StateFlow<Int> = mutableTabIndex.asStateFlow();
    val externallyLoading: StateFlow<Boolean> = mutableExternallyLoading.asStateFlow();


    fun refreshScreen() {
        val currentScreenIdentifier = screen.value?.identifier ?: return;
        runBlocking {
            temporarilySuspend {
                fetchScreen(currentScreenIdentifier);
            }
        }
    }

    private fun cacheScreen(response: ScreenResponse?) {
        if (response?.screen == null) return;

        val key = response.screen.identifier;

        val previousCachedScreen = cachedScreens[key];

        if (previousCachedScreen != null && previousCachedScreen.hash == response.screen.hash) {
            // No need to update cache if screen hasn't changed
            return;
        }

        cachedScreens[key] = response.screen;
    }

    suspend fun fetchInitialScreen() {
        temporarilySuspend {
            val response = serverConnector.fetch<ScreenResponse>(appInstance, "/")
            mutableTabs.value = response?.tabs ?: emptyList();
            mutableScreen.value = response?.screen

            cacheScreen(response);
        }
    }

    suspend fun fetchScreen(screenIdentifier: String) {
        temporarilySuspend {
            val response = serverConnector.fetchScreen(screenIdentifier);
            mutableScreen.value = response?.screen;
            cacheScreen(response);
        }
    }

    private suspend fun temporarilySuspend(action: suspend () -> Unit) {
        mutableExternallyLoading.value = true;
        action()
        mutableExternallyLoading.value = false;
    }

    /**
     * Sets the selected tab index and updates the screen if it's cached.
     */
    fun setSelectedTabIndex(index: Int) {
        mutableTabIndex.value = index;

        if (cachedScreens.containsKey(tabs.value[index].screenIdentifier)) {
            mutableScreen.value = cachedScreens[tabs.value[index].screenIdentifier]
        } else {
            runBlocking {
                fetchScreen(tabs.value[index].screenIdentifier)
            }
        }
    }
}