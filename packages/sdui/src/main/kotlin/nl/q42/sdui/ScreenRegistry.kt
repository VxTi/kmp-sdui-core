package nl.q42.sdui

import nl.q42.common.screen.Screen
import nl.q42.common.screen.ScreenTab
import nl.q42.core.RequestContext
import nl.q42.sdui.screen.*
import org.springframework.stereotype.Service

@Service
class ScreenRegistry(private val screens: MutableList<ScreenInstance>) {

    val all: MutableList<ScreenInstance>
        get() = this.screens

    fun getByIdentifier(screenIdentifier: String, context: RequestContext): Screen? {
        return this.screens.stream()
            .filter { screen: ScreenInstance? -> screen?.name().equals(screenIdentifier) }
            .findFirst()
            .map { screen: ScreenInstance -> screen.create(context) }
            .orElse(null)
    }

    fun defaultScreen(context: RequestContext): Screen {
        val initial = getByIdentifier(DEFAULT_SCREEN, context)

        return when {
            initial != null -> initial
            screens.isNotEmpty() -> screens[0].create(context)
            else -> throw IllegalStateException("No screens available in the registry")
        }

    }

    companion object {
        val SCREEN_TABS: List<ScreenTab> = listOf<ScreenTab>(
            ScreenTab("tab.home", "home.png", HomeScreen.SCREEN_NAME),
            ScreenTab("tab.search", "search.png", SearchScreen.SCREEN_NAME),
            ScreenTab("tab.profile", "profile.png", ProfileScreen.SCREEN_NAME),
            ScreenTab("tab.settings", "settings.png", SettingsScreen.SCREEN_NAME)
        )

        const val DEFAULT_SCREEN = HomeScreen.SCREEN_NAME;
    }
}
