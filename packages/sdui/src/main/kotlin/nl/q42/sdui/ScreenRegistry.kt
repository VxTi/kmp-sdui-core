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
        val initial = getByIdentifier(DEFAULT_SCREEN_IDENTIFIER, context)

        return when {
            initial != null -> initial
            screens.isNotEmpty() -> screens[0].create(context)
            else -> throw IllegalStateException("No screens available in the registry")
        }

    }

    companion object {
        val SCREEN_TABS: List<ScreenTab> = listOf<ScreenTab>(
            ScreenTab("Home", "home.png", HomeScreen.SCREEN_IDENTIFIER),
            ScreenTab("Search", "search.png", SearchScreen.SCREEN_IDENTIFIER),
            ScreenTab("Profile", "profile.png", ProfileScreen.SCREEN_IDENTIFIER),
            ScreenTab("Settings", "settings.png", SettingsScreen.SCREEN_IDENTIFIER)
        )

        const val DEFAULT_SCREEN_IDENTIFIER = HomeScreen.SCREEN_IDENTIFIER;
    }
}
