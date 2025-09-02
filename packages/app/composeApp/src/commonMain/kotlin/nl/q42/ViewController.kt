package nl.q42

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nl.q42.common.Event
import nl.q42.common.NavigationEvent
import nl.q42.common.ScreenResponse
import nl.q42.common.screen.Screen
import nl.q42.common.screen.ScreenTab
import nl.q42.core.AppInstance
import nl.q42.core.ServerConnector

internal class ViewController(
    val appInstance: AppInstance = AppInstance.fromConfig(),
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

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())


    fun emitEvents(events: List<Event>) {
        events.forEach { emitEvent(it) }
    }

    fun emitEvent(event: Event) {
        scope.launch {
            when (event) {
                is NavigationEvent -> {
                    val screen = tryGetCachedScreen(event.screenId)
                    println("Navigation action invoked to path: ${event.screenId}");

                    if (screen == null) {
                        fetchScreen(event.screenId, setCurrent = true)
                    } else {
                        setCurrentScreen(screen);
                    }
                }
            }
        }
    }

    /**
     * Preprocesses events. This can mean caching additional data before it's being used,
     * or ensuring linked data is present.
     */
    fun preprocessEvents(events: List<Event>) {
        events.forEach { event -> preprocessEvent(event) }
    }

    fun preprocessEvent(event: Event) {
        when (event) {
            is NavigationEvent -> {
                if (!event.prefetch) return;

                tryPrefetchScreen(event.screenId);
            }
        }
    }

    fun tryPrefetchScreen(screenId: String) {
        println("Prefetching screen");
        val cachedScreen = tryGetCachedScreen(screenId);

        // Screen is already cached, and cache is still usable.
        if (cachedScreen != null) return;

        fetchScreen(screenId, cache = true);
    }

    fun refreshScreen() {
        val currentScreenId = screen.value?.id ?: return;
        scope.launch {
            runLoadableAction {
                fetchScreen(currentScreenId, setCurrent = true, cache = true);
            }
        }
    }

    private fun tryGetCachedScreen(screenId: String): Screen? {
        if (!cachedScreens.containsKey(screenId)) return null;

        return cachedScreens[screenId];
    }

    private fun cacheScreen(screen: Screen) {
        val key = screen.id;

        val previousCachedScreen = cachedScreens[key];

        if (previousCachedScreen != null && previousCachedScreen.hash == screen.hash) {
            // No need to update cache if screen hasn't changed
            return;
        }

        cachedScreens[key] = screen;
    }

    fun fetchInitialScreen() {
        scope.launch {
            runLoadableAction {
                val response = serverConnector.fetch<ScreenResponse>(appInstance, "/")
                mutableTabs.value = response?.tabs ?: emptyList();
                mutableScreen.value = response?.screen
            }
        }
    }

    /**
     * Retrieves a screen from the server, and optionally caches it.
     */
    fun fetchScreen(screenIdentifier: String, setCurrent: Boolean = false, cache: Boolean = false) {
        scope.launch {
            runLoadableAction {
                val response = serverConnector.fetchScreen(screenIdentifier);
                response?.screen?.let { fetchedScreen ->
                    mutableScreen.value = fetchedScreen

                    if (cache) cacheScreen(fetchedScreen)

                    if (setCurrent) setCurrentScreen(fetchedScreen)
                }
            }
        }
    }

    fun setCurrentScreen(screen: Screen) {
        mutableScreen.value = screen;

        val tabIndex = tabs.value.indexOfFirst { tab -> tab.screenId == screen.id };

        if (tabIndex > -1) setSelectedTabIndex(tabIndex)
    }

    private suspend fun runLoadableAction(action: suspend () -> Unit) {
        mutableExternallyLoading.value = true;
        try {
            action()
        } catch (e: Exception) {
            println("An error occurred whilst attempting to execute action: ${e.message}")
        } finally {
            mutableExternallyLoading.value = false;
        }
    }

    /**
     * Sets the selected tab index and updates the screen if it's cached.
     */
    fun setSelectedTabIndex(index: Int) {
        mutableTabIndex.value = index;

        if (cachedScreens.containsKey(tabs.value[index].screenId)) {
            mutableScreen.value = cachedScreens[tabs.value[index].screenId]
            return;
        }

        fetchScreen(tabs.value[index].screenId, setCurrent = true, cache = true)
    }
}