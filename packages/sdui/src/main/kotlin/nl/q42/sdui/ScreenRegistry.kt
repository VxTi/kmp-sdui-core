package nl.q42.sdui

import nl.q42.common.screen.Screen
import nl.q42.common.screen.ScreenTab
import nl.q42.sdui.screen.*
import org.springframework.stereotype.Service

@Service
class ScreenRegistry(private val screens: MutableList<Screen>) {

    val all: MutableList<Screen>
        get() = this.screens

    fun getByIdentifier(screenIdentifier: String?): Screen? {
        return this.screens.stream()
            .filter { screen: Screen? -> screen?.name.equals(screenIdentifier) }
            .findFirst()
            .orElse(null)
    }

    companion object {
        val SCREEN_TABS: Array<ScreenTab> = arrayOf<ScreenTab>(
            ScreenTab("tab.home", "home.png", HomeScreen.SCREEN_NAME),
            ScreenTab("tab.search", "search.png", SearchScreen.SCREEN_NAME),
            ScreenTab("tab.profile", "profile.png", ProfileScreen.SCREEN_NAME),
            ScreenTab("tab.settings", "settings.png", SettingsScreen.SCREEN_NAME)
        )
    }
}
